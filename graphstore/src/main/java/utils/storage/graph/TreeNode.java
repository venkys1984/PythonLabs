package utils.storage.graph;

import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TreeNode {
    public Map<String,Object> identifier;
    public Map<String, Object> properties ;
    public Long timeStamp;
    public List<TreeNode> children = new ArrayList<TreeNode>();
}