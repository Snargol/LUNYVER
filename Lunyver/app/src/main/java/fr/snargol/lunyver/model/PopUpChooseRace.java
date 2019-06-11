package fr.snargol.lunyver.model;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import fr.snargol.lunyver.R;
import fr.snargol.lunyver.model.Enums.Race;

public class PopUpChooseRace extends Dialog{
    private Race currentRace;

    private ImageButton buttonCentaur;
    private ImageButton buttonGnome;
    private ImageButton buttonWerewolf;
    private ImageButton buttonFairy;
    private ImageButton buttonElf;
    private ImageButton buttonTroll;

    private ImageView buttonCentaurValid;
    private ImageView buttonGnomeValid;
    private ImageView buttonWerewolfValid;
    private ImageView buttonFairyValid;
    private ImageView buttonElfValid;
    private ImageView buttonTrollValid;

    private Button buttonValid;
    private Button buttonAnnul;

    public PopUpChooseRace(Activity activity) {
        super(activity, R.style.Theme_AppCompat_Dialog);
        setContentView(R.layout.template_pop_up_race);

        setButtonCentaur((ImageButton) findViewById(R.id.pop_up_race_but_centaur));
        setButtonElf((ImageButton) findViewById(R.id.pop_up_race_but_elf));
        setButtonFairy((ImageButton) findViewById(R.id.pop_up_race_but_fairy));
        setButtonGnome((ImageButton) findViewById(R.id.pop_up_race_but_gnome));
        setButtonTroll((ImageButton) findViewById(R.id.pop_up_race_but_troll));
        setButtonWerewolf((ImageButton) findViewById(R.id.pop_up_race_but_werewolf));

        setButtonCentaurValid((ImageView) findViewById(R.id.pop_up_race_but_centaur_valid));
        setButtonElfValid((ImageView) findViewById(R.id.pop_up_race_but_elf_valid));
        setButtonFairyValid((ImageView) findViewById(R.id.pop_up_race_but_fairy_valid));
        setButtonGnomeValid((ImageView) findViewById(R.id.pop_up_race_but_gnome_valid));
        setButtonTrollValid((ImageView) findViewById(R.id.pop_up_race_but_troll_valid));
        setButtonWerewolfValid((ImageView) findViewById(R.id.pop_up_race_but_werewolf_valid));

        setButtonAnnul((Button) findViewById(R.id.pop_up_race_but_annul));
        setButtonValid((Button) findViewById(R.id.pop_up_race_but_valid));

        setOnclick();

    }

    public void setInvisible() {
        getButtonCentaurValid().setVisibility(View.INVISIBLE);
        getButtonFairyValid().setVisibility(View.INVISIBLE);
        getButtonElfValid().setVisibility(View.INVISIBLE);
        getButtonGnomeValid().setVisibility(View.INVISIBLE);
        getButtonTrollValid().setVisibility(View.INVISIBLE);
        getButtonWerewolfValid().setVisibility(View.INVISIBLE);

    }

    public void build() {
    show();
}

    public void setOnclick(){

        getButtonCentaur().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInvisible();
                getButtonCentaurValid().setVisibility(View.VISIBLE);
                setCurrentRace(Race.CENTAUR);
            }
        });
        getButtonFairy().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInvisible();
                getButtonFairyValid().setVisibility(View.VISIBLE);
                setCurrentRace(Race.FAIRY);
            }
        });
        getButtonElf().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInvisible();
                getButtonElfValid().setVisibility(View.VISIBLE);
                setCurrentRace(Race.ELF);
            }
        });
        getButtonGnome().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInvisible();
                getButtonGnomeValid().setVisibility(View.VISIBLE);
                setCurrentRace(Race.GNOME);
            }
        });
        getButtonTroll().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInvisible();
                getButtonTrollValid().setVisibility(View.VISIBLE);
                setCurrentRace(Race.TROLL);
            }
        });
        getButtonWerewolf().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInvisible();
                getButtonWerewolfValid().setVisibility(View.VISIBLE);
                setCurrentRace(Race.WEREWOLF);
            }
        });
    }

    public Race getCurrentRace() {
        return currentRace;
    }

    public void setCurrentRace(Race currentRace) {
        this.currentRace = currentRace;
    }

    public ImageButton getButtonCentaur() {
        return buttonCentaur;
    }

    public void setButtonCentaur(ImageButton buttonCentaur) {
        this.buttonCentaur = buttonCentaur;
    }

    public ImageButton getButtonGnome() {
        return buttonGnome;
    }

    public void setButtonGnome(ImageButton buttonGnome) {
        this.buttonGnome = buttonGnome;
    }

    public ImageButton getButtonWerewolf() {
        return buttonWerewolf;
    }

    public void setButtonWerewolf(ImageButton buttonWerewolf) {
        this.buttonWerewolf = buttonWerewolf;
    }

    public ImageButton getButtonFairy() {
        return buttonFairy;
    }

    public void setButtonFairy(ImageButton buttonFairy) {
        this.buttonFairy = buttonFairy;
    }

    public ImageButton getButtonElf() {
        return buttonElf;
    }

    public void setButtonElf(ImageButton buttonElf) {
        this.buttonElf = buttonElf;
    }

    public ImageButton getButtonTroll() {
        return buttonTroll;
    }

    public void setButtonTroll(ImageButton buttonTroll) {
        this.buttonTroll = buttonTroll;
    }

    public ImageView getButtonCentaurValid() {
        return buttonCentaurValid;
    }

    public void setButtonCentaurValid(ImageView buttonCentaurValid) {
        this.buttonCentaurValid = buttonCentaurValid;
    }

    public ImageView getButtonGnomeValid() {
        return buttonGnomeValid;
    }

    public void setButtonGnomeValid(ImageView buttonGnomeValid) {
        this.buttonGnomeValid = buttonGnomeValid;
    }

    public ImageView getButtonWerewolfValid() {
        return buttonWerewolfValid;
    }

    public void setButtonWerewolfValid(ImageView buttonWerewolfValid) {
        this.buttonWerewolfValid = buttonWerewolfValid;
    }

    public ImageView getButtonFairyValid() {
        return buttonFairyValid;
    }

    public void setButtonFairyValid(ImageView buttonFairyValid) {
        this.buttonFairyValid = buttonFairyValid;
    }

    public ImageView getButtonElfValid() {
        return buttonElfValid;
    }

    public void setButtonElfValid(ImageView buttonElfValid) {
        this.buttonElfValid = buttonElfValid;
    }

    public ImageView getButtonTrollValid() {
        return buttonTrollValid;
    }

    public void setButtonTrollValid(ImageView buttonTrollValid) {
        this.buttonTrollValid = buttonTrollValid;
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
}
