package com.agh.introwertycznelosie.data;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

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