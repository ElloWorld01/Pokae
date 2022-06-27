package fr.gardevoir.pokaetoxicque.data;

import fr.gardevoir.pokaetoxicque.Main;
import fr.gardevoir.pokaetoxicque.game.pokedex.Capacite;
import fr.gardevoir.pokaetoxicque.game.pokedex.Espece;
import fr.gardevoir.pokaetoxicque.interfaces.ICapacite;
import fr.gardevoir.pokaetoxicque.interfaces.IEspece;
import fr.gardevoir.pokaetoxicque.interfaces.IType;
import fr.gardevoir.pokaetoxicque.utils.PokeDatabase;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Le type de données de la classe Espèce.
 */
public class EspeceData {

	/**
	 * Retourne l'identifiant d'une espèce à partir de son nom
	 *
	 * @param especeNom Nom de l'espèce
	 * @return Numéro de l'espèce dans le pokédex
	 */
	public static int getId(String especeNom) {
		String name = especeNom.toLowerCase();
		if (Main.getPokedex() != null) {
			Espece espece = Main.getPokedex().getEspeces().values().stream().filter(
					e -> e.getNom().equals(name)).findFirst().orElse(null);
			if (espece == null)
				throw new IllegalArgumentException("L'espèce " + name + " n'existe pas");
		}
		JSONObject o = PokeDatabase.getObject("pokemon-species");
		JSONArray liste = (JSONArray) o.get("results");
		for (Object obj : liste) {
			JSONObject jsonObject = (JSONObject) obj;
			if (jsonObject.get("name").equals(name)) {
				return Integer.parseInt(jsonObject.get("url").toString()
						.replace("/api/v2/pokemon-species/", "").replace("/", ""));
			}
		}
		throw new NullPointerException("L'espèce " + name + " n'existe pas");
	}

	/**
	 * Retourne le niveau de départ d'une espèce
	 *
	 * @param especeNom Nom de l'espèce
	 * @return Niveau de départ de l'espèce
	 */
	public static int getNiveauDepartEvolution(String especeNom) {
		HashMap<String, Integer> evoNiveau = getAllEvolutions(especeNom);
		List<String> pokemons = new ArrayList<>(evoNiveau.keySet());
		for (int i = 0; i < pokemons.size(); i++) {
			if (pokemons.get(i).equalsIgnoreCase(especeNom))
				if (i == pokemons.size() - 1)
					throw new IllegalArgumentException("Il n'y a pas d'évolutions pour l'espèce " + especeNom);
				else
					return evoNiveau.get(pokemons.get(i + 1));
		}
		throw new IllegalArgumentException("Il n'y a pas d'évolutions pour l'espèce " + especeNom);
	}


	/**
	 * Retourne l'expérience de base de l'espèce
	 *
	 * @param especeNom le nom de l'espèce.
	 * @return la base d'expérience.
	 */
	public static int getBaseExp(String especeNom) {
		JSONObject pokemon = PokeDatabase.getObject(PokeDatabase.pokemon() + getId(especeNom));
		return Math.toIntExact((long) pokemon.get("base_experience"));
	}

	/**
	 * Retourne l'ensemble des capacités de l'espèce dans une liste nommé : "capacite [ ]".
	 *
	 * @param especeNom le nom de l'espèce.
	 * @return la liste : capacite [ ].
	 */
	public static ICapacite[] getCapSet(String especeNom) {
		JSONObject pokemon = PokeDatabase.getObject(PokeDatabase.pokemon() + getId(especeNom));
		JSONArray capSet = (JSONArray) pokemon.get("moves");

		List<ICapacite> capList = new ArrayList<>();
		for (Object o : capSet) {
			JSONObject cap = (JSONObject) o;
			JSONObject move = (JSONObject) cap.get("move");

			if (CapaciteData.isFirstGen(move.get("name").toString())) {
				String path = (String) move.get("url");
				if (CapaciteData.isNotStatus(path))
					capList.add(getCapacite(path));
			}
		}
		return capList.toArray(new ICapacite[0]);
	}

	/**
	 * Retourne la capacite de l'espèce.
	 *
	 * @param path le move url
	 * @return la capacite
	 */
	public static ICapacite getCapacite(String path) {
		JSONObject moveObject = PokeDatabase.getObject(path);
		String moveName = (String) moveObject.get("name");
		return new Capacite(moveName);
	}

	/**
	 * Gets all capacites.
	 *
	 * @param espece the espece
	 * @return the all capacites
	 */
//TODO n'avoir que les capacités que le pokémon peut apprendre à son niveau
	public static HashMap<ICapacite, Integer> getAllCapacites(IEspece espece) {
		JSONObject pokemon = PokeDatabase.getObject(PokeDatabase.pokemon() + EspeceData.getId(espece.getNom()));
		JSONArray allMoves = (JSONArray) pokemon.get("moves");
		List<String> capUrl = new ArrayList<>();

		for (ICapacite c : getCapSet(espece.getNom())) {
			for (Object o : allMoves) {
				JSONObject move = (JSONObject) o;
				JSONObject m = (JSONObject) move.get("api/v2/move");
				String moveName = (String) m.get("name");
				if (c.getNom().equalsIgnoreCase(moveName)) {
					capUrl.add((String) m.get("url"));
				}
			}
		}
		return null;
	}

	/**
	 * Retourne l'évolution de l'espèce donnée.
	 *
	 * @param nomEspece le nom de espèce.
	 * @return l 'évolution de l'espèce.
	 */
	public static IEspece getEvolution(String nomEspece) {
		List<String> evolutions = new ArrayList<>(getAllEvolutions(nomEspece).keySet().stream().toList());
		if (evolutions.size() == 0) return null;

		for (int i = 0; i < evolutions.size(); i++) {
			if (evolutions.get(i).equals(nomEspece))
				if (i == evolutions.size() - 1)
					return null;
				else {
					String nomEspeceEvo = evolutions.get(i + 1);
					if (Main.getPokedex().getEspeces().values().stream().anyMatch(e -> e.getNom().equals(nomEspeceEvo)))
						return new Espece(nomEspeceEvo);

				}
		}
		return null;
	}

	public static HashMap<String, Integer> getAllEvolutions(String nomEspece) {
		LinkedHashMap<String, Integer> evolutionBaseLevel = new LinkedHashMap<>();
		JSONObject pokemon = PokeDatabase.getObject(PokeDatabase.species() + getId(nomEspece));
		JSONObject evolution = (JSONObject) pokemon.get("evolution_chain");
		String evolutionChainUrl = (String) evolution.get("url");

		JSONObject chain_evo = PokeDatabase.getObject(evolutionChainUrl);
		JSONObject chain = (JSONObject) chain_evo.get("chain");

		JSONObject evolutions1 = addNomEspeceNiveauDepart(evolutionBaseLevel, chain);
		if (evolutions1 == null) return evolutionBaseLevel;
		JSONObject evolutions2 = addNomEspeceNiveauDepart(evolutionBaseLevel, evolutions1);
		if (evolutions2 == null) return evolutionBaseLevel;
		addNomEspeceNiveauDepart(evolutionBaseLevel, evolutions2);
		return evolutionBaseLevel;
	}

	public static JSONObject addNomEspeceNiveauDepart(HashMap<String, Integer> evolutionBaseLevel, JSONObject
			evolutionObject) {

		if (evolutionObject == null || evolutionObject.size() == 0)
			return null;

		JSONArray evolutionDetails = (JSONArray) evolutionObject.get("evolution_details");
		if (evolutionDetails == null || evolutionDetails.size() == 0) {
			evolutionBaseLevel.put((String) ((JSONObject) evolutionObject.get("species")).get("name"), 1);
		} else {
			JSONObject species = (JSONObject) evolutionObject.get("species");
			JSONObject evo = (JSONObject) evolutionDetails.get(0);
			if (evo.get("min_level") != null) {
				evolutionBaseLevel.put((String) species.get("name"), Math.toIntExact((long) evo.get("min_level")));
			} else {
				//TODO le pokémon ne mute pas avec son niveau mais avec un objet
				evolutionBaseLevel.put((String) species.get("name"), 20);
			}
		}
		JSONArray evolvesTo = (JSONArray) evolutionObject.get("evolves_to");
		if (evolvesTo.size() == 0)
			return null;
		return (JSONObject) evolvesTo.get(0);
	}

	/**
	 * Retourne le(s) type(s) de l'espèce dans une liste nommé: "type [ ]".
	 *
	 * @param especeNom le nom de l'espèce.
	 * @return la liste "type [ ]" .
	 */
	public static IType[] getTypes(String especeNom) {
		JSONObject pokemon = PokeDatabase.getObject(PokeDatabase.pokemon() + getId(especeNom));
		JSONArray types = (JSONArray) pokemon.get("types");
		IType[] typeArray = new IType[types.size()];
		for (int i = 0; i < types.size(); i++) {
			JSONObject typeObject = (JSONObject) types.get(i);
			JSONObject type = (JSONObject) typeObject.get("type");
			String nom = (String) type.get("name");
			if (TypeData.isFirstGen(nom.toUpperCase())) {
				typeArray[i] = TypeData.getType(nom);
			}
		}
		return typeArray;
	}

	/**
	 * Gets name.
	 *
	 * @param id the id
	 * @return the name
	 */
	public static String getName(int id) {
		return PokeDatabase.getObject(PokeDatabase.pokemon() + id).get("name").toString();
	}
}