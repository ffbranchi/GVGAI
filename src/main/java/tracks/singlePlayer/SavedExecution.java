package tracks.singlePlayer;

import static java.util.logging.Level.INFO;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Logger;

import tools.Utils;
import tracks.ArcadeMachine;
import tracks.singlePlayer.florabranchi.agents.meta.GameOptions;
import tracks.singlePlayer.florabranchi.agents.meta.RunOptions;
import tracks.singlePlayer.florabranchi.persistence.PropertyLoader;

/**
 * author: Flora Branchi (florabranchi@gmail.com)
 */
public class SavedExecution {


  private final static Logger LOGGER = Logger.getLogger("GVGAI_BOT");


  public static void main(String[] args) throws IOException {

    PropertyLoader propertyLoader = new PropertyLoader(args[0]);


    //Game settings
    LOGGER.setLevel(propertyLoader.LOGGER_LEVEL);
    LOGGER.log(INFO, "Starting game....");
    final boolean saveActions = false;

    //boolean visuals = false;
    int seed = new Random().nextInt();


    // Load available games
    String spGamesCollection = "examples/all_games_sp.csv";
    String[][] games = Utils.readGames(spGamesCollection);

    int gameIdx = propertyLoader.GAME;
    int levelIdx = propertyLoader.LEVEL;
    int episodes = propertyLoader.EPISODES;

    String selectedAgent = propertyLoader.AGENT;
    boolean visuals = propertyLoader.VISUALS;

    String gameName = games[gameIdx][1];
    String game = games[gameIdx][0];
    String level1 = game.replace(gameName, gameName + "_lvl" + levelIdx);


    GameOptions gameOptions = new GameOptions();
    gameOptions.reuseTree = propertyLoader.TREE_REUSE;
    gameOptions.lossAvoidance = propertyLoader.LOSS_AVOIDANCE;
    gameOptions.expandAllNodes = propertyLoader.EXPAND_ALL_CHILD_NODES;
    gameOptions.safetyPreprunning = propertyLoader.SAFETY_PREPRUNNING;
    gameOptions.shallowRollout = propertyLoader.SIMULATION_DEPTH <= 50;
    gameOptions.rawGameScore = propertyLoader.TREE_REUSE;

    RunOptions runOptions = new RunOptions();
    runOptions.game = "aliens";
    runOptions.level = 1;
    runOptions.totalGames = 1;
    runOptions.scores = new int[5];
    runOptions.scores[1] = 50;
    runOptions.scores[2] = 40;
    runOptions.scores[3] = 30;
    runOptions.wr = 30.5;

    // where to record the actions
    // executed. null if not to save.
    String recordActionsFile = null;
    if (saveActions) {
      recordActionsFile = "botLogs/actions_" + gameName + "_lvl" + levelIdx + "_" + seed + ".txt";
    }

    if (visuals) {
      final double[] doubles = ArcadeMachine.runOneGame(game, level1, visuals, selectedAgent, recordActionsFile, seed, 0);

      System.out.println(Arrays.toString(doubles));
    } else {
      String[] levelFiles;
      levelFiles = new String[1];
      levelFiles[0] = level1;

      for (int i = 0; i < episodes; i++) {
        final double[] doubles = ArcadeMachine.runOneGame(game, level1, visuals, selectedAgent, recordActionsFile, seed, 0);
        System.out.println(Arrays.toString(doubles));
      }
      
      //ArcadeMachine.runGames(game, levelFiles, episodes, selectedAgent, null);
    }

  }
}
