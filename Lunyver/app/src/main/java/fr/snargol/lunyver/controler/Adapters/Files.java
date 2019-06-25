package fr.snargol.lunyver.controler.Adapters;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import fr.snargol.lunyver.model.Enums.Class;
import fr.snargol.lunyver.model.Enums.Race;
import fr.snargol.lunyver.model.Player;

public class Files extends AppCompatActivity {

    public Files(ArrayList<Player> player, String file) {

        try {
            saveDatas(player, file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            player = loadDatas(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Toast.makeText(getApplicationContext(), player.get(0).get_name(), Toast.LENGTH_LONG).show();

    }

    public void createFileIfNotExist(String file_name) {
        File file = new File(file_name + ".txt");
        try {
            file.createNewFile(); // if file already exists will do nothing
            String test = ""+getFilesDir();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Player> loadDatas(String file_name) throws IOException {
        FileInputStream file = null;
        ArrayList<Player> playerList = new ArrayList<Player>();

        try {
            file = openFileInput(file_name);
            InputStreamReader isr = new InputStreamReader(file);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            String text;
            ArrayList<String> stringList = new ArrayList<>();
            StringBuilder word = new StringBuilder();

            try {
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            text = sb.toString();

            int i = 0;
            int y = 0;
            int z = 0;

            while(y < text.length()) {
                while (text.charAt(y) != '/') {
                    while (text.charAt(y) != '|') {
                        word.append(text.charAt(y));
                        y++;
                    }
                    stringList.add(String.valueOf(word));
                    word.setLength(0);
                    y++;
                }
                y ++;
            }

            while(z < stringList.size()){
                String str = stringList.get(z);
                Race race = Race.valueOf(stringList.get(z+1));
                Class _class = Class.valueOf(stringList.get(z+2));
                int attack = Integer.parseInt(stringList.get(z+3));
                int defense = Integer.parseInt(stringList.get(z+4));
                int life = Integer.parseInt(stringList.get(z+5));
                int level = Integer.parseInt(stringList.get(z+6));
                int money = Integer.parseInt(stringList.get(z+7));
                Boolean isAlive = Boolean.parseBoolean(stringList.get(z+8));
                playerList.add(new Player(str,race,_class,attack,defense,life,level,money,isAlive));
                z += 9;
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            assert file != null;
            file.close();
        }

        return playerList;
    }

    public void saveDatas(ArrayList<Player> player_list, String file_name) throws FileNotFoundException {
        FileOutputStream file = null;


        file = openFileOutput(file_name, MODE_PRIVATE);

        for (Player player: player_list) {
            try {
                String string = player.get_name() + "|" + player.get_race() + "|" +
                        player.get_class() + "|" + player.get_attack() + "|" + player.get_defense() + "|" +
                        player.get_life() + "|" + player.get_level() + "|" + player.get_contributed_money() + "|" +
                        player.get_isAlive()+"|/";
                file.write(string.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        Toast.makeText(getApplicationContext(), "Saved to "+ getFilesDir() + "/" + file_name, Toast.LENGTH_LONG).show();
    }

}
