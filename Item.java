public class Item{
    private String itemName;
    private int damage = 0;

    private int critChance = 0;
    private int bleedChance = 0;

    private int procChance = 100;

    private int heal = 0;
    private int defenseIncrease = 0;
    private int speedincrease = 0;
    private int staminaIncrease = 0;
    private boolean negateBleed = false;
    private int damageIncrease = 0;
    private int dodgeChance = 0;

    private int staminaUse = 0;

    private int selfDamage = 0;

    public void initializeItem(String role, int itemNum)
    {
        switch(role){
            case "Knight":
                switch (itemNum){
                    case 1:
                        this.itemName = "Sword";
                        this.damage = 35;
                        this.staminaUse = 40;
                        this.critChance = 20;
                        break;
                    case 2:
                        this.itemName = "Parry";
                        this.procChance = 80;
                        this.damage = 20;
                        this.staminaUse = 20;
                        break;
                    case 3:
                        this.itemName = "King's Potion";
                        this.heal = 30;
                        this.speedincrease = 20;
                        this.staminaIncrease = 50;
                        this.negateBleed = true;
                        break;
                    case 4:
                        this.itemName = "Warrior's Cry";
                        this.damageIncrease = 20;
                        break;
                }
            case "Samurai":
                switch (itemNum){
                    case 1:
                        this.itemName = "Katana";
                        this.damage = 30;
                        this.staminaUse = 20;
                        this.bleedChance = 25;
                        break;
                    case 2:
                        this.itemName = "Quick Reflexes";
                        this.heal = 20;
                        this.staminaUse = 20;
                        this.dodgeChance = 75;
                        break;
                    case 3:
                        this.itemName = "Disarm";
                        this.procChance = 50;
                        this.staminaUse = 50;
                        this.damage = 20;
                        break;
                    case 4:
                        this.itemName = "Focus";
                        this.speedincrease = 30;
                        this.staminaIncrease = 50;
                        break;
                }
            case "Wizard":
                switch (itemNum){
                    case 1:
                        this.itemName = "Staff";
                        this.damage = 20;
                        this.staminaUse = 20;
                        break;
                    case 2:
                        this.itemName = "Protection Spell";
                        this.defenseIncrease = 50;
                        this.staminaUse = 30;
                        this.negateBleed = true;
                        break;
                    case 3:
                        this.itemName = "Spellbook";
                        this.procChance = 50;
                        this.damage = 20;
                        break;
                    case 4:
                        this.itemName = "Lifesteal";
                        this.speedincrease = 30;
                        this.staminaUse = 50;
                        break;
                }

            case "Thief":

        }

    }
}
