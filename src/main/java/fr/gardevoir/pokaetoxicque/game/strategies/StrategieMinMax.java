package fr.gardevoir.pokaetoxicque.game.strategies;

import fr.gardevoir.pokaetoxicque.data.CombatData;
import fr.gardevoir.pokaetoxicque.game.combat.Dresseur;
import fr.gardevoir.pokaetoxicque.game.pokedex.Capacite;
import fr.gardevoir.pokaetoxicque.interfaces.IAttaque;
import fr.gardevoir.pokaetoxicque.interfaces.IPokemon;

import java.util.Random;

/**
 * The type Strategie min max.
 */
public class StrategieMinMax extends Strategie {
	private final Random r = new Random();

	private final Dresseur ia;
	private final Dresseur adv;

	/**
	 * Instantiates a new Strategie min max.
	 *
	 * @param ia  the ranch
	 * @param adv the ranch
	 */
	public StrategieMinMax(Dresseur ia, Dresseur adv) {
		super(ia.getRanchCopy());
		this.ia = ia;
		this.adv = adv;
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
		return minimax(attaquant, defenseur, 2).getAttaque();
	}

	@Override
	public void initCapacitesRanch() {

	}

	private EtatDuJeu minimax(IPokemon pokIA, IPokemon pokAdv, int depth) {
		long start1 = System.nanoTime();
		int etat = CombatData.etatTerminal(ia, adv);
		if (depth == 0 || etat != -1) {
			if (etat == 1)
				return new EtatDuJeu(null, (float) (-100 - depth));
			else if (etat == 2)
				return new EtatDuJeu(null, (float) (100 + depth));
			else
				return new EtatDuJeu(null, (float) 0);
		}

		float max = Float.NEGATIVE_INFINITY;
		IAttaque cmax = MinMaxUtils.getCoupPossible(ia, pokIA, 0);
		int score = 0;
		float score_save;
		int tueLePokemon;
		int coupsPossiblesIA = MinMaxUtils.getCoupsSize(ia, pokIA);
		int coupsPossiblesAdv = MinMaxUtils.getCoupsSize(ia, pokIA);


		for (int x = 0; x < coupsPossiblesIA; x++) {
			IAttaque c1 = MinMaxUtils.getCoupPossible(ia, pokIA, x);
			tueLePokemon = pokAdv.getStat().getPV() - c1.calculeDommage(pokIA, pokAdv) <= 0 ? c1.calculeDommage(pokIA, pokAdv) : 0;
			score += 100 + tueLePokemon;

			for (int y = 0; y < coupsPossiblesAdv; y++) {
				IAttaque c2 = MinMaxUtils.getCoupPossible(adv, pokAdv, y);
				tueLePokemon = pokIA.getStat().getPV() - c2.calculeDommage(pokAdv, pokIA) <= 0 ? c1.calculeDommage(pokAdv, pokIA) : 0;
				score_save = minimax(pokIA, pokAdv, depth - 1).getRecompense();
				score += score_save;

				if (score > max) {
					max = score;
					cmax = MinMaxUtils.getCoupPossible(ia, pokIA, 0);
				}

				score -= score_save;
			}

			score -= 100 + tueLePokemon;
		}

		System.out.println("StrategieMinMax TEMPS : " + (System.nanoTime() - start1) / 1000000000.0 + " s");

		if (cmax instanceof Capacite c)
			System.out.println("StrategieMinMax CAPACITE : " + c.getNom());

		return new EtatDuJeu(cmax, max);
	}
}