package com.bfi.tek.main;

import java.util.ArrayList;

import com.bfi.tek.controllers.ConsolePlayerController;
import com.bfi.tek.models.Player;
import com.bfi.tek.races.Dwarves;
import com.bfi.tek.races.Elves;


public class Main {

	public static void main(String[] args){
		Game game = new Game();
		
		
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player());
		players.add(new Player());
		
		players.get(0).setRace(new Dwarves());
		players.get(1).setRace(new Elves());
		players.get(0).setName("Zach");
		players.get(0).setName("Mike");
		new ConsolePlayerController().setPlayer(players.get(0));
		new ConsolePlayerController().setPlayer(players.get(1));
		
		game.setUpGame(players);
		game.Start();
	}

}
