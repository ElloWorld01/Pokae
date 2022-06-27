package fr.gardevoir.pokaetoxicque.utils;

import fr.gardevoir.pokaetoxicque.data.CapaciteData;
import fr.gardevoir.pokaetoxicque.data.EspeceData;
import fr.gardevoir.pokaetoxicque.interfaces.ICapacite;
import fr.gardevoir.pokaetoxicque.interfaces.IPokemon;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * The type Layout.
 */
public class Layout {
	/**
	 * The constant RESET.
	 */
	public static final String RESET = "\033[0m";

	/**
	 * Species to french string.
	 *
	 * @param p the p
	 * @return the string
	 */
	public static String speciesToFrench(IPokemon p) {
		return getElementInFrench(PokeDatabase.getObject(PokeDatabase.species() + EspeceData.getId(p.getEspece().getNom())));
	}

	/**
	 * Move to french string.
	 *
	 * @param c the c
	 * @return the string
	 */
	public static String moveToFrench(ICapacite c) {
		return getElementInFrench(PokeDatabase.getObject(PokeDatabase.move() + CapaciteData.getId(c.getNom())));
	}

	private static String getElementInFrench(JSONObject languages) {
		for (Object key : languages.keySet()) {
			if (key.equals("names")) {
				JSONArray names = (JSONArray) languages.get(key);
				for (Object o : names) {
					JSONObject object = (JSONObject) o;
					String pokemonName = (String) object.get("name");
					JSONObject language = (JSONObject) object.get("language");
					String langue = (String) language.get("name");
					if (langue.equalsIgnoreCase("fr"))
						return pokemonName.substring(0, 1).toUpperCase() + pokemonName.substring(1).toLowerCase();
				}
			}
		}
		return "";
	}

	/**
	 * Error.
	 *
	 * @param error the error
	 */
	public static void error(String error) {
		String ANSI_RED_BACKGROUND = "\u001B[41m";
		System.out.println(ANSI_RED_BACKGROUND + "Erreur : " + error + RESET);
	}

	/**
	 * Info.
	 *
	 * @param info the info
	 */
	public static void info(String info) {
		String ANSI_CYAN_BACKGROUND = "\u001B[46m";
		System.out.println(ANSI_CYAN_BACKGROUND + "Info : " + info + RESET);
	}
}