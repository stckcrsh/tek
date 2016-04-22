package com.bfi.tek.races;

import java.util.ArrayList;
import java.util.List;

import com.bfi.tek.main.Game;
import com.bfi.tek.main.Game.Action;
import com.bfi.tek.models.CragSpace;
import com.bfi.tek.models.Player;
import com.bfi.tek.models.PlayerAdapter;
import com.bfi.tek.models.Space;
import com.bfi.tek.models.Unit;
import com.bfi.tek.models.WaterSpace;

public class Lizardfolk extends Race {

	public Lizardfolk() {
		title = "Lizardfolk";
	}

	@Override
	public void Level1() {
		// may patrol to any space on a territory card
		player.addPatrolValidationListener(new PlayerAdapter() {

			@Override
			public boolean patrolValidation(Unit _unit, Space _from, Space _to,
					boolean _isValid) {
				if ((_to.getClass() != WaterSpace.class && _to.getClass() != CragSpace.class)) {
					return true;
				}
				return _isValid;
			}

		});

	}

	@Override
	public void Level2() {
		// may quest instead of patrol and vice versa
		player.addInsteadOfActionListener(new PlayerAdapter() {

			@Override
			public List<Action> insteadOfActions(Player _player,
					Action _insteadOfAction) {
				List<Game.Action> actions = new ArrayList<Game.Action>();

				if (_insteadOfAction == Game.Action.PATROL) {
					actions.add(Game.Action.QUEST);
				} else if (_insteadOfAction == Game.Action.QUEST) {
					actions.add(Game.Action.PATROL);
				}

				return actions;
			}
		});

	}

	@Override
	public void Level3() {
		// When you patrol or quest into a space gain that resource
		PlayerAdapter pa = new PlayerAdapter() {

			@Override
			public void patrolResolution(Unit _unit, Space _from, Space _to) {
				int[] resources = new int[3];
				resources[0] = 0;
				resources[1] = 0;
				resources[2] = 0;
				if (_to.getResourceType() != null) {
					resources[_to.getResourceType().getId()] = 1;
					player.addResources(resources);
				}

			}

			@Override
			public void questResolution(Unit _unit, Space _from, Space _to) {
				int[] resources = new int[3];
				resources[0] = 0;
				resources[1] = 0;
				resources[2] = 0;
				if (_to.getResourceType() != null) {
					resources[_to.getResourceType().getId()] = 1;
					player.addResources(resources);
				}
			}

		};

		player.addQuestResolutionListener(pa);
		player.addPatrolResolutionListener(pa);
	}

	@Override
	public void Level4() {
		// When Questing you may instead switch spaces with another players cube
		// thats not cohabitating

		// add a quest validation listener that will look for spaces that are at
		// stacklimit with one player in them

		PlayerAdapter adapter = new PlayerAdapter() {

			@Override
			public boolean questValidation(Unit _unit, Space _from, Space _to,
					boolean _initial) {
				if (_to.getTerritory().getEdgeSpace().getNeighborSpaces()
						.contains(_to)
						&& _from.getTerritory().getEdgeSpace()
								.getNeighborSpaces().contains(_from)
						&& _to.getUnits().size() >= _to.getStackLimit()
						&& _to.getTerritory() != _unit.getSpace()
								.getTerritory()
						&& !_to.isTraversible()
						&& _to.getPlayers().size() == 1) {
					return true;
				}

				return _initial;
			}

			@Override
			public void questResolution(Unit _unit, Space _from, Space _to) {

				// if there are 2 units already at the space we are going to we
				// know that we are swapping
				if (_to.getUnits().size() > _to.getStackLimit()) {
					// take one of the units and quest it back to the space that you came from
					ArrayList<Unit> units = (ArrayList<Unit>) _to.getUnits();
					units.remove(_unit);
					Unit swapper = units.get(0);
					
					swapper.getPlayer().quest(swapper, _from);
				}

			}
		};

		player.addQuestValidationListener(adapter);
		player.addQuestResolutionListener(adapter);

	}

	@Override
	public void Level5() {
		// +1 score for every other territory you occupy
		player.addCalcScoreListener(new PlayerAdapter() {

			@Override
			public int calcScore(int _initialScore) {
				List<Player> otherPlayers = Game.getPlayers();
				otherPlayers.remove(player);

				int bonus = 0;

				for (Player otherPlayer : otherPlayers) {
					for (Space space : otherPlayer.getTerritory().getSpaces()) {
						if (space.getPlayers().contains(player)) {
							bonus++;
							break;
						}
					}
				}

				return _initialScore + bonus;
			}

		});
	}

}
