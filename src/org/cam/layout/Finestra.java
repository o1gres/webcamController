package org.cam.layout;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinPwmOutput;
import com.pi4j.io.gpio.RaspiPin;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPin;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.GpioPinPwm;
import com.pi4j.io.gpio.GpioPinPwmOutput;
import com.pi4j.io.gpio.PinDirection;
import com.pi4j.io.gpio.PinMode;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.trigger.GpioCallbackTrigger;
import com.pi4j.io.gpio.trigger.GpioPulseStateTrigger;
import com.pi4j.io.gpio.trigger.GpioSetStateTrigger;
import com.pi4j.io.gpio.trigger.GpioSyncStateTrigger;
import com.pi4j.wiringpi.Gpio;
import com.pi4j.wiringpi.SoftPwm;
import com.pi4j.io.gpio.event.GpioPinListener;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.pi4j.io.gpio.event.PinEventType;


public class Finestra extends JFrame{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public void mainWindow (){
		 
		 final CheckProcess checkProcess = new CheckProcess();
//		 final JFrame frame = new JFrame("Display Mode");
		 
		 JFrame mainFrame = new JFrame();
		
		
		 
		 final JButton start = new JButton("Start");
		 final JButton stop = new JButton("Stop");
		 final JButton close = new JButton("Close");
		 final JButton offDisplay = new JButton("Display");
		 
//		 mainFrame.add(start);
//         mainFrame.add(stop);  
//         mainFrame.add(close); 
         
		 
		 
		 
		 if (validProcess().equalsIgnoreCase("Start")){
			 stop.setEnabled(false);
    		 start.setEnabled(true); 
		 } else {
			 stop.setEnabled(true);
    		 start.setEnabled(false);  
		 }
		 
		 
		 
		 //GESTIONE PULSANTE START
	     start.addActionListener(new ActionListener() {
	    	  
	     public void actionPerformed(ActionEvent event) {
	    	 String[] command = {"/bin/bash","-c","echo \"password\"| sudo -S service motion start"};
	    	 //String array[]= {"sudo", "-S", "service apache2 start"};
	    	 try {
				checkProcess.sudoCommand(command);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	 //checkProcess.executeCommand(command);
	    	 start.setEnabled(false);
    		 stop.setEnabled(true);	   
	         }
	     });
	       
         
	     
	     //GESTIONE PULSANTE STOP
         stop.addActionListener(new ActionListener() {
    	  
         public void actionPerformed(ActionEvent event) {
        	 String[] command = {"/bin/bash","-c","echo \"password\"| sudo -S service motion stop"};
        	 //String array[]= {"sudo", "-S", "service apache2 stop"};
	    	 try {
				checkProcess.sudoCommand(command);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	 //checkProcess.executeCommand(command);
    		 setTitle(validProcess());
    		 stop.setEnabled(false);
    		 start.setEnabled(true);  
             }
	     });
         
         
         
	     //GESTIONE PULSANTE CLOSE
         close.addActionListener(new ActionListener() {
       	  
             public void actionPerformed(ActionEvent event) {
            	 System.exit(0);
                 }
    	     });
         
         
         
         //GESTIONE PULSANTE OFFDISPLAY
         offDisplay.addActionListener(new ActionListener() {
       	  
             public void actionPerformed(ActionEvent event) {
            	 setPinToLow();
                 }
    	     });


         
         //DISPOSIZIONE GRAFICA PULSANTI
         JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
         
         start.setPreferredSize(new Dimension(160, 80));
         stop.setPreferredSize(new Dimension(160, 80)); 
         close.setPreferredSize(new Dimension(160, 80));
         offDisplay.setPreferredSize(new Dimension(160, 80));
         
         panel.setLayout(new BorderLayout());
        
        
         panel.add(start, BorderLayout.WEST);
         panel.add(stop, BorderLayout.LINE_END);
         panel.add(close ,BorderLayout.PAGE_END);
         panel.add(offDisplay ,BorderLayout.PAGE_START);
         panel.setSize(480, 320);
         mainFrame.add(panel); 
         

         mainFrame.pack();
         mainFrame.setSize(480, 320);
		 mainFrame.setVisible(true);
		 mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	 }
	 
	 
	
	//CONTROLLA SE MOTION E' AVVIATO OPPURE NO E SETTA OPPORTUNATE LO STATO DEI PULSANTI
	 public String validProcess(){
		 
		 final CheckProcess checkProcess = new CheckProcess();
		 
		 String idProcesso = "";
		 try {
			 idProcesso = checkProcess.executeCommand("pgrep apache2");
		 } catch (Exception e){
			e.printStackTrace(); 
		 	}
		 
		 String buttonText = "";
		 
		 if (idProcesso.isEmpty()){
			 buttonText = "Start";
		 } else {
			 buttonText = "Stop";
		  	}
		 
		 return buttonText;
	 }
	 
	 
	 
	 
	 static private boolean setPinToLow()
	    {
	    	 final GpioController gpio = GpioFactory.getInstance();
	                  
	    	 GpioPinPwmOutput pwmPin = gpio.provisionPwmOutputPin(RaspiPin.GPIO_01);

	    	 //pwmPin.setPwm(3);

	    	 System.out.println("Pin status: "+pwmPin.getPwm());
	    	 
	    	 System.out.println("Set pin to low");
	    	 
	    	 return true;

	    }
	 
	 
	 
	
	 

}
