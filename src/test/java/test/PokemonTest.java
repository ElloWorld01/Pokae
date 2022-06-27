package test;

import fr.gardevoir.pokaetoxicque.Main;
import fr.gardevoir.pokaetoxicque.game.pokedex.Capacite;
import fr.gardevoir.pokaetoxicque.game.pokedex.Espece;
import fr.gardevoir.pokaetoxicque.game.pokedex.Pokemon;
import fr.gardevoir.pokaetoxicque.interfaces.ICapacite;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Pokemon Tester.
 *
 * @author @ElloWorld01, Alexandre Colin, Santiago Di Fede, Théo Séminara
 * @version 1.0
 * @since <pre>mai 28, 2022</pre>
 */
public class PokemonTest {

	private static Pokemon testPokemon;

	/**
	 * Sets .
	 */
	@BeforeAll
	public static void setup() {
		Main.main(new String[]{"pokedex"});
		testPokemon = new Pokemon(new Espece(1));
	}

	/**
	 * Test get id pokemon.
	 */
	@Test
	public void testGetIdPokemon() {
		Pokemon pokemonNo125 = new Pokemon(new Espece("Electabuzz"));
		Assertions.assertEquals(pokemonNo125.getId(), 125);
	}

	/**
	 * Method: vaMuterEn(IEspece esp)
	 */
	@Test
	public void testVaMuterEn() {
		//TODO Assert.assertEquals(new Espece(2), testPokemon.vaMuterEn());
	/*	testPokemon.vaMuterEn();
		Assert.assertEquals(new Espece(2), testPokemon.getEspece());*/
	}

	/**
	 * Method: apprendCapacites(ICapacite[] caps)
	 */
	@Test
	public void testApprendCapacites() {
		testPokemon.apprendCapacites(new ICapacite[]{
				new Capacite("razor-wind"),
				new Capacite("cut"),
				new Capacite("bind"),
				new Capacite("vine-whip")
		});
	}

	/**
	 * Method: apprendCapacites(ICapacite[] caps)
	 */
	@Test
	public void testApprendCapacitesDoublon() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> testPokemon.apprendCapacites(new ICapacite[]{
				new Capacite("razor-wind"),
				new Capacite("cut"),
				new Capacite("bind"),
				new Capacite("bind")
		}));
	}

	/**
	 * Method: apprendCapacites(ICapacite[] caps)
	 */
	@Test
	public void testApprendCapacitesVide() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> testPokemon.apprendCapacites(new ICapacite[]{}));
	}

	/**
	 * Method: remplaceCapacite(int numeroCapacite, ICapacite cap)
	 */
	@Test
	public void testRemplaceCapacite() {
		testPokemon.remplaceCapacite(1, new Capacite("razor-wind"));
	}

	/**
	 * Method: remplaceCapacite(int numeroCapacite, ICapacite cap)
	 */
	@Test
	public void testRemplaceCapaciteOutOfBounds() {
		Assertions.assertThrows(IndexOutOfBoundsException.class,
				() -> testPokemon.remplaceCapacite(4, new Capacite("razor-wind")));
	}

	/**
	 * Method: subitAttaqueDe(IPokemon pok, IAttaque atk)
	 */
	@Test
	public void testSubitAttaqueDe() {

	}

	/**
	 * Method: aChangeNiveau()
	 */
	@Test
	public void testAChangeNiveau() {
	}

	/**
	 * Method: courbeExperience()
	 */
	@Test

	public void testCourbeExperience() {
	}
}
