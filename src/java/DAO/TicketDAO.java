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
import Entity.Plane;
import Entity.Ticket;
import java.util.ArrayList;
import java.util.Iterator;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class TicketDAO {

    public TicketDAO() {

    }

    

    public ArrayList<Ticket> fetchTickets(String webAppPath) {
        ArrayList<Ticket> arr = new ArrayList<Ticket>();
        SAXReader reader = new SAXReader();
        Document document;
        try {
            document = reader.read(webAppPath + "/xml/Tickets.xml");
            Element root = document.getRootElement();
            CustomerDAO customerDAO = new CustomerDAO();
            FlightDAO flightDAO = new FlightDAO();
            for (Iterator i = root.elementIterator("Ticket"); i.hasNext();) {
                Element elt = (Element) i.next();
                Ticket ticket = new Ticket();
                ticket.setTicketId(Integer.parseInt(elt.element("TicketId").getText()));
                ticket.setFlightId(Integer.parseInt(elt.element("FlightId").getText()));
                ticket.setCustomerId(Integer.parseInt(elt.element("CustomerId").getText()));
                ticket.setCustomerName(customerDAO.getCustomerFromId(webAppPath, ticket.getCustomerId()).getName());
                Flight flight = flightDAO.getFlightFromId(webAppPath, ticket.getFlightId());
                ticket.setArrivalDate(flight.getArrivalDate());
                ticket.setDepatureDate(flight.getDepartureDate());
                ticket.setOrigin(flight.getOriginName());
                ticket.setDestination(flight.getDestinationName());
     
                arr.add(ticket);
            }
        } catch (DocumentException ex) {
            System.out.println("fetchTickets failed!");
        }
        System.out.println("Done!");
        return arr;
    }
}
