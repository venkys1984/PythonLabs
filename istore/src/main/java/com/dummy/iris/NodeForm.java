package com.dummy.iris;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NodeForm {
	public String source;
	public String identifier;
	public Map<String, String> otherProperties = new HashMap<>();
	public Long timeStamp;
	
	public MetaInfo parent;
	public List<MetaInfo> children = new ArrayList<>();
	
	public NodeForm() {}	
}