package environment;

import java.util.ArrayList;

import gameCommons.IEnvironment;
import graphicalElements.FroggerGraphic;
import util.Case;
import environment.Car;
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
	public Lane(Game game, int ord, double density){
		this.game = game;
		this.ord = ord;
		this.speed = game.randomGen.nextInt(game.minSpeedInTimerLoops);
		this.cars = new ArrayList<>();
		this.leftToRight = game.randomGen.nextBoolean();
		this.density = density;



	}

	/* A changer */
	public Lane(Game game, int ord) {
		this(game, ord, game.defaultDensity);
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
			moveCarsonLane();
			mayAddCar();


			removeCars();
			timer = 0;
		}
		for (int i = 0; i < cars.size(); i++ ) {
			cars.get(i).addToGraphics();
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
		if (isSafe(getFirstCase()) && isSafe(getBeforeFirstCase()) && this.game.randomGen.nextDouble() < this.density) {
				cars.add(new Car(game, getBeforeFirstCase(), leftToRight));


		}
	}

	/* Bouge chaque de l'array cars */
	public void moveCarsonLane() {
		for (int i = 0; i < cars.size(); i++ ) {
			cars.get(i).move();
		}
	}

	/* Vérifie case chaque voiture est vide */
	public boolean isSafe(Case caseFrog) {

		for (Car c : cars) {
			if(c.CarOnFrog(caseFrog)){
				return false;
			}
		}
		return true;
	}

	/* retire voiture si fini trajet*/
	private void removeCars() {

		while(!cars.isEmpty() && cars.get(0).shoulBeDeleted()) {
			cars.remove(0);
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
