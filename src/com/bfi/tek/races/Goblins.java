package com.bfi.tek.races;

import com.bfi.tek.main.Game;
import com.bfi.tek.models.Player;
import com.bfi.tek.models.PlayerAdapter;

public class Goblins extends Race {

	public Goblins(){
		title="Goblins";
	}
	
	@Override
	public void Level1() {
		// when patrolling may move two cubes

	}

	@Override
	public void Level2() {
		// if you win a battle gain +1 resource
		player.addWarResolutionListener(new PlayerAdapter(){

			@Override
			public void warResolution(Player _winner, Player _loser) {
				if(_winner == player){
					//TODO ask for one resource
				}
			}
			
		});

	}

	@Override
	public void Level3() {
		// When another player expands gain +1 food
		for(Player otherPlayer : Game.getOtherPlayers(player)){
			otherPlayer.addExpandResolutionListener(new PlayerAdapter(){

				@Override
				public void expandResolution(Player _player) {
					int[] resources = new int[3];
					resources[0] = 0;
					resources[1] = 0;
					resources[2] = 0;
					resources[Player.Resources.FOOD.getId()] = 1;
					player.addResources(resources);
				}
				
			});
		}
	}

	@Override
	public void Level4() {
		// if you lose a battle steal a resource from the winner
		player.addWarResolutionListener(new PlayerAdapter(){

			@Override
			public void warResolution(Player _winner, Player _loser) {
				if(_loser == player){
					//TODO steal a resource from the loser
					
				}
			}
			
		});
	}

	@Override
	public void Level5() {
		// if you have or are tied for the highest population +2 score
		player.addCalcScoreListener(new PlayerAdapter(){

			@Override
			public int calcScore(int _initialScore) {
				int bonus = 0;
				
				for(Player otherPlayer : Game.getOtherPlayers(player)){
					if(otherPlayer.getActiveUnits().size()<=player.getActiveUnits().size()){
						bonus ++;
					}
				}
				
				return _initialScore + bonus;
			}
			
		});
	}

}
