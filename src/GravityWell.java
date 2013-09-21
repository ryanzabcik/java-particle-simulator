import java.awt.Graphics;
import java.awt.Color;


public class GravityWell {
	private int x;
	private int y;
	private int radius;
	private final double G = 0.05;
	
	public GravityWell(int x, int y, int radius) {
		super();
		this.x = x;
		this.y = y;
		this.radius = radius;
	}
	
	public void accel(Sphere s)
	{
		
		double magnitude = G*s.getMass()*(4 / 3 * Math.PI * Math.pow(radius, 3)) / ((s.getX() - this.x)*(s.getX() - this.x) + (s.getY() - this.y)*(s.getY() - this.y));
		double theta = Math.atan2(s.getX()-this.x, s.getX()-this.x);
		
		s.setXa(-magnitude * Math.cos(theta));
		s.setYa(-magnitude * Math.sin(theta));
		
	}
	
	public void draw(Graphics g){
		g.setColor(Color.WHITE);
		g.fillOval((int) (this.getX() - this.getRadius()),
			(int) (this.getY() - this.getRadius()), this.getRadius() * 2,
			this.getRadius() * 2);		
		g.setColor(Color.BLACK);
		g.fillOval((int) (this.getX() - this.getRadius() + 1),
				(int) (this.getY() - this.getRadius()) + 1, this.getRadius() * 2 - 2,
				this.getRadius() * 2 - 2);
		
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
	
}
