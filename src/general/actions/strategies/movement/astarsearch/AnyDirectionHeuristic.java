package general.actions.strategies.movement.astarsearch;

import general.Vector;

/**
 * Calculates distance from start to the goal based on distance.
 * Each step is weighted (rather than the actual distance traveled itself).
 * A step in a cardinal direction is weighted 1 and a step diagonally is weighted sqrt(2). (Octile Distance)
 * This heuristic assumes simplified structure of only moving in 8 directions (4 cardinal and 4 diagonal)
 * For more information: http://theory.stanford.edu/~amitp/GameProgramming/Heuristics.html
 * 
 * @author Ashka Stephen
 */
public class AnyDirectionHeuristic implements AStarHeuristic{

	@Override
	public double getEstDistance(Vector start, Vector goal) {
		return start.distanceTo(goal);
	}
}