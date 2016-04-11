package com.mygdx.game.mistfall.model;

public class QuestCharter {
	
	private int timeTrackPosition;
	private int[][] timeTrack = new int[19][3];
	private int reinforcementTrackPosition;
	private int[] reinforcementTrack = new int[8];
	
	//false == easy
	//true == hard
	public QuestCharter(boolean nightmareQuestCharter, boolean moreTime, int playerCount){
		
		if(nightmareQuestCharter){	
			
			// First Element: BIG BLACK NUMBER YO; Second Element: Enrage Enemies? (1=yes, 0=no); Third Element: Move Reinforcement by 1 (1=yes, 0=no)
			timeTrack[0][0]=0;
			timeTrack[0][1]=0;
			timeTrack[0][2]=0;
			
			timeTrack[1][0]=0;
			timeTrack[1][1]=0;
			timeTrack[1][2]=0;
			
			timeTrack[2][0]=0;
			timeTrack[2][1]=0;
			timeTrack[2][2]=0;
			
			timeTrack[3][0]=0;
			timeTrack[3][1]=0;
			timeTrack[3][2]=0;
			
			timeTrack[4][0]=1;
			timeTrack[4][1]=0;
			timeTrack[4][2]=0;
			
			timeTrack[5][0]=1;
			timeTrack[5][1]=0;
			timeTrack[5][2]=0;
			
			timeTrack[6][0]=1;
			timeTrack[6][1]=0;
			timeTrack[6][2]=0;
			
			timeTrack[7][0]=1;
			timeTrack[7][1]=0;
			timeTrack[7][2]=0;
			
			timeTrack[8][0]=1;
			timeTrack[8][1]=1;
			timeTrack[8][2]=0;
			
			timeTrack[9][0]=2;
			timeTrack[9][1]=0;
			timeTrack[9][2]=0;
			
			timeTrack[10][0]=2;
			timeTrack[10][1]=0;
			timeTrack[10][2]=1;
			
			timeTrack[11][0]=3;
			timeTrack[11][1]=0;
			timeTrack[11][2]=0;
			
			timeTrack[12][0]=3;
			timeTrack[12][1]=0;
			timeTrack[12][2]=1;
			
			timeTrack[13][0]=3;
			timeTrack[13][1]=1;
			timeTrack[13][2]=0;
			
			timeTrack[14][0]=4;
			timeTrack[14][1]=0;
			timeTrack[14][2]=0;
			
			timeTrack[15][0]=4;
			timeTrack[15][1]=1;
			timeTrack[15][2]=0;
			
			timeTrack[16][0]=4;
			timeTrack[16][1]=0;
			timeTrack[16][2]=1;
			
			timeTrack[17][0]=4;
			timeTrack[17][1]=1;
			timeTrack[17][2]=0;
			
			timeTrack[18][0]=5;
			timeTrack[18][1]=0;
			timeTrack[18][2]=0;
			
			timeTrack[19][0]=5;
			timeTrack[19][1]=1;
			timeTrack[19][2]=0;
			
			reinforcementTrack[0]=0;
			reinforcementTrack[1]=1;
			reinforcementTrack[2]=2;
			reinforcementTrack[3]=3;
			reinforcementTrack[4]=4;
			reinforcementTrack[5]=4;
			reinforcementTrack[6]=5;
			reinforcementTrack[7]=5;
		}
		else{
			// First Element: BIG BLACK NUMBER YO; Second Element: Enrage Enemies? (1=yes, 0=no); Third Element: Move Reinforcement by 1 (1=yes, 0=no)
			timeTrack[0][0]=0;
			timeTrack[0][1]=0;
			timeTrack[0][2]=0;
			
			timeTrack[1][0]=0;
			timeTrack[1][1]=0;
			timeTrack[1][2]=0;
			
			timeTrack[2][0]=0;
			timeTrack[2][1]=0;
			timeTrack[2][2]=0;
			
			timeTrack[3][0]=0;
			timeTrack[3][1]=0;
			timeTrack[3][2]=0;
			
			timeTrack[4][0]=1;
			timeTrack[4][1]=0;
			timeTrack[4][2]=0;
			
			timeTrack[5][0]=1;
			timeTrack[5][1]=0;
			timeTrack[5][2]=0;
			
			timeTrack[6][0]=1;
			timeTrack[6][1]=0;
			timeTrack[6][2]=0;
			
			timeTrack[7][0]=1;
			timeTrack[7][1]=0;
			timeTrack[7][2]=0;
			
			timeTrack[8][0]=1;
			timeTrack[8][1]=1;
			timeTrack[8][2]=0;
			
			timeTrack[9][0]=2;
			timeTrack[9][1]=0;
			timeTrack[9][2]=0;
			
			timeTrack[10][0]=2;
			timeTrack[10][1]=0;
			timeTrack[10][2]=0;
			
			timeTrack[11][0]=2;
			timeTrack[11][1]=0;
			timeTrack[11][2]=0;
			
			timeTrack[12][0]=2;
			timeTrack[12][1]=1;
			timeTrack[12][2]=0;
			
			timeTrack[13][0]=3;
			timeTrack[13][1]=0;
			timeTrack[13][2]=0;
			
			timeTrack[14][0]=3;
			timeTrack[14][1]=0;
			timeTrack[14][2]=0;
			
			timeTrack[15][0]=3;
			timeTrack[15][1]=0;
			timeTrack[15][2]=0;
			
			timeTrack[16][0]=3;
			timeTrack[16][1]=1;
			timeTrack[16][2]=0;
			
			timeTrack[17][0]=4;
			timeTrack[17][1]=0;
			timeTrack[17][2]=0;
			
			timeTrack[18][0]=4;
			timeTrack[18][1]=0;
			timeTrack[18][2]=0;
			
			timeTrack[19][0]=4;
			timeTrack[19][1]=0;
			timeTrack[19][2]=0;
			
			reinforcementTrack[0]=0;
			reinforcementTrack[1]=1;
			reinforcementTrack[2]=1;
			reinforcementTrack[3]=2;
			reinforcementTrack[4]=3;
			reinforcementTrack[5]=3;
			reinforcementTrack[6]=4;
			reinforcementTrack[7]=4;
		}
		//Starting Position
		switch (playerCount){
			case 1:
				timeTrackPosition=3;
			break;
			case 2:
				timeTrackPosition=5;
			break;
			case 3:
				timeTrackPosition=6;
			break;
			case 4:
				timeTrackPosition=7;
			break;
			default:
				timeTrackPosition=3;
			break;
		}
		//Reinforcement Start
		reinforcementTrackPosition=0;
		
		if(moreTime){
			timeTrackPosition-=(2*playerCount);
		}
		
	}

	public int getTimeTrackPosition() {
		return timeTrackPosition;
	}

	public void setTimeTrackPosition(int timeTrackPosition) {
		this.timeTrackPosition = timeTrackPosition;
	}

	public int[][] getTimeTrack() {
		return timeTrack;
	}

	public int getReinforcementTrackPosition() {
		return reinforcementTrackPosition;
	}

	public void setReinforcementTrackPosition(int reinforcementTrackPosition) {
		this.reinforcementTrackPosition = reinforcementTrackPosition;
	}

	public int[] getReinforcementTrack() {
		return reinforcementTrack;
	}

	
	
	public void moveTimeTrackPositionRight(boolean right){
		if (right){
			timeTrackPosition++;
		}
		else{
			timeTrackPosition--;
		}
	}
	
	public void moveReinforcementTrackPositionRight(boolean right){
		if (right){
			reinforcementTrackPosition++;
		}
		else{
			reinforcementTrackPosition--;
		}
	}

}
