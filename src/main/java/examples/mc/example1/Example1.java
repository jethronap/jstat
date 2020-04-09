package examples.mc.example1;
import java.util.Random;

public class Example1 {

    public static void main(String[] args){

        final int N_ITRS = 1000;
        final String[] doors = new String[3];
        doors[0] = "A";
        doors[1] = "B";
        doors[2] = "C";

        int first_choice_wins = 0;
        int change_wins = 0;

        Random rand = new Random();

        for(int itr=0; itr<N_ITRS; ++itr){

            int  winner_idx = rand.nextInt(doors.length);
            int pick_idx = rand.nextInt(doors.length);

            System.out.println("You chose: " + doors[pick_idx] + " Winner door: "
                    + doors[winner_idx]);

            if(winner_idx == pick_idx){
                first_choice_wins++;
            }
            else{
                change_wins++;
            }
        }

        System.out.println("Wins with original choice: " + first_choice_wins);
        System.out.println("Wins with change choice: " + change_wins);
        System.out.println("Probability of winning with initial guess: " + (double)(first_choice_wins)/N_ITRS);
        System.out.println("Probability of winning with change guess: "  + (double)(change_wins)/N_ITRS);


    }
}
