package com.mongo.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(PowerMockRunner.class)
@PrepareForTest(JsonTransformerImpl.class)
public class JsonTransformerImplTest {

    @Mock
    private JsonTransformer jsonTransformer = new JsonTransformerImpl();

    @Spy
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void transformJson() throws JsonProcessingException, JSONException {
        String input = "{\n" +
                "    \"a\": 1,\n" +
                "    \"b\": null,\n" +
                "    \"c\": {\n" +
                "        \"d\": 3,\n" +
                "        \"e\": {\n" +
                "            \"test\" : 123,\n" +
                "            \"val\" : false\n" +
                "        },\n" +
                "        \"f\" : \"some value\"\n" +
                "    }\n" +
                "}";
        String outputExpected = "{\"a\":1,\"b\":null,\"c.d\":3,\"c.e.test\":123,\"c.e.val\":false,\"c.f\":\"some value\"}";
        JsonNode jsonNode = objectMapper.readTree(input);
        ObjectNode outputJson = jsonTransformer.transformJson(jsonNode, objectMapper);
        assertNotNull(outputJson);
        assertEquals(objectMapper.writeValueAsString(outputJson), outputExpected);
        JSONAssert.assertEquals(outputExpected, objectMapper.writeValueAsString(outputJson), JSONCompareMode.LENIENT);
    }

    @Test
    public void transformEmptyJson() throws JsonProcessingException, JSONException {
        String input = "{}";
        String outputExpected = "{}";
        JsonNode jsonNode = objectMapper.readTree(input);
        ObjectNode outputJson = jsonTransformer.transformJson(jsonNode, objectMapper);
        assertNotNull(outputJson);
        assertEquals(objectMapper.writeValueAsString(outputJson), outputExpected);
        JSONAssert.assertEquals(outputExpected, objectMapper.writeValueAsString(outputJson), JSONCompareMode.LENIENT);
    }
}
