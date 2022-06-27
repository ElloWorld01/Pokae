package fr.gardevoir.pokaetoxicque.game.pokedex;

import fr.gardevoir.pokaetoxicque.data.CalculDommage;
import fr.gardevoir.pokaetoxicque.data.CapaciteData;
import fr.gardevoir.pokaetoxicque.data.TypeData;
import fr.gardevoir.pokaetoxicque.interfaces.*;
import fr.gardevoir.pokaetoxicque.utils.PokeDatabase;
import org.json.simple.JSONObject;

import java.util.Objects;

/**
 * Définition d'une capacité.
 */
public class Capacite implements ICapacite, IAttaque {

	private final String nom;
	private final String capacitePath;
	private final double precision;
	private final int puissance;
	private final ICategorie categorie;
	private final IType type;
	private int pp;

	/**
	 * Instancie une nouvelle Capacité.
	 *
	 * @param nom le nom de la capacité.
	 */
	public Capacite(String nom) {
		this.nom = nom.toLowerCase();
		capacitePath = CapaciteData.getPath(nom);
		JSONObject json = PokeDatabase.getObject(capacitePath);
		String damageClass = CapaciteData.getDamageClass(nom, json);
		this.precision = CapaciteData.getAccuracy(json);
		this.puissance = CapaciteData.getPower(json);
		this.pp = CapaciteData.getPP(json);
		this.categorie = new Categorie(damageClass);
		this.type = TypeData.getType(json);
	}


	/**
	 * Instantiates a new Capacite.
	 *
	 * @param nom       the nom
	 * @param puissance the puissance
	 */
	public Capacite(String nom, int puissance) {
		this.nom = nom.toLowerCase();
		capacitePath = CapaciteData.getPath(nom);
		JSONObject json = PokeDatabase.getObject(capacitePath);
		String damageClass = CapaciteData.getDamageClass(nom, json);
		this.precision = CapaciteData.getAccuracy(json);
		this.puissance = puissance;
		this.pp = CapaciteData.getPP(json);
		this.categorie = new Categorie(damageClass);
		this.type = TypeData.getType(json);
	}

	@Override
	public int calculeDommage(IPokemon lanceur, IPokemon receveur) {
		return CalculDommage.calcul(lanceur, receveur, this);
	}

	@Override
	public void utilise() {
		if (pp > 0)
			pp--;
	}

	@Override
	public String getNom() {
		return nom;
	}

	@Override
	public double getPrecision() {
		return precision;
	}

	@Override
	public int getPuissance() {
		return puissance;
	}

	@Override
	public int getPP() {
		return pp;
	}

	@Override
	public void resetPP() {
		pp = Integer.parseInt(PokeDatabase.getObject(capacitePath).get("pp").toString());
	}

	@Override
	public ICategorie getCategorie() {
		return categorie;
	}

	@Override
	public IType getType() {
		return type;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Capacite capacite = (Capacite) o;
		return Double.compare(capacite.precision, precision) == 0
				&& puissance == capacite.puissance
				&& pp == capacite.pp
				&& Objects.equals(nom, capacite.nom)
				&& Objects.equals(capacitePath, capacite.capacitePath)
				&& Objects.equals(categorie, capacite.categorie)
				&& Objects.equals(type, capacite.type);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nom, capacitePath, precision, puissance, categorie, type, pp);
	}
}