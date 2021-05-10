package hu.bme.jegmezo.graphics;

import hu.bme.jegmezo.core.PolarBear;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * A hu.bme.jegmezo.graphics.GCharacter jegesmedvéhez tartozó konkrét
 * leszátmazottja.
 */
public class GPolarBear extends GCharacter {
    /**
     * Konstruktor, ami beállítja a karaktert, a hozzá tartozó képet és az
     * invetorit, ami ez esetben üres, csak a karakter iconját jeleníti meg, hogy
     * jelezze a karakter körét.
     * 
     * @param polarBear
     */
    public GPolarBear(PolarBear polarBear) {
        super(polarBear);
        try {
            var imageFile = new File("src/main/resources/images/polarbear.png");
            img = ImageIO.read(imageFile);
        } catch (IOException e) {
            // Semmi se történik itt.
        }
        inventory = new Inventory("polarbear.png");
    }
}
