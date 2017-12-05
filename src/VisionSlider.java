import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class VisionSlider {

	//USB - min and max values for each slider
	static final int MIN_HUE_MIN = 0;
	static final int MIN_HUE_MAX = 255;
	static final int MAX_HUE_MIN = 0;
	static final int MAX_HUE_MAX = 255;
	static final int MIN_SAT_MIN = 0;
	static final int MIN_SAT_MAX = 255;
	static final int MAX_SAT_MIN = 0;
	static final int MAX_SAT_MAX = 255;
	static final int MIN_VAL_MIN = 0;
	static final int MIN_VAL_MAX = 255;
	static final int MAX_VAL_MIN = 0;
	static final int MAX_VAL_MAX = 255;
	
	//Pi - min and max values for each slider
	static final int MIN_HUE_MIN2 = 0;
	static final int MIN_HUE_MAX2 = 255;
	static final int MAX_HUE_MIN2 = 0;
	static final int MAX_HUE_MAX2 = 255;
	static final int MIN_SAT_MIN2 = 0;
	static final int MIN_SAT_MAX2 = 255;
	static final int MAX_SAT_MIN2 = 0;
	static final int MAX_SAT_MAX2 = 255;
	static final int MIN_VAL_MIN2 = 0;
	static final int MIN_VAL_MAX2 = 255;
	static final int MAX_VAL_MIN2 = 0;
	static final int MAX_VAL_MAX2 = 255;
	
	private Color foreColor = Color.WHITE;
	private Color backColor = Color.BLACK;
	static final String SIZE = "small";
	int minHueInit, maxHueInit, minSatInit, maxSatInit, minValInit, maxValInit,
		minHueInit2, maxHueInit2, minSatInit2, maxSatInit2, minValInit2, maxValInit2;

	public VisionSlider() {
		/*
		String colorChoice = JOptionPane.showInputDialog("Choose color: blue, green, maroon");
		switch(colorChoice){
			case "blue": foreColor = new Color(112,255,216);
						 backColor = new Color(38,38,38); break;
			case "green": foreColor = Color.GREEN;
						  backColor = Color.BLACK; break;
			case "maroon": foreColor = new Color(255,255,255);
						   backColor = new Color(50,0,0); break;
			default: break;
		} 
		*/
		
		File initFile = new File(System.getProperty("user.home") + "/Desktop/init.txt");
		Scanner in;
		try {
			in = new Scanner(initFile);
			minHueInit = Integer.parseInt(in.nextLine());
			maxHueInit = Integer.parseInt(in.nextLine());
			minSatInit = Integer.parseInt(in.nextLine());
			maxSatInit = Integer.parseInt(in.nextLine());
			minValInit = Integer.parseInt(in.nextLine());
			maxValInit = Integer.parseInt(in.nextLine());
			
			minHueInit2 = Integer.parseInt(in.nextLine());
			maxHueInit2 = Integer.parseInt(in.nextLine());
			minSatInit2 = Integer.parseInt(in.nextLine());
			maxSatInit2 = Integer.parseInt(in.nextLine());
			minValInit2 = Integer.parseInt(in.nextLine());
			maxValInit2 = Integer.parseInt(in.nextLine());
			in.close();
		} catch (FileNotFoundException e2) {
			minHueInit= maxHueInit=minSatInit= maxSatInit= minValInit= maxValInit=
			minHueInit2= maxHueInit2= minSatInit2= maxSatInit2= minValInit2= maxValInit2= 0;
		}
		
		
		JFrame frame = new JFrame("Vision Slider by Kazuki Shin");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if(SIZE.equals("big")){
			//frame.setSize(1200,1800);
			frame.setSize(1100,1800);
			
		}
		else{
			frame.setSize(500, 800);
			//frame.setSize(500, 400);
		}
	    frame.setLocationRelativeTo(null);
	    frame.setResizable(false);
	    GridLayout layout = new GridLayout(1, 2);
	    frame.setLayout(layout);
	    
	    JPanel sliderPanel = new JPanel();
	    sliderPanel.setBackground(backColor);
	    sliderPanel.setLayout(new BoxLayout(sliderPanel,BoxLayout.PAGE_AXIS));
	    
	    JPanel sliderPanel2 = new JPanel();
	    sliderPanel2.setBackground(backColor);
	    sliderPanel2.setLayout(new BoxLayout(sliderPanel2,BoxLayout.PAGE_AXIS));
	    
	    /*
	     * Labels for slider
	     */
	    ArrayList<JLabel> sliderLabels = new ArrayList<JLabel>();
	    sliderLabels.add(new JLabel("Hue Min", JLabel.CENTER));
	    sliderLabels.add(new JLabel("Hue Max", JLabel.CENTER));
	    sliderLabels.add(new JLabel("Saturation Min", JLabel.CENTER));
	    sliderLabels.add(new JLabel("Saturation Max", JLabel.CENTER));
	    sliderLabels.add(new JLabel("Value Min", JLabel.CENTER));
	    sliderLabels.add(new JLabel("Value Max", JLabel.CENTER));
	    
	    Font labelFont = null;
	    if(SIZE.equals("big"))
	    	labelFont = new Font("Times New Roman", Font.BOLD, 60);
	    else
	    	labelFont = new Font("Times New Roman", Font.BOLD, 20);
	    for(JLabel e: sliderLabels){
	    	e.setFont(labelFont);
	    	e.setForeground(foreColor);
	    	e.setAlignmentX(Component.CENTER_ALIGNMENT);
	    }
	    
	    /*
	     * Creates the sliders
	     */
	    ArrayList<JSlider> sliders = new ArrayList<JSlider>();
	    sliders.add(new JSlider(JSlider.HORIZONTAL, MIN_HUE_MIN, MIN_HUE_MAX, minHueInit));
	    sliders.add(new JSlider(JSlider.HORIZONTAL, MAX_HUE_MIN, MAX_HUE_MAX, maxHueInit));
	    sliders.add(new JSlider(JSlider.HORIZONTAL, MIN_SAT_MIN, MIN_SAT_MAX, minSatInit));
	    sliders.add(new JSlider(JSlider.HORIZONTAL, MAX_SAT_MIN, MAX_SAT_MAX, maxSatInit));
	    sliders.add(new JSlider(JSlider.HORIZONTAL, MIN_VAL_MIN, MIN_VAL_MAX, minValInit));
	    sliders.add(new JSlider(JSlider.HORIZONTAL, MAX_VAL_MIN, MAX_VAL_MAX, maxValInit));
	    
	    Font sliderFont = null;
	    if(SIZE.equals("big"))
	    	sliderFont = new Font("Times New Roman", Font.BOLD, 45);
	    else
	    	sliderFont = new Font("Times New Roman", Font.BOLD, 15);
	    for(JSlider e: sliders){
	    	e.setForeground(foreColor);
	    	e.setBackground(backColor);
	    	e.setMajorTickSpacing(50);
	        e.setMinorTickSpacing(50);
	        e.setPaintTicks(true);
	        e.setPaintLabels(true);
	        e.setBorder(
	                BorderFactory.createEmptyBorder(0,0,10,0));
	        e.setFont(sliderFont);
	    }
	    
	    /*
	     * Creates the text fields
	     */
	    ArrayList<JTextField> textFields = new ArrayList<JTextField>();
	    textFields.add(new JTextField());
	    textFields.add(new JTextField());
	    textFields.add(new JTextField());
	    textFields.add(new JTextField());
	    textFields.add(new JTextField());
	    textFields.add(new JTextField());
	    
	    Font fieldFont = null;
	    if(SIZE.equals("big"))
	    	fieldFont = new Font("Times New Roman", Font.BOLD, 60);
	    else
	    	fieldFont = new Font("Times New Roman", Font.BOLD, 20);
	    int fieldCount = 0;
	    for(JTextField e: textFields){
	    	e.setFont(fieldFont);
	    	e.setHorizontalAlignment(JTextField.CENTER);
	    	e.setText(""+sliders.get(fieldCount).getValue());
	    	e.setForeground(foreColor);
	    	e.setBackground(backColor);
	    	fieldCount++;
	    }
	    
	    /* 
	     * Text field value is updates to slider value 
	     */
	    sliders.get(0).addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent ce) {
                textFields.get(0).setText(""+((JSlider) ce.getSource()).getValue());
                saveData(textFields);
            }
	    });
	    sliders.get(1).addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent ce) {
                textFields.get(1).setText(""+((JSlider) ce.getSource()).getValue());
                saveData(textFields);
            }
	    });
	    sliders.get(2).addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent ce) {
                textFields.get(2).setText(""+((JSlider) ce.getSource()).getValue());
                saveData(textFields);
            }
	    });
	    sliders.get(3).addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent ce) {
                textFields.get(3).setText(""+((JSlider) ce.getSource()).getValue());
                saveData(textFields);
            }
	    });
	    sliders.get(4).addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent ce) {
                textFields.get(4).setText(""+((JSlider) ce.getSource()).getValue());
                saveData(textFields);
            }
	    });
	    sliders.get(5).addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent ce) {
                textFields.get(5).setText(""+((JSlider) ce.getSource()).getValue());
                saveData(textFields);
            }
	    });
	    
	    /*
	     * Slider value is updated to text field value
	     */
	    textFields.get(0).addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                String typed = textFields.get(0).getText();
                //sliders.get(0).setValue(0);
                if(!typed.matches("\\d+") || typed.length() > 3) {
                    return;
                }
                int value = Integer.parseInt(typed);
                sliders.get(0).setValue(value);
                saveData(textFields);
            }
        });
	    textFields.get(1).addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                String typed = textFields.get(1).getText();
                //sliders.get(0).setValue(0);
                if(!typed.matches("\\d+") || typed.length() > 3) {
                    return;
                }
                int value = Integer.parseInt(typed);
                sliders.get(1).setValue(value);
                saveData(textFields);
            }
        });
	    textFields.get(2).addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                String typed = textFields.get(2).getText();
                //sliders.get(0).setValue(0);
                if(!typed.matches("\\d+") || typed.length() > 3) {
                    return;
                }
                int value = Integer.parseInt(typed);
                sliders.get(2).setValue(value);
                saveData(textFields);
            }
        });
	    textFields.get(3).addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                String typed = textFields.get(3).getText();
                //sliders.get(0).setValue(0);
                if(!typed.matches("\\d+") || typed.length() > 3) {
                    return;
                }
                int value = Integer.parseInt(typed);
                sliders.get(3).setValue(value);
                saveData(textFields);
            }
        });
	    textFields.get(4).addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                String typed = textFields.get(4).getText();
                //sliders.get(0).setValue(0);
                if(!typed.matches("\\d+") || typed.length() > 3) {
                    return;
                }
                int value = Integer.parseInt(typed);
                sliders.get(4).setValue(value);
                saveData(textFields);
            }
        });
	    textFields.get(5).addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                String typed = textFields.get(5).getText();
                //sliders.get(0).setValue(0);
                if(!typed.matches("\\d+") || typed.length() > 3) {
                    return;
                }
                int value = Integer.parseInt(typed);
                sliders.get(5).setValue(value);
                saveData(textFields);
            }
        });
	    
	    JPanel usbPanel = new JPanel();
	    GridLayout usbPanelLayout = new GridLayout(1, 2);
	    usbPanel.setLayout(usbPanelLayout);
	    usbPanel.setForeground(Color.WHITE);
	    usbPanel.setBackground(Color.BLACK);
	    JButton usbButton = new JButton("USB Load");
		usbButton.setForeground(Color.BLACK);
		usbButton.setBackground(Color.WHITE);
		JButton usbSaveButton = new JButton("USB Save");
		usbSaveButton.setForeground(Color.BLACK);
		usbSaveButton.setBackground(Color.WHITE);
		
		usbButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e){
				//Opens file
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setCurrentDirectory(new File (System.getProperty("user.home") + "/Desktop"));
					if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
						File file = fileChooser.getSelectedFile();
						  try {
							Scanner in = new Scanner(file);
							sliders.get(0).setValue(in.nextInt());
							sliders.get(1).setValue(in.nextInt());
							sliders.get(2).setValue(in.nextInt());
							sliders.get(3).setValue(in.nextInt());
							sliders.get(4).setValue(in.nextInt());
							sliders.get(5).setValue(in.nextInt());
							in.close();
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		);
		
		usbSaveButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e){
				//Saves File
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setCurrentDirectory(new File (System.getProperty("user.home") + "/Desktop"));
					if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
					  File file = fileChooser.getSelectedFile();
					  try {
						PrintWriter writer = new PrintWriter(file);
						writer.write(textFields.get(0).getText()+"\r\n");
				 	    writer.write(textFields.get(1).getText()+"\r\n");
				 	    writer.write(textFields.get(2).getText()+"\r\n");
				 	    writer.write(textFields.get(3).getText()+"\r\n");
				 	    writer.write(textFields.get(4).getText()+"\r\n");
				 	    writer.write(textFields.get(5).getText()+"\r\n");
						writer.close();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					}
				}
			}
		);
		
		if(SIZE.equals("big")){
	    	usbPanel.setPreferredSize(new Dimension(0,100));
	    	usbButton.setPreferredSize(new Dimension(500,70));
	    	usbSaveButton.setPreferredSize(new Dimension(500,70));
	    	usbButton.setFont(new Font("Times New Roman", Font.BOLD, 50));
	    	usbSaveButton.setFont(new Font("Times New Roman", Font.BOLD, 50));
	    }
	    else{
	    	usbPanel.setPreferredSize(new Dimension(0,34));
	    	usbButton.setPreferredSize(new Dimension(167,24));
	    	usbSaveButton.setPreferredSize(new Dimension(167,24));
	    	usbButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
	    	usbSaveButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
	    }
		usbPanel.add(usbButton);
		usbPanel.add(usbSaveButton);
	    
	    /*
	     * Adds components to the panel and frame
	     */
		sliderPanel.add(usbPanel);
	    sliderPanel.add(sliderLabels.get(0));
	    sliderPanel.add(sliders.get(0));
	    sliderPanel.add(textFields.get(0));
	    sliderPanel.add(sliderLabels.get(1));
	    sliderPanel.add(sliders.get(1));
	    sliderPanel.add(textFields.get(1));
	    sliderPanel.add(sliderLabels.get(2));
	    sliderPanel.add(sliders.get(2));
	    sliderPanel.add(textFields.get(2));
	    sliderPanel.add(sliderLabels.get(3));
	    sliderPanel.add(sliders.get(3));
	    sliderPanel.add(textFields.get(3));
	    sliderPanel.add(sliderLabels.get(4));
	    sliderPanel.add(sliders.get(4));
	    sliderPanel.add(textFields.get(4));
	    sliderPanel.add(sliderLabels.get(5));
	    sliderPanel.add(sliders.get(5));
	    sliderPanel.add(textFields.get(5));
	    
	   //////////////////////////!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!/////////////////////////////
	   //////////////////////////!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!/////////////////////////////
	    
	    /*
	     * Labels for slider
	     */
	    ArrayList<JLabel> sliderLabels2 = new ArrayList<JLabel>();
	    sliderLabels2.add(new JLabel("Hue Min", JLabel.CENTER));
	    sliderLabels2.add(new JLabel("Hue Max", JLabel.CENTER));
	    sliderLabels2.add(new JLabel("Saturation Min", JLabel.CENTER));
	    sliderLabels2.add(new JLabel("Saturation Max", JLabel.CENTER));
	    sliderLabels2.add(new JLabel("Value Min", JLabel.CENTER));
	    sliderLabels2.add(new JLabel("Value Max", JLabel.CENTER));
	    
	    Font labelFont2 = null;
	    if(SIZE.equals("big"))
	    	labelFont2 = new Font("Times New Roman", Font.BOLD, 60);
	    else
	    	labelFont2 = new Font("Times New Roman", Font.BOLD, 20);
	    for(JLabel e: sliderLabels2){
	    	e.setFont(labelFont2);
	    	e.setForeground(foreColor);
	    	e.setAlignmentX(Component.CENTER_ALIGNMENT);
	    }
	    
	    /*
	     * Creates the sliders
	     */
	    ArrayList<JSlider> sliders2 = new ArrayList<JSlider>();
	    sliders2.add(new JSlider(JSlider.HORIZONTAL, MIN_HUE_MIN2, MIN_HUE_MAX2, minHueInit2));
	    sliders2.add(new JSlider(JSlider.HORIZONTAL, MAX_HUE_MIN2, MAX_HUE_MAX2, maxHueInit2));
	    sliders2.add(new JSlider(JSlider.HORIZONTAL, MIN_SAT_MIN2, MIN_SAT_MAX2, minSatInit2));
	    sliders2.add(new JSlider(JSlider.HORIZONTAL, MAX_SAT_MIN2, MAX_SAT_MAX2, maxSatInit2));
	    sliders2.add(new JSlider(JSlider.HORIZONTAL, MIN_VAL_MIN2, MIN_VAL_MAX2, minValInit2));
	    sliders2.add(new JSlider(JSlider.HORIZONTAL, MAX_VAL_MIN2, MAX_VAL_MAX2, maxValInit2));
	    
	    Font sliderFont2 = null;
	    if(SIZE.equals("big"))
	    	sliderFont2 = new Font("Times New Roman", Font.BOLD, 45);
	    else
	    	sliderFont2 = new Font("Times New Roman", Font.BOLD, 15);
	    for(JSlider e: sliders2){
	    	e.setForeground(foreColor);
	    	e.setBackground(backColor);
	    	e.setMajorTickSpacing(50);
	        e.setMinorTickSpacing(50);
	        e.setPaintTicks(true);
	        e.setPaintLabels(true);
	        e.setBorder(
	                BorderFactory.createEmptyBorder(0,0,10,0));
	        e.setFont(sliderFont2);
	    }
	    
	    /*
	     * Creates the text fields
	     */
	    ArrayList<JTextField> textFields2 = new ArrayList<JTextField>();
	    textFields2.add(new JTextField());
	    textFields2.add(new JTextField());
	    textFields2.add(new JTextField());
	    textFields2.add(new JTextField());
	    textFields2.add(new JTextField());
	    textFields2.add(new JTextField());
	    
	    Font fieldFont2 = null;
	    if(SIZE.equals("big"))
	    	fieldFont2 = new Font("Times New Roman", Font.BOLD, 60);
	    else
	    	fieldFont2 = new Font("Times New Roman", Font.BOLD, 20);
	    int fieldCount2 = 0;
	    for(JTextField e: textFields2){
	    	e.setFont(fieldFont2);
	    	e.setHorizontalAlignment(JTextField.CENTER);
	    	e.setText(""+sliders2.get(fieldCount2).getValue());
	    	e.setForeground(foreColor);
	    	e.setBackground(backColor);
	    	fieldCount2++;
	    }
	    
	    /* 
	     * Text field value is updates to slider value 
	     */
	    sliders2.get(0).addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent ce) {
                textFields2.get(0).setText(""+((JSlider) ce.getSource()).getValue());
                saveData2(textFields2);
            }
	    });
	    sliders2.get(1).addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent ce) {
                textFields2.get(1).setText(""+((JSlider) ce.getSource()).getValue());
                saveData2(textFields2);
            }
	    });
	    sliders2.get(2).addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent ce) {
                textFields2.get(2).setText(""+((JSlider) ce.getSource()).getValue());
                saveData2(textFields2);
            }
	    });
	    sliders2.get(3).addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent ce) {
                textFields2.get(3).setText(""+((JSlider) ce.getSource()).getValue());
                saveData2(textFields2);
            }
	    });
	    sliders2.get(4).addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent ce) {
                textFields2.get(4).setText(""+((JSlider) ce.getSource()).getValue());
                saveData2(textFields2);
            }
	    });
	    sliders2.get(5).addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent ce) {
                textFields2.get(5).setText(""+((JSlider) ce.getSource()).getValue());
                saveData2(textFields2);
            }
	    });
	    
	    /*
	     * Slider value is updated to text field value
	     */
	    textFields2.get(0).addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                String typed = textFields2.get(0).getText();
                //sliders.get(0).setValue(0);
                if(!typed.matches("\\d+") || typed.length() > 3) {
                    return;
                }
                int value = Integer.parseInt(typed);
                sliders2.get(0).setValue(value);
                saveData2(textFields2);
            }
        });
	    textFields2.get(1).addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                String typed = textFields2.get(1).getText();
                //sliders2.get(0).setValue(0);
                if(!typed.matches("\\d+") || typed.length() > 3) {
                    return;
                }
                int value = Integer.parseInt(typed);
                sliders2.get(1).setValue(value);
                saveData2(textFields2);
            }
        });
	    textFields2.get(2).addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                String typed = textFields2.get(2).getText();
                //sliders2.get(0).setValue(0);
                if(!typed.matches("\\d+") || typed.length() > 3) {
                    return;
                }
                int value = Integer.parseInt(typed);
                sliders2.get(2).setValue(value);
                saveData2(textFields2);
            }
        });
	    textFields2.get(3).addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                String typed = textFields2.get(3).getText();
                //sliders2.get(0).setValue(0);
                if(!typed.matches("\\d+") || typed.length() > 3) {
                    return;
                }
                int value = Integer.parseInt(typed);
                sliders2.get(3).setValue(value);
                saveData2(textFields2);
            }
        });
	    textFields2.get(4).addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                String typed = textFields2.get(4).getText();
                //sliders2.get(0).setValue(0);
                if(!typed.matches("\\d+") || typed.length() > 3) {
                    return;
                }
                int value = Integer.parseInt(typed);
                sliders2.get(4).setValue(value);
                saveData2(textFields2);
            }
        });
	    textFields2.get(5).addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                String typed = textFields2.get(5).getText();
                //sliders2.get(0).setValue(0);
                if(!typed.matches("\\d+") || typed.length() > 3) {
                    return;
                }
                int value = Integer.parseInt(typed);
                sliders2.get(5).setValue(value);
                saveData2(textFields2);
            }
        });
	    
	    JPanel piPanel = new JPanel();
	    GridLayout piPanelLayout = new GridLayout(1, 2);
	    piPanel.setLayout(piPanelLayout);
	    piPanel.setForeground(Color.WHITE);
	    piPanel.setBackground(Color.BLACK);
	    JButton piButton = new JButton("Pi Load");
		piButton.setForeground(Color.BLACK);
		piButton.setBackground(Color.WHITE);
		JButton piSaveButton = new JButton("Pi Save");
		piSaveButton.setForeground(Color.BLACK);
		piSaveButton.setBackground(Color.WHITE);
		
		piButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
			//Opens file
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File (System.getProperty("user.home") + "/Desktop"));
				if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					  try {
						Scanner in = new Scanner(file);
						sliders2.get(0).setValue(in.nextInt());
						sliders2.get(1).setValue(in.nextInt());
						sliders2.get(2).setValue(in.nextInt());
						sliders2.get(3).setValue(in.nextInt());
						sliders2.get(4).setValue(in.nextInt());
						sliders2.get(5).setValue(in.nextInt());
						in.close();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	);
	
	piSaveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
			//Saves File
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File (System.getProperty("user.home") + "/Desktop"));
				if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
				  File file = fileChooser.getSelectedFile();
				  try {
					PrintWriter writer = new PrintWriter(file);
					writer.write(textFields2.get(0).getText()+"\r\n");
			 	    writer.write(textFields2.get(1).getText()+"\r\n");
			 	    writer.write(textFields2.get(2).getText()+"\r\n");
			 	    writer.write(textFields2.get(3).getText()+"\r\n");
			 	    writer.write(textFields2.get(4).getText()+"\r\n");
			 	    writer.write(textFields2.get(5).getText()+"\r\n");
					writer.close();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				}
			}
		}
	);
		
		if(SIZE.equals("big")){
	    	piPanel.setPreferredSize(new Dimension(0,100));
	    	piButton.setPreferredSize(new Dimension(500,70));
	    	piSaveButton.setPreferredSize(new Dimension(500,70));
	    	piButton.setFont(new Font("Times New Roman", Font.BOLD, 50));
	    	piSaveButton.setFont(new Font("Times New Roman", Font.BOLD, 50));
	    }
	    else{
	    	piPanel.setPreferredSize(new Dimension(0,34));
	    	piButton.setPreferredSize(new Dimension(167,24));
	    	piSaveButton.setPreferredSize(new Dimension(167,24));
	    	piButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
	    	piSaveButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
	    }
		piPanel.add(piButton);
		piPanel.add(piSaveButton);
	    
	    /*
	     * Adds components to the panel and frame
	     */
		sliderPanel2.add(piPanel);
	    sliderPanel2.add(sliderLabels2.get(0));
	    sliderPanel2.add(sliders2.get(0));
	    sliderPanel2.add(textFields2.get(0));
	    sliderPanel2.add(sliderLabels2.get(1));
	    sliderPanel2.add(sliders2.get(1));
	    sliderPanel2.add(textFields2.get(1));
	    sliderPanel2.add(sliderLabels2.get(2));
	    sliderPanel2.add(sliders2.get(2));
	    sliderPanel2.add(textFields2.get(2));
	    sliderPanel2.add(sliderLabels2.get(3));
	    sliderPanel2.add(sliders2.get(3));
	    sliderPanel2.add(textFields2.get(3));
	    sliderPanel2.add(sliderLabels2.get(4));
	    sliderPanel2.add(sliders2.get(4));
	    sliderPanel2.add(textFields2.get(4));
	    sliderPanel2.add(sliderLabels2.get(5));
	    sliderPanel2.add(sliders2.get(5));
	    sliderPanel2.add(textFields2.get(5));
	    
	    //////////////////////////!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!/////////////////////////////
	    //////////////////////////!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!/////////////////////////////
	    
	    saveData(textFields);
	    saveData2(textFields2);
	    JScrollPane usbScroll = new JScrollPane(sliderPanel);
	    
	    frame.add(usbScroll);
	    frame.add(new JScrollPane(sliderPanel2));
	    
	    //frame.add(sliderPanel);
	    //frame.add(sliderPanel2);
	    
	    frame.setVisible(true);
	    
	}
	
	private void saveData(ArrayList<JTextField> textFields){
		//System.out.println("Values Updated");
 	    PrintWriter writer = null;
			try {
				writer = new PrintWriter(new File(System.getProperty("user.home") + "/Desktop","USB_SV.txt"));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
 	    writer.write(textFields.get(0).getText()+"\r\n");
 	    writer.write(textFields.get(1).getText()+"\r\n");
 	    writer.write(textFields.get(2).getText()+"\r\n");
 	    writer.write(textFields.get(3).getText()+"\r\n");
 	    writer.write(textFields.get(4).getText()+"\r\n");
 	    writer.write(textFields.get(5).getText()+"\r\n");
 	    writer.close();
	}
	
	private void saveData2(ArrayList<JTextField> textFields){
		//System.out.println("Values Updated");
 	    PrintWriter writer = null;
			try {
				writer = new PrintWriter(new File(System.getProperty("user.home") + "/Desktop","PI_CAM_SV.txt"));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
 	    writer.write(textFields.get(0).getText()+"\r\n");
 	    writer.write(textFields.get(1).getText()+"\r\n");
 	    writer.write(textFields.get(2).getText()+"\r\n");
 	    writer.write(textFields.get(3).getText()+"\r\n");
 	    writer.write(textFields.get(4).getText()+"\r\n");
 	    writer.write(textFields.get(5).getText()+"\r\n");
 	    writer.close();
	}
	
}




















