package projekt;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class RozpoczecieTankowaniaEvent extends BasicSimEvent<Stanowisko, Object> {
	public Stanowisko stanowisko;
    public RozpoczecieTankowaniaEvent(Stanowisko entity, double delay) throws SimControlException {
		super(entity, delay);
		stanowisko=entity;
	}
    public RozpoczecieTankowaniaEvent(Stanowisko entity) throws SimControlException
    {
        super(entity);
        stanowisko = entity;
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
		if (stanowisko.ListaKlientow.size() > 0)
        {
			// ustaw stanowisko jako zajete, przenies pierwszego klienta z kolejki do stanowiska jako aktualnego klienta
            stanowisko.wolny=false;
            // Pobierz zg³oszenie
            Klient k = stanowisko.delete();
            stanowisko.aktualnyKlient = k;
         // oblicz czas mycia i zaplanuj jego zakonczenie po uplywie tego czasu
            double czasObslugi = 0;
            if(k.getTyppaliwa() == 0)
                czasObslugi = Ustawienia.randCzasObslugiBenzyna();
            else if(k.getTyppaliwa() == 1)
                czasObslugi = Ustawienia.randCzasObslugiLPG();
            else if(k.getTyppaliwa() == 2)
                czasObslugi = Ustawienia.randCzasObslugiON();
            System.out.println("Poczatek tankowania na stanowisku nr " + stanowisko.getID() + " klient(" + k.getID()+") czas:"+simTime());
            stanowisko.aktualnyKlient.aktualneStanowisko=stanowisko;
            stanowisko.zakoncz = new ZakonczenieTankowaniaEvent(stanowisko, czasObslugi);
        }
	}

}
