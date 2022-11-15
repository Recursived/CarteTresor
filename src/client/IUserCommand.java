package client;


public interface IUserCommand {
	
	
	void loadGame(String filename) throws Exception;
	void simulateGame() throws Exception;
	void simulateGameParralel() throws RuntimeException;
	void toggleDebug();
	
}
