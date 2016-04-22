package com.bfi.tek.models;

import java.util.List;

import com.bfi.tek.main.Game;

public interface PlayerInterface {
	
	//Instead of actions
	public List<Game.Action>insteadOfActions(Player _player, Game.Action _insteadOfAction);
	
	//Trade
	public boolean tradeValidation(int[] _resources, boolean _initial);
	public void tradeResolution(Player _player, int[] _resources);
	
	//Patrol
	public boolean patrolValidation(Unit _unit, Space _from, Space _to, boolean _initial);
	public void patrolResolution(Unit _unit, Space _from, Space _to);
	
	//Quest
	public boolean questValidation(Unit _unit, Space _from, Space _to, boolean _initial);
	public void questResolution(Unit _unit, Space _from, Space _to);
	
	//Research
	public int calcResearchCost(Player _player, int _initialCost);
	public boolean validateResearchPayment(int _cost, int[] _resources, boolean _initial);
	public void researchResolution(Player _player);
	
	//Build
	public int calcBuildCost(Player _player, int _initialCost);
	public boolean validateBuildPayment(int _cost, int[] _resources, boolean _initial);
	public void buildResolution(Player _player);
	
	//Expand
	public int calcExpandCost(Player _player, int _initialCost);
	public boolean expandValidation(Player _player, Space _space, boolean _initial);
	public boolean validateExpandPayment(int _cost, int[] _resources);
	public void expandResolution(Player _player);
	
	//Collect Resources
	public boolean validateResourceSpace(Player _player, Space _space, boolean _initial);
	public int[] calcResourcesReceived(Player _player, List<Space> _spaces, int[] _initialResources);
	public void collectResolution(Player _player, List<Space> _spaces, int[] _resources);
	
	//Score listener
	public int calcScore(int _initialScore);
	
	//war 
	public int[] calcResourceWarEffort(int[] _warEffort, Player _enemy);
	public int calcWarEffort(int[] _resources, int _initialWarEffort);
	public void warResolution(Player _winner, Player _loser);
}
