package org.academiadecodigo.bootcamp.objects;

import org.academiadecodigo.bootcamp.master.Sound;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.bootcamp.master.Handler;
import org.academiadecodigo.simplegraphics.pictures.Picture;


public class Princess extends GameObject{

    private Handler handler;
    private int maxVelGrav;
    private boolean falling;
    private Picture princess;
    private Sound yaySound;

    public Princess(int x, int y, Handler handler){

        this.handler = handler;
        falling = true;
        this.posX = x;
        this.posY = y;
        this.velX = 0;
        this.velY = 0;
        this.maxX = posX + 32;
        this.maxY = posY + 64;
        this.maxVelGrav = 15;

        yaySound = new Sound("resources/princess-yay-sound.wav");
        princess = new Picture(x, y, "resources/princess.png");
    }

    @Override
    public void render() {

        princess.draw();
    }

    @Override
    public void tick() {


        int prevPosX = posX;
        int prevPosY = posY;

        posX += velX;
        posY += velY;
        this.maxX = posX + 32;
        this.maxY = posY + 64;

        if(falling = true) {
            velY += Player.gravity;
        }

        if(velY > maxVelGrav && falling == true) {
            velY = maxVelGrav;
        }
        crashes();

        princess.translate(posX - prevPosX, posY - prevPosY);
    }

    private void crashes(){

        for(int i = 0; i < handler.getGameObjects().size(); i++){

            if(handler.getGameObjects().get(i) != this) {

                if (handler.detectCollision(getDown(), handler.getGameObjects().get(i))) {

                    if(handler.getGameObjects().get(i) instanceof Block || handler.getGameObjects().get(i) instanceof SpecialBlock) {

                        if(handler.getGameObjects().get(i) instanceof Block){
                            yaySound.play(true);
                        }
                        velY = 0;
                        posY = handler.getGameObjects().get(i).getPosY() - princess.getHeight();
                        falling = false;
                    }
                }
            }
        }
    }

    private Rectangle getDown(){

        return new Rectangle(posX + 10, posY + (princess.getHeight() / 4) * 3, princess.getWidth() - 20, princess.getHeight() / 4);
    }

    public Picture getPrincess(){
        return princess;
    }

}
