package environment;

import gameCommons.IEnvironment;
import gameCommons.Game;
import environment.Car;
import util.Case;


public class Environment implements IEnvironment {
    private Car car;
    private Game game;


    @Override
    public boolean isSafe(Case c) {
        return car.PositionCarFrog(c);
    }

    @Override
    public boolean isWinningPosition(Case c) {

        return c.ord == game.height - 1 ;
    }

    @Override
    public void update() {

    }

    //TODO


}
