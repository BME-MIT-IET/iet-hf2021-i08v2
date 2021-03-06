package hu.bme.jegmezo.core;

/**
 * Az eszkimó típusú karaktert reprezentáló osztály.
 * Különleges képessége, hogy tud iglut építeni.
 */
public class Eskimo extends Character {
    /**
     * Az eszkimó osztály konstruktora. Meghívja a hu.bme.jegmezo.core.Character ősosztály konstruktorát.
     * @param i Erre a jégtáblára lépteti a szereplőt.
     */
    public Eskimo(IceTable i) {
        super(i);
        bodyTemperature = 5;
        workUnit = 4;
        initialWorkUnit = 4;
    }

    /**
     * A paraméterben kapott táblára épít egy iglut.
     * @param t A jégtábla.
     * @return -1, nem használandó.
     */
    @Override
    public int useAbility(IceTable t) {
        getIceTable().addIglu();
        workUnit--;
        if (workUnit == 0) {
            game.nextPlayer();
            workUnit = initialWorkUnit;
        }
        return -1;
    }

    /**
     * Ez a függvény üresen kerül definiálásra az eszkimó
     * részéről.
     */
    @Override
    public void invadeOtherCharacters() {
        // Ez a függvény üresen kerül definiálásra az eszkimó részéről.
     }
}
