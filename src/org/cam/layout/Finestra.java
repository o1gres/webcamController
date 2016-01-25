package org.cam.layout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.swing.*;

public class Finestra extends JFrame{
	
	 public void mainWindow (){
		 
		 final CheckProcess checkProcess = new CheckProcess();
		 
		 
		 JFrame mainFrame = new JFrame();
		 mainFrame.setSize(480, 320);
		 mainFrame.setVisible(true);
		 mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		 
		 final JButton start = new JButton("Start");
		 final JButton stop = new JButton("Stop");
		 
		 mainFrame.add(start);
         mainFrame.add(stop);  
         
		 start.setBounds(50, 60, 80, 30);
		 stop.setBounds(150, 60, 80, 30);
		 
		 
		 if (validProcess().equalsIgnoreCase("Start")){
			 stop.setEnabled(false);
    		 start.setEnabled(true); 
		 } else {
			 stop.setEnabled(true);
    		 start.setEnabled(false);  
		 }
		 
	     start.addActionListener(new ActionListener() {
	    	  
	     public void actionPerformed(ActionEvent event) {
	    	 checkProcess.executeCommand("service mysql start");
	    	 start.setEnabled(false);
    		 stop.setEnabled(true);	   
	         }
	     });
	       
         
         stop.addActionListener(new ActionListener() {
    	  
         public void actionPerformed(ActionEvent event) {
		     checkProcess.executeCommand("service mysql stop");
    		 setTitle(validProcess());
    		 stop.setEnabled(false);
    		 start.setEnabled(true);  
             }
	     });

	     mainFrame.add(stop);

	    setSize(300, 200);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	 }
	 
	 
	 public String validProcess(){
		 
		 final CheckProcess checkProcess = new CheckProcess();
		 
		 String idProcesso = "";
		 try {
			 idProcesso = checkProcess.executeCommand("pgrep mysql");
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
