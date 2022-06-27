package test;

import fr.gardevoir.pokaetoxicque.Main;
import fr.gardevoir.pokaetoxicque.game.pokedex.Espece;
import fr.gardevoir.pokaetoxicque.game.pokedex.Pokedex;
import fr.gardevoir.pokaetoxicque.game.pokedex.Type;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Pokedex Tester.
 *
 * @author @ElloWorld01, Alexandre Colin, Santiago Di Fede, Théo Séminara
 * @version 1.0
 * @since <pre>mai 28, 2022</pre>
 */
public class PokedexTest {
	private static Pokedex pokedex;

	/**
	 * Sets .
	 */
	@BeforeAll
	public static void setup() {
		Main.main(new String[]{"pokedex"});
		pokedex = Main.getPokedex();
	}

	/**
	 * Method: engendreRanch()
	 */
	@RepeatedTest(value = 10, name = "Test ranch Pokémons distincts - {currentRepetition}/{totalRepetitions}")
	public void testEngendreRanch() {
		Assertions.assertEquals(pokedex.engendreRanch().length, Arrays.stream(pokedex.engendreRanch()).distinct().count());
	}

	/**
	 * Method: getEfficacite(IType attaque, IType defense)
	 */
	@Test
	public void testGetEfficacite() {
		Assertions.assertEquals(pokedex.getEfficacite(Type.FIRE, Type.WATER), 0.5, 0.01);
		Assertions.assertEquals(pokedex.getEfficacite(Type.NORMAL, Type.NORMAL), 1.0, 0.01);
		Assertions.assertEquals(pokedex.getEfficacite(Type.WATER, Type.FIRE), 2.0, 0.01);
		Assertions.assertEquals(pokedex.getEfficacite(Type.ELECTRIC, Type.GROUND), 0.0, 0.01);
	}

	/**
	 * Test pokemon evo.
	 */
	@Test
	public void testPokemonEvo() {
		Assertions.assertEquals(
				pokedex.getInfo("pikachu").getEvolution(1).getNom(),
				new Espece("raichu").getNom());
	}
}