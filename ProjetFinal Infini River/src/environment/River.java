package environment;

import java.awt.*;
import java.util.ArrayList;

import frog.FrogInf;
import graphicalElements.Element;
import util.Case;
import gameCommons.Game;

public class River extends LaneInf {


    public River(Game game, int ord, double density, FrogInf frog) {
        super(game, ord, density, frog);
        traps.clear();
    }

    @Override
    public boolean isSafe(Case caseFrog) {
        return !super.isSafe(caseFrog);
    }

    @Override
    public void mayAddCar() {
        if (isSafe(getFirstCase()) && isSafe(getBeforeFirstCase()) && this.game.randomGen.nextDouble() < this.density) {
            cars.add(new Car(game, getBeforeFirstCase(), leftToRight, true));


        }
    }

    @Override
    public void moveCarsonLane() {
        for (Car c : cars) {
            c.move();
            if(isSafe(frog.getPosition())){
                frog.setPositionGrenouille(new Case(c.getPosition().absc,frog.getPosition().ord ));
            }
            c.addToGraphics();
        }



    }

    public void addRiverToGraphics(){
        for (int i = 0; i < game.width; i++) {
            game.getGraphic().add(new Element(i, ord, Color.CYAN));
            }
    }


    @Override
    public void update(){

        timer++;
        if(timer > speed) {
            moveCarsonLane();
            mayAddCar();


            removeCars();
            timer = 0;
        }
        addRiverToGraphics();
        for (Car c : cars) {c.addToGraphics();}

    }
}


