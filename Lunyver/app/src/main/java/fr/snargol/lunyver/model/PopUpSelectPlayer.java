package fr.snargol.lunyver.model;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import fr.snargol.lunyver.R;
import fr.snargol.lunyver.model.Enums.Race;

public class PopUpSelectPlayer extends Dialog {

    private ArrayList<Player> player_list = new ArrayList<>();
    private ArrayList<Player> player_list_off = new ArrayList<>();
    private ArrayList<Player> player_list_def = new ArrayList<>();

    private Button buttonValid;
    private Button buttonAnnul;
    private TextView title;
    private boolean isAttack;

    private ImageView centaurValid;
    private ImageView elfValid;
    private ImageView fairyValid;
    private ImageView trollValid;
    private ImageView gnomeValid;
    private ImageView werewolfValid;
    private ImageView mobValid;
    private ImageView bossValid;

    private ImageButton centaurButton;
    private ImageButton elfButton;
    private ImageButton fairyButton;
    private ImageButton trollButton;
    private ImageButton gnomeButton;
    private ImageButton werewolfButton;
    private ImageButton mobButton;
    private ImageButton bossButton;


    private TextView centaurPseudo;
    private TextView elfPseudo;
    private TextView fairyPseudo;
    private TextView trollPseudo;
    private TextView gnomePseudo;
    private TextView werewolfPseudo;
    private TextView mobPseudo;
    private TextView bossPseudo;

//    isAttack : True(this is the popup for select the attack players) / False(popup for Def players)
//    player_list_section : list of attack player or def player
//    player_list : list of all players
    public PopUpSelectPlayer(Activity activity, ArrayList<Player> player_list, ArrayList<Player> player_list_section, boolean isAttack) {
        super(activity, R.style.Theme_AppCompat_Dialog);
        setContentView(R.layout.template_pop_up_choose_text);
        if (isAttack())
            setPlayer_list_off(player_list_section);
        else
            setPlayer_list_def(player_list_section);
        setAttack(isAttack);
        setDatas();
        setPseudo(player_list);
        setOnClicks();
    }

    public void setDatas() {
        setButtonAnnul((Button) findViewById(R.id.pop_up_but_annul));
        setButtonValid((Button) findViewById(R.id.pop_up_but_valid));

        setCentaurValid((ImageView) findViewById(R.id.pop_up_centaur_valid));
        setElfValid((ImageView) findViewById(R.id.pop_up_elf_valid));
        setFairyValid((ImageView) findViewById(R.id.pop_up_fairy_valid));
        setTrollValid((ImageView) findViewById(R.id.pop_up_troll_valid));
        setGnomeValid((ImageView) findViewById(R.id.pop_up_gnome_valid));
        setWerewolfValid((ImageView) findViewById(R.id.pop_up_werewolf_valid));
        setMobValid((ImageView) findViewById(R.id.pop_up_mob_valid));
        setBossValid((ImageView) findViewById(R.id.pop_up_boss_valid));

        setCentaurButton((ImageButton) findViewById(R.id.pop_up_image_centaur));
        setElfButton((ImageButton) findViewById(R.id.pop_up_image_elf));
        setFairyButton((ImageButton) findViewById(R.id.pop_up_image_fairy));
        setTrollButton((ImageButton) findViewById(R.id.pop_up_image_troll));
        setGnomeButton((ImageButton) findViewById(R.id.pop_up_image_gnome));
        setWerewolfButton((ImageButton) findViewById(R.id.pop_up_image_werewolf));
        setMobButton((ImageButton) findViewById(R.id.pop_up_image_mob));
        setBossButton((ImageButton) findViewById(R.id.pop_up_image_boss));

        setCentaurPseudo((TextView) findViewById(R.id.pop_up_pseudo_centaur));
        setElfPseudo((TextView) findViewById(R.id.pop_up_pseudo_elf));
        setFairyPseudo((TextView) findViewById(R.id.pop_up_pseudo_fairy));
        setTrollPseudo((TextView) findViewById(R.id.pop_up_pseudo_troll));
        setGnomePseudo((TextView) findViewById(R.id.pop_up_pseudo_gnome));
        setWerewolfPseudo((TextView) findViewById(R.id.pop_up_pseudo_werewolf));
    }

    private void setOnClicks() {
        getCentaurButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPlayerSelect(getPlayerByRace(Race.CENTAUR), getListToExclude())) {
                    if (getCentaurValid().getVisibility() == View.INVISIBLE) {
                        getCentaurValid().setVisibility(View.VISIBLE);
                        getGoodList().add(getPlayerByRace(Race.WEREWOLF));
                    }
                    else {
                        getCentaurValid().setVisibility(View.INVISIBLE);
                        if (getPlayerPositionByRace(getGoodList(), Race.CENTAUR) >= 0)
                            getGoodList().remove(getPlayerPositionByRace(getGoodList(), Race.CENTAUR));
                    }
                }
            }
        });
        //TODO appliquer les modifs du centaur aux autres
        getElfButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPlayerSelect(getPlayerByRace(Race.ELF), getListToExclude())) {
                    getElfValid().setVisibility(View.VISIBLE);
                    getGoodList().add(getPlayerByRace(Race.ELF));
                }
            }
        });
        getFairyButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPlayerSelect(getPlayerByRace(Race.FAIRY), getListToExclude())) {
                    getFairyValid().setVisibility(View.VISIBLE);
                    getGoodList().add(getPlayerByRace(Race.FAIRY));
                }
            }
        });
        getTrollButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPlayerSelect(getPlayerByRace(Race.TROLL), getListToExclude())) {
                    getTrollValid().setVisibility(View.VISIBLE);
                    getGoodList().add(getPlayerByRace(Race.TROLL));
                }
            }
        });
        getGnomeButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPlayerSelect(getPlayerByRace(Race.GNOME), getListToExclude())) {
                    getGnomeValid().setVisibility(View.VISIBLE);
                    getGoodList().add(getPlayerByRace(Race.GNOME));
                }
            }
        });
        getWerewolfButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPlayerSelect(getPlayerByRace(Race.WEREWOLF), getListToExclude())) {
                    getWerewolfValid().setVisibility(View.VISIBLE);
                    getGoodList().add(getPlayerByRace(Race.WEREWOLF));
                }
            }
        });
        getMobButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMobValid().setVisibility(View.VISIBLE);
            }
        });
        getBossButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBossValid().setVisibility(View.VISIBLE);
//                getGoodList().add()
            }
        });
    }

    private ArrayList<Player> getListToExclude() {
        if (!isAttack()){
            return getPlayer_list_off();
        }
        else {
            return getPlayer_list_def();
        }
    }

    private ArrayList<Player> getGoodList() {
        if (isAttack()){
            return getPlayer_list_off();
        }
        else {
            return getPlayer_list_def();
        }
    }

    private Player getPlayerByRace(Race race) {
        for (Player player: getPlayer_list()) {
            if (player.get_race() == race)
                    return player;
        }
        return null;
    }

    private int getPlayerPositionByRace(ArrayList<Player> list, Race race) {
        int i = 0;
        for (Player player: list) {
            if (player.get_race() == race) {
                return i;
            }
            i++;
        }
        return -1;
    }

//    if the player is already on the other list (off or def) then he is not selectable
//    if the player is null, there is not player with this race so it is not selectable
    private boolean checkPlayerSelect(Player player, ArrayList<Player> list_to_exclude) {
        if (player == null)
            return false;
        for (Player player_in_list:list_to_exclude) {
            if (player_in_list.get_race() == player.get_race())
                return false;
        }
        return true;
    }

    public void setPseudo(ArrayList<Player> list) {
        for (Player player: list) {
            switch(player.get_race()) {
                case CENTAUR:
                    getCentaurPseudo().setText(player.get_name());
                    break;
                case ELF:
                    getElfPseudo().setText(player.get_name());
                    break;
                case FAIRY:
                    getFairyPseudo().setText(player.get_name());
                    break;
                case GNOME:
                    getGnomePseudo().setText(player.get_name());
                    break;
                case TROLL:
                    getTrollPseudo().setText(player.get_name());
                    break;
                case WEREWOLF:
                    getWerewolfPseudo().setText(player.get_name());
                    break;
                default:
                    break;
            }
        }
    }

    public void build(){
        show();
    }


    public Button getButtonValid() {
        return buttonValid;
    }

    public void setButtonValid(Button buttonValid) {
        this.buttonValid = buttonValid;
    }

    public Button getButtonAnnul() {
        return buttonAnnul;
    }

    public void setButtonAnnul(Button buttonAnnul) {
        this.buttonAnnul = buttonAnnul;
    }

    public TextView getTitle() {
        return title;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }

    public ImageView getCentaurValid() {
        return centaurValid;
    }

    public void setCentaurValid(ImageView centaurValid) {
        this.centaurValid = centaurValid;
    }

    public ImageView getElfValid() {
        return elfValid;
    }

    public void setElfValid(ImageView elfValid) {
        this.elfValid = elfValid;
    }

    public ImageView getFairyValid() {
        return fairyValid;
    }

    public void setFairyValid(ImageView fairyValid) {
        this.fairyValid = fairyValid;
    }

    public ImageView getTrollValid() {
        return trollValid;
    }

    public void setTrollValid(ImageView trollValid) {
        this.trollValid = trollValid;
    }

    public ImageView getGnomeValid() {
        return gnomeValid;
    }

    public void setGnomeValid(ImageView gnomeValid) {
        this.gnomeValid = gnomeValid;
    }

    public ImageView getWerewolfValid() {
        return werewolfValid;
    }

    public void setWerewolfValid(ImageView werewolfValid) {
        this.werewolfValid = werewolfValid;
    }

    public ImageView getMobValid() {
        return mobValid;
    }

    public void setMobValid(ImageView mobValid) {
        this.mobValid = mobValid;
    }

    public ImageView getBossValid() {
        return bossValid;
    }

    public void setBossValid(ImageView bossValid) {
        this.bossValid = bossValid;
    }

    public TextView getCentaurPseudo() {
        return centaurPseudo;
    }

    public void setCentaurPseudo(TextView centaurPseudo) {
        this.centaurPseudo = centaurPseudo;
    }

    public TextView getElfPseudo() {
        return elfPseudo;
    }

    public void setElfPseudo(TextView elfPseudo) {
        this.elfPseudo = elfPseudo;
    }

    public TextView getFairyPseudo() {
        return fairyPseudo;
    }

    public void setFairyPseudo(TextView fairyPseudo) {
        this.fairyPseudo = fairyPseudo;
    }

    public TextView getTrollPseudo() {
        return trollPseudo;
    }

    public void setTrollPseudo(TextView trollPseudo) {
        this.trollPseudo = trollPseudo;
    }

    public TextView getGnomePseudo() {
        return gnomePseudo;
    }

    public void setGnomePseudo(TextView gnomePseudo) {
        this.gnomePseudo = gnomePseudo;
    }

    public TextView getWerewolfPseudo() {
        return werewolfPseudo;
    }

    public void setWerewolfPseudo(TextView werewolfPseudo) {
        this.werewolfPseudo = werewolfPseudo;
    }

    public TextView getMobPseudo() {
        return mobPseudo;
    }

    public void setMobPseudo(TextView mobPseudo) {
        this.mobPseudo = mobPseudo;
    }

    public TextView getBossPseudo() {
        return bossPseudo;
    }

    public void setBossPseudo(TextView bossPseudo) {
        this.bossPseudo = bossPseudo;
    }

    public ArrayList<Player> getPlayer_list() {
        return player_list;
    }

    public void setPlayer_list(ArrayList<Player> player_list) {
        this.player_list = player_list;
    }

    public ArrayList<Player> getPlayer_list_off() {
        return player_list_off;
    }

    public void setPlayer_list_off(ArrayList<Player> player_list_off) {
        this.player_list_off = player_list_off;
    }

    public ArrayList<Player> getPlayer_list_def() {
        return player_list_def;
    }

    public void setPlayer_list_def(ArrayList<Player> player_list_def) {
        this.player_list_def = player_list_def;
    }

    public ImageButton getCentaurButton() {
        return centaurButton;
    }

    public void setCentaurButton(ImageButton centaurButton) {
        this.centaurButton = centaurButton;
    }

    public ImageButton getElfButton() {
        return elfButton;
    }

    public void setElfButton(ImageButton elfButton) {
        this.elfButton = elfButton;
    }

    public ImageButton getFairyButton() {
        return fairyButton;
    }

    public void setFairyButton(ImageButton fairyButton) {
        this.fairyButton = fairyButton;
    }

    public ImageButton getTrollButton() {
        return trollButton;
    }

    public void setTrollButton(ImageButton trollButton) {
        this.trollButton = trollButton;
    }

    public ImageButton getGnomeButton() {
        return gnomeButton;
    }

    public void setGnomeButton(ImageButton gnomeButton) {
        this.gnomeButton = gnomeButton;
    }

    public ImageButton getWerewolfButton() {
        return werewolfButton;
    }

    public void setWerewolfButton(ImageButton werewolfButton) {
        this.werewolfButton = werewolfButton;
    }

    public ImageButton getMobButton() {
        return mobButton;
    }

    public void setMobButton(ImageButton mobButton) {
        this.mobButton = mobButton;
    }

    public ImageButton getBossButton() {
        return bossButton;
    }

    public void setBossButton(ImageButton bossButton) {
        this.bossButton = bossButton;
    }

    public boolean isAttack() {
        return isAttack;
    }

    public void setAttack(boolean attack) {
        isAttack = attack;
    }
}
