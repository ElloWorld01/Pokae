package fr.gardevoir.pokaetoxicque.game.combat;

import fr.gardevoir.pokaetoxicque.game.strategies.StrategieRandom;
import fr.gardevoir.pokaetoxicque.interfaces.*;

import java.util.Arrays;
import java.util.Objects;

/**
 * The type Dresseur.
 */
public class Dresseur implements IDresseur {
	/**
	 * Le Nom.
	 */
	protected final String name;
	/**
	 * Le Ranch.
	 */
	protected final IPokemon[] ranch;
	private IStrategy strategy;

	/**
	 * Instancie un nouveau Dresseur.
	 *
	 * @param name  le nom du dresseur.
	 * @param ranch le ranch du dresseur.
	 */
	public Dresseur(String name, IPokemon[] ranch) {
		this.name = name;
		this.ranch = ranch;
		this.strategy = new StrategieRandom(ranch);
	}

	/**
	 * Instantiates a new Dresseur.
	 *
	 * @param name     the name
	 * @param ranch    the ranch
	 * @param strategy the strategy
	 */
	public Dresseur(String name, IPokemon[] ranch, IStrategy strategy) {
		this.name = name;
		this.ranch = ranch;
		this.strategy = strategy;
	}

	/**
	 * Sets strategy.
	 *
	 * @param strategy the strategy
	 */
	public void setStrategy(IStrategy strategy) {
		this.strategy = strategy;
	}


	@Override
	public void enseigne(IPokemon pok, ICapacite[] caps) {
		pok.apprendCapacites(caps);
	}

	@Override
	public void soigneRanch() {
		Arrays.stream(ranch).forEach(IPokemon::soigne);
	}

	@Override
	public IPokemon choisitCombattant() {
		return strategy.choisitCombattant();
	}

	@Override
	public IPokemon choisitCombattantContre(IPokemon pok) {
		return strategy.choisitCombattantContre(pok);
	}

	@Override
	public IAttaque choisitAttaque(IPokemon attaquant, IPokemon defenseur) {
		return strategy.choisitAttaque(attaquant, defenseur);
	}

	@Override
	public int getNiveau() {
		return Arrays.stream(ranch).mapToInt(IPokemon::getNiveau).sum();
	}

	@Override
	public String getNom() {
		return name;
	}

	@Override
	public IPokemon getPokemon(int i) {
		return ranch[i];
	}

	@Override
	public IPokemon[] getRanchCopy() {
		return Arrays.copyOf(ranch, ranch.length);
	}

	/**
	 * Gets strategy.
	 *
	 * @return the strategy
	 */
	public IStrategy getStrategy() {
		return strategy;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Dresseur dresseur = (Dresseur) o;
		return Objects.equals(name, dresseur.name) && Arrays.equals(ranch, dresseur.ranch);
	}

	@Override
	public int hashCode() {
		int result = Objects.hash(name);
		result = 31 * result + Arrays.hashCode(ranch);
		return result;
	}
}