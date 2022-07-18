package org.academiadecodigo.bootcamp.objects;

import org.academiadecodigo.bootcamp.master.Handler;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.LinkedList;

public class Bowser extends GameObject{

    private Handler handler;
    private LinkedList<FireBall> fireBalls;
    private Picture[] frames;
    private Picture bowser;
    private int lives;
    private boolean dead;
    private boolean directionLeft;
    private int time = 0;

    public Bowser(int x, int y, Handler handler){

        this.handler = handler;
        this.posX = x;
        this.posY = y;
        this.maxX = posX + 126;
        this.maxY = posY + 146;
        this.velX = 2;
        this.velY = 0;
        this.lives = 10;

        fireBalls = new LinkedList<>();
        bowser = new Picture(x, y - 32, "resources/bowser-left.png");
        frames = new Picture[]{new Picture(x, y - 32, "resources/bowser-right.png"), new Picture(x, y - 32, "resources/bowser-left.png")};
    }

    public boolean isDead(){
        return dead;
    }

    public Picture getBowser(){
        return bowser;
    }
    public LinkedList<FireBall> getFireBalls(){
        return fireBalls;
    }
    @Override
    public void render() {

        bowser.draw();
    }

    @Override
    public void tick() {

        if(velX > 0){
            directionLeft = false;
            bowser.delete();
            bowser = frames[0];
            bowser.draw();
        }
        if(velX < 0){
            directionLeft = true;
            bowser.delete();
            bowser = frames[1];
            bowser.draw();
        }

        if(time % 100 == 0){

            velX = - velX;
        }
        posX += velX;
        maxX = posX + 126;

        for(Picture pic : frames){

            pic.translate(velX, 0);
        }

        if (time % 120 == 0) {
            for(int i = 0; i < 5; i++) {
                fireBalls.add(new Bowser.FireBall(posX + (maxX - posX) / 2, posY + (maxY - posY) / 2 - 32, i));
            }
        }

        for(int i = 0; i < fireBalls.size(); i++){

            fireBalls.get(i).tick();
            if(fireBalls.get(i).isDead()){

                fireBalls.get(i).fireBall.delete();
                fireBalls.remove(fireBalls.get(i));
            }
        }

        if(lives == 0){
          die();
        }
        time++;
    }

    public void die(){

        dead = true;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public class FireBall extends GameObject {

        private Picture fireBall;

        private boolean dead;


        private int dir;

        public FireBall(int x, int y, int dir) {

            fireBall = new Picture(x, y, "resources/lava.png");

            this.dir = dir;
            this.posX = x;
            this.posY = y;
            this.maxX = posX + Handler.blockSize;
            this.maxY = posY + Handler.blockSize;
            this.velX = 0;
            this.velY = 0;
        }

        public boolean isDead() {
            return dead;
        }

        //hitBox para collisions
        private Rectangle getHitBox(){
            return new Rectangle(posX, posY, Handler.blockSize, Handler.blockSize);
        }

        private void crashes(){
            for(int i = 0; i < handler.getGameObjects().size(); i++){

                if((handler.getGameObjects().get(i) instanceof Block)) {

                    if(handler.detectCollision(getHitBox(), handler.getGameObjects().get(i))) {

                        dead = true;
                    }
                }
                if(handler.getGameObjects().get(i) instanceof Player){

                    if(handler.detectCollision(getHitBox(), handler.getGameObjects().get(i))){

                        fireBall.delete();
                    }
                }
            }
        }

        public Picture getFireBall() {
            return fireBall;
        }

        @Override
        public void render() {
            fireBall.draw();
        }

        @Override
        public void tick() {
            render();

            if(dir == 0){
                velX = -6;
                velY = 0;
                posY += velY;
                maxY += velY;
                posX += velX;
                maxX += velX;
            }
            if(dir == 1){
                velX = -3;
                velY = -3;
                posY += velY;
                maxY += velY;
                posX += velX;
                maxX += velX;
            }
            if(dir == 2){
                velX = 0;
                velY = -6;
                posY += velY;
                maxY += velY;
                posX += velX;
                maxX += velX;
            }
            if(dir == 3){
                velX = 3;
                velY = -3;
                posY += velY;
                maxY += velY;
                posX += velX;
                maxX += velX;
            }
            if(dir == 4){
                velX = 6;
                velY = 0;
                posY += velY;
                maxY += velY;
                posX += velX;
                maxX += velX;
            }

            fireBall.translate(velX, velY);

            crashes();
        }
    }
}
