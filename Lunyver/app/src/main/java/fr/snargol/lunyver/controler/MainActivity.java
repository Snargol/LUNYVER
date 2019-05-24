package fr.snargol.lunyver.controler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fr.snargol.lunyver.R;

public class MainActivity extends AppCompatActivity {

    private Button goToPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setVars();

        goToPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playersActivity = new Intent(getApplicationContext(), PlayersActivity.class);
                startActivity(playersActivity);
                finish();
            }
        });
    }

    private void setVars() {
        this.goToPlayers = (Button) findViewById(R.id.main_button_classical_game);
    }



}
