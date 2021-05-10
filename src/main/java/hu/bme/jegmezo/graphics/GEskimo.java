package hu.bme.jegmezo.graphics;

import hu.bme.jegmezo.core.Eskimo;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * A hu.bme.jegmezo.graphics.GCharacter eszkimóhoz tartozó konkrét
 * leszátmazottja.
 */
public class GEskimo extends GCharacter {
    /**
     * Konstruktor, ami beállítja a karaktert, hozzá tartozó képet és az inventorit.
     * 
     * @param eskimo
     */
    public GEskimo(Eskimo eskimo) {
        super(eskimo);
        try {
            var imageFile = new File("src/main/resources/images/eskimo.png");
            img = ImageIO.read(imageFile);
        } catch (IOException e) {
            // Semmi se történik itt.
        }
        inventory = new Inventory("eskimo.png");
        inventory.addItem(new GPickable("signalrocket.png", true));
    }
}
