package com.bfi.tek.models;

import java.util.ArrayList;
import java.util.List;

import com.bfi.tek.controllers.Controller;
import com.bfi.tek.main.Game;
import com.bfi.tek.races.Race;
import com.bfi.tek.territories.Territory;

public class Player {

	public static final int[] TOWERPOINTS = { 0, 1, 2, 4, 7, 10 };
	protected static final int MAXWAREFFORT = 11;
	protected static final int MAXRESOURCES = 9;

	protected Race race;

	protected String name;

	protected int ore;
	protected int food;
	protected int magic;

	protected int[] resources;

	protected Unit[] units;

	protected Territory territory;

	protected int magicLv;
	protected int towerLv;

	protected Controller controller;

	public enum Resources {
		ORE(1, 0), MAGIC(2, 1), FOOD(0, 2), WILD(0, 3);

		private int warEffort;
		private int id;

		public int getId() {
			return id;
		}

		public int WarEffort() {
			return warEffort;
		}

		Resources(int _warEffort, int _id) {
			warEffort = _warEffort;
			id = _id;
		}
	}

	// =================LISTENERS======================
	private List<PlayerInterface> tradeValidationListeners;
	private List<PlayerInterface> tradeResolutionListeners;
	private List<PlayerInterface> patrolValidationListeners;
	private List<PlayerInterface> patrolResolutionListeners;
	private List<PlayerInterface> questValidationListeners;
	private List<PlayerInterface> questResolutionListeners;
	private List<PlayerInterface> calcResearchCostListeners;
	private List<PlayerInterface> researchPaymentValidationListeners;
	private List<PlayerInterface> researchResolutionListeners;
	private List<PlayerInterface> calcBuildCostListeners;
	private List<PlayerInterface> buildPaymentValidationListeners;
	private List<PlayerInterface> buildResolutionListeners;
	private List<PlayerInterface> calcExpandCostListeners;
	private List<PlayerInterface> expandValidationListeners;
	private List<PlayerInterface> expandPaymentValidationListeners;
	private List<PlayerInterface> expandResolutionListeners;
	private List<PlayerInterface> calcScoreListeners;
	private List<PlayerInterface> insteadOfActionListeners;
	private List<PlayerInterface> validateResourceSpaceListeners;
	private List<PlayerInterface> calcResourcesRecievedListeners;
	private List<PlayerInterface> resourceResolutionListeners;
	private List<PlayerInterface> calcResourceWarEffortListeners;
	private List<PlayerInterface> calcWarEffortListeners;
	private List<PlayerInterface> warResolutionListeners;

	public Player() {
		race = null;

		resources = new int[3];
		resources[0] = 0;
		resources[1] = 0;
		resources[2] = 0;

		units = new Unit[7];
		for (int i = 0; i < 7; i++) {
			units[i] = new Unit(this);
		}

		magicLv = 0;
		towerLv = 0;

		tradeValidationListeners = new ArrayList<PlayerInterface>();
		tradeResolutionListeners = new ArrayList<PlayerInterface>();
		patrolValidationListeners = new ArrayList<PlayerInterface>();
		patrolResolutionListeners = new ArrayList<PlayerInterface>();
		questValidationListeners = new ArrayList<PlayerInterface>();
		questResolutionListeners = new ArrayList<PlayerInterface>();
		calcResearchCostListeners = new ArrayList<PlayerInterface>();
		researchPaymentValidationListeners = new ArrayList<PlayerInterface>();
		researchResolutionListeners = new ArrayList<PlayerInterface>();
		calcBuildCostListeners = new ArrayList<PlayerInterface>();
		buildPaymentValidationListeners = new ArrayList<PlayerInterface>();
		buildResolutionListeners = new ArrayList<PlayerInterface>();
		calcExpandCostListeners = new ArrayList<PlayerInterface>();
		expandValidationListeners = new ArrayList<PlayerInterface>();
		expandPaymentValidationListeners = new ArrayList<PlayerInterface>();
		expandResolutionListeners = new ArrayList<PlayerInterface>();
		calcScoreListeners = new ArrayList<PlayerInterface>();
		insteadOfActionListeners = new ArrayList<PlayerInterface>();
		validateResourceSpaceListeners = new ArrayList<PlayerInterface>();
		calcResourcesRecievedListeners = new ArrayList<PlayerInterface>();
		resourceResolutionListeners = new ArrayList<PlayerInterface>();
		calcResourceWarEffortListeners = new ArrayList<PlayerInterface>();
		calcWarEffortListeners = new ArrayList<PlayerInterface>();
		warResolutionListeners = new ArrayList<PlayerInterface>();
	}

	// ==================LISTENER GET/SET=====================
	public void addTradeValidationListener(PlayerInterface _listener) {
		tradeValidationListeners.add(_listener);
	}
	public void removeTradeValidationListener(PlayerInterface _listener) {
		tradeValidationListeners.remove(_listener);
	}

	public void addTradeResolutionListener(PlayerInterface _listener) {
		tradeResolutionListeners.add(_listener);
	}


	public void removeTradeResolutionListener(PlayerInterface _listener) {
		tradeResolutionListeners.remove(_listener);
	}

	public void addPatrolValidationListener(PlayerInterface _listener) {
		patrolValidationListeners.add(_listener);
	}


	public void removePatrolValidationListener(PlayerInterface _listener) {
		patrolValidationListeners.remove(_listener);
	}

	public void addPatrolResolutionListener(PlayerInterface _listener) {
		patrolResolutionListeners.add(_listener);
	}


	public void removePatrolResolutionListener(PlayerInterface _listener) {
		patrolResolutionListeners.remove(_listener);
	}

	public void addQuestValidationListener(PlayerInterface _listener) {
		questValidationListeners.add(_listener);
	}


	public void removeQuestValidationListener(PlayerInterface _listener) {
		questValidationListeners.remove(_listener);
	}

	public void addQuestResolutionListener(PlayerInterface _listener) {
		questResolutionListeners.add(_listener);
	}


	public void removeQuestResolutionListener(PlayerInterface _listener) {
		questResolutionListeners.remove(_listener);
	}

	public void addCalcResearchListener(PlayerInterface _listener) {
		calcResearchCostListeners.add(_listener);
	}


	public void removeCalcResearchListener(PlayerInterface _listener) {
		calcResearchCostListeners.remove(_listener);
	}

	public void addResearchPaymentValidationListener(PlayerInterface _listener) {
		researchPaymentValidationListeners.add(_listener);
	}


	public void removeResearchPaymentValidationListener(PlayerInterface _listener) {
		researchPaymentValidationListeners.remove(_listener);
	}

	public void addResearchResolutionListener(PlayerInterface _listener) {
		researchResolutionListeners.add(_listener);
	}


	public void removeResearchResolutionListener(PlayerInterface _listener) {
		researchResolutionListeners.remove(_listener);
	}

	public void addCalcBuildCostListener(PlayerInterface _listener) {
		calcBuildCostListeners.add(_listener);
	}


	public void removeCalcBuildCostListener(PlayerInterface _listener) {
		calcBuildCostListeners.remove(_listener);
	}

	public void addBuildPaymentValidationListener(PlayerInterface _listener) {
		buildPaymentValidationListeners.add(_listener);
	}


	public void removeBuildPaymentValidationListener(PlayerInterface _listener) {
		buildPaymentValidationListeners.remove(_listener);
	}

	public void addBuildResolutionListener(PlayerInterface _listener) {
		buildResolutionListeners.add(_listener);
	}


	public void removeBuildResolutionListener(PlayerInterface _listener) {
		buildResolutionListeners.remove(_listener);
	}

	public void addCalcExpandCostListener(PlayerInterface _listener) {
		calcExpandCostListeners.add(_listener);
	}


	public void removeCalcExpandCostListener(PlayerInterface _listener) {
		calcExpandCostListeners.remove(_listener);
	}

	public void addExpandValidationListener(PlayerInterface _listener) {
		expandValidationListeners.add(_listener);
	}


	public void removeExpandValidationListener(PlayerInterface _listener) {
		expandValidationListeners.remove(_listener);
	}

	public void addExpandPaymentValidationListener(PlayerInterface _listener) {
		expandPaymentValidationListeners.add(_listener);
	}


	public void removeExpandPaymentValidationListener(PlayerInterface _listener) {
		expandPaymentValidationListeners.remove(_listener);
	}

	public void addExpandResolutionListener(PlayerInterface _listener) {
		expandResolutionListeners.add(_listener);
	}


	public void removeExpandResolutionListener(PlayerInterface _listener) {
		expandResolutionListeners.remove(_listener);
	}

	public void addCalcScoreListener(PlayerInterface _listener) {
		calcScoreListeners.add(_listener);
	}


	public void removeCalcScoreListener(PlayerInterface _listener) {
		calcScoreListeners.remove(_listener);
	}

	public void addInsteadOfActionListener(PlayerInterface _listener) {
		insteadOfActionListeners.add(_listener);
	}


	public void removeInsteadOfActionListener(PlayerInterface _listener) {
		insteadOfActionListeners.remove(_listener);
	}

	public void addValidateResourceSpaceListener(PlayerInterface _listener) {
		validateResourceSpaceListeners.add(_listener);
	}


	public void removeValidateResourceSpaceListener(PlayerInterface _listener) {
		validateResourceSpaceListeners.remove(_listener);
	}

	public void addCalcResourcesRecievedListener(PlayerInterface _listener) {
		calcResourcesRecievedListeners.add(_listener);
	}


	public void removeCalcResourcesRecievedListener(PlayerInterface _listener) {
		calcResourcesRecievedListeners.remove(_listener);
	}

	public void addResourceResolutionListener(PlayerInterface _listener) {
		resourceResolutionListeners.add(_listener);
	}


	public void removeResourceResolutionListener(PlayerInterface _listener) {
		resourceResolutionListeners.remove(_listener);
	}

	public void addCalcResourceWarEffortListener(PlayerInterface _listener) {
		calcResourceWarEffortListeners.add(_listener);
	}


	public void removeCalcResourceWarEffortListener(PlayerInterface _listener) {
		calcResourceWarEffortListeners.remove(_listener);
	}

	public void addCalcWarEffortListener(PlayerInterface _listener) {
		calcWarEffortListeners.add(_listener);
	}


	public void removeCalcWarEffortListener(PlayerInterface _listener) {
		calcWarEffortListeners.remove(_listener);
	}

	public void addWarResolutionListener(PlayerInterface _listener) {
		warResolutionListeners.add(_listener);
	}


	public void removeWarResolutionListener(PlayerInterface _listener) {
		warResolutionListeners.remove(_listener);
	}

	// =============GETTERS SETTERS====================

	public void setRace(Race _race) {
		race = _race;
		race.setPlayer(this);
	}

	public Race getRace() {
		return race;
	}

	public int[] getResources() {
		return resources;
	}

	public void subtractResources(int[] _values) {
		adjustMagic(_values[Resources.MAGIC.getId()] * -1);
		adjustOre(_values[Resources.ORE.getId()] * -1);
		adjustFood(_values[Resources.FOOD.getId()] * -1);
	}

	public void addResources(int[] _values) {
		adjustMagic(_values[Resources.MAGIC.getId()]);
		adjustOre(_values[Resources.ORE.getId()]);
		adjustFood(_values[Resources.FOOD.getId()]);
	}

	public void setResources(int[] _values) {
		resources = _values;
	}

	public int getMagic() {
		return resources[Resources.MAGIC.getId()];
	}

	public void setMagic(int _value) {
		resources[Resources.MAGIC.getId()] = _value;
	}

	public void adjustMagic(int _value) {
		resources[Resources.MAGIC.getId()] += _value;
		resources[Resources.MAGIC.getId()] = resources[Resources.MAGIC.getId()] > 9 ? 9
				: resources[Resources.MAGIC.getId()];
		resources[Resources.MAGIC.getId()] = resources[Resources.MAGIC.getId()] < 0 ? 0
				: resources[Resources.MAGIC.getId()];
	}

	public int getOre() {
		return resources[Resources.ORE.getId()];
	}

	public void setOre(int _value) {
		resources[Resources.ORE.getId()] = _value;
	}

	public void adjustOre(int _value) {
		resources[Resources.ORE.getId()] += _value;
		resources[Resources.ORE.getId()] = resources[Resources.ORE.getId()] > 9 ? 9
				: resources[Resources.ORE.getId()];
		resources[Resources.ORE.getId()] = resources[Resources.ORE.getId()] < 0 ? 0
				: resources[Resources.ORE.getId()];
	}

	public int getFood() {
		return resources[Resources.FOOD.getId()];
	}

	public void setFood(int _value) {
		resources[Resources.FOOD.getId()] = _value;
	}

	public void adjustFood(int _value) {
		resources[Resources.FOOD.getId()] += _value;
		resources[Resources.FOOD.getId()] = resources[Resources.FOOD.getId()] > 9 ? 9
				: resources[Resources.FOOD.getId()];
		resources[Resources.FOOD.getId()] = resources[Resources.FOOD.getId()] < 0 ? 0
				: resources[Resources.FOOD.getId()];
	}

	public Territory getTerritory() {
		return territory;
	}

	public void setTerritory(Territory _value) {
		territory = _value;
		territory.setPlayer(this);
	}

	public int getMagicLv() {
		return magicLv;
	}

	public void setMagicLv(int _value) {
		magicLv = _value;
	}

	public int getTowerLv() {
		return towerLv;
	}

	public void setTowerLv(int _value) {
		towerLv = _value;
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller _value) {
		controller = _value;
	}

	public String getName() {
		return name;
	}

	public void setName(String _value) {
		name = _value;
	}

	// =========================================================================================================
	// HELPERS
	// =========================================================================================================

	/**
	 * Returns a list of all of this players occupied spaces
	 * 
	 * @return
	 */
	public List<Space> getOccupiedSpaces() {
		ArrayList<Space> occupiedSpaces = new ArrayList<Space>();
		for (Unit unit : units) {
			if (!occupiedSpaces.contains(unit.getSpace()) && unit.getSpace() != null) {
				occupiedSpaces.add(unit.getSpace());
			}
		}

		return occupiedSpaces;
	}

	public List<Unit> getActiveUnits() {
		ArrayList<Unit> activeUnits = new ArrayList<Unit>();

		for (Unit unit : units) {
			if (unit.isActive()) {
				activeUnits.add(unit);
			}
		}

		return activeUnits;
	}

	public Unit getFreeUnit() {
		for (Unit unit : units) {
			if (unit.getSpace() == null) {
				return unit;
			}
		}

		return null;
	}

	/**
	 * Sets the intial starting space with 2 units
	 * 
	 * @param _start
	 */
	public void setStartSpace(Space _start) {
		Unit newUnit = null;
		for (Unit unit : units) {
			if (!unit.isActive()) {
				newUnit = unit;
			}
		}

		if (newUnit != null) {
			_start.addUnit(newUnit);
		}

		newUnit = null;
		for (Unit unit : units) {
			if (!unit.isActive()) {
				newUnit = unit;
			}
		}

		if (newUnit != null) {
			_start.addUnit(newUnit);
		}

	}

	/**
	 * returns the number of active units
	 * 
	 * @return
	 */
	public int calcActiveUnits() {
		int activeUnits = 0;
		for (Unit unit : units) {
			if (unit.isActive()) {
				activeUnits++;
			}
		}

		return activeUnits;
	}

	/**
	 * calculates this players score
	 * 
	 * @return
	 */
	public int calcScore() {
		int score = 0;
		score += calcActiveUnits();
		score += getMagicLv();
		score += TOWERPOINTS[getTowerLv()];

		if (calcScoreListeners.size() > 0) {
			for (PlayerInterface listener : calcScoreListeners) {
				score = listener.calcScore(score);
			}
		}

		return score;
	}

	/**
	 * call this to begin the end of the game
	 */
	public void gameEnder() {

	}

	// =========================================================================================================
	// ACTIONS ACTIONS
	// =========================================================================================================

	public List<Game.Action> insteadOfActions(Game.Action _action) {
		List<Game.Action> actions = new ArrayList<Game.Action>();

		if (insteadOfActionListeners.size() > 0) {
			for (PlayerInterface listener : insteadOfActionListeners) {
				actions.addAll(listener.insteadOfActions(this, _action));
			}
		}

		return actions;
	}

	// ===================================================
	// PATROL
	// ===================================================

	/**
	 * checks what spaces a unit can patrol to
	 * 
	 * @param _unit
	 * @return
	 */
	public List<Space> patrolOptions(Unit _unit) {
		// create a starting list of all spaces in the territory
		ArrayList<Space> finalSpaces = new ArrayList<Space>();

		for (Space space : _unit.getSpace().getTerritory().getSpaces()) {
			boolean valid = true;

			// first check if the space the space is a neighbor space
			// and that you wont exceed the stack limit
			if (space.getUnits().size() >= space.getStackLimit()
					|| space.getTerritory() != _unit.getSpace().getTerritory()
					|| !space.isTraversible()
					|| !_unit.getSpace().getNeighborSpaces().contains(space)) {
				valid = false;
			}

			// if there are quest validation listeners go through them now
			if (patrolValidationListeners.size() > 0) {
				for (PlayerInterface listener : patrolValidationListeners) {
					// if the listener returns true keep valid the same
					// else make valid false
					valid = listener.patrolValidation(_unit, _unit.getSpace(),
							space, valid);
				}
			}

			// if the space is still valid add it to the final spaces
			if (!finalSpaces.contains(space) && valid) {
				finalSpaces.add(space);
			}

		}

		return finalSpaces;
	}

	/**
	 * patrols a unit to a space
	 * 
	 * @param _unit
	 * @param _space
	 */
	public void patrol(Unit _unit, Space _space) {
		Space from = _unit.getSpace();
		if (_unit.getPlayer() == this) {
			_unit.getSpace().removeUnit(_unit);
			_unit.setSpace(_space);
			_space.addUnit(_unit);

			if (patrolResolutionListeners.size() > 0) {
				for (PlayerInterface listener : patrolResolutionListeners) {
					listener.questResolution(_unit, from, _space);
				}
			}

			List<Unit> warUnits = _space.getUnits();
			if (_space.getPlayers().size() == 2) {
				warUnits.remove(_unit);
				WAR(_unit, warUnits.get(0).getPlayer(), warUnits.get(0), _space);
			}
		}
	}

	// ===================================================
	// QUEST
	// ===================================================

	/**
	 * checks what spaces a unit can quest to
	 * 
	 * @param _unit
	 * @return
	 */
	public List<Space> questOptions(Unit _unit) {

		ArrayList<Space> finalSpaces = new ArrayList<Space>();

		// get all spaces on the board
		List<Space> spaces = new ArrayList<Space>();
		for (Player otherPlayer : Game.getPlayers()) {
			spaces.addAll(otherPlayer.getTerritory().getSpaces());
		}

		// loop through all spaces and check their validation
		for (Space space : spaces) {
			boolean valid = true;

			// first check if the space that you are on or the space you are
			// going to is an edge space and that the space is not at its stack
			// limit
			if (!space.getTerritory().getEdgeSpace().getNeighborSpaces()
					.contains(space)
					|| !_unit.getSpace().getTerritory().getEdgeSpace()
							.getNeighborSpaces().contains(_unit.getSpace())
					|| space.getUnits().size() >= space.getStackLimit()
					|| space.getTerritory() == _unit.getSpace().getTerritory()
					|| !space.isTraversible()) {
				valid = false;
			}

			// if there are quest validation listeners go through them now
			if (questValidationListeners.size() > 0) {
				for (PlayerInterface listener : questValidationListeners) {
					// if the listener returns true keep valid the same
					// else make valid false
					valid = listener.questValidation(_unit, _unit.getSpace(),
							space, valid);
				}
			}

			// if the space is still valid add it to the final spaces
			if (!finalSpaces.contains(space) && valid) {
				finalSpaces.add(space);
			}
		}

		return finalSpaces;
	}

	/**
	 * quests a unit to a space
	 * 
	 * @param _unit
	 * @param _space
	 */
	public void quest(Unit _unit, Space _space) {
		Space from = _unit.getSpace();
		if (_unit.getPlayer() == this) {
			_unit.getSpace().removeUnit(_unit);
			_unit.setSpace(_space);
			_space.addUnit(_unit);

			if (questResolutionListeners.size() > 0) {
				for (PlayerInterface listener : questResolutionListeners) {
					listener.questResolution(_unit, from, _space);
				}
			}

			List<Unit> warUnits = _space.getUnits();
			if (_space.getPlayers().size() == 2) {
				warUnits.remove(_unit);
				WAR(_unit, warUnits.get(0).getPlayer(), warUnits.get(0), _space);
			}
		}
	}

	// ===================================================
	// EXPAND
	// ===================================================

	/**
	 * calculates the cost to expand
	 * 
	 * @return
	 */
	public int calcExpandCost() {
		int cost = calcActiveUnits();

		if (calcExpandCostListeners.size() > 0) {
			for (PlayerInterface listener : calcExpandCostListeners) {
				cost = listener.calcExpandCost(this, cost);
			}
		}

		return cost;
	}

	/**
	 * checks if it is possible to expand
	 * 
	 * @return
	 */
	public boolean canExpand() {
		boolean expandable = false;

		for (Unit unit : units) {
			if (unit.isActive()
					&& unit.getSpace().getUnits().size() < unit.getSpace()
							.getStackLimit()) {
				expandable = true;
			}
		}

		if (calcActiveUnits() >= 7) {
			expandable = false;
		}
		if (ore >= calcExpandCost()) {
			expandable = false;
		}

		return expandable;
	}

	/**
	 * returns spaces that can be expanded on
	 * 
	 * @return
	 */
	public List<Space> expandOptions() {
		ArrayList<Space> options = new ArrayList<Space>();
		ArrayList<Space> finalOptions = new ArrayList<Space>();

		// first get all the possible spaces
		for (Player otherPlayer : Game.getPlayers()) {
			options.addAll(otherPlayer.getTerritory().getSpaces());
		}

		// loop through all the possible spaces and see if they are valid
		for (Space space : options) {
			boolean valid = false;

			// basic checks
			// is space full >= stackLimit
			// is space where the unit is
			if (space.getStackLimit() > space.getUnits().size()
					&& space.getPlayers().contains(this)) {
				valid = true;
			}

			// loop through the expand listeners
			if (expandValidationListeners.size() > 0) {
				for (PlayerInterface listener : expandValidationListeners) {
					valid = listener.expandValidation(this, space, valid);
				}
			}

			// if the space is valid add it to final options
			if (valid && !finalOptions.contains(space)) {
				finalOptions.add(space);
			}

		}

		return finalOptions;
	}

	/**
	 * Validates that the payment to expand and includes racial bonuses
	 * 
	 * @param _cost
	 * @param _resources
	 * @return
	 */
	public boolean validateExpandPayment(int _cost, int[] _resources) {
		boolean isValid = false;

		if (_resources[Resources.MAGIC.getId()] > 0
				|| _resources[Resources.ORE.getId()] > 0
				|| _resources[Resources.FOOD.getId()] < _cost) {
			isValid = false;
		} else {
			isValid = true;
		}

		if (expandPaymentValidationListeners.size() > 0) {
			for (PlayerInterface listener : expandPaymentValidationListeners) {
				isValid = listener.validateExpandPayment(_cost, _resources);
			}
		}

		return isValid;
	}

	/**
	 * expand onto a space
	 * 
	 * @param _space
	 */
	public void expand(Space _space, int[] _resources) {
		Unit newUnit = null;
		for (Unit unit : units) {
			if (!unit.isActive()) {
				newUnit = unit;
			}
		}

		if (newUnit != null) {
			_space.addUnit(newUnit);

			if (expandResolutionListeners.size() > 0) {
				for (PlayerInterface listener : expandResolutionListeners) {
					listener.expandResolution(this);
				}
			}
		} else {
			return;
		}

	}

	// ===================================================
	// BUILD
	// ===================================================

	/**
	 * calculate the cost to build the next level of the tower
	 * 
	 * @return
	 */
	public int calcBuildCost() {
		int cost = (towerLv + 1) > 6 ? -1 : towerLv + 1;

		if (calcBuildCostListeners.size() > 0) {
			for (PlayerInterface listener : calcBuildCostListeners) {
				cost = listener.calcBuildCost(this, cost);
			}
		}

		return cost;
	}

	/**
	 * checks if it is possible to build
	 * 
	 * @return
	 */
	public boolean canBuild() {
		boolean buildable = false;

		if (towerLv < 6) {
			buildable = true;
		} else {
			buildable = false;
		}

		return buildable;
	}

	/**
	 * Validates the Build payment with racial bonuses
	 * 
	 * @param _cost
	 * @param _resources
	 * @return
	 */
	public boolean validateBuildPayment(int _cost, int[] _resources) {
		boolean isValid = false;

		if (_resources[Resources.MAGIC.getId()] > 0
				|| _resources[Resources.FOOD.getId()] > 0
				|| _resources[Resources.ORE.getId()] < _cost) {
			isValid = false;
		} else {
			isValid = true;
		}

		if (buildPaymentValidationListeners.size() > 0) {
			for (PlayerInterface listener : buildPaymentValidationListeners) {
				isValid = listener.validateBuildPayment(_cost, _resources,
						isValid);
			}
		}

		return isValid;
	}

	/**
	 * builds the next tower level
	 */
	public void build(int[] _resources) {
		subtractResources(_resources);
		towerLv++;
		towerLv = towerLv > 6 ? 6 : towerLv;

		if (buildResolutionListeners.size() > 0) {
			for (PlayerInterface listener : buildResolutionListeners) {
				listener.buildResolution(this);
			}
		}
	}

	// ===================================================
	// RESEARCH
	// ===================================================

	/**
	 * calculates the research cost
	 * 
	 * @return
	 */
	public int calcResearchCost() {
		int cost = (magicLv + 1) > 5 ? -1 : magicLv + 1;

		if (calcResearchCostListeners.size() > 0) {
			for (PlayerInterface listener : calcResearchCostListeners) {
				cost = listener.calcResearchCost(this, cost);
			}
		}

		return cost;
	}

	/**
	 * checks if it is possible to research
	 * 
	 * @return
	 */
	public boolean canResearch() {
		boolean researchable = false;

		if (magicLv < 5) {
			researchable = true;
		} else {
			researchable = false;
		}

		return researchable;
	}

	/**
	 * Validates the research payment with racial bonuses
	 * 
	 * @param _cost
	 * @param _resources
	 * @return
	 */
	public boolean validateResearchPayment(int _cost, int[] _resources) {
		boolean isValid = false;

		if (_resources[Resources.ORE.getId()] > 0
				|| _resources[Resources.FOOD.getId()] > 0
				|| _resources[Resources.MAGIC.getId()] < _cost) {
			isValid = false;
		} else {
			isValid = true;
		}

		if (researchPaymentValidationListeners.size() > 0) {
			for (PlayerInterface listener : researchPaymentValidationListeners) {
				isValid = listener.validateResearchPayment(_cost, _resources,
						isValid);
			}
		}

		return isValid;
	}

	/**
	 * research
	 */
	public void research(int[] _resources) {
		subtractResources(_resources);
		magicLv++;
		switch (magicLv) {
		case 1:
			race.Level1();
			break;
		case 2:
			race.Level2();
			break;
		case 3:
			race.Level3();
			break;
		case 4:
			race.Level4();
			break;
		case 5:
			race.Level5();
			break;
		}

		if (researchResolutionListeners.size() > 0) {
			for (PlayerInterface listener : researchResolutionListeners) {
				listener.researchResolution(this);
			}
		}
	}

	// ===================================================
	// TRADE
	// ===================================================

	/**
	 * validates if a trade is valid or not
	 * 
	 * @param _resources
	 * @return
	 */
	public boolean isTradeValid(int[] _resources) {
		boolean isValid = true;
		int tradeTotal = 0;
		int numberOfTypes = 0;
		for (int i : _resources) {
			tradeTotal += i;
			if (i != 0) {
				numberOfTypes++;
			}
		}

		if (tradeTotal != 0 || numberOfTypes > 2) {
			isValid = false;
		}

		if (tradeValidationListeners.size() > 0) {
			for (PlayerInterface listener : tradeValidationListeners) {

				isValid = listener.tradeValidation(_resources, isValid);
			}
		}

		return isValid;
	}

	/**
	 * trade one type of good for another
	 * 
	 * @param _giveType
	 * @param _receiveType
	 */
	public void trade(int[] _resources) {

		addResources(_resources);
		if (tradeResolutionListeners.size() > 0) {
			for (PlayerInterface listener : tradeResolutionListeners) {
				listener.tradeResolution(this, _resources);
			}
		}
	}

	// ===================================================
	// COLLECT RESOURCES
	// ===================================================

	/**
	 * Collect resources in 3 parts
	 */
	public void collectResources(List<Space> _spaces, int[] _resources) {
		addResources(_resources);

		if (resourceResolutionListeners.size() > 0) {
			for (PlayerInterface listener : resourceResolutionListeners) {
				listener.collectResolution(this, _spaces, _resources);
			}
		}
	}

	/**
	 * checks all the resource spaces and creates
	 * 
	 * @return
	 */
	public List<Space> validateCollectResourceSpaces() {
		ArrayList<Space> collectibleSpaces = new ArrayList<Space>();

		// get all the occupied spaces and run through them
		for (Space space : getOccupiedSpaces()) {

			boolean collectible = false;

			if (space.getResourceType() != null
					|| space.getClass() == RuinsSpace.class) {
				collectible = true;
			}

			if (validateResourceSpaceListeners.size() > 0) {
				for (PlayerInterface listener : validateResourceSpaceListeners) {

					collectible = listener.validateResourceSpace(this, space,
							collectible);

				}
			}

			if (collectible && !collectibleSpaces.contains(space)) {
				collectibleSpaces.add(space);
			}
		}

		return collectibleSpaces;
	}

	/**
	 * calculates the resources that you will recieve including bonuses
	 * 
	 * @param _spaces
	 * @return
	 */
	public int[] calcResourcesReceived(List<Space> _spaces) {
		int[] resourcesRecieved = new int[4];
		resourcesRecieved[0] = 0;
		resourcesRecieved[1] = 0;
		resourcesRecieved[2] = 0;
		resourcesRecieved[3] = 0;

		for (Space space : _spaces) {
			if (space.getResourceType() != null) {
				resourcesRecieved[space.getResourceType().getId()]++;
			}

		}

		if (calcResourcesRecievedListeners.size() > 0) {
			for (PlayerInterface listener : calcResourcesRecievedListeners) {
				resourcesRecieved = listener.calcResourcesReceived(this,
						_spaces, resourcesRecieved);
			}
		}

		return resourcesRecieved;
	}

	// ===================================================
	// WAR
	// ===================================================

	/**
	 * returns the war effort of this player
	 * 
	 * @return
	 */
	public int[] calcResourceWarEffort(Player _enemy) {

		int[] warEffort = new int[3];
		warEffort[Resources.FOOD.getId()] = Resources.FOOD.WarEffort();
		warEffort[Resources.ORE.getId()] = Resources.ORE.WarEffort();
		warEffort[Resources.MAGIC.getId()] = Resources.MAGIC.WarEffort();
		if (calcResourceWarEffortListeners.size() > 0) {
			for (PlayerInterface listener : calcResourceWarEffortListeners) {
				warEffort = listener.calcResourceWarEffort(warEffort, _enemy);
			}
		}

		return warEffort;
	}

	/**
	 * get the highest possible war effort
	 * 
	 * @return
	 */
	public int calcPossibleWarEffort(Player _enemy) {
		int warEffort = 0;

		for (int ore = 0; ore < resources[Resources.ORE.getId()]; ore++) {
			if (warEffort >= 11) {
				break;
			}
			for (int magic = 0; magic < resources[Resources.MAGIC.getId()]; magic++) {
				if (warEffort >= 11) {
					break;
				}
				for (int food = 0; food < resources[Resources.FOOD.getId()]; food++) {
					if (warEffort >= 11) {
						break;
					}
					if (ore + food + magic <= 9) {
						int[] tempResources = new int[3];
						tempResources[Resources.ORE.getId()] = ore;
						tempResources[Resources.FOOD.getId()] = food;
						tempResources[Resources.MAGIC.getId()] = magic;
						int temp = calcWarEffort(tempResources, _enemy);
						warEffort = temp > warEffort ? temp : warEffort;
					}

				}
			}
		}

		return warEffort > 11 ? 11 : warEffort;

	}

	public int calcWarEffort(int[] _resources, Player _enemy) {
		int warEffort = 0;
		int[] warResourceEffort = calcResourceWarEffort(_enemy);

		for (Resources resource : Resources.values()) {
			warEffort += _resources[resource.getId()]
					* warResourceEffort[resource.getId()];
		}

		if (calcWarEffortListeners.size() > 0) {
			for (PlayerInterface listener : calcWarEffortListeners) {
				warEffort = listener.calcWarEffort(_resources, warEffort);
			}
		}

		return warEffort;
	}

	/**
	 * this will return the amount of your warEffort with racial bonuses
	 * accounted for
	 * 
	 * @return
	 */
	public int finalWarEffort(int _bid, int[] _resources) {
		addResources(_resources);

		return _bid;
	}

	/**
	 * 
	 * @param _attackerUnit
	 * @param _defender
	 * @param _defenderUnit
	 * @param _space
	 */
	private void WAR(Unit _attackerUnit, Player _defender, Unit _defenderUnit,
			Space _space) {

		// REDO war maybe i will add it to the attacking players stuff
		// to start we need to calculate the possible bids for war

		int attackerBid = controller.bidForWar(_defender);
		int defenderBid = _defender.getController().bidForWar(this);
		// first lets calculate the final warEfforts of both players
		int attackerWarEffort = this.finalWarEffort(attackerBid,
				controller.payForWar(attackerBid));
		int defenderWarEffort = _defender.finalWarEffort(defenderBid, _defender
				.getController().payForWar(defenderBid));

		if (attackerBid == 0 && defenderBid == 0) {
			// nothing happens they are now in an alliance
		} else {
			Player winner = null;
			Player loser = null;
			Unit losingUnit = null;

			if (defenderWarEffort >= attackerWarEffort) {
				winner = _defender;
				loser = this;
				losingUnit = _attackerUnit;
			} else {
				winner = this;
				loser = _defender;
				losingUnit = _defenderUnit;
			}

			// now that we have a winner and a loser
			// the winner takes the space and the users unit is removed from
			// that space
			_space.removeUnit(losingUnit);
			losingUnit.setSpace(null);

			winner.warResolution(winner, loser);
			loser.warResolution(winner, loser);

			// if the players were in an alliance remove all the winners pieces
			for (Unit unit : winner.getActiveUnits()) {
				if (unit.getSpace().getPlayers().contains(loser)) {
					unit.getSpace().removeUnit(unit);
					unit.setSpace(null);
				}
			}
		}
	}

	private void warResolution(Player _winner, Player _loser) {
		if (warResolutionListeners.size() > 0) {
			for (PlayerInterface _listener : warResolutionListeners) {
				_listener.warResolution(_winner, _loser);
			}
		}

	}
}
