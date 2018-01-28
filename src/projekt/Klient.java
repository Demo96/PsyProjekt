package projekt;

import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.simcore.BasicSimObj;

public class Klient extends BasicSimObj {
	private int ID;
	private int typpaliwa=0;;//0tylkomyjnia 1benzyna 2LPG 3ON
	private Boolean myjnia=true;
	public double startObs;
	public Stanowisko aktualneStanowisko;
    public Klient(int id)
    {
    	ID=id;
    	myjnia=Ustawienia.czyMyjnia();
    }
	public int getTyppaliwa() {
		return typpaliwa;
	}

	public void setTyppaliwa(int typpaliwa) {
		this.typpaliwa = typpaliwa;
	}

	public Boolean getMyjnia() {
		return myjnia;
	}

	public void setMyjnia(Boolean myjnia) {
		this.myjnia = myjnia;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
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
}
