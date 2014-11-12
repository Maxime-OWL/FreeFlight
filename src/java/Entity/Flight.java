/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Duc
 */
public class Flight {
    private int FlightId;
    private int Origin;
    private int Destination;
    private String Fee;
    private int Plane;
    private String DepartureDate;
    private String ArrivalDate;

    public int getFlightId() {
        return FlightId;
    }

    public void setFlightId(int FlightId) {
        this.FlightId = FlightId;
    }

    public int getOrigin() {
        return Origin;
    }

    public void setOrigin(int Origin) {
        this.Origin = Origin;
    }

    public int getDestination() {
        return Destination;
    }

    public void setDestination(int Destination) {
        this.Destination = Destination;
    }

    public String getFee() {
        return Fee;
    }

    public void setFee(String Fee) {
        this.Fee = Fee;
    }

    public int getPlane() {
        return Plane;
    }

    public void setPlane(int Plane) {
        this.Plane = Plane;
    }

    public String getDepartureDate() {
        return DepartureDate;
    }

    public void setDepartureDate(String DepartureDate) {
        this.DepartureDate = DepartureDate;
    }

    public String getArrivalDate() {
        return ArrivalDate;
    }

    public void setArrivalDate(String ArrivalDate) {
        this.ArrivalDate = ArrivalDate;
    }

    
    
}
