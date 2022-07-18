package org.academiadecodigo.bootcamp.objects;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Coins extends GameObject {

    private Picture coins;
    private boolean dead;

    public Coins (int x, int y){
    this.posX = x;
    this.posY = y;
    this.maxX = x + 30;
    this.maxY = y + 30;

    coins = new Picture(x, y, "resources/coin.png");
    }

    public boolean isDead() {
        return dead;
    }

    public Picture getCoins() {
        return coins;
    }

    @Override
    public void render() {

        coins.draw();
    }

    @Override
    public void tick() {

    }

    public void die(){
        dead = true;
    }
}
