package com.alex.materials;

import com.sun.jersey.json.impl.provider.entity.JSONArrayProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class FacingService extends ConnectionDB{

//    public static void main(String[] args) throws SQLException {
//
//        //insertInFacing("testqweqweqwe");
//        //deleteInFacing(1);
//        //Facing test = (Facing) getFacingById(29);
//        //insertInFacingObj(test);
//        //System.out.println(st);
//    }

    public static Object insertInFacingObj(Facing facing) throws SQLException {
        Connection dbConnection = null;
        PreparedStatement statement = null;
        List<MaterialsToFacing> materials;
        materials = facing.getMaterials();
        //Object[] test = materials.toArray();

        System.out.println("materials... " + materials.toString() + "\n size... " + materials.size());

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.prepareStatement("INSERT INTO  facing (id, name) VALUES (DEFAULT, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, facing.getName());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    facing.setId(generatedKeys.getInt(1));
                    System.out.println("Table \"facing\" is updated!" + "\n" + "name = " + facing.getName() + " " + "id = " + facing.getId());
                }
                else {
                    throw new SQLException("Creating facings failed, no ID obtained.");
                }
            }

            for(int i = 0; i < materials.size(); i++) {
                MaterialsToFacing element = materials.get(i);

                statement = dbConnection.prepareStatement("INSERT INTO  materialstofacing (id_materials, square, id_facing, id) VALUES (?, ?, ?, DEFAULT)", Statement.RETURN_GENERATED_KEYS);
                statement.setInt(1, element.getId_materials());
                statement.setDouble(2, element.getSquare());
                statement.setInt(3, facing.getId());

                element.setId_facing(facing.getId());

                statement.executeUpdate();

                try (ResultSet generatedKeys1 = statement.getGeneratedKeys()) {
                    if (generatedKeys1.next()) {
                        element.setId(generatedKeys1.getInt(4));
                        System.out.println( "id (materialsTofFacing) = " + facing.getId());
                    }
                    else {
                        throw new SQLException("Creating facings failed, no ID obtained.");
                    }
                }
            }



            statement.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        System.out.println(facing.toString());

        return facing;
    }

    // one to one
    public static String insertInFacing(String name, int id_materials, double square) throws SQLException {
        Connection dbConnection = null;
        PreparedStatement statement = null;
        int id_facing = 0;
        int id = 0;

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.prepareStatement("INSERT INTO  facing (id, name) VALUES (DEFAULT, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, name);
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id_facing = generatedKeys.getInt(1);
                }
                else {
                    throw new SQLException("Creating facings failed, no ID obtained.");
                }
            }
            statement.close();
            System.out.println("Table \"facing\" is updated!" + "\n" + "name = " + name + "\n" + "id = " + id_facing);

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.prepareStatement("INSERT INTO  materialsToFacing (id, id_facing, id_materials, square) VALUES (DEFAULT, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id_facing);
            statement.setInt(2, id_materials);
            statement.setDouble(3, square);
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(4);
                }
                else {
                    throw new SQLException("Creating facings failed, no ID obtained.");
                }
            }
            statement.close();
            System.out.println("Table \"facing\" is updated!" + "\n" + "name = " + name + "\n" + "id = " + id);

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        String st ="{" +
                        "\"facing\" : {" +
                            "\"id\" : " + id_facing +
                            ", \"name\" : \"" + name +
                            "\", \"materials\" : {" +
                                " \"id\" : " + id +
                                ", \"id_facing\" : " + id_facing +
                                ", \"id_materials\" : " + id_materials +
                                ", \"square\" : " + square +
                            "}" +
                        "}" +
                    "}";
        return st;
    }
// имеет ли смысл делить запросы

    public static void deleteInFacing(int id) throws SQLException {
        Connection dbConnection = null;
        PreparedStatement statement = null;

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.prepareStatement("DELETE from facing where ID = ?");
            statement.setInt(1, id);
            statement.executeUpdate();

            System.out.println("Row " + id + " in \"facing\" is delete!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }

    public static Object getFacingById(int id) throws SQLException {
        Connection dbConnection = null;
        PreparedStatement statement = null;

        String name;
        List<MaterialsToFacing> materialsToFacing = new ArrayList<>();
        int id_materials;
        int id_facing;
        double square;
        long count1 = 0;
        Object facing = new Object();
        int id1;

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.prepareStatement("SELECT id, name FROM facing WHERE id = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                name = rs.getString("name");
                statement = dbConnection.prepareStatement("SELECT id_materials, square, id_facing, id FROM materialstofacing WHERE id_facing = ?");
                statement.setInt(1, id);
                ResultSet rs1 = statement.executeQuery();

                    while (rs1.next()){
                        id1 = rs1.getInt("id");
                        id_materials = rs1.getInt("id_materials");
                        id_facing = rs1.getInt("id_facing");
                        square = rs1.getDouble("square");

                        materialsToFacing.add(new MaterialsToFacing(id1, id_facing, id_materials, square));
                        count1++;
                        System.out.println(materialsToFacing.size());
                    }
                facing = new Facing(id, name, materialsToFacing);
            }
            statement.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        System.out.println(facing.toString());
        System.out.println(" -------- ");
        return facing;
    }

}
