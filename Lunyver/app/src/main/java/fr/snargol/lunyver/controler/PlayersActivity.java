package fr.snargol.lunyver.controler;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

import fr.snargol.lunyver.R;
import fr.snargol.lunyver.controler.Adapters.Files;
import fr.snargol.lunyver.controler.Adapters.PlayerAdapter;
import fr.snargol.lunyver.model.Enums.Class;
import fr.snargol.lunyver.model.Enums.Race;
import fr.snargol.lunyver.model.Player;
import fr.snargol.lunyver.model.PopUpConfirm;

public class PlayersActivity extends AppCompatActivity {

    final private String FILE_NAME = "datasLUNYVER";
    ArrayList<Player> player_list = new ArrayList<>();
    Activity activity;
    ListView player_list_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);
        setActivity(this);

//        player_list.add(new Player("Snargol", 14, 7, Class.THIEF, Race.WEREWOLF));
//        player_list.add(new Player("Euxiniar", 12, 6, Class.ARCHER, Race.FAIRY));
//        player_list.add(new Player("Maxou", 11, 9 , Class.BERSERK, Race.TROLL));
//        player_list.add(new Player("Zozo", 15, 7 , Class.FLASH, Race.CENTAUR));
//        player_list.add(new Player("Phelie", 16, 5 , Class.TRAINER, Race.GNOME));
//        player_list.add(new Player("Jere", 13, 8 , Class.PROTECTOR, Race.ELF));
//
//        try {
//            saveData(getPlayer_list(), FILE_NAME);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

        player_list = loadData(FILE_NAME);


        setPlayer_list_view((ListView) findViewById(R.id.list_view_player_list));

        PlayerAdapter playerAdapter = new PlayerAdapter(this, player_list, getNames());
        player_list_view.setAdapter(playerAdapter);

        player_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goToEditPlayer(position);
            }
        });

        player_list_view.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final PopUpConfirm popUp = new PopUpConfirm(getActivity());
                popUp.setTitle("Voulez vous supprimer le personnage " + getPlayer_list().get(position).get_name() + " ?");
                popUp.getButtonAnnul().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popUp.dismiss();
                    }
                });
                popUp.getButtonValid().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), getPlayer_list().get(position).get_name() + " supprimé", Toast.LENGTH_SHORT).show();
                        getPlayer_list().remove(position);

                        saveData(getPlayer_list(), FILE_NAME);

                        PlayerAdapter playerAdapter = new PlayerAdapter(getActivity(), getPlayer_list(), getNames());
                        getPlayer_list_view().setAdapter(playerAdapter);
                        popUp.dismiss();
                    }
                });
                popUp.build();

                return false;
            }
        });

        Button buttonAddPlayer = (Button) findViewById(R.id.button_add_player);
        buttonAddPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPlayer_list().add(new Player("Nom", 1, 0, Class.NO_CLASS, Race.NO_RACE));
                goToEditPlayer(getPlayer_list().size()-1);

                saveData(player_list, FILE_NAME);

            }
        });

        Button buttonFight = findViewById(R.id.button_fight);
        buttonFight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity = new Intent(getApplicationContext(), PlayersFightActivity2.class);
                startActivity(activity);
                finish();
            }
        });

        Button button_restart = findViewById(R.id.button_restart);
        button_restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Files file = new Files(getPlayer_list(), FILE_NAME);
            }
        });

        Button buttondes = findViewById(R.id.button_des);
        buttondes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "des de 8 : "+(0 + (int) Math.round(Math.random()
                        * ((8 - 0) + 1)) + "   des de 5 : " + (0 + (int) Math.round(Math.random()
                        * ((5 - 0) + 1)))),Toast.LENGTH_LONG).show();
            }
        });

    }

    public void goToEditPlayer(int position) {
        Intent playersEditActivity = new Intent(getApplicationContext(), PlayerEditActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("player_list", player_list);
        playersEditActivity.putExtras(bundle);
        playersEditActivity.putExtra("position", position);
        startActivity(playersEditActivity);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent activity = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(activity);
        finish();
    }

    public String[] getNames() {
        String[] names = new String[getPlayer_list().size()];
        int i = 0;
        for (Player player: getPlayer_list()
        ) {
            if (i <= 5)
                names[i] = player.get_name();
            i++;
        }
        return names;
    }

    public ArrayList<Player> getPlayer_list() {
        return player_list;
    }

    public void setPlayer_list(ArrayList<Player> player_list) {
        this.player_list = player_list;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public ListView getPlayer_list_view() {
        return player_list_view;
    }

    public void setPlayer_list_view(ListView player_list_view) {
        this.player_list_view = player_list_view;
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
                int id = Integer.parseInt(stringList.get(z+9));
                playerList.add(new Player(str,race,_class,attack,defense,life,level,money,isAlive,id));
                z += 10;
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
                        player.get_isAlive()+"|"+player.getId()+"|/";
                file.write(string.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        Toast.makeText(getApplicationContext(), "Saved to "+ getFilesDir() + "/" + file_name, Toast.LENGTH_LONG).show();
    }

    private void saveData(ArrayList<Player> list2Save, String nameList2Save) {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list2Save);
        editor.putString(nameList2Save, json);
        editor.apply();
    }

    private ArrayList<Player> loadData(String nameList2Get) {
        ArrayList<Player> list2Return = new ArrayList<>();
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(nameList2Get, null);
        Type type = new TypeToken<ArrayList<Player>>() {}.getType();
        list2Return = gson.fromJson(json, type);

        if (list2Return == null) {
            list2Return = new ArrayList<>();
        }

        return list2Return;
    }
}
