package fr.snargol.lunyver.model;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import fr.snargol.lunyver.R;
import fr.snargol.lunyver.controler.Adapters.PlayerAdapter;
import fr.snargol.lunyver.model.Enums.Class;
import fr.snargol.lunyver.model.Enums.Race;
import fr.snargol.lunyver.model.Player;
import fr.snargol.lunyver.model.PopUpConfirm;
import fr.snargol.lunyver.model.PopUpEditMob;

public class PopUpSelectMob extends Dialog {
    ArrayList<Player> mob_list = new ArrayList<>();
    ArrayList<Integer> idsToRemove = new ArrayList<>();
    Activity activity;
    ListView mob_list_view;
    Boolean needSave = true;
    ImageButton buttonReturn;
    ImageButton buttonAdd;


    public PopUpSelectMob(Activity activity, ArrayList<Player> mob_list) {
        super(activity, R.style.Theme_AppCompat_Dialog);
        setContentView(R.layout.activity_mobs);
        setMob_list(mob_list);
        setActivity(activity);
        setButtonReturn((ImageButton) findViewById(R.id.activity_mob_button_back));
        setButtonAdd((ImageButton) findViewById(R.id.activity_mob_button_add));
        setMob_list_view((ListView) findViewById(R.id.activity_mob_list_mob));

        PlayerAdapter playerAdapter = new PlayerAdapter(getContext(), getMob_list(), getNames());
        getMob_list_view().setAdapter(playerAdapter);

        getMob_list_view().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                createPopUp(position);
            }
            });

        ImageButton buttonAddMonster = findViewById(R.id.activity_mob_button_add);
        buttonAddMonster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMob_list().add(new Player("Mob", 1, 0, Class.NO_CLASS, Race.MONSTER));
                createPopUp(getMob_list().size()-1);
            }
        });

        getMob_list_view().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final PopUpConfirm popUp = new PopUpConfirm(getActivity());
                popUp.setTitle("Voulez vous supprimer le monstre " + getMob_list().get(position).get_name() + " ?");
                popUp.getButtonAnnul().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popUp.dismiss();
                    }
                });
                popUp.getButtonValid().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), getMob_list().get(position).get_name() + " supprimé", Toast.LENGTH_SHORT).show();
                        getIdsToRemove().add(getMob_list().get(position).getId());
                        getMob_list().remove(position);
                        setNeedSave(true);
                        PlayerAdapter playerAdapter = new PlayerAdapter(getActivity(), getMob_list(), getNames());
                        getMob_list_view().setAdapter(playerAdapter);
                        popUp.dismiss();
                    }
                });
                popUp.build();

                return false;
            }
        });
    }

    public void createPopUp(final int position){
            final PopUpEditMob popUp = new PopUpEditMob(getActivity(), getMob_list(), position);
            popUp.getButtonAnnul().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popUp.dismiss();
                }
            });
            popUp.getButtonValid().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setNeedSave(true);
                    PlayerAdapter playerAdapter = new PlayerAdapter(getActivity(), getMob_list(), getNames());
                    getMob_list_view().setAdapter(playerAdapter);
                    popUp.dismiss();
                }
            });
            popUp.build();
    }

    public void build(){
        show();
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

    public Boolean getNeedSave() {
        return needSave;
    }

    public void setNeedSave(Boolean needSave) {
        this.needSave = needSave;
    }

    public ImageButton getButtonReturn() {
        return buttonReturn;
    }

    public void setButtonReturn(ImageButton buttonReturn) {
        this.buttonReturn = buttonReturn;
    }

    public ImageButton getButtonAdd() {
        return buttonAdd;
    }

    public void setButtonAdd(ImageButton buttonAdd) {
        this.buttonAdd = buttonAdd;
    }

    public ArrayList<Integer> getIdsToRemove() {
        return idsToRemove;
    }

    public void setIdsToRemove(ArrayList<Integer> idsToRemove) {
        this.idsToRemove = idsToRemove;
    }
}
