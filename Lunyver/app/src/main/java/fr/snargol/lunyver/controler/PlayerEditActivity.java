package fr.snargol.lunyver.controler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import fr.snargol.lunyver.R;
import fr.snargol.lunyver.model.Player;

public class PlayerEditActivity extends AppCompatActivity {

    Player current_player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_edit);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            setCurrent_player((Player) bundle.getSerializable("player_list"));
        }

        setDatasOnView();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent activity = new Intent(getApplicationContext(), PlayersActivity.class);
        startActivity(activity);
        finish();
    }

    public void setDatasOnView(){
        TextView playerName = findViewById(R.id.player_edit_name);
        playerName.setText(getCurrent_player().get_name());

        TextView playerAttack = findViewById(R.id.player_edit_attack);
        playerAttack.setText(String.valueOf(getCurrent_player().get_attack()));

        TextView playerDefense = findViewById(R.id.player_edit_defense);
        playerDefense.setText(String.valueOf(getCurrent_player().get_defense()));

        TextView playerLife = findViewById(R.id.player_edit_life);
        playerLife.setText(String.valueOf(getCurrent_player().get_life()));

        TextView playerLevel = findViewById(R.id.player_edit_level);
        playerLevel.setText(String.valueOf(getCurrent_player().get_level()));

        ImageView playerRace= findViewById(R.id.player_edit_race);
        String ressourceName = "race_" + getCurrent_player().get_race();
        int resId =  getApplicationContext().getResources().getIdentifier(ressourceName.toLowerCase(), "drawable", getApplicationContext().getPackageName());
        playerRace.setImageResource(resId);

        ImageView playerClass= findViewById(R.id.player_edit_class);
        String ressourceName2 = "class_" + getCurrent_player().get_class();
        int resId2 =  getApplicationContext().getResources().getIdentifier(ressourceName2.toLowerCase(), "drawable",  getApplicationContext().getPackageName());
        playerClass.setImageResource(resId2);
    }

    public Player getCurrent_player() {
        return current_player;
    }

    public void setCurrent_player(Player current_player) {
        this.current_player = current_player;
    }
}
