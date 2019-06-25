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

import java.util.ArrayList;
import java.util.List;

import fr.snargol.lunyver.R;
import fr.snargol.lunyver.model.Bonus;
import fr.snargol.lunyver.model.Enums.Race;
import fr.snargol.lunyver.model.Player;

public class PlayerFightAdapter extends ArrayAdapter<String> {

    private Context context;
    private List<Player> players_list;
    private ArrayList<Bonus> bonus_list;

    public PlayerFightAdapter(Context context, List<Player> players_list, String[] names, ArrayList<Bonus> bonus_list) {
        super(context, R.layout.adapter_players_fight, R.id.adapter_player_fight_text_pseudo, names);
        setContext(context);
        setContext(context);
        setPlayers_list(players_list);
        setBonus_list(bonus_list);
    }

    private Bonus getBonusById(int id) {
        int i = 0;
        for (Bonus bonus:getBonus_list()) {
            if (bonus.getId() == id)
                return getBonus_list().get(i);
            i++;
        }
        return null;
    }

    public Player getPlayer(int position) {
        return getPlayers_list().get(position);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.adapter_players_fight, parent, false);

//        convertView = inflater.inflate(R.layout.adapter_player_list, parent, false);

        Player currentPlayer = getPlayer(position);
        setDatasOnView(currentPlayer, row);
        setBonus(row, getBonusById(currentPlayer.getId()));

        return row;
    }

    @SuppressLint("SetTextI18n")
    public void setDatasOnView(Player player, View view){
        TextView playerName = view.findViewById(R.id.adapter_player_fight_text_pseudo);
        playerName.setText(player.get_name());

        TextView playerAttack = view.findViewById(R.id.adapter_player_fight_text_attack);
        playerAttack.setText(player.get_attack()+"");

        TextView playerDefense = view.findViewById(R.id.adapter_player_fight_text_defense);
        playerDefense.setText(player.get_defense()+"");

        TextView playerLife = view.findViewById(R.id.adapter_player_fight_text_life);
        playerLife.setText(player.get_life()+"");

        ImageView playerRace= view.findViewById(R.id.adapter_player_fight_image_race);
        String ressourceName = "race_" + player.get_race();
        int resId = context.getResources().getIdentifier(ressourceName.toLowerCase(), "drawable", context.getPackageName());
        playerRace.setImageResource(resId);
    }

    public void setBonus(View view, Bonus bonus) {
        TextView textAttack = view.findViewById(R.id.adapter_player_fight_bonus_attack);
        TextView textDefense = view.findViewById(R.id.adapter_player_fight_bonus_defense);

        if (bonus != null) {
            if (bonus.getAttack_bonus() != 0) {
                textAttack.setText(bonus.getAttack_bonus_string());
                textAttack.setVisibility(View.VISIBLE);
            }
            if (bonus.getDefense_bonus() != 0) {
                textDefense.setText(bonus.getDefense_bonus_string());
                textDefense.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Player> getPlayers_list() {
        return players_list;
    }

    public void setPlayers_list(List<Player> players_list) {
        this.players_list = players_list;
    }

    public ArrayList<Bonus> getBonus_list() {
        return bonus_list;
    }

    public void setBonus_list(ArrayList<Bonus> bonus_list) {
        this.bonus_list = bonus_list;
    }


}
