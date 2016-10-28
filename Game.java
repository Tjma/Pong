import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel {
	private static final int Y = 330;
	private static final int Z = 30;
	Ball ball = new Ball(this);
	Racquet racquet = new Racquet(this,Y);
	Racquet racquet1 = new Racquet(this,Z);
	int speed = 1;
	int score1 = 0;
	int score2 = 0;

	
	

	public Game() {
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				//racquet.keyReleased(e);
				//racquet1.keyReleased(e);
				racquet.xa = 0;
				racquet1.xa = 0;
			}

			@Override
			public void keyPressed(KeyEvent e) {
				//racquet.keyPressed(e);
				//racquet1.keyPressed(e);
				if (e.getKeyCode() == KeyEvent.VK_LEFT)
					racquet.xa = -2;
				if (e.getKeyCode() == KeyEvent.VK_RIGHT)
					racquet.xa = 2;
				if(e.getKeyCode() == KeyEvent.VK_A)
					racquet1.xa = -2;
				if(e.getKeyCode() == KeyEvent.VK_D)
					racquet1.xa = 2;
			}
		});
		setFocusable(true);
		Sound.BACK.loop();
	}

	private void move() { // movement
		ball.move();
		racquet.move();
		racquet1.move();
	}

	@Override
	public void paint(Graphics g) { // paints the objects
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.RED);
		ball.paint(g2d);
		g2d.setColor(Color.BLACK);
		racquet.paint(g2d);
		racquet1.paint(g2d);

		g2d.setColor(Color.BLUE);
		g2d.setFont(new Font("Verdana", Font.BOLD, 20));
		g2d.drawString(String.valueOf(score1), 10, 30);
		g2d.drawString(String.valueOf(score2), 10, 330);
		g2d.drawOval(96, 138, 100, 100);
		g2d.drawLine(0, 190, 300, 190);
		
		
	}

	public void gameOver() { // ends the game
		Sound.BACK.stop();
		Sound.GAMEOVER.play();
		String winner = "";
		if(score1 == 3){
			winner = "Player1";
		}
		else if(score2 == 3){
			winner = "Player2";
		}
			JOptionPane.showMessageDialog(this, winner+" wins!","Game Over", JOptionPane.YES_NO_OPTION);
					
		if(JOptionPane.showConfirmDialog(this,"Would you like to play again?", "", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
			score1 = 0; score2 = 0;
		}else{
			System.exit(ABORT);
		}

		
	}
	
	public void winCheck(){ // chech if there's a winner
		
		if(score1 == 3 || score2 == 3) {
			gameOver();
			
		}
	}
	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Pong");
		Game game = new Game();
		frame.add(game);
		frame.setSize(300, 400);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		while (true) {
			game.move();
			game.repaint();
			Thread.sleep(10);
		}
	}
}