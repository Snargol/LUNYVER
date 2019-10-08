package fr.snargol.lunyver.model;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import fr.snargol.lunyver.R;

public class PopUpEditPlayer  extends Dialog {

    private Button buttonValid;

    private ImageButton resetPlayer;
    private ImageButton rightArrow;
    private ImageButton leftArrow;

    private TextView title;
    private TextView pseudo;

    private EditText inputAttackBonus;
    private EditText inputDefenseBonus;
    private EditText inputAttack;
    private EditText inputDefense;
    private EditText inputLife;

    private ImageView raceImage;
    private ImageView classImage;

    private int indexToDisplay = 0;
    private int sizeList;
    private ArrayList<Player> player_list;

    public PopUpEditPlayer(Activity activity, ArrayList<Player> list, int i) {
        super(activity, R.style.Theme_AppCompat_Dialog);
        setContentView(R.layout.template_pop_up_edit_player_fight);
        setPlayer_list(list);
        setSizeList(getPlayer_list().size());
        setIndexToDisplay(i);
        setDatas();
        setOnClick();

    }

    public void build(){
        show();
    }

    private void setOnClick() {
        getLeftArrow().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDatasToPlayer();
                decaleIndexToTheLeft();
                DisplayPlayer(getPlayer_list().get(getIndexToDisplay()));
            }
        });

        getRightArrow().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDatasToPlayer();
                decaleIndexToTheRight();
                DisplayPlayer(getPlayer_list().get(getIndexToDisplay()));
            }
        });
    }

    private void setDatas() {
        setButtonValid((Button) findViewById(R.id.pop_up_edit_player_button_valid));
        setResetPlayer((ImageButton) findViewById(R.id.pop_up_edit_player_button_reset));
        setRightArrow((ImageButton) findViewById(R.id.pop_up_edit_player_button_right_arrow));
        setLeftArrow((ImageButton) findViewById(R.id.pop_up_edit_player_button_left_arrow));
        setTitle((TextView) findViewById(R.id.pop_up_edit_player_text_title));
        setPseudo((TextView) findViewById(R.id.pop_up_edit_player_text_pseudo));
        setInputAttackBonus((EditText) findViewById(R.id.pop_up_edit_player_text_bonus_off));
        setInputDefenseBonus((EditText) findViewById(R.id.pop_up_edit_player_text_bonus_def));
        setInputAttack((EditText) findViewById(R.id.pop_up_edit_player_text_off));
        setInputDefense((EditText) findViewById(R.id.pop_up_edit_player_text_def));
        setInputLife((EditText) findViewById(R.id.pop_up_edit_player_text_life));
        setRaceImage((ImageView) findViewById(R.id.pop_up_edit_player_image_race));
        setClassImage((ImageView) findViewById(R.id.pop_up_edit_player_image_class));

        DisplayPlayer(getPlayer_list().get(getIndexToDisplay()));
    }



    @SuppressLint("SetTextI18n")
    public void DisplayPlayer(Player player) {
        getPseudo().setText(player.get_name());
        getInputAttack().setText(Integer.toString(player.get_attack()));
        getInputDefense().setText(Integer.toString(player.get_defense()));
        getInputLife().setText(Integer.toString(player.get_life()));
        getInputAttackBonus().setText(Integer.toString(player.get_bonus_attack()));
        getInputDefenseBonus().setText(Integer.toString(player.get_bonus_defense()));
        getRaceImage().setImageResource(player.getRessourceIdRace(getContext()));
        getClassImage().setImageResource(player.getRessourceIdClass(getContext()));
    }

//    On enregistre les données pour modifier le joueur
    public void setDatasToPlayer() {
        getPlayer_list().get(getIndexToDisplay()).set_attack(Integer.parseInt(getInputAttack().getText().toString()));
        getPlayer_list().get(getIndexToDisplay()).set_defense(Integer.parseInt(getInputDefense().getText().toString()));
        getPlayer_list().get(getIndexToDisplay()).set_life(Integer.parseInt(getInputLife().getText().toString()));
        getPlayer_list().get(getIndexToDisplay()).set_bonus_attack(Integer.parseInt(getInputAttackBonus().getText().toString()));
        getPlayer_list().get(getIndexToDisplay()).set_bonus_defense(Integer.parseInt(getInputDefenseBonus().getText().toString()));
    }

    public Button getButtonValid() {
        return buttonValid;
    }

    public void setButtonValid(Button buttonValid) {
        this.buttonValid = buttonValid;
    }

    public ImageButton getResetPlayer() {
        return resetPlayer;
    }

    public void setResetPlayer(ImageButton resetPlayer) {
        this.resetPlayer = resetPlayer;
    }

    public ImageButton getRightArrow() {
        return rightArrow;
    }

    public void setRightArrow(ImageButton rightArrow) {
        this.rightArrow = rightArrow;
    }

    public ImageButton getLeftArrow() {
        return leftArrow;
    }

    public void setLeftArrow(ImageButton leftArrow) {
        this.leftArrow = leftArrow;
    }

    public TextView getTitle() {
        return title;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }

    public TextView getPseudo() {
        return pseudo;
    }

    public void setPseudo(TextView pseudo) {
        this.pseudo = pseudo;
    }

    public EditText getInputAttackBonus() {
        return inputAttackBonus;
    }

    public void setInputAttackBonus(EditText inputAttackBonus) {
        this.inputAttackBonus = inputAttackBonus;
    }

    public EditText getInputDefenseBonus() {
        return inputDefenseBonus;
    }

    public void setInputDefenseBonus(EditText inputDefenseBonus) {
        this.inputDefenseBonus = inputDefenseBonus;
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

    public ImageView getRaceImage() {
        return raceImage;
    }

    public void setRaceImage(ImageView raceImage) {
        this.raceImage = raceImage;
    }

    public ImageView getClassImage() {
        return classImage;
    }

    public void setClassImage(ImageView classImage) {
        this.classImage = classImage;
    }

    public int getIndexToDisplay() {
        return indexToDisplay;
    }

    public void setIndexToDisplay(int indexToDisplay) {
        if (indexToDisplay >= 0 && indexToDisplay <= getSizeList()-1)
            this.indexToDisplay = indexToDisplay;
        else
            this.indexToDisplay = 0;
    }

    public void decaleIndexToTheLeft() {
        if (getIndexToDisplay() - 1 < 0){
            setIndexToDisplay(getSizeList()-1);
        }
        else {
            setIndexToDisplay(getIndexToDisplay()-1);
        }
    }

    public void decaleIndexToTheRight() {
        if (getIndexToDisplay() + 1 > getSizeList() -1){
            setIndexToDisplay(0);
        }
        else {
            setIndexToDisplay(getIndexToDisplay()+1);
        }
    }

    public int getSizeList() {
        return sizeList;
    }

    public void setSizeList(int sizeList) {
        this.sizeList = sizeList;
    }

    public ArrayList<Player> getPlayer_list() {
        return player_list;
    }

    public void setPlayer_list(ArrayList<Player> player_list) {
        this.player_list = player_list;
    }
}
