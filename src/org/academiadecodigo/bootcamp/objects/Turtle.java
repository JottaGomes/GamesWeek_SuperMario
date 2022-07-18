package org.academiadecodigo.bootcamp.objects;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Turtle extends GameObject{

    Picture[] frames;
    private Picture turtle;
    private boolean dead;
    private boolean directionLeft;
    private int time = 0;
    public Turtle(int x, int y){

        this.posX = x;
        this.posY = y;
        this.maxX = posX + 32;
        this.maxY = posY + 32;
        this.velX = 2;
        this.velY = 0;

        turtle = new Picture(x, y - 32, "resources/turtle-left.png");
        frames = new Picture[]{new Picture(x, y - 32, "resources/turtle-right.png"), new Picture(x, y - 32, "resources/turtle-left.png")};
    }

    public boolean isDead(){
        return dead;
    }
    public Picture getTurtle(){
        return turtle;
    }
    @Override
    public void render() {

        turtle.draw();
    }

    @Override
    public void tick() {

        if(velX > 0){
            directionLeft = false;
            turtle.delete();
            turtle = frames[0];
            turtle.draw();
        }
        if(velX < 0){
            directionLeft = true;
            turtle.delete();
            turtle = frames[1];
            turtle.draw();
        }

            if(time % 100 == 0){

                velX = - velX;
            }
            posX += velX;
            maxX = posX + 32;

            for(Picture pic : frames){

                pic.translate(velX, 0);
            }

            time++;
    }

    public void die(){

        dead = true;
    }
}
