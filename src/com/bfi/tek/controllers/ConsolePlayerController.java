package com.bfi.tek.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.bfi.tek.main.Game;
import com.bfi.tek.main.Game.Action;
import com.bfi.tek.models.Player;
import com.bfi.tek.models.Player.Resources;
import com.bfi.tek.models.Space;
import com.bfi.tek.models.Unit;
import com.bfi.tek.territories.Territory;


public class ConsolePlayerController extends Controller {

	@Override
	public Action SelectAction(List<Action> _actions) {
		displayPlayerInformation();
		displayTerritoryInformation();
		
		Scanner user_input = new Scanner(System.in);
		
		int input = -1;
		String temp;
		while(input<0 || input>_actions.size()){
			try{
				System.out.printf("%s choose an action\n", player.getName());
				for(int i = 0 ; i < _actions.size(); i++){
					System.out.printf("%d: %s\n", i, _actions.get(i).toString());
				}
				System.out.printf("Enter your choice: ");
				temp = user_input.nextLine();
				input = Integer.parseInt(temp);
			} catch(NumberFormatException ex){
			    System.out.println("Its not a valid Integer try again");
			    input = -1;
			    continue;
			}
		
		}
		
		return _actions.get(input);
		
	}

	@Override
	public void PatrolAction(boolean _isCurrent) {
		Game.Action thisAction = Game.Action.PATROL;
		Game.Action action = getGameAction(thisAction, _isCurrent);
		
		if(action == thisAction){
			Scanner user_input;
			
			System.out.printf("%s: PATROL ACTION\n", player.getName());
			List<Unit> activeUnits = player.getActiveUnits();
			
			int unitChoice = -1;
			String temp;
			user_input = new Scanner(System.in);
			while(unitChoice < 0 || unitChoice > activeUnits.size()){
				
				try {
					System.out.printf("\tTerritory\tType\n");
					//first choose the unit that you want to move
					for(int i = 0; i<activeUnits.size();i++){
						System.out.printf("%d: \t%s\t%s\n", i, activeUnits.get(i).getSpace().getTerritory().getPlayer().getName(), activeUnits.get(i).getSpace().getTitle());
					}
					System.out.print("Enter your choice: ");
					temp = user_input.nextLine();
					unitChoice = Integer.parseInt(temp);
				} catch(NumberFormatException ex){
				    System.out.println("Its not a valid Integer try again");
				    unitChoice = -1;
				    continue;
				}
			}
			
			Unit activeUnit = activeUnits.get(unitChoice);
			List<Space> patrolOptions = player.patrolOptions(activeUnit);
			
			int patrolChoice = -1;
			user_input = new Scanner(System.in);
			while(patrolChoice < 0 || patrolChoice > patrolOptions.size()){
				
				try { 
					System.out.printf("Patrol Options: \n");
					
					for(int i=0;i<patrolOptions.size();i++){
						System.out.printf("%d: %s\n", i, patrolOptions.get(i).getTitle());
					}
					System.out.printf("Enter your choice: ");
					temp = user_input.nextLine();
					patrolChoice = Integer.parseInt(temp);
				} catch(NumberFormatException ex){
				    System.out.println("Its not a valid Integer try again");
				    patrolChoice = -1;
				    continue;
				}
			}
			
			player.patrol(activeUnit, patrolOptions.get(patrolChoice));
		} else {
			action.useAction(player, true);
		}
	}
	
	@Override
	public void QuestAction(boolean _isCurrent) {
		Game.Action thisAction = Game.Action.QUEST;
		Game.Action action = getGameAction(thisAction, _isCurrent);
		
		if(action == thisAction){
			System.out.printf("%s: QUEST ACTION\n", player.getName());
			
			Scanner user_input;
			List<Unit> activeUnits = player.getActiveUnits();
			
			int unitChoice = -1;
			String temp;
			user_input = new Scanner(System.in);
			while(unitChoice < 0 || unitChoice > activeUnits.size()){
				try {
					System.out.printf("\tTerritory\tType\n");
					//first choose the unit that you want to move
					for(int i = 0; i<activeUnits.size();i++){
						System.out.printf("%d: \t%s\t%s\n", i, activeUnits.get(i).getSpace().getTerritory().getPlayer().getName(), activeUnits.get(i).getSpace().getTitle());
					}
					System.out.print("Enter your choice: ");
					temp = user_input.nextLine();
					unitChoice = Integer.parseInt(temp);
				} catch(NumberFormatException ex){
				    System.out.println("Its not a valid Integer try again");
				    unitChoice = -1;
				    continue;
				}
			}
			
			Unit activeUnit = activeUnits.get(unitChoice);
			List<Space> questOptions = player.questOptions(activeUnit);
			
			int questChoice = -1;
			user_input = new Scanner(System.in);
			while(questChoice < 0 || questChoice > questOptions.size()){
				
				try { 
					System.out.printf("Quest Options\n");
					
					for(int i=0;i<questOptions.size();i++){
						System.out.printf("%d: %s\t%s\n", i, questOptions.get(i).getTerritory().getPlayer().getName(),questOptions.get(i).getTitle());
					}
					System.out.printf("Enter your choice: ");
					temp = user_input.nextLine();
					questChoice = Integer.parseInt(temp);
				} catch(NumberFormatException ex){
				    System.out.println("Its not a valid Integer try again");
				    questChoice = -1;
				    continue;
				}
			}
			
			player.quest(activeUnit, questOptions.get(questChoice));
			
		}else {
			action.useAction(player, true);
		}
		
	}
	
	@Override
	public void ExpandAction(boolean _isCurrent) {
		Game.Action thisAction = Game.Action.EXPAND;
		Game.Action action = getGameAction(thisAction, _isCurrent);
		
		if(action == thisAction){
			Scanner user_input;
			int[] resources = new int[3];
			int cost = player.calcExpandCost();
			do{
				resources = askForResources("Pay for expand with ("+cost+") Food");
			} while (!player.validateExpandPayment(cost, resources));
			
			System.out.printf("%s: EXPAND ACTION\n", player.getName());
			
			List<Space> expandableSpaces = player.expandOptions();
			System.out.printf("\tTerritory\tType\n");
			
			int input = -1;
			String temp;
			user_input = new Scanner(System.in);
			while(input < 0 || input > expandableSpaces.size()){
				
				try { 
					System.out.printf("Expand Options\n");
					
					for(int i=0;i<expandableSpaces.size();i++){
						System.out.printf("%d: %s\t%s\n", i, expandableSpaces.get(i).getTerritory().getPlayer().getName(),expandableSpaces.get(i).getTitle());
					}
					System.out.printf("Enter your choice: ");
					temp = user_input.nextLine();
					input = Integer.parseInt(temp);
				} catch(NumberFormatException ex){
				    System.out.println("Its not a valid Integer try again");
				    input = -1;
				    continue;
				}			
			}
			
			player.expand(expandableSpaces.get(input), resources);
		} else { 
			action.useAction(player, true);
		}
	}
	
	@Override
	public void TradeAction(boolean _isCurrent) {
		Game.Action thisAction = Game.Action.TRADE;
		Game.Action action = getGameAction(thisAction, _isCurrent);
		
		if(action == thisAction){
			Scanner user_input;
			
			int[] tradeResources = new int[3];
			boolean tryAgain = false;
			
			displayPlayerInformation();
			
			System.out.printf("%s: TRADE ACTION\n", player.getName());
			System.out.printf("Trade Options\n");
			
			int[] giveResources = new int[3];
			int[] getResources = new int[3];
			do{
				giveResources = askForResources("Give Resources");
				getResources = askForResources("Get Resources");
				
				for(int i = 0;i<3;i++){
					getResources[i] -= giveResources[i];
				}
				
			} while (!player.isTradeValid(getResources));
			
//			String temp;
//			int give, get, amount, maxResources = 0;
//			do {
//				
//				user_input = new Scanner(System.in);
//				tradeResources[0] = 0;
//				tradeResources[1] = 0;
//				tradeResources[2] = 0;
//				if(tryAgain){System.out.println("Try Again");}
//				
//				tryAgain = false;
//				
//				try{
//					
//					System.out.printf("Give\n");
//					System.out.printf("Ore: ");
//					temp = user_input.nextLine();
//					tradeResources[Player.Resources.ORE.getId()] += Integer.parseInt(temp)*-1;
//					
//					System.out.printf("Magic: ");
//					temp = user_input.nextLine();
//					tradeResources[Player.Resources.MAGIC.getId()] += Integer.parseInt(temp)*-1;
//					
//					System.out.printf("Food: ");
//					temp = user_input.nextLine();
//					tradeResources[Player.Resources.FOOD.getId()] += Integer.parseInt(temp)*-1;
//					
//					System.out.printf("Get\n");
//					System.out.printf("Ore: ");
//					temp = user_input.nextLine();
//					tradeResources[Player.Resources.ORE.getId()] += Integer.parseInt(temp);
//					
//					System.out.printf("Magic: ");
//					temp = user_input.nextLine();
//					tradeResources[Player.Resources.MAGIC.getId()] += Integer.parseInt(temp);
//					
//					System.out.printf("Food: ");
//					temp = user_input.nextLine();
//					tradeResources[Player.Resources.FOOD.getId()] += Integer.parseInt(temp);
//					
//				
//				} catch(NumberFormatException ex){
//				    System.out.println("Invalid choice try again");
//				    tryAgain = true;
//				    continue;
//				}
//				
//			} while(!player.isTradeValid(tradeResources) || tryAgain);
			
			player.trade(tradeResources);
		} else { 
			action.useAction(player, true);
		}
	}

	/**
	 * This is where we get the information to complete the build action
	 */
	@Override
	public void BuildAction(boolean _isCurrent) {
		Game.Action thisAction = Game.Action.BUILD;
		Game.Action action = getGameAction(thisAction, _isCurrent);
		
		if(action == thisAction){
			System.out.printf("%s: BUILD ACTION\n", player.getName());
			int[] resources = new int[3];
			int cost = player.calcBuildCost();
			do{
				resources = askForResources("Pay for Tower with ("+cost+") Ore");
			} while (!player.validateBuildPayment(cost, resources));
			player.build(resources);
			
			System.out.printf("%s's TowerLv: %d\n", player.getName(), player.getTowerLv());
		} else { 
			action.useAction(player, true);
		}
	}

	/**
	 * This is where we get the information to complete the research action
	 */
	@Override
	public void ResearchAction(boolean _isCurrent) {
		Game.Action thisAction = Game.Action.RESEARCH;
		Game.Action action = getGameAction(thisAction, _isCurrent);
		
		if(action == thisAction){
			System.out.printf("%s: RESEARCH ACTION\n", player.getName());
			int[] resources = new int[3];
			int cost = player.calcResearchCost();
			do{
				resources = askForResources("Pay for Magic with ("+cost+") Mana");
			} while (!player.validateResearchPayment(cost, resources));
			player.research(resources);
			
			System.out.printf("%s's MagicLv: %d\n", player.getName(), player.getMagicLv());
		} else {
			action.useAction(player, true);
		}
		
	}
	
	/**
	 * helper method that is called at the beginning of each action
	 * @param _currentAction
	 * @param _isCurrent
	 * @return
	 */
	public Game.Action getGameAction(Game.Action _currentAction, boolean _isCurrent){
		List<Game.Action> insteadOfActions = new ArrayList<Game.Action>();
		insteadOfActions.addAll(player.insteadOfActions(_currentAction));
		if(!_isCurrent){
			insteadOfActions.add(Game.Action.COLLECTRESOURCES);
			insteadOfActions.add(_currentAction);
			_currentAction = chooseOtherAction(insteadOfActions, _isCurrent);
		} else {
			if(insteadOfActions.size()>0){
				insteadOfActions.add(_currentAction);
				_currentAction = chooseOtherAction(insteadOfActions, _isCurrent);
			} 
		}
		
		return _currentAction;
	}

	private Game.Action chooseOtherAction(List<Action> _insteadOfActions, boolean _isCurrent) {
		Scanner user_input;
		
		int input = -1;
		
		System.out.printf("%s choose an action\n", player.getName());
		
		do{
			user_input = new Scanner(System.in);
			
			for(int i = 0;i<_insteadOfActions.size();i++){
				
				System.out.printf("%d: %s\n", i, _insteadOfActions.get(i).getName());
			}
			System.out.printf("Choose an option: ");
			
			String temp = "";
			try{
				temp = user_input.next();
				input = Integer.parseInt(temp);
				if(input < 0 || input > _insteadOfActions.size()){
					throw new NumberFormatException();
				}
			} catch(NumberFormatException ex){
			    System.out.println( temp + " is not a valid Selection try again");
			    continue;
			}
			
		} while (input < 0 || input > _insteadOfActions.size());
	
		return _insteadOfActions.get(input);
		
	}

	@Override
	public void CollectResources() {
		System.out.printf("%s's resources\n", player.getName());
		System.out.println("before");
		System.out.printf("Ore: %s\nFood: %s\nMagic: %s\n", player.getOre(), player.getFood(), player.getMagic());
		List<Space> spaces = player.validateCollectResourceSpaces();
		int[] resources = player.calcResourcesReceived(spaces);
		
		if(resources.length > 3 && resources[3] > 0){
			int[] tempResources = new int[3];
			tempResources[0] = 0;
			tempResources[1] = 0;
			tempResources[2] = 0;
			
			do{
				tempResources = askForResources(resources[3] + " Wilds to choose from");
			} while((tempResources[Player.Resources.ORE.getId()]+
					tempResources[Player.Resources.MAGIC.getId()]+
					tempResources[Player.Resources.FOOD.getId()])!=resources[3]);
			
			resources[0] += tempResources[0];
			resources[1] += tempResources[1];
			resources[2] += tempResources[2];
			resources[3] = 0;
		}
		
		player.collectResources(spaces, resources);
		System.out.println("after");
		System.out.printf("Ore: %s\nFood: %s\nMagic: %s\n", player.getOre(), player.getFood(), player.getMagic());
	}

	
	private boolean hasChosenCollect(String _actionName) {
		Scanner user_input;
		String firstCharacter = _actionName.substring(0, 1);
		String input = "";
		user_input = new Scanner(System.in);
		while(!input.toLowerCase().equals("c") && !input.toLowerCase().equals(firstCharacter.toLowerCase())){
			
			System.out.printf("(C)ollect Resources\n(%1s)%s\nEnter Choice: ", firstCharacter.toUpperCase(), _actionName.substring(1));
			input = user_input.next();
		} 
	
		
		return input.toLowerCase().equals("c");
	}

	@Override
	public int bidForWar(Player _enemy) {
		Scanner user_input;
		
		//calculate the possible bid you can make
		int possibleBid = player.calcPossibleWarEffort(_enemy);
		int bid = -1;
		
		String temp;
		while(bid < 0 || bid > possibleBid){
			user_input = new Scanner(System.in);
			try{
				System.out.printf("%s bid for war(0-%d): ", player.getName(), possibleBid);
				temp = user_input.nextLine();
				bid = Integer.parseInt(temp);
			} catch(NumberFormatException ex){
			    System.out.println("Its not a valid Integer try again");
			    bid = -1;
			    continue;
			}	
		}
		
		return bid;
		
	}

	@Override
	public int[] payForWar(int _bid) {
		int[] resources = new int[3];
		resources[0] = 0;
		resources[1] = 0;
		resources[2] = 0;
		
		int[] playerResources = player.getResources();
		Scanner user_input;
		
		String again = "n";
		
		String temp;
		int check;
		while(!again.equals("y") && !again.equals("Y")){
			check = 0;
			user_input = new Scanner(System.in);
			System.out.printf("%s pay for war\n", player.getName());
			
			try{
				
				System.out.printf("ORE (%d) amount: ", playerResources[Player.Resources.ORE.getId()]);
				temp = user_input.nextLine();
			    resources[Player.Resources.ORE.getId()] = Integer.parseInt(temp);
			    if(resources[Player.Resources.ORE.getId()]>playerResources[Player.Resources.ORE.getId()]){
			    	throw new NumberFormatException();
			    }
				System.out.printf("FOOD (%d) amount: ", playerResources[Player.Resources.FOOD.getId()]);
				temp = user_input.nextLine();
				resources[Player.Resources.FOOD.getId()] = Integer.parseInt(temp);
				if(resources[Player.Resources.FOOD.getId()]>playerResources[Player.Resources.FOOD.getId()]){
			    	throw new NumberFormatException();
			    }
				System.out.printf("MAGIC (%d) amount: ", playerResources[Player.Resources.MAGIC.getId()]);
				temp = user_input.nextLine();
				resources[Player.Resources.MAGIC.getId()] = Integer.parseInt(temp);
				if(resources[Player.Resources.MAGIC.getId()]>playerResources[Player.Resources.MAGIC.getId()]){
			    	throw new NumberFormatException();
			    }
			}
			catch(NumberFormatException ex){
			    System.out.println("Invalid Entry try again");
			    continue;
			}
			
			
			for(Resources resource : Resources.values()){
				check += ((resources[resource.getId()]<0)?0:resources[resource.getId()]) * resource.WarEffort();
			}
			if(check<_bid){
				System.out.printf("must have atleast %d effort\n", _bid);
			} else {
				for(int i = 0;i<resources.length;i++){
					resources[i] *= -1;
				}
			
				System.out.printf("ORE: %d\nFOOD: %d\nMAGIC: %d\n", 
						resources[Player.Resources.ORE.getId()], 
						resources[Player.Resources.FOOD.getId()], 
						resources[Player.Resources.MAGIC.getId()]);
				System.out.print("Is this correct(y/n): ");
				again = user_input.next();
			}
			
			
		}
		
		return resources;
	}
	
	private void displayPlayerInformation(){
		System.out.printf("%s\t SCORE: %d\nResources\tSpecial\n"
				+ "Ore:   \t%d\tTowerLV: \t%d\n"
				+ "Magic: \t%d\tMagicLv: \t%d\n"
				+ "Food:  \t%d\tUnits:   \t%d\n", 
				player.getName(), player.calcScore(),
				player.getOre(), player.getTowerLv(),
				player.getMagic(), player.getMagicLv(),
				player.getFood(), player.getActiveUnits().size());
		
	}
	
	private void displayTerritoryInformation(){
		System.out.printf("\nTERRITORY\n");
		System.out.printf("Type    \t|Unit 1 \t|Unit 2 \t\n");
		for(Space space : player.getTerritory().getSpaces()){
			System.out.printf("%-8s\t|%-7s \t|%-7s \t\n",space.getTitle(), 
					space.getUnits().size()>0?space.getUnits().get(0).getPlayer().getName():"null", 
					space.getUnits().size()>1?space.getUnits().get(1).getPlayer().getName():"null");
		}
	}
	
	private int[] askForResources(String _query){
		Scanner user_input;
		int[] resources = new int[3];
		String again = "n";
		
		String temp = null;
		do {
			user_input = new Scanner(System.in);
			System.out.println(_query);
			
			try{
				
				System.out.print("ORE amount: ");
				temp = user_input.nextLine();
			    resources[Player.Resources.ORE.getId()] = Integer.parseInt(temp);
				System.out.print("FOOD amount: ");
				temp = user_input.nextLine();
				resources[Player.Resources.FOOD.getId()] = Integer.parseInt(temp);
				System.out.print("MAGIC amount: ");
				temp = user_input.nextLine();
				resources[Player.Resources.MAGIC.getId()] = Integer.parseInt(temp);
			}
			catch(NumberFormatException ex){
			    System.out.println( temp + " is not a valid Integer try again");
			    continue;
			}
			
			System.out.printf("ORE: %d\nFOOD: %d\nMAGIC: %d\n", resources[Player.Resources.ORE.getId()], 
					resources[Player.Resources.FOOD.getId()], 
					resources[Player.Resources.MAGIC.getId()]);
			System.out.print("Is this correct(y/n): ");
			again = user_input.next();
		
			
			
		} while(!again.equals("y") && !again.equals("Y"));
		
		return resources;
	}

	@Override
	public boolean willUseAbility(String _message) {
		Scanner user_input;
		
		user_input = new Scanner(System.in);
		System.out.printf("%s\n(Y)es/(N)o",_message);
		String again = "y";
		do{
			again = user_input.nextLine();
		} while(!again.toLowerCase().equals("y") && !again.toLowerCase().equals("n"));
		
		return again.toLowerCase().equals("y");
			
	}

	@Override
	public Territory ChooseTerritory(Territory _one, Territory _two) {
		//TODO finish this for now just choose the first one
		
		System.out.println("Chosen Territory: "+_one.getName());
		return _one;
	}

	@Override
	public int[] getInitialResources() {
		//get initial resource amount must have 6 total
		int[] resources = new int[3];
		do{
			resources = askForResources("Initial Resources: must have 6 total");
		} while((resources[Player.Resources.ORE.getId()]+
				resources[Player.Resources.MAGIC.getId()]+
				resources[Player.Resources.FOOD.getId()])!=6);
		
		return resources;
	}

	@Override
	public Space getStartingSpace() {
		Scanner user_input = new Scanner(System.in);
		
		//set the initial space for the player
		//first get a list of the potential starting spots
		ArrayList<Space> startingSpaces = new ArrayList<Space>();
		for(Space space : player.getTerritory().getSpaces()){
			startingSpaces.add(space);
		}
		
		String temp;
		int spaceID = -1;
		while(spaceID < 0 || spaceID > startingSpaces.size()){
			user_input = new Scanner(System.in);
			try{
				System.out.println(player.getTerritory().getName());
				System.out.println("Starting space");
				for(int i = 0 ; i < startingSpaces.size(); i++){
					System.out.printf("%d: %s\n", i, startingSpaces.get(i).getTitle());
				}
				System.out.printf("Choose a space: ");
				temp = user_input.nextLine();
			    spaceID = Integer.parseInt(temp);
			}catch(NumberFormatException ex){
			    System.out.println("Its not a valid Integer try again");
			    continue;
			}
			
		}
		
		return startingSpaces.get(spaceID);
	}

	@Override
	public String getPlayerName() {
		Scanner user_input = new Scanner(System.in);
		String again="n";
		
		String name = "";
		while(!again.equals("y") && !again.equals("Y")){
			user_input = new Scanner(System.in);
			System.out.print("Player Name: ");
			name = user_input.next();
			System.out.printf("Is %s correct(y/n): ", name);
			again = user_input.next();
		}
		
		return name;
	}
	
}
