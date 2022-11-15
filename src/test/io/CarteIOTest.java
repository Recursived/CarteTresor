package test.io;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import data.Game;
import io.CarteInOutImp;

@TestInstance(Lifecycle.PER_CLASS)
public class CarteIOTest {

	
	private CarteInOutImp cio;
	// Valid test files
	private String validMapEmpty;
	private String validMapUnordered;
	private String validMap;
	
	// Invalid test files
	private String invalidMapBadGrammar;
	private String invalidMapBadPosition;
	private String invalidMapBadPosition2;
	private String invalidMapEmpty;
	private String invalidMapDuplicatePlayer;
	private String invalidMapDuplicateElem;
	
	
	
	/*
	 * Input test section
	 */
	
	@BeforeAll
	public void setUp() {
		this.cio = new CarteInOutImp();
		
		this.validMap = "src/test/io/testmaps/valid/valid_map.test";
		this.validMapEmpty = "src/test/io/testmaps/valid/valid_map_empty.test";
		this.validMapUnordered = "src/test/io/testmaps/valid/valid_map_unordered.test";
		
		this.invalidMapBadGrammar = "src/test/io/testmaps/invalid/invalid_bad_grammar.test";
		this.invalidMapDuplicatePlayer = "src/test/io/testmaps/invalid/invalid_duplicate_player.test";
		this.invalidMapBadPosition = "src/test/io/testmaps/invalid/invalid_map_bad_position.test";
		this.invalidMapBadPosition2 = "src/test/io/testmaps/invalid/invalid_map_bad_position2.test";
		this.invalidMapDuplicateElem = "src/test/io/testmaps/invalid/invalid_map_duplicate.test";
		this.invalidMapEmpty = "src/test/io/testmaps/invalid/invalid_map_empty.test";
	}
	
	
	//
	
	@DisplayName("Map should produce a correct game instance")
	@Test
	void validMapTest() {
		assertDoesNotThrow(() -> cio.readGameFile(this.validMap));
		try {
			Game g = cio.readGameFile(this.validMap);
			assertTrue(g.getLargeur() == 3);
			assertTrue(g.getHauteur() == 4);
			assertTrue(g.getAdventurers().size() == 1);
			assertTrue(g.getElements().size() == 4);
		} catch (Exception e) {
		}
	}
	
	@DisplayName("Empty map should produce a correct game instance")
	@Test
	void validMapEmptyTest() {
		assertDoesNotThrow(() -> cio.readGameFile(this.validMapEmpty));
		try {
			Game g = cio.readGameFile(this.validMapEmpty);
			assertTrue(g.getLargeur() == 5);
			assertTrue(g.getHauteur() == 2);
			assertTrue(g.getAdventurers().size() == 0);
			assertTrue(g.getElements().size() == 0);
		} catch (Exception e) {
		}
	}
	
	@DisplayName("Unordered map file should produce a correct game instance")
	@Test
	void validMapUnorderedTest() {
		assertDoesNotThrow(() -> cio.readGameFile(this.validMapUnordered));
		try {
			Game g = cio.readGameFile(this.validMapUnordered);
			assertTrue(g.getHauteur() == 10);
			assertTrue(g.getLargeur() == 10);
			assertTrue(g.getAdventurers().size() == 1);
			assertTrue(g.getElements().size() == 1);
		} catch (Exception e) {
		}
	}
	
	@DisplayName("Invalid grammar map file should throw an Exception")
	@Test
	void invalidBadGrammarTest() {
		assertThrows(Exception.class, () -> cio.readGameFile(this.invalidMapBadGrammar));
	}
	
	@DisplayName("Invalid duplicate player map file should throw an Exception")
	@Test
	void invalidDuplicatePlayerTest() {
		assertThrows(Exception.class, () -> cio.readGameFile(this.invalidMapDuplicatePlayer));
	}
	
	@DisplayName("Invalid bad position map file should throw an Exception")
	@Test
	void invalidBadPositionTest() {
		assertThrows(Exception.class, () -> cio.readGameFile(this.invalidMapBadPosition));
	}
	
	@DisplayName("Invalid bad position n°2 map file should throw an Exception")
	@Test
	void invalidBadPosition2Test() {
		assertThrows(Exception.class, () -> cio.readGameFile(this.invalidMapBadPosition2));
	}
	
	@DisplayName("Invalid duplicate elem map file should throw an Exception")
	@Test
	void invalidDuplicateElemTest() {
		assertThrows(Exception.class, () -> cio.readGameFile(this.invalidMapDuplicateElem));
	}
	
	@DisplayName("Invalid empty map file should throw an Exception")
	@Test
	void invalidEmptyMapTest() {
		assertThrows(Exception.class, () -> cio.readGameFile(this.invalidMapEmpty));
	}
	
	
	
	
	
	

}
