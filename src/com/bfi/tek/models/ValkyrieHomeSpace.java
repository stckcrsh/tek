package com.bfi.tek.models;

import com.bfi.tek.models.Player.Resources;

public class ValkyrieHomeSpace extends Space {
	
	public ValkyrieHomeSpace(){
		super();
	}

	@Override
	public boolean isTraversible() {
		return true;
	}

	@Override
	public int getStackLimit() {
		return 3;
	}

	@Override
	public String getTitle() {
		return "Valkyrie Home";
	}

	@Override
	public Resources getResourceType() {
		return null;
	}

}
