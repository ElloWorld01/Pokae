package test;

import fr.gardevoir.pokaetoxicque.Main;
import fr.gardevoir.pokaetoxicque.data.PokedexData;
import fr.gardevoir.pokaetoxicque.game.combat.Combat;
import fr.gardevoir.pokaetoxicque.game.combat.Dresseur;
import fr.gardevoir.pokaetoxicque.game.pokedex.Pokedex;
import fr.gardevoir.pokaetoxicque.game.strategies.StrategieRandom;
import fr.gardevoir.pokaetoxicque.interfaces.IPokemon;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

/**
 * The type Combat test.
 */
public class CombatTest {

	/**
	 * The Pokedex.
	 */
	static Pokedex pokedex;

	/**
	 * Sets .
	 */
	@BeforeAll
	public static void setup() {
		Main.main(new String[]{"pokedex"});
		pokedex = Main.getPokedex();
	}

//	@Test
//	@Timeout(value = 100)
//	public void testCombatEnCours() {
//		IPokemon[] ranch1 = PokedexData.engendreRanchRandom();
//		IPokemon[] ranch2 = PokedexData.engendreRanchRandom();
//		Dresseur joueur1 = new Dresseur("test1", ranch1, new StrategieRandom(ranch1));
//		Dresseur joueur2 = new Dresseur("test2", ranch2, new StrategieRandom(ranch2));
//		Combat combat2 = new Combat(joueur1, joueur2);
//		combat2.commence();
//	}
}
