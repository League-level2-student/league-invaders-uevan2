import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	Rocketship r;
	
	ArrayList<Projectile> projectile = new ArrayList<Projectile>();
	
	ArrayList<Alien> aliens = new ArrayList<Alien>();
	
	Random ran = new Random();
	
	ObjectManager(Rocketship r){
		r=new Rocketship(0, 0, 0, 0);
	}
	
	void addAlien(){
		
		aliens.add(new Alien(ran.nextInt(LeagueInvaders.WIDTH),0,50,50));
	}
	
	void update() {
		for(Alien alien : aliens) {
			if(alien.y>LeagueInvaders.HEIGHT) {
				alien.isActive=false;
			}
		}
	}
	
	void purgeObjects() {
		
	}
}
