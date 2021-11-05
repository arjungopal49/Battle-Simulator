package sample;

import javafx.scene.image.Image;

public class Character {
    private String name = "";
    private Image selectedCharacterImage;
    private Boolean unlocked = false;
    private Image mainImage;
    private Image lobbyImage;

    private String specialAbility = "";
    private int classicStrikeAbility;
    private int jumpHitAbility;
    private int blockHitAbility;
    private int defensiveRating;

    public Character(String n, Image mainSource, Image lis, Image scSource, Boolean u, int classicStrike, int jumpHit, int blockHit, int defensive, String special){
        name = n;
        mainImage = mainSource;
        unlocked = u;
        lobbyImage = lis;
        selectedCharacterImage = scSource;
        classicStrikeAbility = classicStrike;
        jumpHitAbility = jumpHit;
        blockHitAbility = blockHit;
        defensiveRating = defensive;
        specialAbility = special;
    }

    public String getName(){
        return name;
    }

    public Image getMainImage(){
        return mainImage;
    }

    public void setUnlocked(Boolean x){
        unlocked = x;
    }

    public Boolean getUnlocked(){
        return unlocked;
    }

    public Image getSelectedCharacterImage() {
        return selectedCharacterImage;
    }

    public Image getLobbyImageSource(){
        return lobbyImage;
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


    public void upgradeAbilities(){
        blockHitAbility+=(int)(Math.random()*2+1);
        jumpHitAbility+=(int)(Math.random()*2+1);
        classicStrikeAbility+=(int)(Math.random()*2+1);
        defensiveRating+=(int)(Math.random()*3+1);
    }
}
