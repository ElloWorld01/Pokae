/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 *
 * @date ICombat.java
 */
package fr.gardevoir.pokaetoxicque.interfaces;


/**
 * L'interface ICombat permet de définir les combats entre les dresseurs.
 *
 * @author Leo Donati
 */
public interface ICombat {
	/**
	 * On Commence le combat .
	 */
	@SuppressWarnings("UnnecessaryModifier")
	public void commence();

	/**
	 * On prend le dresseur 1.
	 *
	 * @return le dresseur 1
	 */
	@SuppressWarnings("UnnecessaryModifier")
	public IDresseur getDresseur1();

	/**
	 * On prend dresseur 2.
	 *
	 * @return le dresseur 2
	 */
	@SuppressWarnings("UnnecessaryModifier")
	public IDresseur getDresseur2();

	/**
	 * Nouveau tour du combat.
	 *
	 * @param pok1 le pokemon du dresseur 1
	 * @param atk1 l' attaque choisi du pokemon du dresseur 1
	 * @param pok2 le pokemon du dresseur 2
	 * @param atk2 l' attaque choisi du pokemon du dresseur 2
	 * @return le tour
	 */
	@SuppressWarnings("UnnecessaryModifier")
	public ITour nouveauTour(IPokemon pok1, IAttaque atk1, IPokemon pok2, IAttaque atk2);

	/**
	 * On Termine le combat.
	 */
	@SuppressWarnings("UnnecessaryModifier")
	public void termine();
}
