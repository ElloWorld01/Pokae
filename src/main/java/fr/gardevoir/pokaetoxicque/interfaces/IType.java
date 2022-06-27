/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 *
 * @date IType.java
 */
package fr.gardevoir.pokaetoxicque.interfaces;

/**
 * L'interface IType permet de définir les types de Pokémon.
 *
 * @author Leo Donati
 */
public interface IType {
	/**
	 * Retourne le nom du type.
	 *
	 * @return le nom du type.
	 */
	@SuppressWarnings("UnnecessaryModifier")
	public String getNom();
}
