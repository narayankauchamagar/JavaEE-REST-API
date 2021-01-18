package com.f1soft.college.exception;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
    private static final Logger logger = LoggerFactory.getLogger(ValidationExceptionMapper.class);

    @Override
    public Response toResponse(ConstraintViolationException e) {

        Map<String, String> error = e.getConstraintViolations()
                .stream()
                .collect(Collectors.toMap((t) -> getKey(t.getPropertyPath().toString()), ConstraintViolation::getMessage));
        JsonArrayBuilder jarr = Json.createArrayBuilder();
        error.entrySet().stream().forEach((entry) -> {
            jarr.add(Json.createObjectBuilder().add(entry.getKey(), entry.getValue()));
        });

        JsonObject jsonObject = Json.createObjectBuilder()
                .add("success", false)
                .add("status", 400)
                .add("message", "Validation Failure")
                .add("errors", Json.createArrayBuilder().add(jarr))
                .build();

        return Response.status(400).
                entity(jsonObject)
                .build();
    }

    public String getKey(String string) {
        List<String> keys = Arrays.asList(string.split("\\."));
        String key = keys.subList(2, keys.size()).stream().collect(Collectors.joining("."));
        return !key.isEmpty() ? key : "entity";
    }
}
