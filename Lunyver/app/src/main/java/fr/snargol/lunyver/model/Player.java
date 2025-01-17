package fr.snargol.lunyver.model;

import android.content.Context;

import java.io.Serializable;

import fr.snargol.lunyver.controler.IDGenerator;
import fr.snargol.lunyver.model.Enums.Class;
import fr.snargol.lunyver.model.Enums.Race;
import fr.snargol.lunyver.model.Enums.Statu;

public class Player implements Serializable {

    private String _name;
    private int _attack = 0;
    private int _defense = 0;
    private int _life = 20;
    private int _suffer_damages = 0;
    private int _bonus_attack = 0;
    private int _bonus_defense = 0;
    private Class _class;
    private Race _race;
    private int _level = 0;
    private int _contributed_money = 0;
    private Boolean _isAlive = true;
    private Boolean _isSelectable = false;
    private Statu _statu = Statu.NOTHING;
    private int id;


    public Player(String _name, Class _class, Race _race) {
        this._name = _name;
        this._class = _class;
        this._race = _race;
        set_life(0);
        set_level(0);
        set_attack(0);
        set_defense(0);
        set_contributed_money(0);
        set_isAlive(true);
        setId(IDGenerator.getId());
        set_isSelectable(false);
    }

    public Player(String _name, int _attack, int _defense, Class _class, Race _race) {
        this._name = _name;
        this._attack = _attack;
        this._defense = _defense;
        this._class = _class;
        this._race = _race;
        set_life(0);
        set_level(0);
        set_contributed_money(0);
        set_isAlive(true);
        setId(IDGenerator.getId());
        set_isSelectable(false);
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
        setId(IDGenerator.getId());
        set_isSelectable(false);
    }

    public Player(String _name, Race _race, Class _class, int _attack, int _defense, int _life, int _level, int _contributed_money, Boolean _isAlive, int id) {
        set_name(_name);
        set_attack(_attack);
        set_defense(_defense);
        set_life(_life);
        set_class(_class);
        set_race(_race);
        set_level(_level);
        set_contributed_money(_contributed_money);
        set_isAlive(_isAlive);
        setId(id);
        set_isSelectable(false);
    }

    public int getId() {
        return id;
    }

    public void resetPlayer(){
        set_attack(0);
        set_defense(0);
        set_life(0);
        set_class(Class.NO_CLASS);
        set_race(Race.NO_RACE);
        set_level(0);
        set_contributed_money(0);
        deleteTempDatas();
    }

    public void setPlayer(Player p){
        set_name(p.get_name());
        set_attack(p.get_attack());
        set_defense(p.get_defense());
        set_life(p.get_life());
        set_class(p.get_class());
        set_race(p.get_race());
        set_contributed_money(p.get_contributed_money());
        setId(p.getId());
        set_isSelectable(p.get_isSelectable());
        set_isAlive(p.get_isAlive());
        set_statu(p.get_statu());
    }

    public void generateId() {
        setId(IDGenerator.getId());
    }

    public void deleteTempDatas() {
        set_statu(Statu.NOTHING);
        set_isSelectable(false);
        set_bonus_attack(0);
        set_bonus_defense(0);
        set_suffer_damages(0);
    }

    public int getRessourceIdRace(Context context) {
        String ressourceName = "race_" + get_race();
        int resId = context.getResources().getIdentifier(ressourceName.toLowerCase(), "drawable", context.getPackageName());
        return resId;
    }

    public int getRessourceIdClass(Context context){
        String ressourceName = "class_" + get_class();
        int resId = context.getResources().getIdentifier(ressourceName.toLowerCase(), "drawable", context.getPackageName());
        return resId;
    }

    public Boolean get_isSelectable() {
        return _isSelectable;
    }

    public void set_isSelectable(Boolean _isSelectable) {
        this._isSelectable = _isSelectable;
    }

    public void setId(int id) {
        this.id = id;
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

    public Statu get_statu() {
        return _statu;
    }

    public void set_statu(Statu _statu) {
        this._statu = _statu;
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

    public void add_level(int valueToAdd){
        if (get_level()+valueToAdd >= 0)
            set_level(get_level()+valueToAdd);
    }

    public void add_attack(int valueToAdd){
        if (get_attack()+valueToAdd >= 0)
            set_attack(get_attack()+valueToAdd);
        else
            set_attack(0);
    }

    public void add_defense(int valueToAdd){
        if (get_defense()+valueToAdd >= 0)
            set_defense(get_defense()+valueToAdd);
        else
            set_defense(0);
    }

    public void add_life(int valueToAdd){
        if (get_life()+valueToAdd >= 0)
            set_life(get_life()+valueToAdd);
        else
            set_life(0);
    }

    public int get_bonus_attack() {
        return _bonus_attack;
    }

    public void set_bonus_attack(int _bonus_attack) {
        this._bonus_attack = _bonus_attack;
    }

    public int get_bonus_defense() {
        return _bonus_defense;
    }

    public void set_bonus_defense(int _bonus_defense) {
        this._bonus_defense = _bonus_defense;
    }

    public int get_suffer_damages() {
        return _suffer_damages;
    }

    public void set_suffer_damages(int _suffer_damages) {
        this._suffer_damages = _suffer_damages;
    }

    public void add_suffer_damage(int _suffer_damage) {
        if (get_suffer_damages() + _suffer_damage >= 0)
            if (get_suffer_damages() + _suffer_damage > get_life())
                set_suffer_damages(get_life());
            else
                this._suffer_damages += _suffer_damage;

    }
}
