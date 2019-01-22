package sz.fov;

public interface FOVMap
{
	public boolean blockLOS( int x, int y );

	public void setSeen( int x, int y );
}
