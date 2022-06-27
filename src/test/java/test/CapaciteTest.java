package test;

import fr.gardevoir.pokaetoxicque.Main;
import fr.gardevoir.pokaetoxicque.data.TypeData;
import fr.gardevoir.pokaetoxicque.game.pokedex.Capacite;
import fr.gardevoir.pokaetoxicque.game.pokedex.Pokedex;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * TestCapacite Tester.
 *
 * @author @ElloWorld01, Alexandre Colin, Santiago Di Fede, Théo Séminara
 * @version 1.0
 * @since <pre>mai 28, 2022</pre>
 */
public class CapaciteTest {

	/**
	 * The C.
	 */
	static final Capacite c = new Capacite("PoUnD");

	/**
	 * Le Pokedex.
	 */
	static Pokedex pokedex;

	/**
	 * Sets .
	 */
	@BeforeAll
	public static void setup() {
		Main.main(new String[]{"pokedex"});
		pokedex = Main.getPokedex();
		assertEquals(c.getNom(), "pound");
		assertEquals(c.getPrecision(), 100, 0.001);
		assertEquals(c.getPuissance(), 40);
		assertEquals(c.getPP(), 35);
		assertEquals(c.getCategorie().nom(), "physical");
		assertEquals(c.getType(), TypeData.getType("Normal"));
	}

	/**
	 * Test creation capacite interdite.
	 */
	@Test
	public void testCreationCapaciteInterdite() {
		assertThrows(IllegalArgumentException.class, () -> new Capacite("focus-energy"));
	}

	/**
	 * Method: testUtilise()
	 */
	@Test
	public void testUtilise() {
		for (int i = 0; i < 35; i++)
			c.utilise();
		assertEquals(c.getPP(), 0);
	}
} 
