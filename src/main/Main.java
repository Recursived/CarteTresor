package main;

import client.IUserCommand;
import core.GameManager;

public class Main {
	public static void main(String[] args) {

		IUserCommand uc = new GameManager();
		uc.toggleDebug();
		try {
			uc.loadGame("src/test/core/teAst_hollistic.test");
			uc.simulateGame();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
