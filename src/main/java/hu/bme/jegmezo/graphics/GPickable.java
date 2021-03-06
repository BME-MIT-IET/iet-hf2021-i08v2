package hu.bme.jegmezo.graphics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * A felvehető tárgyak kirajzolásához tartozó osztály.
 */
public class GPickable implements IDrawable {
    private Image img;
    private boolean useableItem;

    /**
     * Konstruktor, ami a paraméternek megfelelően beállítja az osztályhoz tartozó
     * képet, és azt, hogy hu.bme.jegmezo.core.Usable típusúak, vagyis, hogy el
     * kell-e őket tárolni az inventoriban.
     * 
     * @param imageName
     * @param useableItem
     */
    public GPickable(String imageName, boolean useableItem) {
        try {
            var imageFile = new File("src/main/resources/images/" + imageName);
            img = ImageIO.read(imageFile);
        } catch (IOException e) {
            // Semmi se történik itt.
        }
        this.useableItem = useableItem;
    }

    /**
     * Kirajzolja magát a paraméterben átadott helyre.
     * 
     * @param g Az objektum, amire kirajzolja magát.
     * @param x x szerinti pozíció.
     * @param y y szerinti pozíció.
     */
    @Override
    public void draw(Graphics g, int x, int y) {
        g.drawImage(img, x, y, null);
    }

    /**
     * Visszaadja azt, hogy a tárgy inventoriban eltárolandó vagy sem.
     * 
     * @return Inventoriban eltárolandó-e?
     */
    public boolean isUseableItem() {
        return useableItem;
    }
}
