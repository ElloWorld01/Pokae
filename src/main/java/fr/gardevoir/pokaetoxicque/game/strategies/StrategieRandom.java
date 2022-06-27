package fr.gardevoir.pokaetoxicque.game.strategies;

import fr.gardevoir.pokaetoxicque.data.PokedexData;
import fr.gardevoir.pokaetoxicque.interfaces.IAttaque;
import fr.gardevoir.pokaetoxicque.interfaces.IPokemon;

import java.util.Random;


/**
 * The type Strategie random.
 */
public class StrategieRandom extends Strategie {

	private final Random r = new Random();

	/**
	 * Instantiates a new Strategie random.
	 *
	 * @param ranch the ranch
	 */
	public StrategieRandom(IPokemon[] ranch) {
		super(ranch);
	}

	@Override
	public IPokemon choisitCombattant() {
		IPokemon remplacant = ranch[r.nextInt(ranch.length)];
		while (remplacant.estEvanoui())
			remplacant = ranch[r.nextInt(ranch.length)];
		return remplacant;
	}

	@Override
	public IPokemon choisitCombattantContre(IPokemon pok) {
		return choisitCombattant();
	}

	@Override
	public IAttaque choisitAttaque(IPokemon attaquant, IPokemon defenseur) {
		Random r = new Random();
		return attaquant.getCapacitesApprises()[r.nextInt(attaquant.getCapacitesApprises().length)];
	}

	@Override
	public void initCapacitesRanch() {
		PokedexData.engendreRanchRandom();
	}
}