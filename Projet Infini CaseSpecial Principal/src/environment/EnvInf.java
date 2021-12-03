package environment;

import frog.FrogInf;
import gameCommons.Game;
import gameCommons.IEnvironment;
import util.Case;
import util.Case;
import util.Direction;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.ArrayList;

public class EnvInf implements IEnvironment {
    private FrogInf frog;
    private Game game;
    private ArrayList<LaneInf> lanes;


    public EnvInf(Game game, FrogInf frog){
        this.game = game;
        this.lanes = new ArrayList<>();
        this.frog = frog;

        this.lanes.add(new LaneInf(game, 1, 0,frog));
        for(int i = 2; i < game.height - 1; i++) {
            this.lanes.add(new LaneInf(game, i, game.defaultDensity,frog));
        }
    }

    public void removeLane(){
        while(!lanes.isEmpty() && lanes.get(0).shouldBeDeleted()) {
            lanes.remove(0);
        }
    }

    public void addLane(){
        lanes.add(new LaneInf(game, game.height, game.defaultDensity, frog));
    }

    public void moveLaneinEnv(){
            for (LaneInf l:lanes) {
                l.moveLane();
            }
    }

    /* vérifie que chaque lane soit safe */
    @Override
    public boolean isSafe(Case caseFrog) {

        for (LaneInf l : lanes) {
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
        for (LaneInf l:lanes) {
            l.update();
        }
        for(LaneInf l: lanes){
            if(l.frogOnTrap(frog.getPosition())){
                l.activateEffect();
            }
            l.getTrapToDelete();
        }
        if (frog.getDirection() == Direction.up) {
            addLane();
            moveLaneinEnv();
            removeLane();
            frog.setDirection(null);
        }
        frog.updateScore();





    }


    //TODO


}

