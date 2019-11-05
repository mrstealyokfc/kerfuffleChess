package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Peice {
	int x,y;
	int type;
	int side;
	boolean taken = false;
	BufferedImage img=null;
	Color[] sideColor= {
		Color.red,Color.blue	
	};
	Peice(int type,int side, int x,int y){
		this.type=type;
		this.side=side;
		this.x=x;
		this.y=y;
		img=getImg();
	}
	public BufferedImage getImg() {
		BufferedImage img=null;
		String[] fileNames= {
				"./peices/chess_kn.png","./peices/chess_qu.png","./peices/chess_bi.png","./peices/chess_rk.png"	,
				"./peices/chess_hej.png", "./peices/chess_pn.png"
		};
		try {
			img=ImageIO.read(new File(fileNames[type]));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return img;
	}
	public void draw(Graphics g){
		if(!taken) {
			Graphics2D g2d=(Graphics2D) g;
			g2d.drawImage(img,getTrans(), null);
			g.setColor(sideColor[side]);
			g.fillRect((x*100), (y*100), 25, 25);
			g.fillRect((x*100)+75, (y*100), 25, 25);
			g.fillRect((x*100), (y*100)+75, 25, 25);
			g.fillRect((x*100)+75, (y*100)+75, 25, 25);
		}
		
	}
		public AffineTransform getTrans() {
		AffineTransform trans=new AffineTransform();
		trans.translate(x*100,y*100);
		trans.scale((1.0/5.0),(1.0/5.0));
		return trans;
	}
	public void take() {
		y=-1;
		x=-1;
		side=-1;
		type=-1;
		taken=true;
	}
}
