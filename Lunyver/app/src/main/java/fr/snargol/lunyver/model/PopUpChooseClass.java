package fr.snargol.lunyver.model;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.media.Image;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import fr.snargol.lunyver.R;
import fr.snargol.lunyver.model.Enums.Class;

public class PopUpChooseClass extends Dialog {

    private Class currentClass;

    private ImageButton buttonThief;
    private ImageButton buttonFlash;
    private ImageButton buttonTrainer;
    private ImageButton buttonBerserk;
    private ImageButton buttonArcher;
    private ImageButton buttonProtector;

    private ImageView buttonThiefValid;
    private ImageView buttonFlashValid;
    private ImageView buttonTrainerValid;
    private ImageView buttonBerserkValid;
    private ImageView buttonArcherValid;
    private ImageView buttonProtectorValid;

    private Button buttonValid;
    private Button buttonAnnul;

    private Boolean isValid;

    public PopUpChooseClass(Activity activity) {
        super(activity, R.style.Theme_AppCompat_Dialog);
        setContentView(R.layout.template_pop_up_class);

        setButtonThief((ImageButton) findViewById(R.id.pop_up_class_but_thief));
        setButtonFlash((ImageButton) findViewById(R.id.pop_up_class_but_flash));
        setButtonTrainer((ImageButton) findViewById(R.id.pop_up_class_but_trainer));
        setButtonBerserk((ImageButton) findViewById(R.id.pop_up_class_but_berserk));
        setButtonArcher((ImageButton) findViewById(R.id.pop_up_class_but_archer));
        setButtonProtector((ImageButton) findViewById(R.id.pop_up_class_but_protector));

        setButtonThiefValid((ImageView) findViewById(R.id.pop_up_class_but_thief_valid));
        setButtonFlashValid((ImageView) findViewById(R.id.pop_up_class_but_flash_valid));
        setButtonTrainerValid((ImageView) findViewById(R.id.pop_up_class_but_trainer_valid));
        setButtonBerserkValid((ImageView) findViewById(R.id.pop_up_class_but_berserk_valid));
        setButtonArcherValid((ImageView) findViewById(R.id.pop_up_class_but_archer_valid));
        setButtonProtectorValid((ImageView) findViewById(R.id.pop_up_class_but_protector_valid));

        setButtonAnnul((Button) findViewById(R.id.pop_up_class_but_annul));
        setButtonValid((Button) findViewById(R.id.pop_up_class_but_valid));

        setOnclick();

    }

    public void setInvisible() {
        getButtonThiefValid().setVisibility(View.INVISIBLE);
        getButtonFlashValid().setVisibility(View.INVISIBLE);
        getButtonTrainerValid().setVisibility(View.INVISIBLE);
        getButtonBerserkValid().setVisibility(View.INVISIBLE);
        getButtonArcherValid().setVisibility(View.INVISIBLE);
        getButtonProtectorValid().setVisibility(View.INVISIBLE);
    }

    public void setOnclick(){
        getButtonThief().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInvisible();
                getButtonThiefValid().setVisibility(View.VISIBLE);
                setCurrentClass(Class.THIEF);
            }
        });

        getButtonFlash().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInvisible();
                getButtonFlashValid().setVisibility(View.VISIBLE);
                setCurrentClass(Class.FLASH);
            }
        });

        getButtonProtector().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInvisible();
                getButtonProtectorValid().setVisibility(View.VISIBLE);
                setCurrentClass(Class.PROTECTOR);
            }
        });

        getButtonArcher().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInvisible();
                getButtonArcherValid().setVisibility(View.VISIBLE);
                setCurrentClass(Class.ARCHER);
            }
        });

        getButtonBerserk().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInvisible();
                getButtonBerserkValid().setVisibility(View.VISIBLE);
                setCurrentClass(Class.BERSERK);
            }
        });

        getButtonTrainer().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInvisible();
                getButtonTrainerValid().setVisibility(View.VISIBLE);
                setCurrentClass(Class.TRAINER);
            }
        });
    }

    public void build() {
        show();
    }

    public Class getCurrentClass() {
        return currentClass;
    }

    public void setCurrentClass(Class currentClass) {
        this.currentClass = currentClass;
    }

    public ImageButton getButtonThief() {
        return buttonThief;
    }

    public void setButtonThief(ImageButton buttonThief) {
        this.buttonThief = buttonThief;
    }

    public ImageButton getButtonFlash() {
        return buttonFlash;
    }

    public void setButtonFlash(ImageButton buttonFlash) {
        this.buttonFlash = buttonFlash;
    }

    public ImageButton getButtonTrainer() {
        return buttonTrainer;
    }

    public void setButtonTrainer(ImageButton buttonTrainer) {
        this.buttonTrainer = buttonTrainer;
    }

    public ImageButton getButtonBerserk() {
        return buttonBerserk;
    }

    public void setButtonBerserk(ImageButton buttonBerserk) {
        this.buttonBerserk = buttonBerserk;
    }

    public ImageButton getButtonArcher() {
        return buttonArcher;
    }

    public void setButtonArcher(ImageButton buttonArcher) {
        this.buttonArcher = buttonArcher;
    }

    public ImageButton getButtonProtector() {
        return buttonProtector;
    }

    public void setButtonProtector(ImageButton buttonProtector) {
        this.buttonProtector = buttonProtector;
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

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }

    public ImageView getButtonThiefValid() {
        return buttonThiefValid;
    }

    public void setButtonThiefValid(ImageView buttonThiefValid) {
        this.buttonThiefValid = buttonThiefValid;
    }

    public ImageView getButtonFlashValid() {
        return buttonFlashValid;
    }

    public void setButtonFlashValid(ImageView buttonFlashValid) {
        this.buttonFlashValid = buttonFlashValid;
    }

    public ImageView getButtonTrainerValid() {
        return buttonTrainerValid;
    }

    public void setButtonTrainerValid(ImageView buttonTrainerValid) {
        this.buttonTrainerValid = buttonTrainerValid;
    }

    public ImageView getButtonBerserkValid() {
        return buttonBerserkValid;
    }

    public void setButtonBerserkValid(ImageView buttonBerserkValid) {
        this.buttonBerserkValid = buttonBerserkValid;
    }

    public ImageView getButtonArcherValid() {
        return buttonArcherValid;
    }

    public void setButtonArcherValid(ImageView buttonArcherValid) {
        this.buttonArcherValid = buttonArcherValid;
    }

    public ImageView getButtonProtectorValid() {
        return buttonProtectorValid;
    }

    public void setButtonProtectorValid(ImageView buttonProtectorValid) {
        this.buttonProtectorValid = buttonProtectorValid;
    }
}
