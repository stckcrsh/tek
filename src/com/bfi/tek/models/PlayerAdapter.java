package com.bfi.tek.models;

import java.util.List;

import com.bfi.tek.main.Game.Action;

public class PlayerAdapter implements PlayerInterface {

	@Override
	public boolean tradeValidation(int[] _resources, boolean _initial) {
		
		return false;
	}

	@Override
	public void tradeResolution(Player _player, int[] _resources) {
		

	}

	@Override
	public boolean patrolValidation(Unit _unit, Space _from, Space _to, boolean _initial) {
		
		return false;
	}

	@Override
	public void patrolResolution(Unit _unit, Space _from, Space _to) {
		

	}

	@Override
	public boolean questValidation(Unit _unit, Space _from, Space _to, boolean _initial) {
		
		return false;
	}

	@Override
	public void questResolution(Unit _unit, Space _from, Space _to) {
		

	}

	@Override
	public int calcResearchCost(Player _player, int _initialCost) {
		
		return 0;
	}

	@Override
	public boolean validateResearchPayment(int _cost, int[] _resources, boolean _initial) {
		
		return false;
	}

	@Override
	public void researchResolution(Player _player) {
		

	}

	@Override
	public int calcBuildCost(Player _player, int _initialCost) {
		
		return 0;
	}

	@Override
	public boolean validateBuildPayment(int _cost, int[] _resources, boolean _initial) {
		
		return false;
	}

	@Override
	public void buildResolution(Player _player) {
		

	}

	@Override
	public int calcExpandCost(Player _player, int _initialCost) {
		
		return 0;
	}

	@Override
	public boolean expandValidation(Player _player, Space _space, boolean _initial) {
		
		return false;
	}

	@Override
	public boolean validateExpandPayment(int _cost, int[] _resources) {
		
		return false;
	}

	@Override
	public void expandResolution(Player _player) {
		

	}

	@Override
	public boolean validateResourceSpace(Player _player, Space _space, boolean _initial) {
		
		return false;
	}

	@Override
	public int[] calcResourcesReceived(Player _player, List<Space> _spaces,
			int[] _initialResources) {
		
		return null;
	}

	@Override
	public void collectResolution(Player _player, List<Space> _spaces, int[] _resources) {
		
		
	}

	@Override
	public int calcScore(int _initialScore) {
		
		return 0;
	}

	@Override
	public List<Action> insteadOfActions(Player _player, Action _insteadOfAction) {
		
		return null;
	}

	@Override
	public int[] calcResourceWarEffort(int[] _warEffort, Player _enemy) {
		
		return null;
	}

	@Override
	public int calcWarEffort(int[] _resources, int _initialWarEffort) {
		
		return 0;
	}

	@Override
	public void warResolution(Player _winner, Player _loser) {
		
		
	}

}
