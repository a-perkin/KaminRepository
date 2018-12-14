package com.alex.rest;

import com.alex.materials.Facing;
//import com.owlike.genson.Genson;

import javax.swing.text.html.parser.Entity;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.alex.materials.FacingService.getFacingById;
import static com.alex.materials.FacingService.insertInFacing;
import static com.alex.materials.FacingService.insertInFacingObj;

@Path("/facing")
public class FacingController {

        //Genson genson = new Genson();
//    @POST
//    @Path("/update")
//    public Entity getViaQueryParam(
//            @QueryParam("facing")
//            @DefaultValue("{\"Entity\":{\"foo\":\"bar\",\"bar\":\"foo\"}}")
//            final Entity facing) {
//        return entity;
//    }
//
//    @GET
//    @Path("header")
//    public Entity getViaHeaderParam(@HeaderParam("Entity") final Entity entity) {
//        return entity;
//    }

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
    @Path("/getFacing")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response facingByID (
            @QueryParam("id") int id) throws SQLException {
        return Response.status(200).entity(getFacingById(id)).build();
    }

    @POST
    @Path("/updateObj")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertInFacingJSON(Facing facing) throws SQLException {
        Object facing1 = insertInFacingObj(facing);

        return Response.status(200).entity(facing1).build();
    }

}
