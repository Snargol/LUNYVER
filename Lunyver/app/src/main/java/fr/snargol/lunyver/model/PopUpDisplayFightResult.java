package fr.snargol.lunyver.model;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import fr.snargol.lunyver.R;
import fr.snargol.lunyver.controler.Adapters.PlayerAdapter;
import fr.snargol.lunyver.controler.Adapters.PlayerFightDisplayResultAdapter;

public class PopUpDisplayFightResult extends Dialog {
    TextView title;
    TextView winner_title;
    TextView continue_winner;
    Button valid;
    Button annul;
    Button select_off;
    Button select_def;
    ListView player_list_view;

    ArrayList<Player> players_list_off;
    ArrayList<Player> players_list_def;

    boolean winner_is_off;

    public PopUpDisplayFightResult(Activity activity, ArrayList<Player> players_list_off, ArrayList<Player> players_list_def, String winner) {
        super(activity, R.style.Theme_AppCompat_Dialog);
        setContentView(R.layout.template_pop_up_display_fight_result2);
        setPlayers_list_off(players_list_off);
        setPlayers_list_def(players_list_def);
        setDatas(winner);
        setOnClick();
    }

    public void setDatas(String winner) {
        setWinner_title((TextView) findViewById(R.id.pop_up_display_fight_result_text_winner));
        setContinue_winner((TextView) findViewById(R.id.pop_up_display_fight_result_text_continue));
        setValid((Button) findViewById(R.id.pop_up_display_fight_result_button_valid));
        setAnnul((Button) findViewById(R.id.pop_up_display_fight_result_button_annul));
        setSelect_def((Button) findViewById(R.id.pop_up_display_fight_result_button_select_def));
        setSelect_off((Button) findViewById(R.id.pop_up_display_fight_result_button_select_off));
        setPlayer_list_view((ListView) findViewById(R.id.pop_up_display_fight_result_list));

        getWinner_title().setText("Victoire des "+winner+" !");
        getContinue_winner().setText("Les "+winner+" remportent le combat. \n Voulez vous continuer ?");

//TODO erreur à ce niveau (crash appli lors d'un fight)
        PlayerFightDisplayResultAdapter playerAdapter = new PlayerFightDisplayResultAdapter(getContext(), players_list_off , getNames(getPlayers_list_off()));
        getPlayer_list_view().setAdapter(playerAdapter);
    }

    public String[] getNames(ArrayList<Player> list) {
        String[] names = new String[list.size()];
        int i = 0;
        for (Player player: list
        ) {
            if (i <= 5)
                names[i] = player.get_name();
            i++;
        }
        return names;
    }

    public void setOnClick() {
        getSelect_off().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        getSelect_def().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void build(){
        show();
    }

    public ListView getPlayer_list_view() {
        return player_list_view;
    }

    public void setPlayer_list_view(ListView player_list_view) {
        this.player_list_view = player_list_view;
    }

    public void setColorPseudo(ArrayList<Player> players_list) {

    }

    public void setListToView(ArrayList<Player> players_list) {

    }

    public void setButtonSelect(boolean is_button_off) {

    }

    public TextView getTitle() {
        return title;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }

    public TextView getWinner_title() {
        return winner_title;
    }

    public void setWinner_title(TextView winner_title) {
        this.winner_title = winner_title;
    }

    public TextView getContinue_winner() {
        return continue_winner;
    }

    public void setContinue_winner(TextView continue_winner) {
        this.continue_winner = continue_winner;
    }

    public Button getValid() {
        return valid;
    }

    public void setValid(Button valid) {
        this.valid = valid;
    }

    public Button getAnnul() {
        return annul;
    }

    public void setAnnul(Button annul) {
        this.annul = annul;
    }

    public Button getSelect_off() {
        return select_off;
    }

    public void setSelect_off(Button select_off) {
        this.select_off = select_off;
    }

    public Button getSelect_def() {
        return select_def;
    }

    public void setSelect_def(Button select_def) {
        this.select_def = select_def;
    }

    public boolean isWinner_is_off() {
        return winner_is_off;
    }

    public void setWinner_is_off(boolean winner_is_off) {
        this.winner_is_off = winner_is_off;
    }

    public ArrayList<Player> getPlayers_list_off() {
        return players_list_off;
    }

    public void setPlayers_list_off(ArrayList<Player> players_list_off) {
        this.players_list_off = players_list_off;
    }

    public ArrayList<Player> getPlayers_list_def() {
        return players_list_def;
    }

    public void setPlayers_list_def(ArrayList<Player> players_list_def) {
        this.players_list_def = players_list_def;
    }
}
