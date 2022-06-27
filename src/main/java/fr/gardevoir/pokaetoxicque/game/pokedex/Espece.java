package fr.gardevoir.pokaetoxicque.game.pokedex;

import fr.gardevoir.pokaetoxicque.data.EspeceData;
import fr.gardevoir.pokaetoxicque.data.StatData;
import fr.gardevoir.pokaetoxicque.interfaces.ICapacite;
import fr.gardevoir.pokaetoxicque.interfaces.IEspece;
import fr.gardevoir.pokaetoxicque.interfaces.IStat;
import fr.gardevoir.pokaetoxicque.interfaces.IType;

import java.util.HashMap;
import java.util.Objects;

/**
 * Définition d'une espèce.
 */
public class Espece implements IEspece {

	private final String nom;
	private final int id;
	/**
	 * les Capacités.
	 */
	public HashMap<ICapacite, Integer> capacites;

	/**
	 * Instancie une nouvelle espèce.
	 *
	 * @param nom le nom de l'espèce.
	 */
	public Espece(String nom) {
		this.nom = nom.toLowerCase();
		this.id = EspeceData.getId(nom);
		for (ICapacite c : EspeceData.getCapSet(nom)) {
			//TODO la clef à cet index == le niveau de base du pokemon
		}
		EspeceData.getCapSet(nom);

	}

	/**
	 * Instancie une nouvelle espèce.
	 *
	 * @param id l'id de l'espèce.
	 */
	public Espece(int id) {
		if (id < 0 || id > 151)
			throw new IllegalArgumentException("L'id doit être compris entre 0 et 151");
		this.id = id;
		this.nom = EspeceData.getName(id);
	}

	@Override
	public IStat getBaseStat() {
		return StatData.getBaseStat(nom);
	}

	@Override
	public String getNom() {
		return nom;
	}

	@Override
	public int getNiveauDepart() {
		return EspeceData.getNiveauDepartEvolution(nom);
	}

	@Override
	public int getBaseExp() {
		return EspeceData.getBaseExp(nom);
	}

	@Override
	public IStat getGainsStat() {
		return StatData.getEVStat(nom);
	}

	@Override
	public ICapacite[] getCapSet() {
		return EspeceData.getCapSet(nom);
	}

	//TODO NIVEAU ? ON MODIFIE L'INTERFACE ?
	@Override
	public IEspece getEvolution(int niveau) {
		return EspeceData.getEvolution(nom);
	}

	@Override
	public IType[] getTypes() {
		return EspeceData.getTypes(nom);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Espece espece = (Espece) o;
		return id == espece.id && Objects.equals(nom, espece.nom) && Objects.equals(capacites, espece.capacites);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nom, id, capacites);
	}
}