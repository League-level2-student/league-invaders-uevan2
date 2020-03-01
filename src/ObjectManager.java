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
	
	int score = 0;
	
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
		
		checkCollision();
		purgeObjects();
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
				System.out.println("if alien is active");
			}
		}
		for(int i = 0; i <projectile.size(); i++) {
			if(projectile.get(i).isActive==false) {
				projectile.remove(i);
				System.out.println("if projectile is active");
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		addAlien();
	}
	
	void checkCollision(){
		for(int i = 0; i < aliens.size(); i++) {
			if(r.collisionBox.intersects(aliens.get(i).collisionBox)){
				r.isActive=false;
				aliens.get(i).isActive=false;
				System.out.println("check collision alien and rocketship");
			}
		}
		
		for(int i = 0; i < projectile.size(); i++) {
			for(int j = 0; j < aliens.size(); j++) {
				if(projectile.get(i).collisionBox.intersects(aliens.get(j).collisionBox)){
					aliens.get(j).isActive=false;
					projectile.get(i).isActive=false;
					System.err.println("check collision alien and projectile");
					score++;
				}
			}
		}
	}
	
	int getScore() {
		return score;
	}
}
