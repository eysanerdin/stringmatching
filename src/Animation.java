import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.Timer;

public class Animation extends JPanel implements ActionListener{
	
	String text, pattern;
	boolean naive;
	
	public Timer timer;
	
	Map<Character, Integer> heuristic;
	
	char [] pArray;
	char [] tArray;
	//text, pattern length
	int n ,m;
	
	int pos=1;
	boolean red=false;
	int comparisoncount=0;
	
	

	public Animation(String text, String text2, int n, int b, MySlider slider) {
		
		//algorithm type
		if (n==0 && b==1) naive=false;
		else naive=true;
		//initializations
		this.timer=new Timer((-10)*slider.getValue(),this);
		this.text=text;
		this.pattern=text2;
		this.pArray=pattern.toCharArray();
		this.tArray=text.toCharArray();
		this.n=tArray.length;
		this.m=pArray.length;
		this.j=m;
		
		this.paint = new int[m];
		
		for (int i=0; i<m; i++) {
			this.paint[i]=0;
		}
		
		//heuristic table for bmh
		if(!naive) {
			
			heuristic= new HashMap<Character, Integer>();
				
			for (int i=0; i<256; i++) {
				heuristic.put((char)i, m);
					
			}
			
			for (int i=0; i<m-1; i++) {
				heuristic.replace(pArray[i], m-(i+1));
			}
		}
		//design
		this.setPreferredSize(new Dimension(5000, 5000));
		this.setBackground(Color.LIGHT_GRAY);
		this.setLayout(null);
		
		//animation start
		timer.start();
		
	}
	
	//to handle line seperators
    void drawString(Graphics2D g, String text, int x, int y) {
	    for (String line : text.split(ls))
	        g.drawString(line, x, y += g.getFontMetrics().getHeight());
	}
	
	int xkoordinate;
	public void paintComponent (Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2D= (Graphics2D) g;
		 
		
		g2D.setPaint(Color.BLACK);
		g2D.setFont(new Font("Courier", Font.BOLD, 40));
		
		//drawing the text
		g2D.drawChars(tArray, 0, n, 40, 100);
		
		
		//drawing the pattern
		xkoordinate=40+(pos-1)*24;
		
		for (int i=0; i<m; i++) {
			if(paint[i]==(-1)) {
				g2D.setPaint(Color.RED);
				g2D.drawChars(pArray, i, 1, xkoordinate, 130);
				xkoordinate+=24;
				g2D.setPaint(Color.BLACK);
			}
			else if (paint[i]==(1)) {
				g2D.setPaint(Color.GREEN);
				g2D.drawChars(pArray, i, 1, xkoordinate, 130);
				xkoordinate+=24;
				g2D.setPaint(Color.BLACK);
			}
			else {
				g2D.drawChars(pArray, i, 1, xkoordinate, 130);
				xkoordinate+=24;
			}
		}
		
		//drawing the explanations
		g2D.setPaint(Color.BLACK);
		g2D.setFont(new Font("Courier New", Font.BOLD, 12));
		drawString(g2D, print.toString(), 140,230);
		g2D.drawString(("Comparison count :" + comparisoncount), 140, 217);
		g2D.drawString("Pattern Size: " +m+", Text Size: " +n, 140, 230);
	
	}
	
	
	//array that decides in what color characters will be drawn
	int[] paint; //-1:red, 0: black, 1:green
	//variable for character comparison in the algorithms
	int j;
	
	//resets the paint array
	private void resetPaintArray() {
		for (int i=0; i<m; i++) {
			paint[i]=0;
		}
	}
	
	
	String ls=System.lineSeparator();
	
	//print variable
	StringBuilder print = new StringBuilder(ls +"Position 1:" +ls) ;
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
			//if there are still positions to check
			if (pos<=n-m+1) {
				
				//the last action was turning a char to green 
				if (!red) { 
					//if there are still characters to compare at the current position
					if (j>0) {
						//if characters match
						if (pArray[j-1]==tArray[pos+j-2]) {
							comparisoncount++; 
							paint[j-1]=1;
							j--;
							print.append("t["+ (pos+j-1) + "] is equal to w[" + j + "]." + ls);
						  //if characters does not match
						} else {
							comparisoncount++; 
							paint[j-1]=-1;
							red=true;
							j--;
							print.append("t["+ (pos+j-1) + "] is not equal to w[" + j + "]." + ls);
						}
					  //last action green and no characters left to compare at the current position
					} else { 
						print.append("'" +pattern + "' occurs at this position." +ls);
						resetPaintArray();
						//adjusts the position according to the chosen algorithm
						if (naive) pos++;
						else pos+=heuristic.get(tArray[pos+m-2]);
						
						print.append(ls +"Position: " + pos + ls);
							
						j=m;
						
					}
				  //last action was turning a char to red
				} else { 
					print.append("'" +pattern + "' does not occur at this position." +ls);
				    //to not jump any more positions if all characters are checked
					if(pos<n-m+1) {
						resetPaintArray();
						//adjusts the position according to the chosen algorithm
						if (naive) pos++;
						else pos+=heuristic.get(tArray[pos+m-2]);
						
						print.append(ls +"Position: " + pos + ls);
						
						red=false;
						j=m;
						
					} else {
						print.append(ls +"All possible positions checked.");
						timer.stop();
					}
				}
			 //if there are no positions left to check
			} else {
				print.append(ls +"All possible positions checked.");
				timer.stop();
			}
	 
	
			
	} 
	
}