import java.awt.Graphics2D;
import java.awt.Rectangle;
//import java.awt.event.KeyEvent;

public class Racquet {
	
	private static final int WIDTH = 60;
	private static final int HEIGHT = 10;
	int x = 100;
	int xa = 0;
	int x1 = 100;
	int xa1 = 0;
	int y = 0;
	private Game game;

	public Racquet(Game game, int y) {
		this.game = game;
		this.y = y; 
	}

	public void move() { // movement L to R, R to L
		if (x + xa > 0 && x + xa < game.getWidth() - WIDTH)
			x = x + xa;
		if(x1 + xa1 > 0 && x1 +xa1<game.getWidth() - WIDTH)
			x1 = x1 + xa1;
	}

	public void paint(Graphics2D g) { // paints the racquet at given location
		g.fillRect(x, y, WIDTH, HEIGHT);
	}


	public Rectangle getBounds() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}
	

	public int getTopY() { // get the edge of the paddles
		if(y==30){
			return y+HEIGHT;
		}else
			return y-HEIGHT;
	}
	
	
}