package com.vaitheeswaran.rest.restapp;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("students")
public class StudentResource {

	@GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Studnets";
    }
}
