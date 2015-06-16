package hotel;

import java.util.*;

public class Reservation
{
	// User
	private User client;

	// Arrival
	private Calendar arrival;

	// Departure
	private Calendar departure;

	//number of people
	private int adults;
	private int children;
	private int babies;


	// Setters
	public void setClient(User client){
		this.client = client;
	}

	public void setArrival(Calendar arrival){
		this.arrival = arrival;
	}

	public void setDeparture(Calendar departure){
		this.departure = departure;
	}

	public void setAdults(Integer adults){
		this.adults = adults;
	}

	public void setChildren(Integer children){
		this.children = children;
	}

	public void setBabies(Integer babies){
		this.babies = babies;
	}

	// Getters
	public User getClient(){
		return this.client;
	}

	public Calendar getArrival(){
		return this.arrival;
	}

	public Calendar getDeparture(){
		return this.departure;
	}

	public int getAdults(){
		return this.adults;
	}
	
	public int getChildren(){
		return this.children;
	}

	public int getBabies(){
		return this.babies;
	}

}
