package projekt;

import dissimlab.monitors.Statistics;
import dissimlab.simcore.SimControlEvent;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimManager;
import dissimlab.simcore.SimParameters.SimControlStatus;

public class Main {
	public static void main(String[] args) {

		SimManager simManager = SimManager.getInstance();
		Stacja myStacja = null;
		try {
			myStacja = new Stacja();
		} catch (SimControlException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			SimControlEvent stopEvent = new SimControlEvent(105.0, SimControlStatus.STOPSIMULATION);
		} catch (SimControlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			simManager.startSimulation();
		} catch (SimControlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < myStacja.ListaStanowisk.size(); i++) {

			System.out.println("oczekiwan¹ graniczn¹ liczbê samochodów w kolejkach do stanowiska nr "+(i+1)+" = "
					+ Statistics.arithmeticMean(myStacja.ListaStanowisk.get(i).liczbaklientow));
		}
		System.out.println("oczekiwan¹ graniczn¹ liczbê samochodów w kolejkach do myjni= "
				+ Statistics.arithmeticMean(myStacja.myjnia.liczbaklientow));
		System.out.println(
				"oczekiwany graniczny czas tankowania samochodu= " + Statistics.arithmeticMean(myStacja.czasTankowania));
		System.out
				.println("oczekiwany graniczny czas mycia samochodu = " + Statistics.arithmeticMean(myStacja.czasMycia));
		System.out.println("graniczne prawdopodobieñstwo rezygnacji z obs³ugi przez kierowcê samochodu= "
				+ myStacja.zrezygnowaniKlienci/myStacja.wszyscyKlienci);
	}
}
