/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 *
 * @date IEspece.java
 */
package fr.gardevoir.pokaetoxicque.interfaces;

/**
 * L'interface IEspece permet de définir une espèce de pokémon.
 *
 * @author Leo Donati
 */
public interface IEspece {
	/**
	 * Retourne les bases stats de l'espèce.
	 *
	 * @return les bases stats de l'espèce.
	 */
	@SuppressWarnings("UnnecessaryModifier")
	public IStat getBaseStat();

	/**
	 * Retourne le nom de l'espèce.
	 *
	 * @return le nom de l'espèce
	 */
	@SuppressWarnings("UnnecessaryModifier")
	public String getNom();

	/**
	 * Retourne le niveau de depart de l'espèce.
	 *
	 * @return le niveau de depart de l'espèce.
	 */
	@SuppressWarnings("UnnecessaryModifier")
	public int getNiveauDepart();

	/**
	 * Retourne la base d'expérience de l'espèce.
	 *
	 * @return la base d'expérience de l'espèce.
	 */
	@SuppressWarnings("UnnecessaryModifier")
	public int getBaseExp();

	/**
	 * Retourne le gains de stat de l'espèce.
	 *
	 * @return le gains de stat de l'espèce.
	 */
	@SuppressWarnings("UnnecessaryModifier")
	public IStat getGainsStat();

	/**
	 * Retourne la liste des capacités disponibles pour cette espèce.
	 *
	 * @return la liste des capacités disponibles pour cette espèce.
	 */
	@SuppressWarnings("UnnecessaryModifier")
	public ICapacite[] getCapSet();

	/**
	 * Retourne l'evolution de l'espèce.
	 *
	 * @param niveau le niveau du pokémon.
	 * @return l 'évolution du pokémon ou "null" si aucune evolution possible.
	 */
	@SuppressWarnings("UnnecessaryModifier")
	public IEspece getEvolution(int niveau);

	/**
	 * Retourne la liste des types de l'espèce.
	 *
	 * @return la liste des types de l'espèce.
	 */
	@SuppressWarnings("UnnecessaryModifier")
	public IType[] getTypes();
}
