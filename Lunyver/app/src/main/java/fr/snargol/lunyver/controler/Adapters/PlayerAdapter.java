package fr.snargol.lunyver.controler.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import fr.snargol.lunyver.R;
import fr.snargol.lunyver.model.Player;

public class PlayerAdapter extends BaseAdapter {

    private Context context;
    private List<Player> players_list;
    private LayoutInflater inflater;

    public PlayerAdapter(Context context, List<Player> players_list) {
        this.context = context;
        this.players_list = players_list;
        this.inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return players_list.size();
    }

    @Override
    public Object getItem(int position) {
        return players_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = inflater.inflate(R.layout.adapter_player_list, null);

        Player currentPlayer = (Player) getItem(position);
        setDatasOnView(currentPlayer, convertView);

        return convertView;
    }

    @SuppressLint("SetTextI18n")
    public void setDatasOnView(Player player, View view){
        TextView playerName = view.findViewById(R.id.text_player_name_list);
        playerName.setText(player.get_name());

        TextView playerAttack = view.findViewById(R.id.text_attack_list);
        playerAttack.setText(player.get_attack()+"");

        TextView playerDefense = view.findViewById(R.id.text_defense_list);
        playerDefense.setText(player.get_defense()+"");

        TextView playerLife = view.findViewById(R.id.text_life_list);
        playerLife.setText(player.get_life()+"");

        TextView playerLevel = view.findViewById(R.id.text_level_list);
        playerLevel.setText(player.get_level()+"");

        ImageView playerRace= view.findViewById(R.id.image_player_list);
        String ressourceName = "race_" + player.get_race();
        int resId = context.getResources().getIdentifier(ressourceName.toLowerCase(), "drawable", context.getPackageName());
        playerRace.setImageResource(resId);

        ImageView playerClass= view.findViewById(R.id.image_class_list);
        String ressourceName2 = "class_" + player.get_class();
        int resId2 = context.getResources().getIdentifier(ressourceName2.toLowerCase(), "drawable", context.getPackageName());
        playerClass.setImageResource(resId2);

    }
}
