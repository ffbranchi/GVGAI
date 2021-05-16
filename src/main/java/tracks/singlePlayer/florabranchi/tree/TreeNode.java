package tracks.singlePlayer.florabranchi.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import ontology.Types;

public class TreeNode {

  public int id;

  public TreeNode parent;

  public List<TreeNode> children = new ArrayList<>();

  public double value;

  public int visits;

  public Types.ACTIONS previousAction;

  public TreeNode(final int nodeId,
                  final TreeNode parent,
                  final Types.ACTIONS previousAction) {
    this.id = nodeId;
    this.parent = parent;
    this.previousAction = previousAction;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", TreeNode.class.getSimpleName() + "[", "]")
        .add("id=" + id)
        .add("value=" + value)
        .add("visits=" + visits)
        .add("previousAction=" + previousAction)
        .toString();
  }
}
