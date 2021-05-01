package hu.bme.jegmezo.graphics;

import hu.bme.jegmezo.core.Researcher;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * A hu.bme.jegmezo.graphics.GCharacter sarkkutatóhoz tartozó konkrét leszátmazottja.
 */
public class GResearcher extends GCharacter{
    /**
     * Konstruktor, ami beállítja a karaktert, a hozzá tartozó képet és az inventorit.
     * @param researcher
     */
    public GResearcher(Researcher researcher) {
        super(researcher);
        try {
            File imageFile = new File("src/main/resources/images/researcher.png");
            img = ImageIO.read(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        inventory = new Inventory("researcher.png");
        inventory.addItem(new GPickable("signalrocket.png", true));
    }
}
