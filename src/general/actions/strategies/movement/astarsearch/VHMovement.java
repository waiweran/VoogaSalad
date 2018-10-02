package general.actions.strategies.movement.astarsearch;


import general.Vector;

/**
 * Only allows movements in cardinal directions 
 * @author Ashka Stephen
 */
class VHMovement implements AStarHeuristic {

	@Override
	public double getEstDistance(Vector start, Vector goal) {
		return start.distanceTo(goal);
		/*double XDist = start.getX() - goal.getX();
		double YDist = start.getY() - goal.getY();
		return YDist + XDist ;*/
	}
}
