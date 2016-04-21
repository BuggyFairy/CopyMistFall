package com.mygdx.game.mistfall.enemy;

import java.util.LinkedList;

import com.mygdx.game.mistfall.controller.GameController;
import com.mygdx.game.mistfall.enemy.enums.EnemyAbilityType;
import com.mygdx.game.mistfall.model.Card;
import com.mygdx.game.mistfall.model.Conditions;
import com.mygdx.game.mistfall.model.enums.EnemyKeyword;
import com.mygdx.game.mistfall.model.enums.EnemyType;
import com.mygdx.game.mistfall.model.enums.Vulnerability;
import com.mygdx.game.mistfall.model.modifications.ModSource;
import com.mygdx.game.mistfall.model.modifications.ModTarget;
import com.mygdx.game.mistfall.model.modifications.ModType;
import com.mygdx.game.mistfall.model.modifications.Modification;

public class Enemy extends Card {
	
	private int enemyID;
	private EnemyType enemyType;
	private boolean specialEnemy;
	private LinkedList<EnemyKeyword> enemyKeyword;
	private int resolve;
	private LinkedList<Vulnerability> vulnerability;
	private Conditions conditions;

	private AttackValues attack;
	private ResistanceValues resistances;
	private LifeValues life;
	private int targetRange;
	
	private LinkedList<EnemyAbility> abilities;
	private LinkedList<Modification> modifications;
	
	
	
	public Enemy(){
		enemyKeyword=new LinkedList<EnemyKeyword>();
		vulnerability=new LinkedList<Vulnerability>();
		conditions=new Conditions();
		attack= new AttackValues();
		resistances=new ResistanceValues();
		life=new LifeValues();
		abilities=new LinkedList<EnemyAbility>();
		modifications=new LinkedList<Modification>();
	}
	
	public void appendEnemyKeyword(EnemyKeyword ek){
		if(this.enemyKeyword != null){
			this.enemyKeyword.add(ek);
		}
	}
	
	
	public void appendVunerability(Vulnerability v){
		if(this.vulnerability != null){
			this.vulnerability.add(v);
		}
	}

	public EnemyType getEnemyType() {
		return enemyType;
	}

	public void setEnemyType(EnemyType enemyType) {
		this.enemyType = enemyType;
	}

	public boolean isSpecialEnemy() {
		return specialEnemy;
	}

	public void setSpecialEnemy(boolean specialEnemy) {
		this.specialEnemy = specialEnemy;
	}

	public LinkedList<EnemyKeyword> getEnemyKeyword() {
		return enemyKeyword;
	}

	public int getResolve() {
		return resolve;
	}

	public void setResolve(int resolve) {
		this.resolve = resolve;
	}

	public LinkedList<Vulnerability> getVulnerability() {
		return vulnerability;
	}

	public Conditions getConditions() {
		return conditions;
	}

	public AttackValues getAttack() {
		return attack;
	}

	public ResistanceValues getResistances() {
		return resistances;
	}

	public LifeValues getLife() {
		return life;
	}

	
	public LinkedList<EnemyAbility> getAbilities(){
		return abilities;
	}
	
	public LinkedList<Modification> getModifications() {
		return modifications;
	}
	
	
	/**
	 * @param modSource
	 * @param modType
	 * @param modTarget
	 * @param value
	 * @param sourceID
	 * @param sourceName
	 * @return return true if the Modification was updated and false if a new Modification was created
	 * 
	 * Checks if the specified Modification already exists, and updates it if necessary.
	 * If the Modification was not found, a new one is created and the values are updated if necessary
	 */
	public boolean updateModification(ModSource modSource, ModType modType,ModTarget modTarget,int value, int sourceID, String sourceName){
		boolean updated=false;
		int pos=-1;
		
		// Check if the Modification exists and save the position in the list
		for (int i=0;i<modifications.size();i++){
			if (modifications.get(i).getModType()==modType && 
				modifications.get(i).getModSource()==modSource &&
				modifications.get(i).getSourceID()==sourceID){
				pos=i;
				break;
			}
		}
		// If the Modification exists, update it
		if (pos!=-1){
			updated=true;
			// Update Values
			switch(modTarget){
				case ATTACK:
					attack.setValueMod(attack.getValueMod()-modifications.get(pos).getValue()+value);
				break;
				case LIFE:
					life.setValueBase(life.getValueBase()-modifications.get(pos).getValue()+value);
					life.setValueCurrent(life.getValueCurrent()-modifications.get(pos).getValue()+value);
				break;
				case PHYSICAL_RESISTANCE:
					resistances.setPhysicalResMod(resistances.getPhysicalResMod()-modifications.get(pos).getValue()+value);
				break;
				case MAGICAL_RESISTANCE:
					resistances.setMagicalResMod(resistances.getMagicalResMod()-modifications.get(pos).getValue()+value);
				break;
				case RANGE:
				break;
				
				default:
				break;
			}
			// Update Modification
			modifications.get(pos).setValue(value);
		}
		// If the Modification does not exist, create a new one
		else{
			modifications.add(new Modification(modSource,modType,modTarget,value,sourceID,sourceName));
			switch(modTarget){
				case ATTACK:
					attack.setValueMod(attack.getValueMod()+value);
				break;
				case LIFE:
					life.setValueBase(life.getValueBase()+value);
					life.setValueCurrent(life.getValueCurrent()+value);
				break;
				case PHYSICAL_RESISTANCE:
					resistances.setPhysicalResMod(resistances.getPhysicalResMod()+value);
				break;
				case MAGICAL_RESISTANCE:
					resistances.setMagicalResMod(resistances.getMagicalResMod()+value);
				break;
				case RANGE:
					targetRange=targetRange+value;
				break;
				
				default:
				break;
			}
		}
		return updated;
	}
	/**
	 * @param modType
	 * @param sourceName
	 * @return true if the removal was successful
	 * Removes the specified Modification if available and adjusts the values if necessary
	 */
	public boolean removeModification(ModType modType, ModSource modSource, int sourceID){
		boolean removed=false;
		int pos=-1;
		// Check if the Modification exists and save the position in the list
		for (int i=0;i<modifications.size();i++){
			if (modifications.get(i).getModType()==modType && 
				modifications.get(i).getModSource()==modSource &&
				modifications.get(i).getSourceID()==sourceID){
				pos=i;
				break;
			}
		}
		// If the Modifications was found
		if (pos!=-1){
			// Adjust Values if necessary
			switch(modifications.get(pos).getModTarget()){
				case ATTACK:
					attack.setValueMod(attack.getValueMod()-modifications.get(pos).getValue());
				break;
				case LIFE:
					life.setValueBase(life.getValueBase()-modifications.get(pos).getValue());
					life.setValueCurrent(life.getValueCurrent()-modifications.get(pos).getValue());
				break;
				case PHYSICAL_RESISTANCE:
					resistances.setPhysicalResMod(resistances.getPhysicalResMod()-modifications.get(pos).getValue());
				break;
				case MAGICAL_RESISTANCE:
					resistances.setMagicalResMod(resistances.getMagicalResMod()-modifications.get(pos).getValue());
				break;
				case RANGE:
					targetRange=targetRange-modifications.get(pos).getValue();
				break;
				
				default:
				break;
			}
			// Remove Entry and set return
			modifications.remove(pos);
			removed=true;
		}
		
		return removed;
	}
	
	public boolean searchModification(ModType modType){
		boolean modFound=false;
		
		for (Modification m : modifications){
			if (m.getModType()==modType){
				return true;
			}
		}
		
		return modFound;
	}
	
	public boolean searchAbility(EnemyAbilityType enemyAbilityType){
		boolean abilityFound=false;
		
		for (EnemyAbility eA : abilities){
			if (eA.getType()==enemyAbilityType){
				return true;
			}
		}
		
		return abilityFound;
	}

//	public int posOfAbility (EnemyAbilityType enemyAbilityType){
//		int pos=-1;
//		for (int i=0;i<abilities.size();i++){
//			if (abilities.get(i).getType()==enemyAbilityType){
//				pos=i;
//				break;
//			}
//		}
//		return pos;
//	}
	
	
//	public String toString(){
//		String text;
//		text = "This Enemy contains: \n"+ "Name: "+getName()+"\nLife: "+getLife()+"\nAttack Type: "+ getAttackType().toString()+
//				"\nAttack Value: "+getAttackValue()+"\nPhysical Resistance: "+getPhysicalRes()+"\nMagical Resistance: "+getMagicalRes()+
//				"\nResolve: "+getResolve()+"\nEnemy Type: "+getEnemyType().toString()+"\nEnemy Keywords: "+getEnemyKeyword().toString()+
//				"\nVunerabilities: "+getVunerability().toString();
//		return text;
//	}

	
//	/**
//	 * @param woundCount
//	 * @param gc
//	 * @return true if the enemy died
//	 * 
//	 * Removes the specified "woundCount" value from the current life of the enemy
//	 * if the enemy has zero or less life left, the resolve count is added to the resolve pool
//	 */
	public boolean applyWounds(int woundCount, GameController gc){
		boolean enemyDead=false;
		// Apply Wounds
		life.setValueCurrent(life.getValueCurrent()-woundCount);
		// Check if the Enemy died
		if (life.getValueCurrent()<=0){
			// Reward Resolve
			gc.getGameSetupController().changeResolvePool(resolve);
			enemyDead=true;
		}
		
		return enemyDead;
	}

	public int getEnemyID() {
		return enemyID;
	}

	public void setEnemyID(int enemyID) {
		this.enemyID = enemyID;
	}

	public int getTargetRange() {
		return targetRange;
	}

	public void setTargetRange(int targetRange) {
		this.targetRange = targetRange;
	}

	

}
