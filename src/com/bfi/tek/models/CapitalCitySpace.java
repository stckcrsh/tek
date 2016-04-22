package com.bfi.tek.models;

import com.bfi.tek.models.Player.Resources;

public class CapitalCitySpace extends Space {

	public CapitalCitySpace(){
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
		return "Capital City";
	}

	@Override
	public Resources getResourceType() {
		return null;
	}

}
