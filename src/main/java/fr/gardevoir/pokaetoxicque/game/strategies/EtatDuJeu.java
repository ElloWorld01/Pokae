package fr.gardevoir.pokaetoxicque.game.strategies;

import fr.gardevoir.pokaetoxicque.interfaces.IAttaque;

/**
 * The type Etat du jeu.
 */
public class EtatDuJeu {

	/**
	 * The Attaque.
	 */
	IAttaque attaque;
	/**
	 * The Max.
	 */
	Float recompense;

	/**
	 * Instantiates a new Etat du jeu.
	 *
	 * @param attaque the attaque
	 * @param recompense     the max
	 */
	public EtatDuJeu(IAttaque attaque, Float recompense) {
		this.attaque = attaque;
		this.recompense = recompense;
	}

	/**
	 * Gets attaque.
	 *
	 * @return the attaque
	 */
	public IAttaque getAttaque() {
		return attaque;
	}

	/**
	 * Gets max.
	 *
	 * @return the max
	 */
	public Float getRecompense() {
		return recompense;
	}

	/**
	 * Sets attaque.
	 *
	 * @param attaque the attaque
	 */
	public void setAttaque(IAttaque attaque) {
		this.attaque = attaque;
	}

	/**
	 * Sets max.
	 *
	 * @param recompense the max
	 */
	public void setRecompense(Float recompense) {
		this.recompense = recompense;
	}
}