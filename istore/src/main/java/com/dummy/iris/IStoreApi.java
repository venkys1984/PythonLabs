package com.dummy.iris;

import java.util.*;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;
import com.google.api.server.spi.response.UnauthorizedException;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

/**
  * Add your first API methods in this class, or you may create another class. In that case, please
  * update your web.xml accordingly.
 **/
@Api(name = "istore", version = "v1", scopes = { Constants.EMAIL_SCOPE }, clientIds = {
        Constants.WEB_CLIENT_ID, Constants.API_EXPLORER_CLIENT_ID }, description = "API for basic storage of iris entities.")
public class IStoreApi {
	
	/*@ApiMethod(name = "dummyGet", path = "dummyGet", httpMethod = HttpMethod.GET)
	public DummyClass dummyGet(){
		return new DummyClass(5,"my name");
	}
	
	@ApiMethod(name = "getNodes", path = "getNodes", httpMethod = HttpMethod.GET)
	public List<Node> getNodes(){
		List<Node> nodes = OfyService.ofy().load().type(Node.class).list();
		return nodes;
	}*/
	
	@ApiMethod(name="storeNode", path="storeNode", httpMethod = HttpMethod.POST)
	public Node storeNode(NodeForm nf){
		Node nodeEntity = new Node();
		nodeEntity.createNode(nf);
		Objectify ofy = OfyService.ofy();
		ofy.save().entity(nodeEntity).now();
		return nodeEntity;
	}
	
	/*@ApiMethod(name="queryNode", path="queryNode", httpMethod = HttpMethod.GET)
	public List<Node> queryNode(@Named("property") String property, @Named("value") String value){
		List<Node> nodes = OfyService.ofy().load().type(Node.class).filter(property + " " + "=", value).list();
		System.out.println("Reached here. Size = " + nodes.size());
		return nodes;
	}*/
	
	private Node getEntity(String property, String value){
		List<Node> nodes = OfyService.ofy().load().type(Node.class).filter(property + " " + "=", value).list();		
		return nodes.get(0);
	}
	

	
	/*@ApiMethod(name="getChildren", path="getChildren", httpMethod = HttpMethod.GET)
	public List<Node> getChildren(@Named("property") String property, @Named("value") String value){		
	    
		List<Node> childEntities = new ArrayList<Node>();
		
		Node parentEntity = getEntity(property, value);
		List<MetaInfo> childMetaInfo = parentEntity.children;
		
		for(int index =0; index < childMetaInfo.size(); index++){
			MetaInfo m = childMetaInfo.get(index);
			Node entityInfo = getEntity(m.type, m.ref);
			childEntities.add(entityInfo);
		}
		
		return childEntities;
	}*/
	
	/*@ApiMethod(name="getTree", path="getTree", httpMethod = HttpMethod.GET)
	public TreeNode getTree(@Named("property") String property, @Named("value") String value){
		Node root = getEntity(property, value);
		TreeNode treeRoot = makeTreeNode(root);
		return treeRoot;
	}
	
	
	
	private TreeNode makeTreeNode(Node root){
		TreeNode t = new TreeNode();
		t.properties = root.otherProperties;
		List<MetaInfo> childMetaInfo = root.children;
		for(int index = 0; index < childMetaInfo.size(); index++){
			MetaInfo m = childMetaInfo.get(index);
			Node entityInfo = getEntity(m.type, m.ref);
			t.children.add(makeTreeNode(entityInfo));
		}
		return t;
	}*/
	
	@ApiMethod(name="getTreeOnTimestamp", path="getTreeOnTimestamp", httpMethod = HttpMethod.GET)
	public TreeNode getTreeOnTimestamp(@Named("property") String property, @Named("value") String value, @Nullable @Named("timestamp") Long timestamp){
		if(timestamp == null){
			Node root = getLatestEntity(property, value);
			TreeNode treeRoot = makeLatestTreeNode(root);
			return treeRoot;
		}
		else{
			Node root = getEntityOnTimestamp(property, value,timestamp);
			TreeNode treeRoot = makeTreeNodeOnTimestamp(root,timestamp);
			return treeRoot;
		}
		
	}
	
	private TreeNode makeTreeNodeOnTimestamp(Node root,Long timestamp){
		TreeNode t = new TreeNode();
		t.properties = root.otherProperties;
		List<MetaInfo> childMetaInfo = root.children;
		for(int index = 0; index < childMetaInfo.size(); index++){
			MetaInfo m = childMetaInfo.get(index);
			Node entityInfo = getEntityOnTimestamp(m.type, m.ref,timestamp);
			t.children.add(makeTreeNodeOnTimestamp(entityInfo,timestamp));
		}
		return t;
	}
	
	private TreeNode makeLatestTreeNode(Node root){
		TreeNode t = new TreeNode();
		t.properties = root.otherProperties;
		List<MetaInfo> childMetaInfo = root.children;
		for(int index = 0; index < childMetaInfo.size(); index++){
			MetaInfo m = childMetaInfo.get(index);
			Node entityInfo = getLatestEntity(m.type, m.ref);
			t.children.add(makeLatestTreeNode(entityInfo));
		}
		return t;
	}
	
	private Node getEntityOnTimestamp(String property, String value, Long timestamp){
		Date date = new Date(timestamp);
		List<Node> nodes = OfyService.ofy().load().type(Node.class).filter(property + " " + "=", value)
				.filter("timeStamp <",date).order("-timeStamp").list();		
		return nodes.get(0);
	}

	private Node getLatestEntity(String property, String value){
		List<Node> nodes = OfyService.ofy().load().type(Node.class).filter(property + " " + "=", value)
				.order("-timeStamp").list();		
		return nodes.get(0);
	}

	
	
	
	
}
