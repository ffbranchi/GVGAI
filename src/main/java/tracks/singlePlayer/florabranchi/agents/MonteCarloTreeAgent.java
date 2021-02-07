package tracks.singlePlayer.florabranchi.agents;

import java.util.ArrayList;
import java.util.Random;

import core.game.StateObservation;
import ontology.Types.ACTIONS;
import tools.ElapsedCpuTimer;
import tracks.singlePlayer.florabranchi.tree.TreeController;
import tracks.singlePlayer.florabranchi.tree.TreeNode;

/**
 * Flora Branchi (florabranchi@gmail.com) September 2019
 */
public class MonteCarloTreeAgent extends AbstractAgent {

  private TreeController treeController;
  /**
   * Random generator for the agent.
   */
  protected Random randomGenerator;

  /**
   * List of available actions for the agent
   */
  protected ArrayList<ACTIONS> actions;

  protected boolean showTree;

  /**
   * ' initialize all variables for the agent
   *
   * @param stateObs     Observation of the current state.
   * @param elapsedTimer Timer when the action returned is due.
   */
  public MonteCarloTreeAgent(final StateObservation stateObs,
                             final ElapsedCpuTimer elapsedTimer) {
    super(stateObs, elapsedTimer);
    showTree = propertyLoader.SHOW_TREE;

    randomGenerator = new Random();
    actions = stateObs.getAvailableActions();
    treeController = new TreeController(stateObs, displayDebug());
  }

  protected boolean displayDebug() {
    return false;
  }

  @Override
  protected String getPropertyPath() {
    return "mcts.properties";
  }


  /**
   * return Action given selected policy.
   */
  @Override
  public ACTIONS act(final StateObservation stateObs,
                     final ElapsedCpuTimer elapsedTimer) {

    System.out.println("SCORE: " + stateObs.getGameScore());
    return monteCarloSearch(stateObs, elapsedTimer);

  }


  public ACTIONS monteCarloSearch(final StateObservation stateObs,
                                  final ElapsedCpuTimer elapsedTimer) {

    // Perform tree Search
    // todo add time limit here
    treeController.treeSearch(30, stateObs);

    final TreeNode bestChild = treeController.getMostVisitedChild(treeController.rootNode);
    final ACTIONS bestFoundAction = bestChild.previousAction;

    // Update Visualization
    if (displayDebug()) {
      treeController.updateTreeVisualization(stateObs, 0, bestFoundAction);
    }

    System.out.println(elapsedTimer.elapsedMillis());

    // todo add prune support
    //treeController.pruneTree(bestFoundAction);

    return bestFoundAction;
  }
}
