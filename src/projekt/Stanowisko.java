package projekt;

import java.util.LinkedList;

import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.monitors.Change;
import dissimlab.monitors.MonitoredVar;
import dissimlab.simcore.BasicSimObj;

public class Stanowisko extends BasicSimObj {
	private int ID;
	private int typ;
	public Klient aktualnyKlient;
    public LinkedList<Klient> ListaKlientow=new LinkedList<Klient>();
    public RozpoczecieTankowaniaEvent rozpoczecie;
    public ZakonczenieTankowaniaEvent zakoncz;
	public MonitoredVar liczbaklientow;
    public boolean wolny;
    public Stacja stacja;
	
    public Stanowisko(Stacja st,int id,int t)
    {
    	stacja=st;
    	ID=id;
    	typ=t;
    	wolny=true;
    	liczbaklientow = new MonitoredVar();
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

	public int getTyp() {
		return typ;
	}

	public void setTyp(int typ) {
		this.typ = typ;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	//dodanie klienta do stanowiska 
	public void add(Klient k) {
        this.liczbaklientow.setValue((double)ListaKlientow.size()+1);
        this.ListaKlientow.add(k);
    }
	//usuniecie klienta ze stanowiska 
	public Klient delete() {
        this.liczbaklientow.setValue((double)this.ListaKlientow.size()-1);
        return ListaKlientow.removeFirst();
	}

}
