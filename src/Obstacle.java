

import java.awt.Color;
import java.awt.Graphics;

public class Obstacle {

	int x;
	int y;
	double dx;
	double dy;
	int length;

	StartingPoint parent;
	Bit bit;

	public Obstacle(StartingPoint sp, Bit b) {
		parent = sp;
		bit = b;
		x = -21;
		length = 40;
		y = 20;
		dx = 0;
		dy = 0;
	}

	public void paint(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x - length / 2, y - length / 2, length, length);
		g.setColor(Color.BLACK);
		g.drawRect(x - length / 2, y - length / 2, length, length);

	}

	public void update(Graphics g) {
		checkForCollision();

		x += dx;
		y += dy;

		// if (x - length / 2 > 0) {
		// dx = -1 * dx;
		// }
		//
		// if (x + length / 2 < 0) {
		// dx = -1 * dx;
		// }
		if (dx != 0) {
			if (x <= -22) {
				dx = 0;
				x = -21;
				parent.finishMove = true;

			}
			if (x >= 20 && x <= 30) {
				dx = 0;
				x = 20;
				parent.finishMove = true;
				// return false;
			}
			if (x >= 420) {
				dx = 0;
				x = 421;
				parent.finishMove = true;
			}
			if (x <= 380 && x >= 370) {
				dx = 0;
				x = 380;
				parent.finishMove = true;
			}
		}
	}

	public boolean move() {
		// TODO Auto-generated method stub
		if (dx == 0) {
			if (x <= -20) {
				dx = 3;
				x = -19;
			}
			if (x >= 20 && x <= 30) {
				dx = -3;
				x = +19;
			}
			if (x >= 420) {
				dx = -3;
				x = 419;
			}
			if (x <= 380 && x >= 370) {
				dx = 3;
				x = 381;
			}

		}
		return true;
	}

	private void checkForCollision() {
		// TODO Auto-generated method stub
		int bitX = bit.x;
		int bitY = bit.y;
		int bitL = bit.length;

		boolean forX = ((bitX - bitL / 2) < (x + length / 2))
				&& ((bitX + bitL / 2) > (x - length / 2));

		boolean forY = ((bitY + bitL / 2) > (y - length / 2))
				&& ((bitY - bitL / 2) < (y + length / 2));

		if (forX && forY) {
			// System.out.println("IN");
			dx = 0;
			dy = 0;
			bit.dy = 0;
			bit.dt = 0;
			bit.dx = 0;
			parent.gameOver = true;
		}

	}
}
