package entity;

import main.GamePanel;
import java.awt.*;

public class PlaneBullet extends Entity{
	
	
	GamePanel gp;

	public PlaneBullet(GamePanel gp,int x, int y) {
		super();
		this.gp=gp;
		this.y=y;
		this.x=x;
		solidArea=new Rectangle(x,y, 30,20);
	}
	
	public void update() {
		speed=(int)Math.sqrt(gp.score)+15;
		x+=speed;
		solidArea.x=x;
		if(collisionCheck()==true) {
			gp.planeBulletsList.remove(this);
			
		}
		
	}
	public boolean collisionCheck() {
		if(x>=gp.screenWidth+30) {
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
		for(EnemyBullet enemyBullet : gp.enemiesBulletsList) {
			if(solidArea.intersects(enemyBullet.solidArea)) {
				gp.enemiesBulletsList.remove(enemyBullet);
				return true;
				
			}
		}
		for(Enemy enemy : gp.enemiesList) {
			if(solidArea.intersects(enemy.solidArea)) {
				enemy.life-=60;
				return true;
			}
		}
		
		return false;
	}

	public void draw(Graphics2D g2) {
		g2.setColor(Color.red);
		g2.fillRect(x,y, 30,20);
	}

}
