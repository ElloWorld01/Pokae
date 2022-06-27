package fr.gardevoir.pokaetoxicque.data;

import fr.gardevoir.pokaetoxicque.game.combat.Dresseur;
import fr.gardevoir.pokaetoxicque.game.combat.Echange;
import fr.gardevoir.pokaetoxicque.game.pokedex.Capacite;
import fr.gardevoir.pokaetoxicque.interfaces.IAttaque;
import fr.gardevoir.pokaetoxicque.interfaces.IDresseur;
import fr.gardevoir.pokaetoxicque.interfaces.IPokemon;
import fr.gardevoir.pokaetoxicque.utils.FinDuCombat;
import fr.gardevoir.pokaetoxicque.utils.Layout;

import java.util.Arrays;

/**
 * Le type de données de la classe Combat.
 */
public class CombatData {

	/**
	 * Retourne le dresseur qui a gagné le combat.
	 *
	 * @param dresseur1 le dresseur1 du combat.
	 * @param dresseur2 le dresseur2 du combat.
	 * @return le dresseur1 ou le dresseur2 du combat.
	 */
	public static FinDuCombat getWinner(Dresseur dresseur1, Dresseur dresseur2) {

		if (isRanchEvanoui(dresseur1))
			return FinDuCombat.DRESSEUR2;
		if (isRanchEvanoui(dresseur2))
			return FinDuCombat.DRESSEUR1;

		return FinDuCombat.CONTINUE;
	}

	/**
	 * Etat terminal int.
	 *
	 * @param dresseur1 the dresseur 1
	 * @param dresseur2 the dresseur 2
	 * @return the int
	 */
	public static int etatTerminal(Dresseur dresseur1, Dresseur dresseur2) {

		if (isRanchEvanoui(dresseur1))
			return 1;
		if (isRanchEvanoui(dresseur2))
			return 2;

		return -1;
	}

	/**
	 * Renvoie "Vrai" si tous les pokemons du ranch du dresseur sont évanouis , sinon renvoie "Faux".
	 *
	 * @param dresseur le dresseur du ranch.
	 * @return Vrai ou Faux
	 */
	public static boolean isRanchEvanoui(Dresseur dresseur) {
		int count = 0;
		for (int i = 0; i < 6; i++) {
			if (dresseur.getPokemon(i).estEvanoui()) {
				count++;
			}
		}
		return count == 6;
	}


	public static IPokemon echangeImpossible(IDresseur dresseur, IPokemon pok) {
		IPokemon pokemonPasEvanoui = Arrays.stream(dresseur.getRanchCopy()).filter(p -> !p.estEvanoui()).findFirst().orElse(null);
		if (Arrays.stream(dresseur.getRanchCopy()).filter(IPokemon::estEvanoui).count() >= 5 && pokemonPasEvanoui == pok) {
			return pok;
		}
		return null;
	}


	/**
	 * Retourne le nouveau pokemon envoyé de force par le dresseur.
	 *
	 * @param dresseur1 le dresseur forcé d'échanger.
	 * @param pok       the pok
	 * @return le pokemon remplaçant.
	 */
	public static IPokemon forceEchange(Dresseur dresseur1, IPokemon pok) {
		IPokemon pokemonRemplacant = echangeImpossible(dresseur1, pok);
		if (pokemonRemplacant != null) {
			System.out.println("échange impossible.");
			return pokemonRemplacant;
		}
		pokemonRemplacant = echange(dresseur1, pok);
		if (pokemonRemplacant != null) {
			System.out.println("Le pokemon " + Layout.speciesToFrench(pok)
					+ " est remplacé de force par " + Layout.speciesToFrench(pokemonRemplacant));
		}
		return pokemonRemplacant;
	}

	private static IPokemon echange(Dresseur dresseur1, IPokemon pok) {
		IPokemon pokemonRemplacant = echangeImpossible(dresseur1, pok);
		if (pokemonRemplacant != null) {
			System.out.println("échange impossible.");
			return pokemonRemplacant;
		}

		while (pokemonRemplacant == null)
			pokemonRemplacant = new Echange(pok, dresseur1.getStrategy().choisitCombattant()).echangeCombattant();

		return pokemonRemplacant;
	}

	/**
	 * Renvoie "Vrai" si le pokemon qui utilise l'attaque met KO le pokemon qui subit l'attaque,
	 * sinon renvoie "Faux.
	 *
	 * @param atk      l'attaque utilisée.
	 * @param lanceur  le pokemon qui utilise l'attaque.
	 * @param receveur le pokemon qui subit l'attaque.
	 * @return Vrai ou Faux.
	 */
	public static boolean attaquePokemon(IAttaque atk, IPokemon lanceur, IPokemon receveur) {
		if (atk == null) return false;
		if (atk instanceof Capacite c) {

			receveur.subitAttaqueDe(lanceur, atk);
			atk.utilise();
			System.out.println(Layout.speciesToFrench(lanceur)
					+ " utilise " + Layout.moveToFrench(c)
					+ " sur " + Layout.speciesToFrench(receveur));
			System.out.println(Layout.speciesToFrench(receveur) + " a " +
					Math.round(receveur.getPourcentagePV()) + "% de ces pv\n");
			return receveur.estEvanoui();
		}
		return false;
	}
}