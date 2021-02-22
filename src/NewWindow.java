import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class NewWindow implements ActionListener {
	//saves the input window in this variable so that we can go back to it with Adjust button
	MyFrame inputwindow;
	//new frame for new window
	JFrame frame= new JFrame();
	
	JButton back= new JButton("ADJUST INPUT");
	JButton pause = new JButton ("PAUSE");
	JButton resume = new JButton ("RESUME");
	
	JLabel buttons_and_input_label= new JLabel();
	
	JPanel buttons_and_input_panel= new JPanel();
	
	Animation myanimation;
	
	JScrollPane scrollable;
	
	
	NewWindow(String text, String text2, int n, int b, MyFrame inputwindow, MySlider slider) {
		
		this.inputwindow=inputwindow;
		
		back.setPreferredSize(new Dimension(135,40));
		back.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
		back.addActionListener(this);
		back.setForeground(new Color(250,0,250));
		
		pause.setPreferredSize(new Dimension(80,40));
		pause.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
		pause.addActionListener(this);
		pause.setForeground(new Color(250,0,250));
		
		resume.setPreferredSize(new Dimension(95,40));
		resume.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
		resume.addActionListener(this);
		resume.setForeground(new Color(250,0,250));
		
		buttons_and_input_label.setText("Pattern: " +text2 + "       Text: " + text);
		buttons_and_input_label.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		buttons_and_input_label.setForeground(new Color(0x00FF00));
		
		buttons_and_input_panel.setBackground(Color.BLACK);
		buttons_and_input_panel.setSize(new Dimension(800,1500));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("String Matching Algorithms");
		frame.setSize(900,700);
		
		buttons_and_input_panel.add(back);
		buttons_and_input_panel.add(pause);
		buttons_and_input_panel.add(resume);
		buttons_and_input_panel.add(buttons_and_input_label);
		
		frame.add(buttons_and_input_panel, BorderLayout.NORTH);
		
		myanimation=new Animation(text, text2, n, b, slider);
		
		scrollable= new JScrollPane(myanimation, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollable.setPreferredSize(new Dimension(900,650));
		frame.add(scrollable, BorderLayout.CENTER);
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		//if back button is pressed
		if(e.getSource()==back) {
			inputwindow.setVisible(true);
			frame.dispose();
		}
		//if pause button is pressed
		if(e.getSource()==pause) {
			myanimation.timer.stop();
		}
		//if resume button is pressed
		if(e.getSource()==resume) {
			myanimation.timer.restart();
		}
		
	}
}
