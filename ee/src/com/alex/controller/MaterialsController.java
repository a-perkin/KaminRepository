package com.alex.controller;
import com.alex.entity.Materials;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

import static com.alex.services.MaterialsService.*;

@Path("/materials")
public class MaterialsController {

    @GET
    @Produces("application/json")
    public Response getAll() throws SQLException {
        List<Materials> st = getListAsObject();
        return Response.status(200).entity(st).build();
    }

    @GET
    @Path("/one")
    @Produces("application/json")
    public Response getOneMaterial(
            @QueryParam("id") int id) throws SQLException {
        return Response.status(200).entity(getMaterialById(id)).build();
    }

    @POST
    @Path("/update")
    @Produces("application/json")
    public Response insertInMaterials(
            @FormParam("name") String name,
            @FormParam("price") double price,
            @FormParam("thickness") int thickness) throws SQLException {

        Materials materials = new Materials(name, price, thickness);

        return Response.status(200).entity(insertInMaterialsObj(materials)).build();
    }

    @POST
    @Path("/delete")
    public Response deleteFromMaterials(
            @FormParam("id") int id) throws SQLException {
        deleteInMaterials(id);
        String resp = "Material with ID="+id+" deleted.";
        return Response.status(200).entity(resp).build();
    }

    @POST
    @Path("/updName")
    public Response updateNameMaterial(
            @FormParam("name") String name,
            @FormParam("id") int id) throws SQLException {

        updateName(name, id);

        String respUpdName = "New name is \""+ name +"\"";
        return Response.status(200).entity(respUpdName).build();
    }

    @POST
    @Path("/updPrice")
    public Response updatePriceMaterial(
            @FormParam("price") double price,
            @FormParam("id") int id) throws SQLException {

        updatePrice(price, id);

        String respUpdName = "New price is \""+ price +"\"";
        return Response.status(200).entity(respUpdName).build();
    }

    @POST
    @Path("/updThickness")
    public Response updatePriceMaterial(
            @FormParam("thickness") int thickness,
            @FormParam("id") int id) throws SQLException {

        updateThickness(thickness, id);

        String respUpdName = "New thickness is \""+ thickness +"\"";
        return Response.status(200).entity(respUpdName).build();
    }
}
