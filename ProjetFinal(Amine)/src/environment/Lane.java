package environment;

import java.util.ArrayList;

import gameCommons.IEnvironment;
import util.Case;
import environment.Environment;
import gameCommons.Game;

public class Lane {
	private Game game;
	private int ord;
	private int speed;
	private ArrayList<Car> cars ;
	private boolean leftToRight;
	private double density;
	private int timer = 0;

	// TODO : Constructeur(s)
	public Lane(Game game, int ord, boolean leftToRight){
		this.game = game;
		this.ord = ord;
		this.speed = game.randomGen.nextInt(game.minSpeedInTimerLoops);
		this.cars = new ArrayList<>();
		this.leftToRight = game.randomGen.nextBoolean();
		this.density = game.defaultDensity;


	}


	public void update() {

		// TODO

		// Toutes les voitures se d�placent d'une case au bout d'un nombre "tic
		// d'horloge" �gal � leur vitesse
		// Notez que cette m�thode est appel�e � chaque tic d'horloge

		// Les voitures doivent etre ajoutes a l interface graphique meme quand
		// elle ne bougent pas

		// A chaque tic d'horloge, une voiture peut �tre ajout�e
		timer++;
		if(timer > speed) {
			mayAddCar();
			moveCarsonLane();
			removeCars();
			timer = 0;
		}
	}

	// TODO : ajout de methodes

	/*
	 * Fourni : mayAddCar(), getFirstCase() et getBeforeFirstCase() 
	 */

	/**
	 * Ajoute une voiture au d�but de la voie avec probabilit� �gale � la
	 * densit�, si la premi�re case de la voie est vide
	 */
	private void mayAddCar() {
		if (isSafe(getFirstCase()) && isSafe(getBeforeFirstCase())) {
			if (game.randomGen.nextDouble() < density) {
				cars.add(new Car(game, getBeforeFirstCase(), leftToRight));
			}
		}
	}

	/* Bouge chaque de l'array cars */
	public void moveCarsonLane() {
		for (int i = 0; i < cars.size(); i++ ) {
			cars.get(i).move();
		}
	}

	/* Vérifie case chaque voiture est vide */
	public boolean isSafe(Case c) {
		for (int i = 0; i < cars.size(); i++) {
			if (cars.get(i).PositionCarEmpty(c) != true) {
				return false;
			}
		}
		return true;
	}

	/* retire voiture si fini trajet*/
	private void removeCars(){
		for(int i = 0; i < cars.size(); i++){
			if(cars.get(i).getDirection() == true && cars.get(i).getLeftPosition() == new Case(game.width , ord) ){
				cars.remove(i);
			}else if (cars.get(i).getDirection() == false && cars.get(i).getLeftPosition() == new Case(-1, ord)){
				cars.remove(i);
			}
		}

	}

	private Case getFirstCase() {
		if (leftToRight) {
			return new Case(0, ord);
		} else
			return new Case(game.width - 1, ord);
	}

	private Case getBeforeFirstCase() {
		if (leftToRight) {
			return new Case(-1, ord);
		} else
			return new Case(game.width, ord);
	}

}
