package hotel;

import java.util.*;

public class Hotel 
{
	private volatile static Hotel uniqueInstance;
	private ArrayList <Reservation> reservations;

	private Hotel(){
		this.reservations = new ArrayList <Reservation>();
	}

	public static Hotel getInstance(){

		if (uniqueInstance == null) {
			synchronized (Hotel.class) {
				if (uniqueInstance == null) {
					uniqueInstance = new Hotel();
				}
			}
		}
		return uniqueInstance;
	}

	public ArrayList <Reservation> getReservations(){
		return this.reservations;
	}

	public void setReservations(ArrayList <Reservation> reservations){
		this.reservations = reservations;
	}

}