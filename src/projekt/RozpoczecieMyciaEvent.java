package projekt;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class RozpoczecieMyciaEvent extends BasicSimEvent<Myjnia, Object> {

	Myjnia myjnia;

	public RozpoczecieMyciaEvent(projekt.Myjnia entity, double delay) throws SimControlException {
		super(entity, delay);
		myjnia = entity;
	}

	public RozpoczecieMyciaEvent(projekt.Myjnia entity) throws SimControlException {
		super(entity);
		myjnia = entity;
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
		if (myjnia.listaklientow.size() > 0) {
			// ustawia myjnie zajeta,przerzuac pierwsza osobe z kolejki jako aktualnego klienta
			// korzystajaca z myjni
			myjnia.wolny = false;
			Klient k = myjnia.usun();
			myjnia.aktualnyklient = k;
			myjnia.aktualnyklient.startObs = simTime();
			// oblicz czas mycia i zaplanuj jego zakonczenie po uplywie tego czasu
			double czasObslugi = Ustawienia.randCzasMycia();
			System.out.println("Rozpoczecie mycia w myjni" + " klient(" + k.getID() + ") czas: " + simTime());
			myjnia.zakonczenieMycia = new ZakonczenieMyciaEvent(myjnia, czasObslugi);
		}

	}
}
