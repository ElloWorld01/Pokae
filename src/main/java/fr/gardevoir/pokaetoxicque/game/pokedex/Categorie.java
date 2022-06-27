package fr.gardevoir.pokaetoxicque.game.pokedex;

import fr.gardevoir.pokaetoxicque.interfaces.ICategorie;

/**
 * Définition d'une catégorie.
 */
public record Categorie(String nom) implements ICategorie {

	/**
	 * Instancie une nouvelle Catégorie.
	 *
	 * @param nom le nom de la nouvelle catégorie.
	 */
	public Categorie {
		if (!nom.equalsIgnoreCase("special") && !nom.equalsIgnoreCase("physical"))
			throw new IllegalArgumentException("La catégorie " + nom + " n'est pas valide.");
	}

	@Override
	public boolean isSpecial() {
		return nom.equalsIgnoreCase("special");
	}
}