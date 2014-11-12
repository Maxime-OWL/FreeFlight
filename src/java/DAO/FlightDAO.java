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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import java.util.ArrayList;

public class FlightDAO {

    private static final Set<String> generatedNumbers = new HashSet<>();
    private Session session = null;
    Message message = null;

    public FlightDAO() {

    }

    public Flight getFlightFromId(String webAppPath, int Id) {
        SAXReader reader = new SAXReader();
        Document document;
        try {
            document = reader.read(webAppPath + "/xml/Flights.xml");
            Element root = document.getRootElement();
            LocationDAO locationDAO = new LocationDAO();
            PlaneDAO planeDAO = new PlaneDAO();
            for (Iterator i = root.elementIterator("Flight"); i.hasNext();) {
                Element elt = (Element) i.next();
                if (Id == Integer.parseInt(elt.element("FlightId").getText())) {
                    Flight flight = new Flight();
                    flight.setFlightId(Integer.parseInt(elt.element("FlightId").getText()));
                    flight.setOrigin(Integer.parseInt(elt.element("Origin").getText()));
                    flight.setDestination(Integer.parseInt(elt.element("Destination").getText()));
                    flight.setFee(elt.element("Fee").getText());
                    flight.setPlane(Integer.parseInt(elt.element("Plane").getText()));
                    flight.setDepartureDate(elt.element("DepartureTime").getText());
                    flight.setArrivalDate(elt.element("ArrivalTime").getText());
                    flight.setOriginName(locationDAO.getLocationFromId(webAppPath, flight.getOrigin()).getName());
                    flight.setDestinationName(locationDAO.getLocationFromId(webAppPath, flight.getDestination()).getName());
                    flight.setPlaneName(planeDAO.getPlaneFromId(webAppPath, flight.getPlane()).getModel());
                    return flight;
                }
            }
        } catch (DocumentException ex) {
            System.out.println("fetchFlights failed!");
        }
        System.out.println("Done!");
        return null;
    }

    public ArrayList<Flight> fetchFlights(String webAppPath) {
        ArrayList<Flight> arr = new ArrayList<Flight>();
        SAXReader reader = new SAXReader();
        Document document;
        try {
            document = reader.read(webAppPath + "/xml/Flights.xml");
            Element root = document.getRootElement();
            LocationDAO locationDAO = new LocationDAO();
            PlaneDAO planeDAO = new PlaneDAO();
            for (Iterator i = root.elementIterator("Flight"); i.hasNext();) {
                Element elt = (Element) i.next();
                Flight flight = new Flight();
                flight.setFlightId(Integer.parseInt(elt.element("FlightId").getText()));
                flight.setOrigin(Integer.parseInt(elt.element("Origin").getText()));
                flight.setDestination(Integer.parseInt(elt.element("Destination").getText()));
                flight.setFee(elt.element("Fee").getText());
                flight.setPlane(Integer.parseInt(elt.element("Plane").getText()));
                flight.setDepartureDate(elt.element("DepartureTime").getText());
                flight.setArrivalDate(elt.element("ArrivalTime").getText());
                flight.setOriginName(locationDAO.getLocationFromId(webAppPath, flight.getOrigin()).getName());
                flight.setDestinationName(locationDAO.getLocationFromId(webAppPath, flight.getDestination()).getName());
                flight.setPlaneName(planeDAO.getPlaneFromId(webAppPath, flight.getPlane()).getModel());
                arr.add(flight);
            }
        } catch (DocumentException ex) {
            System.out.println("fetchFlights failed!");
        }
        System.out.println("Done!");
        return arr;
    }

    public String formatDate(String datetime) throws ParseException {

        // *** note that it's "yyyy-MM-dd hh:mm:ss" not "yyyy-mm-dd hh:mm:ss"  
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = dt.parse(datetime);

        // *** same for the format String below
        SimpleDateFormat dt1 = new SimpleDateFormat("MMMMM dd, yyyy");
        return dt1.format(date);
    }

    public boolean sendMail(String sendTo, String subject, String body) {
        final String username = "tupvse02404@fpt.edu.vn";
        final String password = "vantu1992";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username,
                                password);
                    }
                });
        try {

            message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(sendTo));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

            return true;
        } catch (MessagingException e) {
            //throw new RuntimeException(e);
            return false;
        }
    }

    public double getValidPrice(String str) {
        double d = 0;
        try {
            d = Double.parseDouble(str);
            if (d < 0) {
                return -1;
            }
        } catch (NumberFormatException nfe) {
            return -1;
        }
        return d;
    }

    public boolean isPasswordValid(String password) {
        return password != null && !password.isEmpty() && password.length() >= 6;
    }

    public String getValidYoutubeUrl(String v_youtube) {
        if (v_youtube == null || !v_youtube.contains("?v=")) {
            return "";
        }
        //System.out.println("Processing string: '"+v_youtube+"'");
        int startIndex = v_youtube.indexOf("?v=");
        if (startIndex == -1) {
            return "";
        }
        startIndex = startIndex + 3;
        //System.out.println("Index where v= at is "+startIndex);
        String result = v_youtube.substring(startIndex, startIndex + 11);
        if (result != null && result.length() == 11) {
            return result;
        }
        return "";
    }

    public DateTime getDateTimeFromString(String dateString) {
        if (dateString.isEmpty()) {
            return DateTime.now();
        }
        org.joda.time.format.DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy/MM/dd HH:mm");
        DateTime dt = formatter.parseDateTime(dateString);
        return dt;
    }

    public DateTime getDateTimeFromString2(String dateString) {
        if (dateString.isEmpty()) {
            return DateTime.now();
        }
        org.joda.time.format.DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss");
        DateTime dt = formatter.parseDateTime(dateString);
        return dt;
    }
}
