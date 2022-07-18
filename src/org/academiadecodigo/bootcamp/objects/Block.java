package org.academiadecodigo.bootcamp.objects;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Block extends GameObject{

    private Picture block;

    public Block(int x, int y){

        this.posX = x;
        this.posY = y;
        this.maxX = this.getPosX() + 32;
        this.maxY = this.getPosY() + 32;

        block = new Picture(x, y, "resources/block.png");

    }

    public Picture getBlock() {
        return block;
    }

    @Override
    public void render() {
        block.draw();
    }


    @Override
    public void tick() {

    }
}
