package projekt;

import java.util.LinkedList;

import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.monitors.MonitoredVar;
import dissimlab.simcore.BasicSimObj;

public class Myjnia extends BasicSimObj {
	LinkedList<Klient> listaklientow=new LinkedList<Klient>();
    public RozpoczecieMyciaEvent rozpoczecieMycia;
    public ZakonczenieMyciaEvent zakonczenieMycia;
    Stacja stacja;
    private double lastChangeTime = 0;
    private MonitoredVar carsNumber=new MonitoredVar();
    private MonitoredVar time=new MonitoredVar();
    Klient aktualnyklient;
    boolean wolny=true;
	@Override
	public boolean filter(IPublisher arg0, INotificationEvent arg1) {
		// TODO Auto-generated method stub
		return false;
	}
    public Myjnia(Stacja s)
    {
    	stacja=s;
    }
	@Override
	public void reflect(IPublisher arg0, INotificationEvent arg1) {
		// TODO Auto-generated method stub
		
	}
    public void add(Klient k) {
        double dt = simTime()-this.lastChangeTime;
        this.carsNumber.setValue(this.listaklientow.size()+1);
        this.time.setValue(dt);
        this.lastChangeTime=simTime();

        this.listaklientow.add(k);
    }

    public Klient usun()
    {
        double dt = simTime()-this.lastChangeTime;
        this.carsNumber.setValue(this.listaklientow.size()-1);
        this.time.setValue(dt);
        this.lastChangeTime=simTime();

        return this.listaklientow.removeFirst();
    }
}
