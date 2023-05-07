package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

public class KeyHandler implements KeyListener, MouseListener{

	public boolean downPressed=false,upPressed=false,leftPressed=false,rightPressed=false;
	public boolean firePressed=false,laserPressed=false,enterPressed=false;
	
	GamePanel gp;
	public KeyHandler(GamePanel gp) {
		this.gp=gp;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		int code=e.getKeyCode();
		if(gp.gameOver==false) {
			playState(code);
		}else {
			pauseState(code);
		}
		
	
	}
	
	public void pauseState(int code) {
		if(code==KeyEvent.VK_ENTER) {
			enterPressed=true;
		}
		
	}
	
	public void playState(int code) {
		switch(code) {
		case KeyEvent.VK_W:
			upPressed=true;
			break;
		case KeyEvent.VK_S:
			downPressed=true;
			break;
		case KeyEvent.VK_A:
			leftPressed=true;
			break;
		case KeyEvent.VK_D:
			rightPressed=true;
			break;
		
		
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code=e.getKeyCode();
			switch(code) {
			case KeyEvent.VK_W:
				upPressed=false;
				break;
			case KeyEvent.VK_S:
				downPressed=false;
				break;
			case KeyEvent.VK_A:
				leftPressed=false;
				break;
			case KeyEvent.VK_D:
				rightPressed=false;
				break;
			case KeyEvent.VK_ENTER:
				enterPressed=false;
				break;
			
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		if(SwingUtilities.isLeftMouseButton(e)) {
			firePressed=true;
		}else if(SwingUtilities.isRightMouseButton(e) && gp.plane.energy==144){
			laserPressed=true;
		}
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(SwingUtilities.isLeftMouseButton(e)) {
			firePressed=false;
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
