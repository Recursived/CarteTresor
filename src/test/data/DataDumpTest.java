package test.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Vector;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import data.*;
import io.MapFileParser;


@TestInstance(Lifecycle.PER_CLASS)
public class DataDumpTest {

	private Vector<Mountain> mountains;
	private Vector<Treasure> treasures;
	private Vector<Adventurer> adventurers;
	private Vector<Game> games;
	
	
	@BeforeAll
	public void setUp() {
		this.mountains = new Vector<>();
		this.mountains.add(new Mountain(1, 2));
		this.mountains.add(new Mountain(1123, 22));
		this.mountains.add(new Mountain(0, 0));
		this.mountains.add(new Mountain(11, 56));
		
		this.treasures = new Vector<>();
		this.treasures.add(new Treasure(1, 2, 2));
		this.treasures.add(new Treasure(1123, 22, 11));
		this.treasures.add(new Treasure(0, 0, 1));
		this.treasures.add(new Treasure(11, 56, 12));
		
		this.adventurers = new Vector<>();
		this.adventurers.add(new Adventurer("Jeanne", Orientation.West, 0 , 1, "AAAAGAADA" ));
		this.adventurers.add(new Adventurer("Jonathan", Orientation.South, 11 , 0, "A" ));
		this.adventurers.add(new Adventurer("Jeanne", Orientation.North, 10 , 10, "AAGGAGGGGA" ));
		this.adventurers.add(new Adventurer("Jeanne", Orientation.East, 12 , 1, "DDDDDDDDDD" ));
		
		this.games = new Vector<>();
		this.games.add(new Game(12, 12));
		this.games.add(new Game(1, 12));
		this.games.add(new Game(12, 0));
		this.games.add(new Game(2, 0));
	}
	
	@DisplayName("Should produce valid output for file writing (mountain)")
	@Test
	void mountainTest() {
		this.mountains.stream().forEach((m) -> {
			assertTrue(MapFileParser.isValidGrammar(m.toString()));
		});
	}
	
	@DisplayName("Should produce valid output for file writing (treasure)")
	@Test
	void treasureTest() {
		this.treasures.stream().forEach((m) -> {
			assertTrue(MapFileParser.isValidGrammar(m.toString()));
		});
	}
	
	@DisplayName("Should produce valid output for file writing (adventurer)")
	@Test
	void adventurerTest() {
		this.adventurers.stream().forEach((m) -> {
			assertTrue(MapFileParser.isValidGrammar(m.toString()));
		});
	}
	
	@DisplayName("Should produce valid output for file writing (game)")
	@Test
	void gameTest() {
		this.games.stream().forEach((m) -> {
			assertTrue(MapFileParser.isValidGrammar(m.toString()));
		});
	}
	
	
	@DisplayName("Should produce an empty string when Treasure is empty")
	@Test
	void emptyTreasureTest() {
		Treasure t = new Treasure(1, 3, 0);
		assertTrue(t.toString().isEmpty());
	}

}
