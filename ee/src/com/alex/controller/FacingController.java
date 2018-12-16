package com.alex.controller;

import com.alex.entity.Facing;
//import com.owlike.genson.Genson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

import static com.alex.services.FacingService.*;

@Path("/facing")
public class FacingController {

    @POST
    @Path("/update")
    public Response testInsert(
            @QueryParam("name") String name,
            @QueryParam("id_materials") int id_materials,
            @QueryParam("square") double square) throws SQLException {

        String st1 = insertInFacing(name, id_materials, square);

        return Response.status(200).entity(st1).build();
    }

    @GET
    @Path("/getFacingById")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response facingByID (
            @QueryParam("id") int id) throws SQLException {
        return Response.status(200).entity(getFacingById(id)).build();
    }

    @POST
    @Path("/updateFacing")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertInFacingJSON(Facing facing) throws SQLException {
        Object facing1 = insertInFacingObj(facing);

        return Response.status(200).entity(facing1).build();
    }

    @POST
    @Path("/deleteFacing")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteFacing(@QueryParam("id") int id) throws SQLException {
        deleteInFacing(id);

        return Response.status(200).entity("Facing with id = " + id + " deleted").build();
    }

    @GET
    @Path("/getFacing")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllFacing () throws SQLException {

        return Response.status(200).entity(getFacing()).build();
    }

    @GET
    @Path("/deleteMaterial")
    public Response deleteMaterial (
            @QueryParam("id") int id
    ) throws SQLException {
        deleteMaterialInFacing(id);
        return Response.status(200).entity("Row " + id + " in \"materialsToFacing\" is delete!").build();
    }

    @POST
    @Path("/addMaterial")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteFacing(
            @QueryParam("id_facing") int id_facing,
            @QueryParam("id_materials") int id_materials,
            @QueryParam("square") double square) throws SQLException {

        return Response.status(200).entity(addMaterialToFacing(id_facing, id_materials, square)).build();
    }
}
