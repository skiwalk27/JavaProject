public class player {
  private int hp = 0;  //Health pool
  private int sp = 0;  //Stamina pool
  private int dp = 0;  //Defense pool (Damage mitigated on hit)
  private String name = "";
  private String role = "";
  
  private class roleElements {
    
    private class item {
      private String itemName;
      private int dmg;
      private int stamina;
      private int hitChance;
      private String effect; //Stuns opponent, hurts castor, increases defense, etc.
    }
    
    public roleElements(String role)
    {
      private String[] playerItems = {"", "", "", ""};
      
      if (role == "knight")
      {
        playerItems[0] = "Sword";  //Deals physical damage
        playerItems[1] = "Shield";  //Could either block incoming damage or be used to parry if opponent uses physical attack
        playerItems[2] = "Blessing";  //Heals hp
        playerItems[3] = "Warrior Cry";  //Increases dmg next turn, very small chance to stun
      } //else if for all other roles

      
    }     
  }
  
  public player()
  {
    hp = 100;
    sp = 100;
    dp = 100;
    name = "Player";
    role = "knight";
    
    roleElements(role);
  }

  public player(String n, String r)
  {
    hp = 100;
    sp = 100;
    dp = 100;
    name = n;
    role = r;
    
    roleElements(role);
  }
}
