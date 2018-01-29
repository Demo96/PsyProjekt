package projekt;

import java.util.logging.Logger;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class ZakonczeniePlaceniaEvent extends BasicSimEvent<Kasa, Object> {
	public Kasa kasa;
	public int numerkasy;
	public ZakonczeniePlaceniaEvent(projekt.Kasa entity, double delay,int nr) throws SimControlException {
		super(entity, delay);
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

	        // Odblokuj gniazdo
	        kasa.wolnekasy[numerkasy]=true;
	        if(kasa.aktualnyklient[numerkasy].getTyppaliwa()!=0) {
	        kasa.aktualnyklient[numerkasy].aktualneStanowisko.wolny=true;
	        if (kasa.aktualnyklient[numerkasy].aktualneStanowisko.ListaKlientow.size() > 0)
	               {
	        	kasa.aktualnyklient[numerkasy].aktualneStanowisko.rozpoczecie = new RozpoczecieTankowaniaEvent(kasa.aktualnyklient[numerkasy].aktualneStanowisko);
	               }
	        }
	        System.out.println("Koniec placenia w kasie nr" +(numerkasy+1) + " klient(" + kasa.aktualnyklient[numerkasy].getID()+") czas: "+simTime());
	        double czasObs = simTime() - kasa.aktualnyklient[numerkasy].startObs;
	        kasa.stacja.czasTankowania.setValue(czasObs);
	        // Zaplanuj dalsza obs³uge
	        if (kasa.ListaKlientow.size() > 0)
	        {
	            kasa.rozpoczeciePlacenia = new RozpoczeciePlaceniaEvent(kasa,numerkasy);
	        }
	        //a moze do myjni  tez
	        if(kasa.aktualnyklient[numerkasy].getMyjnia())
	        {
	        	kasa.stacja.myjnia.add(kasa.aktualnyklient[numerkasy]);
	            if (kasa.stacja.myjnia.listaklientow.size()==1 && kasa.stacja.myjnia.wolny) {
	                kasa.stacja.myjnia.rozpoczecieMycia= new RozpoczecieMyciaEvent(kasa.stacja.myjnia);
	            }
	        }
		
	}

}
