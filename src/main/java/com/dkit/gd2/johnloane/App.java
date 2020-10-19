package com.dkit.gd2.johnloane;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    private static Locations locations = new Locations();
    public static void main( String[] args )
    {
        Scanner keyboard = new Scanner(System.in);

        Map<String, String> vocabulary = new HashMap<>();
        vocabulary.put("QUIT", "Q");
        vocabulary.put("WEST", "W");
        vocabulary.put("EAST", "E");
        vocabulary.put("NORTH", "N");
        vocabulary.put("SOUTH", "S");

        int loc = 1;

        while(true)
        {
            System.out.println(locations.get(loc).getDescription());

            if(loc == 0)
            {
                break;
            }

            Map<String, Integer> exits = locations.get(loc).getExits();
            System.out.println("Available exits are: ");
            for(String exit: exits.keySet())
            {
                System.out.println(exit + ", ");
            }
            System.out.println();

            String direction = keyboard.nextLine().toUpperCase();
            //go north
            //run north
            //move quickly north
            if(direction.length() > 1)
            {
                String[] words = direction.split(" ");
                for(String word: words)
                {
                    if(vocabulary.containsKey(word))
                    {
                        direction = vocabulary.get(word);
                        break;
                    }
                }
            }
            System.out.println(direction);
            if(direction.equals("Q"))
            {
                loc = 0;
            }
            else if (exits.containsKey(direction))
            {
                loc = exits.get(direction);
            }
            else
            {
                System.out.println("There is no exit in that direction");
            }
        }
    }
}
