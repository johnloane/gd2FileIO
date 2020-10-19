package com.dkit.gd2.johnloane;

import java.io.*;
import java.util.*;

public class Locations implements Map<Integer, Location>
{
    private static Map<Integer, Location> locations = new HashMap<Integer, Location>();

    public static void main (String[] args) throws IOException
    {
        //FileWriter locationFile = null;
        //Let's redo this using try with resources
        //Edit the code below to also write out the exits to a file
        //called directions.txt -> 1, W, 2, 1, E, 3, 1, N, 5, 1, S, 4
        try(BufferedWriter locationFile = new BufferedWriter(new FileWriter("locations.txt"));
            BufferedWriter directionsFile = new BufferedWriter(new FileWriter("directions.txt")))
        {
            for(Location location: locations.values())
            {
                locationFile.write(location.getLocationID()+ ", " + location.getDescription() + "\n");
                for(String direction : location.getExits().keySet())
                {
                    directionsFile.write(location.getLocationID() + ", " + direction + ", " + location.getExits().get(direction) + "\n");
                }
            }
        }
//        try
//        {
//            locationFile = new FileWriter("locations.txt");
//            for(Location location: locations.values())
//            {
//                locationFile.write(location.getLocationID() + ", " + location.getDescription() + "\n");
//            }
//
//        }
//        finally
//        {
//            if(locationFile != null)
//            {
//                locationFile.close();
//            }
//        }
//        FileWriter locationFile = null;
//        try
//        {
//            locationFile = new FileWriter("locations.txt");
//            //throw new IOException("This is a forced IOException");
//            System.out.println("After the FileWriter line");
//            for (Location location : locations.values())
//            {
//                locationFile.write(location.getLocationID() + ", " + location.getDescription() + "\n");
//            }
//        }
//        catch(IOException ioe)
//        {
//            System.out.println("Something went wrong reading locations.txt");
//            ioe.printStackTrace();
//        }
//        finally
//        {
//            System.out.println("In the finally block");
//            try
//            {
//                // LYBL don't try and close a null file
//                if(locationFile != null)
//                {
//                    locationFile.close();
//                }
//            }
//            catch(IOException ioe)
//            {
//                ioe.printStackTrace();
//            }
//        }
    }

    //static region gets called when the class is loaded

    static
    {
        /*
        Challenge to rewrite the code below to move from
        try, catch, finally to try with resources
        Hopefully you will notice that you write less code
        with try with resources
         */

        try(Scanner fileScanner=new Scanner(new BufferedReader(new FileReader("locations_big.txt"))))
        {
            while(fileScanner.hasNextLine())
            {
                //Read in and parse a line at a time
                String input = fileScanner.nextLine();
                String[] data = input.split(",");
                int location = Integer.parseInt(data[0]);
                String description = data[1];
                Map<String, Integer> tempExit = new HashMap<>();
                locations.put(location, new Location(location, description, tempExit));
            }
        }
        catch(FileNotFoundException fne)
        {
            System.out.println("Could not open the file");
        }

        //Now read the exits
        try(Scanner fileScanner=new Scanner(new BufferedReader(new FileReader("directions_big.txt"))))
        {
            String input;
            while(fileScanner.hasNextLine())
            {
                input = fileScanner.nextLine();
                String[] data = input.split(",");
                int loc = Integer.parseInt(data[0]);
                String direction = data[1];
                int destination = Integer.parseInt(data[2]);

                System.out.println(loc + ": " + direction + ": " + destination);
                Location location = locations.get(loc);
                location.addExit(direction, destination);
            }
        }
        catch(FileNotFoundException fne)
        {
            System.out.println("Problem opening directions.txt");
        }

//        Scanner fileScanner = null;
//        try
//        {
//            fileScanner = new Scanner(new FileReader("locations_big.txt"));
//            fileScanner.useDelimiter(",");
//            while(fileScanner.hasNextLine())
//            {
//                int location = fileScanner.nextInt();
//                fileScanner.skip(fileScanner.delimiter());
//                String description = fileScanner.nextLine();
//                System.out.println("Imported location: " + location + ": " + description);
//                Map<String, Integer> tempExit = new HashMap<>();
//                locations.put(location, new Location(location, description, tempExit));
//            }
//        }
//        catch(FileNotFoundException fne)
//        {
//            fne.printStackTrace();
//        }
//        finally
//        {
//            if(fileScanner != null)
//            {
//                fileScanner.close();
//            }
//        }

        //See can you read in the exits
        //Use try, catch, finally

//        try
//        {
//            fileScanner = new Scanner(new BufferedReader(new FileReader("directions_big.txt")));
//            fileScanner.useDelimiter(",");
//            while(fileScanner.hasNextLine())
//            {
//                int location = fileScanner.nextInt();
//                fileScanner.skip(fileScanner.delimiter());
//                String direction = fileScanner.next();
//                fileScanner.skip(fileScanner.delimiter());
//                String dest = fileScanner.nextLine();
//                int destination = Integer.parseInt(dest.trim());
//                String input = fileScanner.nextLine();
//                String[] data = input.split(",");
//                int location = Integer.parseInt(data[0]);
//                String direction = data[1].trim();
//                int destination = Integer.parseInt(data[2].trim());
//
//                System.out.println(location + ": " + direction + ": " + destination);
//                Location loc = locations.get(location);
//                loc.addExit(direction, destination);
//            }
//        }
//        catch(FileNotFoundException fne)
//        {
//            fne.printStackTrace();
//        }
//        finally
//        {
//            if(fileScanner != null)
//            {
//                fileScanner.close();
//            }
//        }

        // North -> 1
        // East -> 2
        // South -> 3
        // West -> 4
//        Map<String, Integer> tempExit = new HashMap<>();
//        locations.put(0, new Location(0, "Thank you for playing Zak's birthday game", null));
//
//        tempExit = new HashMap<String, Integer>();
//        tempExit.put("W", 2);
//        tempExit.put("E", 3);
//        tempExit.put("S", 4);
//        tempExit.put("N", 5);
//
//        locations.put(1, new Location(1, "The room is grey and bland. It s cold and freezing", tempExit));
//
//        //set up room 2
//        tempExit = new HashMap<String, Integer>();
//        tempExit.put("N", 5);
//        tempExit.put("E", 1);
//        locations.put(2, new Location(2, "The room has colourfull wallpaper, balloons and a happy birthday sign.", tempExit));
//
//        //Set up room 3
//        tempExit = new HashMap<String, Integer>();
//        tempExit.put("W", 1);
//        locations.put(3, new Location(3, "A stannis baratheon piniata", tempExit));
//
//        //Set up room 4
//        tempExit = new HashMap<String, Integer>();
//        tempExit.put("N", 1);
//        tempExit.put("W", 2);
//        locations.put(4, new Location(4, "It is a sunny garden", tempExit));
//
//        //Set up room 5
//        tempExit = new HashMap<String, Integer>();
//        tempExit.put("S", 1);
//        tempExit.put("W", 2);
//        locations.put(5, new Location(5, "There is a big birthday cake in the room. It is covered in 19 candles and says Happy Birthday Zak!!!", tempExit));
    }
    @Override
    public int size()
    {
        //Return how many locations there are in our game
        return locations.size();
    }

    @Override
    public boolean isEmpty()
    {
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key)
    {
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value)
    {
        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key)
    {
        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value)
    {
        return locations.put(key, value);
    }

    @Override
    public Location remove(Object key)
    {
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m)
    {

    }

    @Override
    public void clear()
    {
        locations.clear();
    }

    @Override
    public Set<Integer> keySet()
    {
        return locations.keySet();
    }

    @Override
    public Collection<Location> values()
    {
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet()
    {
        return locations.entrySet();
    }
}
