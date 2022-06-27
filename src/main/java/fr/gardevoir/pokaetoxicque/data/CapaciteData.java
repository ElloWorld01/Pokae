package fr.gardevoir.pokaetoxicque.data;

import fr.gardevoir.pokaetoxicque.Main;
import fr.gardevoir.pokaetoxicque.game.pokedex.Capacite;
import fr.gardevoir.pokaetoxicque.interfaces.ICapacite;
import fr.gardevoir.pokaetoxicque.utils.PokeDatabase;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Random;

/**
 * Le type de données de la classe Capacite .
 */
public class CapaciteData {

	/**
	 * Retourne la capacité.
	 *
	 * @param nunCapacite le numéro de la capcité.
	 * @return la capacité.
	 */
	public static ICapacite getCapacite(int nunCapacite) {
		if (nunCapacite > Main.getPokedex().getCapacites().size() || nunCapacite <= 0)
			throw new IllegalArgumentException("La capacité demandée n'existe pas.");

		JSONObject gen1 = PokeDatabase.getObject(PokeDatabase.generation1());
		JSONArray listeCapacites = (JSONArray) gen1.get("moves");
		JSONObject capacite = (JSONObject) listeCapacites.get(nunCapacite - 1);
		if (capacite == null)
			return null;
		return new Capacite(capacite.get("name").toString());
	}

	/**
	 * Is not status boolean.
	 *
	 * @param path the path
	 * @return the boolean
	 */
	public static boolean isNotStatus(String path) {
		String realPath = path;
		JSONObject capInfos = PokeDatabase.getObject(realPath);
		return !((JSONObject) capInfos.get("damage_class")).get("name").equals("status");
	}

	/**
	 * Is first gen boolean.
	 *
	 * @param capName the cap name
	 * @return the boolean
	 */
	public static boolean isFirstGen(String capName) {
		if (Main.getPokedex() == null) return false;
		for (Capacite cap : Main.getPokedex().getCapacites().values())
			if (cap.getNom().equalsIgnoreCase(capName)) return true;
		return false;
	}

	/**
	 * Is allowed boolean.
	 *
	 * @param path    the path
	 * @param capName the cap name
	 * @return the boolean
	 */
	public static boolean isAllowed(String path, String capName) {
		return isNotStatus(path) && isFirstGen(capName);
	}


	/**
	 * Gets id.
	 *
	 * @param capName the cap name
	 * @return the id
	 */
	public static int getId(String capName) {
		JSONObject o = PokeDatabase.getObject(PokeDatabase.move());
		JSONArray liste = (JSONArray) o.get("results");
		for (Object obj : liste) {
			JSONObject jsonObject = (JSONObject) obj;
			if (jsonObject.get("name").equals(capName)) {
				return Integer.parseInt(jsonObject.get("url").toString()
						.replace("/api/v2/move/", "").replace("/", ""));
			}
		}
		throw new IllegalArgumentException("La capacité demandée n'existe pas.");
	}

	/**
	 * Gets path.
	 *
	 * @param nom the nom
	 * @return the path
	 */
	public static String getPath(String nom) {
		return PokeDatabase.move() + CapaciteData.getId(nom.toLowerCase());
	}

	/**
	 * Gets damage class.
	 *
	 * @param nom  the nom
	 * @param json the json
	 * @return the damage class
	 */
	public static String getDamageClass(String nom, JSONObject json) {
		String damageClass = ((JSONObject) json.get("damage_class")).get("name").toString();
		if (damageClass.equals("status"))
			throw new IllegalArgumentException("La capacité " + nom + " est de type statut, ce qui n'est pas autorisé.");
		return damageClass;
	}

	/**
	 * Gets accuracy.
	 *
	 * @param json the json
	 * @return the accuracy
	 */
	public static double getAccuracy(JSONObject json) {
		return Double.parseDouble((json.get("accuracy") == null ? 100 : json.get("accuracy")).toString());
	}

	/**
	 * Gets pp.
	 *
	 * @param json the json
	 * @return the pp
	 */
	public static int getPP(JSONObject json) {
		return Integer.parseInt(json.get("pp").toString());
	}

	/**
	 * Gets power.
	 *
	 * @param json the json
	 * @return the power
	 */
	public static int getPower(JSONObject json) {
		return Integer.parseInt((json.get("power") == null ? new Random().nextInt(101) + 50 : json.get("power")).toString());
	}
}