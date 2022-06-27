package fr.gardevoir.pokaetoxicque.game.combat;

import fr.gardevoir.pokaetoxicque.data.CalculDommage;
import fr.gardevoir.pokaetoxicque.data.CombatData;
import fr.gardevoir.pokaetoxicque.data.EspeceData;
import fr.gardevoir.pokaetoxicque.game.pokedex.Capacite;
import fr.gardevoir.pokaetoxicque.interfaces.*;
import fr.gardevoir.pokaetoxicque.utils.FinDuCombat;

import java.util.HashMap;
import java.util.Random;

/**
 * Définition d'un Combat pokemon entre deux dresseurs
 */
public class Combat implements ICombat {

	private static boolean combatEnCours = false;
	private final Dresseur dresseur1;
	private final Dresseur dresseur2;
	/**
	 * le R.
	 */
	Random r = new Random();
	/**
	 * The Bide counter.
	 */
	int bideCounter = r.nextInt(r.nextInt(2) == 1 ? 2 : 3);
	/**
	 * The Bide count.
	 */
	HashMap<IPokemon, Integer> bideCount = new HashMap<>();
	/**
	 * The Bide damage.
	 */
	HashMap<IPokemon, Integer> bideDamage = new HashMap<>();
	private static HashMap<IPokemon, Integer> nbPresence1 = new HashMap<>();
	private static HashMap<IPokemon, Integer> nbPresence2 = new HashMap<>();

	private void initNbPresence() {
		for (IPokemon p : dresseur1.getRanchCopy()) {
			nbPresence1.put(p, 0);

		}
		for (IPokemon p : dresseur2.getRanchCopy()) {
			nbPresence2.put(p, 0);
		}
	}

	/**
	 * Instancie un nouveau Combat.
	 *
	 * @param dresseur1 le premier dresseur pour le combat.
	 * @param dresseur2 le deuxième dresseur pour le combat.
	 */
	public Combat(Dresseur dresseur1, Dresseur dresseur2) {
		this.dresseur1 = dresseur1;
		this.dresseur2 = dresseur2;
		initNbPresence();
	}

	IPokemon p1;
	IPokemon p2;

	/**
	 * Is combat en cours boolean.
	 *
	 * @return the boolean
	 */
	public static boolean isCombatEnCours() {
		return combatEnCours;
	}

	@Override
	public void commence() {
		if (combatEnCours) return;
		combatEnCours = true;
		dresseur1.getStrategy().initCapacitesRanch();
		dresseur2.getStrategy().initCapacitesRanch();

		//TODO TJRS LE MEME CHENAPAN
		p1 = dresseur1.choisitCombattant();
		p2 = dresseur2.choisitCombattant();
		System.out.println("~~~~~DEBUT DU COMBAT~~~~~");
		while (combatEnCours) {
			nouveauTour(
					p1,
					dresseur1.choisitAttaque(p1, p2),
					p2,
					dresseur2.choisitAttaque(p2, p1)
			);
		}
	}

	@Override
	public IDresseur getDresseur1() {
		return dresseur1;
	}

	@Override
	public IDresseur getDresseur2() {
		return dresseur2;
	}

	@Override
	public ITour nouveauTour(IPokemon pok1, IAttaque atk1, IPokemon pok2, IAttaque atk2) {

		IAttaque newAtk1 = atk1;
		IAttaque newAtk2 = atk2;

		if (isBide(newAtk1)) newAtk1 = bide(atk1, atk2, pok1, pok2);
		if (isBide(newAtk2)) newAtk2 = bide(atk2, atk1, pok2, pok1);
		if (isCounter(newAtk1)) newAtk1 = counter(atk1, atk2, pok1, pok2);
		if (isCounter(newAtk2)) newAtk2 = counter(atk2, atk1, pok2, pok1);

		if (atk1 == null)
			forfait(dresseur1);

		if (atk2 == null)
			forfait(dresseur2);

		//TODO IMPORTANT POUVOIR CHANGER LES CAPACITES EN PLEIN COMBAT
		termine();
		if (combatEnCours) {
			if (pok1.estEvanoui()) {
				IPokemon p = CombatData.forceEchange(dresseur1, pok1);
				pok1 = p == null ? pok1 : p;
			}
			if (pok2.estEvanoui()) {
				IPokemon p = CombatData.forceEchange(dresseur2, pok2);
				pok2 = p == null ? pok2 : p;
			}
			IPokemon newPok1 = null;
			IPokemon newPok2 = null;
			if (atk1 instanceof Echange e)
				newPok1 = e.echangeCombattant();
			if (atk2 instanceof Echange e)
				newPok2 = e.echangeCombattant();

			pok1 = (newPok1 == null ? pok1 : newPok1);
			pok2 = (newPok2 == null ? pok2 : newPok2);

			p1 = pok1;
			p2 = pok2;
			return new Tour(pok1, newAtk1, pok2, newAtk2);
		}
		return null;
	}

	@Override
	public void termine() {
		FinDuCombat winner = CombatData.getWinner(dresseur1, dresseur2);
		if (winner.equals(FinDuCombat.CONTINUE))
			return;
		System.out.println("~~~~~FIN DU COMBAT~~~~~");
		combatEnCours = false;
		String victoryAnnouncement = "Le match entre " + dresseur1.getNom() + " et " + dresseur2.getNom()
				+ " est remporté par %s !";

		//TODO répétion de code
		if (winner.equals(FinDuCombat.DRESSEUR1)) {
			System.out.println(victoryAnnouncement.replace("%s", dresseur1.getNom()));
			for (int i = 0; i < 6; i++) {
				if (!dresseur1.getPokemon(i).estEvanoui())
					dresseur1.getPokemon(i).vaMuterEn(EspeceData.getEvolution(dresseur1.getPokemon(i).getEspece().getNom()));
			}

		} else if (winner.equals(FinDuCombat.DRESSEUR2)) {
			System.out.println(victoryAnnouncement.replace("%s", dresseur2.getNom()));
			for (int i = 0; i < 6; i++) {
				if (!dresseur2.getPokemon(i).estEvanoui())
					dresseur2.getPokemon(i).vaMuterEn(EspeceData.getEvolution(dresseur2.getPokemon(i).getEspece().getNom()));
			}
		}

		dresseur1.soigneRanch();
		dresseur2.soigneRanch();
	}

	/**
	 * Is counter boolean.
	 *
	 * @param atk l'attaque
	 * @return the boolean
	 */
	public boolean isCounter(IAttaque atk) {
		return atk instanceof Capacite c && c.getNom().equalsIgnoreCase("Counter");
	}

	/**
	 * Is bide boolean.
	 *
	 * @param atk l'attaque
	 * @return the boolean
	 */
	public boolean isBide(IAttaque atk) {
		return atk instanceof Capacite c && c.getNom().equalsIgnoreCase("Bide");
	}

	/**
	 * Counter attaque.
	 *
	 * @param atk1 l'attaque du pokemon 1
	 * @param atk2 l'attaque du pokemon 2
	 * @param pok1 le pokemon 1
	 * @param pok2 le pokemon 2
	 * @return l 'attaque
	 */
	public IAttaque counter(IAttaque atk1, IAttaque atk2, IPokemon pok1, IPokemon pok2) {
		if (atk1 instanceof Capacite c1 && c1.getNom().equalsIgnoreCase("Counter")) {
			if (atk2 instanceof Capacite c2 && !c2.getCategorie().isSpecial()) {
				return new Capacite("counter", CalculDommage.calcul(pok1, pok2, c2) * 2);
			}
		}
		return atk1;
	}

	/**
	 * Bide attaque.
	 *
	 * @param atk1 l'attaque du pokemon 1
	 * @param atk2 l'attaque du pokemon 2
	 * @param pok1 le pokemon 1
	 * @param pok2 le pokemon 2
	 * @return l 'attaque
	 */
	public IAttaque bide(IAttaque atk1, IAttaque atk2, IPokemon pok1, IPokemon pok2) {
		if (atk1 instanceof Capacite c1 && c1.getNom().equalsIgnoreCase("Bide")) {
			if (atk2 instanceof Capacite c2 && !c2.getCategorie().isSpecial()) {
				if (bideCount.get(pok1) == null) {
					bideCount.put(pok1, 0);
					bideDamage.put(pok1, CalculDommage.calcul(pok1, pok2, c2));
					return null;
				}
				if (bideCount.get(pok1) >= bideCounter) {
					Capacite c = new Capacite("bide", bideDamage.get(pok1));
					bideDamage.remove(pok1);
					bideCount.remove(pok1);
					return c;
				} else {
					int damage = bideDamage.get(pok1);
					bideDamage.remove(pok1);
					bideDamage.put(pok1, damage + CalculDommage.calcul(pok1, pok2, c2));
				}
			}
		}
		return null;
	}

	private void forfait(Dresseur d) {
		for (int i = 0; i < 6; i++)
			if (!d.getPokemon(i).estEvanoui())
				d.getPokemon(i).getStat().setPV(0);
	}

	public static HashMap<IPokemon, Integer> getNbPresence1() {
		return nbPresence1;
	}

	public static HashMap<IPokemon, Integer> getNbPresence2() {
		return nbPresence2;
	}
}