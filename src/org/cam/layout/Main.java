package org.cam.layout;

import org.cam.layout.CheckProcess;;


public class Main {
		public static void main(String[] args) {
		
			System.out.println("Sono nel main");
			
			//SET DEFAULT DISPLAY X SYSTEM
			CheckProcess checkProccess = new CheckProcess();
			checkProccess.executeCommand("export DISPLAY=:0.0");
			
			Finestra finestra = new Finestra();
			finestra.mainWindow();
			System.out.println("Ho creato la finestra");
			
	}

}
