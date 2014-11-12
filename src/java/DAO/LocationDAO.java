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
import Entity.Flight;
import Entity.Location;
import java.util.ArrayList;
import java.util.Iterator;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
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
}
