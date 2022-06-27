package fr.gardevoir.pokaetoxicque.game.strategies;

import fr.gardevoir.pokaetoxicque.interfaces.IPokemon;
import fr.gardevoir.pokaetoxicque.interfaces.IStrategy;

/**
 * The type Strategie.
 */
public abstract class Strategie implements IStrategy {

	/**
	 * The Ranch.
	 */
	protected final IPokemon[] ranch;

	/**
	 * Instantiates a new Strategie.
	 *
	 * @param ranch the ranch
	 */
	public Strategie(IPokemon[] ranch) {
		this.ranch = ranch;
	}
}