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
    public MonitoredVar liczbaklientow=new MonitoredVar();
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
	//dodanie klienta do myjni 
    public void add(Klient k) {
        this.liczbaklientow.setValue((double)this.listaklientow.size()+1);
        this.listaklientow.add(k);
    }
  //usuniecie klienta z myjni 
    public Klient usun()
    {
        this.liczbaklientow.setValue((double)this.listaklientow.size()-1);
        return this.listaklientow.removeFirst();
    }

}
