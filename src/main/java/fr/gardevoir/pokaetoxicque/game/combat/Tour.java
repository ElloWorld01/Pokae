package fr.gardevoir.pokaetoxicque.game.combat;

import fr.gardevoir.pokaetoxicque.data.CombatData;
import fr.gardevoir.pokaetoxicque.interfaces.IAttaque;
import fr.gardevoir.pokaetoxicque.interfaces.ICapacite;
import fr.gardevoir.pokaetoxicque.interfaces.IPokemon;
import fr.gardevoir.pokaetoxicque.interfaces.ITour;

/**
 * Définition d'un Tour lors d'un combat pokemon
 */
public class Tour implements ITour {

	private final IPokemon pok1;
	private final IPokemon pok2;
	private final IAttaque atk1;
	private final IAttaque atk2;

	/**
	 * Instancie un nouveau Tour du comabt.
	 *
	 * @param pok1 le pokemon du dresseur1.
	 * @param atk1 l'attaque que le dresseur1 a choisis d'utiliser.
	 * @param pok2 le pokemon du dresseur2.
	 * @param atk2 l'attaque que le dresseur2 a choisis d'utiliser.
	 */
	public Tour(IPokemon pok1, IAttaque atk1, IPokemon pok2, IAttaque atk2) {
		this.pok1 = pok1;
		this.pok2 = pok2;
		this.atk1 = atk1;
		this.atk2 = atk2;
		this.commence();
	}

	@Override
	public void commence() {
		System.out.println("~~~~~ Début du tour ~~~~~");
		if (atk1 instanceof ICapacite && atk2 instanceof ICapacite) {
			if (pok1.getStat().getVitesse() > pok2.getStat().getVitesse()) {
				if (CombatData.attaquePokemon(atk1, pok1, pok2)) return;
				if (CombatData.attaquePokemon(atk2, pok2, pok1)) return;
			} else {
				if (CombatData.attaquePokemon(atk2, pok2, pok1)) return;
				if (CombatData.attaquePokemon(atk1, pok1, pok2)) return;
			}
		} else if (atk1 instanceof ICapacite) {
			if (CombatData.attaquePokemon(atk1, pok1, pok2)) return;
		} else if (atk2 instanceof ICapacite) {
			if (CombatData.attaquePokemon(atk2, pok2, pok1)) return;
		}
		System.out.println("~~~~~ Fin du tour ~~~~~");
	}
}