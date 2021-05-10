package hu.bme.jegmezo.graphics;

import hu.bme.jegmezo.core.Hole;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * A hu.bme.jegmezo.graphics.GIceTable lyukhoz tartozó konkrét leszármazottja.
 */
public class GHole extends GIceTable {

    /**
     * Konstruktor, ami beállítja a jégtábla típusát és a hozzá tartozó képet.
     * 
     * @param hole
     */
    public GHole(Hole hole) {
        super(hole, null);
        try {
            File imageFile = new File("src/main/resources/images/hole.png");
            iceTableImg = ImageIO.read(imageFile);
        } catch (IOException e) {
            // Semmi se történik itt.
        }
    }
}
