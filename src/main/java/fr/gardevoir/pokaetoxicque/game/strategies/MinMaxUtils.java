package fr.gardevoir.pokaetoxicque.game.strategies;

import fr.gardevoir.pokaetoxicque.data.CombatData;
import fr.gardevoir.pokaetoxicque.game.combat.Dresseur;
import fr.gardevoir.pokaetoxicque.game.combat.Echange;
import fr.gardevoir.pokaetoxicque.interfaces.IAttaque;
import fr.gardevoir.pokaetoxicque.interfaces.ICapacite;
import fr.gardevoir.pokaetoxicque.interfaces.IDresseur;
import fr.gardevoir.pokaetoxicque.interfaces.IPokemon;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Min max utils.
 */
public class MinMaxUtils {

	/**
	 * Gets coup possible.
	 *
	 * @param dresseur the dresseur
	 * @param pok      the pok
	 * @param index    the index
	 * @return the coup possible
	 */
	public static IAttaque getCoupPossible(Dresseur dresseur, IPokemon pok, int index) {

		List<IAttaque> coupsPossibles = new ArrayList<>();
		for (ICapacite c : pok.getCapacitesApprises())
			if (c.getPP() > 0)
				coupsPossibles.add(c);

		if (CombatData.echangeImpossible(dresseur, pok) != null)
			return coupsPossibles.get(index);

		for (IPokemon p : getAllPokemonsAlive(dresseur))
			if (p != pok)
				coupsPossibles.add(new Echange(pok, p));

		return coupsPossibles.get(index);
	}

	/**
	 * Gets coups size.
	 *
	 * @param dresseur the dresseur
	 * @param pok      the pok adv
	 * @return the coups size
	 */
	public static int getCoupsSize(IDresseur dresseur, IPokemon pok) {
		List<IAttaque> coupsPossibles = new ArrayList<>();
		for (ICapacite c : pok.getCapacitesApprises())
			if (c.getPP() > 0)
				coupsPossibles.add(c);

		if (CombatData.echangeImpossible(dresseur, pok) != null)
			return coupsPossibles.size();

		for (IPokemon p : getAllPokemonsAlive(dresseur))
			if (p != pok)
				coupsPossibles.add(new Echange(pok, p));

		return coupsPossibles.size();
	}


	/**
	 * Gets all pokemons alive.
	 *
	 * @param dresseur the dresseur
	 * @return the all pokemons alive
	 */
	public static List<IPokemon> getAllPokemonsAlive(IDresseur dresseur) {
		List<IPokemon> pokemons = new ArrayList<>();
		for (IPokemon p : dresseur.getRanchCopy())
			if (!p.estEvanoui())
				pokemons.add(p);

		return pokemons;
	}
}