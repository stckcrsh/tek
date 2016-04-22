package com.bfi.tek.models;

import java.util.ArrayList;
import java.util.List;

import com.bfi.tek.models.Player.Resources;
import com.bfi.tek.territories.Territory;

public abstract class Space {

	public enum SpaceType{
		MOUNTAIN 			(Player.Resources.ORE, "Mountain"),
		FOREST				(Player.Resources.MAGIC, "Forest"),
		PLAINS				(Player.Resources.FOOD, "Plains"),
		WATER				(null, "Water"),
		RUIN				(null, "Ruins"),
		CRAG				(null, "Crag"),
		CAPITALCITY			(null, "Capital City"),
		EDGE				(null, "EDGE"),
		VALKYRIE			(null, "Valkyrie's Home");
		
		private Player.Resources resource;
		private String title;
		
		public String getTitle(){return title;}
		public Resources getResource(){return resource;}
		
		SpaceType(Player.Resources _resource, String _title){
			resource = _resource;
			title = _title;
		}
	}
	
	protected List<Unit> units;
	
	protected List<Space> neighborSpaces;
	protected Territory territory;
	
	public Space(){
		units = new ArrayList<Unit>();
		neighborSpaces = new ArrayList<Space>();
	}
	
	//====================GETTERS SETTERS====================
	
	public Territory getTerritory(){return territory;}
	public void setTerritory(Territory _territory){territory = _territory;}
	
	public abstract boolean isTraversible();
	
	public abstract int getStackLimit();
	
	public abstract String getTitle();
	
	public abstract Resources getResourceType();
	
	public void addNeighborSpace(Space _space){
		if(!neighborSpaces.contains(_space)){
			neighborSpaces.add(_space);
		}
	}
	public List<Space> getNeighborSpaces(){
		return neighborSpaces;
	}
	
	public void addUnit(Unit _unit){
		
		units.add(_unit);
		_unit.setSpace(this);
			
	}
	
	public void removeUnit(Unit _unit){
		units.remove(_unit);
	}
	
	public List<Unit> getUnits(){return units;}
	public Unit getOtherUnit(Unit _unit){
		ArrayList<Unit> otherUnit = new ArrayList<Unit>();
		otherUnit.addAll(_unit.getSpace().getUnits());
		otherUnit.remove(_unit);
		return otherUnit.get(0);
	}
	
	/**
	 * returns a list of the players 
	 * @return
	 */
	public List<Player> getPlayers(){
		ArrayList<Player> players = new ArrayList<Player>();
		for(Unit unit : units){
			if(!players.contains(unit.getPlayer())){players.add(unit.getPlayer());}
		}
		return players;
	}
	
	
}
