package fr.snargol.lunyver.controler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import fr.snargol.lunyver.R;
import fr.snargol.lunyver.model.Player;

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

        setOnClick();

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

    public void setOnClick() {
        final TextView playerLevel = findViewById(R.id.player_edit_level);
        Button but_up_level = findViewById(R.id.player_edit_but_up_level);
        but_up_level.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPlayer_list().get(getPosition()).add_level(1);
                playerLevel.setText(String.valueOf(getPlayer_list().get(getPosition()).get_level()));
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
                        player.get_isAlive()+"|/";
                file.write(string.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Toast.makeText(getApplicationContext(), "Saved to "+ getFilesDir() + "/" + file_name, Toast.LENGTH_LONG).show();
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
