package fr.gardevoir.pokaetoxicque.game.strategies;

import fr.gardevoir.pokaetoxicque.data.PokedexData;
import fr.gardevoir.pokaetoxicque.game.combat.Echange;
import fr.gardevoir.pokaetoxicque.interfaces.IAttaque;
import fr.gardevoir.pokaetoxicque.interfaces.IPokemon;
import fr.gardevoir.pokaetoxicque.utils.Layout;
import fr.gardevoir.pokaetoxicque.utils.Messages;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Définition d'un Dresseur
 */
public class StrategiePlayer extends Strategie {

	/**
	 * Instantiates a new Strategie player.
	 *
	 * @param ranch the ranch
	 */
	public StrategiePlayer(IPokemon[] ranch) {
		super(ranch);
	}

	@Override
	public IPokemon choisitCombattant() {
		System.out.println("Choisissez un pokémon : ");
		Messages.listRanch(ranch);
		boolean choixOk = false;
		IPokemon pok = null;
		Scanner sc = new Scanner(System.in);
		while (!choixOk) {
			System.out.print("Entrez le numéro du pokémon : ");
			int i;
			try {
				i = sc.nextInt() - 1;
				if (i >= 1 && i <= 6) {
					pok = ranch[i];
					if (!pok.estEvanoui())
						choixOk = true;
					else
						Layout.info("Ce pokemon est évanoui, vous devez en choisir un autre.");

				} else {
					Layout.error("Veuillez entrer un nombre entre 1 et 6.");
				}
			} catch (NumberFormatException e) {
				Layout.error("Veuillez entrer un nombre");
			}
		}
		return pok;
	}

	@Override
	public IPokemon choisitCombattantContre(IPokemon pok) {
		System.out.println("Quel pokemon voulez-vous choisir pour attaquer " + pok.getNom() + " ?");
		return choisitCombattant();
	}

	@Override
	public IAttaque choisitAttaque(IPokemon attaquant, IPokemon defenseur) {
		System.out.println("Quelle attaque de " + Layout.speciesToFrench(attaquant)
				+ " allez voulez-vous lancer sur " + Layout.speciesToFrench(defenseur) + " ?");
		System.out.println("1: Echange");
		System.out.println("2: Attaque");
		String attaque;
		int finalAttaque = 0;
		boolean choixOk = false;
		Scanner sc = new Scanner(System.in);
		while (!choixOk) {
			try {
				attaque = sc.nextLine().trim();
				if (attaque.equalsIgnoreCase("stop") || attaque.equalsIgnoreCase(":q!")) {
					Layout.info("Vous avez abandonné le combat !");
					return null;
				}
				finalAttaque = Integer.parseInt(attaque);
				if (finalAttaque != 1 && finalAttaque != 2) {
					Layout.error("Veuillez entrer un nombre entre 1 et 2.");
				} else
					choixOk = true;
			} catch (NumberFormatException e) {
				Layout.error("Veuillez entrer un nombre.");
			}
		}

		if (finalAttaque == 1)
			return new Echange(attaquant, choisitCombattant());


		for (int i = 0; i < attaquant.getCapacitesApprises().length; i++)
			System.out.println((i + 1) + ": " + Layout.moveToFrench(attaquant.getCapacitesApprises()[i]));
		System.out.print("\nChoix : ");
		choixOk = false;
		IAttaque capacite = null;
		while (!choixOk) {
			try {
				attaque = sc.nextLine().trim();
				if (attaque.equalsIgnoreCase("stop") || attaque.equalsIgnoreCase(":q!")) {
					System.out.println("Vous avez abandonné le combat !");
					return null;
				}
				finalAttaque = Integer.parseInt(attaque) - 1;
				if (finalAttaque >= attaquant.getCapacitesApprises().length || finalAttaque < 1) {
					Layout.error("Vous devez choisir entre 1 et 6 !");
					continue;
				}
				if (attaquant.getCapacitesApprises()[finalAttaque] == null) {
					Layout.error("Vous avez choisi une capacité qui n'existe pas !");
				} else {
					capacite = attaquant.getCapacitesApprises()[finalAttaque];
					choixOk = true;
				}
			} catch (NumberFormatException e) {
				Layout.error("Veuillez entrer un nombre");
			}
		}
		return capacite;
	}

	@Override
	public void initCapacitesRanch() {
		System.out.print("Voulez-vous donner les capacités à vos pokemons ? (O/N) : ");
		Scanner sc = new Scanner(System.in);
		String reponse;
		reponse = sc.nextLine();
		if (reponse.trim().equals("O") || reponse.trim().equals("o")) {

			for (int i = 0; i < ranch.length; i++)
				for (int j = 0; j < ranch[i].getCapacitesApprises().length; j++)
					while (!PokedexData.demandeCapacite(i, j, Arrays.stream(ranch).toList())) ;
		} else {
			for (IPokemon pokemon : ranch)
				PokedexData.donneCapacites(pokemon);
		}
	}
}