package main;

import java.util.ArrayList;
import java.util.Random;
import java.awt.*;

import javax.swing.JPanel;

import entity.Asteroid;

import entity.Enemy;
import entity.EnemyBullet;
import entity.Pillar;
import entity.Plane;
import entity.PlaneBullet;


public class GamePanel extends JPanel implements Runnable{
	
	//Screen
	public final int tileSize = 135;
	public final int screenWidth = 1280;
	public final int screenHeight = 720;
	
	int FPS=60;
	public Random rand;
	public long score=5;
	public boolean gameOver=false;
	int randomIndex=18000;
	
	Thread gameThread;
	public KeyHandler keyH=new KeyHandler(this);
	public Plane plane= new Plane(this);
	Sound music=new Sound();
	Sound se=new Sound();
	
	
	
	
	
	public ArrayList<PlaneBullet> planeBulletsList=new ArrayList<PlaneBullet>();
	public ArrayList<EnemyBullet> enemiesBulletsList=new ArrayList<EnemyBullet>();
	public ArrayList<Pillar> upperPillarsList = new ArrayList<Pillar>();
	public ArrayList<Pillar> lowerPillarsList = new ArrayList<Pillar>();
	public ArrayList<Asteroid> asteroidsList = new ArrayList<Asteroid>();
	public ArrayList<Enemy> enemiesList = new ArrayList<Enemy>();
	
	
	
	
	
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.addMouseListener(keyH);
		this.setFocusable(true);
		
	}
	
	public void startGameThread() {
		gameThread=new Thread(this);
		gameThread.start();
	}
	
	public void setupGame() {
		playMusic(0);
		
	}
	
	
	@Override
	public void run() {
		
		
		
		double drawInterval= 1000000000/FPS;
		double delta=0;
		long lastTime=System.nanoTime();
		long currentTime;
		while(gameThread!=null) {
			currentTime=System.nanoTime();
			delta+=(currentTime-lastTime)/drawInterval;
			lastTime=currentTime;
			
			if(delta>=1) {
				
				update();
				repaint();
				delta--;
				
			}
		}
	}
	
	
	public void update() {
		
		
		
		
		if(gameOver==false) {
			

			rand=new Random();
			
			
			
			//Pillars
			int createPillar=rand.nextInt(20000);
			randomIndex=(int) (19000+(1000-90000/(score+90)));
			
			if(createPillar>=randomIndex && upperPillarsList.isEmpty()) {
				
				int height=rand.nextInt(500)+1;
				int distancePillar=rand.nextInt(120);
				upperPillarsList.add(new Pillar(this,0, 30,height));
				lowerPillarsList.add(new Pillar(this , height+180+distancePillar, 30,
						screenHeight-height-180-distancePillar));
			}
			try {
				for(Pillar pillar : upperPillarsList) {
					pillar.update();
				
				}
			}catch(Exception e){
				
			}
			try {
				for(Pillar pillar : lowerPillarsList) {
					pillar.update();
				
				}
			}catch(Exception e){
				
			}
			
			
			
			//Asteroids
			
			
			
			int createAsteroid=rand.nextInt(20000);
			if(score>=80) {
				randomIndex=(int) (19000+(1000-90000/(score+90)));
			}else {
				randomIndex=(int) (18000+(2000-90000/(score+45)));
			}
			
			if(createAsteroid>=randomIndex && asteroidsList.isEmpty()) {
				int distanceAsteroid=rand.nextInt(100)+50;
				int height=rand.nextInt(screenHeight-distanceAsteroid);
				asteroidsList.add(new Asteroid(this,distanceAsteroid, height));
			}
			try {
				for(Asteroid asteroid : asteroidsList) {
					asteroid.update();
					
					
				}
			}catch(Exception e){
				
			}
			
			
			//Enemies
			
			if(score>=80) {
				int createEnemy=rand.nextInt(20000);
				randomIndex=(int) (19000+(1000-90000/(score+90)));

				if(createEnemy>=randomIndex && enemiesList.isEmpty()) {
					int height=rand.nextInt(screenHeight-tileSize/2);
					enemiesList.add(new Enemy(this, height));
				}
				try {
					for(Enemy enemy : enemiesList) {
						enemy.update();
						
						
					}
				}catch(Exception e){
					
				}
			}
			
			
			
			
			try {
				for(EnemyBullet enemyBullet : enemiesBulletsList) {
					enemyBullet.update();
				}
			}catch(Exception e){
				
			}
			
			
			
			try {
				for(PlaneBullet PlaneBullet : planeBulletsList) {
					PlaneBullet.update();
				}
			}catch(Exception e){
				
			}
			
			
			
			plane.update();
			
			
			
		}else {
			if(keyH.enterPressed==true) {
				gameOver=false;
				score=5;
				plane.x=100;
				plane.y=293;
				plane.life=3;
				plane.energy=144;
				planeBulletsList.removeAll(planeBulletsList);
				enemiesBulletsList.removeAll(enemiesBulletsList);
				upperPillarsList.removeAll(upperPillarsList);
				lowerPillarsList.removeAll(lowerPillarsList);
				asteroidsList.removeAll(asteroidsList);
				enemiesList.removeAll(enemiesList);
				keyH.laserPressed=false;
				plane.laserArea.width=0;
				plane.laserArea.height=0;
			}
		}
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(Color.black);
		g2.fillRect(0,0,screenWidth,screenHeight);
			
			
		try {
			for(Pillar pillar : upperPillarsList) {
				pillar.draw(g2);
					
			}
				
		}catch(Exception e){
				
		}
		try {
			for(Pillar pillar : lowerPillarsList) {
				pillar.draw(g2);
					
			}
		}catch(Exception e){
				
		}
			
			
			
		try {
			for(Asteroid asteroid : asteroidsList) {
				asteroid.draw(g2);
					
			}
		}catch(Exception e){
				
		}
			
		try {
			for(Enemy enemy : enemiesList) {
				enemy.draw(g2);
					
			}
		}catch(Exception e){
				
		}
			
		try {
			for(EnemyBullet enemyBullet : enemiesBulletsList) {
				enemyBullet.draw(g2);
			}
		}catch(Exception e){
				
		}
			
		try {
			for(PlaneBullet PlaneBullet : planeBulletsList) {
				PlaneBullet.draw(g2);
			}
		}catch(Exception e){
				
		}
			
		plane.draw(g2);
			
		if(gameOver==true){
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,70));
			String text="Play Again";
			int x=getXforCenteredText(text, g2);
			g2.setColor(Color.white);
			g2.drawString(text,x,screenHeight/3+100);
			
			
		}
			
			
			
			
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,60));
		String text="Score: "+String.valueOf(score/5-1);
		int x=getXforCenteredText(text, g2);
		g2.setColor(Color.white);
		g2.drawString(text,x,100);

		
		g2.dispose();
	}
	
	
	
	
	public void playMusic(int i) {
		music.setFile(i);
		music.play();
		music.loop();
	}
	public void stopMusic() {
		music.stop();
	}
	public void playSE(int i) {
		se.setFile(i);
		se.play();
	}

	
	public int getXforCenteredText(String text, Graphics2D g2) {
		int length=(int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
		int x=(screenWidth-length)/2;
		return x;
	}

}
