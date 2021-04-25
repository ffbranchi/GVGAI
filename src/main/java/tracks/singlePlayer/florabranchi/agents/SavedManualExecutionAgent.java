
package tracks.singlePlayer.florabranchi.agents;

import core.game.StateObservation;
import tools.ElapsedCpuTimer;

public class SavedManualExecutionAgent extends ParametrizedMonteCarloTreeAgent {

  public SavedManualExecutionAgent(final StateObservation stateObs, final ElapsedCpuTimer elapsedTimer) {

    super(stateObs, elapsedTimer);
    showTree = propertyLoader.SHOW_TREE;
  }

  @Override
  protected String getPropertyPath() {
    return "sarsa.properties";
  }
}

