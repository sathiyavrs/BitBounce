

import java.awt.Color;
import java.awt.Graphics;

public class Bit {

	int length;
	int x;
	int y;
	double dx;
	double dy = 0;
	StartingPoint parent;

	double gravity = 15;
	double dt = 0.17;

	public Bit(StartingPoint sp) {
		dx = 3;
		x = 20;
		y = 620;
		length = 30;
		parent = sp;
	}

	public void paint(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(x - length / 2, y - length / 2, length, length);
		g.setColor(Color.BLACK);
		g.drawRect(x - length / 2, y - length / 2, length, length);

	}

	public void update(Graphics g) {

		if (x - length / 2 < 0) {
			dx = -1 * dx;
			parent.triggerStuffRight = true;
		}

		if (x + length / 2 > parent.getWidth()) {
			dx = -1 * dx;
			parent.triggerStuffLeft = true;
		}

		dy += dt * gravity;

		x += dx;

		y += dy * dt + 0.5 * gravity * dt * dt;

		// if (y + length / 2 > parent.getHeight()) {
		// dy = -dy;
		// }

		if (y > parent.getHeight() + 10) {
			parent.obs.dx = 0;
			parent.obs.dy = 0;
			dy = 0;
			dt = 0;
			dx = 0;
			parent.gameOver = true;
		}

		if (y - length / 2 <= 0) {
			// parent.obs.dx = 0;
			// parent.obs.dy = 0;
			// dy = 0;
			// dt = 0;
			// dx = 0;
			// parent.gameOver = true;
			dy = -dy / 2;
			y = length / 2 + 1;
		}

	}

}
