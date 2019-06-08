package fr.snargol.lunyver.controler;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;

import fr.snargol.lunyver.R;
import fr.snargol.lunyver.controler.Adapters.PlayerAdapter;
import fr.snargol.lunyver.model.Enums.Class;
import fr.snargol.lunyver.model.Enums.Race;
import fr.snargol.lunyver.model.Player;

public class PlayersActivity extends AppCompatActivity {

    final private String FILE_NAME = "datasLUNYVER";
    ArrayList<Player> player_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

//        player_list.add(new Player("Snargol", 14, 7, Class.THIEF, Race.WEREWOLF));
//        player_list.add(new Player("Euxiniar", 12, 6, Class.ARCHER, Race.FAIRY));
//        player_list.add(new Player("Maxou", 11, 9 , Class.BERSERK, Race.TROLL));
//        player_list.add(new Player("Zozo", 15, 7 , Class.FLASH, Race.CENTAUR));
//        player_list.add(new Player("Phelie", 16, 5 , Class.TRAINER, Race.GNOME));
//        player_list.add(new Player("Jere", 13, 8 , Class.PROTECTOR, Race.ELF));
//        player_list.add(new Player("Snargol", 14, 7, Class.THIEF, Race.WEREWOLF));

        try {
            player_list = loadDatas(FILE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ListView player_list_view = findViewById(R.id.list_view_player_list);

        String[] names = new String[] {player_list.get(0).get_name(), player_list.get(1).get_name(), player_list.get(2).get_name(), player_list.get(3).get_name(), player_list.get(4).get_name(), player_list.get(5).get_name()};

        PlayerAdapter playerAdapter = new PlayerAdapter(this, player_list, names);
        player_list_view.setAdapter(playerAdapter);

        player_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent playersEditActivity = new Intent(getApplicationContext(), PlayerEditActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("player_list", player_list);
                playersEditActivity.putExtras(bundle);
                playersEditActivity.putExtra("position", position);
                startActivity(playersEditActivity);
                finish();
            }
        });

//        Button buttonAddPlayer = (Button) findViewById(R.id.button_add_player);
//        buttonAddPlayer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    saveDatas(player_list, FILE_NAME);
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//            }
//        });

        Button buttondes = (Button) findViewById(R.id.button_des);
        buttondes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    player_list = loadDatas(FILE_NAME);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent activity = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(activity);
        finish();
    }


//    public void saveDatas(ArrayList<Player> player_list, String file_name) throws FileNotFoundException {
//        FileOutputStream file = null;
//
//
//        file = openFileOutput(file_name, MODE_PRIVATE);
//
//        for (Player player: player_list) {
//            try {
//                String string = player.get_name() + "|" + player.get_race() + "|" +
//                        player.get_class() + "|" + player.get_attack() + "|" + player.get_defense() + "|" +
//                        player.get_life() + "|" + player.get_level() + "|" + player.get_contributed_money() + "|" +
//                        player.get_isAlive()+"|/";
//                file.write(string.getBytes());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        Toast.makeText(getApplicationContext(), "Saved to "+ getFilesDir() + "/" + file_name, Toast.LENGTH_LONG).show();
//    }


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

    public ArrayList<Player> getPlayer_list() {
        return player_list;
    }

    public void setPlayer_list(ArrayList<Player> player_list) {
        this.player_list = player_list;
    }
}
