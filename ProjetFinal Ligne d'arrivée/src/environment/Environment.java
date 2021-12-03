package environment;

import gameCommons.IEnvironment;
import gameCommons.Game;
import environment.Car;
import environment.Lane;
import util.Case;
import java.util.ArrayList;


public class Environment implements IEnvironment {
    private Game game;
    private ArrayList<Lane> lanes;

    public Environment(Game game){
        this.game = game;
        this.lanes = new ArrayList<>();

        this.lanes.add(new Lane(game, 0, 0.0 ));

        for(int i = 1; i < game.height - 1; i++) {
            this.lanes.add(new Lane(game, i));
        }

        this.lanes.add(new Lane(game, game.height - 1, 0.0));

    }

    /* vérifie que chaque lane soit safe */
    @Override
    public boolean isSafe(Case caseFrog) {

        for (Lane l : lanes) {
            if(!l.isSafe(caseFrog)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isWinningPosition(Case caseFrog) {

        return caseFrog.ord == game.height - 1 ;
    }

    /* vérifie que tout soit safe et win or lose */

    @Override
    public void update() {

        for(int i = 0; i < lanes.size(); i++){
            lanes.get(i).update();
        }

    }

    //TODO


}
