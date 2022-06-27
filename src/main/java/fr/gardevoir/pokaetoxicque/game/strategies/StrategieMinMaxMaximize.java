package fr.gardevoir.pokaetoxicque.game.strategies;

import fr.gardevoir.pokaetoxicque.data.CombatData;
import fr.gardevoir.pokaetoxicque.game.combat.Dresseur;
import fr.gardevoir.pokaetoxicque.interfaces.IAttaque;
import fr.gardevoir.pokaetoxicque.interfaces.IDresseur;
import fr.gardevoir.pokaetoxicque.interfaces.IPokemon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StrategieMinMaxMaximize extends Strategie {
	private final Random r = new Random();

	private final Dresseur ia;
	private final Dresseur adv;


	/**
	 * Instantiates a new Strategie min max.
	 *
	 * @param ia  the ranch
	 * @param adv the ranch
	 */
	public StrategieMinMaxMaximize(Dresseur ia, Dresseur adv) {
		super(ia.getRanchCopy());
		this.ia = ia;
		this.adv = adv;
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
		return minmax(attaquant, defenseur, 2, 1).getAttaque();
	}

	@Override
	public void initCapacitesRanch() {

	}


	private EtatDuJeu minmax(IPokemon pokIA, IPokemon pokAdv, int depth, int maximize) {
		int etat = CombatData.etatTerminal(ia, adv);
		if (depth == 0 || etat != -1) {
			if (etat == 1)
				return new EtatDuJeu(null, (float) (-100 - depth));
			else if (etat == 2)
				return new EtatDuJeu(null, (float) (100 + depth));
			else
				return new EtatDuJeu(null, (float) 0);
		}


		List<IAttaque> bestActions = new ArrayList<>();
		float bestScore = (maximize == 0 ? Float.POSITIVE_INFINITY : Float.NEGATIVE_INFINITY);

		IAttaque c;
		int tueLePokemon = 0;
		float score;
		for (int x = 0; x < MinMaxUtils.getCoupsSize(getJoueur(maximize), getPokemon(maximize, pokIA, pokAdv)); x++) {
			if (maximize == 1)
				c = MinMaxUtils.getCoupPossible(ia, pokIA, x);
			else
				c = MinMaxUtils.getCoupPossible(adv, pokAdv, x);

			if (c != null)
				tueLePokemon = pokIA.getStat().getPV() - c.calculeDommage(pokAdv, pokIA) <= 0 ? 10000 : 0;
			score = minmax(pokIA, pokAdv, depth - 1, maximize == 1 ? 0 : 1).getRecompense() + tueLePokemon;

			if ((score < bestScore && maximize == 0) || (score > bestScore && maximize == 1)) {
				bestScore = score;
				bestActions.clear();
				bestActions.add(c);
			} else if (score == bestScore)
				bestActions.add(c);
		}
		return new EtatDuJeu(bestActions.get(new Random().nextInt(bestActions.size())), bestScore);
	}


	private IDresseur getJoueur(int maximize) {
		if (maximize == 1)
			return ia;
		else
			return adv;
	}

	private IPokemon getPokemon(int maximize, IPokemon pokIA, IPokemon pokAdv) {
		if (maximize == 1)
			return pokIA;
		else
			return pokAdv;
	}
}