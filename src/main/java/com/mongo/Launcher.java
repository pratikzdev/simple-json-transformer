package com.mongo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mongo.impl.JsonTransformer;
import com.mongo.impl.JsonTransformerImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Launcher {
    private String[] args;

    public void run(String[] args) throws JsonProcessingException {
        this.args = args;

        ObjectMapper mapper = getObjectMapper();
        StringBuilder builder = new StringBuilder();

        var lines = readInput();
        lines.forEach(builder::append);
        JsonNode inputJson = mapper.readTree(builder.toString());

        System.out.println("Input is : ");
        System.out.println(mapper.writeValueAsString(inputJson));

        JsonTransformer parser = new JsonTransformerImpl();
        ObjectNode outputJson = parser.transformJson(inputJson, mapper);

        System.out.println("Output is : ");
        System.out.println(mapper.writeValueAsString(outputJson));
    }

    /**
     * Method that read the input as file
     * @return Stream<String> that represents the input file
     */
    protected Stream<String> readInput(){
        var reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            if (!reader.ready()){
                return Stream.empty();
            }else {
                return reader.lines();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Invalid argument supplied");
    }

    /**
     * Returns the configured object mapper
     * @return ObjectMapper
     */
    protected ObjectMapper getObjectMapper(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper;
    }
}
