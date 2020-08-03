public class Enemy {
    private int strength;
    private int dexterity;
    private int constitution;
    private int hp;

    // Math.random comprised in some neat lil box :)
    // Which is a constructor.
    public Enemy(){
        this.strength += 8 + (int) ((Math.random() * 5 + 1));
        this.dexterity += 2 + (int) ((Math.random() * 5 + 1));
        this.constitution += 4 + (int) ((Math.random() * 5 + 1));
        this.hp = constitution * 2;
    }

    /**
     * Gets damage for the enemy, wherein no correct selection brings 0.
     * @return Error... Right?
     */
    public int getDamage() {
        // Array containing classes
        String[] classes = {"barbarian", "fighter", "rogue"};
        // Generates random class here
        String className = classes[(int) (Math.random() * 2)];
        // Switch case for every class
        return switch (className) {
            case "barbarian" -> (int) (2 * (Math.random() * 5 + 1)) + strength / 2;
            case "fighter" -> (int) (2 * (Math.random() * 10 + 1)) + strength / 2;
            case "rogue" -> (int) (2 * (Math.random() * 5 + 1)) + dexterity / 2;
            default -> 0;
        };
    }

    // Getters, gets the stuff. No set 'cause it don't need to change.
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

}