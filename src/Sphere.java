import java.awt.Color;
import java.awt.Graphics;

public class Sphere extends Particle {

	private int radius;
	private double material;
	private Color color;
	private double mass;

	public Sphere(int x, int y, double xv, double yv, double xa, double ya,
			double material, Color color, int radius) {
		super(x, y, xv, yv, xa, ya);
		this.setMaterial(material);
		this.setRadius(radius);
		this.setColor(color);
		this.setMass(4 * Math.PI * Math.pow(radius, 3) / 3 * material);
	}

	public void draw(Graphics g) {
		g.setColor(this.getColor());
		g.fillOval((int) (this.getX() - this.getRadius()),
				(int) (this.getY() - this.getRadius()), this.getRadius() * 2,
				this.getRadius() * 2);
	}

	public void move(GravityWell g) {
		super.setX((int) (super.getX() + super.getXv()));
		super.setY((int) (super.getY() + super.getYv()));
		super.setXv(super.getXv() + super.getXa());
		super.setYv(super.getYv() + super.getYa());
		if (g != null)
			g.accel(this);
		setBoundary();
		// System.out.println("(" + x + ", " + y + ") <" + xv + ", " + yv +
		// ">");
	}

	public void setBoundary() {
		if (super.getX() > Main.Width - radius) { // EAST WALL
			super.setXv(-material * super.getXv() - super.getXa());
			super.setX(Main.Width - radius + (super.getXa() > 0 ? 1 : 0));
			if (super.getYv() > Main.staticFrictionThreshold)
				super.setYv(super.getYv() - super.getXa() * Main.FrictionValue);
			else if (super.getYv() < -Main.staticFrictionThreshold)
				super.setYv(super.getYv() + super.getXa() * Main.FrictionValue);
			else
				super.setYv(super.getYa());
		}
		if (super.getX() < radius) { // WEST WALL 
			super.setXv(-material * super.getXv() + super.getXa());
			super.setX(radius /* + (super.getXa() < 0 ? -1 : 0)*/);
			if (super.getYv() > Main.staticFrictionThreshold)
				super.setYv(super.getYv() + super.getXa() * Main.FrictionValue);
			else if (super.getYv() < -Main.staticFrictionThreshold)
				super.setYv(super.getYv() - super.getXa() * Main.FrictionValue);
			else
				super.setYv(super.getYa());
		}
		if (super.getY() > Main.Height - radius) { // SOUTH WALL
			super.setYv(-material * super.getYv() + super.getYa());
			if (super.getYa() > 0)
				super.setY(Main.Height - radius + 1);
			else
				super.setY(Main.Height - radius);
			if (super.getXv() > Main.staticFrictionThreshold)
				super.setXv(super.getXv() - super.getYa() * Main.FrictionValue);
			else if (super.getXv() < -Main.staticFrictionThreshold)
				super.setXv(super.getXv() + super.getYa() * Main.FrictionValue);
			else
				super.setXv(super.getXa());
		}
		if (super.getY() < radius) { // NORTH WALL
			super.setYv(-material * super.getYv() + super.getYa());
			super.setY(radius + (super.getXa() < 0 ? -1 : 0));
			if (super.getXv() < -Main.staticFrictionThreshold)
				super.setXv(super.getXv() - super.getYa() * Main.FrictionValue);
			else if (super.getXv() > Main.staticFrictionThreshold)
				super.setXv(super.getXv() + super.getYa() * Main.FrictionValue);
			else
				super.setXv(super.getXa());
		}
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public double getMaterial() {
		return material;
	}

	public void setMaterial(double material) {
		this.material = material;
	}

	public double getMass() {
		return mass;
	}

	public void setMass(double mass) {
		this.mass = mass;
	}

}
