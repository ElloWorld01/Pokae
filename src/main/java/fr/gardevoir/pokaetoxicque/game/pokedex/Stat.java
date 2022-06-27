package fr.gardevoir.pokaetoxicque.game.pokedex;

import fr.gardevoir.pokaetoxicque.interfaces.IStat;

import java.util.Objects;

/**
 * Définition d'un objet contenant des statistiques de points d'effort.
 */
public class Stat implements IStat {
	private int pointsDeVie;
	private int attaque;
	private int defense;
	private int special;
	private int vitesse;

	/**
	 * Instancie un nouvel objet contenant des statistiques.
	 *
	 * @param hp      les points de vie
	 * @param attack  l'attaque
	 * @param defense la défense
	 * @param special le spécial
	 * @param speed   la vitesse
	 */
	public Stat(int hp, int attack, int defense, int special, int speed) {
		this.pointsDeVie = hp;
		this.attaque = attack;
		this.defense = defense;
		this.special = special;
		this.vitesse = speed;
	}

	@Override
	public int getPV() {
		return pointsDeVie;
	}

	@Override
	public void setPV(int pointsDeVie) {
		this.pointsDeVie = pointsDeVie;
	}

	@Override
	public int getForce() {
		return attaque;
	}

	@Override
	public void setForce(int attaque) {
		this.attaque = attaque;
	}

	@Override
	public int getDefense() {
		return defense;
	}

	@Override
	public void setDefense(int defense) {
		this.defense = defense;
	}

	@Override
	public int getSpecial() {
		return special;
	}

	@Override
	public void setSpecial(int special) {
		this.special = special;
	}

	@Override
	public int getVitesse() {
		return vitesse;
	}

	@Override
	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Stat stat = (Stat) o;
		return pointsDeVie == stat.pointsDeVie
				&& attaque == stat.attaque
				&& defense == stat.defense
				&& special == stat.special
				&& vitesse == stat.vitesse;
	}

	@Override
	public int hashCode() {
		return Objects.hash(pointsDeVie, attaque, defense, special, vitesse);
	}
}