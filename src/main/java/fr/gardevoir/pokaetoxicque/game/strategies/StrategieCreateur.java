package fr.gardevoir.pokaetoxicque.game.strategies;

import fr.gardevoir.pokaetoxicque.interfaces.IAttaque;
import fr.gardevoir.pokaetoxicque.interfaces.IPokemon;

import java.util.Random;


/**
 * The type Strategie createur.
 */
public class StrategieCreateur extends Strategie {

	/**
	 * The R.
	 */
	Random r = new Random();

	/**
	 * Instantiates a new Strategie createur.
	 *
	 * @param ranch the ranch
	 */
	public StrategieCreateur(IPokemon[] ranch) {
		super(ranch);
	}

	@Override
	public IPokemon choisitCombattant() {
		return ranch[r.nextInt(ranch.length)];
	}

	@Override
	public IPokemon choisitCombattantContre(IPokemon pok) {
		Random r = new Random();
		return ranch[r.nextInt(ranch.length)];
	}

	@Override
	public IAttaque choisitAttaque(IPokemon attaquant, IPokemon defenseur) {
		IAttaque atkChoisie = attaquant.getCapacitesApprises()[0];
		int atk = atkChoisie.calculeDommage(attaquant, defenseur);
		for (IAttaque a : attaquant.getCapacitesApprises()) {
			if (a.calculeDommage(attaquant, defenseur) > atk)
				atkChoisie = a;
		}
		return atkChoisie;
	}

	@Override
	public void initCapacitesRanch() {

	}
}