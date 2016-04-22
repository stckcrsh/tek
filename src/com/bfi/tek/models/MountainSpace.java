package com.bfi.tek.models;

import com.bfi.tek.models.Player.Resources;

public class MountainSpace extends Space {
	
	public MountainSpace(){
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
		return "Mountain";
	}

	@Override
	public Resources getResourceType() {
		return Resources.ORE;
	}
}
