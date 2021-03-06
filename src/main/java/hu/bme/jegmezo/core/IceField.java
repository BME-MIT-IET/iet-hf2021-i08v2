package hu.bme.jegmezo.core;

import hu.bme.jegmezo.Main;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * A jégmezőt reprezentáló osztály. Tartalmazza a jégtáblákat.
 */
public class IceField {
    private ArrayList<IceTable> iceTables = new ArrayList<>();

    private SecureRandom random = new SecureRandom();

    /**
     * A hóvihar determinisztikus működését a paraméterben megadott érték alapján
     * tudjuk beállítani. Determinisztikus esetben minden tartalmazott jégtáblára
     * meghívja addSnow(1), majd a getUnprotectedCharacters() függvényt, és az így
     * visszakapott karaktereken meghívja a changeHeat(-1). Nem determinisztikus
     * esetben véletlenszerű táblákra futtatja le a fentebb lévő műveleteket.
     */
    public void snowStorm() {
        if (random.nextBoolean() && !Main.det)
            return;
        for (IceTable t : iceTables) {
            if (random.nextBoolean() || Main.det) {
                t.addSnow(1);
                List<Character> characters = t.getUnprotectedCharacters();
                if (characters == null)
                    return;
                for (Character c : characters) {
                    c.changeHeat(-1);
                }
            }

        }
    }

    /**
     * Ezzel a függvénnyel egy jégtáblát adhatunk az iceField-hez. A paraméterben
     * kapott jégtáblát hozzáadja az iceTables adattagjához. Fontos, hogy a kapott
     * hu.bme.jegmezo.core.IceTable-nek a szomszédsági kapcsolatai definiálva
     * legyenek előre.
     * 
     * @param it A hozzáadandó hu.bme.jegmezo.core.IceTable.
     */
    public void addIceTable(IceTable it) {
        iceTables.add(it);
    }

    /**
     * Ez a függvény felszedeti a lerakott sátrakat a jégtáblákról. Egy ciklusban
     * végigmegy az iceTables összes elemén és meghívja rajtuk a setTent() függvényt
     * false paraméterrel.
     */
    public void takeTents() {
        for (IceTable t : iceTables) {
            t.setTent(false);
        }
    }
}
