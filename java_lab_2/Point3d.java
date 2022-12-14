package java_lab_2;

public class Point3d extends Point2d{
	private double zCoord;

	public Point3d (double x, double y, double z) {
		super(x, y);
		zCoord = z;
	}
	
	public Point3d () {
		this(0, 0, 0);
	}
	
	public double getZ () {
		return zCoord;
	}
		
	public void setZ (double val) {
		zCoord = val;
	}
	
	public boolean isValEqual(Point3d o) {
		return (super.isValEqual2d((Point2d) o) &&
				this.getZ() == o.getZ());
 	}
	
	public double distanceTo(Point3d o) {
		double diffX = this.getX() - o.getX();
		double diffY = this.getY() - o.getY();
		double diffZ = this.getZ() - o.getZ();
		
		return Math.round(Math.pow(Math.pow(diffX, 2) +
						  		   Math.pow(diffY, 2) +
						  		   Math.pow(diffZ, 2),
				  		  0.5)*100)/100.0;		
	}	
}
	
	