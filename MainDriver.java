import java.util.Scanner;

/**
 * The MainDriver class is the place to play the battle game.
 * It allows users to create characters, assign stats, and progress through 
 * three levels against enemy characters.
 * 
 * @author williamyao
 */
public class MainDriver {
    public static void main(String[] args) throws InterruptedException {
        Scanner kb = new Scanner(System.in); 
        
        // Part - A (Creating player characters)
        Character[] player = new Character[2];
        
        for (int i = 0; i < 2; i++) {
            System.out.println("Choose a class for player " + (i + 1) + ":"); 
            Thread.sleep(500);
            System.out.println("1) Fighter");
            System.out.println("2) Cleric");
            System.out.println("3) Rogue");
            System.out.println("4) Wizard"); 
            Thread.sleep(500);
            
            System.out.print("\nEnter the number of your choice " + (i + 1) + ": ");
            int choice = kb.nextInt(); 
            Thread.sleep(500);
            
            while (choice != 1 && choice != 2 && choice != 3 && choice != 4) {
                System.out.println("\nTry again: 😂"); Thread.sleep(300);
                System.out.print("Enter the number of your choice " + (i + 1) + ": ");
                choice = kb.nextInt(); Thread.sleep(500);
            }
            
            System.out.print("\nEnter the name you want for the character: ");
            String name = kb.next();
            int strength; int dexterity; int intelligence; int wisdom; int total;
            
            do {
                Thread.sleep(500);
                System.out.println("\nYou have 24 points, distribute them between " 
                        + "strength, dexterity, intelligence, and wisdom");
                
                System.out.print("Strength: ");
                strength = kb.nextInt();

                System.out.print("Dexterity: ");
                dexterity = kb.nextInt();

                System.out.print("Intelligence: ");
                intelligence = kb.nextInt();

                System.out.print("Wisdom: ");
                wisdom = kb.nextInt(); Thread.sleep(500);
                
                total = strength + dexterity + intelligence + wisdom;
                
                if (total != 24) {
                    System.out.println("Learn your math! They don't add to 24!");
                } 
                nextLine(); nextLine();
            } while (total != 24);
            
            switch (choice) {
                case 1:
                    player[i] = new Fighter(name, 1, strength, dexterity, 
                                           intelligence, wisdom);
                    break;
                case 2:
                    player[i] = new Cleric(name, 1, strength, dexterity, 
                                          intelligence, wisdom);
                    break;
                case 3:
                    
                    player[i] = new Rogue(name, 1, strength, dexterity, 
                                         intelligence, wisdom);
                    break;
                case 4: 
                    player[i] = new Wizard(name, 1, strength, dexterity, 
                                          intelligence, wisdom);
                    break;
            } 
        }
        
        Thread.sleep(1000);
        System.out.println("Here are your character's stats:\n"); 
        Thread.sleep(1000);
        System.out.println(player[0] + "\nMax HP: " + player[0].getHP() + "\n"); 
        Thread.sleep(500);
        System.out.println(player[1] + "\nMax HP:" + player[1].getHP());

        
        // Part - B (Creating enemy characters)
        // Three Rogue enemies
        Rogue[] eFight1 = new Rogue[3];
        
        for (int i = 0; i < 3; i++) {
            eFight1[i] = new Rogue("EnemyRogue " + (i+1), 1, 3, 3, 3, 3);
        }
        
        // One Wizard enemy,  one Cleric enemy
        Character[] eFight2 = new Character[2];
        
        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                eFight2[i] = new Wizard("Wizard 1", 3, 6, 6, 6, 6);
            } else {
                eFight2[i] = new Cleric("Cleric 1", 3, 6, 6, 6, 6);
            }
        }
        
        // One Fighter enemy
        Character[] eFight3 = new Character[1];
        eFight3[0] = new Fighter("Fighter 1", 6, 10, 10, 10, 10);
        
        
        // Part - C 
        Thread.sleep(5000);
        System.out.println("\n\nLoading Level 1....\n\n");
        Thread.sleep(3000);
        boolean passed1 = mixLevel(player, eFight1);
        boolean passed2 = false;
        boolean passed3 = false;
    
        if (passed1) {
            Thread.sleep(1000);
            System.out.println("\nYou have passed level 1");
            
            System.out.println("\nYour characters have leveled up!");
            levelUp(player);
            
            System.out.println("\n\nLoading Level 2....\n\n");
            Thread.sleep(3000);
            passed2 = mixLevel(player, eFight2);
        }   
        
        if (passed2) {
            Thread.sleep(1000);
            System.out.println("\nYou have passed level 2");
           
            System.out.println("\nYour characters have leveled up!");
            levelUp(player);
            
            System.out.println("\nLoading Level 3....\n\n");
            Thread.sleep(3000);
            passed3 = mixLevel(player, eFight3);
        }

        if (passed3) {
            Thread.sleep(1000);
            System.out.println("\n\nYou have passed level 3"); 
            Thread.sleep(1000);
            System.out.println("Good job. Stay tuned for more levels added "
                               + "in the future!");
        }
    }
    
    /**
     * mixLevel(): Character[] -> Boolean
     * Description: consumes two Character[] array and returns true if player wins,
     *              false otherwise
     * Example: mixLevel(player, eFight) -> True
     * @param player Character array to be played with
     * @param eFight Character array to be played with
     * @return returns true or returns false
     * @throws InterruptedException 
     */
    public static boolean mixLevel (Character[] player, Character[] eFight) 
        throws InterruptedException{
        
        Scanner kb = new Scanner(System.in);
        final String RED = "\u001B[31m";
        final String GREEN = "\u001B[32m";
        final String RESET = "\u001B[0m";
        
        showStats(player, eFight); 
        Thread.sleep(3000);
        int turn = 0;

        while (!gameOver(player) && !gameOver(eFight)) {
            if (turn % 2 == 0) {
                int index = (turn / 2) % 2;
                Character current = player[index];

                if (current.getIsFainted()) {
                    index = 1 - index;
                    current = player[index];
                }
                
                int choice;

                if (current instanceof Fighter) {
                    do {
                        System.out.println("You are using " + current + " (Fighter)");
                        System.out.println("\nAvailable actions:\n1) Attack");
                        choice = kb.nextInt();
                        
                        if (choice != 1) {
                            System.out.println("Try Again...");
                        }
                    } while (choice != 1);
                } else if (current instanceof Wizard) {
                    do {
                        System.out.println("You are using " + current + " (Wizard)");
                        System.out.println("\nAvailable actions:\n1) Attack\n2) MultiAttack");
                        choice = kb.nextInt();
                        
                        if (choice != 1 && choice != 2) {
                            System.out.println("Try Again...");
                        }  
                    } while (choice != 1 && choice != 2);
                    
                    if (choice == 2) {
                        ((Wizard) current).multiAttack(eFight);
                        Thread.sleep(500);
                        System.out.println(RED+"\nYou attacked all enemies."+RESET);
                    }
                } else {
                    do {
                        if (current instanceof Cleric) {
                            System.out.println("You are using " + current + " (Cleric)");
                        } else {
                            System.out.println("You are using " + current + " (Rogue)");
                        }
                        
                        System.out.println("\nAvailable actions:\n1) Attack\n2) Heal");
                        choice = kb.nextInt();

                        if (choice == 2 && player[1 - index].getIsFainted()) {
                            System.out.println("\nYour other player is already fainted");
                            choice = -1;
                        }
                        
                        if (choice != 1 && choice != 2) {
                            System.out.println("Try Again");
                        }
                    } while (choice != 1 && choice != 2);
                    
                    if (choice == 2) {
                        if (current instanceof Cleric) {
                            ((Cleric) current).heal(player[1 - index]);
                        } else if (current instanceof Rogue) {
                            ((Rogue) current).heal(player[1 - index]);
                        }
                        
                        Thread.sleep(500);
                        System.out.println("\n"+GREEN+"You healed your teammate"+RESET);
                    }
                }
                
                if (choice == 1) {
                    int targetIndex = nextAttackIndex(eFight);
                    current.attack(eFight[targetIndex]);
                    Thread.sleep(500);
                    System.out.println("\n"+RED+"You attacked "+ eFight[targetIndex].getName()+RESET);
                }
            } else { 
                Character current;
                int index = (turn / 2) % eFight.length;
                
                while (eFight[index].getIsFainted()) {
                    index = (index + 1) % eFight.length;
                }
                
                current = eFight[index];
                
                int targetIndex = nextAttackIndex(player);
                current.attack(player[targetIndex]);
                Thread.sleep(500);
                
                System.out.println("\n"+RED+current.getName() + " attacked " 
                                   + player[targetIndex].getName()+RESET);
                
                if (player[targetIndex].getIsFainted()) {
                    System.out.println(RED+"One of your player is now fainted!"+RESET);
                }
            }
            
            Thread.sleep(500); 
            nextLine();
            showStats(player, eFight);
            Thread.sleep(3000);
            turn++;
        }

        return !gameOver(player);
    }
    
    /**
     * nextAttackIndex(): Character[] -> int
     * Description: Consumes a Character array and returns an int value that should
     *              be the index of the enemy array to be attacked next
     * Example: nextAttackIndex(eFight) -> 2
     * @param c Character array to be used to find the next index
     * @return returns an int value
     */
    public static int nextAttackIndex(Character[] c) {
        for (int i = 0; i < c.length; i++) {
            if (!c[i].getIsFainted()) {
                return i;
            }
        }
        
        return -1;
    }
    
    /**
     * gameOver(): Character[] -> Boolean
     * Description: consumes a Character array and returns true if all objects
     *              is the array are fainted, false otherwise
     * Example: gameOver(eFight) -> true
     * @param c Character array to be checked
     * @return returns true or returns false
     */
    public static boolean gameOver(Character[] c) {
        for (Character x: c) {
            if (!x.getIsFainted()) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * levelUp(): Character[] -> void
     * Description: consumes a Character array and levelUp all objects in the array
     * Example: levelUp(player) will level up all objects in the player array
     * @param c Character array to be leveled up with
     */
    public static void levelUp(Character[] c){
        for (Character x: c) {
            x.levelUp();
        }
    }
    
    /**
     * showStats(): Character[] -> void
     * Description: consumes two Character array and shows their names and HPs
     * Example: showStats(player, eFight) -> ----- Current Battle Stats -----
                                             Your Team:
                                             William - HP: 26.0
                                             Kelvin - HP: 26.0

                                             Enemies:
                                             EnemyRogue 1 - HP: 14.0
                                             EnemyRogue 2 - HP: 14.0
                                             EnemyRogue 3 - HP: 14.0
                                             --------------------------------
     * @param player Character array to show stats with
     * @param enemy Character array to show stats with
     * @throws InterruptedException 
     */
    public static void showStats(Character[] player, Character[] enemy) throws 
        InterruptedException {
        System.out.println("\n----- Current Battle Stats -----");

        System.out.println("Your Team:");
        for (Character p : player) {
            Thread.sleep(500);
            System.out.println(p.getName() + 
                    (p.getIsFainted() ? " (Fainted)" : " - HP: " + p.getHP()));
        }

        System.out.println("\nEnemies:");
        for (Character e : enemy) {
            Thread.sleep(500);
            System.out.println(e.getName() + 
                    (e.getIsFainted() ? " (Fainted)" : " - HP: " + e.getHP()));
        }

        System.out.println("--------------------------------\n");
    }
    
    /**
     * nextLine(): void -> void
     * Description: prints an empty line
     * Example: nextLine(): -> "\n"
     */
    public static void nextLine() {
        System.out.println();
    }
}