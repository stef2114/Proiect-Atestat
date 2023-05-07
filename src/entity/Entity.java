package entity;

import java.awt.*;


public class Entity {
	
	public int x,y;
	public int life;
	public int speed;
	final int initialSpeed=15;
	public Rectangle solidArea;
	public int spriteCounter=0;
	//public int spriteNum=1;
	//public String name="";
	
	//public GamePanel gp;
	
	
	
	
	public Entity() {
		//solidArea=new Rectangle(6,2,gp.tileSize-12,gp.tileSize);
		//speed=gp.screenWidth/480;
	}

}
