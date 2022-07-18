package org.academiadecodigo.bootcamp.objects;

import org.academiadecodigo.bootcamp.master.Handler;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.LinkedList;

public class LavaGenerator extends GameObject {


    private int time = 0;
    private Handler handler;
    private LinkedList<Lava> lavaDrops;


    public LavaGenerator(int x, int y, Handler handler) {
        lavaDrops = new LinkedList<Lava>();
        this.posX = x;
        this.posY= y;
        this.handler = handler;
    }

    public LinkedList<Lava> getLavaDrops() {
        return lavaDrops;
    }

    @Override
    public void render() {

    }

    public void tick() {

        if (time % 80 == 0) {
            Lava lava = new Lava(posX, posY);
            lavaDrops.add(lava);
        }

        for(Lava lava : lavaDrops){

            lava.tick();
            if(lava.isDead()){

                lavaDrops.remove(lava);
                lava.lava.delete();
            }
        }
        time++;
    }


        public class Lava extends GameObject {

            private Picture lava;

            private boolean dead;

            private int maxVelGrav;

            public Lava(int x, int y) {

                lava = new Picture(x, y, "resources/lava.png");
                this.posX = x;
                this.posY = y;
                this.maxX = posX + Handler.blockSize;
                this.maxY = posY + Handler.blockSize;
                this.velX = 0;
                this.velY = 0;
                this.maxVelGrav = 10;
            }

            public boolean isDead() {
                return dead;
            }

            private Rectangle getHitBox(){
                return new Rectangle(posX, posY, Handler.blockSize, Handler.blockSize);
            }

            private void crashes(){
                for(int i = 0; i < handler.getGameObjects().size(); i++){

                    if(!(handler.getGameObjects().get(i) instanceof LavaGenerator) && !(handler.getGameObjects().get(i) instanceof Coins) && !(handler.getGameObjects().get(i) instanceof Player)) {

                        if(handler.detectCollision(getHitBox(), handler.getGameObjects().get(i))) {

                            dead = true;
                        }
                    }
                    if(handler.getGameObjects().get(i) instanceof Player){

                        if(handler.detectCollision(getHitBox(), handler.getGameObjects().get(i))){

                            lava.delete();
                        }
                    }
                }
            }

            public Picture getLava() {
                return lava;
            }

            @Override
            public void render() {
                lava.draw();
            }

            @Override
            public void tick() {
                render();
                velY += Player.gravity;
                posY += velY;
                maxY += velY;
                lava.translate(0, velY);
                if(velY > maxVelGrav){
                    velY = maxVelGrav;
                }
                crashes();
            }
        }
}
