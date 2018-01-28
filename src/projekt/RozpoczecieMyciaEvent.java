package projekt;

import java.util.logging.Logger;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class RozpoczecieMyciaEvent extends BasicSimEvent<Myjnia, Object> {

	Myjnia myjnia;
	public RozpoczecieMyciaEvent(projekt.Myjnia entity, double delay) throws SimControlException {
		super(entity, delay);
		myjnia=entity;
	}
	public RozpoczecieMyciaEvent(projekt.Myjnia entity) throws SimControlException {
		super(entity);
		myjnia=entity;
	}
	@Override
	public Object getEventParams() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	protected void onInterruption() throws SimControlException {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void onTermination() throws SimControlException {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void stateChange() throws SimControlException {
		 final Logger LOGGER=  Logger.getLogger(Main.class .getName());

	        if (myjnia.listaklientow.size()> 0)
	        {
	            // Zablokuj gniazdo
	            myjnia.wolny=false;
	            // Pobierz zgloszenie
	            Klient k = myjnia.usun();
	            myjnia.aktualnyklient = k;
		        myjnia.aktualnyklient.startObs = simTime();
	            // Wygeneruj czas obs³ugi
	            double czasObslugi = Ustawienia.randCzasMycia();
	            LOGGER.info("Rozpoczecie mycia w myjni"  + " klient(" + k.getID() +") czas: "+simTime());
	            // Zaplanuj koniec obs³ugi
	            myjnia.zakonczenieMycia = new ZakonczenieMyciaEvent(myjnia, czasObslugi);
	        }
		
	}
}
