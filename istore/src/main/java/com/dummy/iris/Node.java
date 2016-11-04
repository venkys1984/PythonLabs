package com.dummy.iris;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Node{
	
	@Id public Long nodeId;
	
	@Index public String source;
	public String identifier;
	
	@Index public Map<String, String> otherProperties = new HashMap<>();
	
	@Index public Date timeStamp;
	
	public MetaInfo parent;
	public List<MetaInfo> children = new ArrayList<>();
	
	public Node() {}	
	
	public void createNode(NodeForm nf){
		this.source = nf.source;
		this.identifier = nf.identifier;
		this.otherProperties = nf.otherProperties;
		
		if(nf.timeStamp == null){
			this.timeStamp = new Date(System.currentTimeMillis());
		}
		else {
			this.timeStamp = new Date(nf.timeStamp);
		}
		this.parent = nf.parent;
		this.children = nf.children;
	}
	
}