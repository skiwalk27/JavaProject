import java.util.Scanner;
import java.lang.Math;

public class Player {
    private int maxhp = 0;  //Health pool
    private int defaultsp = 0;  //Stamina pool
    private int defaultdp = 0;  //Defense pool (Damage mitigated on hit)
    private int defaultSpeed = 0;

    private int hp = 0;
    private int sp = 0;
    private int dp = 0;
    private int speed = 0;
    private int damageBuff = 0;

    private String name = "";


    private Item item1 = new Item();
    private Item item2 = new Item();
    private Item item3 = new Item();
    private Item item4 = new Item();

    private final Item[] allItems = { item1, item2, item3, item4 };

    private int itemUsed = 0;

    private boolean bleeding = false;
    private int bleedTimer = 0;

    private int defenseBoost = 0;
    private int speedBoost = 0;
    private int damageBoost = 0;

    private boolean attackMissed = false;
    private boolean attackWillHit = false;

    public void setPlayerInfo(String n, String r){
        name = n;

        switch(r){
            case "Knight":
                hp = 100;
                defaultsp = 100;
                defaultdp = 50;
                defaultSpeed = 60;
                break;
            case "Samurai":
                hp = 80;
                defaultsp = 150;
                defaultdp = 10;
                defaultSpeed = 100;
                break;
            case "Wizard":
                hp = 100;
                defaultsp = 100;
                defaultdp = 20;
                defaultSpeed = 70;
                break;
            case "Thief":
                hp = 50;
                defaultsp = 100;
                defaultdp = 30;
                defaultSpeed = 90;
                break;
        }

        maxhp = hp + 20;
        sp = defaultsp;
        dp = defaultdp;
        speed = defaultSpeed;

        for (int i = 0; i < 4; i++){
            allItems[i].initializeItem(r, i+1);
        }

    }

    public boolean isAlive() {
        return hp > 0;
    }

    public int getSpeed() { return speed; }

    public String getItem(int i)
    {
        switch(i){
            case 1:
                return item1.getItemName();
            case 2:
                return item2.getItemName();
            case 3:
                return item3.getItemName();
            case 4:
                return item4.getItemName();
            default:
                return "";

        }
    }

    public boolean getAttackMissed() { return attackMissed; }

    public void setAttackMissed(boolean b) { attackMissed = b; }

    public boolean getAttackWillHit() {return attackWillHit; }

    public void setAttackWillHit(boolean b) { attackWillHit = b; }

    public int getDamageBuff() { return damageBuff; }

    public boolean avoidsPhysicalItem()
    {
        return allItems[itemUsed-1].avoidsPhysical();
    }

    public int avoidPhysicalChance()
    {
        return allItems[itemUsed-1].getProcChance();
    }

    public void printStats(){
        System.out.println("HP: " + hp);
        System.out.println("Stamina: " + sp);
        System.out.println("Defense: " + dp);
        System.out.println("Speed: " + speed);
    }

    public void chooseAttack(){
        boolean checkFailed = false;
        Scanner scan = new Scanner(System.in);

        System.out.println("\nIt is " + this.name + "'s turn.\n");

        System.out.println("Current Stats:");

        this.printStats();

        do{
            try {
                System.out.println("\nWhat Item would you like to use?");

                for (int i = 0; i < 4; i++) {
                    System.out.println(i + 1 + " - " + this.getItem(i + 1));
                }

                itemUsed = scan.nextInt();

                if (itemUsed < 0 || itemUsed > 4) { throw new Exception(); }

            }catch (Exception e){
                System.out.println("\n\nInvalid Choice. Please try again.\n\n");
                checkFailed = true;
                scan.nextLine();
            }
        } while (checkFailed);

        if (allItems[itemUsed-1].avoidsPhysical()) { speed = 1; }

        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    public void attack(Player p)
    {
        System.out.println("\n" + name + " used " + allItems[itemUsed-1].getItemName() + "!");

        allItems[itemUsed-1].useItem(this, p);
    }

    public void changeStats(int hp, int sp, int dp, int speed, boolean bleed, boolean negateBleed, String spellType, int dmgBuff){
        this.hp += (hp - (hp / this.dp));
        this.sp += sp;
        this.dp += dp;
        this.speed += speed;

        if (!bleeding && bleed)
            bleeding = true;
        if (!spellType.isEmpty()) { System.out.println(spellType); }
        if (negateBleed) bleeding = false;

        if (dmgBuff > 0 && this.damageBoost == 0)
        {
            damageBoost = 1;
            this.damageBuff += dmgBuff;
            System.out.println(name + " increased their damage for next round!");
        }

        if (hp < 0)
        {
            System.out.println(name + " took "  + Math.abs(hp) + " damage. (" + this.hp + "hp left)");
        }
        else if (hp > 0)
        {
            if (this.hp > this.maxhp)
                this.hp = this.maxhp;
            else
                System.out.println(name + " replenished " + hp + "hp. (" + this.hp + "hp left)");
        }

        if (sp > 0) { System.out.println(name + " replenished " + sp + " stamina. (" + this.sp + "sp left)"); }

        if (dp > 0 && defenseBoost == 0)
        {
            defenseBoost = 1;
            System.out.println(name + " increased their defense by " + dp + "!");
        }

        if (speed > 0 && speedBoost == 0) {
            speedBoost = 1;
            System.out.println(name + " increased their speed by " + speed + "!");
        }
    }

    public void changeStats(int sp){
        this.changeStats(0,sp, 0, 0, false, false, "", 0);
    }

    public void newTurn()
    {
        attackMissed = false;
        attackWillHit = false;

        if (bleeding){
            if (bleedTimer == 0){ bleedTimer = 3; }

            bleedTimer--;

            System.out.println(name + " is bleeding and lost 10HP.  Mends in " + bleedTimer + " turns.");

            this.hp -= 10;

            if (bleedTimer == 0)
            {
                bleeding = false;
                System.out.println(name + " is no longer bleeding.");
            }

            if (hp <= 0) hp = 1;
        }

        if (speedBoost > 0) { speedBoost--; }
        else { speed = defaultSpeed; }

        if (defenseBoost > 0) { defenseBoost--; }
        else { dp = defaultdp; }

        if (damageBoost > 0) { damageBoost--; }
        else { damageBuff = 0; }


    }

}