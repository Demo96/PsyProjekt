package projekt;

import java.util.LinkedList;

import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.monitors.MonitoredVar;
import dissimlab.simcore.BasicSimObj;

public class Stanowisko extends BasicSimObj {
	private int ID;
	private int typ;
	public Klient aktualnyKlient;
    public LinkedList<Klient> ListaKlientow=new LinkedList<Klient>();
    public RozpoczecieTankowaniaEvent rozpoczecie;
    public ZakonczenieTankowaniaEvent zakoncz;
	private double lastChangeTime;
	private MonitoredVar carsNumber;
    private MonitoredVar time;
    public boolean wolny;
    public Stacja stacja;
	
    public Stanowisko(Stacja st,int id,int t)
    {
    	stacja=st;
    	ID=id;
    	typ=t;
    	wolny=true;
    	carsNumber = new MonitoredVar();
        time = new MonitoredVar();
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
	public void add(Klient k) {
        double dt = simTime()-this.lastChangeTime;
        this.carsNumber.setValue(ListaKlientow.size()+1);
        this.time.setValue(dt);
        this.lastChangeTime=simTime();

        this.ListaKlientow.add(k);
    }
	public Klient delete() {
		double dt = simTime()-this.lastChangeTime;
        this.carsNumber.setValue(this.ListaKlientow.size()-1);
        this.time.setValue(dt);
        this.lastChangeTime=simTime();
        return ListaKlientow.removeFirst();
	}
}
