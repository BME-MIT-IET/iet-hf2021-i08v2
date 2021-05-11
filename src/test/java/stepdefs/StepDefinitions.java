package stepdefs;

import helper.Translator;
import hu.bme.jegmezo.core.*;
import hu.bme.jegmezo.core.Character;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;
import java.util.HashMap;

public class StepDefinitions {
    private final HashMap<String, IceTable> iceTables = new HashMap<>();
    private int nextDirectionNumber = 0;
    private final HashMap<String, Direction> directions = new HashMap<>();
    private final HashMap<String, Character> characters = new HashMap<>();
    private int usabilityResult;

    @Given("Create {string} {string} field with {int} capacity and {string} item")
    public void createIceTableFieldWithCapacityAndItem(String iceTableId, String iceTableType, int iceTableCapacity, String itemType) {
        IceTable createdField = Translator.createIceTableFromString(iceTableType, itemType);
        createdField.setCapacity(iceTableCapacity);
        iceTables.put(iceTableId, createdField);
    }

    @Given("{string} {string} neighbour {string}")
    public void setIceTableNeighbour(String aIceTableId, String direction, String bIceTableId) {
        IceTable aField = iceTables.get(aIceTableId);
        IceTable bField = iceTables.get(bIceTableId);
        directions.put(direction, new Direction(nextDirectionNumber++));
        aField.setNeighbour(bField, directions.get(direction));
    }

    @Given("Create {string} {string} type character on {string} field")
    public void createTypeCharacterOnField(String characterId, String characterType, String iceTableId) {
        IceTable initialPosition = iceTables.get(iceTableId);
        Character eskimo = Translator.createCharacterFromString(characterType, initialPosition);
        characters.put(characterId, eskimo);
    }

    @Given("{string} move to {string}")
    public void characterMoveTo(String characterId, String direction) {
        Character character = characters.get(characterId);
        character.move(directions.get(direction));
    }

    @Given("Add {int} layer of snow to {string}")
    public void addLayerOfSnowToIceTable(int snowLayer, String fieldId) {
        IceTable field = iceTables.get(fieldId);
        field.addSnow(snowLayer);
    }

    @When("{string} is on {string}")
    public void characterIsOnIceTable(String characterId, String iceTableId) {
        Character character = characters.get(characterId);
        IceTable expectedField = iceTables.get(iceTableId);
        assertEquals(expectedField, character.getIceTable());
    }

    @When("{string} has {int} work unit")
    public void characterHasWorkUnit(String characterId, int expectedWorkUnit) {
        Character character = characters.get(characterId);
        assertEquals(expectedWorkUnit, character.getWorkUnit());
    }

    @When("{string} is in water")
    public void characterIsInWater(String characterId) {
        Character character = characters.get(characterId);
        assertTrue(character.getInWater());
    }

    @When("{string} is not in water")
    public void characterIsNotInWater(String characterId) {
        Character character = characters.get(characterId);
        assertFalse(character.getInWater());
    }

    @When("{string} dig on the ice field")
    public void characterDigOnIceTable(String characterId) {
        Character character = characters.get(characterId);
        character.dig();
    }

    @When("{string} use the first item")
    public void characterUseTheItem(String characterId) {
        Character character = characters.get(characterId);
        character.useUsable(1, null);
    }

    @Then("{string} use ability on {string}")
    public void characterUseAbilityOnIceTable(String characterId, String iceTableId) {
        Character character = characters.get(characterId);
        IceTable field = iceTables.get(iceTableId);
        usabilityResult = character.useAbility(field);
    }

    @Then("There is an igloo on the {string}")
    public void iceTableHasAnIgloo(String iceTableId) {
        IceTable field = iceTables.get(iceTableId);
        assertTrue(field.getIgluState());
    }

    @Then("{string} capacity equal the value from use ability")
    public void iceTableCapacityEqualTheValueFromUseAbility(String iceTableId) {
        IceTable field = iceTables.get(iceTableId);
        assertEquals(field.getCapacity(), usabilityResult);
    }

    @Then("There is no snow on {string}")
    public void iceTableHasLayerOfSnow(String iceTableId) {
        IceTable field = iceTables.get(iceTableId);
        assertFalse(field.anySnow());
    }

    @Then("{string} has a diving suit")
    public void characterHasADivingSuit(String characterId) {
        Character character = characters.get(characterId);
        assertTrue(character.isDiver());
    }

    @Then("{string} body temperature is {int}")
    public void characterBodyTemperatureIs(String characterId, int bodyTemperature) {
        Character character = characters.get(characterId);
        assertEquals(character.getBodyTemperature(), bodyTemperature);
    }

    @Then("There is a tent on the {string}")
    public void thereIsATentOnTheIceField(String iceTableId) {
        IceTable field = iceTables.get(iceTableId);
        assertTrue(field.getTentState());
    }
}
