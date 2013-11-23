import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SnakeBoard extends JPanel {

    private final int width;
    private final int height;
    private int[][] board;
    private SnakeGame snakegame;
    private Timer timer;

    public SnakeBoard(int width, int height){
	this.width = width;
	this.height = height;
	this.board = new int[height][width];
	this.snakegame = new SnakeGame(width, height);
	timer = new Timer(222, new ActionListener() {
		public void actionPerformed(ActionEvent ae)
		{
		    
		    if (! snakegame.isAlive()) {
			snakegame = new SnakeGame(30, 30);
		    }

		    if (snakegame.getDirection() == Direction.UP){
			snakegame.moveSnake(-1, 0);
		    }
		    else if (snakegame.getDirection() == Direction.DOWN){
			snakegame.moveSnake(1, 0);
		    }
		    else if (snakegame.getDirection() == Direction.LEFT){
			snakegame.moveSnake(0, -1);
		    }
		    else snakegame.moveSnake(0, 1);

		    repaint();
		}


	    });
	timer.start();
	this.addKeyListener( new KeyListener() {
		public void keyTyped(KeyEvent e) {

		}
		public void keyPressed(KeyEvent e) {
		    if (e.getKeyCode() == KeyEvent.VK_RIGHT && snakegame.getDirection() != Direction.LEFT)
			{
			    snakegame.setDirection(Direction.RIGHT);
			}
		    if (e.getKeyCode() == KeyEvent.VK_LEFT && snakegame.getDirection() != Direction.RIGHT)
			{
			    snakegame.setDirection(Direction.LEFT);
			}
		    if (e.getKeyCode() == KeyEvent.VK_UP && snakegame.getDirection() != Direction.DOWN)
			{
			    snakegame.setDirection(Direction.UP);
			}
		    if (e.getKeyCode() == KeyEvent.VK_DOWN && snakegame.getDirection() != Direction.UP)
			{
			    snakegame.setDirection(Direction.DOWN);
			}

		}
		public void keyReleased(KeyEvent e) {
		}
	    });
	this.setFocusable(true); 
	this.requestFocus();
    }

    public void paintComponent(Graphics g){
    	super.paintComponent(g);
    	Graphics2D g2 = (Graphics2D) g;
	g2.setPaint(Color.BLACK);
	for (int i = 0; i < height; i++){
	    for (int j = 0; j < width; j++)
		{
		    g2.drawRect(i * 20, j * 20, 20, 20);
		}
	}
	for (Snake s : snakegame.getSnakes())
	    {
		g2.fillRect(s.getX() * 20, s.getY() * 20, 20, 20);
	    }

	g2.setPaint(Color.GREEN);
	g2.fillRect(snakegame.getFoodX() * 20, snakegame.getFoodY() * 20, 20, 20);
    }


    public void finalize(){
	timer.stop();
    }


    public static void main(String[] args){
	final JFrame main = new JFrame("Snakes on a JVM");
	final SnakeBoard sb = new SnakeBoard(30, 30);
	sb.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	sb.setPreferredSize(new Dimension(601, 601));
	main.add(sb);
	main.pack();
	main.setResizable(false);
	main.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent we) {
		    sb.finalize();
		    main.dispose();
		}
	    });

	main.setVisible(true);

    }
}