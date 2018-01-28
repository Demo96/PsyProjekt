package projekt;

import java.util.LinkedList;

import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.simcore.BasicSimObj;

public class Kasa extends BasicSimObj {
	public LinkedList<Klient> ListaKlientow = new LinkedList<Klient>();
	public boolean kasy[] = new boolean[Ustawienia.liczbakas];
	public boolean wolny = true;
	public RozpoczeciePlaceniaEvent rozpoczeciePlacenia;
	public ZakonczeniePlaceniaEvent zakonczeniePlacenia;
	public Klient aktualnyklient;
	public Stacja stacja;

	public Kasa(Stacja s) {
		for (int i = 0; i < kasy.length; i++) {
			kasy[i] = true;
		}
		stacja = s;
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

	public boolean czywolny() {
		for (int i = 0; i < kasy.length; i++) {
			if (kasy[i] = true)
				return true;
		}
		return false;
	}

	public boolean getwolny() {
		return wolny;
	}

	public void setwolny(boolean w) {
		wolny = w;
	}
}
