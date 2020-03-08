import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
	
	final int MENU = 0;
    final int GAME = 1;
    final int END = 2;
    
    Font titleFont;
    Font normalFont;
    
    int currentState = MENU;
    
    Timer frameDraw;
    
    Timer alienSpawn;
    
    public static BufferedImage image;
    public static boolean needImage = true;
    public static boolean gotImage = false;	
    
    Rocketship r = new Rocketship(250, 700, 50, 50);
    
    ObjectManager o = new ObjectManager(r);
	
	GamePanel(){
	    titleFont = new Font("Arial", Font.PLAIN, 48);
	    normalFont = new Font("Arial", Font.PLAIN, 18);
	    
	    frameDraw = new Timer(1000/60,this);
	    frameDraw.start();
	    
	    if (needImage) {
	        loadImage ("space.png");
	    }
	}
	
	@Override
	public void paintComponent(Graphics g){
		if(currentState == MENU){
		    drawMenuState(g);
		}else if(currentState == GAME){
		    drawGameState(g);
		}else if(currentState == END){
		    drawEndState(g);
		}
	}
    
    void updateMenuState() { 
    	
    }
    
    void updateGameState() { 
    	o.update();
    	if(r.isActive==false) {
    		currentState=END;
    	}
    }
    
    void updateEndState()  { 
    	
    }
    
    void drawMenuState(Graphics g) { 
    g.setColor(Color.BLUE);
    g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
    
    g.setFont(titleFont);
    g.setColor(Color.YELLOW);
    g.drawString("LEAGUE INVADERS", 25, 100);
    
    g.setFont(normalFont);
    g.drawString("Press ENTER to start", 150, 350);
    
    g.drawString("Press SPACE for instructions", 125, 500);
    
    }
    
    void drawGameState(Graphics g) { 
    	if (gotImage) {
    		g.drawImage(image, 0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT, null);
    	} else {
    		g.setColor(Color.BLUE);
    		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
    	}
    	o.draw(g);
    }
    
    void drawEndState(Graphics g)  { 
    g.setColor(Color.RED);
    g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
    g.setFont(titleFont);
    g.setColor(Color.YELLOW);
    g.drawString("Your score was "+o.getScore(), 25, 100);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(currentState == MENU){
		    updateMenuState();
		}else if(currentState == GAME){
		    updateGameState();
		}else if(currentState == END){
		    updateEndState();
		}
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
			if (currentState == MENU) {
				startGame();
			}
			if (currentState == GAME) {
				//endGame();
			}
		    if (currentState == END) {
		        currentState = MENU;
		        r = new Rocketship(250, 700, 50, 50);
		        o = new ObjectManager(r);
		    } else {
		        currentState++;
		    }
		}   
		if(currentState==GAME) {
			if (e.getKeyCode()==KeyEvent.VK_UP) {
				if(r.y>0) {
					r.up();
				}
			}
			
			if (e.getKeyCode()==KeyEvent.VK_DOWN) {
				if(r.y<750) {
					r.down();
				}
			}
			
			if (e.getKeyCode()==KeyEvent.VK_LEFT) {
				if(r.x>0) {
					r.left();
				}
			}
		
			if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
				if(r.x<450) {
					r.right();
				}
			}
			
			if(e.getKeyCode()==KeyEvent.VK_SPACE) {
			
				o.addProjectile(r.getProjectile());
			}
			
		
		}
		
		if(currentState==END) {
			endGame();
		
		}
		
		if(e.getKeyCode()==KeyEvent.VK_SPACE) {
			if(currentState==MENU) {
				JOptionPane.showMessageDialog(null, "Use arrow keys to move. Press SPACE to fire. Try not to die");
			}
		}
	}
		
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	void startGame() {
		alienSpawn = new Timer(1000, o);
		alienSpawn.start();
	}
	
	void endGame() {
		alienSpawn.stop();
	}
	
	
 
}
