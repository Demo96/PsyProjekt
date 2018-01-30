package projekt;

import java.util.LinkedList;

import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.monitors.MonitoredVar;
import dissimlab.simcore.BasicSimObj;
import dissimlab.simcore.SimControlException;

public class Stacja extends BasicSimObj {
    public LinkedList<Stanowisko> ListaStanowisk=new LinkedList<Stanowisko>();
    public Kasa kasa;
    public Myjnia myjnia;
    public PrzybycieKlientaEvent przybycie;
    public MonitoredVar czasTankowania;
    public MonitoredVar czasMycia;
    public double zrezygnowaniKlienci;//liczba kientow  ktorzy zrezygnowali
    public double wszyscyKlienci;//liczba wszystkich klientow
    public Stacja() throws SimControlException
    {
    	czasTankowania=new MonitoredVar();
    	czasMycia=new MonitoredVar();
    	kasa=new Kasa(this);
    	myjnia=new Myjnia(this);
    	this.przybycie = new PrzybycieKlientaEvent(this,0);
    	int pom=0;
    	for(int i=0;i<Ustawienia.benzyna;i++)
    	{
    		pom++;
    		Stanowisko e=new Stanowisko(this,pom,1);
    		ListaStanowisk.add(e);
    	}
    	for(int i=0;i<Ustawienia.LPG;i++)
    	{
    		pom++;
    		Stanowisko e=new Stanowisko(this,pom,2);
    		ListaStanowisk.add(e);
    	}
    	for(int i=0;i<Ustawienia.ON;i++)
    	{
    		pom++;
    		Stanowisko e=new Stanowisko(this,pom,3);
    		ListaStanowisk.add(e);
    	}
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

	public Myjnia getMyjnia() {
		return myjnia;
	}

	public void setMyjnia(Myjnia myjnia) {
		this.myjnia = myjnia;
	}

}
