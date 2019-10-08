package fr.snargol.lunyver.controler;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import fr.snargol.lunyver.R;
import fr.snargol.lunyver.model.Player;
import fr.snargol.lunyver.model.PopUpConfirm;

public class PlayersFightActivity2 extends AppCompatActivity {
    FightModel model = new FightModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players_fight2);
        setDatas();

        ArrayList<Player> list = loadData(model.getFILE_NAME());
//            setPlayer_list_off(loadDatas(FILE_NAME_OFF));
//            setPlayer_list_def(loadDatas(FILE_NAME_DEF));

        model.setActivity(this);

        //On supprime les données temporaires des joueurs pour qu'ils ne soient plus sélectionnés
        model.setAll_player_list(model.buildListPlayer(list));
        model.setMob_list(loadData(model.getFILE_NAME_MOBS()));
        model.suprTempDatas(model.getAll_player_list());
        model.suprTempDatas(model.getMob_list());
        model.regenerateIds();

//        On actualise les texts pour qu'ils correspondent aux noms des joueurs
        model.applyListOff(getApplicationContext());
//        On regénère les ids des joueurs pour éviter les doublons


        model.getFight_button().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Fight fight = new Fight(model.getPlayer_list_off(), model.getPlayer_list_def(), model.getBonus_list());
                fight.startFight();

                final PopUpConfirm popUp = new PopUpConfirm(model.getActivity());
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
                        saveData(model.getPlayer_list(), model.getFILE_NAME());
                        saveData(model.getMob_list(), model.getFILE_NAME_MOBS());
                        Intent playersActivity = new Intent(getApplicationContext(), PlayersActivity.class);
                        startActivity(playersActivity);
                        finish();
                    }
                });
                popUp.build();
            }
        });

//        Afficher les monstres
        model.getArrowRightOff().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.getArrowRightOff().setVisibility(View.INVISIBLE);
                model.getArrowLeftOff().setVisibility(View.VISIBLE);
                model.setOffDisplayMonster(true);
                model.applyListOff(getApplicationContext());
            }
        });

//        Afficher les joueurs
        model.getArrowLeftOff().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.getArrowLeftOff().setVisibility(View.INVISIBLE);
                model.getArrowRightOff().setVisibility(View.VISIBLE);
                model.setOffDisplayMonster(false);
                model.applyListOff(getApplicationContext());
            }
        });

//        setonclicklisteneer pour les sélections de joueur off
        model.getButton1off().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.actionPlayerSelectOff(0, getApplicationContext());
            }
        });

        model.getButton2off().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.actionPlayerSelectOff(1, getApplicationContext());
            }
        });

        model.getButton3off().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.actionPlayerSelectOff(2, getApplicationContext());
            }
        });

        model.getButton4off().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.actionPlayerSelectOff(3, getApplicationContext());
            }
        });

        model.getButton5off().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.actionPlayerSelectOff(4, getApplicationContext());
            }
        });

        model.getButton6off().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.actionPlayerSelectOff(5, getApplicationContext());
            }
        });

        model.getButton1off().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                model.actionPlayerLongClick(0);
                return false;
            }
        });

        model.getButton2off().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                model.actionPlayerLongClick(1);
                return false;
            }
        });

        model.getButton3off().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                model.actionPlayerLongClick(2);
                return false;
            }
        });

        model.getButton4off().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                model.actionPlayerLongClick(3);
                return false;
            }
        });

        model.getButton5off().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                model.actionPlayerLongClick(4);
                return false;
            }
        });

        model.getButton6off().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                model.actionPlayerLongClick(5);
                return false;
            }
        });

    }

//    Définition des données pur accéder aux éléments de la vue
    public void setDatas() {
        model.setButton1off((ImageButton) findViewById(R.id.players_fight_select_button_1_off));
        model.setButton2off((ImageButton) findViewById(R.id.players_fight_select_button_2_off));
        model.setButton3off((ImageButton) findViewById(R.id.players_fight_select_button_3_off));
        model.setButton4off((ImageButton) findViewById(R.id.players_fight_select_button_4_off));
        model.setButton5off((ImageButton) findViewById(R.id.players_fight_select_button_5_off));
        model.setButton6off((ImageButton) findViewById(R.id.players_fight_select_button_6_off));

        model.setValid1off((ImageView) findViewById(R.id.players_fight_valid_image_1_off));
        model.setValid2off((ImageView) findViewById(R.id.players_fight_valid_image_2_off));
        model.setValid3off((ImageView) findViewById(R.id.players_fight_valid_image_3_off));
        model.setValid4off((ImageView) findViewById(R.id.players_fight_valid_image_4_off));
        model.setValid5off((ImageView) findViewById(R.id.players_fight_valid_image_5_off));
        model.setValid6off((ImageView) findViewById(R.id.players_fight_valid_image_6_off));

        model.setPseudo1off((TextView) findViewById(R.id.players_fight_pseudo_text_1_off));
        model.setPseudo2off((TextView) findViewById(R.id.players_fight_pseudo_text_2_off));
        model.setPseudo3off((TextView) findViewById(R.id.players_fight_pseudo_text_3_off));
        model.setPseudo4off((TextView) findViewById(R.id.players_fight_pseudo_text_4_off));
        model.setPseudo5off((TextView) findViewById(R.id.players_fight_pseudo_text_5_off));
        model.setPseudo6off((TextView) findViewById(R.id.players_fight_pseudo_text_6_off));

        model.setFight_button((Button) findViewById(R.id.players_fight_button_fight));
        model.setArrowRightOff((ImageButton) findViewById(R.id.players_fight_off_right_arrow));
        model.setArrowLeftOff((ImageButton) findViewById(R.id.players_fight_off_left_arrow));

        model.setTotalDefOff((TextView) findViewById(R.id.players_fight_text_total_off_def));
        model.setTotalOffOff((TextView) findViewById(R.id.players_fight_text_total_off_off));
        model.setTotalLifeOff((TextView) findViewById(R.id.players_fight_text_total_off_life));

        //Ajout des boutons et bordures dans une liste pour y accéder facilement et itérativement
        model.getButtons_off_list().add(model.getButton1off());
        model.getButtons_off_list().add(model.getButton2off());
        model.getButtons_off_list().add(model.getButton3off());
        model.getButtons_off_list().add(model.getButton4off());
        model.getButtons_off_list().add(model.getButton5off());
        model.getButtons_off_list().add(model.getButton6off());

        model.getGreen_borders_off_list().add(model.getValid1off());
        model.getGreen_borders_off_list().add(model.getValid2off());
        model.getGreen_borders_off_list().add(model.getValid3off());
        model.getGreen_borders_off_list().add(model.getValid4off());
        model.getGreen_borders_off_list().add(model.getValid5off());
        model.getGreen_borders_off_list().add(model.getValid6off());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent activity = new Intent(getApplicationContext(), PlayersActivity.class);
        saveData(model.getPlayer_list(), model.getFILE_NAME());
        saveData(model.getMob_list(), model.getFILE_NAME_MOBS());
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
