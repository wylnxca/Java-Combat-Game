import java.util.Random;

/**
 * Represents a Fighter character, a subclass of Character
 * 
 * @author williamyao
 */
public class Fighter extends Character {
    /**
     * new Fighter(): String -> void
     * Description: Consumes a string value and sets the name 
     *              to the consumed string value, level to 1, isFainted to false,
     *              stats to int values between 1 ~ 6, and HP based on the stats
     * Example: new Fighter("William") initializes all fields 
     *          with name as William
     * @param name String value to set name as
     */
    public Fighter(String name) {
        super(name);
    }
    
    /**
     * new Fighter(): String, int -> void
     * Description: Consumes a string value and four int values and sets the name
     *              to the consumed string value, isFainted to false, and
     *              other fields to the consumed int values
     * Example: new Fighter("William", 1, 1, 1, 1, 1) initializes name to
     *          William, level, strength, dexterity, intelligence, and wisdom
     *          to 1
     * @param name String value to set name as
     * @param level int value to set level as
     * @param strength int value to set strength as
     * @param dexterity int value to set dexterity as
     * @param intelligence int value to set intelligence as
     * @param wisdom int value to set wisdom as
     */
    public Fighter(String name, int level, int strength, int dexterity,
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
            
        double raw = ((r1 + r2 - 6) / 6.0) * (super.getStrength()
                     + super.getDexterity() + super.getLevel()) + 6;
        int attack = (int)Math.floor(Math.abs(raw));
        // "Math.floor" always rounds the number downward, toward negative infinity.
        
        if (!c.getIsFainted()) {
            c.setHP(c.getHP() - attack);
        }
    }
    
    /**
     * levelUp(): void -> void
     * Description: sets level to original level + 1, strength to 
     *              original strength + 2, dexterity to original dexterity + 1
     *              intelligence to original intelligence + 1, and wisdom
     *              to original wisdom + 1
     * Example: levelUp() sets all fields with new changes
     */
    @Override
    public void levelUp() {
        super.setLevel(super.getLevel() + 1);
        super.setStrength(super.getStrength() + 2);
        super.setDexterity(super.getDexterity() + 1);
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
     * Example: toString() -> Level 1 fighter named William with 6 strength, 
     *                        6 dexterity, 6 intelligence, and 6 wisdom.
     * @return returns a string value
     */
    @Override
    public String toString() {
        return "Level " + super.getLevel() + " fighter named " + super.getName()
               + " with " + super.getStrength() + " strength, " + super.getDexterity()
               + " dexterity, " + super.getIntelligence() + " intelligence, and " 
               + super.getWisdom() + " wisdom.";
    }  
}