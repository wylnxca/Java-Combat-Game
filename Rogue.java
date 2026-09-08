import java.util.Random;

/**
 * Represents a Rogue character, a subclass of Character
 * 
 * @author williamyao
 */
public class Rogue extends Character {
    /**
     * new Rogue(): String -> void
     * Description: Consumes a string value and sets the name 
     *              to the consumed string value, level to 1, isFainted to false,
     *              stats to int values between 1 ~ 6, and HP based on the stats
     * Example: new Rogue("William") initializes all fields 
     *          with name as William
     * @param name String value to set name as
     */
    public Rogue(String name) {
        super(name);
    }
    
    /**
     * new Rogue(): String, int -> void
     * Description: Consumes a string value and four int values and sets the name
     *              to the consumed string value, isFainted to false, and
     *              other fields to the consumed int values
     * Example: new Rogue("William", 1, 1, 1, 1, 1) initializes name to
     *          William, level, strength, dexterity, intelligence, and wisdom
     *          to 1
     * @param name String value to set name as
     * @param level int value to set level as
     * @param strength int value to set strength as
     * @param dexterity int value to set dexterity as
     * @param intelligence int value to set intelligence as
     * @param wisdom int value to set wisdom as
     */
    public Rogue(String name, int level, int strength, int dexterity,
                int intelligence, int wisdom) {
        super(name, level, strength, dexterity, intelligence, wisdom);
    }
    
    /**
     * attack(): Character -> void
     * Description: consumes a Character object and attacks it with the object
     *              that calls this method.
     * Example: attack(eFight) attacks eFight
     * @param c Character object to be attacked
     */
    @Override
    public void attack(Character c) {
        Random r = new Random();
        int r1 = r.nextInt(6) + 1;
        int r2 = r.nextInt(6) + 1;
            
        double raw = ((r1 + r2 - 4) / 6.0 * (super.getStrength() 
                     + super.getDexterity() + super.getLevel())) + 4;
        int attack = (int)Math.floor(Math.abs(raw));
        
        if (!c.getIsFainted()) {
            c.setHP(c.getHP() - attack);
        }
    }
    
    /**
     * heal(): Character -> void
     * Description: consumes a Character object and heals it with the object 
     *              that calls this method
     * Example: heal(eFight) heals eFight
     * @param c Character object to be healed
     */
    public void heal(Character c) {
        Random r = new Random();
        int r1 = r.nextInt(8) + 1;
        
        double raw = (r1 + super.getWisdom() + super.getLevel()) / 3.0;
        int heal = (int)Math.floor(raw);
        double maxHealth = 2 * (c.getLevel() 
                        + Math.min(c.getStrength(), c.getDexterity()) 
                        + Math.min(c.getIntelligence(), c.getWisdom()));
        
        if (!c.getIsFainted()) {
            c.setHP(Math.min(c.getHP() + heal, maxHealth));
        }
    }
    
    /**
     * levelUp(): void -> void
     * Description: sets level to original level + 1, strength to 
     *              original strength + 1, dexterity to original dexterity + 2
     *              intelligence to original intelligence + 1, and wisdom
     *              to original wisdom + 1
     * Example: levelUp() sets all fields with new changes
     */
    @Override
    public void levelUp() {
        super.setLevel(super.getLevel() + 1);
        super.setStrength(super.getStrength() + 1);
        super.setDexterity(super.getDexterity() + 2);
        super.setIntelligence(super.getIntelligence() + 1);
        super.setWisdom(super.getWisdom() + 1);
        
        int newHP = 2 * (super.getLevel()  
                    + Math.min(super.getStrength(), super.getDexterity()) 
                    + Math.min(super.getIntelligence(), super.getWisdom()));
        super.setHP(newHP);
    }
    
    /**
     * toString(): void -> String
     * Descriptions: returns the fields of the object
     * Example: toString() -> Level 1 rogue named William with 6 strength, 
     *                        6 dexterity, 6 intelligence, and 6 wisdom.
     * @return returns a string value
     */
    @Override
    public String toString() {
        return "Level " + super.getLevel() + " rogue named " + super.getName() 
               + " with " + super.getStrength() + " strength, " + super.getDexterity() 
               + " dexterity, " + super.getIntelligence() + " intelligence, and " 
               + super.getWisdom() + " wisdom.";
    }  
}