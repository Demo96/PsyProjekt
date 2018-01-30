package projekt;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class RozpoczeciePlaceniaEvent extends BasicSimEvent<Kasa, Object>{
	public Kasa kasa;
	public int numerkasy;

	public RozpoczeciePlaceniaEvent(Kasa entity,int nr) throws SimControlException {
		super(entity);
		kasa=entity;
		numerkasy=nr;
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
		if (kasa.ListaKlientow.size() > 0)
        {
            // ustaw kase jako zajeta, przenies pierwszego klienta z kolejki do kasy jako aktualnego klienta 
            kasa.wolnekasy[numerkasy]=false;
            Klient k = kasa.ListaKlientow.removeFirst();
            kasa.aktualnyklient[numerkasy] = k;

         // oblicz czas placenia i zaplanuj jego zakonczenie po uplywie tego czasu
            double czasObslugi = Ustawienia.randCzasPlacenia();
            System.out.println("Rozpoczecie placenia w kasie nr "+(numerkasy+1)+ " klienta(" + k.getID() +") czas: "+simTime());
            kasa.zakonczeniePlacenia = new ZakonczeniePlaceniaEvent(kasa, czasObslugi,numerkasy);
        }
		
	}
	

}
