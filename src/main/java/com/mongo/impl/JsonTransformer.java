package com.mongo.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public interface JsonTransformer {
    ObjectNode transformJson(JsonNode inputJson, ObjectMapper mapper);
}
