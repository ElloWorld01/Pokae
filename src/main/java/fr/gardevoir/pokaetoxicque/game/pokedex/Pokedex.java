package fr.gardevoir.pokaetoxicque.game.pokedex;

import fr.gardevoir.pokaetoxicque.data.CapaciteData;
import fr.gardevoir.pokaetoxicque.data.EspeceData;
import fr.gardevoir.pokaetoxicque.data.PokedexData;
import fr.gardevoir.pokaetoxicque.data.TypeData;
import fr.gardevoir.pokaetoxicque.interfaces.*;
import fr.gardevoir.pokaetoxicque.utils.PokeDatabase;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;

/**
 * Définition d'un Pokedex.
 */
public class Pokedex implements IPokedex {

	private final HashMap<Integer, Espece> especes = new HashMap<>();
	private final HashMap<Integer, Capacite> capacites = new HashMap<>();

	/**
	 * Instancie un nouveau Pokedex.
	 */
	public Pokedex() {
		JSONObject listPokemons = PokeDatabase.getObject("pokemon");
		JSONArray pokes = (JSONArray) listPokemons.get("results");
		for (int i = 1; i <= 151; i++)
			especes.put(i, new Espece((String) ((JSONObject) pokes.get(i - 1)).get("name")));

		JSONObject listCapacites = PokeDatabase.getObject("generation/1");
		JSONArray capes = (JSONArray) listCapacites.get("moves");
		for (int i = 1; i <= capes.size(); i++) {
			String path = (String) ((JSONObject) capes.get(i - 1)).get("url");
			String name = (String) ((JSONObject) capes.get(i - 1)).get("name");
			if (CapaciteData.isNotStatus(path)) {
				capacites.put(i, new Capacite(name));
			}
		}
	}

	@Override
	public IPokemon[] engendreRanch() {
		return PokedexData.engendreRanchRandom();
	}

	@Override
	public IEspece getInfo(String nomEspece) {
		return new Espece(EspeceData.getId(nomEspece));
	}

	@Override
	public Double getEfficacite(IType attaque, IType defense) {
		return TypeData.getEfficacite(attaque, defense);
	}

	@Override
	public ICapacite getCapacite(String nomCapacite) {
		return new Capacite(nomCapacite);
	}

	@Override
	public ICapacite getCapacite(int nunCapacite) {
		return CapaciteData.getCapacite(nunCapacite);
	}

	/**
	 * Retourne l'espèces.
	 *
	 * @return l 'espèces
	 */
	public HashMap<Integer, Espece> getEspeces() {
		return especes;
	}

	/**
	 * Retournes les capacites.
	 *
	 * @return les capacites
	 */
	public HashMap<Integer, Capacite> getCapacites() {
		return capacites;
	}
}