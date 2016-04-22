package com.bfi.tek.models;

import com.bfi.tek.models.Player.Resources;

public class WaterSpace extends Space {
	public WaterSpace(){
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
		return "Water";
	}

	@Override
	public Resources getResourceType() {
		return null;
	}
}
