package fr.snargol.lunyver.controler.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import fr.snargol.lunyver.R;
import fr.snargol.lunyver.model.Player;

public class PlayerFightDisplayResultAdapter extends ArrayAdapter<String> {

    private Context context;
    private List<Player> players_list;

    public PlayerFightDisplayResultAdapter(Context context, List<Player> players_list, String[] names) {
        super(context, R.layout.adapter_player_display_fight_result, R.id.text_player_name_list, names);
        this.context = context;
        this.players_list = players_list;
    }


    public Player getPlayer(int position) {
        return players_list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.adapter_player_display_fight_result, parent, false);

//        convertView = inflater.inflate(R.layout.adapter_player_list, parent, false);

        Player currentPlayer = (Player) getPlayer(position);
        setDatasOnView(currentPlayer, row);

        return row;
    }

    @SuppressLint("SetTextI18n")
    public void setDatasOnView(Player player, View view){
        TextView playerName = view.findViewById(R.id.adapter_player_display_fight_pseudo);
        playerName.setText(player.get_name());
        if (player.get_life() > 0)
            playerName.setTextColor(ContextCompat.getColor(getContext(), R.color.colorGreen));
        else
            playerName.setTextColor(ContextCompat.getColor(getContext(), R.color.colorRed));

        TextView playerLifeAfter = view.findViewById(R.id.adapter_player_display_fight_life_after);
        playerLifeAfter.setText(player.get_life()+"");

        TextView playerLifeBefore = view.findViewById(R.id.adapter_player_display_fight_life_before);
        playerLifeBefore.setText((player.get_life()+player.get_suffer_damages())+"");

        TextView playerLifeDamage = view.findViewById(R.id.adapter_player_display_fight_suffer_damages);
        playerLifeDamage.setText("-"+player.get_suffer_damages());

        ImageView playerRace= view.findViewById(R.id.adapter_player_display_fight_image_race);
        playerRace.setImageResource(player.getRessourceIdRace(getContext()));
    }
}