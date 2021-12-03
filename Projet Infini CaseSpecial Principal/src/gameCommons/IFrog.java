package gameCommons;

import util.Case;
import util.Direction;
import givenEnvironment.GivenEnvironment;
public interface IFrog {
	
	/**
	 * Donne la position actuelle de la grenouille
	 * @return
	 */
	public Case getPosition();
	
	/**
	 * Donne la direction de la grenouille, c'est � dire de son dernier mouvement 
	 * @return
	 */
	public Direction getDirection();

	public void setDirection(Direction d);
	public int getScore();
	public void updateScore();
	public void bonusScore(int i);
	public void setDefaultPosition();
	
	/**
	 * D�place la grenouille dans la direction donn�e et teste la fin de partie
	 * @param key
	 */
	public void move(Direction key);



}
