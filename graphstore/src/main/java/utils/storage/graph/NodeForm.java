package utils.storage.graph;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NodeForm {

    public Map<String,Object> identifier;
    public Map<String, Object> properties ;
    public Long timeStamp;

    public Map<String,Object> parent;   //a json
    public List<Map<String,Object>> children;  //a list of json

    public NodeForm() {}
}