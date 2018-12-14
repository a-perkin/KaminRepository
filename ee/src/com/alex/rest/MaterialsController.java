package com.alex.rest;
import com.alex.materials.Materials;
import com.alex.materials.MaterialsService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import java.sql.SQLException;

import static com.alex.materials.MaterialsService.*;

@Path("/materials")
public class MaterialsController {

    @GET
    @Produces("application/json")
    public Response getAll() throws SQLException {
        String st = getListAsObject().toString();
        String soMaterials = "{\"materials\": "+ st +"}";
        return Response.status(200).entity(soMaterials).build();
    }

    @GET
    @Path("/query")
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

        insertInMaterialsObj(materials);
        String st = "{" +
                "\"name\" : \"" + name +
                "\", \"price\" : " + price +
                ", \"thickness\" : " +thickness +
                '}';
        System.out.println(st);
        return Response.status(200).entity(st).build();
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
