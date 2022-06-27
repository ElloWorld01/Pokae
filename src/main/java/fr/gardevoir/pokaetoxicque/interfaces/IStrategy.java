/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 *
 * @date IStrategy.java
 */
package fr.gardevoir.pokaetoxicque.interfaces;

/**
 * The interface Strategy.
 *
 * @author Leo Donati Une stratégie est utilisée par les dresseurs non humains (IA) pour prendre les décisions  Un DresseurIA possède une référence sur une IStrategy à qui il délègue la prise de décision Un dresseur humain n'utilise pas IStrategy Chaque méthode de IStrategy correspond à la méthode homonyme de IDresseur
 */
public interface IStrategy {
	/**
	 * Choisit combattant pokemon.
	 *
	 * @return the pokemon
	 */
	public IPokemon choisitCombattant();

	/**
	 * Choisit combattant contre pokemon.
	 *
	 * @param pok the pok
	 * @return the pokemon
	 */
	public IPokemon choisitCombattantContre(IPokemon pok);

	/**
	 * Choisit attaque attaque.
	 *
	 * @param attaquant the attaquant
	 * @param defenseur the defenseur
	 * @return the attaque
	 */
	public IAttaque choisitAttaque(IPokemon attaquant, IPokemon defenseur);

	/**
	 * Init capacites ranch.
	 */
	public void initCapacitesRanch();   // méthode qui choisit les capacités à enseigner à chaque Pokemon du Ranch
}
