/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author MrTu
 */
public class Category {

    int id;
    String name;
    String description;

    public Category() {
    }

    public String getDescription() {
        if (this.description==null) {
            return "";
        }
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int categoryid) {
        this.id = categoryid;
    }

    public String getName() {
        if (this.name==null) {
            return "";
        }
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
