package org.academiadecodigo.bootcamp.master;
import org.academiadecodigo.bootcamp.objects.*;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.LinkedList;

public class    Handler {

    public static final int blockSize = 32;
    private Field field;
    private LinkedList<GameObject> gameObjects;
    private  Chars lives;
    private Chars coins;
    private boolean freePrincess;
    private int level;
    private Picture picturelvl;


    public Handler(Field field){

        gameObjects = new LinkedList<GameObject>();
        this.field = field;
        this.level = 1;
    }

    public void buildLevel(int level){

        switch(level){
            case 1:
                buildLevelOne();
                break;
            case 2:
                buildLevelTwo();
                break;
        }

    }
    public void buildLevelOne(){

        // Coins
        for(int i = 0; i < 9 * blockSize; i += 3 * blockSize) {
            gameObjects.add(Factory.getNewObject(i + 3 * blockSize  - blockSize / 2, 21 * blockSize, ObjectType.COINS, this));
            gameObjects.add(Factory.getNewObject(i + 17 * blockSize  - blockSize / 2, 17 * blockSize, ObjectType.COINS, this));
            gameObjects.add(Factory.getNewObject(i + 40 * blockSize, 19 * blockSize, ObjectType.COINS, this));
            gameObjects.add(Factory.getNewObject(i + 40 * blockSize, 25 * blockSize, ObjectType.COINS, this));
            gameObjects.add(Factory.getNewObject(i + 17 * blockSize - blockSize / 2 , field.getHeight() - 23 * blockSize, ObjectType.COINS, this));
        }

        gameObjects.add(Factory.getNewObject(29 * blockSize, 12 * blockSize, ObjectType.COINS, this));
        gameObjects.add(Factory.getNewObject(31 * blockSize, 3 * blockSize, ObjectType.COINS, this));
        gameObjects.add(Factory.getNewObject(40 * blockSize - blockSize / 2, 7 * blockSize, ObjectType.COINS, this));
        gameObjects.add(Factory.getNewObject(40 * blockSize, 15 * blockSize, ObjectType.COINS, this));
        gameObjects.add(Factory.getNewObject(47 * blockSize, 11 * blockSize, ObjectType.COINS, this));

        // CHARS
        lives = new Chars(825, 80, 3, Color.WHITE, 20, 17);
        coins = new Chars(395, 80, 0, Color.WHITE, 25, 17);


        picturelvl =new Picture(1200, 60, "resources/LEVEL1.png");
        new Chars(775, 83, "X", Color.WHITE, 15, 10);
        new Chars(340, 83, "X", Color.WHITE, 15, 10);


        gameObjects.add(Factory.getNewObject(12 * blockSize + blockSize / 2, blockSize, ObjectType.LAVAGENERATOR, this));
        gameObjects.add(Factory.getNewObject(field.getWidth() - 7 * blockSize + blockSize / 2, blockSize, ObjectType.LAVAGENERATOR, this));
        gameObjects.add(Factory.getNewObject(field.getWidth() - 15 * blockSize + blockSize / 2, blockSize, ObjectType.LAVAGENERATOR, this));
        gameObjects.add(Factory.getNewObject(field.getWidth() - 24 * blockSize + blockSize / 2, blockSize, ObjectType.LAVAGENERATOR, this));


        // PLAYER AND TURTLES
        gameObjects.add(Factory.getNewObject(100, field.getHeight() - 4 * blockSize, ObjectType.PLAYER, this));
        gameObjects.add(Factory.getNewObject(3 * blockSize, 3 * blockSize, ObjectType.PRINCESS, this));

        gameObjects.add(Factory.getNewObject(field.getWidth() - 30 * blockSize, field.getHeight() - 2 * blockSize, ObjectType.TURTLE, this));
        gameObjects.add(Factory.getNewObject(field.getWidth() - 27 * blockSize, field.getHeight() - 22 * blockSize, ObjectType.TURTLE, this));
        gameObjects.add(Factory.getNewObject(field.getWidth() - 42 * blockSize, field.getHeight() - 6 * blockSize, ObjectType.TURTLE, this));
        gameObjects.add(Factory.getNewObject(field.getWidth() - 27 * blockSize, field.getHeight() - 10 * blockSize, ObjectType.TURTLE, this));
        gameObjects.add(Factory.getNewObject(field.getWidth() - 15 * blockSize, field.getHeight() - 2 * blockSize, ObjectType.TURTLE, this));

        // BLOCK'S
        for(int i = 0; i < field.getWidth(); i += blockSize){ // Ground and Ceiling

            gameObjects.add(Factory.getNewObject(i, field.getHeight() - blockSize, ObjectType.BLOCK, this));
            gameObjects.add(Factory.getNewObject(i, 0, ObjectType.BLOCK, this ));
        }

        for(int i = blockSize; i < field.getHeight() - blockSize; i += blockSize){ // Walls

            gameObjects.add(Factory.getNewObject(field.getWidth() - blockSize, i, ObjectType.BLOCK, this));
            gameObjects.add(Factory.getNewObject(0, i, ObjectType.BLOCK, this));
        }

        for(int i = 0; i < 10 * blockSize; i += blockSize){ // Platforms of 10


            gameObjects.add(Factory.getNewObject(i + 1 * blockSize, field.getHeight() - 5 * blockSize, ObjectType.BLOCK, this));

            gameObjects.add(Factory.getNewObject(i + 15 * blockSize , field.getHeight() - 9 * blockSize, ObjectType.BLOCK, this));
            gameObjects.add(Factory.getNewObject(i + 15 * blockSize , field.getHeight() - 21 * blockSize, ObjectType.BLOCK, this));

            gameObjects.add(Factory.getNewObject(field.getWidth() - 21 * blockSize, field.getHeight() / 2 + i, ObjectType.BLOCK, this));
        }

        for(int i = 0; i < 11 * blockSize; i += blockSize) {

            gameObjects.add(Factory.getNewObject(i + field.getWidth() - 12 * blockSize, field.getHeight() - 7 * blockSize, ObjectType.BLOCK, this));

        }

        for(int i = 0; i < 9 * blockSize; i += blockSize) {

            gameObjects.add(Factory.getNewObject(38 * blockSize, field.getHeight() - 19 * blockSize + i, ObjectType.BLOCK, this));
        }

        for(int i = 0; i < 3 * blockSize; i += blockSize){


            gameObjects.add(Factory.getNewObject(i + 39 * blockSize , field.getHeight() - 11 * blockSize, ObjectType.BLOCK, this));
            gameObjects.add(Factory.getNewObject(i + 46 * blockSize , field.getHeight() - 15 * blockSize, ObjectType.BLOCK, this));
            gameObjects.add(Factory.getNewObject(i + 39 * blockSize , field.getHeight() - 19 * blockSize, ObjectType.BLOCK, this));

        }

        for(int i = 0; i < 5 * blockSize; i += blockSize) {

            gameObjects.add(Factory.getNewObject(i + 29 * blockSize , field.getHeight() - 23 * blockSize, ObjectType.BLOCK, this));

            gameObjects.add(Factory.getNewObject(i + blockSize , 5 * blockSize, ObjectType.SPECIALBLOCK, this));
            gameObjects.add(Factory.getNewObject(6 * blockSize , i + blockSize, ObjectType.SPECIALBLOCK, this));
        }

            for (GameObject obj : gameObjects) {

            obj.render();
        }
    }

    public void buildLevelTwo(){

        // CHARS

        lives = new Chars(825, 80, 3, Color.WHITE, 20, 17);
        coins = new Chars(395, 80, 0, Color.WHITE, 25, 17);


        picturelvl = new Picture(1200, 60, "resources/LEVEL2.png");
        new Chars(775, 83, "X", Color.WHITE, 15, 10);
        new Chars(340, 83, "X", Color.WHITE, 15, 10);


        gameObjects.add(Factory.getNewObject(8 * blockSize, blockSize, ObjectType.LAVAGENERATOR, this));
        gameObjects.add(Factory.getNewObject(16 * blockSize, blockSize, ObjectType.LAVAGENERATOR, this));
        gameObjects.add(Factory.getNewObject(24 * blockSize, blockSize, ObjectType.LAVAGENERATOR, this));
        gameObjects.add(Factory.getNewObject(32 * blockSize, blockSize, ObjectType.LAVAGENERATOR, this));
        gameObjects.add(Factory.getNewObject(40 * blockSize, blockSize, ObjectType.LAVAGENERATOR, this));


        // PLAYER AND BOWSER
        gameObjects.add(Factory.getNewObject(100, field.getHeight() - 4 * blockSize, ObjectType.PLAYER, this));
        gameObjects.add(Factory.getNewObject(field.getWidth() / 2, field.getHeight() - 4 * blockSize, ObjectType.BOWSER, this));


        // BLOCKS
        for(int i = 0; i < field.getWidth(); i += blockSize){ // Ground and Ceiling

            gameObjects.add(Factory.getNewObject(i, field.getHeight() - blockSize, ObjectType.BLOCK, this));
            gameObjects.add(Factory.getNewObject(i, 0, ObjectType.BLOCK, this ));
        }

        for(int i = blockSize; i < field.getHeight() - blockSize; i += blockSize){ // Walls

            gameObjects.add(Factory.getNewObject(field.getWidth() - blockSize, i, ObjectType.BLOCK, this));
            gameObjects.add(Factory.getNewObject(0, i, ObjectType.BLOCK, this));
        }

        for (GameObject obj : gameObjects) {

            obj.render();
        }
    }

    public LinkedList<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void updateGameObjects(){

        int turtleCounter = 0;
        int coinCounter = 0;

        for (int i = 0; i < gameObjects.size(); i++) {

            if (gameObjects.get(i) instanceof Player) {

                if (((Player) gameObjects.get(i)).isDead() == true) {

                    ((Player) gameObjects.get(i)).getPlayer().delete();
                    gameObjects.remove(gameObjects.get(i));

                }
            }
            if (gameObjects.get(i) instanceof Turtle) {

                turtleCounter++;
                if (((Turtle) gameObjects.get(i)).isDead() == true) {

                    ((Turtle) gameObjects.get(i)).getTurtle().delete();
                    gameObjects.remove(gameObjects.get(i));
                    turtleCounter--;
                }
            }
            if (gameObjects.get(i) instanceof Coins) {

                coinCounter++;
                if (((Coins) gameObjects.get(i)).isDead() == true) {

                    ((Coins) gameObjects.get(i)).getCoins().delete();
                    gameObjects.remove(gameObjects.get(i));
                    coins.setNum(coins.getNum()+1);
                    coinCounter--;
                }
            }
            if (gameObjects.get(i) instanceof Bowser) {

                if (((Bowser) gameObjects.get(i)).isDead() == true) {

                    ((Bowser) gameObjects.get(i)).getBowser().delete();
                    gameObjects.remove(gameObjects.get(i));
                }
            }
            if(freePrincess == true) {
                if (gameObjects.get(i) instanceof SpecialBlock) {

                    ((SpecialBlock) gameObjects.get(i)).getSpecialBlock().delete();
                    gameObjects.remove(gameObjects.get(i));
                }
            }
        }
        if(coinCounter == 0 && turtleCounter == 0) {

            freePrincess = true;
        }

    }

    public boolean detectCollision(Rectangle first, GameObject second){


            boolean crossY = first.getY() + first.getHeight() > second.getPosY() && first.getY() < second.getMaxY();
            boolean crossX = first.getX() + first.getWidth() > second.getPosX() && first.getX() < second.getMaxX();

                if(crossY && crossX) {

                    return true;
                }
            return false;
    }
    public Chars getLives(){
        return lives;
    }

    public Chars getCoins() {
        return coins;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
