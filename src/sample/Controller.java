package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class Controller {

    //declare fxml variables
    @FXML
    Label instructionsLabel, selectedCharacterLabel, newCharacterLabel, progressBarNumberLabel, enemyLobbyName, enemySpecialAbilitiesLabel, characterSpecialAbilitiesLabel, enemyRecordLabel, recommendedDifficultyLabel, mapLabel, progressBarNameLabel1, progressBarNameLabel2, characterHealthLabel, enemyHealthLabel, characterDefensiveRatingLabel, enemyDefensiveRatingLabel, chooseYourMoveLabel, computerMoveLabel, specialAbilityLabel, endResultLabel, resultsLabel, totalWinsLossLabel, additionalStatsLabel;
    @FXML
    ImageView introScreen, selectedCharacterImage, newCharacterImage, marioLobbyImage, kirbyLobbyImage, yoshiLobbyImage, peachLobbyImage, linkLobbyImage, pikachuLobbyImage, enemyLobbyImage, enemyMapScreenImage, playerMapScreenImage, battleMapImage, enemyBattleImage, characterBattleImage;
    @FXML
    Button startButton, beginButton, continueNewCharacterButton, computerTurnButton, classicStrikeButton, blockHitButton, upgradeDefenseButton, jumpHitButton, specialAbilityButton, upgradeButton, continueFromResultButton;
    @FXML
    AnchorPane lobbyAnchorPane, characterUnlockedScreen, chooseMapScreen, battleScene, gameEndScreen, statsScreen;
    @FXML
    ProgressBar overallProgressBar, characterBattleProgressBar, enemyBattleProgressBar;
    @FXML
    Slider enemyDifficultySlider;
    @FXML
    ListView mapsListView;

    //declare character, enemy, and map arrays
    ArrayList<Character> characters = new ArrayList<>();
    ArrayList<Enemy> enemies = new ArrayList<>();
    ArrayList<Map> maps = new ArrayList<>();
    private void createCharacters(){
        characters.clear();
        characters.add(new Character("Mario", new Image("Resources/marioMain.png"), new Image("Resources/marioLobby.png"), new Image("Resources/marioFace.jpg") ,true, randInt(30, 50), randInt(20, 40), randInt(20, 40), randInt(8, 15), "Fire"));
        characters.add(new Character("Kirby", new Image("Resources/kirbyMain.png"), new Image("Resources/kirbyLobby.jpg"),new Image("Resources/kirbyFace.png"),false, randInt(25, 45), randInt(30, 50), randInt(20, 40), randInt(15, 28), "Air"));
        characters.add(new Character("Yoshi", new Image("Resources/yoshiMain.png"), new Image("Resources/yoshiLobby.png"),new Image("Resources/yoshiFace.jpg"),false, randInt(25, 45), randInt(25, 45), randInt(35, 55), randInt(12, 23), "Earth"));
        characters.add(new Character("Peach", new Image("Resources/peachMain.png"),new Image("Resources/peachLobby.png"),new Image("Resources/peachFace.png") ,false, randInt(35, 55), randInt(20, 40), randInt(30, 50), randInt(8, 15), "Air"));
        characters.add(new Character("Link", new Image("Resources/linkMain.png"), new Image("Resources/linkLobby.png"),new Image("Resources/linkFace.png"),false, randInt(30, 50), randInt(25, 45), randInt(45, 65), randInt(12, 23), "Ice"));
        characters.add(new Character("Pikachu", new Image("Resources/pikachuMain.png"), new Image("Resources/pikachuLobby.jpg"),new Image("Resources/pikachuFace.jpg"),false, randInt(45, 65), randInt(40, 60), randInt(25, 45), randInt(18, 32), "Electricity"));
    }
    private void createEnemies(){
        enemies.clear();
        enemies.add(new Enemy("Wario", new Image("Resources/warioMain.png"), new Image("Resources/warioFace.png"), randInt(20, 40), randInt(25, 45), randInt(30, 50), randInt(15, 28), "Earth"));
        enemies.add(new Enemy("Ike", new Image("Resources/ikeMain.png"), new Image("Resources/ikeFace.png"), randInt(30, 50), randInt(25, 45), randInt(35, 55), randInt(12, 23), "Ice"));
        enemies.add(new Enemy("Diddy Kong", new Image("Resources/diddyKongMain.png"), new Image("Resources/diddyKongFace.jpg"), randInt(25, 45), randInt(40, 60), randInt(25, 45), randInt(11, 21), "Air"));
        enemies.add(new Enemy("Charizard", new Image("Resources/charizardMain.png"), new Image("Resources/charizardFace.png"), randInt(45, 65), randInt(20, 40), randInt(25, 45), randInt(12, 23), "Fire"));
        enemies.add(new Enemy("Donkey Kong", new Image("Resources/donkeyKongMain.png"), new Image("Resources/donkeyKongFace.png"), randInt(25, 45), randInt(30, 50), randInt(40, 60), randInt(19, 33), "Earth"));
        enemies.add(new Enemy("Bowser", new Image("Resources/bowserMain.png"), new Image("Resources/bowserFace.png"), randInt(40, 60), randInt(30, 50), randInt(40, 60), randInt(19, 33), "Ball of Doom"));
    }
    private void createMaps(){
        maps.clear();
        maps.add(new Map("Battlefield", new Image("Resources/battlefieldMap.png"), -80));
        maps.add(new Map("Boxing Ring", new Image("Resources/boxingRingMap.png"), 45));
        maps.add(new Map("Kongo Jungle", new Image("Resources/kongoJungleMap.png"), -10));
        maps.add(new Map("Mario Galaxy", new Image("Resources/marioGalaxyMap.jpg"), 20));
        maps.add(new Map("Town and City", new Image("Resources/townAndCityMap.png"), 20));
    }






    @FXML
    private void handleStart(){
        startButton.setVisible(false);
        introScreen.setVisible(false);
        instructions();
    }

    private void instructions(){
        instructionsLabel.setVisible(true);
        beginButton.setVisible(true);
    }

    @FXML
    private void handleBegin(){
        instructionsLabel.setVisible(false);
        beginButton.setVisible(false);
        createCharacters();
        createEnemies();
        createMaps();
        populateMapsListView();
        displayLobby();
    }


    private double level = 0;
    private int currentEnemy = 0;
    private int wins = 0;
    private int losses = 0;
    private boolean unlockedNewCharacter = false;
    public void displayLobby(){
        lobbyAnchorPane.setVisible(true);
        selectedCharacterLabel.setText("Selected Character:                    " + characters.get(selectedCharacter).getName());
        displayMainProgressBar();
        generateRandomEnemy();
        generateRecommendedDifficulty();
        displayAbilities();
    }

    public void displayMainProgressBar(){
        double temp = level*100;
        temp = Math.round(temp);
        temp = temp%100;
        temp = temp/100;
        overallProgressBar.setProgress(temp);
        temp = Math.floor(level);
        progressBarNumberLabel.setText((int)temp + "                                                                            " + (int)(temp+1));
    }

    public void displayAbilities(){
        characterSpecialAbilitiesLabel.setText("Classic Strike: " + characters.get(selectedCharacter).getClassicStrikeAbility() + "\nJump Hit: " + characters.get(selectedCharacter).getJumpHitAbility() + "\nBlock Hit: " + characters.get(selectedCharacter).getBlockHitAbility() + "\nDefensive Rating: " + characters.get(selectedCharacter).getDefensiveRating() + "\nSpecial Ability: " + characters.get(selectedCharacter).getSpecialAbility());
        enemySpecialAbilitiesLabel.setText("Classic Strike: " + enemies.get(currentEnemy).getClassicStrikeAbility() + "\nJump Hit: " + enemies.get(currentEnemy).getJumpHitAbility() + "\nBlock Hit: " + enemies.get(currentEnemy).getBlockHitAbility() + "\nDefensive Rating: " + enemies.get(currentEnemy).getDefensiveRating() + "\nSpecial Ability: " + enemies.get(currentEnemy).getSpecialAbility());
    }

    public void generateRandomEnemy(){
        if (level>6){
            currentEnemy = 5;
            enemyLobbyName.setText("Final Boss: " + enemies.get(currentEnemy).getName());
        } else {
            currentEnemy = (int) (Math.random() * 5);
            enemyLobbyName.setText("Enemy: " + enemies.get(currentEnemy).getName());
        }

        enemyLobbyImage.setImage(enemies.get(currentEnemy).getFaceImage());
        enemyRecordLabel.setText("Record against this enemy: " + enemies.get(currentEnemy).getGamesLost() + " - " + (enemies.get(currentEnemy).getGamesPlayed()-enemies.get(currentEnemy).getGamesLost()));
        enemies.get(currentEnemy).handleRandomizeAbilities();
    }

    public void generateRecommendedDifficulty(){
        int recommendedDifficulty = (int) Math.round(5.0*level/3);
        if (recommendedDifficulty==0){
            recommendedDifficulty=1;
        }
        enemyDifficulty = recommendedDifficulty;
        enemyDifficultySlider.setValue(recommendedDifficulty);
        recommendedDifficultyLabel.setText("Recommended Difficulty Based On Level: " + recommendedDifficulty);
    }

    private int enemyDifficulty = 1;
    @FXML
    private void changeEnemyDifficulty(){
        enemyDifficulty = (int)enemyDifficultySlider.getValue();
        System.out.println(enemyDifficulty);
    }

    private int selectedCharacter = 0;
    private void lobbyCharacterClickedOn(int a) {
        if (characters.get(a).getUnlocked()){
            selectedCharacterLabel.setText("Selected Character:                    " + characters.get(a).getName());
            selectedCharacterImage.setImage(characters.get(a).getSelectedCharacterImage());
            selectedCharacter = a;
            displayAbilities();
        }

    }

    @FXML
    private void clickedOnMario() {
        lobbyCharacterClickedOn(0);
    }
    @FXML
    private void clickedOnKirby() {
        lobbyCharacterClickedOn(1);
    }
    @FXML
    private void clickedOnYoshi() {
        lobbyCharacterClickedOn(2);
    }
    @FXML
    private void clickedOnPeach() {
        lobbyCharacterClickedOn(3);
    }
    @FXML
    private void clickedOnLink() {
        lobbyCharacterClickedOn(4);
    }
    @FXML
    private void clickedOnPikachu() {
        lobbyCharacterClickedOn(5);
    }

    @FXML
    private void goToMapScreen(){
        lobbyAnchorPane.setVisible(false);
        chooseMapScreen.setVisible(true);
        playerMapScreenImage.setImage(characters.get(selectedCharacter).getSelectedCharacterImage());
        enemyMapScreenImage.setImage(enemies.get(currentEnemy).getFaceImage());
        map = (int)(Math.random()*maps.size());
    }

    int map = 0;
    public void populateMapsListView(){
        for (int i = 0; i < maps.size(); i++){
            mapsListView.getItems().add(i, maps.get(i).getName());
        }
        mapsListView.getItems().add(maps.size(), "Random");
    }

    @FXML
    private void selectMap(){
        int temp = mapsListView.getSelectionModel().getSelectedIndex();
        if (temp == maps.size()){
            map = (int)(Math.random()*maps.size());
        } else {
            map = temp;
        }
        mapLabel.setText("Map: " + maps.get(map).getName());

    }

    private int charactersUnlocked = 0;
    public void unlockCharacter() {
        unlockedNewCharacter = false;
        charactersUnlocked++;
        characters.get(charactersUnlocked).setUnlocked(true);
        newCharacterLabel.setText(characters.get(charactersUnlocked).getName());
        newCharacterImage.setImage(characters.get(charactersUnlocked).getSelectedCharacterImage());
        if (charactersUnlocked == 1) {
            setLobbyImage(kirbyLobbyImage);
        } else if (charactersUnlocked == 2) {
            setLobbyImage(yoshiLobbyImage);
        } else if (charactersUnlocked == 3) {
            setLobbyImage(peachLobbyImage);
        } else if (charactersUnlocked == 4) {
            setLobbyImage(linkLobbyImage);
        } else if (charactersUnlocked == 5) {
            setLobbyImage(pikachuLobbyImage);
        }
    }

    public void setLobbyImage(ImageView x) {
        x.setImage(characters.get(charactersUnlocked).getLobbyImageSource());
    }

    @FXML
    private void handleContinueNewCharacter() {
        characterUnlockedScreen.setVisible(false);
        displayLobby();
    }

    private int characterHealth = 500;
    private int enemyHealth = 500;
    private double characterDefensiveRating = 0;
    private double enemyDefensiveRating = 0;
    private int turn = 0;
    private boolean characterUsedSpecialAbility = false;
    private boolean enemyUsedSpecialAbility = false;
    @FXML
    private void startBattle(){
        characterHealth = 500;
        enemyHealth = 500;
        characterDefensiveRating = characters.get(selectedCharacter).getDefensiveRating();
        enemyDefensiveRating = enemies.get(currentEnemy).getDefensiveRating();
        characterUsedSpecialAbility = false;
        enemyUsedSpecialAbility = false;
        turn = (int)(Math.random()*2);
        specialAbilityButton.setVisible(false);
        specialAbilityLabel.setVisible(false);
        specialAbilityLabel.setText("(" + characters.get(selectedCharacter).getSpecialAbility() + ")");
        computerMoveLabel.setText("");
        enemies.get(currentEnemy).addGamesPlayed();
        setBattleScene();
        setBattleProgressBars();
        displayBattleDefensiveRatings();
        displayBattleButtonsAndLabels((turn%2)==1);
        turn++;
    }

    public void setBattleScene(){
        maps.get(map).addNumTimesPlayed();
        chooseMapScreen.setVisible(false);
        battleScene.setVisible(true);
        characterBattleImage.setImage(characters.get(selectedCharacter).getMainImage());
        enemyBattleImage.setImage(enemies.get(currentEnemy).getMainImage());
        int yCoord = maps.get(map).getPlayerImageViewYCoord();
        characterBattleImage.setY(yCoord);
        enemyBattleImage.setY(yCoord);
        battleMapImage.setImage(maps.get(map).getMapImage());
        progressBarNameLabel1.setText(characters.get(selectedCharacter).getName());
        progressBarNameLabel2.setText(enemies.get(currentEnemy).getName());
    }

    public void setBattleProgressBars(){
        characterBattleProgressBar.setProgress(characterHealth/500.0);
        enemyBattleProgressBar.setProgress(enemyHealth/500.0);
        characterHealthLabel.setText("Health: " + characterHealth + "/" + "500");
        enemyHealthLabel.setText("Health: " + enemyHealth + "/" + "500");
    }

    public void displayBattleDefensiveRatings(){
        if (characterDefensiveRating > 100){
            characterDefensiveRating = 100;
        }
        if (enemyDefensiveRating > 100){
            enemyDefensiveRating = 100;
        }
        characterDefensiveRatingLabel.setText("Defensive Rating: " + (int)characterDefensiveRating);
        enemyDefensiveRatingLabel.setText("Defensive Rating: " + (int)enemyDefensiveRating);
    }

    public void switchTurn(){
        if (characterHealth<0){
            losses++;
            changeArray("You lost to " + enemies.get(currentEnemy).getName() + " as " + characters.get(selectedCharacter).getName());
            displayGameEndScreen("Lost.");
        } else if(enemyHealth<0){
            if (level>6){
               continueFromResultButton.setVisible(false);
            }
            enemies.get(currentEnemy).addGamesLost();
            wins++;
            upgradeButton.setVisible(true);
            changeArray("You won against " + enemies.get(currentEnemy).getName() + " as " + characters.get(selectedCharacter).getName());
            displayGameEndScreen("Won!");
        } else {
            setBattleProgressBars();
            displayBattleDefensiveRatings();
            displayBattleButtonsAndLabels((turn % 2) == 1);
            turn++;
        }
    }

    public void displayBattleButtonsAndLabels(boolean x){
        if (enemyHealth<250 && !characterUsedSpecialAbility){
            specialAbilityButton.setVisible(x);
            specialAbilityLabel.setVisible(x);
        }
        classicStrikeButton.setVisible(x);
        jumpHitButton.setVisible(x);
        blockHitButton.setVisible(x);
        upgradeDefenseButton.setVisible(x);
        chooseYourMoveLabel.setVisible(x);
        computerMoveLabel.setVisible(x);
        computerTurnButton.setVisible(!x);

    }

    @FXML
    private void handleClassicStrike(){
        doClassicStrike();
    }
    @FXML
    private void handleJumpHit(){
        doJumpHit();
    }
    @FXML
    private void handleBlockHit(){
        doBlockHit();
    }
    @FXML
    private void handleUpgradeDefense(){
        doUpgradeDefense();
    }
    @FXML
    private void handleSpecialAbility(){
        doSpecialAbility();
    }
    @FXML
    private void doComputerTurn(){
        if (enemyUsedSpecialAbility || characterHealth >= 250){
            int num = randInt(1, 7);
            if (num == 1 || num == 2){
                doClassicStrike();
            } else if (num == 3 || num == 4){
                doJumpHit();
            } else if (num == 5 || num == 6){
                doBlockHit();
            } else if (num == 7){
                doUpgradeDefense();
            }
        } else {
            doSpecialAbility();
        }

    }


    public void doClassicStrike(){
        if (turn%2==0){
            int damage = characters.get(selectedCharacter).getClassicStrikeAbility();
            damage = (int) ((30*randInt(damage-5, damage+10))/enemyDefensiveRating);
            enemyHealth = enemyHealth - damage;
            enemyDefensiveRating = 0.8*enemyDefensiveRating;
        } else {
            int damage = enemies.get(currentEnemy).getClassicStrikeAbility();
            damage = (int) ((30*randInt(damage-5, damage+10)*((enemyDifficulty+2)/6.0))/characterDefensiveRating);
            characterHealth = characterHealth - damage;
            characterDefensiveRating = 0.8*characterDefensiveRating;
            computerMoveLabel.setText("Computer Last Move: Classic Strike");
        }
        switchTurn();
    }

    public void doJumpHit(){
        if (turn%2==0){
            int damage = characters.get(selectedCharacter).getJumpHitAbility();
            damage = (int) ((35*randInt(damage-5, damage+10))/enemyDefensiveRating);
            enemyHealth = enemyHealth - damage;
            characterDefensiveRating = 0.7*characterDefensiveRating;
        } else {
            int damage = enemies.get(currentEnemy).getJumpHitAbility();
            damage = (int) ((35*randInt(damage-5, damage+10)*((enemyDifficulty+2)/6.0))/characterDefensiveRating);
            characterHealth = characterHealth - damage;
            enemyDefensiveRating = 0.7*enemyDefensiveRating;
            computerMoveLabel.setText("Computer Last Move: Jump Hit");
        }
        switchTurn();
    }

    public void doBlockHit(){
        if (turn%2==0){
            int damage = characters.get(selectedCharacter).getBlockHitAbility();
            damage = (int) ((20*randInt(damage-5, damage+10))/enemyDefensiveRating);
            enemyHealth = enemyHealth - damage;
            characterDefensiveRating = 1.3*characterDefensiveRating;
        } else {
            int damage = enemies.get(currentEnemy).getBlockHitAbility();
            damage = (int) ((20*randInt(damage-5, damage+10)*((enemyDifficulty+2)/6.0))/characterDefensiveRating);
            characterHealth = characterHealth - damage;
            enemyDefensiveRating = 1.3*enemyDefensiveRating;
            computerMoveLabel.setText("Computer Last Move: Block Hit");
        }
        switchTurn();
    }

    public void doUpgradeDefense(){
        if (turn%2==0){
            characterDefensiveRating = characterDefensiveRating + randInt(2, 15);
        } else {
            enemyDefensiveRating = enemyDefensiveRating + randInt(2, 15);
            computerMoveLabel.setText("Computer Last Move: Upgrade Defense");
        }
        switchTurn();
    }

    public void doSpecialAbility(){
        if (turn%2==0){
            int damage = randInt(80, 130);
            damage = (int) (28*damage/enemyDefensiveRating);
            enemyHealth = enemyHealth - damage;
            enemyDefensiveRating = 0.6*enemyDefensiveRating;
            characterUsedSpecialAbility = true;
            specialAbilityButton.setVisible(false);
            specialAbilityLabel.setVisible(false);
        } else {
            int damage = randInt(80, 130);
            damage = (int) (28*damage*((enemyDifficulty+2)/6.0)/characterDefensiveRating);
            characterHealth = characterHealth - damage;
            characterDefensiveRating = 0.6*characterDefensiveRating;
            computerMoveLabel.setText("Computer Last Move: Special Ability: " + enemies.get(currentEnemy).getSpecialAbility());
            enemyUsedSpecialAbility = true;
        }
        switchTurn();
    }

    public void displayGameEndScreen(String result){
        battleScene.setVisible(false);
        gameEndScreen.setVisible(true);
        endResultLabel.setText("You " + result);
        double temp;
        if (result.equals("Lost.")) {
            temp = (enemyDifficulty + 8) / 120.0;
        } else {
            temp = (enemyDifficulty + 8) / 50.0;
        }
        System.out.println(Math.floor(temp)-Math.floor(level));
        if ((Math.floor(level + temp)-Math.floor(level))>0){
            unlockedNewCharacter = true;
        }
        level = level + temp;
        if (level>6){
            unlockedNewCharacter = false;
        }
    }

    @FXML
    private void exitGameEndScreen(){
        gameEndScreen.setVisible(false);
        checkUnlockedNewCharacter();
    }

    public void checkUnlockedNewCharacter(){
        if (unlockedNewCharacter){
            characterUnlockedScreen.setVisible(true);
            unlockCharacter();
        } else {
            displayLobby();
        }
    }

    @FXML
    private void handleUpgrade(){
        upgradeButton.setVisible(false);
        characters.get(selectedCharacter).upgradeAbilities();
        displayAbilities();
    }

    String[] lastThreeResults = {"", "", ""};
    public void changeArray(String x){
        lastThreeResults[0] = lastThreeResults[1];
        lastThreeResults[1] = lastThreeResults[2];
        lastThreeResults[2] = x;
        resultsLabel.setText(lastThreeResults[2] + "\n" + lastThreeResults[1] + "\n" + lastThreeResults[0]);
    }

    @FXML
    private void handleViewStats(){
        statsScreen.setVisible(true);
        lobbyAnchorPane.setVisible(false);
        totalWinsLossLabel.setText("Wins to Losses: " + wins + " - " + losses);
        int mapMostPlayed = 0;
        int max1 = 0;
        for (int i = 0; i<maps.size(); i++) {
           if (maps.get(i).getNumTimesPlayed()> max1){
               max1 = maps.get(i).getNumTimesPlayed();
               mapMostPlayed = i;
           }
        }

        int mostGamesEnemy = 0;
        int max2 = 0;
        for (int i = 0; i<enemies.size(); i++) {
            if (enemies.get(i).getGamesPlayed() > max2){
                max2 = enemies.get(i).getGamesPlayed();
                mostGamesEnemy = i;
            }
        }

        int mostGamesWonEnemy = 0;
        int max3 = 0;
        for (int i = 0; i<enemies.size(); i++) {
            if (enemies.get(i).getGamesLost()> max3){
                max3 = enemies.get(i).getGamesLost();
                mostGamesWonEnemy = i;
            }
        }

        additionalStatsLabel.setText("Map most played on: " + maps.get(mapMostPlayed).getName() + " (" + max1 + ")\n" + "Most games against enemy: " + enemies.get(mostGamesEnemy).getName() + " (" + max2 + ")\n" + "Most games won against enemy: " + enemies.get(mostGamesWonEnemy).getName() + " (" + max3 + ")");
    }

    @FXML
    private void backToLobbyScreen(){
        statsScreen.setVisible(false);
        lobbyAnchorPane.setVisible(true);
    }

    public int randInt(int lowerBound, int upperBound){
        return (int)(Math.random()*(upperBound-lowerBound+1) + lowerBound);
    }

}
