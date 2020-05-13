package com.agh.introwertycznelosie.data;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class FacultyDeserializer extends JsonDeserializer<Faculty> {

    public FacultyDeserializer(){}

    @Override
    public Faculty deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        Faculty faculty = null;
        try {
            faculty = new Faculty(node.asText());
        } catch (Faculty.InvalidFacultyException e) {
            e.printStackTrace();
        }
        return faculty;
    }
}