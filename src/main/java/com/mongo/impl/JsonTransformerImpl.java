package com.mongo.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ValueNode;

import java.util.Iterator;
import java.util.Map;

import static com.mongo.impl.JsonConstant.DOT_OPERATOR;
import static com.mongo.impl.JsonConstant.EMPTY_STRING;

public class JsonTransformerImpl implements JsonTransformer {

    @Override
    public ObjectNode transformJson(JsonNode inputJson, ObjectMapper objectMapper) {
        ObjectNode outputJson = objectMapper.createObjectNode();
        transformJson(EMPTY_STRING.getValue(), inputJson, outputJson);
        return outputJson;
    }

    /**
     * Logic that traverses code and transforms json
     *
     * @param currentKey Current key that is being visited
     * @param inputJson Input Json
     * @param outputJson Output Json
     */
    private static void transformJson(String currentKey, JsonNode inputJson, ObjectNode outputJson) {
        if (inputJson.isObject()) {
            ObjectNode inputNode = (ObjectNode) inputJson;
            Iterator<Map.Entry<String, JsonNode>> iterator = inputNode.fields();
            String keyPrefix = currentKey.isEmpty() ? EMPTY_STRING.getValue() : currentKey +
                    DOT_OPERATOR.getValue();
            while (iterator.hasNext()) {
                Map.Entry<String, JsonNode> entry = iterator.next();
                transformJson(keyPrefix + entry.getKey(), entry.getValue(), outputJson);
            }
        } else if (inputJson.isValueNode()) {
            ValueNode valueNode = (ValueNode) inputJson;
            outputJson.set(currentKey, valueNode);
        }
    }
}
