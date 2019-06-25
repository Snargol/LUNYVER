package fr.snargol.lunyver.controler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import fr.snargol.lunyver.R;
import fr.snargol.lunyver.controler.Adapters.PlayerAdapter;
import fr.snargol.lunyver.controler.Adapters.PlayerFightAdapter;
import fr.snargol.lunyver.model.Bonus;
import fr.snargol.lunyver.model.Enums.Class;
import fr.snargol.lunyver.model.Enums.Race;
import fr.snargol.lunyver.model.Player;
import fr.snargol.lunyver.model.PopUpConfirm;
import fr.snargol.lunyver.model.PopUpEnterBonus;
import fr.snargol.lunyver.model.PopUpSelectPlayer;

public class PlayersFightActivity extends AppCompatActivity {
    ArrayList<Player> player_list = new ArrayList<>();
    ArrayList<Player> player_list_off = new ArrayList<>();
    ArrayList<Player> player_list_def = new ArrayList<>();
    ArrayList<Bonus> bonus_list = new ArrayList<>();
    ListView player_list_view_off;
    ListView player_list_view_def;
    final String FILE_NAME = "datasLUNYVER";
    final String FILE_NAME_OFF = "datasLUNYVERFightOff";
    final String FILE_NAME_DEF = "datasLUNYVERFightDef";
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players_fight);

        try {
            setPlayer_list(loadDatas(FILE_NAME));
//            setPlayer_list_off(loadDatas(FILE_NAME_OFF));
//            setPlayer_list_def(loadDatas(FILE_NAME_DEF));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setActivity(this);
        setPlayer_list_view_off((ListView) findViewById(R.id.players_fight_list_off));
        setPlayer_list_view_def((ListView) findViewById(R.id.players_fight_list_def));

        PlayerFightAdapter playerAdapterOff = new PlayerFightAdapter(this, getPlayer_list_off(), getNames(getPlayer_list_off()), getBonus_list());
        getPlayer_list_view_off().setAdapter(playerAdapterOff);
        PlayerFightAdapter playerAdapterDef = new PlayerFightAdapter(this, getPlayer_list_def(), getNames(getPlayer_list_def()), getBonus_list());
        getPlayer_list_view_def().setAdapter(playerAdapterDef);

        Button addPlayerOff = findViewById(R.id.players_fight_add_off);
        addPlayerOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopUpSelectPlayer popUp = new PopUpSelectPlayer(getActivity(), getPlayer_list(), getPlayer_list_off(), getPlayer_list_def(),  true);
                popUp.getButtonAnnul().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popUp.dismiss();
                    }
                });
                popUp.getButtonValid().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            saveDatas(popUp.getPlayer_list_off(), getFILE_NAME_OFF());
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        setPlayer_list_off(popUp.getPlayer_list_off());
                        PlayerFightAdapter playerAdapterOff = new PlayerFightAdapter(getActivity(), getPlayer_list_off(), getNames(getPlayer_list_off()), getBonus_list());
                        getPlayer_list_view_off().setAdapter(playerAdapterOff);
                        popUp.dismiss();
                    }
                });
                popUp.build();
            }
        });

        Button addPlayerdef = findViewById(R.id.players_fight_add_def);
        addPlayerdef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopUpSelectPlayer popUp = new PopUpSelectPlayer(getActivity(), getPlayer_list(), getPlayer_list_off(), getPlayer_list_def(), false);
                popUp.getButtonAnnul().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popUp.dismiss();
                    }
                });
                popUp.getButtonValid().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        try {
                            saveDatas(popUp.getPlayer_list_def(), getFILE_NAME_DEF());
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        setPlayer_list_def(popUp.getPlayer_list_def());
                        PlayerFightAdapter playerAdapterDef = new PlayerFightAdapter(getActivity(), getPlayer_list_def(), getNames(getPlayer_list_def()), getBonus_list());
                        getPlayer_list_view_def().setAdapter(playerAdapterDef);
                        popUp.dismiss();
                    }
                });
                popUp.build();
            }
        });

        Button fight_button = findViewById(R.id.players_fight_button_fight);
        fight_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Fight fight = new Fight(getPlayer_list_off(), getPlayer_list_def(), getBonus_list());
                fight.startFight();

                final PopUpConfirm popUp = new PopUpConfirm(getActivity());
                popUp.setTitle("Les gagnants sont les "+fight.getWinner() + "s !");
                popUp.getButtonAnnul().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popUp.dismiss();
                    }
                });
                popUp.getButtonValid().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popUp.dismiss();
                        try {
                            saveDatas(getPlayer_list(), getFILE_NAME());
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        Intent playersActivity = new Intent(getApplicationContext(), PlayersActivity.class);
                        startActivity(playersActivity);
                        finish();
                    }
                });
                popUp.build();
            }
        });

        getPlayer_list_view_off().setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final PopUpEnterBonus popUp = new PopUpEnterBonus(getActivity(), getPlayer_list_off().get(position));
                if (getBonusById(getPlayer_list_off().get(position).getId()) != null){
                    popUp.addBonus(getBonusById(getPlayer_list().get(position).getId()));
                }

                popUp.getButtonAnnul().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popUp.dismiss();
                    }
                });
                popUp.getButtonValid().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (popUp.getInputAttack().getText() != null &&
                                popUp.getInputDefense().getText() != null &&
                                popUp.getInputLife().getText() != null) {
                            popUp.assertTextsNonNull();
                            int attack = Integer.parseInt(String.valueOf(popUp.getInputAttack().getText()));
                            int defense = Integer.parseInt(String.valueOf(popUp.getInputDefense().getText()));
                            int life = Integer.parseInt(String.valueOf(popUp.getInputLife().getText()));

                            //if the bonus already exist then ... else ...
                            if (getBonusById(getPlayer_list_off().get(position).getId()).getId() != 0){
                                getBonus_list().set(getPositionOfBonus(getPlayer_list_off().get(position).getId()),
                                        new Bonus(attack,defense,life,getPlayer_list_off().get(position).getId()));
                            }
                            else {
                                getBonus_list().add(new Bonus(attack,defense,life,getPlayer_list_off().get(position).getId()));
                            }
                            PlayerFightAdapter playerAdapterOff = new PlayerFightAdapter(getActivity(), getPlayer_list_off(), getNames(getPlayer_list_off()), getBonus_list());
                            getPlayer_list_view_off().setAdapter(playerAdapterOff);

                            popUp.dismiss();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Veuillez rentrer un bonus dans chaque champ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                popUp.build();
            }
        });


        getPlayer_list_view_def().setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final PopUpEnterBonus popUp = new PopUpEnterBonus(getActivity(), getPlayer_list_def().get(position));
                if (getBonusById(getPlayer_list_def().get(position).getId()) != null){
                    popUp.addBonus(getBonusById(getPlayer_list().get(position).getId()));
                }

                popUp.getButtonAnnul().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popUp.dismiss();
                    }
                });
                popUp.getButtonValid().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (popUp.getInputAttack().getText() != null &&
                                popUp.getInputDefense().getText() != null &&
                                popUp.getInputLife().getText() != null) {
                            popUp.assertTextsNonNull();
                            int attack = Integer.parseInt(String.valueOf(popUp.getInputAttack().getText()));
                            int defense = Integer.parseInt(String.valueOf(popUp.getInputDefense().getText()));
                            int life = Integer.parseInt(String.valueOf(popUp.getInputLife().getText()));

                            //if the bonus already exist then ... else ...
                            if (getBonusById(getPlayer_list_def().get(position).getId()).getId() != 0){
                                getBonus_list().set(getPositionOfBonus(getPlayer_list_def().get(position).getId()),
                                        new Bonus(attack,defense,life,getPlayer_list_def().get(position).getId()));
                            }
                            else {
                                getBonus_list().add(new Bonus(attack,defense,life,getPlayer_list_def().get(position).getId()));
                            }
                            PlayerFightAdapter playerAdapterdef = new PlayerFightAdapter(getActivity(), getPlayer_list_def(), getNames(getPlayer_list_def()), getBonus_list());
                            getPlayer_list_view_def().setAdapter(playerAdapterdef);

                            popUp.dismiss();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Veuillez rentrer un bonus dans chaque champ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                popUp.build();
            }
        });
    }

    private Bonus getBonusById(int id) {
        int i = 0;
        for (Bonus bonus:getBonus_list()) {
            if (bonus.getId() == id)
                return getBonus_list().get(i);
            i++;
        }
        return new Bonus(0,0,0);
    }

    private int getPositionOfBonus(int id) {
        int i = 0;
        for (Bonus bonus:getBonus_list()) {
            if (bonus.getId() == id)
                return i;
            i++;
        }
        return -1;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent activity = new Intent(getApplicationContext(), PlayersActivity.class);
        try {
            saveDatas(getPlayer_list(), FILE_NAME);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        startActivity(activity);
        finish();
    }

    public String[] getNames(ArrayList<Player> list) {
        String[] names = new String[list.size()];
        int i = 0;
        for (Player player: list
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

    public ArrayList<Player> getPlayer_list_off() {
        return player_list_off;
    }

    public void setPlayer_list_off(ArrayList<Player> player_list_off) {
        this.player_list_off = player_list_off;
    }

    public ArrayList<Player> getPlayer_list_def() {
        return player_list_def;
    }

    public void setPlayer_list_def(ArrayList<Player> player_list_def) {
        this.player_list_def = player_list_def;
    }

    public String getFILE_NAME_OFF() {
        return FILE_NAME_OFF;
    }

    public String getFILE_NAME_DEF() {
        return FILE_NAME_DEF;
    }

    public ListView getPlayer_list_view_off() {
        return player_list_view_off;
    }

    public void setPlayer_list_view_off(ListView player_list_view_off) {
        this.player_list_view_off = player_list_view_off;
    }

    public ListView getPlayer_list_view_def() {
        return player_list_view_def;
    }

    public void setPlayer_list_view_def(ListView player_list_view_def) {
        this.player_list_view_def = player_list_view_def;
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

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public ArrayList<Bonus> getBonus_list() {
        return bonus_list;
    }

    public void setBonus_list(ArrayList<Bonus> bonus_list) {
        this.bonus_list = bonus_list;
    }

    public String getFILE_NAME() {
        return FILE_NAME;
    }
}
