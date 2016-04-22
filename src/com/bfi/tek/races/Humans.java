package com.bfi.tek.races;

import java.util.ArrayList;
import java.util.List;

import com.bfi.tek.main.Game;
import com.bfi.tek.main.Game.Action;
import com.bfi.tek.models.Player;
import com.bfi.tek.models.Player.Resources;
import com.bfi.tek.models.PlayerAdapter;
import com.bfi.tek.models.Space;
import com.bfi.tek.models.WaterSpace;

public class Humans extends Race{

	public Humans(){
		title="Humans";
	}
	
	@Override
	public void Level1() {
		//humans may trade instead of research
		player.addInsteadOfActionListener(new PlayerAdapter(){

			@Override
			public List<Action> insteadOfActions(Player _player,
					Action _insteadOfAction) {
				List<Game.Action> actions = new ArrayList<Game.Action>();  
				if(_insteadOfAction == Game.Action.RESEARCH){
					actions.add(Game.Action.TRADE);
				}
				
				return actions;				
			}
			
		});
	}

	@Override
	public void Level2() {
		//when you trade gain an extra resource
		player.addTradeResolutionListener(new PlayerAdapter(){

			@Override
			public void tradeResolution(Player _player, int[] _resources) {
				//find the resource that they gained
				int resourceType = -1;
				int[] bonusResources = new int[3];
				bonusResources[0] = 0;
				bonusResources[1] = 0;
				bonusResources[2] = 0;
				
				for(int i = 0;i<3;i++){
					if(_resources[i]>0){
						resourceType = i;
					}
				}
				
				if(resourceType>-1 && resourceType<3){
					bonusResources[resourceType] = 1;
				}
				
				_player.addResources(bonusResources);
			}
			
		});
	}

	@Override
	public void Level3() {
		//if you pay for a war with only one type of good +2 to war effort
		player.addCalcWarEffortListener(new PlayerAdapter(){

			@Override
			public int calcWarEffort(int[] _resources, int _initialWarEffort) {
				if((_resources[Resources.FOOD.getId()]==0 && _resources[Resources.MAGIC.getId()]==0) ||
						(_resources[Resources.ORE.getId()]==0 && _resources[Resources.MAGIC.getId()]==0) ||
						(_resources[Resources.FOOD.getId()]==0 && _resources[Resources.ORE.getId()]==0)){
					return _initialWarEffort + 2;
				}
				
				return _initialWarEffort;
			}
			
		});
	}

	@Override
	public void Level4() {
		//if you have at least one occupied space next to water gain +1 food when Collecting resources
		player.addCalcResourcesRecievedListener(new PlayerAdapter(){

			@Override
			public int[] calcResourcesReceived(Player _player, List<Space> _spaces,
					int[] _initialResources) {
				int[] resources = _initialResources;
				for(Space space : _spaces){
					for(Space neighbor : space.getNeighborSpaces()){
						if(neighbor.getClass() == WaterSpace.class){
							resources[Player.Resources.FOOD.getId()]+=1;
							return resources;
						}
					}
				}
				
				return resources;
			}
			
		});
	}

	@Override
	public void Level5() {
		//for each resource >=4 gain +1 score
		player.addCalcScoreListener(new PlayerAdapter(){

			@Override
			public int calcScore(int _initialScore) {
				int bonusScore = 0;
				for(int resource : player.getResources()){
					if(resource>=4){bonusScore++;}
				}
				
				return _initialScore + bonusScore;
			}
			
		});

	}

}
