/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 *
 * @date IDresseur.java
 */
package fr.gardevoir.pokaetoxicque.interfaces;

/**
 * L'interface Dresseur permet de définir un dresseur.
 *
 * @author Leo Donati
 */
public interface IDresseur {
	/**
	 * Retourne le nom du dresseur.
	 *
	 * @return le nom du dresseur.
	 */
	public String getNom();

	/**
	 * Retourne le niveau du dresseur.
	 *
	 * @return le niveau du dresseur.
	 */
	public int getNiveau();

	/**
	 * Retourne le pokemon du Ranch du dresseur.
	 *
	 * @param i le i-eme.
	 * @return le i-eme pokemon du Ranch dresseur.
	 */
	public IPokemon getPokemon(int i);

	/**
	 * Enseigne au pokemon les capacités.
	 *
	 * @param pok  le pokemon.
	 * @param caps les capacités.
	 */
	public void enseigne(IPokemon pok, ICapacite[] caps);

	/**
	 * Soigne tous les pokemons du ranch jusqu'à leur PV max.
	 */
	public void soigneRanch();

	/**
	 * Choisit le pokemon combattant.
	 *
	 * @return le pokemon
	 */
	public IPokemon choisitCombattant();

	/**
	 * Choisit le Pokemon pour combattre contre l'autre pokemon.
	 *
	 * @param pok le pokemon
	 * @return le pokemon
	 */
	public IPokemon choisitCombattantContre(IPokemon pok);

	/**
	 * Choisit l'attaque utiliser par le pokemon attaquant contre le pokemon défenseur.
	 *
	 * @param attaquant le pokemon attaquant.
	 * @param defenseur le pokemon défenseur.
	 * @return l'attaque.
	 */
	public IAttaque choisitAttaque(IPokemon attaquant, IPokemon defenseur);

	/**
	 * Récupère une copie (clone) de chaque pokemon du ranch du dresseur dans une liste [ ].
	 *
	 * @return les pokemons dans une liste [ ]
	 */
	public IPokemon[] getRanchCopy();
}
