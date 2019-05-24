package fr.snargol.lunyver.controler;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fr.snargol.lunyver.R;
import fr.snargol.lunyver.controler.Adapters.PlayerAdapter;
import fr.snargol.lunyver.model.Enums.Class;
import fr.snargol.lunyver.model.Enums.Race;
import fr.snargol.lunyver.model.Player;

public class PlayersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

        List<Player> player_list = new ArrayList<>();
        player_list.add(new Player("Snargol", 14, 7 , Class.THIEF, Race.WEREWOLF));
        player_list.add(new Player("Euxiniar", 12, 6 , Class.ARCHER, Race.FAIRY));
        player_list.add(new Player("Maxou", 11, 9 , Class.BERSERK, Race.TROLL));
        player_list.add(new Player("Zozo", 15, 7 , Class.FLASH, Race.CENTAUR));
        player_list.add(new Player("Phelie", 16, 5 , Class.TRAINER, Race.GNOME));
        player_list.add(new Player("Jere", 13, 8 , Class.PROTECTOR, Race.ELF));

        ListView player_list_view = findViewById(R.id.list_view_player_list);
        player_list_view.setAdapter(new PlayerAdapter(this, player_list));

//        ConstraintLayout layout = findViewById(R.id.layout_player_list);
//        layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent goldActivity = new Intent(getApplicationContext(), Gold.class);
////                startActivityForResult(goldActivity, 42);
////                finish();
//
//
//            }
//        });
    }
}
