package com.bfi.tek.models;

import com.bfi.tek.models.Player.Resources;

public class EdgeSpace extends Space {

	public EdgeSpace(){
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
		return "Edge";
	}

	@Override
	public Resources getResourceType() {
		return null;
	}
}
