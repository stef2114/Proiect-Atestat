package entity;


import main.GamePanel;
import java.awt.*;
import java.util.Random;

public class Enemy extends Entity{
	
	
	GamePanel gp;
	static Random rand=new Random();
	

	public Enemy(GamePanel gp, int y) {
		super();
		this.gp=gp;
		this.y=y;
		speed=-15;
		x=gp.screenWidth+100;
		life=120;
		solidArea=new Rectangle(x,y, (int)gp.tileSize/2,(int)gp.tileSize/2);
	}
	
	public void update() {
		speed=(int)Math.sqrt(gp.score)+15;
		x-=speed;
		solidArea.x=x;
		if(collisionCheck()==true) {
			gp.enemiesList.remove(this);
		}
		if(life<=0) {
			gp.score+=5;
			gp.enemiesList.remove(this);
		}
		spriteCounter++;
		if(spriteCounter>=28) {
			
			int createBullet=rand.nextInt(20000);
			if(createBullet>14000) {
				spriteCounter=0;
				EnemyBullet bullet=new EnemyBullet(gp,x-35, y+(int)gp.tileSize/4-10);
				gp.enemiesBulletsList.add(bullet);
			}
		}
		
	}
	public boolean collisionCheck() {
		if(x<=0) {
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
		g2.setColor(Color.green);
		g2.fillRect(x,y, gp.tileSize/2,gp.tileSize/2);
	}

}

