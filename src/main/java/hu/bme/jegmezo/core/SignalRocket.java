package hu.bme.jegmezo.core;

import java.util.ArrayList;

/**
 * A jelzőrakéta tárolja az eddig megtalált részeit. Ha a játékosok az összes
 * részét megtalálták, és egy táblán állnak, akkor elsütésével megnyerik a
 * játékot, erről az osztály értesíti a hu.bme.jegmezo.core.Game-et. Singleton
 * osztály.
 */
public class SignalRocket implements Usable {
    private static SignalRocket instance = new SignalRocket();
    private Game game = Game.getInstance();
    private ArrayList<SignalRocketPart> parts = new ArrayList<>();

    /**
     * Privát konstruktor a Singleton-pattern megvalósításához.
     */
    private SignalRocket() {
    }

    /**
     * Visszaadja az egyetlen hu.bme.jegmezo.core.SignalRocket példányt,
     * Singleton-pattern megvalósításához szükséges.
     * 
     * @return hu.bme.jegmezo.core.SignalRocket példány.
     */
    public static SignalRocket getInstance() {
        return instance;
    }

    /**
     * Először megvizsgálja, hogy a parts listának 3 eleme van-e, ami ha teljesül
     * lekéri a paraméterül kapott karakter mezőjét. Ezután a mezőn meghívja a
     * getCharactersNumber()-t, a game tagváltozóján pedig a
     * getTotalCharactersNumber()-t. Amennyiben ezeknek a száma egyezik meghívja a
     * game-en az endGame(true)-t, ezzel jelezve, hogy megnyerték a játékot.
     * 
     * @param c a karakter, aki meghívja a függvényt
     * @param d a tárgy használatánk iránya
     */
    @Override
    public void use(Character c, Direction d) {
        var i = c.getIceTable();
        int characterNumber = i.getCharactersNumber();
        int totalCharactersNumber = game.getPlayersNumber();

        if (parts.size() == 3 && characterNumber == totalCharactersNumber) {
            game.endGame(true);
        }
    }

    /**
     * Egy karakter hívja meg, ha felvesz alkatrészt
     * (hu.bme.jegmezo.core.SignalRocketPart), amit a
     * hu.bme.jegmezo.core.SignalRocket eltárol a parts listájában.
     * 
     * @param s a felvett alkatrész
     */
    public void build(SignalRocketPart s) {
        parts.add(s);
    }

    /**
     * A jelzőrakéta alaphelyzetbe állítása: alkatrészek törlése a listából.
     */
    public void reset() {
        this.parts.clear();
    }
}
