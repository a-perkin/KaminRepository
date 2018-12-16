package com.alex.entity;

import javax.ws.rs.Produces;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Produces("application/json")
@XmlRootElement(name = "Facing")
public class Facing {
    private int id;
    private String name;
    private List<MaterialsToFacing> materials;

    public Facing(int id, String name, List<MaterialsToFacing> materials) {
        this.id = id;
        this.name = name;
        this.materials = materials;
    }

    public Facing(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Facing(){
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\" : " + id +
                ", \"name\" : \"" + name +
                "\", \"services\" : " + materials +
                '}';
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<MaterialsToFacing> getMaterials() {
        return materials;
    }
    public void setMaterials(List<MaterialsToFacing> materials) {
        this.materials = materials;
    }
}
