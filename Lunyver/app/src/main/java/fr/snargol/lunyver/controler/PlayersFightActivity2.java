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
import android.widget.Toast;

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
        model.applyListDef(getApplicationContext());
//        On regénère les ids des joueurs pour éviter les doublons


        model.getFight_button().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (model.getPlayer_list_off().size() > 0 && model.getPlayer_list_def().size() > 0) {
                    final Fight2 fight = new Fight2(model.getPlayer_list_off(), model.getPlayer_list_def());
                    fight.startFight();

                    final PopUpConfirm popUp = new PopUpConfirm(model.getActivity());
                    popUp.setTitle("Les gagnants sont les " + fight.getWinner() + "s !");
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
                else {
                    Toast.makeText(getApplicationContext(), "Veuillez sélectionner au moins un joueur dans chaque équipe.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // --------------------ATTAQUE--------------------------------
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
        //-----------------------------------------------------------------
        // --------------------DEFENSE-------------------------------------
//        Afficher les monstres
        model.getArrowRightDef().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.getArrowRightDef().setVisibility(View.INVISIBLE);
                model.getArrowLeftDef().setVisibility(View.VISIBLE);
                model.setDefDisplayMonster(true);
                model.applyListDef(getApplicationContext());
            }
        });

//        Afficher les joueurs
        model.getArrowLeftDef().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.getArrowLeftDef().setVisibility(View.INVISIBLE);
                model.getArrowRightDef().setVisibility(View.VISIBLE);
                model.setDefDisplayMonster(false);
                model.applyListDef(getApplicationContext());
            }
        });
        //-----------------------------------------------------------------
        // --------------------ATTAQUE-------------------------------------

//        set onclicklisteneer pour les sélections de joueur off
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
                model.actionPlayerLongClick(0, true);
                return false;
            }
        });

        model.getButton2off().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                model.actionPlayerLongClick(1, true);
                return false;
            }
        });

        model.getButton3off().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                model.actionPlayerLongClick(2, true);
                return false;
            }
        });

        model.getButton4off().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                model.actionPlayerLongClick(3, true);
                return false;
            }
        });

        model.getButton5off().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                model.actionPlayerLongClick(4, true);
                return false;
            }
        });

        model.getButton6off().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                model.actionPlayerLongClick(5, true);
                return false;
            }
        });
        //-----------------------------------------------------------------
        // --------------------DEFENSE-------------------------------------
//        set onclicklisteneer pour les sélections de joueur def
        model.getButton1def().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.actionPlayerSelectDef(0, getApplicationContext());
            }
        });

        model.getButton2def().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.actionPlayerSelectDef(1, getApplicationContext());
            }
        });

        model.getButton3def().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.actionPlayerSelectDef(2, getApplicationContext());
            }
        });

        model.getButton4def().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.actionPlayerSelectDef(3, getApplicationContext());
            }
        });

        model.getButton5def().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.actionPlayerSelectDef(4, getApplicationContext());
            }
        });

        model.getButton6def().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.actionPlayerSelectDef(5, getApplicationContext());
            }
        });

        model.getButton1def().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                model.actionPlayerLongClick(0, false);
                return false;
            }
        });

        model.getButton2def().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                model.actionPlayerLongClick(1, false);
                return false;
            }
        });

        model.getButton3def().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                model.actionPlayerLongClick(2, false);
                return false;
            }
        });

        model.getButton4def().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                model.actionPlayerLongClick(3, false);
                return false;
            }
        });

        model.getButton5def().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                model.actionPlayerLongClick(4, false);
                return false;
            }
        });

        model.getButton6def().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                model.actionPlayerLongClick(5, false);
                return false;
            }
        });
        //-----------------------------------------------------------------

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

        model.setArrowRightOff((ImageButton) findViewById(R.id.players_fight_off_right_arrow));
        model.setArrowLeftOff((ImageButton) findViewById(R.id.players_fight_off_left_arrow));

        model.setTotalDefOff((TextView) findViewById(R.id.players_fight_text_total_off_def));
        model.setTotalOffOff((TextView) findViewById(R.id.players_fight_text_total_off_off));
        model.setTotalLifeOff((TextView) findViewById(R.id.players_fight_text_total_off_life));



        model.setButton1def((ImageButton) findViewById(R.id.players_fight_select_button_1_def));
        model.setButton2def((ImageButton) findViewById(R.id.players_fight_select_button_2_def));
        model.setButton3def((ImageButton) findViewById(R.id.players_fight_select_button_3_def));
        model.setButton4def((ImageButton) findViewById(R.id.players_fight_select_button_4_def));
        model.setButton5def((ImageButton) findViewById(R.id.players_fight_select_button_5_def));
        model.setButton6def((ImageButton) findViewById(R.id.players_fight_select_button_6_def));

        model.setValid1def((ImageView) findViewById(R.id.players_fight_valid_image_1_def));
        model.setValid2def((ImageView) findViewById(R.id.players_fight_valid_image_2_def));
        model.setValid3def((ImageView) findViewById(R.id.players_fight_valid_image_3_def));
        model.setValid4def((ImageView) findViewById(R.id.players_fight_valid_image_4_def));
        model.setValid5def((ImageView) findViewById(R.id.players_fight_valid_image_5_def));
        model.setValid6def((ImageView) findViewById(R.id.players_fight_valid_image_6_def));

        model.setPseudo1def((TextView) findViewById(R.id.players_fight_pseudo_text_1_def));
        model.setPseudo2def((TextView) findViewById(R.id.players_fight_pseudo_text_2_def));
        model.setPseudo3def((TextView) findViewById(R.id.players_fight_pseudo_text_3_def));
        model.setPseudo4def((TextView) findViewById(R.id.players_fight_pseudo_text_4_def));
        model.setPseudo5def((TextView) findViewById(R.id.players_fight_pseudo_text_5_def));
        model.setPseudo6def((TextView) findViewById(R.id.players_fight_pseudo_text_6_def));

        model.setArrowRightDef((ImageButton) findViewById(R.id.players_fight_def_right_arrow));
        model.setArrowLeftDef((ImageButton) findViewById(R.id.players_fight_def_left_arrow));

        model.setTotalDefDef((TextView) findViewById(R.id.players_fight_text_total_def_def));
        model.setTotalOffDef((TextView) findViewById(R.id.players_fight_text_total_def_off));
        model.setTotalLifeDef((TextView) findViewById(R.id.players_fight_text_total_def_life));

        model.setFight_button((Button) findViewById(R.id.players_fight_button_fight));


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


        model.getButtons_def_list().add(model.getButton1def());
        model.getButtons_def_list().add(model.getButton2def());
        model.getButtons_def_list().add(model.getButton3def());
        model.getButtons_def_list().add(model.getButton4def());
        model.getButtons_def_list().add(model.getButton5def());
        model.getButtons_def_list().add(model.getButton6def());

        model.getGreen_borders_def_list().add(model.getValid1def());
        model.getGreen_borders_def_list().add(model.getValid2def());
        model.getGreen_borders_def_list().add(model.getValid3def());
        model.getGreen_borders_def_list().add(model.getValid4def());
        model.getGreen_borders_def_list().add(model.getValid5def());
        model.getGreen_borders_def_list().add(model.getValid6def());
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
