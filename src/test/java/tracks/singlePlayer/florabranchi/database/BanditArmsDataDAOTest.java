package tracks.singlePlayer.florabranchi.database;

import org.junit.Test;

import tracks.singlePlayer.florabranchi.agents.meta.MabParameters;
import tracks.singlePlayer.florabranchi.agents.meta.MultiArmedNaiveSampler;

public class BanditArmsDataDAOTest {
  @Test
  public void createTable() {
    BanditArmsDataDAO dao = new BanditArmsDataDAO();
    dao.createMetaWeightsTable();
  }

  @Test
  public void testCreate() {
    BanditArmsDataDAO dao = new BanditArmsDataDAO();
    MultiArmedNaiveSampler sampler = new MultiArmedNaiveSampler();
    final MabParameters randomGlobalMab = sampler.getRandomGlobalMab();
    sampler.updateMabData(randomGlobalMab, 22.3);
    final MabParameters mabParameters = sampler.addRandomSample();
    sampler.updateMabData(mabParameters, 50);
    //dao.saveBandit(new BanditsArmDTO(new BanditArmsData(sampler.localMabs, sampler.globalMab)));
  }

  @Test
  public void testSave() {
    BanditArmsDataDAO dao = new BanditArmsDataDAO();
    final BanditArmsData banditArmsData = dao.getBanditArmsData(1);
    System.out.println(banditArmsData.toString());
  }

}