package projekt;

import java.util.Random;

import dissimlab.random.SimGenerator;

//kilka kas nie jedna
//klienci tylko do myjni
public class Ustawienia {
	public static SimGenerator generator = new SimGenerator();
	public static double czymyjnia = 0.5;// prawdopodobienstwo ze klient bedzie chcial skorzystac z myjni
	public static int liczbakas = 2;// liczba kas
	public static int benzyna = 1, LPG = 1, ON = 1;// liczba stanowisk z beznyna,LPG,ON
	public static int maxkolejka = 1;// max dlugosc kolejki do stanowiska (wlacznie z obecnie korzystajacym)
	// rozklady typ 0-normalny 1-uniform 2-gamma 3-beta
	public static int RozkladPojawieniaKlienta = 0;// odstep miedzy pojawianiem sie klientow
	public static double PKarg1 = 1.0, PKarg2 = 1.0;
	public static int Rozkladbenzyna = 1;// czas tankowania benzyny
	public static double BENarg1 = 2.0, BENarg2 = 9.0;
	public static int RozkladLPG = 1;// czas LPG
	public static double LPGarg1 = 1.0, LPGarg2 = 7.0;
	public static int RozkladON = 1;// czas ON
	public static double ONarg1 = 2.0, ONarg2 = 5.0;
	public static int RozkladPlacenia = 2;// czas placenia w kasie
	public static double PLarg1 = 5.0, PLarg2 = 5.0;
	public static int RozkladMycia = 3;// czas mycia w myjni
	public static double MYarg1 = 4.0, MYarg2 = 8.0;

	public static boolean czyMyjnia() {
		Random generatorPom = new Random();
		if (czymyjnia > generatorPom.nextDouble())
			return true;
		return false;
	}

	public static double rand(int typ, double a, double b) {
		if (typ == 0)
			return Math.abs(generator.normal(a, b));
		else if (typ == 1)
			return Math.abs(generator.uniform(a, b));
		else if (typ == 2)
			return Math.abs(generator.gamma(a, b));
		else if (typ == 3)
			return Math.abs(generator.beta(a, b));
		else
			return 0;
	}

	public static double randOdstepKlient() {
		return rand(RozkladPojawieniaKlienta, PKarg1, PKarg2);
	}

	public static double randCzasObslugiBenzyna() {
		return rand(Rozkladbenzyna, BENarg1, BENarg2);
	}

	public static double randCzasObslugiLPG() {
		return rand(RozkladLPG, LPGarg1, LPGarg2);
	}

	public static double randCzasObslugiON() {
		return rand(RozkladON, ONarg1, ONarg2);
	}

	public static double randCzasPlacenia() {
		return rand(RozkladPlacenia, PLarg1, PLarg2);
	}

	public static double randCzasMycia() {
		return rand(RozkladMycia, MYarg1, MYarg2);
	}

}
