package fr.snargol.lunyver.model;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import fr.snargol.lunyver.R;

public class PopUpEditMob extends Dialog {
    Button buttonValid;
    Button buttonAnnul;
    Button buttonUpAttack;
    Button buttonDownAttack;
    Button buttonUpDefense;
    Button buttonDownDefense;
    Button buttonUpLife;
    Button buttonDownLife;
    TextView title;
    TextView textAttack;
    TextView textDefense;
    TextView textLife;

    ArrayList<Player> mob_list;
    int position;

    public PopUpEditMob(Activity activity, ArrayList<Player> mob_list, int position) {
        super(activity, R.style.Theme_AppCompat_Dialog);
        setContentView(R.layout.pop_up_edit_monster);
        setMob_list(mob_list);
        setPosition(position);
        setDatas();
        setOnClick();
    }

    public void build(){
        show();
    }

    private void setDatas() {
        setButtonValid((Button) findViewById(R.id.pop_up_edit_mob_confirm_but_valid));
        setButtonAnnul((Button) findViewById(R.id.pop_up_edit_mob_confirm_but_annul));
        setButtonDownAttack((Button) findViewById(R.id.pop_up_edit_mob_but_down_attack));
        setButtonDownDefense((Button) findViewById(R.id.pop_up_edit_mob_but_down_defense));
        setButtonDownLife((Button) findViewById(R.id.pop_up_edit_mob_but_down_life));
        setButtonUpAttack((Button) findViewById(R.id.pop_up_edit_mob_but_up_attack));
        setButtonUpDefense((Button) findViewById(R.id.pop_up_edit_mob_but_up_defense));
        setButtonUpLife((Button) findViewById(R.id.pop_up_edit_mob_but_up_life));
        setTitle((TextView) findViewById(R.id.pop_up_edit_mob_text_name));
        setTextAttack((TextView) findViewById(R.id.pop_up_edit_mob_attack));
        setTextDefense((TextView) findViewById(R.id.pop_up_edit_mob_defense));
        setTextLife((TextView) findViewById(R.id.pop_up_edit_mob_life));

        getTextAttack().setText(String.valueOf(getMob_list().get(getPosition()).get_attack()));
        getTextDefense().setText(String.valueOf(getMob_list().get(getPosition()).get_defense()));
        getTextLife().setText(String.valueOf(getMob_list().get(getPosition()).get_life()));
    }

    private void setOnClick() {
        getButtonUpAttack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMob_list().get(getPosition()).add_attack(1);
                getTextAttack().setText(String.valueOf(getMob_list().get(getPosition()).get_attack()));
            }
        });
        getButtonDownAttack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMob_list().get(getPosition()).add_attack(-1);
                getTextAttack().setText(String.valueOf(getMob_list().get(getPosition()).get_attack()));
            }
        });

        getButtonUpDefense().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMob_list().get(getPosition()).add_defense(1);
                getTextDefense().setText(String.valueOf(getMob_list().get(getPosition()).get_defense()));
            }
        });
        getButtonDownDefense().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMob_list().get(getPosition()).add_defense(-1);
                getTextDefense().setText(String.valueOf(getMob_list().get(getPosition()).get_defense()));
            }
        });

        getButtonUpLife().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMob_list().get(getPosition()).add_life(1);
                getTextLife().setText(String.valueOf(getMob_list().get(getPosition()).get_life()));
            }
        });
        getButtonDownLife().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMob_list().get(getPosition()).add_life(-1);
                getTextLife().setText(String.valueOf(getMob_list().get(getPosition()).get_life()));
            }
        });
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

    public Button getButtonUpAttack() {
        return buttonUpAttack;
    }

    public void setButtonUpAttack(Button buttonUpAttack) {
        this.buttonUpAttack = buttonUpAttack;
    }

    public Button getButtonDownAttack() {
        return buttonDownAttack;
    }

    public void setButtonDownAttack(Button buttonDownAttack) {
        this.buttonDownAttack = buttonDownAttack;
    }

    public Button getButtonUpDefense() {
        return buttonUpDefense;
    }

    public void setButtonUpDefense(Button buttonUpDefense) {
        this.buttonUpDefense = buttonUpDefense;
    }

    public Button getButtonDownDefense() {
        return buttonDownDefense;
    }

    public void setButtonDownDefense(Button buttonDownDefense) {
        this.buttonDownDefense = buttonDownDefense;
    }

    public Button getButtonUpLife() {
        return buttonUpLife;
    }

    public void setButtonUpLife(Button buttonUpLife) {
        this.buttonUpLife = buttonUpLife;
    }

    public Button getButtonDownLife() {
        return buttonDownLife;
    }

    public void setButtonDownLife(Button buttonDownLife) {
        this.buttonDownLife = buttonDownLife;
    }

    public TextView getTitle() {
        return title;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }

    public TextView getTextAttack() {
        return textAttack;
    }

    public void setTextAttack(TextView textAttack) {
        this.textAttack = textAttack;
    }

    public TextView getTextDefense() {
        return textDefense;
    }

    public void setTextDefense(TextView textDefense) {
        this.textDefense = textDefense;
    }

    public TextView getTextLife() {
        return textLife;
    }

    public void setTextLife(TextView textLife) {
        this.textLife = textLife;
    }

    public ArrayList<Player> getMob_list() {
        return mob_list;
    }

    public void setMob_list(ArrayList<Player> mob_list) {
        this.mob_list = mob_list;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
