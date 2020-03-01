import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LeagueInvaders {
	
	JFrame leagueInvadersJFrame;
	GamePanel GP = new GamePanel();
	
	public static final int WIDTH = 500;
	public static final int HEIGHT = 800;
	
	LeagueInvaders(){
		leagueInvadersJFrame = new JFrame();
	}
	
	public static void main(String[] args) {
		LeagueInvaders LI = new LeagueInvaders();
		
		LI.setup();
	}
	
	void setup() {
		leagueInvadersJFrame.add(GP);
		leagueInvadersJFrame.setSize(WIDTH, HEIGHT+20);
		leagueInvadersJFrame.setVisible(true);
		
		leagueInvadersJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		leagueInvadersJFrame.addKeyListener(GP);
		
	}
	
}
