package main;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Draw extends JPanel{
	
	Draw(){
		
	}
	public void paint(Graphics g) {
		drawBG(g);
		System.out.println();
	}
	void drawBG(Graphics g) {
		int y=0;
		while(true) {
			drawWhiteRow(y,g);
			y+=100;
			drawBlackRow(y,g);
			y+=100; 
			if(y>600) {
				break;
			}
		}
		drawPeices(g);
		
	}
	public void drawPeices(Graphics g){
		for(Peice pi:Main.peices) {
			pi.draw(g);
		}
	}

	
	void drawWhiteRow(int y,Graphics g) {
		int x=0;
		while(true) {
			g.setColor(Color.white);
			g.fillRect(x, y, 100, 100);
			x+=100;
			g.setColor(Color.DARK_GRAY);
			g.fillRect(x, y, 100, 100);
			x+=100;
			if(x>600) {
				break;
			}
		}
	}
	void drawBlackRow(int y,Graphics g) {
		int x=0;
		while(true) {
			g.setColor(Color.DARK_GRAY);
			g.fillRect(x, y, 100, 100);
			x+=100;
			g.setColor(Color.white);
			g.fillRect(x, y, 100, 100);
			x+=100;
			if(x>600) {
				break;
			}
		}
	}
}
