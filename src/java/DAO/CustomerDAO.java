/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Customer;
import Entity.Location;
import java.util.Iterator;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 *
 * @author Maxime
 */
public class CustomerDAO {
    public Customer getCustomerFromId(String webAppPath, int Id) {
        SAXReader reader = new SAXReader();
        Document document;
        try {
            document = reader.read(webAppPath + "/xml/Customers.xml");
            Element root = document.getRootElement();
            for (Iterator i = root.elementIterator("Customer"); i.hasNext();) {
                Element elt = (Element) i.next();
                int customerId = Integer.parseInt(elt.element("CustomerId").getText());
                if (customerId == Id) {
                    Customer customer = new Customer();
                    customer.setCustomerId(customerId);
                    customer.setEmail(elt.element("Email").getText());
                    customer.setName(elt.element("Name").getText());
                    customer.setPhone(elt.element("Phone").getText());
                    customer.setRole(Integer.parseInt(elt.element("Role").getText()));
                    return customer;
                }
            }
        } catch (DocumentException ex) {
            System.out.println("getCustomerFromId failed!");
        }
        return null;
    }
}
