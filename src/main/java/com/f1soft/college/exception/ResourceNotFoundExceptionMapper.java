package com.f1soft.college.exception;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ResourceNotFoundExceptionMapper implements ExceptionMapper<ResourceNotFoundException> {

    @Override
    public Response toResponse(ResourceNotFoundException e) {

        JsonObject jsonObject = Json.createObjectBuilder()
                .add("success", false)
                .add("status", 400)
                .add("message", e.getMessage())
                .build();

        return Response.status(400).
                entity(jsonObject)
                .build();
    }
}
