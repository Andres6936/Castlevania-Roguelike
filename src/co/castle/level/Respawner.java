package co.castle.level;

import co.castle.actor.Actor;

public class Respawner extends Actor
{
	private int freq;
	private int prob;

	public Respawner( int freq, int prob )
	{
		this.freq = freq;
		this.prob = prob;
	}

	public String getDescription( )
	{
		return "Respawnie";
	}

	public int getFreq( )
	{
		return freq;
	}

	public int getProb( )
	{
		return prob;
	}
}