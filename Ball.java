import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball {
	private static final int DIAMETER = 30;
	
	int x = 150;
	int y = 150;
	int xa = 1;
	int ya = 1;
	private Game game;

	public Ball(Game game) {
		this.game = game;
	}

	void move() { 
		boolean changeDirection = true;
		if (x + xa < 0){
			xa = game.speed;
		}else if (x + xa > game.getWidth() - DIAMETER){
			xa = -game.speed;
		}else if (y + ya < 0){
			//ya = game.speed;
			++game.score2;
			x = 150; y = 150;
			game.speed = 1;
			xa = 1; ya =1;
			//game.gameOver();
			game.winCheck();
		}else if (y + ya > game.getHeight() - DIAMETER){
			++game.score1;
			x = 100; y = 150 ;
			game.speed = 1;
			xa = 1; ya =1;
			//game.gameOver();
			game.winCheck();
		}else if (collision()){
			ya = -game.speed;
			y = game.racquet.getTopY() - DIAMETER;
			game.speed++;
		}else if (collision1()){
			ya = game.speed;
			y = game.racquet1.getTopY();
			game.speed++;
		}
		else 
			changeDirection = false;
		
		if (changeDirection) 
			Sound.BALL.play();
		x = x + xa;
		y = y + ya;
	}
		
	private boolean collision() { // detects if the ball hits the paddle
		return game.racquet.getBounds().intersects(getBounds());
	}
	private boolean collision1(){
		return game.racquet1.getBounds().intersects(getBounds());
	}

	public void paint(Graphics2D g) { // drawsthe ball
		g.fillOval(x, y, DIAMETER, DIAMETER);
	}

	public Rectangle getBounds() { // tells whether there's a collision
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}
	
}