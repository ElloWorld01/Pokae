package fr.gardevoir.pokaetoxicque.data;

import fr.gardevoir.pokaetoxicque.Main;
import fr.gardevoir.pokaetoxicque.game.combat.Combat;
import fr.gardevoir.pokaetoxicque.game.pokedex.Pokedex;
import fr.gardevoir.pokaetoxicque.game.pokedex.Type;
import fr.gardevoir.pokaetoxicque.interfaces.ICapacite;
import fr.gardevoir.pokaetoxicque.interfaces.ICategorie;
import fr.gardevoir.pokaetoxicque.interfaces.IPokemon;
import fr.gardevoir.pokaetoxicque.interfaces.IType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * The type Calcule dommage.
 */
public class CalculDommage {

	/**
	 * Calcule les dommages infligés par un pokémon à une autre.
	 *
	 * @param lanceur  Pokémon qui lance l'attaque.
	 * @param receveur Pokémon qui reçoit l'attaque.
	 * @param capacite Capacité utilisée par le lanceur.
	 * @return les dommages calculés.
	 */
	public static int calcul(IPokemon lanceur, IPokemon receveur, ICapacite capacite) {

		if (new Random().nextInt(100) + 1 > capacite.getPrecision())
			return 0;

		switch (capacite.getNom()) {
			case "Fissure", "Guillotine", "Drill Horn" -> {
				if (lanceur.getStat().getVitesse() < receveur.getStat().getVitesse())
					return 0;
				return receveur.getStat().getPV();
			}
			case "Sonic Boom" -> {
				if (Arrays.stream(receveur.getEspece().getTypes()).anyMatch(type -> type == Type.GHOST))
					return 0;
				return 20;
			}
			case "Seismic Toss", "Night Shade" -> {
				return receveur.getNiveau();
			}
			case "Dragon Rage" -> {
				return 40;
			}
			case "Swift" -> {
				if (new Random().nextInt(10000) < 9960)
					return 60;
				return 0;
			}
			case "Psywave" -> {
				return (lanceur.getNiveau()) * (new Random().nextInt(11) + 5) / 10;
			}
			case "Super Fang" -> {
				return receveur.getNiveau() / 2;
			}
			case "Struggle" -> {
				if (Combat.isCombatEnCours())
					lanceur.getStat().setPV(lanceur.getStat().getPV() - 25);
				return 50;
			}
			default -> {
				IType type = capacite.getType();
				int forceDuLanceur;
				double efficaciteSuplementaire = 1;
				double efficaciteLanceur;

				IType[] ltypes = lanceur.getEspece().getTypes();
				for (IType ltype : ltypes)
					if (ltype != null && ltype.equals(type)) {
						efficaciteSuplementaire = 1.5;
						break;
					}

				List<Double> efficacites = new ArrayList<>();
				IType[] rtypes = receveur.getEspece().getTypes();
				for (IType rtype : rtypes)
					if (rtype != null)
						efficacites.add(Main.getPokedex().getEfficacite(type, rtype));

				if (efficacites.size() == 1)
					efficaciteLanceur = efficacites.get(0);
				else
					efficaciteLanceur = efficacites.get(0) * efficacites.get(1);
				efficaciteLanceur *= efficaciteSuplementaire;

				if (capacite.getCategorie().isSpecial())
					forceDuLanceur = lanceur.getStat().getSpecial();
				else
					forceDuLanceur = lanceur.getStat().getForce();

				int resultat = (int) (((((lanceur.getNiveau() * 0.4 + 2) * forceDuLanceur * capacite.getPuissance())
						/ (receveur.getStat().getDefense() * 50)) + 2) * efficaciteLanceur);

				return resultat;
			}
		}
	}

	/**
	 * Fait un test qui calcule les dommages infligés par un pokemon à un autre.
	 *
	 * @param lanceur  Pokémon qui lance l'attaque.
	 * @param receveur Pokémon qui reçoit l'attaque.
	 * @param capacite Capacité utilisée par le lanceur.
	 * @return les dommages calculés.
	 */

	public static int calculTest(IPokemon lanceur, IPokemon receveur, ICapacite capacite) {
		double start = System.nanoTime() / 1000000000.0;

		if (new Random().nextInt(100) + 1 > capacite.getPrecision())
			return 0;

		double rdm = System.nanoTime() / 1000000000.0;
		System.out.println("Random pour précision : " + (rdm - start) + " s");

		double defaultCase = System.nanoTime() / 1000000000.0;
		System.out.println("Arrivée à la fin du Switch-Case : " + (defaultCase - rdm));
		IType type = capacite.getType();
		ICategorie categorie = capacite.getCategorie();
		int puissance = capacite.getPuissance();
		int forceDuLanceur;
		double efficaciteSuplementaire = 1;
		double efficaciteLanceur;
		Pokedex pokedex = Main.getPokedex();

		double pokedexCall = System.nanoTime() / 1000000000.0;
		System.out.println("Déclaration des variables : " + (pokedexCall - defaultCase) + " s");
		IType[] ltypes = lanceur.getEspece().getTypes();
		for (IType ltype : ltypes)
			if (ltype != null && ltype.equals(type)) {
				efficaciteSuplementaire = 1.5;
				break;
			}

		List<Double> efficacites = new ArrayList<>();
		IType[] rtypes = receveur.getEspece().getTypes();
		for (IType rtype : rtypes)
			if (rtype != null)
				efficacites.add(pokedex.getEfficacite(type, rtype));

		if (efficacites.size() == 1)
			efficaciteLanceur = efficacites.get(0);
		else
			efficaciteLanceur = efficacites.get(0) * efficacites.get(1);
		efficaciteLanceur *= efficaciteSuplementaire;

		if (categorie.isSpecial())
			forceDuLanceur = lanceur.getStat().getSpecial();
		else
			forceDuLanceur = lanceur.getStat().getForce();

		double efficaciteCalc = System.nanoTime() / 1000000000.0;
		System.out.println("Calcul des efficacités : " + (efficaciteCalc - pokedexCall) + " s");

		int resultat = (int) (((((lanceur.getNiveau() * 0.4 + 2) * forceDuLanceur * puissance)
				/ (receveur.getStat().getDefense() * 50)) + 2) * efficaciteLanceur);

		double resu = System.nanoTime() / 1000000000.0;
		System.out.println("Calcul du résultat : " + (resu - efficaciteCalc) + " s");

		System.out.println("Arrivée moins le début TOTAL : " + (resu - start) + " s");

		return resultat;
	}

	/**
	 * Fait un test qui calcule les dommages infligés par un pokemon à un autre et calcule le temps nécessaire pour effectuer cela.
	 *
	 * @param lanceur  Pokémon qui lance l'attaque.
	 * @param receveur Pokémon qui reçoit l'attaque.
	 * @param capacite Capacité utilisée par le lanceur.
	 * @return les dommages calculés.
	 */


	public static int calculTestStartEnd(IPokemon lanceur, IPokemon receveur, ICapacite capacite) {
		double start = System.nanoTime() / 1000000000.0;
		if (new Random().nextInt(100) + 1 > capacite.getPrecision())
			return 0;

		switch (capacite.getNom()) {
			case "Fissure", "Guillotine", "Drill Horn" -> {
				if (lanceur.getStat().getVitesse() < receveur.getStat().getVitesse())
					return 0;
				return receveur.getStat().getPV();
			}
			case "Sonic Boom" -> {
				if (Arrays.stream(receveur.getEspece().getTypes()).anyMatch(type -> type == Type.GHOST))
					return 0;
				return 20;
			}
			case "Seismic Toss", "Night Shade" -> {
				return receveur.getNiveau();
			}
			case "Dragon Rage" -> {
				return 40;
			}
			case "Swift" -> {
				if (new Random().nextInt(10000) < 9960)
					return 60;
				return 0;
			}
			case "Psywave" -> {
				return (lanceur.getNiveau()) * (new Random().nextInt(11) + 5) / 10;
			}
			case "Super Fang" -> {
				return receveur.getNiveau() / 2;
			}
			case "Struggle" -> {
				if (Combat.isCombatEnCours())
					lanceur.getStat().setPV(lanceur.getStat().getPV() - 25);
				return 50;
			}
			default -> {
				IType type = capacite.getType();
				int forceDuLanceur;
				double efficaciteSuplementaire = 1;
				double efficaciteLanceur;

				IType[] ltypes = lanceur.getEspece().getTypes();
				for (IType ltype : ltypes)
					if (ltype != null && ltype.equals(type)) {
						efficaciteSuplementaire = 1.5;
						break;
					}

				List<Double> efficacites = new ArrayList<>();
				IType[] rtypes = receveur.getEspece().getTypes();
				for (IType rtype : rtypes)
					if (rtype != null)
						efficacites.add(Main.getPokedex().getEfficacite(type, rtype));

				if (efficacites.size() == 1)
					efficaciteLanceur = efficacites.get(0);
				else
					efficaciteLanceur = efficacites.get(0) * efficacites.get(1);
				efficaciteLanceur *= efficaciteSuplementaire;

				if (capacite.getCategorie().isSpecial())
					forceDuLanceur = lanceur.getStat().getSpecial();
				else
					forceDuLanceur = lanceur.getStat().getForce();

				int resultat = (int) (((((lanceur.getNiveau() * 0.4 + 2) * forceDuLanceur * capacite.getPuissance())
						/ (receveur.getStat().getDefense() * 50)) + 2) * efficaciteLanceur);


				System.out.println("Arrivée moins le début TOTAL : " + (System.nanoTime() / 1000000000.0 - start) + " s");

				return resultat;
			}
		}
	}
}