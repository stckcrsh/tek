package com.bfi.tek.models;

import java.util.HashMap;

import com.bfi.tek.models.Player.Resources;

public class RuinsSpace extends Space {

	private HashMap<Unit, Boolean> isLayingDown;
	
	private PlayerAdapter adapter; 
	
	@Override
	public void addUnit(Unit _unit) {
		if(!isLayingDown.containsKey(_unit)){
			isLayingDown.put(_unit, true);
		} else {
			isLayingDown.put(_unit, false);
		}
		
		_unit.getPlayer().addPatrolValidationListener(adapter);
		super.addUnit(_unit);
	}
	
	@Override
	public void removeUnit(Unit _unit) {		
		super.removeUnit(_unit);
		
		if(!this.getPlayers().contains(_unit.getPlayer())){
			_unit.getPlayer().removePatrolValidationListener(adapter);
		}
	}
	
	public RuinsSpace(){
		super();
		isLayingDown = new HashMap<Unit, Boolean>();
		adapter = new PlayerAdapter(){

			@Override
			public boolean patrolValidation(Unit _unit, Space _from, Space _to, boolean _isValid) {
				// if the space is the ruins space and the unit is laying down 
				if(_from==_to && _to.getClass()==RuinsSpace.class){
					return true;
				}
				
				return _isValid;
			}
			
			
		};
	}

	@Override
	public boolean isTraversible() {
		return true;
	}

	@Override
	public int getStackLimit() { 
		return 2;
	}

	@Override
	public String getTitle() { 
		return "Ruins";
	}

	@Override
	public Resources getResourceType() {
		return Resources.WILD;
	}
}
