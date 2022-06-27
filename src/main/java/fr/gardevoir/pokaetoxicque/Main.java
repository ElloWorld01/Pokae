package fr.gardevoir.pokaetoxicque;

import fr.gardevoir.pokaetoxicque.data.CalculDommage;
import fr.gardevoir.pokaetoxicque.game.pokedex.Capacite;
import fr.gardevoir.pokaetoxicque.game.pokedex.Espece;
import fr.gardevoir.pokaetoxicque.game.pokedex.Pokedex;
import fr.gardevoir.pokaetoxicque.game.pokedex.Pokemon;
import fr.gardevoir.pokaetoxicque.utils.Demonstration;

/**
 * Pokaetoxicque,
 *
 * @author @ElloWorld01, Alexandre Colin, Santiago Di Fede, Théo Séminara
 */
public class Main {

	private static Pokedex pokedex;

	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		pokedex = new Pokedex();

		if (args.length > 0 && args[0].equalsIgnoreCase("pokedex"))
			return;

		Demonstration.demonstration();
	}


	/**
	 * Gets pokedex.
	 *
	 * @return the pokedex
	 */
	public static Pokedex getPokedex() {
		return pokedex;
	}
}