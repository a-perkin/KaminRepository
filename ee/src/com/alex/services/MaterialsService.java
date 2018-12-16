package com.alex.services;

import com.alex.entity.Materials;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaterialsService extends ConnectionDB {

    public static void main(String[] args) throws SQLException {
            //getMaterialById(4);
    }

    public static void updateTable(String name, double price, int thickness) throws SQLException {
        Connection dbConnection = null;
        PreparedStatement statement = null;

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.prepareStatement("INSERT INTO materials (name, price, thickness) VALUES (?, ?, ?)");
            statement.setString(1, name);
            statement.setDouble(2, price);
            statement.setInt(3, thickness);
            statement.executeUpdate();
            statement.close();

            System.out.println("Table \"services\" is updated!" + "\n" + "name = " + name + "\n" + "price = " + price + "\n" + "thickness = " + thickness);

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

    public static void updatePrice(double price, int id) throws SQLException {
        Connection dbConnection = null;
        PreparedStatement statement = null;

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.prepareStatement("UPDATE materials SET price = ? WHERE id = ?");
            statement.setDouble(1, price);
            statement.setInt(2, id);
            statement.executeUpdate();

            System.out.println("Table \"services\" is updated! Change price");

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

    public static void updateName(String name, int id) throws SQLException {
        Connection dbConnection = null;
        PreparedStatement statement = null;

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.prepareStatement("UPDATE materials SET name = ? WHERE id = ?");
            statement.setString(1, name);
            statement.setInt(2, id);
            statement.executeUpdate();

            System.out.println("Table \"services\" is updated! Change name");

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

    public static void updateThickness(int thickness, int id) throws SQLException {
        Connection dbConnection = null;
        PreparedStatement statement = null;

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.prepareStatement("UPDATE materials SET thickness = ? WHERE id = ?");
            statement.setInt(1, thickness);
            statement.setInt(2, id);
            statement.executeUpdate();

            System.out.println("Table \"services\" is updated! Change thickness");

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

    public static void deleteInMaterials(int id) throws SQLException {
        Connection dbConnection = null;
        PreparedStatement statement = null;

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.prepareStatement("DELETE from materials where ID = ?");
            statement.setInt(1, id);
            statement.executeUpdate();

            System.out.println("Row " + id + " in \"services\" is delete!");

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

    public static List<Materials> getListAsObject() throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        int id;
        String name;
        double price;
        int thickness;
        long count = 0;
        List<Materials> materials = new ArrayList<>();

        try {
            dbConnection = getDBConnection();
            System.out.println("State db connection ... ");
            statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT id, name, price, thickness FROM materials");

            while (rs.next()) {
                id = rs.getInt("id");
                name = rs.getString("name");
                price = rs.getDouble("price");
                thickness = rs.getInt("thickness");

                materials.add(new Materials(id, name, price, thickness));
                count++;
            }
            System.out.println(materials);
            System.out.println("query done...");

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
        return materials;
    }

    public static Object getMaterialById(int id) throws SQLException {
        Connection dbConnection = null;
        PreparedStatement statement = null;

        String name;
        double price;
        int thickness;
        Object material = new Object();

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.prepareStatement("SELECT id, name, price, thickness FROM materials WHERE id = ?");
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                name = rs.getString("name");
                price = rs.getDouble("price");
                thickness = rs.getInt("thickness");

                material = new Materials(id, name, price, thickness);

                System.out.println("id=" + id + ", name=" + name + ", price=" + price + ", thickness=" + thickness);
                System.out.println(material);
            }

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
        return material;
    }

    public static Object insertInMaterialsObj(Materials materials) throws SQLException {
        Connection dbConnection = null;
        PreparedStatement statement = null;
        Object returnMaterials = new Object();

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.prepareStatement("INSERT INTO materials (id, name, price, thickness) VALUES (DEFAULT, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, materials.getName());
            statement.setDouble(2, materials.getPrice());
            statement.setInt(3, materials.getThickness());
            statement.executeUpdate();
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        materials.setId(generatedKeys.getInt(1));
                        }
                    else {
                        throw new SQLException("Creating facings failed, no ID obtained.");
                    }
                }
            statement.close();
            System.out.println("Table \"materials\" is updated!" + "\n" +" id = " + materials.getId() + " name = " + materials.getName() + " price= " + materials.getPrice() + " thickness= " + materials.getThickness());

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
        return materials;
    }
}

