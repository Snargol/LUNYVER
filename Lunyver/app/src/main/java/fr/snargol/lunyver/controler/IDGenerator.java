package fr.snargol.lunyver.controler;

public class IDGenerator {
    private static int id = 0;

    public static int getId() {
        id ++;
        return id;
    }

    private void setId(int id) {
        IDGenerator.id = id;
    }
}
