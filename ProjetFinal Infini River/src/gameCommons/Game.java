package gameCommons;

import java.awt.Color;
import java.util.Random;

import environment.SpecialCaseSlide;
import graphicalElements.Element;
import graphicalElements.IFroggerGraphics;
import util.Case;


public class Game {

	public final Random randomGen = new Random();

	// Caracteristique de la partie
	public final int width;
	public final int height;
	public final int minSpeedInTimerLoops;

	public double defaultDensity;

	private double highestDensity = 0.2;
	private double lowestDensity = 0.05;

	// Lien aux objets utilis�s
	private IEnvironment environment;
	private IFrog frog;
	private IFroggerGraphics graphic;
	private SpecialCaseSlide specialCase;

	/**
	 * 
	 * @param graphic
	 *            l'interface graphique
	 * @param width
	 *            largeur en cases
	 * @param height
	 *            hauteur en cases
	 * @param minSpeedInTimerLoop
	 *            Vitesse minimale, en nombre de tour de timer avant d�placement
	 *
	 *
	 */
	public Game(IFroggerGraphics graphic, int width, int height, int minSpeedInTimerLoop, double defaultDensity) {
		super();
		this.graphic = graphic;
		this.width = width;
		this.height = height;
		this.minSpeedInTimerLoops = minSpeedInTimerLoop;
		this.defaultDensity = defaultDensity;
	}

	/**
	 * Lie l'objet frog � la partie
	 * 
	 * @param frog
	 */
	public void setFrog(IFrog frog) {
		this.frog = frog;
	}

	/**
	 * Lie l'objet environment a la partie
	 * 
	 * @param environment
	 */
	public void setEnvironment(IEnvironment environment) {
		this.environment = environment;
	}

	/**
	 * 
	 * @return l'interface graphique
	 */
	public IFroggerGraphics getGraphic() {
		return graphic;
	}
	/** is winning pos*/


	/**
	 * Teste si la partie est perdue et lance un �cran de fin appropri� si tel
	 * est le cas
	 * 
	 * @return true si le partie est perdue
	 */
	public boolean testLose() {
		 if(environment.isSafe(frog.getPosition()) == false){
			graphic.endGameScreen("Score : " + frog.getScore());
			return true;
		}return false;


	}

	/**
	 * Teste si la partie est gagnee et lance un �cran de fin appropri� si tel
	 * est le cas
	 * 
	 * @return true si la partie est gagn�e
	 */
	public boolean testWin() {

		if(environment.isWinningPosition(frog.getPosition())){
			graphic.endGameScreen("You win");
			return true;
		}return false;

	}

	/**
	 * Actualise l'environnement, affiche la grenouille et verifie la fin de
	 * partie.
	 * Si partie fini freeze l'affichage pour éviter artefact visuel.
	 */
	public void update() {
		graphic.clear();
		environment.update();
		this.graphic.add(new Element(frog.getPosition(), Color.GREEN));
		if(testLose() || testWin()){
			testLose();
			testWin();
			graphic.freeze();
		}
	}

}
