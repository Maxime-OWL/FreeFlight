/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author ducfpt
 */
import Controller.Controller;
import Entity.Flight;
import Entity.Location;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class LocationDAO {

    public LocationDAO() {

    }

    public Location getLocationFromId(String webAppPath, int Id) {
        SAXReader reader = new SAXReader();
        Document document;
        try {
            document = reader.read(webAppPath + "/xml/Locations.xml");
            Element root = document.getRootElement();
            for (Iterator i = root.elementIterator("Location"); i.hasNext();) {
                Element elt = (Element) i.next();
                int locationId = Integer.parseInt(elt.element("LocationId").getText());
                if (locationId == Id) {
                    Location location = new Location();
                    location.setLocationId(locationId);
                    location.setName(elt.element("Name").getText());
                    return location;
                }
            }
        } catch (DocumentException ex) {
            System.out.println("getLocationFromId failed!");
        }
        return null;
    }

    public ArrayList<Location> fetchLocations(String webAppPath) {
        ArrayList<Location> arr = new ArrayList<Location>();
        SAXReader reader = new SAXReader();
        Document document;
        try {
            document = reader.read(webAppPath + "/xml/Locations.xml");
            Element root = document.getRootElement();
            LocationDAO locationDAO = new LocationDAO();
            for (Iterator i = root.elementIterator("Location"); i.hasNext();) {
                Element elt = (Element) i.next();
                Location location = new Location();
                location.setLocationId(Integer.parseInt(elt.element("LocationId").getText()));
                location.setName(elt.element("Name").getText());
                arr.add(location);
            }
        } catch (DocumentException ex) {
            System.out.println("fetchLocations failed!");
        }
        System.out.println("Done!");
        return arr;
    }

    public boolean addNewLocation(String webAppPath, String location) {
        try {
            System.out.println("Start adding new location.."+location);
            SAXReader reader = new SAXReader();
            Document document = reader.read(webAppPath + "/xml/Locations.xml");
            Element root = document.getRootElement();
            int max = 0;
            for (Iterator i = root.elementIterator("Location"); i.hasNext();) {
                Element elt = (Element) i.next();
                int cur = Integer.parseInt(elt.element("LocationId").getText());
                if (cur > max ) {
                    max = cur;
                }
            }
            System.out.println(max);
            //Node node = document.selectSingleNode("/Locations/Location[not(../Location/LocationId > LocationId)]");
            //String id = node.valueOf("LocationId");
            //System.out.println(id);
            return true;
        } catch (DocumentException ex) {
            System.out.println("Add New Location Failed!");
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
