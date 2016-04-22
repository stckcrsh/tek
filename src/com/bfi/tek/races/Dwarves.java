package com.bfi.tek.races;

import java.util.ArrayList;
import java.util.List;

import com.bfi.tek.main.Game;
import com.bfi.tek.main.Game.Action;
import com.bfi.tek.models.MountainSpace;
import com.bfi.tek.models.Player;
import com.bfi.tek.models.PlayerAdapter;
import com.bfi.tek.models.Space;
import com.bfi.tek.models.Unit;

public class Dwarves extends Race {

	public Dwarves(){
		title="Dwarves";
	}
	
	@Override
	public void Level1() {
		// if a player is trying to build to your spot on the tower +1 cost
		List<Player> otherPlayers = Game.getOtherPlayers(player);
		
		for(Player otherPlayer : otherPlayers){
			otherPlayer.addCalcBuildCostListener(new PlayerAdapter(){
	
				@Override
				public int calcBuildCost(Player _player, int _initialCost) {
					if(_player.getTowerLv()+1==player.getTowerLv()){
						return _initialCost+1;
					} else {
						return _initialCost;
					}
				}
				
			});
		}
	}

	@Override
	public void Level2() {
		// when you patrol or quest to a mountain space gain +1 ore
		PlayerAdapter pa = new PlayerAdapter(){
			
			@Override
			public void patrolResolution(Unit _unit, Space _from, Space _to) {
				int[] resources = new int[3];
				resources[0] = 0;
				resources[1] = 0;
				resources[2] = 0;
				resources[Player.Resources.ORE.getId()] = 1;
				if(_to.getClass()==MountainSpace.class){
					player.addResources(resources);
				}
			}

			@Override
			public void questResolution(Unit _unit, Space _from, Space _to) {
				int[] resources = new int[3];
				resources[0] = 0;
				resources[1] = 0;
				resources[2] = 0;
				resources[Player.Resources.ORE.getId()] = 1;
				if(_to.getClass() == MountainSpace.class){
					player.addResources(resources);
				}
			}
			
		};
		
		player.addPatrolResolutionListener(pa);
		player.addQuestResolutionListener(pa);
	}

	@Override
	public void Level3() {
		// building cost 1 less
		player.addCalcBuildCostListener(new PlayerAdapter(){

			@Override
			public int calcBuildCost(Player _player, int _initialCost) {
				return (_initialCost-1<0)?0:_initialCost-1;
			}
			
		});
	}

	@Override
	public void Level4() {
		// build instead of trade
		player.addInsteadOfActionListener(new PlayerAdapter(){

			@Override
			public List<Action> insteadOfActions(Player _player,
					Action _insteadOfAction) {
				List<Game.Action> actions = new ArrayList<Game.Action>(); 
				if(_insteadOfAction == Game.Action.TRADE){
					actions.add(Game.Action.BUILD);
				}
				
				return actions;
			}
			
		});
		
	}

	@Override
	public void Level5() {
		// if you are highest or tied for highest in tower +2 score
		player.addCalcScoreListener(new PlayerAdapter(){

			@Override
			public int calcScore(int _initialScore) {
				List<Player> otherPlayers = Game.getPlayers();
				otherPlayers.remove(player);
				
				int bonus = 0;
				for(Player otherPlayer : otherPlayers){
					if(otherPlayer.getTowerLv()<=player.getTowerLv()){
						bonus++;
					}
				}
				
				return _initialScore+bonus;
			}
			
		});

	}

}
