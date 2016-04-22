package com.bfi.tek.races;

import com.bfi.tek.models.Player;

public abstract class Race {
	
	protected String title;
	protected Player player;
	
	public abstract void Level1();
	public abstract void Level2();
	public abstract void Level3();
	public abstract void Level4();
	public abstract void Level5();
	
	public String getTitle(){return title;}
	
	public void setPlayer(Player _player){
		player = _player;
	}
}
