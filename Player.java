public class Player {
    private int hp = 0;  //Health pool
    private int sp = 0;  //Stamina pool
    private int dp = 0;  //Defense pool (Damage mitigated on hit)
    private int speed = 0;
    private String name = "";
    private String role = "";

    private Item item1 = new Item();
    private Item item2 = new Item();
    private Item item3 = new Item();
    private Item item4 = new Item();

    public void setPlayerInfo(String n, String r){
        name = n;
        role = r;

        switch(r){
            case "Knight":
                hp = 100;
                sp = 100;
                dp = 50;
                speed = 60;
                break;
            case "Samurai":
                hp = 80;
                sp = 150;
                dp = 10;
                speed = 100;
                break;
            case "Wizard":
                hp = 100;
                sp = 100;
                dp = 20;
                speed = 70;
                break;
            case "Thief":
                hp = 50;
                sp = 100;
                dp = 30;
                speed = 90;
                break;
        }
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public String getPlayerName() { return name; }

    public int getSpeed() { return speed; }

    public void displayPlayerInfo()
    {
        System.out.println("Name: " + name);

    }
}