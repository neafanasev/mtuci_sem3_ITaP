package java_lab_2;

public class lab2 {
	public static void main(String[] args) {
		Point3d p1 = new Point3d();	
		p1.setX(Double.parseDouble(args[0]));
		p1.setY(Double.parseDouble(args[1]));
		p1.setZ(Double.parseDouble(args[2]));
		Point3d p2 = new Point3d();
		p2.setX(Double.parseDouble(args[3]));
		p2.setY(Double.parseDouble(args[4]));
		p2.setZ(Double.parseDouble(args[5]));
		Point3d p3 = new Point3d();
		p3.setX(Double.parseDouble(args[6]));
		p3.setY(Double.parseDouble(args[7]));
		p3.setZ(Double.parseDouble(args[8]));
	
		System.out.println(computeArea(p1, p2, p3));
	}
	
	public static String computeArea(Point3d p1, Point3d p2, Point3d p3) {
		if (p1.isValEqual(p2) | p1.isValEqual(p3) | p2.isValEqual(p3)) {
			return "Невозможно найти площадь, так как какие-то точки равны";
		} else {
			double s1 = p1.distanceTo(p2);
			double s2 = p1.distanceTo(p3);
			double s3 = p3.distanceTo(p2);
			double p = (s1 + s2 + s3) / 2;
			double area = Math.sqrt(p*(p - s1)*(p - s2)*(p - s3));
			return Double.toString(area);
		}		
	}
}
