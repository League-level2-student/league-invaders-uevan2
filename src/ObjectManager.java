import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener {
	Rocketship r;
	
	ArrayList<Projectile> projectile = new ArrayList<Projectile>();
	
	ArrayList<Alien> aliens = new ArrayList<Alien>();
	
	Random ran = new Random();
	
	ObjectManager(Rocketship r){
		this.r=r;
	}
	
	void addAlien(){
		aliens.add(new Alien(ran.nextInt(LeagueInvaders.WIDTH),0,50,50));
	}
	
	void addProjectile(Projectile p) {
		this.projectile.add(p);
	}
	
	void update() {
		for(Alien alien : aliens) {
			alien.update();
			if(alien.y>LeagueInvaders.HEIGHT) {
				alien.isActive=false;
			}
		}
	}
	
	void draw(Graphics g){
		this.r.draw(g);
		for(Alien alien : aliens) {
			alien.draw(g);
		}
		for(Projectile projectile : projectile) {
			projectile.draw(g);
		}
	}
	
	void purgeObjects() {
		for(int i = 0; i <aliens.size(); i++) {
			if(aliens.get(i).isActive==false) {
				aliens.remove(i);
			}
		}
		for(int i = 0; i <projectile.size(); i++) {
			if(projectile.get(i).isActive==false) {
				projectile.remove(i);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		addAlien();
		System.out.println("test");
	}
}
