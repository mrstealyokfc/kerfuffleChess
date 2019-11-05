package main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JFrame;
//hello to anyone who reads this code 
// have fun trying to understand it 
//i have no comments cuz comments gay
//yeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee
//@author JENSEN FUCKING WHANG IM NOT HIGH RN
//TODO remove n words
public class Main {
	public static Peice[] peices= {
		new Peice(0,0,4,0),new Peice(1,0,3,0),new Peice(2,0,2,0),new Peice(2,0,5,0)	,new Peice(3,0,0,0),
		new Peice(3,0,7,0),new Peice(4,0,1,0),new Peice(4,0,6,0),new Peice(5,0,0,1),new Peice(5,0,1,1),
		new Peice(5,0,2,1),new Peice(5,0,3,1),new Peice(5,0,4,1),new Peice(5,0,5,1),new Peice(5,0,6,1),
		new Peice(5,0,7,1),
		new Peice(0,1,3,7),new Peice(1,1,4,7),new Peice(2,1,2,7),new Peice(2,1,5,7),new Peice(3,1,0,7),
		new Peice(3,1,7,7),new Peice(4,1,1,7),new Peice(4,1,6,7),new Peice(5,1,0,6),new Peice(5,1,1,6),
		new Peice(5,1,2,6),new Peice(5,1,3,6),new Peice(5,1,4,6),new Peice(5,1,5,6),new Peice(5,1,6,6),
		new Peice(5,1,7,6)

	};
	public static Draw draw = new Draw();
	public static void main(String[] args) {
		
		JFrame wind = new JFrame("kerfuffle");
		wind.setSize(815,840);
		wind.setVisible(true);
		wind.setFocusable(true);
		wind.add(draw);
		draw.repaint();
		movePeice();
		while(true) {
			draw.repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			movePeice();
		}
		
	}
	public static void movePeice() {
		
		
		putMap();
		
		while(true){
			int peiceIndex=0;
			Scanner ooi=new Scanner(System.in);
			//command format
			String command= ooi.nextLine().toLowerCase();
			if(parseCommand(command)==null) {
				System.out.println("enter a valid command");
				continue;
			}
			int[] cord=parseCommand(command);
			System.out.println(Arrays.toString(parseCommand(command)));
			for(int i:cord) {
				if(i>7||i<0) {
					System.out.println("you cannot do that ");
					continue;
				}
			}
			
			boolean moved =false;
			for(int i=0;i<peices.length;i++) {
				if(peices[i].x==cord[0]&&peices[i].y==cord[1]) {
					peiceIndex=i;
					moved=true;
				}
				
			}
			if(!isLegal(cord,peiceIndex)) {
				moved=false;
			}
			if(!moved) {
				System.out.println("you cannot do that");
				continue;
			}else {
				peices[peiceIndex].x=cord[2];
				peices[peiceIndex].y=cord[3];
			}
			
			break;
		}
		
	}
	public static boolean isLegal(int[] move,int index){
		int type=peices[index].type;
		if(type==0) {
			int xRange,yRange;
			xRange=move[0]-move[2];
			yRange=move[1]-move[3];
			if((xRange==1||xRange==-1)&&(yRange>=-1||yRange<=1)) {
				if(-1==isFull(move[2],move[3])) {
					return true;
				}else if(peices[isFull(move[2],move[3])].side!=peices[index].side){
					peices[isFull(move[2],move[3])].take();
					return true;
				}
				
			}
			if((yRange==1||yRange==-1)&&(xRange>=-1||xRange<=1)) {
				if(-1==isFull(move[2],move[3])) {
					return true;
				}else if(peices[isFull(move[2],move[3])].side!=peices[index].side){
					peices[isFull(move[2],move[3])].take();
					return true;
				}
				
			}
		}
		if(type==3) {
			if(move[0]==move[2]) {
				//moves on the y axis
				if(move[3]>move[1]) {
					//moves on the positive y axis(down on the screen)
					
					int y=move[1];
					int x=move[0];
					while(true) {
						y++;
						if(y==move[3]&&(isFull(x,y)==-1)) {
							return true;
						}else if(y==move[3]&&peices[isFull(x,y)].side==peices[index].side) {
							return false;
						}else if(y==move[3]&&peices[isFull(x,y)].side!=peices[index].side) {
							peices[isFull(x,y)].take();
							return true;
						}else if(isFull(x,y)!=-1) {
							return false;
						}
					}
				}else if(move[3]<move[1]) {
					int y=move[1];
					int x=move[0];
					while(true) {
						y--;
						if(y==move[3]&&(isFull(x,y)==-1)) {
							return true;
						}else if(y==move[3]&&peices[isFull(x,y)].side==peices[index].side) {
							return false;
						}else if(y==move[3]&&peices[isFull(x,y)].side!=peices[index].side) {
							peices[isFull(x,y)].take();
							return true;
						}else if(isFull(x,y)!=-1) {
							return false;
						}
					}
				}
			}else {
				if(move[2]>move[0]) {
					//moves on the positive x axis(right on the screen)
					
					int y=move[1];
					int x=move[0];
					while(true) {
						x++;
						if(x==move[2]&&(isFull(x,y)==-1)) {
							return true;
						}else if(x==move[2]&&peices[isFull(x,y)].side==peices[index].side) {
							return false;
						}else if(x==move[2]&&peices[isFull(x,y)].side!=peices[index].side) {
							peices[isFull(x,y)].take();
							return true;
						}else if(isFull(x,y)!=-1) {
							return false;
						}
					}
				}else if(move[2]<move[0]) {
					int y=move[1];
					int x=move[0];
					while(true) {
						x--;
						if(x==move[2]&&(isFull(x,y)==-1)) {
							return true;
						}else if(x==move[2]&&peices[isFull(x,y)].side==peices[index].side) {
							return false;
						}else if(x==move[2]&&peices[isFull(x,y)].side!=peices[index].side) {
							peices[isFull(x,y)].take();
							return true;
						}else if(isFull(x,y)!=-1) {
							return false;
						}
					}
				}
				
			}
		}
		if(type==5) {
			if((move[0]==move[2])&&(move[1]-move[3]==1||move[1]-move[3]==-1)) {
				if(isFull(move[2],move[3])==-1) {
					return true;
				}
			}
			if(!(isFull(move[2],move[3])==-1)) {
				int dx=move[0]-move [2];
				int dy=move[1]-move[3];
				if((dx==1||dx==-1)&&(dy==1||dy==-1)&&peices[isFull(move[2],move[3])].side!=peices[index].side) {
					peices[isFull(move[2],move[3])].take();
					return true;
				}
			}
		}
		if(type==2) {
			int dx=move[0]-move[2];
			int dy=move[1]-move[3];
			if(dx==0||dy==0||dx*dx!=dy*dy) {
				return false;
			}
			int xDir=0;
			int yDir=0;
			if(dx>0) {
				xDir=-1;
			}else {
				xDir=1;
			}
			if(dy>0) {
				yDir=-1;
			}else {
				yDir=1;
			}
			int x=move[0];
			int y=move[1];
			while(true) {
				x+=xDir;
				y+=yDir;
				System.out.println(x);
				System.out.println(move[2]);
				if(x==move[2]&&(isFull(x,y)==-1)) {
					return true;
				}else if(x==move[2]&&peices[isFull(x,y)].side==peices[index].side) {
					return false;
				}else if(x==move[2]&&peices[isFull(x,y)].side!=peices[index].side) {
					peices[isFull(x,y)].take();
					return true;
				}else if(isFull(x,y)!=-1) {
					return false;
				}
				System.out.println(x+" "+y);
				if(x*x>100) {
					break;
				}
			}
			
		}
		if(type==1) {
			if(move[0]==move[2]||move[1]==move[3]) {
				//moves as a rook
				
				peices[index].type=3;
				boolean legal=isLegal(move,index);
				peices[index].type=1;
				return legal;
			}else {
				peices[index].type=2;
				boolean legal=isLegal(move,index);
				peices[index].type=1;
				return legal;
			}
			
		}
		return false;
	}
	static HashMap<String, Integer> map = new HashMap<String,Integer>();
	static void putMap(){
		map.put("a",0);
		map.put("b",1);
		map.put("c",2);
		map.put("d",3);
		map.put("e",4);
		map.put("f",5);
		map.put("g",6);
		map.put("h",7);
		map.put("0",0);
		map.put("1",1);
		map.put("2",2);
		map.put("3",3);
		map.put("4",4);
		map.put("5",5);
		map.put("6",6);
		map.put("7",7);
	}
	public static int[] parseCommand(String cmd) {
		int[] boi=new int[4];
		try {
			String[] split=cmd.split(" ");
			boi[0]=map.get(String.valueOf(split[0].charAt(1)));
			boi[1]=map.get(String.valueOf(split[0].charAt(0)));
			boi[2]=map.get(String.valueOf(split[2].charAt(1)));
			boi[3]=map.get(String.valueOf(split[2].charAt(0)));	
		}catch(Exception e) {
			boi=null;
			e.printStackTrace();
		}
		
		return boi;
		
	}
	public static int isFull(int x,int y) {
		for(int i=0;i<peices.length;i++) {
			if(peices[i].x==x&&peices[i].y==y) {
				return i;
			}
		}
		return -1;
	}
	
	
}
