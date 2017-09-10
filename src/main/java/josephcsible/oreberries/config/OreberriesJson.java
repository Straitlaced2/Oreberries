package josephcsible.oreberries.config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UncheckedIOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.minecraft.init.Items;

public class OreberriesJson {
	public static boolean needsWrite = false;
	public static JsonObject getDefaults() {
		Gson gson = new Gson();
		JsonObject root = new JsonObject();

		JsonObject iron = new JsonObject();
		iron.addProperty("color", "#FFC7A3");
		iron.addProperty("tooltip", "Sweet Irony");
		iron.addProperty("smeltingResult", Items.IRON_NUGGET.getRegistryName().toString());
		iron.addProperty("rarity", 6);
		root.add("Iron", iron);

		JsonObject gold = new JsonObject();
		gold.addProperty("color", "#FFCC33");
		gold.addProperty("tooltip", "Pure Luster");
		gold.addProperty("smeltingResult", Items.GOLD_NUGGET.getRegistryName().toString());
		gold.addProperty("sizeChance", 6);
		gold.addProperty("rarity", 9);
		gold.addProperty("maxHeight", 32);
		root.add("Gold", gold);

		JsonObject copper = new JsonObject();
		copper.addProperty("color", "#FF8833");
		copper.addProperty("tooltip", "Tastes like metal");
		JsonObject copperNugget = new JsonObject();
		copperNugget.addProperty("color", "#FF6600");
		copper.add("smeltingResult", copperNugget);
		copper.addProperty("rarity", 4);
		copper.addProperty("density", 2);
		copper.addProperty("minHeight", 20);
		copper.addProperty("maxHeight", 60);
		root.add("Copper", copper);

		JsonObject tin = new JsonObject();
		tin.addProperty("color", "#BB4422");
		tin.addProperty("tooltip", "Tin Man");
		JsonObject tinNugget = new JsonObject();
		tinNugget.addProperty("color", "#FFBA80");
		tin.add("smeltingResult", tinNugget);
		tin.addProperty("rarity", 4);
		tin.addProperty("density", 2);
		tin.addProperty("maxHeight", 40);
		root.add("Tin", tin);

		JsonObject aluminum = new JsonObject();
		aluminum.addProperty("color", "#EEFFFF");
		aluminum.addProperty("tooltip", "White Chocolate");
		aluminum.add("oredictNames", gson.toJsonTree(new String[]{"nuggetAluminum", "nuggetAluminium"}));
		JsonObject aluminumNugget = new JsonObject();
		aluminumNugget.add("ingotNames", gson.toJsonTree(new String[]{"ingotAluminum", "ingotAluminium"}));
		aluminum.add("smeltingResult", aluminumNugget);
		aluminum.addProperty("sizeChance", 14);
		aluminum.addProperty("rarity", 3);
		aluminum.addProperty("density", 2);
		aluminum.addProperty("maxHeight", 60);
		root.add("Aluminum", aluminum);

		JsonObject essence = new JsonObject();
		essence.addProperty("bushName", "Essence Berry Bush");
		essence.addProperty("berryName", "Concentrated Essence Berry");
		essence.addProperty("tooltip", "Tastes like Creeper");
		essence.add("oredictNames", new JsonArray());
		essence.add("smeltingResult", JsonNull.INSTANCE);
		essence.addProperty("special", "essence");
		essence.addProperty("growsInLight", true);
		essence.addProperty("tradeable", false);
		essence.addProperty("sizeChance", 8);
		essence.addProperty("rarity", 6);
		essence.addProperty("preferredHeight", 48);
		essence.addProperty("maxHeight", 32);
		root.add("Essence", essence);

		return root;
	}

	public static JsonElement read(File file) {
		JsonElement rootElem;
		JsonParser parser = new JsonParser();
		try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
			rootElem = parser.parse(reader);
		} catch (@SuppressWarnings("unused") FileNotFoundException e) {
			rootElem = getDefaults();
			needsWrite = true;
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
		return rootElem;
	}

	public static void write(File file, JsonElement json) {
		Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().disableHtmlEscaping().create();
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			gson.toJson(json, writer);
		} catch(IOException e) {
			throw new UncheckedIOException(e);
		}
		needsWrite = false;
	}
}