import java.util.Random;

/**
 * Abstract base class for all character types in the game
 * 
 * @author williamyao
 */
public abstract class Character {
    private String name;
    private int level;
    private int[] stats = new int[4]; 
    // 0 - strength, 1 - dexterity, 2 - intelligence, 3 - wisdom
    private double HP;
    private boolean isFainted = false;
    
    /**
     * new Character(): String -> void
     * Description: Consumes a string value and sets the name 
     *              to the consumed string value, level to 1, isFainted to false,
     *              stats to int values between 1 ~ 6, and HP based on the stats
     * Example: new Character("William") initializes all fields 
     *          with name as William
     * @param name String value to set name as
     */
    public Character(String name) {
        Random r = new Random();
        
        this.name = name;
        level = 1;
        isFainted = false;
        
        for (int i = 0; i < 4; i++) {
            stats[i] = r.nextInt(6) + 1;
        }
        
        HP = 2 * (level + Math.min(stats[0], stats[1])
             + Math.min(stats[2], stats[3]));
    }
    
    /**
     * new Character(): String, int -> void
     * Description: Consumes a string value and four int values and sets the name
     *              to the consumed string value, isFainted to false, and
     *              other fields to the consumed int values
     * Example: new Character("William", 1, 1, 1, 1, 1) initializes name to
     *          William, level, strength, dexterity, intelligence, and wisdom
     *          to 1
     * @param name String value to set name as
     * @param level int value to set level as
     * @param strength int value to set strength as
     * @param dexterity int value to set dexterity as
     * @param intelligence int value to set intelligence as
     * @param wisdom int value to set wisdom as
     */
    public Character(String name, int level, int strength, int dexterity,
                    int intelligence, int wisdom) {
        this.name = name;
        this.level = level;
        isFainted = false;
        
        stats[0] = strength;
        stats[1] = dexterity;
        stats[2] = intelligence;
        stats[3] = wisdom;
        
        HP = 2 * (level + Math.min(strength, dexterity)
             + Math.min(intelligence, wisdom));
    }
    
    /**
     * getName(): void -> String
     * Description: returns the name of the object
     * Examples: getName() -> "William"
     * @return returns a String value
     */
    public String getName() { 
        return name; 
    }
    
    /**
     * getLevel(): void -> int
     * Description: returns the level of the object
     * Example: getLevel(): -> 1
     * @return returns an int value
     */
    public int getLevel() { 
        return level; 
    }
    
    /**
     * setLevel(): int -> void
     * Description: Consumes an int value and sets the level as the int value
     * Example: setLevel(1) -> level = 1
     * @param level int value to be set as
     */
    public void setLevel(int level)  {
        this.level = level;
    }
    
    /**
     * getStrength(): void -> int
     * Description: returns the strength of the object
     * Example: getStrength() -> 1
     * @return returns an int value
     */
    public int getStrength() {
        return stats[0];
    }
    
    /**
     * setStrength(): int -> void
     * Description: Consumes an int value and sets the strength as the int value
     * Example: setStrength(1) -> strength = 1
     * @param strength int value to be set as
     */
    public void setStrength(int strength) {
        stats[0] = strength;
    }
    
    /**
     * getDexterity(): void -> int
     * Description: returns the dexterity of the object
     * Example: getDexterity() -> 1
     * @return returns an int value
     */
    public int getDexterity() {
        return stats[1];
    }
    
    /**
     * setDexterity(): int -> void
     * Description: Consumes an int value and sets the dexterity as the int value
     * Example: setDexterity(1) -> dexterity = 1
     * @param dexterity int value to be set as
     */
    public void setDexterity(int dexterity) {
        stats[1] = dexterity;
    }
    
    /**
     * getIntelligence(): void -> int
     * Description: returns the intelligence of the object
     * Example: getIntelligence() -> 1
     * @return returns an int value
     */
    public int getIntelligence() {
        return stats[2];
    }
    
    /**
     * setIntelligence(): int -> void
     * Description: Consumes an int value and sets the intelligence as the int value
     * Example: setIntelligence(1) -> intelligence = 1
     * @param intelligence int value to be set as
     */
    public void setIntelligence(int intelligence) {
        stats[2] = intelligence;
    }
    
    /**
     * getWisdom(): void -> int
     * Description: returns the wisdom of the object
     * Example: getWisdom() -> 1
     * @return returns an int value
     */
    public int getWisdom() {
        return stats[3];
    }
    
    /**
     * setWisdom(): int -> void
     * Description: Consumes an int value and sets the wisdom as the int value
     * Example: setWisdom(1) -> wisdom = 1
     * @param wisdom int value to be set as
     */
    public void setWisdom(int wisdom) {
        stats[3] = wisdom;
    }
    
    /**
     * getHP(): void -> double
     * Description: returns the HP of the object
     * Example: getHP() -> 1.0
     * @return returns an double value
     */
    public double getHP() {
        return HP;
    }
    
    /**
     * setWisdom(): double -> void
     * Description: Consumes an double value and sets the wisdom as the double value
     * Example: setHP(1.0) -> HP = 1.0
     * @param HP double value to be set as
     */
    public void setHP(double HP) {
        if (HP <= 0) {
            this.HP = 0;
            isFainted = true;
        } else {
            this.HP = HP;
            isFainted = false;
        }
    }
    
    /**
     * getIsFainted(): void -> Boolean
     * Description: returns true if fainted, false otherwise
     * Example: getIsFainted() -> true
     * @return returns true or returns false
     */
    public boolean getIsFainted() {
        return isFainted;
    }
    
    /**
     * setIsFainted(): Boolean -> void
     * Description: Consumes an Boolean value and sets the isFainted as the 
     *              Boolean value
     * Example: setIsFainted(true) -> isFainted = true
     * @param isFainted Boolean value to be set as
     */
    public void setIsFainted(boolean isFainted) {
        this.isFainted = isFainted;
    }
    
    /**
     * getStatus(): void -> String
     * Description: returns the name and HP of the object
     * Example: getStatus() -> "William has 1 hp remaining"
     * @return returns an string value
     */
    public String getStatus() {
        return name + " has " + HP + " hp remaining";
    }
    
    public abstract void levelUp();
    
    public abstract void attack(Character c);
    
    @Override
    public abstract String toString();
}