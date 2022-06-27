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
public class CalculeDommage {

	/**
	 * Calcule les dommages infligés par un pokémon à une autre.
	 *
	 * @param lanceur  Pokémon qui lance l'attaque.
	 * @param receveur Pokémon qui reçoit l'attaque.
	 * @param capacite Capacité utilisée par le lanceur.
	 * @return les dommages calculés.
	 */
	public static int calculeDommage(IPokemon lanceur, IPokemon receveur, ICapacite capacite) {
		if (new Random().nextInt(100) + 1 > capacite.getPrecision() && capacite.getPrecision() != 0)
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
				ICategorie categorie = capacite.getCategorie();
				int puissance = capacite.getPuissance();
				int forceDuLanceur;
				double efficaciteSuplementaire = 1;
				double efficaciteLanceur;

				Pokedex pokedex = Main.getPokedex();

				for (int i = 0; i < lanceur.getEspece().getTypes().length; i++)
					if (lanceur.getEspece().getTypes()[i] != null && lanceur.getEspece().getTypes()[i].equals(type))
						efficaciteSuplementaire = 1.5;


				List<Double> efficacites = new ArrayList<>();
				for (int i = 0; i < receveur.getEspece().getTypes().length; i++)
					if (receveur.getEspece().getTypes()[i] != null)
						efficacites.add(pokedex.getEfficacite(type, receveur.getEspece().getTypes()[i]));

				if (efficacites.size() == 1) {
					efficaciteLanceur = efficacites.get(0);
				} else {
					efficaciteLanceur = efficacites.get(0) * efficacites.get(1);
				}
				efficaciteLanceur *= efficaciteSuplementaire;

				if (categorie.isSpecial())
					forceDuLanceur = lanceur.getStat().getSpecial();
				else
					forceDuLanceur = lanceur.getStat().getForce();


				return (int) (((((lanceur.getNiveau() * 0.4 + 2) * forceDuLanceur * puissance)
						/ (receveur.getStat().getDefense() * 50)) + 2) * efficaciteLanceur);
			}
		}
	}
}