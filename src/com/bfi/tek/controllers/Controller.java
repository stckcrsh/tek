package com.bfi.tek.controllers;

import java.util.List;

import com.bfi.tek.main.Game.Action;
import com.bfi.tek.models.Player;
import com.bfi.tek.models.Space;
import com.bfi.tek.territories.Territory;

public abstract class Controller {

	protected Player player;
	
	
	//======================GETTERS SETTERS==========================
	public void setPlayer(Player _value){
		player = _value;
		player.setController(this);
	}
	
	public Player getPlayer(){return player;}
	
	// the controller class will need the different aspects 
	// of each of the actions and how the decisions will be made
	
	// part of the player setup COMPARTMENTALIZE
	public abstract Territory ChooseTerritory(Territory _one, Territory _two);
	public abstract int[] getInitialResources();
	public abstract Space getStartingSpace();
	public abstract String getPlayerName();
	
	// Action Information
	public abstract Action SelectAction(List<Action> _actions);
	
	public abstract void PatrolAction(boolean _isCurrent);
	public abstract void QuestAction(boolean _isCurrent);
	public abstract void ExpandAction(boolean _isCurrent);
	public abstract void TradeAction(boolean _isCurrent);
	public abstract void BuildAction(boolean _isCurrent);
	public abstract void ResearchAction(boolean _isCurrent);
	public abstract void CollectResources();
	
	public abstract int bidForWar(Player _enemy);
	public abstract int[] payForWar(int _bid);

	public abstract boolean willUseAbility(String _message);
}
