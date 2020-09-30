package cs301.Soccer;

import android.util.Log;
import cs301.Soccer.soccerPlayer.SoccerPlayer;

import java.io.EOFException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Soccer player database -- presently, all dummied up
 *
 * @author *** put your name here ***
 * @version *** put date of completion here ***
 *
 */
public class SoccerDatabase implements SoccerDB {

    private Hashtable database = new Hashtable();

    /**
     * add a player
     *
     * @see SoccerDB#addPlayer(String, String, int, String)
     */
    @Override
    public boolean addPlayer(String firstName, String lastName,
                             int uniformNumber, String teamName) {
        //Check if in database
        String keyName = firstName + "##" + lastName;
        if (database.containsKey(keyName)){
            return false;
        }

        //add player to dtabase
        SoccerPlayer player =  new SoccerPlayer(firstName, lastName, uniformNumber, teamName);
        database.put(keyName, player);
        return true;
    }

    /**
     * remove a player
     *
     * @see SoccerDB#removePlayer(String, String)
     */
    @Override
    public boolean removePlayer(String firstName, String lastName) {
        String keyName = firstName + "##" + lastName;
        if (!database.containsKey(keyName))
        {
            return false;
        }
        database.remove(keyName);

        return true;
    }

    /**
     * look up a player
     *
     * @see SoccerDB#getPlayer(String, String)
     */
    @Override
    public SoccerPlayer getPlayer(String firstName, String lastName) {
        //create key to look up in hash
        String keyName = firstName + "##" + lastName;
        if (database.containsKey(keyName))
        {
            return (SoccerPlayer) database.get(keyName);
        }

        //if the player doesn't exist, just returns null
        return null;
    }

    /**
     * increment a player's goals
     *
     * @see SoccerDB#bumpGoals(String, String)
     */
    @Override
    public boolean bumpGoals(String firstName, String lastName) {
        String keyName = firstName + "##" + lastName;
        if (!database.containsKey(keyName))
        {
            return false;
        }
        SoccerPlayer temp = (SoccerPlayer) database.get(keyName);
        temp.bumpGoals();
        return true;
    }

    /**
     * increment a player's assists
     *
     * @see SoccerDB#bumpAssists(String, String)
     */
    @Override
    public boolean bumpAssists(String firstName, String lastName) {
        String keyName = firstName + "##" + lastName;
        if (!database.containsKey(keyName))
        {
            return false;
        }
        SoccerPlayer temp = (SoccerPlayer) database.get(keyName);
        temp.bumpAssists();
        return true;
    }

    /**
     * increment a player's shots
     *
     * @see SoccerDB#bumpShots(String, String)
     */
    @Override
    public boolean bumpShots(String firstName, String lastName) {
        String keyName = firstName + "##" + lastName;
        if (!database.containsKey(keyName))
        {
            return false;
        }
        SoccerPlayer temp = (SoccerPlayer) database.get(keyName);
        temp.bumpShots();
        return true;
    }

    /**
     * increment a player's saves
     *
     * @see SoccerDB#bumpSaves(String, String)
     */
    @Override
    public boolean bumpSaves(String firstName, String lastName) {
        String keyName = firstName + "##" + lastName;
        if (!database.containsKey(keyName))
        {
            return false;
        }
        SoccerPlayer temp = (SoccerPlayer) database.get(keyName);
        temp.bumpSaves();
        return true;
    }

    /**
     * increment a player's fouls
     *
     * @see SoccerDB#bumpFouls(String, String)
     */
    @Override
    public boolean bumpFouls(String firstName, String lastName) {
        String keyName = firstName + "##" + lastName;
        if (!database.containsKey(keyName))
        {
            return false;
        }
        SoccerPlayer temp = (SoccerPlayer) database.get(keyName);
        temp.bumpFouls();
        return true;
    }

    /**
     * increment a player's yellow cards
     *
     * @see SoccerDB#bumpYellowCards(String, String)
     */
    @Override
    public boolean bumpYellowCards(String firstName, String lastName) {
        String keyName = firstName + "##" + lastName;
        if (!database.containsKey(keyName))
        {
            return false;
        }
        SoccerPlayer temp = (SoccerPlayer) database.get(keyName);
        temp.bumpYellowCards();
        return true;
    }

    /**
     * increment a player's red cards
     *
     * @see SoccerDB#bumpRedCards(String, String)
     */
    @Override
    public boolean bumpRedCards(String firstName, String lastName) {
        String keyName = firstName + "##" + lastName;
        if (!database.containsKey(keyName))
        {
            return false;
        }
        SoccerPlayer temp = (SoccerPlayer) database.get(keyName);
        temp.bumpRedCards();
        return true;
    }

    /**
     * tells the number of players on a given team
     *
     * @see SoccerDB#numPlayers(String)
     */
    @Override
    // report number of players on a given team (or all players, if null)
    public int numPlayers(String teamName) {
        if (teamName==null)
        {
            return database.size();
        }
        else
        {
            int temp = 0;
            Enumeration keys = database.keys();
            while (keys.hasMoreElements())
            {
                String tempKey = (String) keys.nextElement();
                SoccerPlayer tempPlayer = (SoccerPlayer) database.get(tempKey);
                if (tempPlayer.getTeamName().equals(teamName))
                {
                    temp++;
                }
            }
            return temp;
        }
    }

    /**
     * gives the nth player on a the given team
     *
     * @see SoccerDB#playerNum(int, String)
     */
    // get the nTH player
    @Override
    public SoccerPlayer playerNum(int idx, String teamName) {
        int numberPlayers = numPlayers(teamName);
        if (idx >= numberPlayers)
        {
            return null;
        }

        Enumeration keys = database.keys();
        for (int i = 0; i <= idx; i++){
            if (i == idx){
                String tempKey = (String) keys.nextElement();
                return (SoccerPlayer) database.get(tempKey);
            }
            keys.nextElement();
        }

        return null;
    }

    /**
     * reads database data from a file
     *
     * @see SoccerDB#readData(java.io.File)
     */
    // read data from file
    @Override
    public boolean readData(File file) {
        Scanner s = null;
        try {
            s = new Scanner(file);
        } catch (Exception e) {
            Log.e("File reading", "reading failed");
            return false;
        }

        while(s.hasNext())
        {
            String fn = s.nextLine();
            String ln = s.nextLine();
            int un =  Integer.parseInt(s.nextLine());
            String tn = s.nextLine();
            SoccerPlayer tempPlayer = new SoccerPlayer(fn,ln,un,tn);
            for (int i=0; i<Integer.parseInt(s.nextLine());i++)
            {
                tempPlayer.bumpGoals();
            }
            for (int i=0; i<Integer.parseInt(s.nextLine());i++)
            {
                tempPlayer.bumpAssists();
            }
            for (int i=0; i<Integer.parseInt(s.nextLine());i++)
            {
                tempPlayer.bumpShots();
            }
            for (int i=0; i<Integer.parseInt(s.nextLine());i++)
            {
                tempPlayer.bumpFouls();
            }
            for (int i=0; i<Integer.parseInt(s.nextLine());i++)
            {
                tempPlayer.bumpSaves();
            }
            for (int i=0; i<Integer.parseInt(s.nextLine());i++)
            {
                tempPlayer.bumpYellowCards();
            }
            for (int i=0; i<Integer.parseInt(s.nextLine());i++)
            {
                tempPlayer.bumpRedCards();
            }
            String keyname = fn+"##"+ln;
            
            if (database.containsKey(keyname)) {
                database.remove(keyname);
            }
            database.put(keyname,tempPlayer);
        }

        /**
        boolean EOF = false;
        while (!EOF) {
            for (int i = 0; i < 11; i++) {
                try {




                } catch (EOFException e) {
                    Log.e("EOF", "Reached End of File");
                    EOF = true;
                    break;
                } catch (Exception e) {
                    Log.e("Fail", "Reading failed");
                    return false;
                }
            }
        }
         */






        return file.exists();
    }

    /**
     * write database data to a file
     *
     * @see SoccerDB#writeData(java.io.File)
     */
    // write data to file
    @Override
    public boolean writeData(File file) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
        } catch (Exception e) {
            Log.e("File writing", "writing failed");
            return false;
        }


        Enumeration keys = database.keys();

        for (int i = 0; i < numPlayers(null); i++){
            String tempKey = (String) keys.nextElement();
            SoccerPlayer tempPlayer = (SoccerPlayer) database.get(tempKey);
            try {
                writer.write(logString(tempPlayer.getFirstName()+"\n"));
                writer.write(logString(tempPlayer.getLastName()+"\n"));
                writer.write(logString(tempPlayer.getUniform()+"\n"));
                writer.write(logString(tempPlayer.getTeamName()+"\n"));
                writer.write(logString(tempPlayer.getGoals()+"\n"));
                writer.write(logString(tempPlayer.getAssists()+"\n"));
                writer.write(logString(tempPlayer.getShots()+"\n"));
                writer.write(logString(tempPlayer.getFouls()+"\n"));
                writer.write(logString(tempPlayer.getSaves()+"\n"));
                writer.write(logString(tempPlayer.getYellowCards()+"\n"));
                writer.write(logString(tempPlayer.getRedCards()+"\n"));
            }
            catch(Exception e){
                Log.e("Tile writing","Writing failed");
                return false;
            }

        }

        return true;

    }

    /**
     * helper method that logcat-logs a string, and then returns the string.
     * @param s the string to log
     * @return the string s, unchanged
     */
    private String logString(String s) {
        Log.i("write string", s);
        return s;
    }

    /**
     * returns the list of team names in the database
     *
     * @see cs301.Soccer.SoccerDB#getTeams()
     */
    // return list of teams
    @Override
    public HashSet<String> getTeams() {
        return new HashSet<String>();
    }

}
