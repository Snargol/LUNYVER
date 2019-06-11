package fr.snargol.lunyver.model;

import android.app.Activity;
import android.app.Dialog;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import fr.snargol.lunyver.R;

public class PopUpChooseText extends Dialog {

    Button buttonValid;
    Button buttonAnnul;
    TextView title;
    EditText inputText;


    public PopUpChooseText(Activity activity) {
        super(activity, R.style.Theme_AppCompat_Dialog);
        setContentView(R.layout.template_pop_up_choose_text);
        setButtonValid((Button) findViewById(R.id.pop_up_choose_text_but_valid));
        setButtonAnnul((Button) findViewById(R.id.pop_up_choose_text_but_annul));
        setTitle((TextView) findViewById(R.id.pop_up_choose_text_title));
        setInputText((EditText) findViewById(R.id.pop_up_choose_text_text_input));
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

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public EditText getInputText() {
        return inputText;
    }

    public void setInputText(EditText inputText) {
        this.inputText = inputText;
    }
}
