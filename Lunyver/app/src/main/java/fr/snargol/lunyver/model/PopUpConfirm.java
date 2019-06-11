package fr.snargol.lunyver.model;

import android.app.Activity;
import android.app.Dialog;
import android.widget.Button;
import android.widget.TextView;

import fr.snargol.lunyver.R;

public class PopUpConfirm extends Dialog {
    Button buttonValid;
    Button buttonAnnul;
    TextView title;

    public PopUpConfirm(Activity activity) {
        super(activity, R.style.Theme_AppCompat_Dialog);
        setContentView(R.layout.template_pop_up_confirm);
        setButtonValid((Button) findViewById(R.id.pop_up_confirm_but_valid));
        setButtonAnnul((Button) findViewById(R.id.pop_up_confirm_but_annul));
        setTitle((TextView) findViewById(R.id.pop_up_confirm_text_title));
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

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public void setTitle(TextView title) {
        this.title = title;
    }
}
