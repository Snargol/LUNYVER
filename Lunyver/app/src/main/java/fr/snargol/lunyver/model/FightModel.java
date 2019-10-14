package fr.snargol.lunyver.model;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import fr.snargol.lunyver.model.Enums.Class;
import fr.snargol.lunyver.model.Enums.Race;
import fr.snargol.lunyver.model.Enums.Statu;

public class FightModel {
//    Il y a trop d'arguments, ils sont donc stoqués dans le model pour éviter de s'y perdre
    private ImageView Valid1off;
    private ImageView Valid2off;
    private ImageView Valid3off;
    private ImageView Valid4off;
    private ImageView Valid5off;
    private ImageView Valid6off;

    private ImageButton Button1off;
    private ImageButton Button2off;
    private ImageButton Button3off;
    private ImageButton Button4off;
    private ImageButton Button5off;
    private ImageButton Button6off;

    private TextView Pseudo1off;
    private TextView Pseudo2off;
    private TextView Pseudo3off;
    private TextView Pseudo4off;
    private TextView Pseudo5off;
    private TextView Pseudo6off;

    private TextView TotalOffOff;
    private TextView TotalDefOff;
    private TextView TotalLifeOff;

    private ImageView Valid1def;
    private ImageView Valid2def;
    private ImageView Valid3def;
    private ImageView Valid4def;
    private ImageView Valid5def;
    private ImageView Valid6def;

    private ImageButton Button1def;
    private ImageButton Button2def;
    private ImageButton Button3def;
    private ImageButton Button4def;
    private ImageButton Button5def;
    private ImageButton Button6def;

    private TextView Pseudo1def;
    private TextView Pseudo2def;
    private TextView Pseudo3def;
    private TextView Pseudo4def;
    private TextView Pseudo5def;
    private TextView Pseudo6def;

    private TextView TotalOffDef;
    private TextView TotalDefDef;
    private TextView TotalLifeDef;

    private ImageButton ArrowLeftOff;
    private ImageButton ArrowRightOff;
    private ImageButton ArrowLeftDef;
    private ImageButton ArrowRightDef;

    private Button fight_button;

    private ArrayList<ImageView> green_borders_off_list = new ArrayList<>();
    private ArrayList<ImageButton> buttons_off_list = new ArrayList<>();
    private ArrayList<Player> player_list_off = new ArrayList<>();

    private ArrayList<Player> all_player_list = new ArrayList<>();
    private ArrayList<Player> mob_list = new ArrayList<>();

    private ArrayList<Player> player_list_def = new ArrayList<>();
    private ArrayList<ImageView> green_borders_def_list = new ArrayList<>();
    private ArrayList<ImageButton> buttons_def_list = new ArrayList<>();

    private ArrayList<Bonus> bonus_list = new ArrayList<>();

    private boolean OffDisplayMonster = false;
    private boolean DefDisplayMonster = false;

    final private  String FILE_NAME = "datasLUNYVER";
    final private String FILE_NAME_MOBS = "datasLUNYVERMOBS";
    final private String FILE_NAME_OFF = "datasLUNYVERFightOff";
    final private String FILE_NAME_DEF = "datasLUNYVERFightDef";
    final private String FILE_NAME_MOB_OFF = "datasLUNIVERMobOff";
    final private String FILE_NAME_MOB_DEF = "datasLUNIVERMobDef";
    private Activity activity;

    public FightModel() {
        getMob_list().add(new Player("Mob 1", Class.NO_CLASS, Race.MONSTER));
        getMob_list().add(new Player("Mob 2", Class.NO_CLASS, Race.MONSTER));
        getMob_list().add(new Player("Mob 3", Class.NO_CLASS, Race.MONSTER));
        getMob_list().add(new Player("Mob 4", Class.NO_CLASS, Race.MONSTER));
        getMob_list().add(new Player("Mob 5", Class.NO_CLASS, Race.MONSTER));
        getMob_list().add(new Player("Mob 6", Class.NO_CLASS, Race.MONSTER));
    }

    private ArrayList<Player> getValidListOff() {
        if (isOffDisplayMonster()){
            return getMob_list();
        }
        else {
            return getAll_player_list();
        }
    }

    private ArrayList<Player> getValidListDef() {
        if (isDefDisplayMonster()){
            return getMob_list();
        }
        else {
            return getAll_player_list();
        }
    }


//    Cette fonction permet d'appliquer une liste de joueur pour adapter l'affichage
    public void applyListOff(Context context) {
        ArrayList<Player> list = getValidListOff();
        int i = 0;
        for (ImageButton button : getButtons_off_list()) {
            button.setImageResource(list.get(i).getRessourceIdRace(context));
            i++;
        }

        getPseudo1off().setText(list.get(0).get_name());
        getPseudo2off().setText(list.get(1).get_name());
        getPseudo3off().setText(list.get(2).get_name());
        getPseudo4off().setText(list.get(3).get_name());
        getPseudo5off().setText(list.get(4).get_name());
        getPseudo6off().setText(list.get(5).get_name());

        actualizeView();
    }

    //    Cette fonction permet d'appliquer une liste de joueur pour adapter l'affichage
    public void applyListDef(Context context) {
        ArrayList<Player> list = getValidListDef();
        int i = 0;
        for (ImageButton button : getButtons_def_list()) {
            button.setImageResource(list.get(i).getRessourceIdRace(context));
            i++;
        }

        getPseudo1def().setText(list.get(0).get_name());
        getPseudo2def().setText(list.get(1).get_name());
        getPseudo3def().setText(list.get(2).get_name());
        getPseudo4def().setText(list.get(3).get_name());
        getPseudo5def().setText(list.get(4).get_name());
        getPseudo6def().setText(list.get(5).get_name());

        actualizeView();
    }

//    Si le nombre de joueur est inferieur à 6 alors on construit la liste pour afficher tout de même les autres joueurs
//            (qui ne pourront pas être sélectionnés)
    public ArrayList<Player> buildListPlayer(ArrayList<Player> list) {
        testPlayersAvailable(list);
        ArrayList<Player> allPlayers = new ArrayList<>();
        allPlayers.add(new Player("", Class.NO_CLASS, Race.TROLL));
        allPlayers.add(new Player("", Class.NO_CLASS, Race.GNOME));
        allPlayers.add(new Player("", Class.NO_CLASS, Race.CENTAUR));
        allPlayers.add(new Player("", Class.NO_CLASS, Race.WEREWOLF));
        allPlayers.add(new Player("", Class.NO_CLASS, Race.ELF));
        allPlayers.add(new Player("", Class.NO_CLASS, Race.FAIRY));

        for (Player playerOnGame:list) {
            for (Player player:allPlayers) {
                if (playerOnGame.get_race() == player.get_race()){
//                    Ici on identifie un joueur par sa classe et on lui transmet les valeurs
                    player.setPlayer(playerOnGame);
                }
            }
        }
        return allPlayers;
    }

    public void regenerateIds() {
        ArrayList<Player> theList = new ArrayList<>();
        theList.addAll(getAll_player_list());
        theList.addAll(getMob_list());

        for (Player player : theList) {
            player.generateId();
        }
    }

//    Cette méthode permet de renvoyer tous les joueurs (joueurs plus monstre) qui ne sont pas dans
//    la liste donnée (+le joueur qui est le joueur sélectionné)
//    Cela sert à construire une liste pour le défilement despersonnages
    public ArrayList<Player> getPlayersNotInTheList(ArrayList<Player> playersNotWanted){
        ArrayList<Player> theList = new ArrayList<>();
        ArrayList<Player> list2Return = new ArrayList<>();
        theList.addAll(getAll_player_list());
        theList.addAll(getMob_list());
        for (Player player : theList){
            if (!doesContainsRace(playersNotWanted, player.get_race()) || player.get_race() == Race.MONSTER){
                if (!player.get_name().equals(""))
                    //Pas de doublons ! le personnage a déjà été ajouté en tout premier avnat l'appel à cette fonction
                    list2Return.add(player);
            }
        }
        return list2Return;
    }

    public ArrayList<Player> getValidPlayers(ArrayList<Player> players2Test){
        ArrayList<Player> list2Return = new ArrayList<>();
        for (Player player : players2Test){
        if ((!player.get_name().equals("") && player.get_life() > 0) || player.get_race()== Race.MONSTER)
            list2Return.add(player);
        }
        return list2Return;
    }

    public boolean doesContainsRace(ArrayList<Player> list, Race race) {
        for (Player player :list) {
            if (player.get_race() == race)
                return true;
        }
        return false;
    }

    public boolean doesContainsId(ArrayList<Player> list, int id) {
        for (Player player :list) {
            if (player.getId() == id)
                return true;
        }
        return false;
    }

    public void suprTempDatas(ArrayList<Player> list){
        for (Player player : list) {
            player.deleteTempDatas();
        }
    }

//    Cette méthode permet de définir si le player est disponible pour être sélectionné (change
//    un boolean dans le player
//    Conditions :
//    Avoir un nom (alors le joueur est dans le jeu)
//    être en vie
//    ne pas être sélectionné dans une autre équipe
    private void testPlayersAvailable(ArrayList<Player> listOfPlayers) {
        for (Player player :listOfPlayers) {
            if ((!player.get_name().equals("") || player.get_race() == Race.MONSTER) && player.get_life() > 0){
                player.set_isSelectable(true);
            }
            else
                player.set_isSelectable(false);
        }
    }

//    Cette méthode grise les joueurs non sélectionnable de l'équipe offensive
    public void showNotAvailablePlayersOff(ArrayList<Player> listOfPlayers){
        int i =0;
        testPlayersAvailable(listOfPlayers);
        for (Player player :listOfPlayers) {
            if (!player.get_isSelectable() || player.get_statu() == Statu.DEF) {
                getButtons_off_list().get(i).setAlpha(120);
            }
            else {
                getButtons_off_list().get(i).setAlpha(255);
            }
            i++;
        }
    }

    //    Cette méthode grise les joueurs non sélectionnable de l'équipe défensive
    public void showNotAvailablePlayersDef(ArrayList<Player> listOfPlayers){
        int i =0;
        testPlayersAvailable(listOfPlayers);
        for (Player player :listOfPlayers) {
            if (!player.get_isSelectable() || player.get_statu() == Statu.OFF) {
                getButtons_def_list().get(i).setAlpha(120);
            }
            else {
                getButtons_def_list().get(i).setAlpha(255);
            }
            i++;
        }
    }

    public void showGreenBordersOff(ArrayList<Player> listOfPlayers){
        int i = 0;
        for (Player p : listOfPlayers) {
            if (p.get_statu() == Statu.OFF){
                getGreen_borders_off_list().get(i).setVisibility(View.VISIBLE);
            }
            else {
                getGreen_borders_off_list().get(i).setVisibility(View.INVISIBLE);
            }
            i++;
        }
    }

    public void showGreenBordersDef(ArrayList<Player> listOfPlayers){
        int i = 0;
        for (Player p : listOfPlayers) {
            if (p.get_statu() == Statu.DEF){
                getGreen_borders_def_list().get(i).setVisibility(View.VISIBLE);
            }
            else {
                getGreen_borders_def_list().get(i).setVisibility(View.INVISIBLE);
            }
            i++;
        }
    }

    public void actionPlayerLongClick(int position, boolean isOff) {
        ArrayList<Player> list = new ArrayList<>();
        Player player;
        if (isOff)
            player = getValidListOff().get(position);
        else
            player = getValidListDef().get(position);
        list.addAll(getValidPlayers(getAll_player_list()));
        list.addAll(getValidPlayers(getMob_list()));

//
//                La liste est composée de tous les joueurs vivants et dans le jeu et de
//                  tous les monstres
        final PopUpEditPlayer popUp = new PopUpEditPlayer(getActivity(), list, getPositionById(list, player.getId()));
        popUp.getButtonValid().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                On teste les deux listes (joueurs et monstres) pour trouver chaque joueur
//                potentiellement modifié et on effectue les modifs pour appliquer  les nouvelles stats
                popUp.setDatasToPlayer();
                for (Player player : popUp.getPlayer_list()) {
                    if (doesContainsId(getValidListOff(), player.getId())){
                        getValidListOff().get(getPositionById(getValidListOff(), player.getId())).setPlayer(player);
                    }
                }
                actualizeView();
                popUp.dismiss();
            }
        });
        popUp.build();
    }


//    Réalise l'action les actions nécéssaires lorsqu'un joueur est sélectionné
    public void actionPlayerSelectOff(int i, Context context){
        Player player = null;
        player = getValidListOff().get(i);

        if (player.get_isSelectable()){
            if (player.get_statu() == Statu.DEF){
//              Si le joueur est sélectionné en défense
                Toast.makeText(context, "Ce joueur est déjà sélectionné dans l'équipe adverse", Toast.LENGTH_SHORT).show();
            }
            else {
                if (player.get_statu() == Statu.NOTHING){
//                  Si le joueur n'est pas encore sélectionné
                    player.set_statu(Statu.OFF);
                    getPlayer_list_off().add(player);
                    getValidListOff().get(getPositionById(getValidListOff(), player.getId())).set_statu(Statu.OFF);
//                    getPlayer_list().get(getPositionByRace(getPlayer_list(), player.get_race())).set_statu(Statu.OFF);
                    actualizeView();
                }
                else {
//                Si le joueur est déjà sélectionné
                    player.set_statu(Statu.NOTHING);
                    getPlayer_list_off().remove(getPositionById(getPlayer_list_off(), player.getId()));
                    getValidListOff().get(getPositionById(getValidListOff(), player.getId())).set_statu(Statu.NOTHING);
//                    getPlayer_list().get(getPositionByRace(getPlayer_list(), player.get_race())).set_statu(Statu.NOTHING);
                    actualizeView();
                }
                actualizeView();
            }
        }
        if (player.get_life() <= 0){
            Toast.makeText(context, "Ce joueur n'a pas assez de vie pour rejoindre un combat", Toast.LENGTH_SHORT).show();
        }
        if (player.get_name().equals("")){
            Toast.makeText(context, "Ce joueur n'est pas dans la partie !", Toast.LENGTH_SHORT).show();
        }
    }

    //    Réalise l'action les actions nécéssaires lorsqu'un joueur est sélectionné
    public void actionPlayerSelectDef(int i, Context context){
        Player player = null;
        player = getValidListDef().get(i);

        if (player.get_isSelectable()){
            if (player.get_statu() == Statu.OFF){
//              Si le joueur est sélectionné en attaque
                Toast.makeText(context, "Ce joueur est déjà sélectionné dans l'équipe adverse", Toast.LENGTH_SHORT).show();
            }
            else {
                if (player.get_statu() == Statu.NOTHING){
//                  Si le joueur n'est pas encore sélectionné
                    player.set_statu(Statu.DEF);
                    getPlayer_list_def().add(player);
                    getValidListDef().get(getPositionById(getValidListDef(), player.getId())).set_statu(Statu.DEF);
                    actualizeView();
                }
                else {
//                Si le joueur est déjà sélectionné
                    player.set_statu(Statu.NOTHING);
                    getPlayer_list_def().remove(getPositionById(getPlayer_list_def(), player.getId()));
                    getValidListDef().get(getPositionById(getValidListDef(), player.getId())).set_statu(Statu.NOTHING);
                    actualizeView();
                }
                actualizeView();
            }
        }
        if (player.get_life() <= 0){
            Toast.makeText(context, "Ce joueur n'a pas assez de vie pour rejoindre un combat", Toast.LENGTH_SHORT).show();
        }
        if (player.get_name().equals("")){
            Toast.makeText(context, "Ce joueur n'est pas dans la partie !", Toast.LENGTH_SHORT).show();
        }
    }

    public void actualizeView() {
        ArrayList<Player> listOff = getValidListOff();
        ActualizeTotalOff();
        showNotAvailablePlayersOff(listOff);
        showGreenBordersOff(listOff);

        ArrayList<Player> listDef = getValidListDef();
        ActualizeTotalDef();
        showNotAvailablePlayersDef(listDef);
        showGreenBordersDef(listDef);
        //TODO actualiser def aussi
    }

//    Actualise l'affichage du total de l'équipe offensive
    @SuppressLint("SetTextI18n")
    private void ActualizeTotalOff() {
        int totalOff = 0;
        int totalDef = 0;
        int totalLife = 0;
        for (Player player : getPlayer_list_off()) {
            totalOff += player.get_attack();
            totalDef += player.get_defense();
            totalLife += player.get_life();
        }

        getTotalDefOff().setText(Integer.toString(totalDef));
        getTotalOffOff().setText(Integer.toString(totalOff));
        getTotalLifeOff().setText(Integer.toString(totalLife));

    }

    //    Actualise l'affichage du total de l'équipe défensive
    @SuppressLint("SetTextI18n")
    private void ActualizeTotalDef() {
        int totalOff = 0;
        int totalDef = 0;
        int totalLife = 0;
        for (Player player : getPlayer_list_def()) {
            totalOff += player.get_attack();
            totalDef += player.get_defense();
            totalLife += player.get_life();
        }

        getTotalDefDef().setText(Integer.toString(totalDef));
        getTotalOffDef().setText(Integer.toString(totalOff));
        getTotalLifeDef().setText(Integer.toString(totalLife));

    }

    public Bonus getBonusById(int id) {
        int i = 0;
        for (Bonus bonus:getBonus_list()) {
            if (bonus.getId() == id)
                return getBonus_list().get(i);
            i++;
        }
        return new Bonus(0,0,0);
    }

    public int getPositionOfBonus(int id) {
        int i = 0;
        for (Bonus bonus:getBonus_list()) {
            if (bonus.getId() == id)
                return i;
            i++;
        }
        return -1;
    }

    public void removeAll(ArrayList<Player> list, ArrayList<Integer> ids) {
        for (int id:ids) {
            list.remove(getPositionById(list, id));
        }
    }

    public void addOrReplaceAll(ArrayList<Player> list, ArrayList<Player> listToAdd) {
        int i=0;
        for (Player player: listToAdd) {
            if (!existOn(player, list))
                list.add(player);
            else
                list.set(i, player);
            i++;
        }
    }

    public boolean existOn(Player player, ArrayList<Player> list){
        for (Player playerOnList: list) {
            if (player.getId() == playerOnList.getId())
                return true;
        }
        return false;
    }

//    Renvoie la position d'un joueur dans une liste en fonction de son id'
    public int getPositionById(ArrayList<Player> list, int id){
        int i = 0;
        for (Player player:list) {
            if (player.getId() == id)
                return i;
            i++;
        }
        return i;
    }

    //Permet d'identifier les vrais joueurs du jeu
    public ArrayList<Player> getPlayer_list() {
        ArrayList<Player> list2Return = new ArrayList<>();
        for (Player player : getAll_player_list()) {
            if (!player.get_name().equals("")){
                list2Return.add(player);
            }
        }
        return list2Return;
    }

    //    Renvoie la position d'un joueur dans une liste en fonction de sa race, attention cela suppose
//    que la race est un identifiant !
    public int getPositionByRace(ArrayList<Player> list, Race race){
        int i = 0;
        for (Player player:list) {
            if (player.get_race() == race)
                return i;
            i++;
        }
        return i;
    }

    public ArrayList<ImageButton> getButtons_off_list() {
        return buttons_off_list;
    }

    public void setButtons_off_list(ArrayList<ImageButton> buttons_off_list) {
        this.buttons_off_list = buttons_off_list;
    }

    public ArrayList<Player> getMob_list() {
        return mob_list;
    }

    public ArrayList<ImageView> getGreen_borders_off_list() {
        return green_borders_off_list;
    }

    public void setGreen_borders_off_list(ArrayList<ImageView> green_borders_off_list) {
        this.green_borders_off_list = green_borders_off_list;
    }

    public void setMob_list(ArrayList<Player> mob_list) {
        this.mob_list = mob_list;
    }

    public Button getFight_button() {
        return fight_button;
    }

    public void setFight_button(Button fight_button) {
        this.fight_button = fight_button;
    }

    public ArrayList<Player> getAll_player_list() {
        return all_player_list;
    }

    public void setAll_player_list(ArrayList<Player> all_player_list) {
        this.all_player_list = all_player_list;
    }

    public ImageView getValid1off() {
        return Valid1off;
    }

    public void setValid1off(ImageView valid1off) {
        Valid1off = valid1off;
    }

    public ImageView getValid2off() {
        return Valid2off;
    }

    public void setValid2off(ImageView valid2off) {
        Valid2off = valid2off;
    }

    public ImageView getValid3off() {
        return Valid3off;
    }

    public void setValid3off(ImageView valid3off) {
        Valid3off = valid3off;
    }

    public ImageView getValid4off() {
        return Valid4off;
    }

    public void setValid4off(ImageView valid4off) {
        Valid4off = valid4off;
    }

    public ImageView getValid5off() {
        return Valid5off;
    }

    public void setValid5off(ImageView valid5off) {
        Valid5off = valid5off;
    }

    public ImageView getValid6off() {
        return Valid6off;
    }

    public void setValid6off(ImageView valid6off) {
        Valid6off = valid6off;
    }

    public ImageButton getButton1off() {
        return Button1off;
    }

    public void setButton1off(ImageButton button1off) {
        Button1off = button1off;
    }

    public ImageButton getButton2off() {
        return Button2off;
    }

    public void setButton2off(ImageButton button2off) {
        Button2off = button2off;
    }

    public ImageButton getButton3off() {
        return Button3off;
    }

    public void setButton3off(ImageButton button3off) {
        Button3off = button3off;
    }

    public ImageButton getButton4off() {
        return Button4off;
    }

    public void setButton4off(ImageButton button4off) {
        Button4off = button4off;
    }

    public ImageButton getButton5off() {
        return Button5off;
    }

    public void setButton5off(ImageButton button5off) {
        Button5off = button5off;
    }

    public ImageButton getButton6off() {
        return Button6off;
    }

    public void setButton6off(ImageButton button6off) {
        Button6off = button6off;
    }

    public TextView getPseudo1off() {
        return Pseudo1off;
    }

    public void setPseudo1off(TextView pseudo1off) {
        Pseudo1off = pseudo1off;
    }

    public TextView getPseudo2off() {
        return Pseudo2off;
    }

    public void setPseudo2off(TextView pseudo2off) {
        Pseudo2off = pseudo2off;
    }

    public TextView getPseudo3off() {
        return Pseudo3off;
    }

    public void setPseudo3off(TextView pseudo3off) {
        Pseudo3off = pseudo3off;
    }

    public TextView getPseudo4off() {
        return Pseudo4off;
    }

    public void setPseudo4off(TextView pseudo4off) {
        Pseudo4off = pseudo4off;
    }

    public TextView getPseudo5off() {
        return Pseudo5off;
    }

    public void setPseudo5off(TextView pseudo5off) {
        Pseudo5off = pseudo5off;
    }

    public TextView getPseudo6off() {
        return Pseudo6off;
    }

    public void setPseudo6off(TextView pseudo6off) {
        Pseudo6off = pseudo6off;
    }

    public ImageView getValid1def() {
        return Valid1def;
    }

    public void setValid1def(ImageView valid1def) {
        Valid1def = valid1def;
    }

    public ImageView getValid2def() {
        return Valid2def;
    }

    public void setValid2def(ImageView valid2def) {
        Valid2def = valid2def;
    }

    public ImageView getValid3def() {
        return Valid3def;
    }

    public void setValid3def(ImageView valid3def) {
        Valid3def = valid3def;
    }

    public ImageView getValid4def() {
        return Valid4def;
    }

    public void setValid4def(ImageView valid4def) {
        Valid4def = valid4def;
    }

    public ImageView getValid5def() {
        return Valid5def;
    }

    public void setValid5def(ImageView valid5def) {
        Valid5def = valid5def;
    }

    public ImageView getValid6def() {
        return Valid6def;
    }

    public void setValid6def(ImageView valid6def) {
        Valid6def = valid6def;
    }

    public ImageButton getButton1def() {
        return Button1def;
    }

    public void setButton1def(ImageButton button1def) {
        Button1def = button1def;
    }

    public ImageButton getButton2def() {
        return Button2def;
    }

    public void setButton2def(ImageButton button2def) {
        Button2def = button2def;
    }

    public ImageButton getButton3def() {
        return Button3def;
    }

    public void setButton3def(ImageButton button3def) {
        Button3def = button3def;
    }

    public ImageButton getButton4def() {
        return Button4def;
    }

    public void setButton4def(ImageButton button4def) {
        Button4def = button4def;
    }

    public ImageButton getButton5def() {
        return Button5def;
    }

    public void setButton5def(ImageButton button5def) {
        Button5def = button5def;
    }

    public ImageButton getButton6def() {
        return Button6def;
    }

    public void setButton6def(ImageButton button6def) {
        Button6def = button6def;
    }

    public TextView getPseudo1def() {
        return Pseudo1def;
    }

    public void setPseudo1def(TextView pseudo1def) {
        Pseudo1def = pseudo1def;
    }

    public TextView getPseudo2def() {
        return Pseudo2def;
    }

    public void setPseudo2def(TextView pseudo2def) {
        Pseudo2def = pseudo2def;
    }

    public TextView getPseudo3def() {
        return Pseudo3def;
    }

    public void setPseudo3def(TextView pseudo3def) {
        Pseudo3def = pseudo3def;
    }

    public TextView getPseudo4def() {
        return Pseudo4def;
    }

    public void setPseudo4def(TextView pseudo4def) {
        Pseudo4def = pseudo4def;
    }

    public TextView getPseudo5def() {
        return Pseudo5def;
    }

    public void setPseudo5def(TextView pseudo5def) {
        Pseudo5def = pseudo5def;
    }

    public TextView getPseudo6def() {
        return Pseudo6def;
    }

    public void setPseudo6def(TextView pseudo6def) {
        Pseudo6def = pseudo6def;
    }

    public ArrayList<Player> getPlayer_list_off() {
        return player_list_off;
    }

    public void setPlayer_list_off(ArrayList<Player> player_list_off) {
        this.player_list_off = player_list_off;
    }

    public ArrayList<Player> getPlayer_list_def() {
        return player_list_def;
    }

    public void setPlayer_list_def(ArrayList<Player> player_list_def) {
        this.player_list_def = player_list_def;
    }

    public ArrayList<Bonus> getBonus_list() {
        return bonus_list;
    }

    public void setBonus_list(ArrayList<Bonus> bonus_list) {
        this.bonus_list = bonus_list;
    }

    public String getFILE_NAME() {
        return FILE_NAME;
    }

    public String getFILE_NAME_OFF() {
        return FILE_NAME_OFF;
    }

    public String getFILE_NAME_DEF() {
        return FILE_NAME_DEF;
    }

    public String getFILE_NAME_MOB_OFF() {
        return FILE_NAME_MOB_OFF;
    }

    public String getFILE_NAME_MOB_DEF() {
        return FILE_NAME_MOB_DEF;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public ImageButton getArrowLeftOff() {
        return ArrowLeftOff;
    }

    public void setArrowLeftOff(ImageButton arrowLeftOff) {
        ArrowLeftOff = arrowLeftOff;
    }

    public ImageButton getArrowRightOff() {
        return ArrowRightOff;
    }

    public void setArrowRightOff(ImageButton arrowRightOff) {
        ArrowRightOff = arrowRightOff;
    }

    public ImageButton getArrowLeftDef() {
        return ArrowLeftDef;
    }

    public void setArrowLeftDef(ImageButton arrowLeftDef) {
        ArrowLeftDef = arrowLeftDef;
    }

    public ImageButton getArrowRightDef() {
        return ArrowRightDef;
    }

    public void setArrowRightDef(ImageButton arrowRightDef) {
        ArrowRightDef = arrowRightDef;
    }

    public TextView getTotalOffOff() {
        return TotalOffOff;
    }

    public void setTotalOffOff(TextView totalOffOff) {
        TotalOffOff = totalOffOff;
    }

    public TextView getTotalDefOff() {
        return TotalDefOff;
    }

    public void setTotalDefOff(TextView totalDefOff) {
        TotalDefOff = totalDefOff;
    }

    public TextView getTotalLifeOff() {
        return TotalLifeOff;
    }

    public void setTotalLifeOff(TextView totalLifeOff) {
        TotalLifeOff = totalLifeOff;
    }

    public boolean isOffDisplayMonster() {
        return OffDisplayMonster;
    }

    public void setOffDisplayMonster(boolean offDisplayMonster) {
        OffDisplayMonster = offDisplayMonster;
    }

    public boolean isDefDisplayMonster() {
        return DefDisplayMonster;
    }

    public void setDefDisplayMonster(boolean defDisplayMonster) {
        DefDisplayMonster = defDisplayMonster;
    }

    public String getFILE_NAME_MOBS() {
        return FILE_NAME_MOBS;
    }

    public TextView getTotalOffDef() {
        return TotalOffDef;
    }

    public void setTotalOffDef(TextView totalOffDef) {
        TotalOffDef = totalOffDef;
    }

    public TextView getTotalDefDef() {
        return TotalDefDef;
    }

    public void setTotalDefDef(TextView totalDefDef) {
        TotalDefDef = totalDefDef;
    }

    public TextView getTotalLifeDef() {
        return TotalLifeDef;
    }

    public void setTotalLifeDef(TextView totalLifeDef) {
        TotalLifeDef = totalLifeDef;
    }

    public ArrayList<ImageView> getGreen_borders_def_list() {
        return green_borders_def_list;
    }

    public void setGreen_borders_def_list(ArrayList<ImageView> green_borders_def_list) {
        this.green_borders_def_list = green_borders_def_list;
    }

    public ArrayList<ImageButton> getButtons_def_list() {
        return buttons_def_list;
    }

    public void setButtons_def_list(ArrayList<ImageButton> buttons_def_list) {
        this.buttons_def_list = buttons_def_list;
    }
}
