package projekt;

import dissimlab.monitors.Statistics;
import dissimlab.simcore.SimControlEvent;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimManager;
import dissimlab.simcore.SimParameters.SimControlStatus;

public class Main {
	public static void main(String[] args) {

		SimManager simmanager = SimManager.getInstance();
		Stacja stacjaX = null;
		try {
			stacjaX = new Stacja();
		} catch (SimControlException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			SimControlEvent stopEvent = new SimControlEvent(100.0, SimControlStatus.STOPSIMULATION);
		} catch (SimControlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			simmanager.startSimulation();
		} catch (SimControlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < stacjaX.ListaStanowisk.size(); i++) {
			System.out.println("oczekiwan¹ graniczn¹ liczbê samochodów w kolejce do dystrybutora nr " + (i + 1) + "= "
					+ stacjaX.ListaStanowisk.get(i).granicznaLiczbaSamochodow());
		}

		System.out.println("oczekiwan¹ graniczn¹ liczbê samochodów w kolejkach do myjni= "
				+ stacjaX.myjnia.granicznaLiczbaSamochodow());
		System.out.println(
				"oczekiwany graniczny czas tankowania samochodu= " + Statistics.arithmeticMean(stacjaX.czasTankowania));
		System.out
				.println("oczekiwany graniczny czas mycia samochodu = " + Statistics.arithmeticMean(stacjaX.czasMycia));
		System.out.println("graniczne prawdopodobieñstwo rezygnacji z obs³ugi przez kierowcê samochodu= "
				+ Statistics.arithmeticMean(stacjaX.czasTankowania));
	}
}
