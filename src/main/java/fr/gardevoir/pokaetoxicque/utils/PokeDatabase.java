package fr.gardevoir.pokaetoxicque.utils;


import fr.gardevoir.pokaetoxicque.Main;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * Le type de Poke api manager.
 */
public class PokeDatabase {

	/**
	 * The Json parser.
	 */
	static final JSONParser jsonParser = new JSONParser();


	/**
	 * Retourne l'objet de l'api.
	 *
	 * @param path le chemin de l'objet auquel on souhaite accéder.
	 * @return l 'objet de l'api.
	 */
	public static JSONObject getObject(String path) {
		String fileName = ("/api/v2/" + path + "/index.json");
		while (fileName.contains("//"))
			fileName = fileName.replace("//", "/");
		while (fileName.contains("/api/v2/api/v2/"))
			fileName = fileName.replace("/api/v2/api/v2/", "/api/v2/");

		InputStream in = Main.class.getResourceAsStream(fileName);
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		try {
			return (JSONObject) jsonParser.parse(reader);
		} catch (ParseException | IOException e) {
			throw new RuntimeException(e);
		}
	}


	/**
	 * Pokemon string.
	 *
	 * @return the string
	 */
	public static String pokemon() {
		return "pokemon/";
	}

	/**
	 * Species string.
	 *
	 * @return the string
	 */
	public static String species() {
		return "pokemon-species/";
	}

	/**
	 * Generation 1 string.
	 *
	 * @return the string
	 */
	public static String generation1() {
		return "generation/1";
	}

	/**
	 * Type string.
	 *
	 * @return the string
	 */
	public static String type() {
		return "type/";
	}

	/**
	 * Move string.
	 *
	 * @return the string
	 */
	public static String move() {
		return "move/";
	}
}