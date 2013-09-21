public abstract class Particle {

	private double x; // position (x, y)
	private double y;
	private double xv; // velocity <xv, yv>
	private double yv;
	private double xa; // acceleration <xa, ya>
	private double ya;

	public Particle(int x, int y, double xv, double yv, double xa, double ya) {
		this.x = x;
		this.y = y;
		this.xv = xv;
		this.yv = yv;
		this.xa = xa;
		this.ya = ya;
	}

	public void move() {
		this.x += this.xv;
		this.y += this.yv;
		this.xv += xa;
		this.yv += ya;
		setBoundary();
		// System.out.println("(" + x + ", " + y + ") <" + xv + ", " + yv +
		// ">");
	}

	public void elasticCollide(Particle that) { // two particle collision,
												// momentum conserved
		double theta = Math.atan2(this.y - that.y, this.x - that.x); // theta_inc
																		// =
																		// atan2(m_tan)
		double[] vthis; // new vector for this in (r, theta) form, rotated so x
						// component is parallel to tan
		vthis = new double[2];
		vthis[0] = Math.sqrt(this.xv * this.xv + this.yv * this.yv); // /r
		vthis[1] = Math.atan2(this.yv, this.xv) - theta; // /theta
		double[] vthat;
		vthat = new double[2];
		vthat[0] = Math.sqrt(that.xv * that.yv + that.yv * that.yv);
		vthat[1] = Math.atan2(that.yv, that.xv) - theta;
		// convert back to rectangular in new frame
		vthis[0] = vthis[0] * Math.cos(vthis[1]); // rcostheta
		vthis[1] = vthis[0] * Math.sin(vthis[1]); // rsintheta
		vthat[0] = vthat[0] * Math.cos(vthat[1]);
		vthat[1] = vthat[0] * Math.sin(vthat[1]);
		// momentum is conserved in y direction
		double buf = vthis[1];
		vthis[1] = vthat[1];
		vthat[1] = buf;
		// convert back to r theta
		vthis[0] = Math.sqrt(this.xv * this.xv + this.yv * this.yv); // /r
		vthis[1] = Math.atan2(this.yv, this.xv) + theta; // /theta
		//convert back to rectangular in old frame
		vthis[0] = vthis[0] * Math.cos(vthis[1]); // rcostheta
		vthis[1] = vthis[0] * Math.sin(vthis[1]); // rsintheta
		vthat[0] = vthat[0] * Math.cos(vthat[1]);
		vthat[1] = vthat[0] * Math.sin(vthat[1]);
		// finally, return completed values
		this.xv = vthis[0];
		this.yv = vthis[1];
		that.xv = vthat[0];
		that.yv = vthat[1];

	}

	public void setBoundary() {
		if (this.x > Main.Width || this.x < 0)
			xv = -xv;
		if (this.y > Main.Height || this.y < 0)
			yv = -yv;
	}

	public double distance(Particle that) {
		return Math.sqrt((this.x - that.x) * (this.x - that.x)
				+ (this.y - that.y) * (this.y - that.y));
	}

	public double getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double getXv() {
		return xv;
	}

	public void setXv(double xv) {
		this.xv = xv;
	}

	public double getYv() {
		return yv;
	}

	public void setYv(double yv) {
		this.yv = yv;
	}

	public double getXa() {
		return xa;
	}

	public void setXa(double xa) {
		this.xa = xa;
	}

	public double getYa() {
		return ya;
	}

	public void setYa(double ya) {
		this.ya = ya;
	}

}
