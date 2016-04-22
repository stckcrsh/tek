package com.bfi.tek.territories;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.bfi.tek.models.EdgeSpace;
import com.bfi.tek.models.Player;
import com.bfi.tek.models.Space;
import com.bfi.tek.models.Space.SpaceType;
import com.bfi.tek.models.SpaceFactory;

public class Territory {
	
	protected String name;

	protected List<Space> spaces;
	
	protected Player player;
	
	public Territory(){
		spaces = new ArrayList<Space>();
	}
	
	//==================GETTERS SETTERS======================
	
	public String getName(){return name;}
	public void setName(String _value){name = _value;}
	
	public Player getPlayer(){return player;}
	public void setPlayer(Player _value){player = _value;}
	
	public List<Space> getSpaces(){return spaces.subList(1, spaces.size());}
	public void setSpaces(List<Space> _spaces){spaces = _spaces;}
	
	public EdgeSpace getEdgeSpace(){return (EdgeSpace) spaces.get(0);}
	
	/**
	 * returns a list of all players on a territory
	 * @return
	 */
	public List<Player> occupyingPlayers(){
		ArrayList<Player> players = new ArrayList<Player>();
		for(Space space : spaces){
			for(Player player : space.getPlayers()){
				if(!players.contains(player)){players.add(player);}
		
			}
		}
		
		return players;
	}
	
	public static Territory LoadFromJSON(JSONObject _json){
		Territory territory = new Territory();
		SpaceFactory spaceFactory = new SpaceFactory();
		
		//set territory name
		territory.setName( (String) _json.get("name"));
		
		//set the initial spaces for the territory
		ArrayList<Space> theSpaces = new ArrayList<Space>();
		
		JSONArray jsonSpaces = (JSONArray) _json.get("spaces");
		for (Object obj : jsonSpaces){
			String spaceType = (String) obj;
			SpaceType type = SpaceType.valueOf(spaceType);
			Space space = spaceFactory.getSpace(type);
			space.setTerritory(territory);
			theSpaces.add(space);
		}
		
		territory.setSpaces(theSpaces);
		
		//set all the neighbors to the spaces
		JSONArray jsonSlots = (JSONArray) _json.get("neighbors");
		for (Object obj : jsonSlots){
			JSONObject jsonSlot = (JSONObject) obj;
			
			int slot = Integer.parseInt((String) jsonSlot.get("slot"));
			
			JSONArray jsonNeighbors = (JSONArray) jsonSlot.get("neighbors");
			for (Object obj1 : jsonNeighbors){
				int neighbor = Integer.parseInt((String) obj1);
				theSpaces.get(slot).addNeighborSpace(theSpaces.get(neighbor));
			}
		}
		
		return territory;
	}
	
}
