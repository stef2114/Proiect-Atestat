package entity;

import main.GamePanel;
import java.awt.*;

public class EnemyBullet extends Entity{
	
	
	GamePanel gp;

	public EnemyBullet(GamePanel gp,int x, int y) {
		super();
		this.gp=gp;
		this.y=y;
		this.x=x;
		solidArea=new Rectangle(x,y, 30,20);
	}
	
	public void update() {
		speed=(int)Math.sqrt(gp.score)+15;
		x=x-5*speed/4;
		solidArea.x=x;
		if(collisionCheck()==true) {
			gp.enemiesBulletsList.remove(this);
		}
		
	}
	public boolean collisionCheck() {
		if(x<=-30) {
			return true;
		}
		for(Pillar pillar : gp.upperPillarsList) {
			if(solidArea.intersects(pillar.solidArea)) {
				return true;
			}
		}
		for(Pillar pillar : gp.lowerPillarsList) {
			if(solidArea.intersects(pillar.solidArea)) {
				return true;
			}
		}
		for(Asteroid asteroid : gp.asteroidsList) {
			if(solidArea.intersects(asteroid.solidArea)) {
				asteroid.life-=60;
				return true;
			}
		}
		
		
		
		if(solidArea.intersects(gp.plane.solidArea)) {
			gp.plane.life--;
			return true;
		}
		
		return false;
	}

	public void draw(Graphics2D g2) {
		g2.setColor(Color.red);
		g2.fillRect(x,y, 30,20);
	}

}
