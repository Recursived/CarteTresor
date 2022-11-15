package test.core;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.Timeout;

import client.IUserCommand;
import core.GameManager;

@TestInstance(Lifecycle.PER_CLASS)
@Timeout(3)
public class GameManagerTest {
	
	private IUserCommand uc;
	
	
	@BeforeAll
	public void setUp() {
		this.uc = new GameManager();
	}
	
	@DisplayName("Loading a valid game file should not throw an error of any kind")
	@Test
	void loadGameTest() {
		assertDoesNotThrow(() -> uc.loadGame("src/test/core/test_out_of_bound.test"));
	}
	
	@DisplayName("Simulating a simple game should take less than one second")
	@Test
	@Timeout(1)
	void simulateGameTOTest() {
		assertDoesNotThrow(() -> uc.loadGame("src/test/core/test_out_of_bound.test"));
		assertDoesNotThrow(() -> uc.simulateGame());
	}
	
	@DisplayName("Test that checks if the collision between players functions properly")
	@Test
	@Timeout(2)
	void simulateCollisionAdventurerTest() {
		assertDoesNotThrow(() -> uc.loadGame("src/test/core/test_collision_adventurer.test"));
		assertDoesNotThrow(() -> uc.simulateGame());
		
		try {
			assertTrue(
					Files.mismatch(
							Path.of("src/test/core/test_collision_adventurer.test_result"), 
							Path.of("src/test/core/test_collision_adventurer.test.simulated_result")) == -1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@DisplayName("Test that checks if the collision between a mountain and a player works properly")
	@Test
	@Timeout(2)
	void simulateCollisionMountainTest() {
		assertDoesNotThrow(() -> uc.loadGame("src/test/core/test_mountain_collision.test"));
		assertDoesNotThrow(() -> uc.simulateGame());
		
		try {
			assertTrue(
					Files.mismatch(
							Path.of("src/test/core/test_mountain_collision.test_result"), 
							Path.of("src/test/core/test_mountain_collision.test.simulated_result")) == -1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@DisplayName("Test that checks that the player does not go out of bound")
	@Test
	@Timeout(2)
	void simulateOutOfBoundTest() {
		assertDoesNotThrow(() -> uc.loadGame("src/test/core/test_out_of_bound.test"));
		assertDoesNotThrow(() -> uc.simulateGame());
		
		try {
			assertTrue(
					Files.mismatch(
							Path.of("src/test/core/test_out_of_bound.test_result"), 
							Path.of("src/test/core/test_out_of_bound.test.simulated_result")) == -1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@DisplayName("Test that checks that the players interacts correctly with a treasure")
	@Test
	@Timeout(2)
	void simulateTreasureInteractionTest() {
		assertDoesNotThrow(() -> uc.loadGame("src/test/core/test_treasure_interaction.test"));
		assertDoesNotThrow(() -> uc.simulateGame());
		
		try {
			assertTrue(
					Files.mismatch(
							Path.of("src/test/core/test_treasure_interaction.test_result"), 
							Path.of("src/test/core/test_treasure_interaction.test.simulated_result")) == -1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	@DisplayName("Test that checks that the players turns correctly")
	@Test
	@Timeout(2)
	void simulateTurnPlayerTest() {
		assertDoesNotThrow(() -> uc.loadGame("src/test/core/test_turn_player.test"));
		assertDoesNotThrow(() -> uc.simulateGame());
		
		try {
			assertTrue(
					Files.mismatch(
							Path.of("src/test/core/test_turn_player.test_result"), 
							Path.of("src/test/core/test_turn_player.test.simulated_result")) == -1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@DisplayName("Test that a complete game functions appropriately")
	@Test
	@Timeout(2)
	void simulateHoplisticTest() {
		assertDoesNotThrow(() -> uc.loadGame("src/test/core/test_hollistic.test"));
		assertDoesNotThrow(() -> uc.simulateGame());
		
		try {
			assertTrue(
					Files.mismatch(
							Path.of("src/test/core/test_hollistic.test_result"), 
							Path.of("src/test/core/test_hollistic.test.simulated_result")) == -1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
