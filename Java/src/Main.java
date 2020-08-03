import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // equalsIgnoreCase ignores upper or lower. Turns "Hi" to "hi".
        // We ask player for name.
        Scanner sc = new Scanner(System.in);
        System.out.println("What do you want the name to be?");
        String name = sc.nextLine();

        // We ask player for class.
        System.out.println("What do you want the class to be?\n" +
                "Barbarian, Fighter, Rogue");
        String className = sc.nextLine();

        // This prints "Invalid" if the player does not select any of the valid choices.
        if (!className.equalsIgnoreCase("barbarian")
                && !className.equalsIgnoreCase("fighter")
                && !className.equalsIgnoreCase("rogue")) {
            System.out.println("Invalid choice.");
            return;
        }

        // We ask player for race.
        System.out.println("What do you want the race to be?\n" +
                "Elf, Dwarf, Orc");
        String race = sc.nextLine();

        // This prints "Invalid" if player did not select any of the valid choices.
        if (!race.equalsIgnoreCase("elf")
                && !race.equalsIgnoreCase("dwarf")
                && !race.equalsIgnoreCase("orc")) {
            System.out.println("Invalid choice.");
            return;
        }

        // There are two array lists for each corresponding side of the battle.
        ArrayList<Enemy> enemies = new ArrayList<>();
        ArrayList<Player> players = new ArrayList<>();

        // Create objects here.
        Player p1 = new Player(name, race, className.toLowerCase());    // toLowerCase does exactly what it says.
        Enemy e1 = new Enemy();
        Enemy e2 = new Enemy();
        enemies.add(e1);
        enemies.add(e2);

        // Keep looping till there are no enemies.
        while(enemies.size() != 0) {
            // Let the user pick enemies.
            System.out.println("Pick an enemy that you want to fight or type \"exit\".");
            for (int i = 0; i < enemies.size(); i++) {
                System.out.println("Enemy" + i + ", HP: " + enemies.get(i).getHp());
            }
            String next = sc.nextLine();

            // If they want to exit, then exit.
            if(next.equalsIgnoreCase("exit")){
                System.out.println("Exiting..");
                System.exit(0);
            }

            // Otherwise, keep going and get the choice they want (1, 2..)
            int choice = Integer.parseInt(next);
            if (choice >= enemies.size()) {
                System.out.println("That enemy doesn't exist.");
                return;
            }

            Enemy chosenEnemy = enemies.get(choice);
            System.out.println("You picked Enemy" + choice +"\n Initiating battle..\n");

            // To figure out who goes first.
            int initiative = (int) (Math.random() * 20 + 1) + p1.getDexterity() / 2;
            // If it's a higher number, player goes first.
            if (initiative >= 10) {
                // Go until either one is dead.
                while (chosenEnemy.getHp() > 0 && p1.getHp() > 0) {
                    // Ask them what they want to do, attack, skip turn or exit the program.
                    System.out.println("What do you want to do?\n" +
                            "Attack or Skip or Exit");

                    // Get their choice of what they want to do.
                    String option = sc.nextLine();

                    // If they want to attack.
                    if (option.equalsIgnoreCase("attack")) {
                        // Damage from enemy to player.
                        int damageFromEnemy = chosenEnemy.getDamage();
                        // Damage from player to enemy.
                        int damageFromPlayer = p1.getDamage();

                        // First player deals damage to enemy.
                        chosenEnemy.dealDamage(damageFromPlayer);
                        System.out.println("\nYou dealt " + damageFromPlayer + " hp damage to enemy. Enemy's hp is now at " + chosenEnemy.getHp());

                        // If hp is already less than 0, then break from while loop.
                        if (chosenEnemy.getHp() <= 0) {
                            break;
                        }
                        // Then the enemy deals damage to player.
                        p1.dealDamage(damageFromEnemy);
                        System.out.println("Enemy dealt " + damageFromEnemy + " hp damage. " + p1.getName() + ", your hp is now at " + p1.getHp());
                    }
                    // If they want to skip their turn.
                    else if (option.equalsIgnoreCase("skip")) {
                        // Again, get the damage from enemy.
                        int damageFromEnemy = chosenEnemy.getDamage();

                        // Deal damage to player from enemy (skip to this since player chose skip).
                        p1.dealDamage(damageFromEnemy);
                        System.out.println("Enemy dealt " + damageFromEnemy + " hp damage. " + p1.getName() + ", your hp is now at " + p1.getHp());
                    }
                    // If they want to exit, just exit the program.
                    else if (option.equalsIgnoreCase("exit")) {
                        System.out.println("Exiting..");
                        System.exit(0);
                    }
                }

                // If the enemy's hp is less than 0, then you won the battle (remove the enemy too).
                if (chosenEnemy.getHp() <= 0) {
                    System.out.println("Yay, you won the battle. Your health has been restored.");
                    enemies.remove(choice);
                    p1.restoreHealth();
                }
                // If not, then you probably died, so, you lost.
                else {
                    System.out.println("You lost the battle and you're dead, unfortunate.");
                    System.exit(0);
                }
            }
            // If it's lower, then enemy goes first.
            else {
                while (chosenEnemy.getHp() > 0 && p1.getHp() > 0) {
                    // Damage from enemy to player.
                    int damageFromEnemy = chosenEnemy.getDamage();
                    p1.dealDamage(damageFromEnemy);
                    System.out.println("Enemy dealt " + damageFromEnemy + " hp damage. " + p1.getName() + ", your hp is now at " + p1.getHp());

                    // Give them choices again.
                    System.out.println("What do you want to do?\n" +
                            "Attack or Skip or Exit");
                    String option = sc.nextLine();
                    // If they want to attack..
                    if (option.equalsIgnoreCase("attack")) {
                        // If hp is already less than 0, then break from while loop.
                        if(p1.getHp() <= 0) {
                            break;
                        }
                        // Damage from player to enemy.
                        int damageFromPlayer = p1.getDamage();
                        chosenEnemy.dealDamage(damageFromPlayer);
                        System.out.println("You dealt " + damageFromPlayer + " hp damage to enemy. Enemy's hp is now at " + chosenEnemy.getHp());
                    }
                    // If they want to exit, just exit the program.
                    else if (option.equalsIgnoreCase("exit")) {
                        System.out.println("Exiting..");
                        System.exit(0);
                    }
                }

                // If the enemy's hp is less than 0, then you won the battle (remove the enemy too).
                if (chosenEnemy.getHp() <= 0) {
                    System.out.println("Yay, you won the battle. Your health has been restored.");
                    enemies.remove(choice);
                    p1.restoreHealth();
                }
                // If not, then you probably died, lost.
                else {
                    System.out.println("You lost the battle and you're dead, unfortunate.");
                    System.exit(0);
                }
            }
        }
    }
}