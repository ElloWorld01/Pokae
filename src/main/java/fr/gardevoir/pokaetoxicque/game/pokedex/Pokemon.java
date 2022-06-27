package fr.gardevoir.pokaetoxicque.game.pokedex;

import fr.gardevoir.pokaetoxicque.data.EspeceData;
import fr.gardevoir.pokaetoxicque.data.PokedexData;
import fr.gardevoir.pokaetoxicque.game.combat.Combat;
import fr.gardevoir.pokaetoxicque.interfaces.*;

import java.util.Arrays;
import java.util.Objects;

/**
 * Définition d'un pokémon.
 */
public class Pokemon implements IPokemon {

	private IEspece espece;
	private String nom;
	private IStat statsActuelles;
	private double experience;
	private boolean estEvanoui;
	private boolean aChangeDeNiveau;
	private ICapacite[] capacitesApprises = new ICapacite[4];
	private int niveau;
	private IStat statsEvADonner;
	private IStat statsEvAccumules;

	/**
	 * Instancie un nouveau pokémon.
	 *
	 * @param espece l'espèce du pokémon.
	 */
	public Pokemon(IEspece espece) {
		niveau = 1;
		this.espece = espece;
		nom = espece.getNom();
		statsActuelles = espece.getBaseStat();
		statsEvADonner = espece.getGainsStat();
		statsEvAccumules = new Stat(0, 0, 0, 0, 0);
		//TODO Formules PV calculées par une autre formule
		//◄ Précédent: 4. EVSuivant: 6. Stats ►
		//5. Points de Vie
		PokedexData.donneCapacites(this);
	}

	/**
	 * Instancie un nouveau pokémon.
	 *
	 * @param espece l'espèce du pokémon.
	 * @param nom    le nom personnalisé du pokémon.
	 */
	public Pokemon(IEspece espece, String nom) {
		this.espece = espece;
		this.nom = nom;
		statsActuelles = espece.getBaseStat();
		statsEvADonner = espece.getGainsStat();
		statsEvAccumules = new Stat(0, 0, 0, 0, 0);
		PokedexData.donneCapacites(this);
	}

	private double experiencePourMonterDeNiveau() {
		return 0.8 * (Math.pow(niveau + 1, 3));
	}

	@Override
	public IStat getStat() {
		return statsActuelles;
	}

	@Override
	public double getExperience() {
		return experience;
	}

	@Override
	public int getNiveau() {
		return niveau;
	}

	@Override
	public int getId() {
		return EspeceData.getId(nom);
	}

	@Override
	public String getNom() {
		return nom;
	}

	/**
	 * Défini le nom.
	 *
	 * @param nom le nom.
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public double getPourcentagePV() {
		return (double) Math.round(statsActuelles.getPV() * 100) / espece.getBaseStat().getPV();
	}

	@Override
	public IEspece getEspece() {
		return espece;
	}

	@Override
	public void vaMuterEn(IEspece espece) {
		if (this.peutMuter() && !Combat.isCombatEnCours()) {
			this.espece = espece;
			statsActuelles = new Stat(
					espece.getBaseStat().getPV() + statsEvAccumules.getPV(),
					espece.getBaseStat().getForce() + statsEvAccumules.getForce(),
					espece.getBaseStat().getDefense() + statsEvAccumules.getDefense(),
					espece.getBaseStat().getSpecial() + statsEvAccumules.getSpecial(),
					espece.getBaseStat().getVitesse() + statsEvAccumules.getVitesse()
			);
			statsEvADonner = espece.getGainsStat();
		}
	}

	@Override
	public ICapacite[] getCapacitesApprises() {
		return capacitesApprises;
	}

	/**
	 * Retournes les capacités apprises.
	 *
	 * @param index l'index
	 * @return les capacités apprises
	 */
	public ICapacite getCapacitesApprises(int index) {
		return capacitesApprises[index];
	}

	@Override
	public void apprendCapacites(ICapacite[] caps) {
		if (capacitesApprises == null || capacitesApprises.length != 4)
			throw new IllegalArgumentException("Il faut 4 capacités pour changer les capacités d'un pokémon");
		if (Arrays.stream(caps).distinct().count() != 4)
			throw new IllegalArgumentException("Il faut 4 capacités différentes pour changer les capacités d'un pokémon");

		capacitesApprises = caps;
	}

	@Override
	public void remplaceCapacite(int numeroCapacite, ICapacite cap) {
		if (capacitesApprises.length <= numeroCapacite || numeroCapacite < 0)
			throw new IndexOutOfBoundsException("Capacité non trouvée");

		capacitesApprises[numeroCapacite] = cap;
	}

	@Override
	public void gagneExperienceDe(IPokemon pok) {
		experience += (1.5 * pok.getNiveau() * pok.getEspece().getBaseExp()) / 7;
		statsEvAccumules = new Stat(
				statsEvAccumules.getPV() + pok.getEspece().getGainsStat().getPV(),
				statsEvAccumules.getForce() + pok.getEspece().getGainsStat().getForce(),
				statsEvAccumules.getDefense() + pok.getEspece().getGainsStat().getDefense(),
				statsEvAccumules.getSpecial() + pok.getEspece().getGainsStat().getSpecial(),
				statsEvAccumules.getVitesse() + pok.getEspece().getGainsStat().getVitesse());
		statsActuelles = new Stat(
				statsActuelles.getPV() + pok.getEspece().getGainsStat().getPV(),
				statsActuelles.getForce() + pok.getEspece().getGainsStat().getForce(),
				statsActuelles.getDefense() + pok.getEspece().getGainsStat().getDefense(),
				statsActuelles.getSpecial() + pok.getEspece().getGainsStat().getSpecial(),
				statsActuelles.getVitesse() + pok.getEspece().getGainsStat().getVitesse());
		if (experiencePourMonterDeNiveau() <= experience) {
			experience = 0 + (experience - experiencePourMonterDeNiveau());
			niveau++;
			aChangeDeNiveau = true;
			vaMuterEn(EspeceData.getEvolution(espece.getNom()));
		} else {
			aChangeDeNiveau = false;
		}
	}

	@Override
	public void subitAttaqueDe(IPokemon pok, IAttaque atk) {
		statsActuelles.setPV(statsActuelles.getPV() - atk.calculeDommage(pok, this));
		if (statsActuelles.getPV() <= 0) {
			estEvanoui = true;
			statsActuelles.setPV(0);
		}
	}

	@Override
	public boolean estEvanoui() {
		return statsActuelles.getPV() <= 0;
	}

	@Override
	public boolean aChangeNiveau() {
		return aChangeDeNiveau;
	}

	@Override
	public boolean peutMuter() {
		return aChangeDeNiveau
				&& EspeceData.getEvolution(espece.getNom()) != null
				&& niveau >= EspeceData.getNiveauDepartEvolution(espece.getNom());
	}

	@Override
	public void soigne() {
		statsActuelles.setPV(espece.getBaseStat().getPV());
		estEvanoui = false;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Pokemon pokemon = (Pokemon) o;
		return Double.compare(pokemon.experience, experience) == 0
				&& estEvanoui == pokemon.estEvanoui
				&& aChangeDeNiveau == pokemon.aChangeDeNiveau
				&& niveau == pokemon.niveau
				&& Objects.equals(espece, pokemon.espece)
				&& Objects.equals(nom, pokemon.nom)
				&& Objects.equals(statsActuelles, pokemon.statsActuelles)
				&& Arrays.equals(capacitesApprises, pokemon.capacitesApprises)
				&& Objects.equals(statsEvADonner, pokemon.statsEvADonner)
				&& Objects.equals(statsEvAccumules, pokemon.statsEvAccumules);
	}

	@Override
	public int hashCode() {
		int result = Objects.hash(espece, nom, statsActuelles, experience, estEvanoui, aChangeDeNiveau, niveau, statsEvADonner, statsEvAccumules);
		result = 31 * result + Arrays.hashCode(capacitesApprises);
		return result;
	}
}