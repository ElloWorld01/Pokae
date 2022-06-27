/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 *
 * @date ICapacite.java
 */
package fr.gardevoir.pokaetoxicque.interfaces;

/**
 * L'interface ICapacite permet de définir les capacités des Pokémon.
 *
 * @author Leo Donati Une capacité est un type d'attaque que le pokémon peut utilser.
 */
public interface ICapacite extends IAttaque {
	/**
	 * Retourne le nom de la capacité.
	 *
	 * @return le nom de la capacité.
	 */
	String getNom();

	/**
	 * Retourne la précision de la capacité.
	 *
	 * @return La précision de la capacité.
	 */
	double getPrecision();

	/**
	 * Retourne le nombre de dégats infligés par la capacité.
	 *
	 * @return le nombre de dégats infligés par la capacité.
	 */
	int getPuissance();

	/**
	 * Retourne le nombre de fois où cette capacité peut être utilisée.
	 *
	 * @return Le nombre de fois où cette capacité peut être utilisée.
	 */
	int getPP();

	/**
	 * Remet les PP au maximum de la capacité.
	 */
	void resetPP();

	/**
	 * La catégorie de la capacité (Physique ou Special)
	 *
	 * @return La catégorie de la capacité.
	 */
	ICategorie getCategorie();

	/**
	 * Type de la capacité (la liste des types est la même que pour le pokemon).
	 *
	 * @return Le type de la capacité.
	 */
	IType getType();
}
