package org.academiadecodigo.bootcamp.objects;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class SpecialBlock extends GameObject{


    private Picture specialBlock;

    public SpecialBlock(int x, int y){

        this.posX = x;
        this.posY = y;
        this.maxX = this.getPosX() + 32;
        this.maxY = this.getPosY() + 32;

        specialBlock = new Picture(x, y, "resources/block.png");
    }

    public Picture getSpecialBlock() {
        return specialBlock;
    }
    @Override
    public void render() {
        specialBlock.draw();
    }


    @Override
    public void tick() {

    }
}
