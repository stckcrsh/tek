package com.bfi.tek.models;


public class SpaceFactory{
	
	public Space getSpace(Space.SpaceType _type){
		if(_type==null){
			return null;
		}
		
		switch(_type){
			case MOUNTAIN: return new MountainSpace();
			case FOREST: return new ForestSpace();
			case PLAINS: return new PlainsSpace();
			case WATER: return new WaterSpace();
			case RUIN: return new RuinsSpace();
			case CRAG: return new CragSpace();
			case CAPITALCITY: return new CapitalCitySpace();
			case EDGE: return new EdgeSpace();
			case VALKYRIE: return new ValkyrieHomeSpace();
			default: return null;
		}
	}

}
