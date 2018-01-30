package projekt;

import java.util.LinkedList;

import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.simcore.BasicSimObj;

public class Kasa extends BasicSimObj {
	public LinkedList<Klient> ListaKlientow = new LinkedList<Klient>();//kolejka do kasy
	public RozpoczeciePlaceniaEvent rozpoczeciePlacenia;
	public ZakonczeniePlaceniaEvent zakonczeniePlacenia;
	public Klient aktualnyklient[]=new Klient[Ustawienia.liczbakas];//aktualni klienci w kasach
	public Stacja stacja;
	public boolean wolnekasy[]=new boolean[Ustawienia.liczbakas];//czy kasa nr X jest wolna

	public Kasa(Stacja s) {
		stacja = s;
		for(int i=0;i<Ustawienia.liczbakas;i++)
			wolnekasy[i]=true;
	}

	@Override
	public boolean filter(IPublisher arg0, INotificationEvent arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void reflect(IPublisher arg0, INotificationEvent arg1) {
		// TODO Auto-generated method stub

	}
	//zwraca numer wolnej kasy lub -1 jesli wszystkie zajete
	public int ktorawolna()
	{
		for(int i=0;i<Ustawienia.liczbakas;i++)
		{
			if(wolnekasy[i]==true)
				return i;
		}
		return -1;
	}
}
