package fr.gardevoir.pokaetoxicque.data;

import fr.gardevoir.pokaetoxicque.game.pokedex.Stat;
import fr.gardevoir.pokaetoxicque.interfaces.IStat;
import fr.gardevoir.pokaetoxicque.utils.PokeDatabase;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Le type de données de la classe Stat.
 */
public class StatData {


	/**
	 * Retourne la base stat de l'espèce.
	 *
	 * @param espece l'espèce.
	 * @return la base stat de l'espèce.
	 */
	public static IStat getBaseStat(String espece) {
		return getStat("base_stat", espece);
	}

	/**
	 * Retourne les ev stat de l'espèce.
	 *
	 * @param espece l' espèce
	 * @return les ev stat de l'espèce.
	 */
	public static IStat getEVStat(String espece) {
		return getStat("effort", espece);
	}

	private static IStat getStat(String statName, String espece) {
		int pointsDeVie = 0, force = 0, defense = 0, special = 0, vitesse = 0;
		JSONObject pokemon = PokeDatabase.getObject("pokemon/" + EspeceData.getId(espece));
		if (pokemon == null)
			return null;

		JSONArray baseStatArray = (JSONArray) pokemon.get("stats");
		for (Object o : baseStatArray) {
			JSONObject stat = (JSONObject) o;
			JSONObject stats = (JSONObject) stat.get("stat");
			String name = (String) stats.get("name");
			long statValue = (long) stat.get(statName);
			switch (name) {
				case "hp" -> pointsDeVie = (int) statValue;
				case "attack" -> force = (int) statValue;
				case "defense" -> defense = (int) statValue;
				case "special-attack" -> special = (int) statValue;
				case "speed" -> vitesse = (int) statValue;
			}
		}
		return new Stat(pointsDeVie, force, defense, special, vitesse);
	}
}