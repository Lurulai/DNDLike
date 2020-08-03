public class Player {
    private String className, Name, race;
    private int strength, dexterity, constitution, hp;

    // Constructor for Player
    public Player(String name, String race, String className) {
        this.Name = name;
        this.race = race;
        this.className = className;
        // Switch case for the races and their bonuses
        switch (race) {
            case "elf" -> {
                strength -= 2;
                dexterity += 2;
            }
            case "dwarf" -> {
                dexterity -= 2;
                constitution += 2;
            }
            case "orc" -> {
                strength += 2;
                dexterity -= 2;
            }
        }
        // Generator for random stats
        this.strength += 6 + (int) (2 * (Math.random() * 5 + 1));
        this.dexterity += 6 + (int) (2 * (Math.random() * 5 + 1));
        this.constitution += 6 + (int) (2 * (Math.random() * 5 + 1));
        this.hp = constitution * 2;
    }

    /**
     * Gets damage for each case, wherein case is the classes
     * @return Incorrect selection... Right?
     */
    public int getDamage() {
        return switch (className) {
            case "barbarian" -> (int) (2 * (Math.random() * 5 + 1)) + strength / 2;
            case "fighter" -> (int) (2 * (Math.random() * 11 + 1)) + strength / 2;
            case "rogue" -> (int) (2 * (Math.random() * 5 + 1)) + dexterity / 2;
            default -> 0;
        };
    }

    // Getters, gets the stuff. No set 'cause it don't need to change.
    public String getClassName() {
        return className;
    }

    public String getName() {
        return Name;
    }

    public String getRace() {
        return race;
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getConstitution() {
        return constitution;
    }

    public int getHp() {
        return hp;
    }

    public void dealDamage(int damage) {
        this.hp -= damage;
    }

    public void restoreHealth(){
        this.hp = 2*this.constitution;
    }
}





