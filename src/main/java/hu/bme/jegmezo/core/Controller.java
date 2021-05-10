package hu.bme.jegmezo.core;

/**
 * A játékosok irányításáért felelős osztály.
 */
public class Controller {

	private static Game game = Game.getInstance();
	private static Direction direction = new Direction(0);

	/**
	 * A soron lévő játékos karakterének jelzi, hogy használja a képességét.
	 * 
	 * @param d az irány száma
	 */
	public static void characterUseAbility(int d) {
		var c = game.getCurrCharacter();
		IceTable it = c.getIceTable().getNeighbour(new Direction(d));
		int value = c.useAbility(it);
		if (value != -1) {
			Game.getInstance().printMessage("Kapacitás: " + value);
		}
	}

	/**
	 * A soron lévő játékos karakterének jelzi, hogy mozogjon.
	 * 
	 * @param d az irány száma
	 */
	public static void moveCharacter(int d) {
		var c = game.getCurrCharacter();
		c.move(new Direction(d));
	}

	/**
	 * A soron lévő játékos karakterének jelzi, hogy használja a képességét.
	 * 
	 * @param idx a kivalaszott tárgy indexe
	 */
	public static void useUsable(int idx) {
		var c = game.getCurrCharacter();
		c.useUsable(idx, direction);
	}

	/**
	 * Jelzi,hogy a soron lévő játékos ásnást hajt végre.
	 */
	public static void digCharacter() {
		var c = game.getCurrCharacter();
		c.dig();
	}

	/**
	 * Jelzi,hogy a soron lévő játékos átadja a körét.
	 */
	public static void passCharacter() {
		var c = game.getCurrCharacter();
		c.pass();
	}

	/**
	 * A tárgy használati irányának beállítása.
	 * 
	 * @param d irány
	 */
	public static void setDirection(int d) {
		direction = new Direction(d);
	}
}
