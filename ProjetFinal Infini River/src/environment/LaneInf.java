package environment;

import frog.FrogInf;
import gameCommons.Game;
import util.Case;

import java.util.ArrayList;

public class LaneInf {
    protected Game game;
    protected int ord;
    protected FrogInf frog;
    protected int speed;
    protected ArrayList<Car> cars ;
    protected ArrayList<SpecialCaseSlide> traps;
    protected boolean leftToRight;
    protected boolean isRiver;
    protected double density;
    protected int random;
    protected int timer;



    // TODO : Constructeur(s)
    public LaneInf(Game game, int ord, double density, FrogInf frog){
        this.game = game;
        this.ord = ord;
        this.frog = frog;
        this.speed = game.randomGen.nextInt(game.minSpeedInTimerLoops);
        this.cars = new ArrayList<>();
        this.traps = new ArrayList<>();
        this.leftToRight = game.randomGen.nextBoolean();
        this.density = density;
        this.random = game.randomGen.nextInt(3);


        setTrapOnLane();


        this.timer = 0;







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
        for (Car c : cars) {c.addToGraphics();}
        for (SpecialCaseSlide s: traps){s.addToGraphics();}
    }

    // TODO : ajout de methodes

    /*
     * Fourni : mayAddCar(), getFirstCase() et getBeforeFirstCase()
     */

    /**
     * Ajoute une voiture au d�but de la voie avec probabilit� �gale � la
     * densit�, si la premi�re case de la voie est vide
     */
    public void mayAddCar() {
        if (isSafe(getFirstCase()) && isSafe(getBeforeFirstCase()) && this.game.randomGen.nextDouble() < this.density) {
            cars.add(new Car(game, getBeforeFirstCase(), leftToRight, false));


        }
    }

    /* Bouge chaque de l'array cars */

    public void moveCarsonLane() {
        for (Car c : cars) {
            c.move();
        }
    }

    /* Vérifie case chaque voiture est vide */
    public boolean isSafe(Case caseFrog) {
        for (Car c : cars) {
            if(c.CarOnFrog(caseFrog)){
                return false;
            }
        }
        for(SpecialCaseSlide s: traps){
            if(!s.getSafety()){
                return false;
            }
        }
        return true;
    }

    /*Vérifie Piege*/
    public boolean frogOnTrap(Case caseFrog){
        for(SpecialCaseSlide s : traps){
            if(s.frogOnTrap(caseFrog)){
                return true;
            }
        }
        return false;
    }

    public void activateEffect(){
        for(SpecialCaseSlide s:traps){s.effect();}
    }





    /* retire voiture si fini trajet*/
    protected void removeCars() {

        while(!cars.isEmpty() && cars.get(0).shoulBeDeleted()) {
            cars.remove(0);
        }
    }


    protected Case getFirstCase() {
        if (leftToRight) {
            return new Case(0, ord);
        } else
            return new Case(game.width - 1, ord);
    }

    protected Case getBeforeFirstCase() {
        if (leftToRight) {
            return new Case(-1, ord);
        } else
            return new Case(game.width, ord);
    }

    /* Bouge la lane vers le bas et mets à jour la position des voitures */
    public void moveLane(){
        this.ord = this.ord - 1;
        for (Car c : cars) {
            c.setLeftPosition(c.getPosition().absc, this.ord);
        }
        for (SpecialCaseSlide s : traps){
            s.setPosition(new Case(s.getPosition().absc, this.ord));

        }
    }


    /* Si la Lane est hors de l'écran on la supprime */
    public boolean shouldBeDeleted(){
        if (this.ord < 0 ) {
            return true;
        }return false;
    }

    public void getTrapToDelete(){
        for(SpecialCaseSlide s:traps){
            if(s.getTrapToDelete()){
                s = null;
            }
        }
    }

    public int getOrd(){return this.ord;}

    public void setTrapOnLane() {
        for (int i = 0; i < game.width; i++) {
            if (game.randomGen.nextDouble() <= 0.01) {
                this.traps.add(new SpecialCaseSlide(game, new Case( i, this.ord), frog));
            }

        }
    }

}

