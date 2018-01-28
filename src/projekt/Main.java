package projekt;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import dissimlab.simcore.SimControlEvent;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimManager;
import dissimlab.simcore.SimParameters.SimControlStatus;


public class Main {
	public static void main(String[] args) {
		final Logger LOGGER=  Logger.getLogger(Main.class .getName());
            try{
                FileHandler fh;
                int limit = 1000000;
                fh = new FileHandler("C:/Users/Jakub/Desktop/abc.log",limit,1,true);
                LOGGER.addHandler(fh);
                SimpleFormatter formatter = new SimpleFormatter();
                fh.setFormatter(formatter);
            }catch (IOException e){
                e.printStackTrace();
            }
          //logger.setLevel(Level.ALL);
            SimManager model = SimManager.getInstance();

            // Utworzenie otoczenia
            try {
				Stacja generatorZgl = new Stacja();
			} catch (SimControlException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            // Dwa sposoby zaplanowanego końca symulacji
            //model.setEndSimTime(10000);
            // lub
            try {
				SimControlEvent stopEvent = new SimControlEvent(1000.0, SimControlStatus.STOPSIMULATION);
			} catch (SimControlException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            // Uruchomienie symulacji za pośrednictwem metody "start"
            try {
				model.startSimulation();
			} catch (SimControlException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            //generatorZgl.pokazWyniki();
	}
}
