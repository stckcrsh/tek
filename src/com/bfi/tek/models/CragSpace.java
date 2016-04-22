package com.bfi.tek.models;

import com.bfi.tek.models.Player.Resources;

public class CragSpace extends Space {
	
	public CragSpace(){
		super();
	}

	@Override
	public boolean isTraversible() {
		return false;
	}

	@Override
	public int getStackLimit() {
		return 0;
	}

	@Override
	public String getTitle() {
		return "Crag";
	}

	@Override
	public Resources getResourceType() {
		return null;
	}
}
