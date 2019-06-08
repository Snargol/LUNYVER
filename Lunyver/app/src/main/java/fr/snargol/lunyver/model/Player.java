package fr.snargol.lunyver.model;

import java.io.Serializable;

import fr.snargol.lunyver.model.Enums.Class;
import fr.snargol.lunyver.model.Enums.Race;

public class Player implements Serializable {

    private String _name;
    private int _attack = 0;
    private int _defense = 0;
    private int _life = 20;
    private Class _class;
    private Race _race;
    private int _level = 0;
    private int _contributed_money = 0;
    private Boolean _isAlive = true;

    public Player(String _name, Class _class, Race _race) {
        this._name = _name;
        this._class = _class;
        this._race = _race;
    }

    public Player(String _name, int _attack, int _defense, Class _class, Race _race) {
        this._name = _name;
        this._attack = _attack;
        this._defense = _defense;
        this._class = _class;
        this._race = _race;
    }

    public Player(String _name, Race _race, Class _class, int _attack, int _defense, int _life, int _level, int _contributed_money, Boolean _isAlive) {
        set_name(_name);
        set_attack(_attack);
        set_defense(_defense);
        set_life(_life);
        set_class(_class);
        set_race(_race);
        set_level(_level);
        set_contributed_money(_contributed_money);
        set_isAlive(_isAlive);
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public int get_attack() {
        return _attack;
    }

    public void set_attack(int _attack) {
        this._attack = _attack;
    }

    public int get_defense() {
        return _defense;
    }

    public void set_defense(int _defense) {
        this._defense = _defense;
    }

    public int get_life() {
        return _life;
    }

    public void set_life(int _life) {
        this._life = _life;
    }

    public Class get_class() {
        return _class;
    }

    public void set_class(Class _class) {
        this._class = _class;
    }

    public Race get_race() {
        return _race;
    }

    public void set_race(Race _race) {
        this._race = _race;
    }

    public int get_level() {
        return _level;
    }

    public void set_level(int _level) {
        this._level = _level;
    }

    public int get_contributed_money() {
        return _contributed_money;
    }

    public void set_contributed_money(int _contributed_money) {
        this._contributed_money = _contributed_money;
    }

    public Boolean get_isAlive() {
        return _isAlive;
    }

    public void set_isAlive(Boolean _isAlive) {
        this._isAlive = _isAlive;
    }


}
