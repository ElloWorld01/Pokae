/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 *
 * @date IPokedex.java
 */
package fr.gardevoir.pokaetoxicque.interfaces;

/**
 * L'interface IPokedex permet de définir un Pokedex.
 *
 * @author Leo Donati
 */
public interface IPokedex {
	/**
	 * Engendre un ranch dans une liste nommé : "pokemon [ ]".
	 *
	 * @return retourne la liste : pokemon [ ]
	 */
	@SuppressWarnings("UnnecessaryModifier")
	public IPokemon[] engendreRanch();            //Renvoie un tableau de 6 Pokemon au hasard

	/**
	 * Retourne l'info de l'espèce.
	 *
	 * @param nomEspece le nom de l'espèce.
	 * @return l 'info de l'espèce.
	 */
	@SuppressWarnings("UnnecessaryModifier")
	public IEspece getInfo(String nomEspece);    //Renvoie une instance de l'espèce de Pokemon dont on fournit le nom

	/**
	 * Retourne l' efficacité.
	 *
	 * @param attaque l' attaque
	 * @param defense la défense
	 * @return l ' efficacité.
	 */
	@SuppressWarnings("UnnecessaryModifier")
	public Double getEfficacite(IType attaque, IType defense); //Calcule l'efficacité d'un type d'attaque sur un type de cible

	/**
	 * Retourne la capacité.
	 *
	 * @param nomCapacite le nom de la capacité
	 * @return la capacité.
	 */
	@SuppressWarnings("UnnecessaryModifier")
	public ICapacite getCapacite(String nomCapacite);   //Renvoie une instance de la capacité appelée nomCapacite

	/**
	 * Retourne la capacité.
	 *
	 * @param nunCapacite le numéro de la capacité.
	 * @return la capacité.
	 */
	@SuppressWarnings("UnnecessaryModifier")
	public ICapacite getCapacite(int nunCapacite);
}
