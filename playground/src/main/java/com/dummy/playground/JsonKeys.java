package com.dummy.playground;

import com.googlecode.objectify.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Entity
public class JsonKeys {
    @Id
    public Long id;

    @Index
    public String name;

    @Index public Map<String, Object> keys;

    @Index
    public String stringifiedKey;

    public JsonKeys() {}

}