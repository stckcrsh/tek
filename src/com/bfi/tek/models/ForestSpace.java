package com.bfi.tek.models;

import com.bfi.tek.models.Player.Resources;

public class ForestSpace extends Space {
	public ForestSpace(){
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
		return "Forest";
	}

	@Override
	public Resources getResourceType() {
		return Player.Resources.MAGIC;
	}
}
