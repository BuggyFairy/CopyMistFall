package com.mygdx.game.mistfall.enemy;

import java.util.LinkedList;

import com.mygdx.game.mistfall.enemy.enums.EnemyAbilityType;
import com.mygdx.game.mistfall.enemy.enums.EnemyKeyword;
import com.mygdx.game.mistfall.enemy.enums.EnemySuit;
import com.mygdx.game.mistfall.enemy.enums.EnemyType;
import com.mygdx.game.mistfall.model.Conditions;
import com.mygdx.game.mistfall.model.enums.AttackType;
import com.mygdx.game.mistfall.model.enums.Keyword;
import com.mygdx.game.mistfall.model.modifications.ModSource;
import com.mygdx.game.mistfall.model.modifications.ModTarget;
import com.mygdx.game.mistfall.model.modifications.ModType;
import com.mygdx.game.mistfall.model.modifications.Modification;

public class Enemy  {
	
	// {{ Attributes
	private int enemyID;
	private String name;
	private EnemySuit enemySuit;
	private EnemyType enemyType;
	private boolean specialEnemy;
	private LinkedList<EnemyKeyword> enemyKeyword;
	private int resolve;
	private LinkedList<Keyword> vulnerability;
	private Conditions conditions;

	private AttackValues attack;
	private ResistanceValues resistances;
	private LifeValues life;
	
	private int targetRange;
	private boolean inRange;
	
	private boolean enraged;
	
	private LinkedList<EnemyAbility> abilities;
	private LinkedList<Modification> modifications;
	
	private boolean selected;
	
	// }}
	
	// {{ Constructor
	public Enemy(){
		enemyKeyword=new LinkedList<EnemyKeyword>();
		vulnerability=new LinkedList<Keyword>();
		conditions=new Conditions();
		attack= new AttackValues();
		resistances=new ResistanceValues();
		life=new LifeValues();
		abilities=new LinkedList<EnemyAbility>();
		modifications=new LinkedList<Modification>();
		setSelected(false);
	}
	// }}
	
	// {{ Getters & Setters
	public int getEnemyID() {
		return enemyID;
	}
	public void setEnemyID(int enemyID) {
		this.enemyID = enemyID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public EnemySuit getEnemySuit() {
		return enemySuit;
	}
	public void setEnemySuit(EnemySuit enemySuit) {
		this.enemySuit = enemySuit;
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
	public void setEnemyKeyword(LinkedList<EnemyKeyword> enemyKeyword) {
		this.enemyKeyword = enemyKeyword;
	}
	public int getResolve() {
		return resolve;
	}
	public void setResolve(int resolve) {
		this.resolve = resolve;
	}
	public LinkedList<Keyword> getVulnerability() {
		return vulnerability;
	}
	public void setVulnerability(LinkedList<Keyword> vulnerability) {
		this.vulnerability = vulnerability;
	}
	public Conditions getConditions() {
		return conditions;
	}
	public void setConditions(Conditions conditions) {
		this.conditions = conditions;
	}
	public AttackValues getAttack() {
		return attack;
	}
	public void setAttack(AttackValues attack) {
		this.attack = attack;
	}
	public ResistanceValues getResistances() {
		return resistances;
	}
	public void setResistances(ResistanceValues resistances) {
		this.resistances = resistances;
	}
	public LifeValues getLife() {
		return life;
	}
	public void setLife(LifeValues life) {
		this.life = life;
	}
	public int getTargetRange() {
		return targetRange;
	}
	public void setTargetRange(int targetRange) {
		this.targetRange = targetRange;
	}
	public boolean isEnraged() {
		return enraged;
	}
	public void setEnraged(boolean enraged) {
		this.enraged = enraged;
	}
	public LinkedList<EnemyAbility> getAbilities() {
		return abilities;
	}
	public void setAbilities(LinkedList<EnemyAbility> abilities) {
		this.abilities = abilities;
	}
	public LinkedList<Modification> getModifications() {
		return modifications;
	}
	public void setModifications(LinkedList<Modification> modifications) {
		this.modifications = modifications;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public boolean isInRange() {
		return inRange;
	}

	public void setInRange(boolean inRange) {
		this.inRange = inRange;
	}


	// }}

	// {{ Other Methods
	
	public void appendEnemyKeyword(EnemyKeyword ek){
		if(this.enemyKeyword != null){
			this.enemyKeyword.add(ek);
		}
	}

	public void appendVunerability(Keyword v){
		if(this.vulnerability != null){
			this.vulnerability.add(v);
		}
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
	public boolean updateModification(ModSource modSource, ModType modType,ModTarget modTarget,int value, int sourceID){
		boolean updated=false;
		int pos=-1;
		
		// Check if the Modification exists and save the position in the list
		for (int i=0;i<modifications.size();i++){
			if (modifications.get(i).getModType()==modType && 
				modifications.get(i).getModSource()==modSource &&
				modifications.get(i).getModTarget()==modTarget &&
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
			modifications.add(new Modification(modSource,modType,modTarget,value,sourceID));
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
	 *  Removes the specified Modification if available and adjusts the values if necessary. 
	 *  Returns TRUE If the specified Modification was found and removed. 
	 *  If sourceID==-1 ANY Modification of the ModType is removed regardless the sourceID
	 */
	public boolean removeModification(ModType modType, ModSource modSource, int sourceID){
		boolean removed=false;
		int pos=-1;
		// If the sourceID is >-1 remove the specified Modification
		if (sourceID>-1){
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
		}
		// If the sourceID == -1 remove all Modification that match the modType
		if (sourceID==-1){
			int i=0;
			// Go through all Modifications
			while (i<modifications.size()){
				// If a matching Modification was found, adjust Values if necessary and remove the Modification
				if (modifications.get(i).getModType()==modType){
					switch(modifications.get(i).getModTarget()){
						case ATTACK:
							attack.setValueMod(attack.getValueMod()-modifications.get(i).getValue());
						break;
						case LIFE:
							life.setValueBase(life.getValueBase()-modifications.get(i).getValue());
							life.setValueCurrent(life.getValueCurrent()-modifications.get(i).getValue());
						break;
						case PHYSICAL_RESISTANCE:
							resistances.setPhysicalResMod(resistances.getPhysicalResMod()-modifications.get(i).getValue());
						break;
						case MAGICAL_RESISTANCE:
							resistances.setMagicalResMod(resistances.getMagicalResMod()-modifications.get(i).getValue());
						break;
						case RANGE:
							targetRange=targetRange-modifications.get(i).getValue();
						break;
						
						default:
						break;
					}
					modifications.remove(i);
					removed=true;
				}
				else{
					i++;
				}
			}
		}
		
		return removed;
	}
	
	/**
	 * Clears the Modification List an resets Life, Attack and Resistance Values to the Base Values
	 */
	public void clearModifications(){
		// Clear Modification List
		getModifications().clear();
		// Reset Life, Attack and Resistance Values to the Base Values
		getAttack().setValueMod(getAttack().getValueBase());
		getLife().setValueMod(getLife().getValueBase());
		getLife().setValueCurrent(getLife().getValueBase());
		getResistances().setMagicalResMod(getResistances().getMagicalResBase());
		getResistances().setPhysicalResMod(getResistances().getPhysicalResBase());
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

	/**
	 * Removes the specified "woundCount" value from the current life of the enemy.
	 * Returns true if the enemy died.
	 */
	public void applyWounds(int woundCount){
		// Apply Wounds
		life.setValueCurrent(life.getValueCurrent()-woundCount);
	}
	
	public void applyDamage(int damageCount, AttackType attackType){
		
		switch (attackType){
			case PHYSICAL:
				if ((resistances.getPhysicalResMod()-damageCount)<0){
					life.setValueCurrent(life.getValueCurrent()+resistances.getPhysicalResMod()-damageCount);
				}
			break;
			
			case MAGICAL:
				if ((resistances.getMagicalResMod()-damageCount)<0){
					life.setValueCurrent(life.getValueCurrent()+resistances.getMagicalResMod()-damageCount);
				}
			break;
			
			default:
			break;
		}
		
	}

	public String getEnrageString(){
		
		switch (enemyType){
			case RENEGADE_FLAMECASTER:
				return "<Enrage> +1<Magical Resistance>.";
			case BLACKWOOD_FIGHTER:
				return "<Enrage> +1<Physical Resistance> and +1<Magical Resistance>.";
			case BLACKWOOD_AMBUSHER:
			case BLACKWOOD_ASSASSIN:
			case BONESORROW_SHOOTER:
			case BONESORROW_WARRIOR:
			case DIRE_WOLF:
			case TRACKER_HOUND:
				return "<Enrage> Deals +1<Physical Damage>.";
			case TWISTED_CURSEBEARER:
			case BLACKWOOD_CHANGELING:
			case BLOODSCORNE_VAMPIRE:
				return "<Enrage> Deals +1<Magical Damage>.";
			case CURSED_WALKER:
			case ICE_REAVER:
				return "<Enrage> Ignore Slow.";
			case BLACK_COVEN_CASTER:
			case GHOREN_RAGECALLER:
			case GHOREN_SMALLHORN:
			case GHOREN_WARRIOR:
			case RAVENOUS_DRAUGR:
			case VAMPIRE_BAT_SWARM:
			case WILD_ICEHOUND:
				return "<Enrage> Attack. <Calm>";
			case UNDEAD_LOREMASTER:
				return "<Enrage> Removes all Wounds from this Enemy. <Calm>";
			case GHOREN_SLINGER:
			case GHOREN_BLOOD_TRACKER:
				return "<Enrage> Remove 1<Wound Token>. <Calm>";
			case BONESORROW_MAGUS:
				return "<Enrage> Remove 1<Wound Token> from all UNDEAD in same Area. <Calm>";
			case WILDLANDS_SHAMAN:
				return "<Enrage> Discard 1 Card. <Calm>";
			case BLACKWOOD_CUTTPURSE:
				return "<Enrage> Apply Thievery effect if a card was Buried. <Calm>";
			case BLACKWOOD_HARASSER:
				return "<Enrage> Apply Skulduggery effect if a card was Buried. <Calm>";
			case BLACKWOOD_MAGEHUNTER:
				return "<Enrage> Apply Mana Drain effect if a card was Buried. <Calm>";
			case TWISTED_LASHER:
				return "<Enrage> Activate the Flailing ability. <Calm>";
			case FELLSTALKER:
				return "<Enrage> Activate the Scavenger ability. <Calm>";
			case VENGEFUL_BANSHEE:
				return "<Enrage> Activate Vengeful Shriek. <Calm>";
			
			default:	
				return "";
		}
	}
	// }}
	
}
