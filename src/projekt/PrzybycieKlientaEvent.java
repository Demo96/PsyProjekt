package projekt;

import java.util.Random;
import java.util.logging.Logger;
import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class PrzybycieKlientaEvent extends BasicSimEvent<Stacja, Object> {
	public static int IdK = 0;
	private Stacja stacja;

	public PrzybycieKlientaEvent(Stacja entity, double delay) throws SimControlException {
		super(entity, delay);
		stacja = entity;
	}

	@Override
	public Object getEventParams() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void onInterruption() throws SimControlException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onTermination() throws SimControlException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void stateChange() throws SimControlException {
		final Logger LOGGER = Logger.getLogger(Main.class.getName());
		Random gen = new Random();
		int typ = gen.nextInt(3) + 1;
		Klient k = new Klient(IdK++, typ);
		k.startObs = simTime();
		LOGGER.info("Pojawienie sie klienta("+k.getID()+") typ paliwa:"+typ +" czas: " + simTime());
		int pom = -1;
		for (int i = 0; i < Ustawienia.maxkolejka; i++) {
			for (int j = 0; j < stacja.ListaStanowisk.size(); j++) {
				if (stacja.ListaStanowisk.get(j).getTyp() == k.getTyppaliwa())
					if (stacja.ListaStanowisk.get(j).ListaKlientow.size() < (i + 1)) {
						pom = j;
						break;
					}
			}
		}
		if (pom > -1) {
			stacja.ListaStanowisk.get(pom).add(k);

			if (stacja.ListaStanowisk.get(pom).ListaKlientow.size() == 1 && stacja.ListaStanowisk.get(pom).wolny) {
				stacja.ListaStanowisk.get(pom).rozpoczecie = new RozpoczecieTankowaniaEvent(
						stacja.ListaStanowisk.get(pom));
			}
		}
		else LOGGER.info("Klient("+k.getID()+") zrezygnowal z powodu braku miejsca na stanowiskach");
		double odstep = Ustawienia.randOdstepKlient();
		stacja.przybycie = new PrzybycieKlientaEvent(stacja, odstep);
	}

}