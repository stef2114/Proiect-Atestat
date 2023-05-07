package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import main.GamePanel;

public class Asteroid extends Entity{
	
	int size;
	GamePanel gp;
	public Asteroid (GamePanel gp, int size, int y) {
		super();
		this.gp=gp;
		x=gp.screenWidth+100;
		this.size=size;
		life=size;
		this.y=y;
		speed=15;
		solidArea=new Rectangle(x,y,size,size);
	}
	
	
	public void update() {
		speed=(int)Math.sqrt(gp.score)+15;
		x-=speed;
		solidArea.x=x;
		if(collisionCheck()==true) {
			gp.asteroidsList.remove(this);
		}
		if(life<=0) {
			gp.score+=5;
			gp.asteroidsList.remove(this);
		}
	}
	public boolean collisionCheck() {
		if(x<=-size) {
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
		return false;
	}

	public void draw(Graphics2D g2) {
		g2.setColor(Color.gray);
		g2.fillRect(x,y, size,size);
	}

}