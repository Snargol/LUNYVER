package fr.snargol.lunyver.model;

import android.app.Activity;
import android.app.Dialog;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import fr.snargol.lunyver.R;

public class PopUpEnterBonus extends Dialog {

    private Button buttonValid;
    private Button buttonAnnul;
    private TextView title;
    private EditText inputAttack;
    private EditText inputDefense;
    private EditText inputLife;


    public PopUpEnterBonus(Activity activity, Player player) {
        super(activity, R.style.Theme_AppCompat_Dialog);
        setDatas(player);
        getInputAttack().setText("0");
        getInputDefense().setText("0");
        getInputLife().setText("0");
    }

    public void addBonus(Bonus bonus) {
        getInputAttack().setText(String.valueOf(bonus.getAttack_bonus_string()));
        getInputDefense().setText(String.valueOf(bonus.getDefense_bonus_string()));
        getInputLife().setText(String.valueOf(bonus.getLife_modif()));
    }

    public void setDatas(Player player) {
        setContentView(R.layout.template_pop_up_add_bonus);
        setButtonValid((Button) findViewById(R.id.pop_up_add_bonus_but_valid));
        setButtonAnnul((Button) findViewById(R.id.pop_up_add_bonus_but_annul));
        setTitle((TextView) findViewById(R.id.pop_up_add_bonus_title));
        setInputAttack((EditText) findViewById(R.id.pop_up_add_bonus_edit_attack));
        setInputDefense((EditText) findViewById(R.id.pop_up_add_bonus_edit_def));
        setInputLife((EditText) findViewById(R.id.pop_up_add_bonus_edit_heart));

        ImageView playerRace = findViewById(R.id.pop_up_add_bonus_image_race);
        ImageView playerClass = findViewById(R.id.pop_up_add_bonus_image_class);
        TextView pseudo = findViewById(R.id.pop_up_add_bonus_pseudo);

        String ressourceName = "race_" + player.get_race();
        int resId = getContext().getResources().getIdentifier(ressourceName.toLowerCase(), "drawable", getContext().getPackageName());
        playerRace.setImageResource(resId);

        String ressourceName2 = "class_" + player.get_class();
        int resId2 = getContext().getResources().getIdentifier(ressourceName2.toLowerCase(), "drawable", getContext().getPackageName());
        playerClass.setImageResource(resId2);

        pseudo.setText(player.get_name());
    }

    public void assertTextsNonNull() {
        if (String.valueOf(getInputAttack().getText()).equals("")){
            getInputAttack().setText("0");
        }
        if (String.valueOf(getInputDefense().getText()).equals("")){
            getInputDefense().setText("0");
        }
        if (String.valueOf(getInputLife().getText()).equals("")){
            getInputLife().setText("0");
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

    public EditText getInputAttack() {
        return inputAttack;
    }

    public void setInputAttack(EditText inputAttack) {
        this.inputAttack = inputAttack;
    }

    public EditText getInputDefense() {
        return inputDefense;
    }

    public void setInputDefense(EditText inputDefense) {
        this.inputDefense = inputDefense;
    }

    public EditText getInputLife() {
        return inputLife;
    }

    public void setInputLife(EditText inputLife) {
        this.inputLife = inputLife;
    }
}
