import java.util.Random;

public class Item{
    private String itemName;
    private int damage = 0;

    private boolean physical = true;

    private int critChance = 0;
    private int bleedChance = 0;

    private int procChance = 100;

    private int heal = 0;
    private int defenseIncrease = 0;
    private int speedincrease = 0;
    private int staminaIncrease = 0;
    private boolean negateBleed = false;
    private int damageIncrease = 0;

    private int staminaUse = 0;

    private int selfDamage = 0;
    private boolean stolen = false;
    private boolean avoidPhysical = false;

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
                        this.avoidPhysical = true;
                        this.physical = false;
                        break;
                    case 3:
                        this.itemName = "King's Potion";
                        this.heal = 30;
                        this.speedincrease = 20;
                        this.staminaIncrease = 50;
                        this.negateBleed = true;
                        this.physical = false;
                        break;
                    case 4:
                        this.itemName = "Warrior's Cry";
                        this.damageIncrease = 20;
                        this.physical = false;
                        break;
                }
                break;
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
                        this.heal = 25;
                        this.staminaUse = 20;
                        this.procChance = 80;
                        this.avoidPhysical = true;
                        this.physical = false;
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
                        this.physical = false;
                        break;
                }
                break;
            case "Wizard":
                switch (itemNum){
                    case 1:
                        this.itemName = "Staff";
                        this.damage = 20;
                        this.staminaIncrease = 10;
                        break;
                    case 2:
                        this.itemName = "Protection Spell";
                        this.defenseIncrease = 50;
                        this.staminaUse = 30;
                        this.negateBleed = true;
                        break;
                    case 3:
                        this.itemName = "Spell book";
                        this.procChance = 50;
                        this.staminaUse = 30;
                        break;
                    case 4:
                        this.itemName = "Life steal";
                        this.speedincrease = 30;
                        this.staminaUse = 50;
                        break;
                }
                break;

            case "Thief":
                //TODO Code thief items

        }
    }

    public String getItemName(){
        if (stolen)
            return "Item has been stolen and cannot be used.";
        return itemName;
    }

    public boolean avoidsPhysical()
    {
        return avoidPhysical;
    }

    public int getProcChance()
    {
        return procChance;
    }

    public void useItem(Player user, Player receiver)
    {
        int userDamage = 0; //Used for self healing/damage
        int userStamina = 0; //Used for stamina drain/gain

        double critValue = 1;

        String spellType = "";

        boolean bleed = false;

        Random rand = new Random();


        if (itemName.equals("Spell Book")){

            int temp = 0;

            this.selfDamage = 0;
            this.damage = 0;

            temp = rand.nextInt(99)+1;

            if (temp < 26)
            {
                this.selfDamage = 25;
                spellType = "The wizard's spell was misspoken...";
            }
            else if (temp < 76)
            {
                this.damage = 30;
                spellType = "The wizard cast fireball!";
            }
            else
            {
                this.damage = rand.nextInt(69)+1;
                spellType = "The wizard cast lightning!";
            }
        }

        if (receiver.avoidsPhysicalItem() && physical)
        {
            if (rng(receiver.avoidPhysicalChance()))
            {
                System.out.println(itemName + " was avoided!");
                user.changeStats(-staminaUse);
                receiver.setAttackWillHit(true);
                return;
            }
            else
            {
                System.out.println(itemName + " was not avoided!");
                user.setAttackMissed(false);
                receiver.setAttackMissed(true);
            }
        }
        else if (receiver.avoidsPhysicalItem() && !physical){
            receiver.setAttackMissed(true);
        }

        if ((this.procChance < 100 || user.getAttackMissed()) && !user.getAttackWillHit())
        {
            if (!rng(this.procChance) || user.getAttackMissed())
            {
                System.out.println(itemName + " Missed!");
                user.changeStats(-staminaUse);
                return;
            }
        }
        if (critChance > 0)
        {
            if (rng(critChance)){
                System.out.println("Critical Hit!");
                critValue = 1.5;
            }
        }

        if (bleedChance > 0) {

            if (rng(bleedChance)) {
                System.out.println(itemName + " inflicted Bleed!");
                bleed = true;
            }
        }

        if (selfDamage > 0) {userDamage = selfDamage;}
        else if (heal > 0) {userDamage = heal;}

        if (staminaUse > 0) {userStamina -= staminaUse;}
        if (staminaIncrease > 0) {userStamina = staminaIncrease;}

        user.changeStats(userDamage, userStamina, defenseIncrease, speedincrease, false, negateBleed, "", damageIncrease);
        receiver.changeStats((int) (-damage * critValue - (damage > 0 ? user.getDamageBuff() : 0)), 0, 0, 0, bleed, false, spellType, 0);
    }

    private boolean rng(int chance){
        Random rand = new Random();

        if (chance == 100) return true;

        return chance <= rand.nextInt(100);
    }
}
