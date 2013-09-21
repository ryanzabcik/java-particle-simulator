
public class Vector {
	public double x;
	public double y;
	
	
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}
	public double getMagnitude(){
		return Math.sqrt(x*x + y*y);
	}
	
	public static Vector tilt (Vector v, double theta){
		theta -= Math.atan2(v.y, v.x);
		theta %= 2*Math.PI;
		return new Vector(v.x*Math.cos(theta), v.y*Math.sin(theta));
	}
	
	public void tilt(double theta){
		theta -= Math.atan2(this.y, this.x);
		this.x = this.x*Math.cos(theta);
		this.y = this.y*Math.sin(theta);
	}
	
	public void tiltBack(double theta){
		this.tilt(Math.PI*2 - theta);
	}
}
