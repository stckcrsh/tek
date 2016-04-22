package com.bfi.tek.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.bfi.tek.models.Player;
import com.bfi.tek.models.Space;
import com.bfi.tek.races.Race;
import com.bfi.tek.territories.Territory;

public class Game {

	private static List<Player> players;
	protected int currentPlayer;
	protected boolean isGameEnding = false;

	protected ArrayList<Race> races;
	protected ArrayList<Territory> territories;

	public enum Action {
		COLLECTRESOURCES("Collect Resources", new PlayerAction() {

			@Override
			public void action(Player _player, boolean _isCurrent) {
				_player.getController().CollectResources();
			}

		}), PATROL("Patrol", new PlayerAction() {

			@Override
			public void action(Player _player, boolean _isCurrent) {
				_player.getController().PatrolAction(_isCurrent);
			}

		}), QUEST("Quest", new PlayerAction() {

			@Override
			public void action(Player _player, boolean _isCurrent) {
				_player.getController().QuestAction(_isCurrent);
			}

		}), RESEARCH("Research", new PlayerAction() {

			@Override
			public void action(Player _player, boolean _isCurrent) {
				_player.getController().ResearchAction(_isCurrent);
			}

		}), BUILD("Build", new PlayerAction() {

			@Override
			public void action(Player _player, boolean _isCurrent) {
				_player.getController().BuildAction(_isCurrent);
			}

		}), EXPAND("Expand", new PlayerAction() {

			@Override
			public void action(Player _player, boolean _isCurrent) {
				_player.getController().ExpandAction(_isCurrent);
			}

		}), TRADE("Trade", new PlayerAction() {

			@Override
			public void action(Player _player, boolean _isCurrent) {
				_player.getController().TradeAction(_isCurrent);
			}

		});

		private PlayerAction action;
		private String name;

		public void useAction(Player _player, boolean _isCurrent) {
			action.action(_player, _isCurrent);
		}

		public String getName() {
			return name;
		}

		Action(String _name, PlayerAction _action) {
			name = _name;
			action = _action;
		}
	}

	public static List<Player> getPlayers() {
		return players;
	}

	public static List<Player> getOtherPlayers(Player _player) {
		ArrayList<Player> otherPlayers = new ArrayList<Player>();
		otherPlayers.addAll(players);
		otherPlayers.remove(_player);
		return otherPlayers;
	}

	/**
	 * this will set up the game creating the correct number of players giving
	 * each of them a different territory setting up the couple of start spaces
	 * ??maybe??
	 * 
	 * @param _numPlayers
	 */
	public void setUpGame(List<Player> _players) {
		territories = new ArrayList<Territory>();
		
		players = _players;
		InputStream i = this
				.getClass()
				.getClassLoader()
				.getResourceAsStream("com/bfi/tek/territories/Territories.json");
		System.out.println(i);
		JSONArray jsonTerritories = new JSONArray();
		try {
			jsonTerritories = (JSONArray) new JSONParser()
					.parse(new BufferedReader(new InputStreamReader(i)));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		for (Object obj : jsonTerritories) {
			JSONObject jsonTerritory = (JSONObject) obj;
			System.out.println(jsonTerritory.get("name"));
			
			Territory territory = Territory.LoadFromJSON(jsonTerritory);
			territories.add(territory);
			
		}
	}

	public void Start() {
		setupPlayers();
		pickFirstPlayer();

		while (!isGameEnding) {
			PlayGameRound();
		}

	}

	protected void PlayGameRound() {
		Action currentAction;
		/**
		 * there are 6 actions that can be taken Patrol Quest Research Expand
		 * Build Trade
		 */
		ArrayList<Action> actions = new ArrayList<Action>();
		for (Action action : Action.values()) {
			if (action != Action.COLLECTRESOURCES) {
				actions.add(action);
			}
		}

		while (actions.size() > 1) {
			currentAction = players.get(currentPlayer).getController()
					.SelectAction(actions);
			actions.remove(currentAction);
			for (int i = 0; i < players.size(); i++) {
				currentAction.useAction(
						players.get((currentPlayer + i) % players.size()),
						i == 0);
			}

			currentPlayer = (currentPlayer + 1) % players.size();

		}
	}

	protected void pickFirstPlayer() {
		Random rand = new Random();
		currentPlayer = rand.nextInt() % (players.size() - 1);
		System.out.println("CurrentPlayer: "+currentPlayer);
	}

	protected void setupPlayers() {
		// first we begin with the setup of the game
		// each player will recieve two Territories and must choose one of them
		// each player needs to gather 6 resources of there choosing
		
		Random rand = new Random();

		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			System.out.println(territories.size());
			Territory territory1 = territories.remove(rand.nextInt(territories.size()));
			System.out.println(territories.size() +"-"+ territory1.getName());
			Territory territory2 = territories.remove(rand.nextInt(territories.size()));
			System.out.println(territories.size()+"-"+ territory2.getName());
			player.setTerritory(player.getController().ChooseTerritory(territory1, territory2));
			player.setResources(player.getController().getInitialResources());
			Space space = player.getController().getStartingSpace();
			player.getFreeUnit().moveToSpace(space);
			player.getFreeUnit().moveToSpace(space);
		}

	}

	/**
	 * Returns true if the Game is ready to begin
	 * 
	 * @return
	 */
	public boolean validateGameIsReady() {
		if (players.size() < 2 || players.size() > 4) {
			return false;
		}

		for (Player player : players) {
			if (player.getRace() == null || player.getController() == null) {
				return false;
			}
		}
		return true;
	}

	// /**
	// * this will figure out the winner and complete all post war effects
	// * @param _attacker
	// * @param _attackerBid
	// * @param _defender
	// * @param _defenderBid
	// * @param _space
	// */
	// public static void WAR(Player _attacker, Unit _attackerUnit,
	// Player _defender, Unit _defenderUnit, Space _space){
	//
	// //REDO war maybe i will add it to the attacking players stuff
	// //to start we need to calculate the possible bids for war
	//
	// int attackerBid = _attacker.getController().bidForWar();
	// int defenderBid = _defender.getController().bidForWar();
	// //first lets calculate the final warEfforts of both players
	// int attackerWarEffort = _attacker.finalWarEffort( attackerBid,
	// _attacker.getController().payForWar(attackerBid));
	// int defenderWarEffort = _defender.finalWarEffort( defenderBid,
	// _defender.getController().payForWar(defenderBid));
	//
	// Player winner = null;
	// Unit winningUnit = null;
	// Player loser = null;
	// Unit losingUnit = null;
	//
	// if(attackerBid==0 && defenderBid==0){
	// //nothing happens they are now in an alliance
	// } else {
	// if(defenderWarEffort >= attackerWarEffort){
	// winner = _defender;
	// winningUnit = _defenderUnit;
	// loser = _attacker;
	// losingUnit = _attackerUnit;
	// } else {
	// winner = _attacker;
	// winningUnit = _attackerUnit;
	// loser = _defender;
	// losingUnit = _defenderUnit;
	// }
	//
	// //now that we have a winner and a loser
	// //the winner takes the space and the users unit is removed from that
	// space
	// _space.removeUnit(losingUnit);
	// losingUnit.setSpace(null);
	//
	// //if the players were in an alliance remove all the winners pieces
	// for(Unit unit : winner.getActiveUnits()){
	// if(unit.getSpace().getPlayers().contains(loser)){
	// unit.getSpace().removeUnit(unit);
	// unit.setSpace(null);
	// }
	// }
	// }
	// }

	private interface PlayerAction {
		public void action(Player _player, boolean _isCurrent);
	}

}
