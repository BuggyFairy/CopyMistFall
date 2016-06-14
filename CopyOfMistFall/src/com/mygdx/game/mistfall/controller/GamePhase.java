package com.mygdx.game.mistfall.controller;

import com.mygdx.game.mistfall.model.enums.GamePhaseType;

public class GamePhase {
	
	private GamePhaseType type;



	
	public GamePhase() {
		type=GamePhaseType.REINFORCEMENT_PHASE;
	}

	
	
	
	
	
	
	
	
	public GamePhaseType getType() {
		return type;
	}

	public void setType(GameController gc, GamePhaseType type) {
		this.type = type;
		if (type!=GamePhaseType.HERO_PHASE){
			gc.setActiveHero(-1);
		}
	}

	public String getString(){
		switch (type){
			case REINFORCEMENT_PHASE:
				return "Reinforcement Phase";
			case TRAVEL_PHASE:
				return "Travel Phase";
			case PURSUIT_PHASE:
				return "Pursuit Phase";
			case HERO_PHASE:
				return "Hero Phase";
			case DEFENCE_PHASE:
				return "Defence Phase";
			case ENCOUNTER_PHASE:
				return "Encounter Phase";
			case TIME_PHASE:
				return "Time Phase";
			default:
				return "";
		}
	}
	
	public void goToNext(GameController gc){
		switch (type){
			case REINFORCEMENT_PHASE:
				type=GamePhaseType.TRAVEL_PHASE;
			break;
			case TRAVEL_PHASE:
				type=GamePhaseType.PURSUIT_PHASE;
			break;
			case PURSUIT_PHASE:
				type=GamePhaseType.HERO_PHASE;
			break;
			case HERO_PHASE:
				type=GamePhaseType.DEFENCE_PHASE;
				gc.setActiveHero(-1);
			break;
			case DEFENCE_PHASE:
				type=GamePhaseType.ENCOUNTER_PHASE;
			break;
			case ENCOUNTER_PHASE:
				type=GamePhaseType.TIME_PHASE;
			break;
			case TIME_PHASE:
				type=GamePhaseType.REINFORCEMENT_PHASE;
			break;
			default:

		}
	}

}
