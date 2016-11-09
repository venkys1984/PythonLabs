package utils.storage.graph;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.Constant;
import com.google.api.server.spi.config.ApiMethod;
import com.google.gson.Gson;
import com.googlecode.objectify.cmd.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
  * Add your first API methods in this class, or you may create another class. In that case, please
  * update your web.xml accordingly.
 **/
@Api(name = "graphStoreAPI", version = "v1", scopes = { Constants.EMAIL_SCOPE }, clientIds = {
        Constants.WEB_CLIENT_ID, Constant.API_EXPLORER_CLIENT_ID }, description = "API for hierarchical storage of iris entities.")

public class GraphStoreAPI {

    @ApiMethod(name="storeNode", path="storeNode", httpMethod = ApiMethod.HttpMethod.POST)
    public Node storeNode(NodeForm nf){
        Node nodeEntity = new Node();
        nodeEntity.createNode(nf);
        OfyService.ofy().save().entity(nodeEntity).now();
        return nodeEntity;
    }

    @ApiMethod(name="getNode", path="getNode", httpMethod = ApiMethod.HttpMethod.POST)
    public NodeForm getNode(Identifier id){
        Query query = OfyService.ofy().load().type(Node.class);
        System.out.println(id.identifier);
        Map<String,Object> original = id.identifier;
        Map<String,Object> flatMap = new HashMap<String, Object>();

        flatten("identifier",original,flatMap);
        System.out.println(flatMap);

        for(String key : flatMap.keySet()){
            query = query.filter(key + " =",flatMap.get(key));
        }

        List<Node> nodes = query.list();
        List<NodeForm> nodeforms = new ArrayList<NodeForm>();
        for(Node item : nodes){
           nodeforms.add(item.fetchNodeForm());
        }

        System.out.println("NodeForm object:");
        System.out.println(nodeforms.get(0).identifier);
        for(String key : nodeforms.get(0).identifier.keySet()){
            System.out.println(key);
            System.out.println(nodeforms.get(0).identifier.get(key).getClass());
        }

        return nodeforms.get(0); //ideally only one node should match the specified filter criteria

    }

    @ApiMethod(name="getNodeAsString", path="getNodeAsString", httpMethod = ApiMethod.HttpMethod.POST)
    public DummyResult getNodeAsString(Identifier id){
        Query query = OfyService.ofy().load().type(Node.class);
        System.out.println(id.identifier);
        Map<String,Object> original = id.identifier;
        Map<String,Object> flatMap = new HashMap<String, Object>();

        flatten("identifier",original,flatMap);
        System.out.println(flatMap);

        for(String key : flatMap.keySet()){
            query = query.filter(key + " =",flatMap.get(key));
        }

        List<Node> nodes = query.list();
        List<NodeForm> nodeforms = new ArrayList<NodeForm>();
        for(Node item : nodes){
            nodeforms.add(item.fetchNodeForm());
        }

        System.out.println("NodeForm object:");
        System.out.println(nodeforms.get(0).identifier);
        for(String key : nodeforms.get(0).identifier.keySet()){
            System.out.println(key);
            System.out.println(nodeforms.get(0).identifier.get(key).getClass());
        }

        DummyResult res = new DummyResult();

        Gson gson = new Gson();
        res.result = gson.toJson(nodeforms.get(0));
        return res;

        //return nodeforms.get(0); //ideally only one node should match the specified filter criteria

    }

    private static void flatten(String prefix, Map<String,Object> ip, Map<String,Object> flatMap){
        for(String key : ip.keySet()){
            Object obj = ip.get(key);
            if(obj instanceof  Map<?,?>){
                if(prefix.equals("")){
                    flatten(key,(Map<String,Object>)obj,flatMap);
                }
                else{
                    flatten(prefix+"."+key , (Map<String,Object>)obj,flatMap);
                }

            }
            else{
                if(prefix.equals("")){
                    flatMap.put(key,ip.get(key));
                }
                else{
                    flatMap.put(prefix+"." + key,ip.get(key));
                }

            }
        }
    }

    private NodeForm queryNode(Map<String,Object> id){
        Query query = OfyService.ofy().load().type(Node.class);
        System.out.println(id);
        Map<String,Object> original = id;
        Map<String,Object> flatMap = new HashMap<String, Object>();

        flatten("identifier",original,flatMap);
        System.out.println(flatMap);

        for(String key : flatMap.keySet()){
            query = query.filter(key + " =",flatMap.get(key));
        }

        List<Node> nodes = query.list();
        List<NodeForm> nodeforms = new ArrayList<NodeForm>();
        for(Node item : nodes){
            nodeforms.add(item.fetchNodeForm());
        }

        if(nodeforms.size() > 0){
            return nodeforms.get(0); //ideally only one node should match the specified filter criteria
        }
        else
            return null;
    }

    @ApiMethod(name="getTopDownTree", path="getTopDownTree", httpMethod = ApiMethod.HttpMethod.POST)
    public TreeNode getTopDownTree(Identifier rootId){
        return getTree(rootId.identifier);
    }

    private TreeNode getTree(Map<String,Object> identifier) //get top down tree, given the root identifier
    {
        NodeForm rootNode = queryNode(identifier);
        if(rootNode == null){
            return null;
        }

        TreeNode tnode = new TreeNode();
        tnode.identifier = rootNode.identifier;
        tnode.properties = rootNode.properties;
        tnode.timeStamp = rootNode.timeStamp;

        if(rootNode.children == null){ //the node has no children
            return tnode;
        }

        for(Map<String,Object> childIdentifier : rootNode.children){
            TreeNode childNode = getTree(childIdentifier);
            if(childNode!= null){
                tnode.children.add(childNode);
            }

        }

        return tnode;
    }




}
