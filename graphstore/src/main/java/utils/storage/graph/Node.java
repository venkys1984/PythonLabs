package utils.storage.graph;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Entity
public class Node{

    @Id public Long nodeId;

    //The identifier is a single level json. No lists allowed.
    //No nested jsons allowed
    @Index public Map<String,Object> identifier;

    public String properties;

    @Index public Date timeStamp;

    //identifier of parent
    public Map<String,Object> parent;   //a json -- single level, no list , no nested json

    //identifier of child . Since there could be more than one, its a list
    public List<Map<String,Object>> children;  //a list of json viz single level, no list , no nested json

    public Node() {}

    public void createNode(NodeForm nf){
        this.identifier = nf.identifier;

        Gson gson = new Gson();
        this.properties = gson.toJson(nf.properties);

        if(nf.timeStamp == null){
            this.timeStamp = new Date(System.currentTimeMillis());
        }
        else {
            this.timeStamp = new Date(nf.timeStamp);
        }
        this.parent = nf.parent;
        this.children = nf.children;
    }

    public NodeForm fetchNodeForm(){
        Gson gson = new Gson();

        NodeForm nf = new NodeForm();
        nf.identifier = this.identifier;
        nf.timeStamp = this.timeStamp.getTime();
        nf.parent = this.parent;
        nf.children = this.children;

        Type type = new TypeToken<Map<String, Object>>(){}.getType();
        nf.properties = gson.fromJson(this.properties, type);

        return nf;
    }

}