package org.cam.layout;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

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
         
         close.addActionListener(new ActionListener() {
       	  
             public void actionPerformed(ActionEvent event) {
            	 System.exit(0);
                 }
    	     });


         JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
         
         start.setPreferredSize(new Dimension(160, 80));
         stop.setPreferredSize(new Dimension(160, 80)); 
         close.setPreferredSize(new Dimension(160, 80)); 
         
         
         panel.add(start);
         panel.add(stop);
         panel.add(close);
         panel.setSize(480, 320);
         mainFrame.add(panel); 
         

         mainFrame.pack();
         mainFrame.setSize(480, 320);
		 mainFrame.setVisible(true);
		 mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	 }
	 
	 
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
	 
	 
	 
	
	 

}
