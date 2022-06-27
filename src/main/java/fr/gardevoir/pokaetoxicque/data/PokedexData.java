package fr.gardevoir.pokaetoxicque.data;

import fr.gardevoir.pokaetoxicque.Main;
import fr.gardevoir.pokaetoxicque.game.pokedex.Capacite;
import fr.gardevoir.pokaetoxicque.game.pokedex.Espece;
import fr.gardevoir.pokaetoxicque.game.pokedex.Pokemon;
import fr.gardevoir.pokaetoxicque.interfaces.ICapacite;
import fr.gardevoir.pokaetoxicque.interfaces.IPokemon;
import fr.gardevoir.pokaetoxicque.utils.Layout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Le type de données de la classe Pokedex.
 */
public class PokedexData {

	private static final Random r = new Random();

	/**
	 * Engendre un ranch dans une liste nommé: "pokemon [ ]".
	 *
	 * @return la liste " pokemon [ ]".
	 */
	public static IPokemon[] engendreRanchPlayer() {
		List<Pokemon> pokemons = creationDuRanch(new ArrayList<>(Main.getPokedex().getEspeces().values()));

		System.out.print("Voulez-vous donner un nom à vos pokemons ? (O/N) : ");
		Scanner sc = new Scanner(System.in);
		String reponse = sc.nextLine();
		if (reponse.trim().equals("O") || reponse.trim().equals("o"))
			demandeNomsPokemons(pokemons);
		else
			for (Pokemon pokemon : pokemons)
				pokemon.setNom(Layout.speciesToFrench(pokemon));

		return pokemons.toArray(new IPokemon[6]);
	}

	/**
	 * Engendre ranch random pokemon [ ].
	 *
	 * @return the pokemon [ ]
	 */
	public static IPokemon[] engendreRanchRandom() {
		List<Pokemon> pokemons = creationDuRanch(new ArrayList<>(Main.getPokedex().getEspeces().values()));

		for (Pokemon pokemon : pokemons)
			pokemon.setNom(Layout.speciesToFrench(pokemon));

		return pokemons.toArray(new IPokemon[6]);
	}

	/**
	 * Donne capacites.
	 *
	 * @param pokemon the pokemon
	 */
	public static void donneCapacites(IPokemon pokemon) {
		Random r = new Random();
		for (int j = 0; j < pokemon.getCapacitesApprises().length; j++) {
			Capacite c = (Capacite) Main.getPokedex().getCapacites()
					.values().toArray()[r.nextInt(Main.getPokedex().getCapacites().size())];
			String nomCapacite = c.getNom();

			Capacite capacite = Main.getPokedex().getCapacites().values().stream().filter(
					t -> t.getNom().equalsIgnoreCase(nomCapacite)).findFirst().orElse(null);
			if (capacite != null) {
				pokemon.getCapacitesApprises()[j] = new Capacite(nomCapacite);
			}
		}
	}

	private static void demandeNomsPokemons(List<Pokemon> pokemons) {
		Scanner sc = new Scanner(System.in);
		for (Pokemon pokemon : pokemons) {
			System.out.println("Quel sera le nom de votre " + pokemon.getEspece().getNom() + " ?\nNom : ");
			String nom = sc.nextLine();
			if (nom.trim().isEmpty())
				nom = Layout.speciesToFrench(pokemon);
			pokemon.setNom(nom);
		}
	}

	/**
	 * Demande capacite boolean.
	 *
	 * @param i        the
	 * @param j        the j
	 * @param pokemons the pokemons
	 * @return the boolean
	 */
	public static boolean demandeCapacite(int i, int j, List<IPokemon> pokemons) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Quelle capacite n°" + (j + 1) + " voulez-vous donner au pokemon " + pokemons.get(i).getNom() + " ?");
		for (ICapacite c : pokemons.get(i).getEspece().getCapSet())
			System.out.print(" | " + c.getNom());
		System.out.println();
		String rep = sc.nextLine();
		if (rep != null && !rep.trim().isEmpty()) {
			try {
				pokemons.get(i).getCapacitesApprises()[j] = new Capacite(rep);
				return true;
			} catch (Exception ignored) {
			}
		}
		System.out.println("Capacite inconnue.");
		return false;
	}

	/**
	 * Creation du ranch list.
	 *
	 * @param especes the especes
	 * @return the list
	 */
	public static List<Pokemon> creationDuRanch(List<Espece> especes) {
		List<Pokemon> pokemons = new ArrayList<>(6);
		for (int i = 0; i < 6; i++) {
			boolean loop = true;
			if (i == 0)
				pokemons.add(new Pokemon(especes.get(r.nextInt(especes.size()))));
			else {
				Pokemon pokemon = null;
				while (loop) {
					pokemon = new Pokemon(especes.get(r.nextInt(especes.size())));
					String newPokemonName = pokemon.getEspece().getNom();
					int j = 0;
					while (j < pokemons.size() && !newPokemonName.equalsIgnoreCase(pokemons.get(j).getEspece().getNom()))
						j++;

					if (j == pokemons.size())
						loop = newPokemonName.equalsIgnoreCase(pokemons.get(j - 1).getEspece().getNom());
					else
						loop = newPokemonName.equalsIgnoreCase(pokemons.get(j).getEspece().getNom());
				}
				pokemons.add(pokemon);
			}
		}
		return pokemons;
	}
}