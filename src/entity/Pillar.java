package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import main.GamePanel;

public class Pillar extends Entity{
	
	int width, height;
	GamePanel gp;
	public Pillar (GamePanel gp, int y, int width, int height) {
		super();
		this.gp=gp;
		
		this.y=y;
		this.width=width;
		this.height=height;
		speed=15;
		x=gp.screenWidth+100;
		solidArea=new Rectangle(x,y,width,height);
	}
	
	
	public void update() {
		speed=(int)Math.sqrt(gp.score)+15;
		x-=speed;
		solidArea.x=x;
		if(collisionCheck()==true) {
			if(gp.upperPillarsList.contains(this)) {
				gp.upperPillarsList.remove(this);
				gp.score+=5;
			}else {
				gp.lowerPillarsList.remove(this);
			}
			
		}
	}
	public boolean collisionCheck() {
		if(x<=-width) {
			return true;
		}
		return false;
	}

	public void draw(Graphics2D g2) {
		g2.setColor(Color.blue);
		g2.fillRect(x,y, width,height);
	}

}
