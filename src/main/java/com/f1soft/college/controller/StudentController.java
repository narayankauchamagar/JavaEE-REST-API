package com.f1soft.college.controller;

import com.f1soft.college.entity.Student;
import com.f1soft.college.exception.ResourceNotFoundException;
import com.f1soft.college.service.StudentService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/student")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StudentController {

    @Inject
    private StudentService studentService;

    @GET
    public Response allStudents() {
        return Response.ok(studentService.getAll()).build();
    }

    @POST
    public Response saveStudent(@Valid Student student){
        System.out.println(student);
        return Response.ok(studentService.saveOrUpdate(student)).build();
    }

    @GET
    @Path("{id: [0-9]* }")
    public Response getStudentById(@PathParam("id") Long id){
        Optional<Student> student  = studentService.findById(id) ;
        if(student.isPresent()){
            return Response.ok(studentService.findById(id)).build();
        }else {
            throw new ResourceNotFoundException("No such Student found");
        }
    }

    @PUT
    @Path("{id: [0-9]* }")
    public Response updateStudentById(@PathParam("id") Long id, @Valid Student student){
        if(studentService.findById(id).isPresent()){
            return Response.ok(studentService.saveOrUpdate(student)).build();
        }
        throw new ResourceNotFoundException("No such Student found");
    }

    @DELETE
    @Path("{id: [0-9]*}")
    public Response deleteStudentById(@PathParam("id") Long id) {
        Optional<Student> studentOptional = studentService.findById(id);
        if(studentOptional.isPresent()){
            studentService.delete(studentOptional.get());
            return Response.ok().build();
        }
        throw new ResourceNotFoundException("No such Student found");
    }
}
