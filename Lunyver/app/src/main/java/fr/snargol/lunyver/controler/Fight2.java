package fr.snargol.lunyver.controler;

import java.util.ArrayList;

import fr.snargol.lunyver.model.Bonus;
import fr.snargol.lunyver.model.Enums.Class;
import fr.snargol.lunyver.model.Enums.Race;
import fr.snargol.lunyver.model.Player;

public class Fight2 {
    ArrayList<Player> list_off;
    ArrayList<Player> list_def;
    ArrayList<Bonus>  list_bonus;
    String winner = "";

    public Fight2(ArrayList<Player> list_off, ArrayList<Player> list_def){
        setList_def(list_def);
        setList_off(list_off);
    }

    public void startFight() {
        while (!areDefeated(getList_def()) && !areDefeated(getList_off())) {
            if (!areDefeated(getList_off())) {
                for (Player playerOff : getPlayersAlive(getList_off())) {
                    if (!areDefeated(getList_def())) {
                        attack(playerOff, getPlayerToAttack(getList_def(), playerOff));
                    }
                }
            }
            if (!areDefeated(getList_def())) {
                for (Player playerDef : getPlayersAlive(getList_def())) {
                    if (!areDefeated(getList_off())) {
                        attack(playerDef, getPlayerToAttack(getList_off(), playerDef));
                    }
                }
            }
        }
        if (areDefeated(getList_off())){
            winner = "défenseurs";
        }
        else {
            winner = "attaquants";
        }
    }

    public void saveFight(ArrayList<Player> list) {
        for (Player player:list) {
            for (Player playerToSave: getList_off()) {
                if (player.get_race() == playerToSave.get_race()){
                    player = playerToSave;
                }
            }
            for (Player playerToSave: getList_def()) {
                if (player.get_race() == playerToSave.get_race()){
                    player = playerToSave;
                }
            }
        }
    }

//    On choisis le premier joueur en vie qui est le plus faible (meurt le plus vite)
//    et qui ont le plus d'attaque
    private Player getPlayerToAttack(ArrayList<Player> list, Player playerOff) {
        return getFirstPlayer(getPlayersMaxAttack(getPlayersWeek(getPlayersAlive(list), playerOff)));
    }

//    The weeker players are selected to ba attacked first
    private ArrayList<Player> getPlayersWeek(ArrayList<Player> list, Player playerOff) {
        int i = 0;
        ArrayList<Player> listPlayersWeek = new ArrayList<Player>();
        Player playerweek = new Player("name", Race.NO_RACE, Class.NO_CLASS, 99999, 99999, 99999, 99999, 0, true);
        for (Player player : list) {
            int numberOfShootsToKillPlayer = (int) Math.round((double) player.get_life() / (playerOff.get_attack()+ playerOff.get_bonus_attack() - player.get_defense()+ player.get_bonus_defense()));
            int numberOfShootsToKillPlayerWeek = (int) Math.round((double) playerweek.get_life() / (playerOff.get_attack()+ playerOff.get_bonus_attack() - playerweek.get_defense()+playerweek.get_bonus_defense()));
            if (numberOfShootsToKillPlayer < numberOfShootsToKillPlayerWeek || numberOfShootsToKillPlayerWeek < 0) {
                if (numberOfShootsToKillPlayer == numberOfShootsToKillPlayerWeek){
                    listPlayersWeek.add(player);
                }
                else {
                    listPlayersWeek = new ArrayList<Player>();
                    listPlayersWeek.add(player);
                    playerweek = player;
                }
            }
        }
        return listPlayersWeek;
    }

    private ArrayList<Player> getPlayersMaxAttack(ArrayList<Player> list) {
        ArrayList<Player> listPlayersStrong = new ArrayList<Player>();
        int maxAttack = -1;
        for (Player player:list) {
            if (player.get_attack() + player.get_bonus_attack() > maxAttack){
                if (player.get_attack() + player.get_bonus_attack() == maxAttack)
                    listPlayersStrong.add(player);
                else {
                    listPlayersStrong = new ArrayList<Player>();
                    listPlayersStrong.add(player);
                    maxAttack = player.get_attack() + player.get_bonus_attack() ;
                }
            }
        }
        return listPlayersStrong;
    }

    private Player getFirstPlayer(ArrayList<Player> list) {
        if (list.size() > 0)
            return list.get(0);
        else {
            return null;
        }
    }

    private ArrayList<Player> getPlayersAlive(ArrayList<Player> list) {
        ArrayList<Player> listPlayersAlive = new ArrayList<>();
        for (Player player:list) {
            if (player.get_life() > 0){
                listPlayersAlive.add(player);
            }
            else {
                player.set_isAlive(false);
            }
        }
        return listPlayersAlive;
    }

    private void attack(Player playerOff, Player playerDef) {
        if ((playerOff.get_attack() + playerOff.get_bonus_attack()) - (playerDef.get_defense() + playerDef.get_bonus_defense()) > 0){
            playerDef.add_life(-((playerOff.get_attack() + playerOff.get_bonus_attack() )
                    - (playerDef.get_defense() + playerDef.get_bonus_defense())));
        }
    }

    private boolean areDefeated(ArrayList<Player> list) {
        if (getPlayersAlive(list).size() > 0)
            return false;
        else
            return true;
    }


    public ArrayList<Player> getList_off() {
        return list_off;
    }

    private void setList_off(ArrayList<Player> list_off) {
        this.list_off = list_off;
    }

    public ArrayList<Player> getList_def() {
        return list_def;
    }

    private void setList_def(ArrayList<Player> list_def) {
        this.list_def = list_def;
    }

    public String getWinner() {
        return winner;
    }
}
