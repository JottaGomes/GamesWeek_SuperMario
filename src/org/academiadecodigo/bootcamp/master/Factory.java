package org.academiadecodigo.bootcamp.master;
import org.academiadecodigo.bootcamp.objects.*;

public class Factory {


    public static GameObject getNewObject(int x, int y, ObjectType objectType, Handler handler){

        if(objectType == ObjectType.PLAYER){

            return new Player(Field.PADDING + x, Field.PADDING + y, handler);
        }
        if(objectType == ObjectType.TURTLE){

            return new Turtle(Field.PADDING + x, Field.PADDING + y);
        }
        if(objectType == ObjectType.BLOCK){

            return new Block(Field.PADDING + x, Field.PADDING + y);
        }

        if(objectType == ObjectType.SPECIALBLOCK){

            return new SpecialBlock(Field.PADDING + x, Field.PADDING + y);
        }

        if(objectType == ObjectType.LAVAGENERATOR) {

            return new LavaGenerator(Field.PADDING + x, Field.PADDING + y, handler);
        }

        if(objectType == ObjectType.COINS){

            return new Coins(Field.PADDING + x, Field.PADDING + y);
        }

        if(objectType == ObjectType.PRINCESS) {

            return new Princess(Field.PADDING + x, Field.PADDING + y, handler);
        }
        if(objectType == ObjectType.BOWSER) {

            return new Bowser(Field.PADDING + x, Field.PADDING + y, handler);
        }




        return null;
    }
}
