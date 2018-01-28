package projekt;

import java.util.logging.Logger;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class ZakonczeniePlaceniaEvent extends BasicSimEvent<Kasa, Object> {
	public Kasa kasa;
	public ZakonczeniePlaceniaEvent(projekt.Kasa entity, double delay) throws SimControlException {
		super(entity, delay);
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

	        // Odblokuj gniazdo
	        kasa.setwolny(true);
	        kasa.aktualnyklient.aktualneStanowisko.wolny=true;
	        if (kasa.aktualnyklient.aktualneStanowisko.ListaKlientow.size() > 0)
	               {
	        	kasa.aktualnyklient.aktualneStanowisko.rozpoczecie = new RozpoczecieTankowaniaEvent(kasa.aktualnyklient.aktualneStanowisko);
	               }
	        LOGGER.info("Koniec placenia w kasie nr"  + " klient(" + kasa.aktualnyklient.getID()+") czas:");

	        // Zaplanuj dalsza obs³uge
	        if (kasa.ListaKlientow.size() > 0)
	        {
	            kasa.rozpoczeciePlacenia = new RozpoczeciePlaceniaEvent(kasa);
	        }
	        //a moze do myjni  tez
	        if(kasa.aktualnyklient.getMyjnia())
	        {
	        	kasa.stacja.myjnia.add(kasa.aktualnyklient);
	            if (kasa.stacja.myjnia.listaklientow.size()==1 && kasa.stacja.myjnia.wolny) {
	                kasa.stacja.myjnia.rozpoczecieMycia= new RozpoczecieMyciaEvent(kasa.stacja.myjnia);
	            }
	        }
		
	}

}
