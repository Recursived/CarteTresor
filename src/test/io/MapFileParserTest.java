package test.io;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.ValueSource;

import data.Adventurer;
import data.Mountain;

import io.MapFileParser;
import utils.Pair;

public class MapFileParserTest {
	

	@DisplayName("Should pass valid grammar to our grammar validator method")
	@ParameterizedTest
	@ValueSource(strings = {"M - 11 - 2", "C - 3 - 13", "T - 2 - 2 - 12", "A - Lara - 0 - 3 - S - 3", "A - Lara - 0 - 3 - S - AGGDAAAAG", "# {T comme Trésor} - {Axe horizontal} - {Axe vertical} - {Nb. de trésors restants}"})
	void isValidGrammar(String line) {
		assertTrue(MapFileParser.isValidGrammar(line));
	}
	
	@DisplayName("Should pass invalid grammar to our grammar validator method")
	@ParameterizedTest
	@ValueSource(strings = {" azoiudazkdbaz da11 ","M- 1 -2", "M - -1 - 2", "C -  - 3", "T - 2 - 2 - 2 ", "   A - Lara - 0 - 3 - S - 3", "A - Lara -    0 - 3 - S - AGGDAAAAG2"})
	void isInvalidGrammar(String line) {
		assertFalse(MapFileParser.isValidGrammar(line));
	}
	
	@DisplayName("The strings should return the corresponding MapElement instance")
	@ParameterizedTest
	@ArgumentsSource(MapElemArgProvider.class)
	void parseMountainTest(Pair<String, Mountain> p) {
		assertTrue(MapFileParser.parseMapElement(p.fst).equals(p.snd));
	}
	
	
	@DisplayName("The strings should return the corresponding Adventurer instance")
	@ParameterizedTest
	@ArgumentsSource(AdventurerArgProvider.class)
	void parseAdventurerTest(Pair<String, Adventurer> p) {
		assertTrue(MapFileParser.parseAdventurer(p.fst).equals(p.snd));
	}
	
	@DisplayName("The strings should return the corresponding dimension ")
	@ParameterizedTest
	@ArgumentsSource(DimensionArgProvider.class)
	void parseDimensionTest(Pair<String, Pair<Integer, Integer>> p) {
		Pair<Integer, Integer> dims = MapFileParser.parseGameDimensions(p.fst);
		assertNotNull(dims);
		assertTrue(MapFileParser.parseGameDimensions(p.fst).equals(p.snd));
	}
	
	

}
