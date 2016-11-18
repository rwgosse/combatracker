package com.myapp.combattracker.Helpers;

/**
 * Created by Richard on 2016-11-09.
 */

public final class PlayerHelper {


    public static int roll(int d, int n) {
        int count = 0;
        int sum = 0;

        while (count < d) {
            sum += (int) (Math.random() * n + 1);
            count++;
        }
        System.out.println("diceroll: " + d + "d" + n + " = " + sum);
        return sum;
    }

    public static int generateStat() {
        int roll1 = roll(1, 6);
        int roll2 = roll(1, 6);
        int roll3 = roll(1, 6);
        int roll4 = roll(1, 6);

        int sum = roll1 + roll2 + roll3 + roll4;
        //sum = sum - Math.min(roll1, Math.min(roll2, Math.min(roll3, roll4)));
        //sum = sum - Math.min(Math.min(Math.min(roll1, roll2),roll3), roll4);

        int smallest = roll1;
        if (smallest > roll2) smallest = roll2;
        if (smallest > roll3) smallest = roll3;
        if (smallest > roll4) smallest = roll4;

        sum = sum - smallest;




        return sum;


    }


    public static int getModifier(int stat) {
        int i = stat - 10;
        i = i / 2;
        return (int) Math.floor(i);

    }


}
