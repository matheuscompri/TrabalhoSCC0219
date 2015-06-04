package hotel;

import java.util.*;

public class Reservation
{
	// User
	private User client;

	// Arrival
	private Date arrival;

	// Departure
	private Date departure;

	// Setters
	public void setClient(User client){
		this.client = client;
	}

	public void setArrival(Date arrival){
		this.arrival = arrival;
	}

	public void setDeparture(Date departure){
		this.departure = departure;
	}

	// Getters
	public User getClient(){
		return this.client;
	}

	public Date getArrival(){
		return this.arrival;
	}

	public Date getDeparture(){
		return this.departure;
	}
}
