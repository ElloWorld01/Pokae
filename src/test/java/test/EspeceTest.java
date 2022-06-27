package test;

import fr.gardevoir.pokaetoxicque.game.pokedex.Espece;
import fr.gardevoir.pokaetoxicque.game.pokedex.Stat;
import fr.gardevoir.pokaetoxicque.game.pokedex.Type;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Espece Tester.
 *
 * @author @ElloWorld01, Alexandre Colin, Santiago Di Fede, Théo Séminara
 * @version 1.0
 * @since <pre>mai 28, 2022</pre>
 */
public class EspeceTest {

	/**
	 * The Espece.
	 */
	final Espece espece = new Espece(1);

	/**
	 * Method: getBaseStat()
	 */
	@Test
	public void testGetBaseStat() {
		Assertions.assertEquals(espece.getBaseStat(), new Stat(45, 49, 49, 65, 45));
	}

	/**
	 * Method: getGainsStat()
	 */
	@Test
	public void testGetGainsStat() {
		Assertions.assertEquals(espece.getGainsStat(), new Stat(0, 0, 0, 1, 0));
	}

	/**
	 * Method: getCapSet()
	 */
	@Test
	public void testGetCapSet() {

	}

	/**
	 * Method: getEvolution(int niveau)
	 */
	@Test
	public void testGetEvolution() {

	}

	/**
	 * Method: getTypes()
	 */
	@Test
	public void testGetTypes() {
		Assertions.assertArrayEquals(espece.getTypes(), new Type[]{Type.GRASS, Type.POISON});
	}
}