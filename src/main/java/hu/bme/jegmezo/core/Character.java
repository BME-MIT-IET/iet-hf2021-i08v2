package hu.bme.jegmezo.core;

import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * Absztrakt osztály. A felhasználó által irányított szereplőket reprezentáló
 * osztály (pl. eszkimó, sarkkutató). Felelősségei többek közt a mozgás,
 * munkavégzés.
 */
public abstract class Character {
	/**
	 * A hátralévő munkaegységek száma az adott körben.
	 */
	protected int workUnit;
	/**
	 * Az elvéghezhető munkaegységek számának eredeti/kezdeti értéke.
	 */
	protected int initialWorkUnit;
	/**
	 * A szereplő testhőjének mértéke.
	 */
	protected int bodyTemperature;
	/**
	 * A szereplő visel-e búvárruhát.
	 */
	protected boolean diver;
	/**
	 * A szereplő vízben van-e.
	 */
	private boolean inWater;
	/**
	 * Ezen a jégtáblán áll a szereplő.
	 */
	private IceTable iceTable;
	/**
	 * A jelzőrakéta.
	 */
	private SignalRocket signalRocket = SignalRocket.getInstance();
	/**
	 * A játék osztály.
	 */
	protected Game game = Game.getInstance();
	/**
	 * A szereplő által birtokolt tárgyak.
	 */
	private ArrayList<Usable> usables = new ArrayList<>();

	/**
	 * A karakter osztály konstruktora.
	 *
	 * @param i Erre a jégtáblára lépteti a szereplőt.
	 */
	protected Character(IceTable i) {
		iceTable = i;
		i.stepOn(this);
		usables.add(signalRocket);
		game.addCharacter(this);
	}

	/**
	 * Az eltárolandó tárgyak által hívott függvény, amely felveszi usebles listába
	 * az adott Useble-t.
	 *
	 * @param u Az eltárolandó tárgy.
	 */
	public void addUsable(Usable u) {
		usables.add(u);
	}

	/**
	 * A usebles listának az adott indexű Useable-jét használja.
	 * 
	 * @param idx index
	 * @param d   irány
	 */
	public void useUsable(int idx, Direction d) {
		if (idx > usables.size() - 1) {
			JOptionPane.showMessageDialog(null, "Nincs ilyen indexű tárgy a Karakternél!");
			return;
		}
		usables.get(idx).use(this, d);
		workUnit--;
		if (workUnit == 0) {
			game.nextPlayer();
			workUnit = initialWorkUnit;
		}
	}

	/**
	 * A testhő megváltozására használható metódus. A paraméterben kapott számot
	 * adja testhőhöz.
	 *
	 * @param diff A hozzáadandó testhő mértéke.
	 */
	public void changeHeat(int diff) {
		bodyTemperature += diff;
		if (bodyTemperature <= 0)
			game.endGame(false);
	}

	/**
	 * A búvárruha felvételekor meghívandó metódus. A diver értéket állítja true-ra.
	 */
	public void makeDiver() {
		diver = true;
	}

	/**
	 * A paraméterként kapott alkatrészt hozzáadja a
	 * hu.bme.jegmezo.core.SignalRocket-hoz.
	 *
	 * @param s Az alkatrész.
	 */
	public void buildSignalRocket(SignalRocketPart s) {
		signalRocket.build(s);
	}

	/**
	 * Absztrakt függvény a karakter speciális képességének használatára a
	 * paraméterben kapott táblán.
	 *
	 * @param t A jégtábla.
	 */
	public abstract int useAbility(IceTable t);

	/**
	 * A szereplő léptetése ‘d’ irányba.
	 *
	 * @param d Az irány.
	 */
	public void move(Direction d) {
		var i = iceTable.getNeighbour(d);
		if (i == null)
			return;

		iceTable.stepOff(this);
		iceTable = i;
		iceTable.stepOn(this);
		workUnit--;
		if (workUnit == 0) {
			game.nextPlayer();
			workUnit = initialWorkUnit;
		}
	}

	/**
	 * A szereplő beleesik a vízbe.
	 */
	public void fallInWater() {
		inWater = true;
		if (!diver) {
			game.nextPlayer();
			workUnit = initialWorkUnit;
		}
	}

	/**
	 * A szereplő meghal.
	 */
	public void die() {
		game.endGame(false);
	}

	/**
	 * A szereplő ás azon a jégtáblán, amelyen éppen áll.
	 */
	public void dig() {
		Pickable p = iceTable.extract(1);
		if (p != null)
			p.pickUp(this);
		workUnit--;
		if (workUnit == 0) {
			game.nextPlayer();
			workUnit = initialWorkUnit;
		}
	}

	/**
	 * Visszaadja a táblán, amin tartózkodik.
	 *
	 * @return A jégtábla, amelyen áll.
	 */
	public IceTable getIceTable() {
		return iceTable;
	}

	/**
	 * A szereplő kijön a víből.
	 */
	public void comeOutOfWater() {
		inWater = false;
	}

	/**
	 * A szereplő táblája frissül.
	 *
	 * @param iceTable az új tábla
	 */
	public void setIceTable(IceTable iceTable) {
		this.iceTable = iceTable;
	}

	/**
	 * A karakter elkap másik karaktereket a jégtáblán, amin áll. Ezt az absztrakt
	 * függvényt csak a hu.bme.jegmezo.core.PolarBear fogja nem üresen
	 * megvalósítani.
	 */
	public abstract void invadeOtherCharacters();

	/**
	 * A hu.bme.jegmezo.core.Game osztály hívja meg minden kör elején. Visszaadja az
	 * inWater tagváltozó értékét.
	 *
	 * @return A karakter vízben van-e.
	 */
	public boolean getInWater() {
		return inWater;
	}

	/**
	 * Ez a függvény hívandó akkor, ha a játékos még nem fejezte be a körét (tehát
	 * nem használta el a 4 munkaegységét). Meghívja a game tagváltozóján a
	 * nextPlayer() metódust, majd visszaállítja a workUnit-ját a kezdeti értékre.
	 */
	public void pass() {
		game.nextPlayer();
		workUnit = initialWorkUnit;
	}

	/**
	 * Az egységnyi munka aktuális értékének lekérdezése.
	 *
	 * @return egységnyi munka értéke
	 */
	public int getWorkUnit() {
		return workUnit;
	}

	/**
	 * A testhő értékének lekérdezése.
	 *
	 * @return testhő értéke.
	 */
	public int getBodyTemperature() {
		return bodyTemperature;
	}

	/**
	 * A diver változó állapotának lektérdezése.
	 *
	 * @return diver értéke
	 */
	public boolean isDiver() {
		return diver;
	}
}
