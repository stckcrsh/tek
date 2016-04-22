package com.bfi.tek.models;


public class Unit {

	protected Space space;
	protected Player player;
	protected boolean isStanding = true;
	
	public Unit(Player _player){
		player = _player;
		space = null;
	}
	
	//==============GETTERS SETTERS=================
	public Player getPlayer(){return player;}
	public void setPlayer(Player _value){player = _value;}
	
	public Space getSpace(){return space;}
	public void setSpace(Space _value){space = _value;}
	
	public void moveToSpace(Space _to){
		if(space != null){
			space.removeUnit(this);
		}
		this.setSpace(_to);
		_to.addUnit(this);
	}
	
	public boolean IsStanding(){return isStanding;}
	public void setStanding(boolean _isStanding){isStanding =_isStanding;}	
	
	/**
	 * checks if the Unit is active
	 * @return
	 */
	public boolean isActive(){
		return space!=null;
	}
}
