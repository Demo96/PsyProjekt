package projekt;

import java.util.logging.Logger;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class RozpoczeciePlaceniaEvent extends BasicSimEvent<Kasa, Object>{
	public Kasa kasa;
	public RozpoczeciePlaceniaEvent(Kasa entity, double delay) throws SimControlException {
		super(entity, delay);
		kasa=entity;
	}
	public RozpoczeciePlaceniaEvent(Kasa entity) throws SimControlException {
		super(entity);
		kasa=entity;
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
		if (kasa.ListaKlientow.size() > 0)
        {
            // Zablokuj gniazdo
            kasa.setwolny(false);
            // Pobierz zg³oszenie
            Klient k = kasa.ListaKlientow.removeFirst();
            kasa.aktualnyklient = k;

            // Wygeneruj czas obs³ugi
            double czasObslugi = Ustawienia.randCzasPlacenia();

            LOGGER.info("Rozpoczecie placenia w kasie nr " + " klienta(" + k.getID() +") czas: "+simTime());
            // Zaplanuj koniec obs³ugi
            kasa.zakonczeniePlacenia = new ZakonczeniePlaceniaEvent(kasa, czasObslugi);
        }
		
	}
	

}
