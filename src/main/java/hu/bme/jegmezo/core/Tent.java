package hu.bme.jegmezo.core;

/**
 * A sátor osztálya.
 */
public class Tent implements Usable, Pickable {
    /**
     * Az sátor lerakása. Lekérdezi a paraméterben kapott karakter jégtábláját. Ezen
     * meghívja az setTent(true) függvényt, amivel sátrat rak rá.
     * 
     * @param c a karakter, aki meghívja a függvényt
     * @param d a tárgy használatánk iránya
     */
    @Override
    public void use(Character c, Direction d) {
        var i = c.getIceTable();
        i.setTent(true);
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
