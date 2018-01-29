package projekt;

import java.util.logging.Logger;

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
            // Zablokuj gniazdo
            kasa.wolnekasy[numerkasy]=false;
            // Pobierz zg³oszenie
            Klient k = kasa.ListaKlientow.removeFirst();
            kasa.aktualnyklient[numerkasy] = k;

            // Wygeneruj czas obs³ugi
            double czasObslugi = Ustawienia.randCzasPlacenia();

            System.out.println("Rozpoczecie placenia w kasie nr "+(numerkasy+1)+ " klienta(" + k.getID() +") czas: "+simTime());
            // Zaplanuj koniec obs³ugi
            kasa.zakonczeniePlacenia = new ZakonczeniePlaceniaEvent(kasa, czasObslugi,numerkasy);
        }
		
	}
	

}
