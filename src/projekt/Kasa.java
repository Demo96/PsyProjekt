package projekt;

import java.util.LinkedList;

import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.simcore.BasicSimObj;

public class Kasa extends BasicSimObj {
	public LinkedList<Klient> ListaKlientow = new LinkedList<Klient>();
	public RozpoczeciePlaceniaEvent rozpoczeciePlacenia;
	public ZakonczeniePlaceniaEvent zakonczeniePlacenia;
	public Klient aktualnyklient[]=new Klient[Ustawienia.liczbakas];
	public Stacja stacja;
	public boolean wolnekasy[]=new boolean[Ustawienia.liczbakas];

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
