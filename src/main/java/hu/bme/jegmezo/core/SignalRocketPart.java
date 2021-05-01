package hu.bme.jegmezo.core;

/**
 * A jelzőrakéta összeszereléséhez kellő alkatrész. A játék megnyeréséhez szükséges a megszerzésük. Összesen 3 ilyen van.
 */
public class SignalRocketPart implements Pickable {
    /**
     * A paraméterben kapott karakteren meghívja a BuildSignalRocket(hu.bme.jegmezo.core.Pickable: i) függvényt, ezzel hozzáadva magát a hu.bme.jegmezo.core.SignalRocket-hez.
     * @param c - A karakter, aki felveszi a tárgyat.
     */
    public void pickUp(Character c) {
        c.buildSignalRocket(this);
    }
}
