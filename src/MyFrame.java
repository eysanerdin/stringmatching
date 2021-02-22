import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;

public class MyFrame  extends JFrame implements ActionListener{
	
	
	//textfields for entering pattern and text
	JTextField patternText = new JTextField(); 
	JTextField textText = new JTextField();
	
	//button to start animation 
	JButton animate = new JButton ("Animate");
	
	//buttons for choosing the algorithm
	JRadioButton naive = new JRadioButton("Naive");
	JRadioButton bmh= new JRadioButton("Boyer Moore Horspool");
	ButtonGroup group = new ButtonGroup();
	
	JPanel input= new JPanel();
	
	MySlider slider;


	MyFrame() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("String Matching Algorithms");
		this.setSize(700,700);
		
		//pattern textfield design
		patternText.setText("ABCDABD");
		patternText.setPreferredSize(new Dimension(250,40));
		patternText.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		patternText.setForeground(new Color(0x00FF00));
		patternText.setBackground(Color.black);
		patternText.setCaretColor(Color.white);
		
		//text textfield design
		textText.setText("ABC ABCDAB ABCDABCDABDE");
		textText.setPreferredSize(new Dimension(250,40));
		textText.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		textText.setForeground(new Color(0x00FF00));
		textText.setBackground(Color.black);
		textText.setCaretColor(Color.white);
		
		//animate button design
		animate.setPreferredSize(new Dimension(85,40));
		//animate.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		animate.setForeground(Color.DARK_GRAY);
		animate.setFont(new Font("Courier", Font.BOLD, 16));
		animate.addActionListener(this);
		
		//naive, bmh button design
		naive.setForeground(Color.DARK_GRAY);
		naive.setFont(new Font("Courier", Font.BOLD, 16));
		bmh.setForeground(Color.DARK_GRAY);
		bmh.setFont(new Font("Courier", Font.BOLD, 16));
		naive.addActionListener(this);
		bmh.addActionListener(this);
		
		group.add(naive);
		group.add(bmh);
		
		input.add(animate);
		input.add(patternText);
		input.add(textText);
		
		input.add(naive);
		input.add(bmh);

		this.setLayout(new BorderLayout());
		this.add(input, BorderLayout.NORTH);
		slider = new MySlider();
		this.add(slider);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		
	}
	
	//variables for determining which button is clicked
	int n=0,b=0;

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//if naive button is selected save it by using variables n,b
		if(e.getSource()==naive) {
			
			n=1; 
			b=0;
			
		} 
		//if bmh button is selected save it by using variables n,b
		if (e.getSource()==bmh) {
			
			b=1; 
			n=0;
			
		}
		
		//if animate button is selected, create the animation according to the chosen algorithm 
		if (e.getSource()==animate) {
			//if an algorithm is not selected
			if (n==0 && b==0) {
				//creates a warning message that demands the user to select an algorithm 
				JOptionPane.showMessageDialog(null, "Please choose the algorithm", "Warning", JOptionPane.WARNING_MESSAGE);
			}
			//if an algorithm is selected
			else {
				//Creates the Animation window 
				NewWindow newwindow= new NewWindow(textText.getText(),patternText.getText(),n ,b, this, slider);
				this.dispose();
				
				
			}
		  
		  
		}
		
	}
}
