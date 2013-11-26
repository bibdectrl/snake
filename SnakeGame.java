import java.util.LinkedList;
import java.util.Random;

public class SnakeGame {

    private LinkedList<Snake> snakes;
    private int length;
    private Direction direction;
    private boolean alive;
    private int[] food;
    private Random rng;
    private int boardHeight;
    private int boardWidth;
    private int snakeHeadX;
    private int snakeHeadY;

    public SnakeGame(int boardHeight, int boardWidth)
    {
	this.length = 1;
	this.direction = Direction.RIGHT;
	this.alive = true;
	this.boardHeight = boardHeight;
	this.boardWidth = boardWidth;
	this.snakes = new LinkedList<Snake>();
	this.rng = new Random();
	snakes.add(new Snake(rng.nextInt(boardHeight-5) + 5, rng.nextInt(boardWidth-5)+5));
	this.food = new int[2];
	this.food[0] = rng.nextInt(boardHeight-1) + 1;
	this.food[1] = rng.nextInt(boardWidth-1) + 1;
    }

    public boolean isAlive()
    {
	return alive;
    }
    
    public LinkedList<Snake> getSnakes() 
    {
	return snakes;
    }
    

    public void feedSnake()
    {
	Snake s1 = snakes.peek();
	int newX = s1.getX();
	int newY = s1.getY();
	snakes.add(new Snake(newX, newY));
    }

    public Direction getDirection()
    {
	return direction;
    }

    public void setDirection(Direction d)
    {
	direction = d;
    }

    public int getFoodX()
    {
	return food[0];
    }
    public int getFoodY()
    {
	return food[1];
    }

    private void newFood(){
	this.food[0] = rng.nextInt(boardHeight);
	this.food[1] = rng.nextInt(boardWidth);
	for (Snake s: snakes)
	    {
		if (s.getX() == getFoodX() && s.getY() == getFoodY())
		    newFood();
	    }
    }

    public void moveSnake(int i, int j)
    {
	Snake s1 = snakes.get(0);
	int newX = s1.getX() + j;
	int newY = s1.getY() + i;

	if ( newX < 0 || newY < 0 || newX >= boardHeight || newY >= boardWidth)
	    alive = false;


	if (newX == getFoodX() && newY == getFoodY()) 
	    {
		feedSnake();
		newFood();
	    }
	
	for (Snake s : snakes)
	    {
		if (newX == s.getX() && newY == s.getY()) 
		    {
			alive = false;
		    }
	    }
	snakes.addFirst(new Snake(newX, newY));
	snakes.removeLast();
    }

}