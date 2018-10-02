package general.actions.strategies.movement.astarsearch;

import general.Vector;

/**
 * Evaluation Heuristic which AStar search algorithm uses to compute the shortest distance
 * @author Ashka Stephen
 */

public interface AStarHeuristic {
	public double getEstDistance(Vector start, Vector goal);
}
