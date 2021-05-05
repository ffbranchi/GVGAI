package tracks.singlePlayer.florabranchi.agents.meta;

import java.util.HashMap;
import java.util.Map;

public class GameFeaturesHelper {

  static Map<Integer, GameFeatures> featuresByGame = new HashMap<>();

  // g1: aliens, butterflies, painter
  //
  //g2: camelRace, frogs, chase
  //
  //g3: jaws, seaquest, surviving zombies

  static {

    // group 1 - aliens 1, butterfly 13, painter 70
    featuresByGame.put(0, new GameFeatures(true, false, false, false));
    featuresByGame.put(13, new GameFeatures(false, false, false, false));
    featuresByGame.put(70, new GameFeatures(true, false, false, false));

    // group 2 - camelRace 15 frogs 44 chase 18
    featuresByGame.put(15, new GameFeatures(true, false, false, false));
    featuresByGame.put(44, new GameFeatures(false, false, true, false));

    // group 3 - jaws, seaquest 77, surviving zombies 84

    // group 4 - brainmain, plants, eggmania
    // frogs 44

  }

  public static Map<Integer, GameFeatures> getFeaturesByGame() {
    return featuresByGame;
  }

  public static GameFeatures getGameFeatures(final int gameId) {
    return featuresByGame.getOrDefault(gameId, null);
  }

  static class GameFeatures {
    public boolean isDeterministic;
    public boolean canUse;
    public boolean canDie;
    public boolean isSurvival; // there is timeout

    public GameFeatures(final boolean isDeterministic,
                        final boolean canUse,
                        final boolean canDie,
                        final boolean isSurvival) {
      this.isDeterministic = isDeterministic;
      this.canUse = canUse;
      this.canDie = canDie;
      this.isSurvival = isSurvival;
    }
  }


}
