package frog;

import gameCommons.Game;
import gameCommons.IFrog;
import util.Case;
import util.Direction;

public class Frog implements IFrog {

	private Game game;
	private Case positionGrenouille;
	private Direction directionGrenouille;

	public Frog(Game game){
		this.game = game;

		this.directionGrenouille = Direction.up;
		this.positionGrenouille = new Case(game.width/2,0);
	}


	@Override
	public Case getPosition() {
		return positionGrenouille;
	}

	@Override
	public Direction getDirection() {
		return directionGrenouille;
	}

	@Override
	public void move(Direction key) {



		if(key == Direction.up){
			positionGrenouille = new Case(positionGrenouille.absc, positionGrenouille.ord+1);
		}
		if(key == Direction.down){
			positionGrenouille = new Case(positionGrenouille.absc, positionGrenouille.ord -1);
		}
		if(key == Direction.right){
			positionGrenouille = new Case(positionGrenouille.absc+1, positionGrenouille.ord );
		}
		if(key == Direction.left){
			positionGrenouille = new Case(positionGrenouille.absc-1, positionGrenouille.ord );
		}


	}
}
