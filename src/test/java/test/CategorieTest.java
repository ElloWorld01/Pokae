package test;

import fr.gardevoir.pokaetoxicque.game.pokedex.Categorie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Categorie Tester.
 *
 * @author @ElloWorld01, Alexandre Colin, Santiago Di Fede, Théo Séminara
 * @version 1.0
 * @since <pre>mai 28, 2022</pre>
 */
public class CategorieTest {

	/**
	 * Test creation categorie.
	 */
	@Test
	public void testCreationCategorie() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Categorie("JeNexistePas"));
	}
}
