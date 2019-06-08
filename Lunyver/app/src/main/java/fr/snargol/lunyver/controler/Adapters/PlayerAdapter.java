package fr.snargol.lunyver.controler.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import fr.snargol.lunyver.R;
import fr.snargol.lunyver.model.Player;

public class PlayerAdapter extends ArrayAdapter<String> {

    private Context context;
    private List<Player> players_list;

    public PlayerAdapter(Context context, List<Player> players_list, String[] names) {
        super(context, R.layout.adapter_player_list, R.id.text_player_name_list, names);
        this.context = context;
        this.players_list = players_list;
    }

    //    @Override
//    public int getCount() {
//        return players_list.size();
//    }
//
//    @Override
    public Player getPlayer(int position) {
        return players_list.get(position);
    }
//
//    @Override
//    public long getItemId(int position) {
//        return 0;
//    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.adapter_player_list, parent, false);

//        convertView = inflater.inflate(R.layout.adapter_player_list, parent, false);

        Player currentPlayer = (Player) getPlayer(position);
        setDatasOnView(currentPlayer, row);

        return row;
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
