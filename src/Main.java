import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

@SuppressWarnings("serial")
public class Main extends Applet implements Runnable, KeyListener,
		MouseMotionListener {

	public static int FPS = 50;
	public static int Width = 1000;
	public static int Height = 800;
	public static boolean Running = true;
	public static Color BG = Color.black;
	public static double FrictionValue = .2; // higher means more friction 0 <
												// fv < 1
	public static double globalMaterial = .7; // higher means bouncier 0 < gM <
												// 1
	public static double staticFrictionThreshold = 1; // value of speed at which
														// static friction takes
														// over and stops
														// skidding
	public int GravityX = 0;
	public int GravityY = 0;
	public GravityWell g = null;
	ArrayList<Sphere> particles;

	public void init() {
		setBackground(BG);
		setSize(Width, Height);
		particles = new ArrayList<Sphere>();
		addKeyListener(this);
	}

	public void run() {
		while (Running) {

			for (int index = 0; index < particles.size(); index++) {
				particles.get(index).move(g);

			}

			repaint();
			try {
				Thread.sleep(1000 / FPS);
			} catch (Exception e) {
			}
		}
	}

	public void start() {
		new Thread(this).start();
	}

	public void stop() {

	}

	public void destroy() {

	}

	public void paint(Graphics g) {
		for (Sphere s : particles)
			s.draw(g);
		if (this.g != null)
			this.g.draw(g);
	}

	public Color randomColor() {
		return new Color(
				(int) (127 * (Math.sin(System.currentTimeMillis()) + 1)),
				(int) (127 * (Math.cos(System.currentTimeMillis()) + 1)),
				(int) (127 * (Math.sin(System.currentTimeMillis()) + 1)));
	}

	public int randomNumberZeroExclusive(int n) {
		int i = (int) (n * 2 * Math.random() - n);
		if (i == 0)
			return i + 1;
		else
			return i;

	}

	@Override
	public void keyPressed(KeyEvent k) {
		// TODO Auto-generated method stub
		if (k.getKeyCode() == KeyEvent.VK_SPACE)
			particles.add(new Sphere((int) (Math.random() * Width), (int) (Math
					.random() * Height), randomNumberZeroExclusive(10),
					randomNumberZeroExclusive(10), GravityX, GravityY,
					globalMaterial, randomColor(), (int) (Math.random() * 20)));
		if (k.getKeyCode() == KeyEvent.VK_Q)
			particles = new ArrayList<Sphere>();
		if (k.getKeyCode() == KeyEvent.VK_LEFT) {
			GravityX = -1;
			GravityY = 0;
			for (Sphere s : particles) {
				s.setXa(GravityX);
				s.setYa(GravityY);
			}

		}
		if (k.getKeyCode() == KeyEvent.VK_RIGHT) {
			GravityX = 1;
			GravityY = 0;
			for (Sphere s : particles) {
				s.setXa(GravityX);
				s.setYa(GravityY);
			}

		}
		if (k.getKeyCode() == KeyEvent.VK_UP) {
			GravityY = -1;
			GravityX = 0;
			for (Sphere s : particles) {
				s.setXa(GravityX);
				s.setYa(GravityY);
			}

		}
		if (k.getKeyCode() == KeyEvent.VK_DOWN) {
			GravityY = 1;
			GravityX = 0;
			for (Sphere s : particles) {
				s.setXa(GravityX);
				s.setYa(GravityY);
			}
			
		}
		if (k.getKeyCode() == KeyEvent.VK_0) {
			GravityX = 0;
			GravityY = 0;
			for (Sphere s : particles) {
				s.setXa(GravityX);
				s.setYa(GravityY);
			}
		}
		if (k.getKeyCode() == KeyEvent.VK_G) {
			if (g == null)
				g = new GravityWell(Width / 2, Height / 2, 5);
			else
				g = null;
			GravityX = 0;
			GravityY = 0;
			for (Sphere s : particles) {
				s.setXa(GravityX);
				s.setYa(GravityY);
			}
			
		}
	}

	@Override
	public void keyReleased(KeyEvent k) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent k) {
		// TODO Auto-generated method stub

	}

	public void mouseDragged(MouseEvent arg0) {

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
