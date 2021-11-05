package sample;

import javafx.scene.image.Image;

public class Map {
    private String name = "";
    private Image mapImage;
    private int numTimesPlayed = 0;
    private int playerImageViewYCoord;

    public Map(String n, Image image, int yCoord){
        name = n;
        mapImage = image;
        playerImageViewYCoord = yCoord;
    }

    public String getName() {
        return name;
    }

    public Image getMapImage() {
        return mapImage;
    }

    public void addNumTimesPlayed(){
        numTimesPlayed = numTimesPlayed + 1;
    }

    public int getNumTimesPlayed() {
        return numTimesPlayed;
    }

    public int getPlayerImageViewYCoord() {
        return playerImageViewYCoord;
    }
}
