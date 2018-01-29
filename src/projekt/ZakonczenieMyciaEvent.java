package projekt;

import java.util.logging.Logger;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class ZakonczenieMyciaEvent extends BasicSimEvent<Myjnia, Object> {

	Myjnia myjnia;
	public ZakonczenieMyciaEvent(projekt.Myjnia entity, double delay) throws SimControlException {
		super(entity, delay);
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

	        // Odblokuj gniazdo
	        myjnia.wolny=true;
	        System.out.println("Koniec mycia w myjni" + " klient(" + myjnia.aktualnyklient.getID()+") czas: "+simTime());

	        double czasObs = simTime() - myjnia.aktualnyklient.startObs;
	        myjnia.stacja.czasMycia.setValue(czasObs);

	        // Zaplanuj dalsza obs³uge
	        if (myjnia.listaklientow.size() > 0)
	        {
	            myjnia.rozpoczecieMycia = new RozpoczecieMyciaEvent(myjnia);
	        }
	}

}
