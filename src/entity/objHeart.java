package entity;

import java.io.IOException;
import java.awt.image.*;
import javax.imageio.ImageIO;
import main.UtilityTool;
import main.GamePanel;

public class objHeart{

	GamePanel gp;
	public BufferedImage image1,image2;
	public String name="";
	UtilityTool uTool = new UtilityTool();
	public objHeart(GamePanel gp) {
		this.gp=gp;	
		name="Heart";
		try{
			image1=ImageIO.read(getClass().getResourceAsStream("/hearts/heart_full.png"));
			image2=ImageIO.read(getClass().getResourceAsStream("/hearts/heart_blank.png"));
			image1=uTool.scaleImage(image1,gp.tileSize/2,gp.tileSize/2);
			image2=uTool.scaleImage(image2,gp.tileSize/2,gp.tileSize/2);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
