package org.cam.layout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CheckProcess {
	
	 public String executeCommand(String command) {

			StringBuffer output = new StringBuffer();

			Process p;
			try {
				p = Runtime.getRuntime().exec(command);
				p.waitFor();
				BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

	                        String line = "";			
				while ((line = reader.readLine())!= null) {
					output.append(line + "\n");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			return output.toString();

		}
	 
	 public void sudoCommand(String[] command) throws IOException{
		 
		 //String[] cmd = {"/bin/bash","-c","echo password| sudo -S ls"};
		    Process pb = Runtime.getRuntime().exec(command);

		    String line;
		    BufferedReader input = new BufferedReader(new InputStreamReader(pb.getInputStream()));
		    while ((line = input.readLine()) != null) {
		        System.out.println(line);
		    }
		    input.close();
		
	 }

}
