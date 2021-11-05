package sample;

import javafx.scene.image.Image;

public class Enemy {
    private String name = "";
    private Image mainImage;
    private Image faceImage;
    private int gamesPlayed = 0;
    private int gamesLost = 0;

    private String specialAbility = "";
    private int classicStrikeAbility;
    private int jumpHitAbility;
    private int blockHitAbility;
    private int defensiveRating;

    public Enemy(String n, Image mainI, Image faceI, int classicStrike, int jumpHit, int blockHit, int defensive, String special) {
        name = n;
        mainImage = mainI;
        faceImage = faceI;
        classicStrikeAbility = classicStrike;
        jumpHitAbility = jumpHit;
        blockHitAbility = blockHit;
        defensiveRating = defensive;
        specialAbility = special;
    }

    public String getName() {
        return name;
    }

    public Image getMainImage() {
        return mainImage;
    }

    public Image getFaceImage() {
        return faceImage;
    }

    public void addGamesPlayed(){
        gamesPlayed++;
    }

    public void addGamesLost(){
        gamesLost++;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getGamesLost() {
        return gamesLost;
    }

    public int getBlockHitAbility() {
        return blockHitAbility;
    }

    public int getClassicStrikeAbility() {
        return classicStrikeAbility;
    }

    public int getJumpHitAbility() {
        return jumpHitAbility;
    }

    public int getDefensiveRating() {
        return defensiveRating;
    }

    public String getSpecialAbility() {
        return specialAbility;
    }


    public void handleRandomizeAbilities(){
        classicStrikeAbility = randomizeAbilities(classicStrikeAbility);
        blockHitAbility = randomizeAbilities(blockHitAbility);
        jumpHitAbility = randomizeAbilities(jumpHitAbility);
        defensiveRating = randomizeAbilities(defensiveRating);
    }

    public int randomizeAbilities(int x){
        int randFactor = (int)(Math.random()*5+1);
        if (x == defensiveRating){
            randFactor = (int)(Math.random()*2+1);
        }
        int randSign = (int)(Math.random()*2+1);
        if (randSign == 1) {
            x+=randFactor;
        } else {
            x-=randFactor;
        }
        return x;
    }
}
