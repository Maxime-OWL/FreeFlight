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
import Entity.Location;
import Entity.Plane;
import java.util.ArrayList;
import java.util.Iterator;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class PlaneDAO {

    public PlaneDAO() {

    }

    public Plane getPlaneFromId(String webAppPath, int Id) {
        SAXReader reader = new SAXReader();
        Document document;
        try {
            document = reader.read(webAppPath + "/xml/Planes.xml");
            Element root = document.getRootElement();
            for (Iterator i = root.elementIterator("Plane"); i.hasNext();) {
                Element elt = (Element) i.next();
                int PlaneId = Integer.parseInt(elt.element("PlaneId").getText());
                if (PlaneId == Id) {
                    Plane plane = new Plane();
                    plane.setPlaneId(PlaneId);
                    plane.setModel(elt.element("Model").getText());
                    plane.setCapacity(Integer.parseInt(elt.element("Capacity").getText()));
                    return plane;
                }
            }
        } catch (DocumentException ex) {
            System.out.println("getLocationFromId failed!");
        }
        return null;
    }

    public ArrayList<Plane> fetchPlanes(String webAppPath) {
        ArrayList<Plane> arr = new ArrayList<Plane>();
        SAXReader reader = new SAXReader();
        Document document;
        try {
            document = reader.read(webAppPath + "/xml/Planes.xml");
            Element root = document.getRootElement();
            PlaneDAO planeDAO = new PlaneDAO();
            for (Iterator i = root.elementIterator("Plane"); i.hasNext();) {
                Element elt = (Element) i.next();
                Plane plane = new Plane();
                plane.setPlaneId(Integer.parseInt(elt.element("PlaneId").getText()));
                plane.setModel(elt.element("Model").getText());
                plane.setCapacity(Integer.parseInt(elt.element("Capacity").getText()));
                arr.add(plane);
            }
        } catch (DocumentException ex) {
            System.out.println("fetchPlanes failed!");
        }
        System.out.println("Done!");
        return arr;
    }
}
