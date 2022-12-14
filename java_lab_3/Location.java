package java_lab_3;

/**
 * This class represents a specific location in a 2D map.  Coordinates are
 * integer values.
 **/
public class Location
{
    /** X coordinate of this location. **/
    public int xCoord;

    /** Y coordinate of this location. **/
    public int yCoord;


    /** Creates a new location with the specified integer coordinates. **/
    public Location(int x, int y)
    {
        xCoord = x;
        yCoord = y;
    }

    /** Creates a new location with coordinates (0, 0). **/
    public Location()
    {
        this(0, 0);
    }
    
    
    @Override
    public boolean equals(Object o) {
    	if (this == o) return true;
    	if (this.getClass() != o.getClass()) return false;
    	Location l = (Location) o;
    	return this.xCoord == l.xCoord && this.yCoord == l.yCoord;
    }
    
    @Override
    public int hashCode() {
    	return xCoord*1000000+yCoord;
    }
    
}