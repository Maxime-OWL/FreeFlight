/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import DAO.LocationDAO;

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
    
    private String OriginName;
    private String DestinationName;
    private String PlaneName;
    

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

    public String getOriginName() {
        return OriginName;
    }

    public void setOriginName(String OriginName) {
        this.OriginName = OriginName;
    }

    public String getDestinationName() {
        return DestinationName;
    }

    public void setDestinationName(String DestinationName) {
        this.DestinationName = DestinationName;
    }

    public String getPlaneName() {
        return PlaneName;
    }

    public void setPlaneName(String PlaneName) {
        this.PlaneName = PlaneName;
    }
    
    

    
    
}
