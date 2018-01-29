package projekt;

import java.util.logging.Logger;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class ZakonczenieTankowaniaEvent extends BasicSimEvent<Stanowisko, Object> {
	public Stanowisko stanowisko;
    public ZakonczenieTankowaniaEvent(Stanowisko entity, double delay) throws SimControlException {
		super(entity, delay);
		stanowisko=entity;
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
        //stanowisko.wolny=true;
		System.out.println("Koniec tankowania na stanowisku numer"  + stanowisko.getID() + " klienta(" + stanowisko.aktualnyKlient.getID() +") czas: "+simTime());

        
     // Zaplanuj dalsza obs³uge
     //   if (stanowisko.ListaKlientow.size() > 0)
     //   {
     //       stanowisko.rozpoczecie = new RozpoczecieTankowaniaEvent(stanowisko);
       // }

        
            // do kasy
            stanowisko.stacja.kasa.ListaKlientow.add(stanowisko.aktualnyKlient);
            int temp=stanowisko.stacja.kasa.ktorawolna();
            if (stanowisko.stacja.kasa.ListaKlientow.size()==1 &&temp >-1) {
                stanowisko.stacja.kasa.rozpoczeciePlacenia = new RozpoczeciePlaceniaEvent(stanowisko.stacja.kasa,temp);

        stanowisko.aktualnyKlient = null;
	}
	}

    
}
