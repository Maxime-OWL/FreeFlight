/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Maxime
 */
public class Ticket {
    int TicketId;
    int FlightId;
    int CustomerId; 
    String CustomerName;
    String Origin;
    String Destination;
    String DepatureDate;
    String ArrivalDate;
    
    public int getTicketId() {
        return TicketId;
    }

    public void setTicketId(int TicketId) {
        this.TicketId = TicketId;
    }

    public int getFlightId() {
        return FlightId;
    }

    public void setFlightId(int FlightId) {
        this.FlightId = FlightId;
    }

    public int getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(int CustomerId) {
        this.CustomerId = CustomerId;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

    public String getOrigin() {
        return Origin;
    }

    public void setOrigin(String Origin) {
        this.Origin = Origin;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String Destination) {
        this.Destination = Destination;
    }

    public String getDepatureDate() {
        return DepatureDate;
    }

    public void setDepatureDate(String DepatureDate) {
        this.DepatureDate = DepatureDate;
    }

    public String getArrivalDate() {
        return ArrivalDate;
    }

    public void setArrivalDate(String ArrivalDate) {
        this.ArrivalDate = ArrivalDate;
    }
    
    
    
    
}
