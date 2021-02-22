import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MySlider extends JPanel implements ChangeListener{
	
	JLabel label;
	JSlider slider;
	 
	
	public int getValue() {
		return slider.getValue();
	}
	
	MySlider() {
		
		 this.setLayout(new FlowLayout());
		
		 slider = new JSlider(-100, 0, -50);
		 slider.setPreferredSize(new Dimension(180,50));
		 
		 slider.setMinorTickSpacing(10);
		 slider.setPaintLabels(true);
		 
		 label= new JLabel();
		 label.setForeground(Color.DARK_GRAY);
		 label.setFont(new Font("Courier", Font.BOLD, 16));
		 label.setText("Adjust Speed");
		 this.add(label);
		 this.add(slider);
		 this.setVisible(true);
		 
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
