package hotel;

import java.util.*;

public class Reservation
{
	private int id;
	// User
	private User client;

	// userId
	private int clientId;

	// Arrival
	private Date arrival;

	// Departure
	private Date departure;

	//number of people
	private int adults;
	private int children;
	private int babies;


	// Setters
	public void setId(int id){
		this.id = id;
	}

	public void setClient(User client){
		this.client = client;
		this.clientId = client.getId();
	}

	public void setClientId(int clientId){
		this.clientId = clientId;
	}

	public void setArrival(Date arrival){
		this.arrival = arrival;
	}

	public void setDeparture(Date departure){
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
	public int getId(){
		return this.id;
	}

	public User getClient(){
		return this.client;
	}
	
	public int getClientId(){
		return this.clientId;
	}
	
	public Date getArrival(){
		return this.arrival;
	}

	public Date getDeparture(){
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
