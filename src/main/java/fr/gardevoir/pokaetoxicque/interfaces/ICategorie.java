/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 *
 * @date ICategory.java
 */
package fr.gardevoir.pokaetoxicque.interfaces;

/**
 * L'interface ICategory permet de définir les méthodes d'une catégorie.
 *
 * @author Leo Donati Il s'agit de la catégorie d'une capacité : - soit Physique - soit Special
 */
public interface ICategorie {
	/**
	 * Si la catégorie de la capacité est de type special renvoie Vrai , sinon Faux (boolean).
	 *
	 * @return Vrai ou Faux.
	 */
	boolean isSpecial();

	/**
	 * On obtiens le nom de la capacité.
	 *
	 * @return le nom de la capcité
	 */
	String nom();
}
