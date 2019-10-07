package fr.snargol.lunyver.controler;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import fr.snargol.lunyver.R;
import fr.snargol.lunyver.controler.Adapters.PlayerAdapter;
import fr.snargol.lunyver.model.Player;

public class MobActivity extends AppCompatActivity {
    ArrayList<Player> mob_list = new ArrayList<>();
    Activity activity;
    ListView mob_list_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobs);

        setMob_list_view((ListView) findViewById(R.id.list_view_player_list));

        PlayerAdapter playerAdapter = new PlayerAdapter(this, getMob_list(), getNames());
        getMob_list_view().setAdapter(playerAdapter);

//        getMob_list_view().setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                goToEditPlayer(position);
//            }
//        });
    }

    public String[] getNames() {
        String[] names = new String[getMob_list().size()];
        int i = 0;
        for (Player player: getMob_list()
        ) {
                names[i] = player.get_name();
            i++;
        }
        return names;
    }

    public void goToEditMob(int position) {

    }

    public ArrayList<Player> getMob_list() {
        return mob_list;
    }

    public void setMob_list(ArrayList<Player> mob_list) {
        this.mob_list = mob_list;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public ListView getMob_list_view() {
        return mob_list_view;
    }

    public void setMob_list_view(ListView mob_list_view) {
        this.mob_list_view = mob_list_view;
    }
}
