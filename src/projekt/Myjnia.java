package projekt;

import java.util.LinkedList;

import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.monitors.Change;
import dissimlab.monitors.MonitoredVar;
import dissimlab.simcore.BasicSimObj;

public class Myjnia extends BasicSimObj {
	LinkedList<Klient> listaklientow=new LinkedList<Klient>();//kolejka klientow do myjni
    public RozpoczecieMyciaEvent rozpoczecieMycia;
    public ZakonczenieMyciaEvent zakonczenieMycia;
    Stacja stacja;
    private double lastChangeTime = 0;
    public MonitoredVar liczbaklientow=new MonitoredVar();
    public MonitoredVar time=new MonitoredVar();
    Klient aktualnyklient;//aktualny klient myjni
    boolean wolny=true;//czy myjnia jest niezajeta
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
	//dodanie klienta do myjni z zapisaniem czasu i dlugosci kolejki w zmiennych MonitoredVar
    public void add(Klient k) {
        double czas = simTime()-this.lastChangeTime;
        this.liczbaklientow.setValue(this.listaklientow.size()+1);
        this.time.setValue(czas);
        this.lastChangeTime=simTime();
        this.listaklientow.add(k);
    }
  //usuniecie klienta z myjni z zapisaniem czasu i dlugosci kolejki w zmiennych MonitoredVar
    public Klient usun()
    {
        double czas = simTime()-this.lastChangeTime;
        this.liczbaklientow.setValue(this.listaklientow.size()-1);
        this.time.setValue(czas);
        this.lastChangeTime=simTime();

        return this.listaklientow.removeFirst();
    }
 //wylicza graniczna liczbe klientow w kolejce
    public double granicznaLiczbaKolejki()
    {
        if(this.time.numberOfSamples() != this.liczbaklientow.numberOfSamples()) return -1;
        if(this.time.numberOfSamples() == 0) return 0;

        double result = 0;
        int numberOfSamples = time.numberOfSamples();

        double p = 0;
        Change changeNumber;
        Change changeTime;
        for (int i = 0; i < numberOfSamples; i++) {
            changeNumber = liczbaklientow.getChanges().get(i);
            changeTime = time.getChanges().get(i);

            result += changeNumber.getValue()*changeTime.getValue();
            p += changeTime.getValue();
        }
        return result/p;
    }
}
