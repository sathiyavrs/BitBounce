

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class StartingPoint extends Applet implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;

	private Image img;
	private Graphics doubleG;
	int obstacleNumber = 1;
	boolean gameOver = false;
	
	private URL url;
	private Image img2;


	Bit bit = new Bit(this);
	Obstacle obs = new Obstacle(this, bit);
	ArrayList<Obstacle> obsList = new ArrayList<Obstacle>();
	ArrayList<Obstacle> obsListRight = new ArrayList<Obstacle>();

	boolean triggerStuffLeft = false;
	boolean triggerStuffRight = false;
	Random rand = new Random();
	boolean checkMovement = true;
	boolean finishMove = true;
	int score = 0;
	Font font;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		setSize(400, 600);
		addKeyListener(this);
		// bit.dx = 4;
		int i;
		for (i = 0; i < 15; i++) {
			obs = new Obstacle(this, bit);
			obs.y = 20 + 40 * i;
			obsList.add(obs);
		}
		for (i = 0; i < 15; i++) {
			obs = new Obstacle(this, bit);
			obs.y = 20 + 40 * i;
			obs.x = 400 + 20 + 1;
			obsListRight.add(obs);
		}
		
		try {
			url = this.getDocumentBase();
		} catch (Exception e) {
			// TODO: handle exception
		}
		img2 = this.getImage(url, "data/Background.jpg");
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		Thread thread = new Thread(this);
		thread.start();

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(img2, 0, 0, this);
		
		bit.paint(g);
		obs.paint(g);
		int i;
		for (i = 0; i < 15; i++) {
			obsList.get(i).paint(g);

		}
		for (i = 0; i < 15; i++) {
			obsListRight.get(i).paint(g);

		}

		font = new Font("Times New Roman", Font.BOLD, 32);
		g.setFont(font);
		g.setColor(Color.CYAN);
		g.drawString("" + score, 60, 60);

		if (gameOver) {
			g.drawString("Press Enter to play!", 60, 360);

		}
		
		
	}

	@Override
	public void update(Graphics g) {
		// TODO Auto-generated method stub
		bit.update(g);

		if (score == 20) {
			obstacleNumber = 9;

		}
		if (score == 14) {
			obstacleNumber = 7;
		} else if (score == 6) {
			obstacleNumber = 5;
		} else if (score == 2) {
			// bit.dx = 4;
			obstacleNumber = 3;
		}

		int i, j;
		if (triggerStuffLeft) {
			score++;
			for (i = 0; i < obstacleNumber; i++) {
				j = rand.nextInt(15);
				obsList.get(j).move();

			}
			for (i = 0; i < 15; i++) {
				if (obsListRight.get(i).x < 415)
					obsListRight.get(i).move();

			}
			triggerStuffLeft = false;
		}
		if (triggerStuffRight) {
			score++;
			for (i = 0; i < obstacleNumber; i++) {
				j = rand.nextInt(15);
				obsListRight.get(j).move();

			}
			for (i = 0; i < 15; i++) {
				if (obsList.get(i).x > -5)
					obsList.get(i).move();

			}
			triggerStuffRight = false;
		}
		for (i = 0; i < 15; i++) {
			obsList.get(i).update(g);
			obsListRight.get(i).update(g);

		}
		if (img == null) {
			img = createImage(this.getWidth(), this.getHeight());
			doubleG = img.getGraphics();
		}

		doubleG.setColor(Color.WHITE);
		doubleG.fillRect(0, 0, this.getWidth(), this.getHeight());

		doubleG.setColor(getForeground());
		paint(doubleG);

		g.drawImage(img, 0, 0, this);

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {

		case KeyEvent.VK_SPACE:
			bit.dy = -62;
			break;

		case KeyEvent.VK_ENTER:
			if (gameOver) {
				int i;

				for (i = 0; i < 15; i++) {
					obsList.get(i).x = -21;
					obsList.get(i).dx = 0;
					obsListRight.get(i).dx = 0;
					obsListRight.get(i).x = 421;
				}

				bit.dx = 3;
				bit.dy = 0;
				bit.dt = 0.17;
				bit.x = 20;
				bit.y = 20;
				score = 0;
				obstacleNumber = 1;
				gameOver = false;
			}
			break;

		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			repaint();
			try {
				Thread.sleep(17);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}