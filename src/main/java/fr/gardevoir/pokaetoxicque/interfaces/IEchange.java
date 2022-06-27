/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 *
 * @date IEchange.java
 */
package fr.gardevoir.pokaetoxicque.interfaces;

/**
 * L'interface IEchange permet de définir les échanges en combat.
 *
 * @author Leo Donati C'est un autre type d'attaque Correspond à l'échange du Pokemon du combat avec un autre Pokemon du ranch
 */
public interface IEchange extends IAttaque {
	/**
	 * On prend le pokemon.
	 *
	 * @param pok le pokemon
	 */
	@SuppressWarnings("UnnecessaryModifier")
	public void setPokemon(IPokemon pok); //choisit le Pokemon remplaçant

	//TODO justifier le changement de cette méthode

	/**
	 * On Echange le combattant pokemon.
	 *
	 * @return le pokemon remplaçant.
	 */
	@SuppressWarnings("UnnecessaryModifier")
	public IPokemon echangeCombattant();  //active le remplacement (et renvoie l'ancien pokemon)
}
