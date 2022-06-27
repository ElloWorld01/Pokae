package fr.gardevoir.pokaetoxicque.data;

import fr.gardevoir.pokaetoxicque.Main;
import fr.gardevoir.pokaetoxicque.game.pokedex.Type;
import fr.gardevoir.pokaetoxicque.interfaces.IType;
import fr.gardevoir.pokaetoxicque.utils.PokeDatabase;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Map;

/**
 * Le type de données de la classe Type.
 */
public class TypeData {


	/**
	 * Retourne le type.
	 *
	 * @param typeName le nom du type
	 * @return le type
	 */
	public static IType getType(String typeName) {
		return Type.valueOf(typeName.toUpperCase());
	}

	/**
	 * Retourne type.
	 *
	 * @param obj le nom du type
	 * @return le type
	 */
	public static IType getType(JSONObject obj) {
		JSONArray array = (JSONArray) obj.get("past_values");
		if (array.size() > 0) {
			JSONObject past = (JSONObject) array.get(0);
			JSONObject type = (JSONObject) past.get("type");
			if (type != null) {
				String typeName = (String) type.get("name");
				return Type.valueOf(typeName.toUpperCase());
			}
		}
		return Type.valueOf(((JSONObject) obj.get("type")).get("name").toString().toUpperCase());
	}

	/**
	 * Retourne l'efficacite.
	 *
	 * @param attaque l'attaque
	 * @param defense ta défense
	 * @return l 'efficacite
	 */
	public static double getEfficacite(IType attaque, IType defense) {
		JSONObject type = PokeDatabase.getObject(PokeDatabase.type() + getId(attaque.getNom()));
		JSONObject relations = (JSONObject) type.get("damage_relations");
		Map<JSONArray, Double> damages = Map.of(
				(JSONArray) relations.get("double_damage_to"), 2.0,
				(JSONArray) relations.get("half_damage_to"), 0.5,
				(JSONArray) relations.get("no_damage_to"), 0.0);
		int i = 0;
		double efficacite = 1.0;
		while (i < damages.size() && efficacite == 1.0) {
			JSONArray array = damages.keySet().toArray(new JSONArray[0])[i];
			for (Object o : array) {
				JSONObject obj = (JSONObject) o;
				String typeName = (String) obj.get("name");
				if (typeName.equalsIgnoreCase(defense.getNom()))
					efficacite = damages.get(array);
			}
			i++;
		}
		return efficacite;
	}

	/**
	 * Récupère l'identifiant d'une espèce à partir de son nom
	 *
	 * @param typeName Nom de l'espèce
	 * @return Numéro de l'espèce dans le pokédex
	 */
	public static int getId(String typeName) {
		String name = typeName.toLowerCase();
		if (Main.getPokedex() != null) {
			IType type = getType(typeName);
			if (type == null)
				throw new IllegalArgumentException("Le type " + name + " n'existe pas");
		}
		JSONObject o = PokeDatabase.getObject("type");
		JSONArray liste = (JSONArray) o.get("results");
		for (Object obj : liste) {
			JSONObject jsonObject = (JSONObject) obj;
			if (jsonObject.get("name").equals(name)) {
				return Integer.parseInt(jsonObject.get("url").toString()
						.replace("/api/v2/type/", "").replace("/", ""));
			}
		}
		throw new IllegalArgumentException("Le type " + name + " n'existe pas");
	}

	/**
	 * Is first gen boolean.
	 *
	 * @param name the name
	 * @return the boolean
	 */
	public static boolean isFirstGen(String name) {
		return !name.equalsIgnoreCase("FAIRY")
				&& !name.equalsIgnoreCase("DARK")
				&& !name.equalsIgnoreCase("STEEL");
	}
}