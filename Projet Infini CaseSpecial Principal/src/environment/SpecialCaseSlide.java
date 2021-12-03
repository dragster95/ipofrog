package environment;

import frog.FrogInf;
import gameCommons.Game;
import util.Case;
import graphicalElements.Element;
import util.Direction;

import java.awt.*;

public class SpecialCaseSlide {
    private Game game;
    private Case positionSpecialCase;
    private FrogInf frog;
    private Color color;
    private int random;
    private int mem;

    public boolean isSafe;
    public boolean toDelete;





    public SpecialCaseSlide(Game game, Case positionSpecialCase, FrogInf frog) {
        this.game = game;
        this.positionSpecialCase = positionSpecialCase;
        this.frog = frog;
        this.mem = 0;

        this.random = game.randomGen.nextInt(4)+1;
        this.isSafe = true;
        this.toDelete = false;



        switch (random){
            case 1:
                this.color = Color.CYAN;
                break;
            case 2:
                this.color = Color.RED;
                break;
            case 3:
                this.color = Color.MAGENTA;
                break;
            case 4:
                this.color = Color.YELLOW;
                break;
        }




    }


    public boolean getSafety(){return isSafe;}

    public boolean frogOnTrap(Case caseFrog){
        return caseFrog.absc == positionSpecialCase.absc && positionSpecialCase.ord == caseFrog.ord;
    }

    public void effect(){
        switch (random){
            case 1:
                game.moveLane();
                break;
            case 2:
                this.isSafe = false;

                break;
            case 3:
                frog.setPositionGrenouille(new Case(frog.getPosition().absc,frog.getPosition().ord - 1));
                break;
            case 4:
                frog.bonusScore(frog.getScore() + 1);
                this.color = Color.GRAY;
                toDelete = true;
                addToGraphics();

                break;
        }
    }

    public void addToGraphics(){
            game.getGraphic().add(new Element(positionSpecialCase, color));
    }

    public Case getPosition(){return this.positionSpecialCase;}

    public void setPosition(Case newCase){ this.positionSpecialCase = new Case(newCase.absc, newCase.ord);}

    public boolean getTrapToDelete(){ return toDelete;}



}


