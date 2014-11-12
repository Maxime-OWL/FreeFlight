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
    
    
}
