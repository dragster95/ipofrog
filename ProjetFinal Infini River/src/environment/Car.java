package environment;

import java.awt.Color;

import util.Case;
import gameCommons.Game;
import graphicalElements.Element;

public class Car {
	private Game game;
	private Case leftPosition;
	private boolean leftToRight;
	private boolean isRaft;
	private int length;
	private Color color;

	//TODO Constructeur(s)
	public Car(Game game,Case leftPosition, boolean leftToRight, boolean isRaft){
		this.game= game;
		this.leftPosition= leftPosition;
		this.isRaft = isRaft;
		this.leftToRight=leftToRight;
		this.length = game.randomGen.nextInt(3) + 1;

		if(this.isRaft) this.color = new Color(113, 54, 0);
		else if(this.leftToRight) this.color = Color.BLUE;
		else this.color = Color.BLACK;
	}
	
	//TODO : ajout de methodes

	/* Bouge la voiure */
	public void move(){
		if(leftToRight == true ){
			leftPosition = new Case(leftPosition.absc + 1, leftPosition.ord) ;
		}else{
			leftPosition = new Case(leftPosition.absc - 1 , leftPosition.ord) ;
		}
		this.addToGraphics();
	}

	public boolean shoulBeDeleted(){
		if(this.leftToRight == true && leftPosition.absc - length > game.width ){
			return true;
		}else if(this.leftToRight == false && leftPosition.absc + length < 0){
			return true;
		}
		return false;
	}
	
	
	/* Fourni : addToGraphics() permettant d'ajouter un element graphique correspondant a la voiture*/
	public void addToGraphics() {
		for (int i = 0; i < length; i++) {
			Color c = this.color;
			if (this.leftToRight){
				c = this.color;
			}
			game.getGraphic().add(new Element(leftPosition.absc + i, leftPosition.ord, c));
		}
	}

	/* Nous dis si Car partage la case d'une case donnée */

	public boolean CarOnFrog(Case caseFrog) {
		return caseFrog.absc >= this.leftPosition.absc && caseFrog.absc < this.leftPosition.absc + this.length && caseFrog.ord == this.leftPosition.ord;

	}

	public boolean getDirection(){
		return leftToRight;
	}


	public Case getPosition() {
		return leftPosition;
	}

	public void setLeftPosition(int a, int o){
		this.leftPosition = new Case(a,o);
	}


	public int getLength(){ return length;}

	public void setColor(Color c){ this.color = c;}
}

