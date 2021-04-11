package co.castle.feature;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Vector;

import sz.util.Position;

public class VFeatures implements Serializable {
	private final Vector<Feature> temp = new Vector<>();
	private final Vector<Feature> tempVector = new Vector<>();

	Vector<Feature> features;

	Hashtable<Position, Feature> mLocs;

	public VFeatures(int size) {
		features = new Vector<>(size);
		mLocs = new Hashtable<>(size);
	}

	public void addFeature(Feature what) {
		features.add(what);
		// mLocs.put(what, what.getPosition());
		mLocs.put(what.getPosition(), what);
	}

	public Vector<Feature> getAllOf(String featureID) {
		tempVector.removeAllElements();
		for (int i = 0; i < features.size(); i++) {
			Feature f = features.elementAt(i);
			if (f.getID().equals(featureID)) {
				tempVector.add(f);
			}
		}
		return tempVector;
	}

	public Feature getFeatureAt( Position p )
	{
		// return (Feature) mLocs.get(p);
		for ( int i = 0; i < features.size( ); i++ ) {
			if (features.elementAt(i).getPosition().equals(p)) {
				return features.elementAt(i);
			}
		}
		// Debug.byebye("Feature not found! "+p);
		return null;
	}

	public Feature[ ] getFeaturesAt( Position p )
	{
		temp.clear( );
		for ( int i = 0; i < features.size( ); i++ ) {
			if (features.elementAt(i).getPosition().equals(p)) {
				temp.add(features.elementAt(i));
			}
		}
		if ( temp.size( ) == 0 )
		{
			return null;
		}
		else {
			return temp.toArray(new Feature[temp.size()]);
		}
	}

	public void removeFeature( Feature o )
	{
		features.remove( o );
		if ( mLocs.containsValue( o ) )
			mLocs.remove( o );
	}

}