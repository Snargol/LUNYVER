package fr.snargol.lunyver.model;

import fr.snargol.lunyver.model.Enums.Race;

public class Bonus {
    private int attack_bonus;
    private int defense_bonus;
    private int life_modif;
    private int id;

    public Bonus(int attack, int defense, int life_change, int id) {
        setId(id);
        setAttack_bonus(attack);
        setDefense_bonus(defense);
        setLife_modif(life_change);
    }

    public Bonus(int attack, int defense, int life_change) {
        //id is not use (0 = impossible value)
        setId(0);
        setAttack_bonus(attack);
        setDefense_bonus(defense);
        setLife_modif(life_change);
    }

    public int getAttack_bonus() {
        return attack_bonus;
    }

    public String getAttack_bonus_string() {
        if (getAttack_bonus() > 0)
            return "+"+getAttack_bonus();
        else if (getAttack_bonus() < 0){
            return "-"+getAttack_bonus();
        }
        else
            return String.valueOf(getAttack_bonus());
    }

    public String getDefense_bonus_string() {
        if (getDefense_bonus() > 0)
            return "+"+getDefense_bonus();
        else if (getDefense_bonus() < 0){
            return "-"+getDefense_bonus();
        }
        else
            return String.valueOf(getDefense_bonus());
    }

    public void setAttack_bonus(int attack_bonus) {
        this.attack_bonus = attack_bonus;
    }

    public void addAttack_bonus(int attack_bonus) {
        setAttack_bonus(getAttack_bonus()+attack_bonus);
    }

    public int getDefense_bonus() {
        return defense_bonus;
    }

    public void setDefense_bonus(int defense_bonus) {
        this.defense_bonus = defense_bonus;
    }

    public void addDefense_bonus(int defense_bonus) {
        setDefense_bonus(getDefense_bonus()+defense_bonus);
    }

    public int getLife_modif() {
        return life_modif;
    }

    public void setLife_modif(int life_modif) {
        this.life_modif = life_modif;
    }

    public void addLife_modif(int life_modif) {
        setLife_modif(getLife_modif()+life_modif);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
