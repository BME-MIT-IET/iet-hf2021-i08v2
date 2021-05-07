package helper;

import hu.bme.jegmezo.core.*;
import hu.bme.jegmezo.core.Character;

public class Translator {

    public static IceTable createIceTableFromString(String iceTableType, String itemType) {
        switch (iceTableType) {
            case "stable":
                return new Stable(createPickableFromString(itemType));
            case "unstable":
                return new Unstable(createPickableFromString(itemType));
            case "hole":
                return new Hole();
            default:
                throw new IllegalArgumentException("IceTable type not found");
        }
    }

    private static Pickable createPickableFromString(String itemType) throws IllegalArgumentException {
        switch (itemType) {
            case "food":
                return new Food();
            case "rope":
                return new Rope();
            case "spade":
                return new Spade();
            case "fragile spade":
                return new FragileSpade();
            case "diving suit":
                return new DivingSuit();
            case "tent":
                return new Tent();
            case "signal rocket part":
                return new SignalRocketPart();
            case "no":
                return null;

            default:
                throw new IllegalArgumentException("Pickable type not found");
        }
    }

    public static Character createCharacterFromString(String characterType, IceTable initialPosition) {
        switch (characterType) {
            case "eskimo":
                return new Eskimo(initialPosition);
            case "researcher":
                return new Researcher(initialPosition);
            case "polar bear":
                return new PolarBear(initialPosition);
            default:
                throw new IllegalArgumentException("Character type not found");
        }
    }
}
