import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Player player1 = new Player();
        Player player2 = new Player();

        boolean checkFailed = false;

        String[] playerName = {"", ""};
        String[] roles =  {"Knight", "Samurai", "Wizard", "Thief"};
        int role = 0;

        Scanner scan = new Scanner(System.in);

        Random rand = new Random();



        System.out.println("Welcome To The Battle Coliseum!");

        for(int j = 0; j < 2; j++)
        {


            do {
                try {
                    checkFailed = false;
                    System.out.println("Enter Player " + (j + 1) + " Name: ");

                    playerName[j] = scan.nextLine();
                    if (j == 1) {
                        if (Objects.equals(playerName[0], playerName[1])) {
                            throw new Exception();
                        }
                    }
                } catch (Exception e)
                {
                    System.out.println("\n\nPlayer Names Cannot Match. Please try again.\n\n");
                    checkFailed = true;
                }
            } while (checkFailed);

            do{
                try {
                    checkFailed = false;


                    System.out.println("Select Role: ");
                    System.out.println("0 - Random");

                    for (int i = 0; i < roles.length; i++) {
                        System.out.println(i + 1 + " - " + roles[i]);
                    }

                    role = scan.nextInt();
                    if (role < 0 || role > 4) { throw new Exception(); }

                } catch(Exception e){
                    System.out.println("\n\nInvalid Choice. Please try again.\n\n");
                    checkFailed = true;
                    scan.nextLine();
                }
            } while(checkFailed);



            if (role == 0) {role = rand.nextInt(3) + 1;}

            if (j == 0)  { player1.setPlayerInfo(playerName[j], roles[role - 1]); }
            else  { player2.setPlayerInfo(playerName[j], roles[role - 1]); }

            scan.nextLine();
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        }

        System.out.println("BEGIN BATTLE!");

        // BATTLING
        do
        {
            // PLAYERS CHOOSE ATTACK

            if (player1.getSpeed() > player2.getSpeed()){
                System.out.println("It is " + player1.getPlayerName() + "'s turn.\n");

            }
        } while (player1.isAlive() && player2.isAlive());
    }
}