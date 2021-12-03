package frog;

import environment.EnvInf;
import gameCommons.Game;
import gameCommons.IFrog;
import util.Case;
import util.Direction;

public class FrogInf implements IFrog {
    private Game game;
    private Case positionGrenouille;
    private Direction directionGrenouille;
    private int upPress;
    private int downPress;
    private int latitude;
    private int score;

    public FrogInf(Game game){
        this.game = game;
        this.upPress = 0;
        this.downPress = 0;
        this.latitude = 0;
        this.score = 0;
        this.directionGrenouille = Direction.up;
        this.positionGrenouille = new Case(game.width/2,0);
    }




    @Override
    public Case getPosition() {
        return positionGrenouille;
    }

    @Override
    public Direction getDirection() {
        return this.directionGrenouille;
    }

    public void setDirection(Direction d){
        this.directionGrenouille = d;
    }

    public int getScore(){
        return score;
    }

    public void updateScore(){
        this.latitude = upPress - downPress;
        if(latitude > score){
            score = latitude;
        }

    }

    public void bonusScore(int i){
        this.score = i;
    }

    public void setDefaultPosition(){
        positionGrenouille = new Case(game.width/2,0);

    }

    public void setPositionGrenouille(Case caseFrog){
        positionGrenouille = new Case(caseFrog.absc, caseFrog.ord);
    }





    @Override
    public void move(Direction key) {

        if(key == Direction.up){
            if (positionGrenouille.ord < 5){
                positionGrenouille = new Case(positionGrenouille.absc, positionGrenouille.ord + 1);
            }else{
                this.directionGrenouille = key;
            }
            this.upPress++;
        }
        if(key == Direction.down){
            if (positionGrenouille.ord != 0 && positionGrenouille.ord <= 5){
                positionGrenouille = new Case(positionGrenouille.absc, positionGrenouille.ord - 1);
                this.downPress++;
            }


        }
        if(key == Direction.right){
            if(positionGrenouille.absc < game.width - 1){
                positionGrenouille = new Case(positionGrenouille.absc + 1, positionGrenouille.ord);
            }
        }
        if(key == Direction.left){
            if(positionGrenouille.absc > 0){
                positionGrenouille = new Case(positionGrenouille.absc - 1, positionGrenouille.ord);
            }

        }




    }




}


