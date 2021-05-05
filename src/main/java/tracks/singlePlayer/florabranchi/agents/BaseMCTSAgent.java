
package tracks.singlePlayer.florabranchi.agents;

import core.game.StateObservation;
import ontology.Types;
import tools.ElapsedCpuTimer;
import tracks.singlePlayer.florabranchi.database.BaseMonteCarloResult;
import tracks.singlePlayer.florabranchi.database.BaseMonteCarloResultDAO;
import tracks.singlePlayer.florabranchi.persistence.PropertyLoader;

public class BaseMCTSAgent extends ParametrizedMonteCarloTreeAgent {

  BaseMonteCarloResultDAO dao = new BaseMonteCarloResultDAO();


  public BaseMCTSAgent(final StateObservation stateObs, final ElapsedCpuTimer elapsedTimer) {
    super(stateObs, elapsedTimer);
  }

  @Override
  public void result(final StateObservation stateObs,
                     final ElapsedCpuTimer elapsedCpuTimer) {

    gameName = PropertyLoader.GAME_NAME;

    showTree = propertyLoader.SHOW_TREE;
    ROLLOUT_DEPTH = PropertyLoader.ROLLOUT_DEPTH;

    final BaseMonteCarloResult baseMonteCarloResult = new BaseMonteCarloResult();
    baseMonteCarloResult.agent = PropertyLoader.AGENT;
    baseMonteCarloResult.gameName = gameName;
    baseMonteCarloResult.gameLevel = PropertyLoader.LEVEL;
    baseMonteCarloResult.finalScore = stateObs.getGameScore();
    baseMonteCarloResult.won = stateObs.getGameWinner().equals(Types.WINNER.PLAYER_WINS);
    baseMonteCarloResult.treeReuse = PropertyLoader.TREE_REUSE;
    baseMonteCarloResult.rawGameScore = PropertyLoader.RAW_GAME_SCORE;
    baseMonteCarloResult.macroActions = PropertyLoader.MACRO_ACTIONS;
    baseMonteCarloResult.lossAvoidance = PropertyLoader.LOSS_AVOIDANCE;
    baseMonteCarloResult.earlyInitialization = PropertyLoader.EARLY_INITIALIZATION;
    baseMonteCarloResult.selectHighestScoreChild = PropertyLoader.SELECT_HIGHEST_SCORE_CHILD;
    dao.save(baseMonteCarloResult);
  }


  @Override
  protected String getPropertyPath() {
    return "base.mcts.properties";
  }
}

