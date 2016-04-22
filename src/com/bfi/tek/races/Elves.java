package com.bfi.tek.races;

import java.util.List;

import com.bfi.tek.main.Game;
import com.bfi.tek.models.Player;
import com.bfi.tek.models.PlayerAdapter;
import com.bfi.tek.models.Player.Resources;

public class Elves extends Race {

	public Elves(){
		title="Elves";
	}

	@Override
	public void Level1() {
		// when trading you may trade ore and food for mana
		player.addTradeValidationListener(new PlayerAdapter(){

			@Override
			public boolean tradeValidation(int[] _resources, boolean _isValid) {
				boolean isValid = _isValid;
				
				int tradeTotal = 0;
				int numberOfTypes = 0;
				for(int i : _resources){
					tradeTotal += i;
					if(i!=0){numberOfTypes++;}
				}
				
				if(tradeTotal!=0){
					isValid = false;
				}
				
				if(numberOfTypes>1 && _resources[Player.Resources.MAGIC.getId()] <= 0){
					return false;
				}
				
				
				return isValid;
			}
		});
	}

	@Override
	public void Level2() {
		// when calculating war effort +1 if you only pay with mana
		player.addCalcWarEffortListener(new PlayerAdapter(){

			@Override
			public int calcWarEffort(int[] _resources, int _initialWarEffort) {
				if(_resources[Resources.MAGIC.getId()]>0 &&
						_resources[Resources.ORE.getId()] == 0 &&
						_resources[Resources.FOOD.getId()] ==0){
					return _initialWarEffort + 1;
				}
				
				return _initialWarEffort;
			}
			
		});
	}

	@Override
	public void Level3() {
		// mana can be used as any resource
		PlayerAdapter listener = new PlayerAdapter(){

			@Override
			public boolean validateBuildPayment(int _cost, int[] _resources, boolean _isValid) {
				if((_resources[Player.Resources.MAGIC.getId()]+_resources[Player.Resources.ORE.getId()]) == _cost){
					return true;
				} else {
					return false;
				}
			}

			@Override
			public boolean validateExpandPayment(int _cost, int[] _resources) {
				if((_resources[Player.Resources.MAGIC.getId()]+_resources[Player.Resources.FOOD.getId()]) == _cost){
					return true;
				} else {
					return false;
				}
			}};
		player.addBuildPaymentValidationListener(listener);
		player.addExpandPaymentValidationListener(listener);

	}

	@Override
	public void Level4() {
		// other players mana only counts as one when against you
		for(Player otherPlayer : Game.getOtherPlayers(player)){
			otherPlayer.addCalcResourceWarEffortListener(new PlayerAdapter(){

				@Override
				public int[] calcResourceWarEffort(int[] _warEffort, Player _enemy) {
					if(_enemy == player){
						_warEffort[Resources.MAGIC.getId()] = 1;
					}
					
					return _warEffort;
				}
				
			});
		}

	}

	@Override
	public void Level5() {
		// for each player with less mana plus one score
		player.addCalcScoreListener(new PlayerAdapter(){

			@Override
			public int calcScore(int _initialScore) {
				int additionalScore = 0;
				List<Player> otherPlayers = Game.getOtherPlayers(player);
				for(Player otherPlayer : otherPlayers){
					if(otherPlayer.getMagic()<player.getMagic()){
						additionalScore++;
					}
				}
				
				return _initialScore + additionalScore;
			}
		});

	}

}
