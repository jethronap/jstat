# Monte Carlo Simulation for the Monty Hall Problem

## Contents
 * [Overview](#overview) 
 * [Import files](#include_files)
 * [The main function](#m_func)
 * [Results](#results)
 * [Source Code](#source_code)

## <a name="overview"></a> Overview

## <a name="include_files"></a> Import files

 ```
package examples.mc.example1;
import java.util.Random;
 ```

## <a name="m_func"></a> The main function

```
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


```

## <a name="results"></a> Results

```
...
You chose: B Winner door: B
You chose: A Winner door: C
You chose: A Winner door: C
You chose: B Winner door: C
You chose: C Winner door: B
You chose: A Winner door: B
You chose: A Winner door: A
You chose: A Winner door: A
You chose: C Winner door: B
You chose: C Winner door: B
You chose: C Winner door: C
You chose: C Winner door: B
You chose: A Winner door: A
You chose: A Winner door: B
You chose: B Winner door: B
You chose: A Winner door: B
You chose: C Winner door: A
You chose: C Winner door: C
You chose: C Winner door: B
Wins with original choice: 313
Wins with change choice: 687
Probability of winning with initial guess: 0.313
Probability of winning with change guess: 0.687
```

 ## <a name="source_code"></a> Source Code
 
 <a href="Example1.java">Example1.java</a>