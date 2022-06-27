/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 *
 * @date IAttaque.java
 */
package fr.gardevoir.pokaetoxicque.interfaces;

/**
 * L'interface IAttaque permet de définir les méthodes d'une attaque.
 *
 * @author Leo Donati Une attaque est une action du Pokemon durant une bataille. Il y a deux types d'attaques : - les capacités (interface ICapacity) - les échanges (interface IEchange)
 */
public interface IAttaque {

	/**
	 * Renvoie le nombre de points de vie qu'il faut enlever au receveur.
	 *
	 * @param lanceur  Le lanceur  : celui qui va envoyer l'attaque
	 * @param receveur Le receveur : celui qui va subir l'attaque
	 * @return nombre de points de vie qu'il faut enlever au receveur
	 */
	int calculeDommage(IPokemon lanceur, IPokemon receveur);

	/**
	 * Fait diminuer de 1 le nombre restant de fois où l'attaque peut être utilisée
	 */
	void utilise();
}
