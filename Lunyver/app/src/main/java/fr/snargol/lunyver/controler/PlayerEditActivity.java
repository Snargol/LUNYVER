package fr.snargol.lunyver.controler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
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
import fr.snargol.lunyver.model.Enums.Class;
import fr.snargol.lunyver.model.Enums.Race;
import fr.snargol.lunyver.model.Player;
import fr.snargol.lunyver.model.PopUpChooseClass;
import fr.snargol.lunyver.model.PopUpChooseRace;
import fr.snargol.lunyver.model.PopUpChooseText;
import fr.snargol.lunyver.model.PopUpConfirm;

public class PlayerEditActivity extends AppCompatActivity {

    final private String FILE_NAME = "datasLUNYVER";
    ArrayList<Player> player_list;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_edit);

        Bundle bundle = getIntent().getExtras();
        int position = getIntent().getIntExtra("position", 0);
        if (bundle != null) {
            setPlayer_list((ArrayList<Player>) bundle.getSerializable("player_list"));
        }
        setPosition(position);
        setDatasOnView(getPlayer_list().get(getPosition()));

        setOnClick(this);

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

    public void setOnClick(final Activity activity) {
        final TextView playerLevel = findViewById(R.id.player_edit_level);
        Button but_up_level = findViewById(R.id.player_edit_but_up_level);
        Button but_down_level = findViewById(R.id.player_edit_but_down_level);
        Button but_up_attack = findViewById(R.id.player_edit_but_up_attack);
        Button but_down_attack = findViewById(R.id.player_edit_but_down_attack);
        Button but_up_defense = findViewById(R.id.player_edit_but_up_defense);
        Button but_down_defense = findViewById(R.id.player_edit_but_down_defense);
        Button but_up_life = findViewById(R.id.player_edit_but_up_life);
        Button but_down_life = findViewById(R.id.player_edit_but_down_life);
        final TextView playerAttack = findViewById(R.id.player_edit_attack);
        final TextView playerDefense= findViewById(R.id.player_edit_defense);
        final TextView playerLife = findViewById(R.id.player_edit_life);
        final ImageButton buttonRace = findViewById(R.id.player_edit_race);
        final ImageButton buttonClass = findViewById(R.id.player_edit_class);
        Button but_reset_player = findViewById(R.id.player_edit_but_reset);
        Button edit_name = (Button) findViewById(R.id.player_edit_name);

        but_reset_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopUpConfirm popUp = new PopUpConfirm(activity);
                popUp.setTitle("Voulez vous réinitialiser le personnage " + getPlayer_list().get(position).get_name() + " ?");
                popUp.getButtonAnnul().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popUp.dismiss();
                    }
                });
                popUp.getButtonValid().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), getPlayer_list().get(position).get_name() + " réinitialisé", Toast.LENGTH_SHORT).show();
                        getPlayer_list().get(getPosition()).resetPlayer();
                        playerLevel.setText(String.valueOf(getPlayer_list().get(getPosition()).get_level()));
                        playerAttack.setText(String.valueOf(getPlayer_list().get(getPosition()).get_attack()));
                        playerDefense.setText(String.valueOf(getPlayer_list().get(getPosition()).get_defense()));
                        playerLife.setText(String.valueOf(getPlayer_list().get(getPosition()).get_life()));
                        String ressourceName1 = "race_" + getPlayer_list().get(getPosition()).get_race();
                        int resId1 = getApplicationContext().getResources().getIdentifier(ressourceName1.toLowerCase(), "drawable", getApplicationContext().getPackageName());
                        buttonRace.setImageResource(resId1);
                        String ressourceName2 = "class_" + getPlayer_list().get(getPosition()).get_class();
                        int resId2 = getApplicationContext().getResources().getIdentifier(ressourceName2.toLowerCase(), "drawable", getApplicationContext().getPackageName());
                        buttonClass.setImageResource(resId2);
                        popUp.dismiss();
                    }
                });
                popUp.build();


            }
        });

        but_up_level.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPlayer_list().get(getPosition()).add_level(1);
                playerLevel.setText(String.valueOf(getPlayer_list().get(getPosition()).get_level()));
            }
        });
        but_down_level.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPlayer_list().get(getPosition()).add_level(-1);
                playerLevel.setText(String.valueOf(getPlayer_list().get(getPosition()).get_level()));
            }
        });

        but_up_attack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPlayer_list().get(getPosition()).add_attack(1);
                playerAttack.setText(String.valueOf(getPlayer_list().get(getPosition()).get_attack()));
            }
        });
        but_down_attack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPlayer_list().get(getPosition()).add_attack(-1);
                playerAttack.setText(String.valueOf(getPlayer_list().get(getPosition()).get_attack()));
            }
        });

        but_up_defense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPlayer_list().get(getPosition()).add_defense(1);
                playerDefense.setText(String.valueOf(getPlayer_list().get(getPosition()).get_defense()));
            }
        });
        but_down_defense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPlayer_list().get(getPosition()).add_defense(-1);
                playerDefense.setText(String.valueOf(getPlayer_list().get(getPosition()).get_defense()));
            }
        });

        but_up_life.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPlayer_list().get(getPosition()).add_life(1);
                playerLife.setText(String.valueOf(getPlayer_list().get(getPosition()).get_life()));
            }
        });
        but_down_life.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPlayer_list().get(getPosition()).add_life(-1);
                playerLife.setText(String.valueOf(getPlayer_list().get(getPosition()).get_life()));
            }
        });

        buttonClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopUpChooseClass popUpClass = new PopUpChooseClass(activity);
                setOnClickPopUp(popUpClass);
                popUpClass.build();
            }
        });

        buttonRace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopUpChooseRace popUpRace = new PopUpChooseRace(activity);
                setOnClickPopUp(popUpRace);
                popUpRace.build();
            }
        });

        edit_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopUpChooseText popUp = new PopUpChooseText(activity);
                setOnClickPopUp(popUp);
                popUp.getInputText().requestFocus();
                if (!String.valueOf(popUp.getInputText().getText()).toLowerCase().equals("nom"))
                    popUp.getInputText().setText(getPlayer_list().get(getPosition()).get_name());
                popUp.setTitle("Veuillez entrer un nom pour le personnage");
                popUp.build();
            }
        });
    }

    public void setOnClickPopUp(final PopUpChooseText popUp) {
        popUp.getButtonValid().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popUp.getInputText().getText().length() != 0){
                    getPlayer_list().get(getPosition()).set_name(String.valueOf(popUp.getInputText().getText()));
                    try {
                        saveDatas(getPlayer_list(), FILE_NAME);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    Button button_name = findViewById(R.id.player_edit_name);
                    button_name.setText(getPlayer_list().get(getPosition()).get_name());
                    popUp.dismiss();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Veuillez sélectionner un nom avant de valider", Toast.LENGTH_LONG).show();
                }
            }
        });
        popUp.getButtonAnnul().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUp.dismiss();
            }
        });

    }

    public void setOnClickPopUp(final PopUpChooseRace popUpRace) {
        popUpRace.getButtonValid().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popUpRace.getCurrentRace() != null){
                    getPlayer_list().get(getPosition()).set_race(popUpRace.getCurrentRace() );
                    ImageButton buttonRace = findViewById(R.id.player_edit_race);
                    String ressourceName2 = "race_" + popUpRace.getCurrentRace();
                    int resId2 = getApplicationContext().getResources().getIdentifier(ressourceName2.toLowerCase(), "drawable", getApplicationContext().getPackageName());
                    buttonRace.setImageResource(resId2);
                    popUpRace.dismiss();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Veuillez sélectionner une race avant de valider", Toast.LENGTH_LONG).show();
                }
            }
        });
        popUpRace.getButtonAnnul().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpRace.dismiss();
            }
        });

    }

    public void setOnClickPopUp(final PopUpChooseClass popUpClass) {
        popUpClass.getButtonValid().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popUpClass.getCurrentClass() != null){
                    getPlayer_list().get(getPosition()).set_class(popUpClass.getCurrentClass() );
                    ImageButton buttonClass = findViewById(R.id.player_edit_class);
                    String ressourceName2 = "class_" + popUpClass.getCurrentClass();
                    int resId2 = getApplicationContext().getResources().getIdentifier(ressourceName2.toLowerCase(), "drawable", getApplicationContext().getPackageName());
                    buttonClass.setImageResource(resId2);
                    popUpClass.dismiss();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Veuillez sélectionner une classe avant de valider", Toast.LENGTH_LONG).show();
                }
            }
        });
        popUpClass.getButtonAnnul().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpClass.dismiss();
            }
        });

    }

    public void setDatasOnView(Player player){
        TextView playerName = findViewById(R.id.player_edit_name);
        playerName.setText(player.get_name());

        TextView playerAttack = findViewById(R.id.player_edit_attack);
        playerAttack.setText(String.valueOf(player.get_attack()));

        TextView playerDefense = findViewById(R.id.player_edit_defense);
        playerDefense.setText(String.valueOf(player.get_defense()));

        TextView playerLife = findViewById(R.id.player_edit_life);
        playerLife.setText(String.valueOf(player.get_life()));

        TextView playerLevel = findViewById(R.id.player_edit_level);
        playerLevel.setText(String.valueOf(player.get_level()));

        ImageView playerRace= findViewById(R.id.player_edit_race);
        String ressourceName = "race_" + player.get_race();
        int resId =  getApplicationContext().getResources().getIdentifier(ressourceName.toLowerCase(), "drawable", getApplicationContext().getPackageName());
        playerRace.setImageResource(resId);

        ImageView playerClass= findViewById(R.id.player_edit_class);
        String ressourceName2 = "class_" + player.get_class();
        int resId2 =  getApplicationContext().getResources().getIdentifier(ressourceName2.toLowerCase(), "drawable",  getApplicationContext().getPackageName());
        playerClass.setImageResource(resId2);
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

    public ArrayList<Player> getPlayer_list() {
        return player_list;
    }

    public void setPlayer_list(ArrayList<Player> player_list) {
        this.player_list = player_list;
    }

    public String getFILE_NAME() {
        return FILE_NAME;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
//
//imgView .setVisibility(View.VISIBLE);
//
//        imgView .setVisibility(View.INVISIBLE);
