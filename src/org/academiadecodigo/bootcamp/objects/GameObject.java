package org.academiadecodigo.bootcamp.objects;

public abstract class GameObject {

    protected int posX;
    protected int posY;
    protected int maxX;
    protected int maxY;

    protected int velX;
    protected int velY;

    public abstract void render();
    public abstract void tick();

    public int getPosX(){

        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }
}
