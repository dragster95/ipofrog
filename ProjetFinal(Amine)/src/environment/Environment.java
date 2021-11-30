package environment;

import gameCommons.IEnvironment;
import gameCommons.Game;
import environment.Car;
import util.Case;
import java.util.ArrayList;


public class Environment implements IEnvironment {
    private Game game;
    private ArrayList<Lane> lanes;

    public Environment(Game game){
        this.game = game;
        this.lanes = new ArrayList<>();

    }

    /* vérifie que chaque lane soit safe */
    @Override
    public boolean isSafe(Case c) {
        for (int i = 0; i < lanes.size(); i++) {
            if (lanes.get(i).isSafe(c) != true) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isWinningPosition(Case c) {

        return c.ord == game.height - 1 ;
    }

    /* vérifie que tout soit safe et win or lose */

    @Override
    public void update() {
        int i = 0;
        while(i < lanes.size()){
            lanes.get(i).update();
            i++;
        }

    }

    //TODO


}
