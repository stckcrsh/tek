package com.bfi.tek.races;

import java.util.ArrayList;
import java.util.List;

import com.bfi.tek.main.Game.Action;
import com.bfi.tek.models.Player;
import com.bfi.tek.models.PlayerAdapter;

public class Undead extends Race {

	public Undead(){
		title="Undead";
	}
	
	@Override
	public void Level1() {
		// When you lose a cube in war gain 2 food
		player.addWarResolutionListener(new PlayerAdapter(){

			@Override
			public void warResolution(Player _winner, Player _loser) {
				if(_loser == player){
					player.adjustFood(2);
				}
			}
			
		});
	}

	@Override
	public void Level2() {
		// may use magic to expand
		player.addExpandPaymentValidationListener(new PlayerAdapter() {

			@Override
			public boolean validateExpandPayment(int _cost, int[] _resources) {
				return ((_resources[Player.Resources.FOOD.getId()] + _resources[Player.Resources.MAGIC
						.getId()]) == _cost)
						&& _resources[Player.Resources.ORE.getId()] == 0;
			}

		});
	}

	@Override
	public void Level3() {
		// when another player loses a cube gain +1 food

	}

	@Override
	public void Level4() {
		// Expand instead of any other action
		player.addInsteadOfActionListener(new PlayerAdapter(){

			@Override
			public List<Action> insteadOfActions(Player _player,
					Action _insteadOfAction) {
				List<Action> actions = new ArrayList<Action>();
				if(_insteadOfAction != Action.EXPAND && _insteadOfAction != Action.COLLECTRESOURCES){
					actions.add(Action.EXPAND);
				}
				
				return actions;
			}
			
		});
	}

	@Override
	public void Level5() {
		// gain +2 score if you have a majority in another players territory

//		player.addCalcScoreListener(new PlayerAdapter(){
//
//			@Override
//			public int calcScore(int _initialScore) {
//				for(Player _otherPlayer : Game.getOtherPlayers(player)){
//					
//					for(Space space : _otherPlayer.getTerritory().getSpaces()){
//						
//					}
//				}
//			}
//			
//		});
	}

}
