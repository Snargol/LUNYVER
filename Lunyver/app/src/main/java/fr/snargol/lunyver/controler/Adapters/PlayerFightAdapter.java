package fr.snargol.lunyver.controler.Adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import fr.snargol.lunyver.R;
import fr.snargol.lunyver.model.Bonus;
import fr.snargol.lunyver.model.Player;

import static fr.snargol.lunyver.R.id.text_player_name_list;
import static fr.snargol.lunyver.R.layout.adapter_player_list;

public class PlayerFightAdapter extends ArrayAdapter<String> {

    private Context context;
    private List<Player> players_list;
    private ArrayList<Bonus> bonus_list;

    public PlayerFightAdapter(Context context, List<Player> players_list, String[] names) {
        super(context, R.layout.adapter_player_list, R.id.text_player_name_list, names);
        this.context = context;
        this.players_list = players_list;
    }
}
