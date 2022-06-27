/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 *
 * @date IStat.java
 */
package fr.gardevoir.pokaetoxicque.interfaces;

/**
 * L'interface IStat permet de définir des objets contenants des statistiques de points d'effort.
 *
 * @author Leo Donati
 */
public interface IStat {
	/**
	 * Retourne les points de vie.
	 *
	 * @return les points de vie.
	 */
	@SuppressWarnings("UnnecessaryModifier")
	public int getPV();

	/**
	 * Modifie les points de vie.
	 *
	 * @param i les points de vie.
	 */
	void setPV(int i);

	/**
	 * Retourne la force.
	 *
	 * @return la force.
	 */
	@SuppressWarnings("UnnecessaryModifier")
	public int getForce();

	/**
	 * Modifie la force.
	 *
	 * @param i la force.
	 */
	void setForce(int i);

	/**
	 * Retourne la défense.
	 *
	 * @return la défense.
	 */
	@SuppressWarnings("UnnecessaryModifier")
	public int getDefense();

	/**
	 * Modifie la défense.
	 *
	 * @param i la défense.
	 */
	void setDefense(int i);

	/**
	 * Retourne le special.
	 *
	 * @return le special.
	 */
	@SuppressWarnings("UnnecessaryModifier")
	public int getSpecial();

	/**
	 * Modifie le special.
	 *
	 * @param i le special.
	 */
	void setSpecial(int i);

	/**
	 * Retourne la vitesse.
	 *
	 * @return la vitesse.
	 */
	@SuppressWarnings("UnnecessaryModifier")
	public int getVitesse();

	/**
	 * Modifie la vitesse.
	 *
	 * @param i la vitesse.
	 */
	void setVitesse(int i);
}
