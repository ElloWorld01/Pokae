/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 *
 * @date IPokemon.java
 */
package fr.gardevoir.pokaetoxicque.interfaces;

/**
 * L'interface IPokemon permet de définir un pokémon.
 *
 * @author Leo Donati
 */
public interface IPokemon {
	/**
	 * Retourne les stats.
	 *
	 * @return les stat
	 */
	@SuppressWarnings("UnnecessaryModifier")
	public IStat getStat();

	/**
	 * Retourne l'experience.
	 *
	 * @return l 'experience
	 */
	@SuppressWarnings("UnnecessaryModifier")
	public double getExperience();

	/**
	 * Retourne le niveau.
	 *
	 * @return le niveau
	 */
	@SuppressWarnings("UnnecessaryModifier")
	public int getNiveau();

	/**
	 * Retourne l' id.
	 *
	 * @return l ' id
	 */
	@SuppressWarnings("UnnecessaryModifier")
	public int getId();

	/**
	 * Retourne le nom.
	 *
	 * @return le nom
	 */
	@SuppressWarnings("UnnecessaryModifier")
	public String getNom();

	/**
	 * Retourne le pourcentage pv.
	 *
	 * @return le pourcentage pv
	 */
	@SuppressWarnings("UnnecessaryModifier")
	public double getPourcentagePV();

	/**
	 * Retourne l'espece.
	 *
	 * @return l 'espece
	 */
	@SuppressWarnings("UnnecessaryModifier")
	public IEspece getEspece();

	/**
	 * Va muter en.....
	 *
	 * @param esp l'espèce
	 */
	@SuppressWarnings("UnnecessaryModifier")
	public void vaMuterEn(IEspece esp);            //Modifie l'espèce du Pokemon en esp

	/**
	 * Retourne les capacités apprises dans la liste "capacite [ ]".
	 *
	 * @return la liste capacite [ ]
	 */
	@SuppressWarnings("UnnecessaryModifier")
	public ICapacite[] getCapacitesApprises();    //Tableau des capacités que le Pokemon peut utiliser

	/**
	 * Apprendre la capacité.
	 *
	 * @param caps le caps pour apprendre la capacité
	 */
	@SuppressWarnings("UnnecessaryModifier")
	public void apprendCapacites(ICapacite[] caps);    //Enseigne les capacités au Pokemon

	/**
	 * Remplace une des capacités du pokémon par une nouvelle capacité à l'emplacement spécifié.
	 *
	 * @param i   l'emplacement de la capacité à remplacer
	 * @param cap la nouvelle capacité
	 * @throws Exception si l'emplacement spécifié est invalide
	 */
	@SuppressWarnings("UnnecessaryModifier")
	public void remplaceCapacite(int i, ICapacite cap) throws Exception;

	/**
	 * Met à jour l'expérience du pokémon suite à la défaite du pokémon adverse pok
	 *
	 * @param pok le pokémon adverse qui a été vaincu
	 */
	@SuppressWarnings("UnnecessaryModifier")
	public void gagneExperienceDe(IPokemon pok);

	/**
	 * Met à jour les stats du pokémon en tenant compte des dégats subits par l'attaque atk du pokémon adverse pok.
	 *
	 * @param pok le pokémon qui attaque le pokémon
	 * @param atk l'attaque utilisée par le pokémon attaquant
	 */
	@SuppressWarnings("UnnecessaryModifier")
	public void subitAttaqueDe(IPokemon pok, IAttaque atk);


	/**
	 * Renvoie vrai si les points de vie du pokemon sont à 0.
	 *
	 * @return vrai si les points de vie du pokemon sont à 0.
	 */
	@SuppressWarnings("UnnecessaryModifier")
	public boolean estEvanoui();

	/**
	 * Renvoie vrai si le pokémon vient de changer de niveau.
	 *
	 * @return vrai si le vient de changer de niveau.
	 */
	@SuppressWarnings("UnnecessaryModifier")
	public boolean aChangeNiveau();

	/**
	 * Renvoie vrai si le Pokemon peut muter.
	 *
	 * @return vrai si le Pokemon peut muter.
	 */
	@SuppressWarnings("UnnecessaryModifier")
	public boolean peutMuter();

	/**
	 * Remet les PV du pokémon au maximum.
	 */
	@SuppressWarnings("UnnecessaryModifier")
	public void soigne();
}
