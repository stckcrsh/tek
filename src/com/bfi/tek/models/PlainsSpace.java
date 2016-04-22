package com.bfi.tek.models;

import com.bfi.tek.models.Player.Resources;

public class PlainsSpace extends Space {
	
	public PlainsSpace(){
		super();
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
		return "Plains";
	}

	@Override
	public Resources getResourceType() {
		return Resources.FOOD;
	}
}
