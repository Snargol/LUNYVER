package fr.snargol.lunyver.model;

public class Bonus {
    int attack_bonus;
    int defense_bonus;
    int life_modif;
    int position;

    public Bonus(int position) {
        this.position = position;
    }

    public int getAttack_bonus() {
        return attack_bonus;
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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
