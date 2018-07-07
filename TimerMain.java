import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class TimerMain extends JFrame implements ActionListener{
	private JPanel mainPanel;
	private JButton Start,Stop,Lap,Reset;
	private JLabel timeLabel;
	private JTextArea lapLabel;
	private int lapcount=0;
	String currentTime; int min=0,sec=0,msec=0;
	javax.swing.Timer timer;
	public TimerMain(){
		this.setTitle("StopWatch");
		mainPanel=new JPanel();	
		mainPanel.setLayout(new GridLayout(3,1));
		
		currentTime=String.format("%02d:%02d.%02d",min,sec,msec);
		timeLabel=new JLabel(currentTime,JLabel.CENTER);
		timeLabel.setFont(new Font("Arial", Font.PLAIN, 100));
		JPanel timePanel=new JPanel();
		timePanel.add(timeLabel,BorderLayout.SOUTH);
		sec=0;
		timer=new javax.swing.Timer(10 , this);
		
		Start=new JButton("START"); Stop=new JButton("STOP"); Lap=new JButton("LAP"); Reset=new JButton("RESET");
		Start.setFont(new Font("Arial", Font.PLAIN, 40));
		Stop.setFont(new Font("Arial", Font.PLAIN, 40));
		Lap.setFont(new Font("Arial", Font.PLAIN, 40));
		Reset.setFont(new Font("Arial", Font.PLAIN, 40));
		Start.addActionListener(this); Stop.addActionListener(this); Lap.addActionListener(this); Reset.addActionListener(this);
		Start.setEnabled(true); Stop.setEnabled(false); Lap.setEnabled(false); Reset.setEnabled(false);
		JPanel buttonPanel=new JPanel(); buttonPanel.setLayout(new GridLayout(2,2));
		buttonPanel.add(Start); buttonPanel.add(Stop); buttonPanel.add(Lap); buttonPanel.add(Reset); 
		
		lapLabel=new JTextArea(5,20);
		lapLabel.setFont(new Font("Arial", Font.PLAIN, 50));
		lapLabel.setEditable(false);
	    JScrollPane scroll = new JScrollPane(lapLabel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		JPanel lapPanel=new JPanel();
		lapPanel.add(scroll);
		
		mainPanel.add(timePanel); mainPanel.add(buttonPanel); mainPanel.add(lapPanel);
		this.add(mainPanel);
		
		this.setSize(1000,1000);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent e) {
		msec++; sec+=msec/100; msec%=100; min+=sec/60; sec%=60;
		currentTime=String.format("%02d:%02d.%02d",min,sec,msec);
		timeLabel.setText(currentTime);
		if(e.getSource()==Start) {
			timer.start();
			Start.setEnabled(false); Stop.setEnabled(true); Lap.setEnabled(true); Reset.setEnabled(false);
		}
		if(e.getSource()==Stop) {
			timer.stop();
			Start.setEnabled(true); Stop.setEnabled(false); Lap.setEnabled(false); Reset.setEnabled(true);
		}
		if(e.getSource()==Lap) {
			lapcount++;
			if(lapcount<10) lapLabel.append("   lap  "+lapcount+":                            "+currentTime+"   \n");
			else            lapLabel.append("   lap"+lapcount+":                            "+currentTime+"   \n"); 
			Start.setEnabled(false); Stop.setEnabled(true); Lap.setEnabled(true); Reset.setEnabled(false);
			
		}
		if(e.getSource()==Reset) {
			lapLabel.setText(""); msec=0; sec=0; min=0; lapcount=0;
			currentTime=String.format("%02d:%02d.%02d",min,sec,msec);
			timeLabel.setText(currentTime);
			Start.setEnabled(true); Stop.setEnabled(false); Lap.setEnabled(false); Reset.setEnabled(false);
		}
	}

	
	public static void main(String argv[]) {
		new TimerMain();
	}
}
