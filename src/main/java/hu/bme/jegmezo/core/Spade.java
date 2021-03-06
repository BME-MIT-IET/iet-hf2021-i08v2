package hu.bme.jegmezo.core;

/**
 * Az ásó osztálya.
 */
public class Spade implements Usable, Pickable {
    /**
     * A paraméterül kapott karaktertől lekéri, hogy melyik mezőn áll, majd annak
     * meghívja az extract(amount: int) függvényét, ahol amount = 2. Amennyiben ez
     * egy felvehető tárggyal tér vissza, akkor meghívja azon a pickUp(c)-t.
     * 
     * @param c a karakter, aki meghívja a függvényt
     * @param d a tárgy használatánk iránya
     */
    @Override
    public void use(Character c, Direction d) {
        var i = c.getIceTable();
        i.extract(2);
    }

    /**
     * A paraméterül kapott karakternek meghívja az addUsable(u:
     * hu.bme.jegmezo.core.Usable) függvényét, amiben paraméterül saját magát adja.
     * 
     * @param c a karakter, aki el felveszi.
     */
    @Override
    public void pickUp(Character c) {
        c.addUsable(this);
    }
}
