package com.bfi.tek.races;

import com.bfi.tek.models.Player;
import com.bfi.tek.models.Player.Resources;
import com.bfi.tek.models.PlayerAdapter;
import com.bfi.tek.models.Space;

public class Orcs extends Race {
	
	public Orcs(){
		title="Orcs";
	}

	@Override
	public void Level1() {
		// Orcs win all ties

	}

	@Override
	public void Level2() {
		// during war Food counts as 1 effort
		player.addCalcResourceWarEffortListener(new PlayerAdapter(){

			@Override
			public int[] calcResourceWarEffort(int[] _warEffort, Player _enemy) {
				_warEffort[Resources.FOOD.getId()] = 1;
				
				return _warEffort;
			}
			
		});

	}

	@Override
	public void Level3() {
		// +1 to all wars
		player.addCalcWarEffortListener(new PlayerAdapter(){

			@Override
			public int calcWarEffort(int[] _resources, int _initialWarEffort) {
				return _initialWarEffort + 1;
			}
			
		});

	}

	@Override
	public void Level4() {
		// may spend an extra mana after resources have been displayed

	}

	@Override
	public void Level5() {
		// if no other player occupies the orcs territory +2 score
		player.addCalcScoreListener(new PlayerAdapter(){

			@Override
			public int calcScore(int _initialScore) {
				int bonus = 2;
				for(Space space : player.getTerritory().getSpaces()){
					if(space.getPlayers().size()>0 && !space.getPlayers().contains(player)){
						bonus = 0;
					}
				}
				
				return _initialScore + bonus;
			}
			
		});

	}

}
