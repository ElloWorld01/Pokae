package fr.gardevoir.pokaetoxicque.game.combat;

import fr.gardevoir.pokaetoxicque.interfaces.IAttaque;
import fr.gardevoir.pokaetoxicque.interfaces.IEchange;
import fr.gardevoir.pokaetoxicque.interfaces.IPokemon;

/**
 * Définition d'un Echange entre le pokemon au combat et le nouveau pokemon qui va le remplacer.
 */
public class Echange implements IEchange, IAttaque {

	private final IPokemon ancienPokemon;
	private final IPokemon nouveauPokemon;


	/**
	 * Instantiates a new Echange.
	 *
	 * @param nouveauPokemon the nouveau pokemon
	 * @param ancienPokemon  the ancien pokemon
	 */
//TODO ne fonctionne pas
	public Echange(IPokemon ancienPokemon, IPokemon nouveauPokemon) {
		this.ancienPokemon = ancienPokemon;
		this.nouveauPokemon = nouveauPokemon;
	}

	@Override
	public int calculeDommage(IPokemon lanceur, IPokemon receveur) {
		return 0;
	}

	@Override
	public void utilise() {

	}

	@Override
	public void setPokemon(IPokemon pok) {

	}

	@Override
	public IPokemon echangeCombattant() {
		if (nouveauPokemon.estEvanoui()) return null;
		if (ancienPokemon == nouveauPokemon) return null;
		if (Combat.getNbPresence1().containsKey(nouveauPokemon)) {
			if (Combat.getNbPresence1().get(nouveauPokemon) >= 5) {
				System.out.println("5555555555555555555555555");
				return null;
			}
		}
		if (Combat.getNbPresence2().containsKey(nouveauPokemon)) {
			if (Combat.getNbPresence2().get(nouveauPokemon) >= 5) {
				System.out.println("5555555555555555555555555");
				return null;
			}
		}
		System.err.println(ancienPokemon.getNom() + " a été échangée pour " + nouveauPokemon.getNom());

		if (Combat.getNbPresence1().containsKey(nouveauPokemon))
			Combat.getNbPresence1().put(nouveauPokemon, Combat.getNbPresence1().get(nouveauPokemon) + 1);
		if (Combat.getNbPresence2().containsKey(nouveauPokemon))
			Combat.getNbPresence2().put(nouveauPokemon, Combat.getNbPresence2().get(nouveauPokemon) + 1);
		return nouveauPokemon;
	}
}