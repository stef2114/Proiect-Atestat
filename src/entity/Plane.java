package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Plane extends Entity{

	
	GamePanel gp;
	public int energy;
	int spriteCounter=0;
	int spriteNum=0;
	public Rectangle laserArea;
	public BufferedImage image0, image1, image2;
	BufferedImage heart_full,heart_blank;
	
	public Plane(GamePanel gp) {
		super();
		this.gp=gp;
		
		x=100;
		y=293;
		life=3;
		energy=144;
		speed=12;
		solidArea=new Rectangle(100+20,y+20, gp.tileSize-50,gp.tileSize-40);
		laserArea=new Rectangle();	
		
		image0=setup(0,(int)1.5*gp.tileSize,gp.tileSize);
		image1=setup(1,(int)1.5*gp.tileSize,gp.tileSize);
		image2=setup(2,(int)1.5*gp.tileSize,gp.tileSize);
	}
	

	public void update() {

		
		
		speed=(int)Math.sqrt(gp.score+15)+10;
		spriteCounter++;
		if(gp.keyH.downPressed==true && y<gp.screenHeight-gp.tileSize-speed) {
			y+=speed;
			solidArea.y=y+20;
		}
		if(gp.keyH.upPressed==true && y>speed) {
			y-=speed;
			solidArea.y=y+20;
		}
		if(gp.keyH.leftPressed==true && x>speed) {
			x-=(int)speed/2;
			solidArea.x=x+20;
		}
		if(gp.keyH.rightPressed==true && x<gp.screenWidth-gp.tileSize-speed) {
			x+=(int)speed/2;
			solidArea.x=x+20;
		}
		if(gp.keyH.firePressed==true && spriteCounter%10==0 && gp.keyH.laserPressed==false) {
			
			PlaneBullet bullet=new PlaneBullet(gp,x+gp.tileSize, y+(int)gp.tileSize/2-10);
			gp.planeBulletsList.add(bullet);
		}
		if(gp.keyH.laserPressed==true) {
			laserArea.x=x+gp.tileSize;
			laserArea.y=y+(int)gp.tileSize/2-20;
			laserCollision();
			laserArea.height=40;
			energy--;
			if(energy==0) {
				gp.keyH.laserPressed=false;
				laserArea.width=0;
				laserArea.height=0;
			}
			
			
		}
		
		if(spriteCounter>=20) {
			spriteCounter=0;
			spriteNum++;
			if(spriteNum==3) {
				spriteNum=0;
			}
			if(energy<144 && gp.keyH.laserPressed==false) {
				energy+=6;
			}
		}
		collisionCheck();
		if(life==0) {
			gp.gameOver=true;
		}
			
		
	}
	
	
	public void collisionCheck() {
		
		
		for(Pillar pillar : gp.upperPillarsList) {
			if(solidArea.intersects(pillar.solidArea)) {
				life=0;
			}
		}
		for(Pillar pillar : gp.lowerPillarsList) {
			if(solidArea.intersects(pillar.solidArea)) {
				life=0;
			}
		}
		for(Asteroid asteroid : gp.asteroidsList) {
			if(solidArea.intersects(asteroid.solidArea)) {
				life=0;
			}
		}
		for(Enemy enemy : gp.enemiesList) {
			if(solidArea.intersects(enemy.solidArea)) {
				life=0;
			}
		}
	}
	
	
	
	public int laserCollision() {
		
		for(int laserX=x+gp.tileSize;laserX<=gp.screenWidth;laserX++) {
			laserArea.width=laserX;
			for(Pillar pillar : gp.upperPillarsList) {
				if(laserArea.intersects(pillar.solidArea)) {
					return 0;
				}
			}
			for(Pillar pillar : gp.lowerPillarsList) {
				if(laserArea.intersects(pillar.solidArea)) {
					return 0;
				}
			}
			for(Asteroid asteroid : gp.asteroidsList) {
				if(laserArea.intersects(asteroid.solidArea)) {
					asteroid.life-=10;
					return 0;
				}
			}
			for(Enemy enemy : gp.enemiesList) {
				if(laserArea.intersects(enemy.solidArea)) {
					enemy.life-=10;
					return 0;
				}
			}
			for(EnemyBullet enemyBullet : gp.enemiesBulletsList) {
				if(laserArea.intersects(enemyBullet.solidArea)) {
					gp.enemiesBulletsList.remove(enemyBullet);					
					return 0;
				}
			}
		}
		
		return 0;
	}
	
	

	public void draw(Graphics2D g2) {

		BufferedImage image=null;
		if(spriteNum==0) {image=image0;}
		if(spriteNum==1) {image=image1;}
		if(spriteNum==2) {image=image2;}
		g2.drawImage(image,x,y,null);
		
		g2.setColor(Color.red);
		g2.fillRect(laserArea.x,laserArea.y,laserArea.width,laserArea.height);
		g2.setColor(Color.white);
		g2.drawRect(496-1,gp.screenHeight-110-1,288+2,20+2);
		g2.setColor(Color.yellow);
		g2.fillRect(496,gp.screenHeight-110,2*energy,20);
		
		objHeart heart=new objHeart(gp);
		heart_full=heart.image1;
		heart_blank=heart.image2;
		int distanceX=70;
		int distanceY=70;
		for(int remainingLife=1;remainingLife<=life;remainingLife++) {
			g2.drawImage(heart_full,distanceX,distanceY,null);
			distanceX+=70;
		}
		for(int neededLife=life;neededLife<3;neededLife++) {
			g2.drawImage(heart_blank,distanceX,distanceY,null);
			distanceX+=70;
		}
		
	}
	
	
	
	
	public BufferedImage setup(int index, int width, int height) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image =null;
		try {
			image=ImageIO.read(getClass().getResourceAsStream("/planes/grayship"+index+".png"));
			image=uTool.scaleImage(image,width,height);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		return image;
	}

}
