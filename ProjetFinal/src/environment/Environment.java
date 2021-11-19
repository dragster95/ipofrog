package environment;

import gameCommons.IEnvironment;
import util.Case;


public class Environment implements IEnvironment {
    private Car car;


    @Override
    public boolean isSafe(Case c) {
        if(c != car.getLeftPosition)
        return false;
    }

    @Override
    public boolean isWinningPosition(Case c) {

        return c.ord == height - 1 ;
    }

    @Override
    public void update() {

    }

    //TODO


}
