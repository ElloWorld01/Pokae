package fr.gardevoir.pokaetoxicque.utils;

import fr.gardevoir.pokaetoxicque.interfaces.IPokemon;

/**
 * The type Messages.
 */
public class Messages {


	/**
	 * List ranch.
	 *
	 * @param ranch the ranch
	 */
	public static void listRanch(IPokemon[] ranch) {
		for (int i = 0; i < ranch.length; i++) {
			System.out.println((i+1) + " : " + (ranch[i].estEvanoui() ? "Evanoui" : ranch[i].getNom()));
		}
	}
}