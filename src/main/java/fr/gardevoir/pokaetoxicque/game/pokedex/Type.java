package fr.gardevoir.pokaetoxicque.game.pokedex;

import fr.gardevoir.pokaetoxicque.interfaces.IType;

/**
 * Définition des types des pokémons et des capacités.
 */
public enum Type implements IType {
	/**
	 * Fighting type.
	 */
	FIGHTING,
	/**
	 * Dragon type.
	 */
	DRAGON,
	/**
	 * Water type.
	 */
	WATER,
	/**
	 * Electric type.
	 */
	ELECTRIC,
	/**
	 * Fire type.
	 */
	FIRE,
	/**
	 * Ice type.
	 */
	ICE,
	/**
	 * Bug type.
	 */
	BUG,
	/**
	 * Normal type.
	 */
	NORMAL,
	/**
	 * Grass type.
	 */
	GRASS,
	/**
	 * Poison type.
	 */
	POISON,
	/**
	 * Psychic type.
	 */
	PSYCHIC,
	/**
	 * Rock type.
	 */
	ROCK,
	/**
	 * Ground type.
	 */
	GROUND,
	/**
	 * Ghost type.
	 */
	GHOST,
	/**
	 * Flying type.
	 */
	FLYING;

	@Override
	public String getNom() {
		return name().substring(0, 1).toUpperCase() + name().substring(1).toLowerCase();
	}
}