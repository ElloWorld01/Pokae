package fr.gardevoir.pokaetoxicque.utils;

import fr.gardevoir.pokaetoxicque.Main;
import fr.gardevoir.pokaetoxicque.data.PokedexData;
import fr.gardevoir.pokaetoxicque.game.combat.Combat;
import fr.gardevoir.pokaetoxicque.game.combat.Dresseur;
import fr.gardevoir.pokaetoxicque.game.pokedex.Pokedex;
import fr.gardevoir.pokaetoxicque.game.pokedex.Type;
import fr.gardevoir.pokaetoxicque.game.strategies.StrategieMinMax;
import fr.gardevoir.pokaetoxicque.game.strategies.StrategieMinMaxMaximize;
import fr.gardevoir.pokaetoxicque.game.strategies.StrategiePlayer;
import fr.gardevoir.pokaetoxicque.game.strategies.StrategieRandom;
import fr.gardevoir.pokaetoxicque.interfaces.IEspece;
import fr.gardevoir.pokaetoxicque.interfaces.IPokemon;

import java.util.Arrays;
import java.util.Scanner;

/**
 * The type Demonstration.
 */
public class Demonstration {

	private static final Pokedex pokedex = Main.getPokedex();
	private static boolean firstTime = true;

	/**
	 * Demonstration.
	 */
	public static void demonstration() {
		String rep = "";
		while (!(rep.equalsIgnoreCase(":wq!") || rep.equalsIgnoreCase("0"))) {
			if (firstTime)
				System.out.println("""
						                                  ,'\\
						    _.----.        ____         ,'  _\\   ___    ___     ____
						_,-'       `.     |    |  /`.   \\,-'    |   \\  /   |   |    \\  |`.
						\\      __    \\    '-.  | /   `.  ___    |    \\/    |   '-.   \\ |  |
						 \\.    \\ \\   |  __  |  |/    ,','_  `.  |          | __  |    \\|  |
						   \\    \\/   /,' _`.|      ,' / / / /   |          ,' _`.|     |  |
						    \\     ,-'/  /   \\    ,'   | \\/ / ,`.|         /  /   \\  |     |
						     \\    \\ |   \\_/  |   `-.  \\    `'  /|  |    ||   \\_/  | |\\    |
						      \\    \\ \\      /       `-.`.___,-' |  |\\  /| \\      /  | |   |
						       \\    \\ `.__,'|  |`-._    `|      |__| \\/ |  `.__,'|  | |   |
						        \\_.-'       |__|    `-._ |              '-.|     '-.| |   |
						                                `'                            '-._|
						Bienvenue dans Pokae Toxicque !""");
			else
				System.out.println("\n");
			firstTime = false;
			System.out.print("""
					Veuillez entrer le numéro de l'action que vous souhaitez effectuer :
					| (Abandonner un combat : :q! ou stop)  | Combat entre deux joueurs : 2 | Pokedex :  4          |
					| Combat entre un joueur et une IA :  1 |  Combat de deux IA :  3       |  Quitter :  0 ou :wq! |
					Choix :\040""");
			Scanner sc = new Scanner(System.in);
			if (sc.hasNext()) {
				rep = sc.next();
				switch (rep) {
					case "1" -> fightPlayerVersusIA(sc);
					case "2" -> fightPlayerVersusPlayer(sc);
					case "3" -> fightIAVersusIA();
					case "4" -> pokedex();
					case "0", ":wq!" -> System.out.println("Au revoir !");
					default -> Layout.error("Choix invalide !");
				}
			} else {
				Layout.error("Veuillez entrer un numéro.");
			}
		}
	}

	/**
	 * Start fight.
	 */
	public static void fightIAVersusIA() {
		IPokemon[] ranch1 = pokedex.engendreRanch();
		IPokemon[] ranch2 = pokedex.engendreRanch();
		Dresseur gagnant = new Dresseur("MinMax", ranch1);
		Dresseur perdant = new Dresseur("Random", ranch2, new StrategieRandom(ranch2));
		gagnant.setStrategy(new StrategieMinMaxMaximize(gagnant, perdant));
		Combat combat1 = new Combat(gagnant, perdant);
		combat1.commence();
	}

	/**
	 * Fight player versus player.
	 *
	 * @param sc the sc
	 */
	public static void fightPlayerVersusPlayer(Scanner sc) {
		System.out.println("Vous avez choisi le mode de combat entre deux joueurs");
		System.out.println("Veuillez entrer le nom du joueur 1 :");
		String nomJoueur1 = sc.next();
		System.out.println("Veuillez entrer le nom du joueur 2 :");
		String nomJoueur2 = sc.next();
		IPokemon[] ranch1 = PokedexData.engendreRanchPlayer();
		IPokemon[] ranch2 = PokedexData.engendreRanchPlayer();
		Dresseur joueur1 = new Dresseur(nomJoueur1, ranch1, new StrategiePlayer(ranch1));
		Dresseur joueur2 = new Dresseur(nomJoueur2, ranch2, new StrategiePlayer(ranch2));
		Combat combat2 = new Combat(joueur1, joueur2);
		combat2.commence();
	}

	/**
	 * Fight player versus ia.
	 *
	 * @param sc the sc
	 */
	public static void fightPlayerVersusIA(Scanner sc) {
		System.out.println("Vous avez choisi le mode de combat entre un joueur et une IA");
		System.out.print("\nVeuillez entrer le nom du joueur : ");
		String nomJoueur = sc.next();
		System.out.print("\nVeuillez entrer le nom de l'IA : ");
		String nomIA = sc.next();
		IPokemon[] pokemonsJoueur = PokedexData.engendreRanchPlayer();
		IPokemon[] pokemonAI = pokedex.engendreRanch();
		Dresseur joueur = new Dresseur(nomJoueur, pokemonsJoueur, new StrategiePlayer(pokemonsJoueur));
		Dresseur ia = new Dresseur(nomIA, pokemonAI, new StrategieRandom(pokemonAI));
		Combat combat = new Combat(joueur, ia);
		combat.commence();
	}

	/**
	 * Pokedex.
	 */
	public static void pokedex() {
		System.out.println("Bienvenue dans le Pokédex !");
		System.out.println("Liste des pokémons :");
		pokedex.getEspeces().values().forEach(type -> System.out.println(type.getNom()));
		System.out.println("Liste des capacités :");
		pokedex.getCapacites().values().forEach(cap -> System.out.println(cap.getNom()));
		System.out.println("Liste des types :");
		Arrays.stream(Type.values()).forEach(type -> System.out.println(type.getNom()));
		Scanner sc2 = new Scanner(System.in);
		System.out.println("Veuillez entrer le nom d'un pokémon pour obtenir ses informations :");
		if (sc2.hasNext()) {
			String nom = sc2.next();
			IEspece pokemon = pokedex.getInfo(nom);
			if (pokemon != null) {
				System.out.println(pokemon);
			} else {
				Layout.error("Ce pokémon n'existe pas !");
			}
		}
	}
}