package com.pixelnos.fire.backend.ymlreader;

import com.pixelnos.fire.ymlreader.YMLReader;
import com.pixelnos.fire.ymlreader.YMLValue;
import com.pixelnos.fire.ymlreader.YMLWriter;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;


public class YMLReaderTest {
    private String yml = "Test: 5\n"+
            "tags:\n" +
            "  - Restaurant\n" +
            "  - Food\n" +
            "  - Extra Expenses\n"+
            "Wallet:\n" +
            "  accounts:\n" +
            "    -\n" +
            "      name: Savings\n" +
            "      balance: 5840.00\n" +
            "      initial_balance: 6010.00\n" +
            "      currency: US Dollars\n" +
            "      transactions:\n" +
            "        -\n" +
            "          amount: 52.00\n" +
            "          location: Lausanne\n" +
            "          date: Fri Nov 22 22:10:22 CET 2019\n" +
            "          tags:\n" +
            "            - Restaurant\n" +
            "            - Food\n" +
            "            - Extra Expenses\n" +
            "        -\n" +
            "          amount: 118.00\n" +
            "          location: Online\n" +
            "          date: Fri Nov 22 22:10:22 CET 2019\n" +
            "          tags:\n" +
            "            - Headset\n" +
            "            - Micro\n" +
            "            - Extra Expenses";
    @Test
    public void read(){
        YMLValue value = YMLReader.read(yml);
        assertEquals(5.0, value.get("Test").asDouble());
        assertEquals("Restaurant", value.get("tags").asArrayList().get(0).asString());
        YMLValue accounts = value.get("Wallet").get("accounts");
        assertEquals(5840.00, accounts.asArrayList().get(0).get("balance").asDouble());
    }

    @Test
    public void readInlineList(){
        yml = "Test: 5\n"+
                "tags:[Restaurant, Food, Extra Expenses]";
        YMLValue value = YMLReader.read(yml);
        assertEquals(5.0, value.get("Test").asDouble());
        assertEquals("Restaurant", value.get("tags").asArrayList().get(0).asString());
    }

    @Test
    public void readComment(){
        yml = "Test: 5\n"+
                "--- # Tags of the transaction\n" +
                "tags:\n" +
                "  - Restaurant\n" +
                "  - Food\n" +
                "  - Extra Expenses\n";
        YMLValue value = YMLReader.read(yml);
        assertEquals(5.0, value.get("Test").asDouble());
        assertEquals("Restaurant", value.get("tags").asArrayList().get(0).asString());
    }

    @Test
    public void readWriteComment(){
        yml = "Test: 5\n"+
                "--- # Tags of the transaction\n" +
                "tags:\n" +
                "  - Restaurant\n" +
                "  --- This is a very special kind of comment\n" +
                "  - Food\n" +
                "  - Extra Expenses";
        YMLValue value = YMLReader.read(yml);
        String newYML = YMLWriter.write(value);
        assertEquals(yml, newYML);
    }

    @Test
    public void readEmptyString(){
        YMLValue value = YMLReader.read("");
        assertEquals("", value.asString());
    }

    @Test
    public void write(){
        YMLValue value = YMLReader.read(yml);
        String newYML = YMLWriter.write(value);
        assertEquals(yml, newYML);
    }

    @Test
    public void writeWithEmptyYMLValue(){
        YMLValue value = new YMLValue();
        String newYML = YMLWriter.write(value);
        assertEquals("", newYML);
    }

    @Test
    public void addEntry(){
        YMLValue value = new YMLValue();
        value.addEntry("testKey",new YMLValue("testValue"));
        assertEquals("testValue", value.get("testKey").asString());
    }

    @Test
    public void addSeveralEntries(){
        YMLValue value = new YMLValue();
        value.addEntry("testKey",new YMLValue("testValue")).addEntry("testKey2", new YMLValue("testValue2"));
        assertEquals("testValue2", value.get("testKey2").asString());
    }

    @Test
    public void add(){
        YMLValue value = new YMLValue();
        try {
            value.add(new YMLValue("testValue")).add(new YMLValue("testValue2"));
        }
        catch(Exception e){ }
        assertEquals("testValue", value.asArrayList().get(0).asString());
    }
}
