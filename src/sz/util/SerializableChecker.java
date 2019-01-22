package sz.util;

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.Externalizable;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.io.ObjectStreamField;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/**
 * Utility class that analyzes objects for non-serializable nodes. Construct
 * with the object you want to check, and then call
 * {@link #writeObject(Object)}. When a non-serializable object is found, a
 * {@link NotSerializableException} is thrown with a message that shows the
 * trace up to the not- serializable object. The exception is thrown for the
 * first non-serializable instance it encounters, so multiple problems will not
 * be shown.
 * <p>
 * As this class depends heavily on JDK's serialization internals using
 * introspection, analyzing may not be possible, for instance when the runtime
 * environment does not have sufficient rights to set fields accessible that
 * would otherwise be hidden. You should call
 * {@link SerializableChecker#isAvailable()} to see whether this class can
 * operate properly. If it doesn't, you should fall back to e.g. re- throwing/
 * printing the {@link NotSerializableException} you probably got before using
 * this class.
 * </p>
 * <p>
 * Modified by Adam Crume to remove all Wicket-specific logic and add various
 * minor improvements.
 * </p>
 *
 * @author eelcohillenius
 * @author Al Maw
 * @author Adam Crume
 */
public class SerializableChecker extends ObjectOutputStream
{
	/**
	 * Lightweight identity hash table which maps objects to integer handles,
	 * assigned in ascending order (comes from {@link ObjectOutputStream}).
	 */
	private static class HandleTable
	{
		/** factor for computing size threshold */
		private final float loadFactor;

		/** maps handle value -> next candidate handle value */
		private int[ ] next;

		/** maps handle value -> associated object */
		private Object[ ] objs;

		/** number of mappings in table/next available handle */
		private int size;

		/** maps hash value -> candidate handle value */
		private int[ ] spine;

		/** size threshold determining when to expand hash spine */
		private int threshold;

		/**
		 * Construct.
		 */
		public HandleTable( )
		{
			this( 16, 0.75f );
		}

		/**
		 * Construct.
		 *
		 * @param initialCapacity
		 * @param loadFactor
		 */
		public HandleTable( int initialCapacity, float loadFactor )
		{
			this.loadFactor = loadFactor;
			spine = new int[ initialCapacity ];
			next = new int[ initialCapacity ];
			objs = new Object[ initialCapacity ];
			threshold = (int) ( initialCapacity * loadFactor );
			clear( );
		}

		/**
		 * Assigns next available handle to given object, and returns handle value.
		 * Handles are assigned in ascending order starting at 0.
		 *
		 * @param obj
		 * @return
		 */
		public int assign( Object obj )
		{
			if ( size >= next.length )
			{
				growEntries( );
			}
			if ( size >= threshold )
			{
				growSpine( );
			}
			insert( obj, size );
			return size++;
		}

		/**
		 * Clears this table.
		 */
		public void clear( )
		{
			Arrays.fill( spine, -1 );
			Arrays.fill( objs, 0, size, null );
			size = 0;
		}

		/**
		 * Whether this table contains the provided object.
		 *
		 * @param obj
		 *            object to check
		 * @return whether it contains the provided object
		 */
		public boolean contains( Object obj )
		{
			return lookup( obj ) != -1;
		}

		/**
		 * Looks up and returns handle associated with given object, or -1 if no mapping
		 * found.
		 *
		 * @param obj
		 * @return
		 */
		public int lookup( Object obj )
		{
			if ( size == 0 )
			{
				return -1;
			}
			int index = hash( obj ) % spine.length;
			for ( int i = spine[ index ]; i >= 0; i = next[ i ] )
			{
				if ( objs[ i ] == obj )
				{
					return i;
				}
			}
			return -1;
		}

		/**
		 * @return The number of elements
		 */
		public int size( )
		{
			return size;
		}

		private void growEntries( )
		{
			int newLength = ( next.length << 1 ) + 1;
			int[ ] newNext = new int[ newLength ];
			System.arraycopy( next, 0, newNext, 0, size );
			next = newNext;

			Object[ ] newObjs = new Object[ newLength ];
			System.arraycopy( objs, 0, newObjs, 0, size );
			objs = newObjs;
		}

		private void growSpine( )
		{
			spine = new int[ ( spine.length << 1 ) + 1 ];
			threshold = (int) ( spine.length * loadFactor );
			Arrays.fill( spine, -1 );
			for ( int i = 0; i < size; i++ )
			{
				insert( objs[ i ], i );
			}
		}

		private int hash( Object obj )
		{
			return System.identityHashCode( obj ) & 0x7FFFFFFF;
		}

		private void insert( Object obj, int handle )
		{
			int index = hash( obj ) % spine.length;
			objs[ handle ] = obj;
			next[ handle ] = spine[ index ];
			spine[ index ] = handle;
		}
	}

	/**
	 * Does absolutely nothing.
	 */
	private static class NoopOutputStream extends OutputStream
	{
		public void close( )
		{
		}

		public void flush( )
		{
		}

		public void write( byte[ ] b )
		{
		}

		public void write( byte[ ] b, int i, int l )
		{
		}

		public void write( int b )
		{
		}
	}

	private static abstract class ObjectOutputAdaptor implements ObjectOutput
	{
		public void close( ) throws IOException
		{
		}

		public void flush( ) throws IOException
		{
		}

		public void write( byte[ ] b ) throws IOException
		{
		}

		public void write( byte[ ] b, int off, int len ) throws IOException
		{
		}

		public void write( int b ) throws IOException
		{
		}

		public void writeBoolean( boolean v ) throws IOException
		{
		}

		public void writeByte( int v ) throws IOException
		{
		}

		public void writeBytes( String s ) throws IOException
		{
		}

		public void writeChar( int v ) throws IOException
		{
		}

		public void writeChars( String s ) throws IOException
		{
		}

		public void writeDouble( double v ) throws IOException
		{
		}

		public void writeFloat( float v ) throws IOException
		{
		}

		public void writeInt( int v ) throws IOException
		{
		}

		public void writeLong( long v ) throws IOException
		{
		}

		public void writeShort( int v ) throws IOException
		{
		}

		public void writeUTF( String str ) throws IOException
		{
		}
	}

	/** Holds information about the field and the resulting object being traced. */
	private static final class TraceSlot
	{
		private final String fieldDescription;

		private final Object object;

		TraceSlot( Object object, StringBuffer fieldDescription )
		{
			this.object = object;
			this.fieldDescription = fieldDescription == null ? null : fieldDescription
					.toString( );
		}

		public String toString( )
		{
			return object.getClass( ) + " - " + fieldDescription;
		}
	}

	/** set for checking circular references. */
	private final HandleTable checked = new HandleTable( 10, (float) 3.00 );

	/** current full field description. */
	private StringBuffer fieldDescription;

	/** root object being analyzed. */
	private Object root;

	/** object stack that with the trace path. */
	private final LinkedList traceStack = new LinkedList( );

	/** cache for classes - writeObject methods. */
	private Map writeObjectMethodCache = new HashMap( );

	/**
	 * Whether we can execute the tests. If false, check will just return.
	 */
	private static boolean available = true;

	private static final NoopOutputStream DUMMY_OUTPUT_STREAM = new NoopOutputStream( );

	private static final Method fieldMethod;

	private static final Method getClassDataLayoutMethod;

	private static final Method getNumObjFields;

	private static final Method getObjFieldValues;

	// this hack - accessing the serialization API through introspection - is
	// the only way to use Java serialization for our purposes without writing
	// the whole thing from scratch (and even then, it would be limited). This
	// way of working is of course fragile for internal API changes, but as we
	// do an extra check on availability and we report when we can't use this
	// introspection fu, we'll find out soon enough and clients on this class
	// can fall back on Java's default exception for serialization errors (which
	// sucks and is the main reason for this attempt).
	private static final Method lookup;

	static
	{
		try
		{
			lookup = ObjectStreamClass.class.getDeclaredMethod( "lookup", new Class[ ]
			{ Class.class, Boolean.TYPE } );
			lookup.setAccessible( true );

			getClassDataLayoutMethod = ObjectStreamClass.class
					.getDeclaredMethod( "getClassDataLayout", null );
			getClassDataLayoutMethod.setAccessible( true );

			getNumObjFields = ObjectStreamClass.class
					.getDeclaredMethod( "getNumObjFields", null );
			getNumObjFields.setAccessible( true );

			getObjFieldValues = ObjectStreamClass.class
					.getDeclaredMethod( "getObjFieldValues", new Class[ ]
			{ Object.class, Object[ ].class } );
			getObjFieldValues.setAccessible( true );

			fieldMethod = ObjectStreamField.class.getDeclaredMethod( "getField", null );
			fieldMethod.setAccessible( true );
		}
		catch ( SecurityException e )
		{
			available = false;
			throw new RuntimeException( e );
		}
		catch ( NoSuchMethodException e )
		{
			available = false;
			throw new RuntimeException( e );
		}
	}

	public SerializableChecker( ) throws IOException
	{
	}

	/**
	 * Gets whether we can execute the tests. If false, calling
	 * {@link #check(Object)} will just return and you are advised to rely on the
	 * {@link NotSerializableException}. Clients are advised to call this method
	 * prior to calling the check method.
	 *
	 * @return whether security settings and underlying API etc allow for accessing
	 *         the serialization API using introspection
	 */
	public static boolean isAvailable( )
	{
		return available;
	}

	public void close( ) throws IOException
	{
	}

	public void flush( ) throws IOException
	{
	}

	/**
	 * @see java.io.ObjectOutputStream#reset()
	 */
	public void reset( ) throws IOException
	{
		root = null;
		checked.clear( );
		fieldDescription = null;
		traceStack.clear( );
		writeObjectMethodCache.clear( );
	}

	private void check( final Object obj ) throws IOException
	{
		if ( obj == null )
		{
			return;
		}

		Class cls = obj.getClass( );
		traceStack.add( new TraceSlot( obj, fieldDescription ) );

		if ( !( obj instanceof Serializable ) )
		{
			throw new NotSerializableException( toPrettyPrintedStack( cls ) );
		}

		final ObjectStreamClass desc;
		try
		{
			desc = (ObjectStreamClass) lookup.invoke( null, new Object[ ]
			{ cls, Boolean.TRUE } );
		}
		catch ( IllegalAccessException e )
		{
			throw new RuntimeException( e );
		}
		catch ( InvocationTargetException e )
		{
			throw new RuntimeException( e );
		}

		if ( cls.isPrimitive( ) )
		{
			// skip
		}
		else if ( cls.isArray( ) )
		{
			checked.assign( obj );
			Class ccl = cls.getComponentType( );
			if ( !( ccl.isPrimitive( ) ) )
			{
				Object[ ] objs = (Object[ ]) obj;
				for ( int i = 0; i < objs.length; i++ )
				{
					fieldDescription.append( '[' ).append( i ).append( ']' );
					check( objs[ i ] );
				}
			}
		}
		else if ( obj instanceof Externalizable && !Proxy.isProxyClass( cls ) )
		{
			Externalizable extObj = (Externalizable) obj;
			extObj.writeExternal( new ObjectOutputAdaptor( )
			{
				private int count = 0;

				public void writeObject( Object streamObj ) throws IOException
				{
					// Check for circular reference.
					if ( checked.contains( streamObj ) )
					{
						return;
					}

					checked.assign( streamObj );
					fieldDescription.append( "[write:" ).append( count++ ).append( ']' );

					check( streamObj );
				}
			} );
		}
		else
		{
			Method writeObjectMethod = null;
			Object o = writeObjectMethodCache.get( cls );
			if ( o != null )
			{
				if ( o instanceof Method )
				{
					writeObjectMethod = (Method) o;
				}
			}
			else
			{
				try
				{
					writeObjectMethod = cls.getDeclaredMethod( "writeObject", new Class[ ]
					{ java.io.ObjectOutputStream.class } );
				}
				catch ( SecurityException e )
				{
					// we can't access/ set accessible to true
					writeObjectMethodCache.put( cls, Boolean.FALSE );
				}
				catch ( NoSuchMethodException e )
				{
					// cls doesn't have that method
					writeObjectMethodCache.put( cls, Boolean.FALSE );
				}
			}

			if ( writeObjectMethod != null )
			{
				class InterceptingObjectOutputStream extends ObjectOutputStream
				{
					private int counter;

					InterceptingObjectOutputStream( ) throws IOException
					{
						super( DUMMY_OUTPUT_STREAM );
						enableReplaceObject( true );
					}

					protected Object replaceObject( Object streamObj ) throws IOException
					{
						if ( streamObj == obj )
						{
							return streamObj;
						}

						counter++;
						// Check for circular reference.
						if ( checked.contains( streamObj ) )
						{
							return null;
						}

						checked.assign( obj );
						fieldDescription.append( "[write:" ).append( counter )
								.append( ']' );
						check( streamObj );
						return streamObj;
					}
				}
				InterceptingObjectOutputStream ioos = new InterceptingObjectOutputStream( );
				ioos.writeObject( obj );
			}
			else
			{
				Object[ ] slots;
				try
				{
					slots = (Object[ ]) getClassDataLayoutMethod.invoke( desc, null );
				}
				catch ( Exception e )
				{
					throw new RuntimeException( e );
				}
				for ( int i = 0; i < slots.length; i++ )
				{
					ObjectStreamClass slotDesc;
					try
					{
						Field descField = slots[ i ].getClass( )
								.getDeclaredField( "desc" );
						descField.setAccessible( true );
						slotDesc = (ObjectStreamClass) descField.get( slots[ i ] );
					}
					catch ( Exception e )
					{
						throw new RuntimeException( e );
					}
					checked.assign( obj );
					checkFields( obj, slotDesc );
				}
			}
		}

		traceStack.removeLast( );
	}

	private void checkFields( Object obj, ObjectStreamClass desc ) throws IOException
	{
		int numFields;
		try
		{
			numFields = ( (Integer) getNumObjFields.invoke( desc, null ) ).intValue( );
		}
		catch ( IllegalAccessException e )
		{
			throw new RuntimeException( e );
		}
		catch ( InvocationTargetException e )
		{
			throw new RuntimeException( e );
		}

		if ( numFields > 0 )
		{
			ObjectStreamField[ ] fields = desc.getFields( );
			Object[ ] objVals = new Object[ numFields ];
			int numPrimFields = fields.length - objVals.length;
			try
			{
				getObjFieldValues.invoke( desc, new Object[ ]
				{ obj, objVals } );
			}
			catch ( IllegalAccessException e )
			{
				throw new RuntimeException( e );
			}
			catch ( InvocationTargetException e )
			{
				throw new RuntimeException( e );
			}
			for ( int i = 0; i < objVals.length; i++ )
			{
				Object val = objVals[ i ];
				if ( val instanceof String || val instanceof Boolean
						|| val instanceof Class )
				{
					// filter out common cases
					continue;
				}

				// Check for circular reference.
				if ( checked.contains( val ) )
				{
					continue;
				}

				ObjectStreamField fieldDesc = fields[ numPrimFields + i ];
				Field field;
				try
				{
					field = (Field) fieldMethod.invoke( fieldDesc, null );
				}
				catch ( IllegalAccessException e )
				{
					throw new RuntimeException( e );
				}
				catch ( InvocationTargetException e )
				{
					throw new RuntimeException( e );
				}

				fieldDescription = new StringBuffer( field.toString( ) );
				check( val );
			}
		}
	}

	/**
	 * Dump with identation.
	 *
	 * @param type
	 *            the type that couldn't be serialized
	 * @return A very pretty dump
	 */
	private final String toPrettyPrintedStack( Class type )
	{
		StringBuffer result = new StringBuffer( );
		StringBuffer spaces = new StringBuffer( );
		result.append( "Unable to serialize class: " );
		result.append( type.getName( ) );
		result.append( "\nField hierarchy is:" );
		for ( Iterator i = traceStack.listIterator( ); i.hasNext( ); )
		{
			spaces.append( "  " );
			TraceSlot slot = (TraceSlot) i.next( );
			result.append( '\n' ).append( spaces ).append( slot.fieldDescription );
			result.append( " [class=" ).append( slot.object.getClass( ).getName( ) );
			result.append( ']' );
		}
		result.append( " <----- field that is not serializable" );
		return result.toString( );
	}

	/**
	 * @see java.io.ObjectOutputStream#writeObjectOverride(java.lang.Object)
	 */
	protected final void writeObjectOverride( Object obj ) throws IOException
	{
		if ( !available )
		{
			return;
		}
		root = obj;

		check( root );
	}
}