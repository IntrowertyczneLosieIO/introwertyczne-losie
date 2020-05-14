package com.agh.introwertycznelosie.data;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class FacultySerializer extends JsonSerializer<Faculty> {

    public FacultySerializer(){}

    @Override
    public void serialize(
            Faculty value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException {
        jgen.writeString(value.getAcronym());
    }
}