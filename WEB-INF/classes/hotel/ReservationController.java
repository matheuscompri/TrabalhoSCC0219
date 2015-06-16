package hotel;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;

public class ReservationController extends HttpServlet
{
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	{
		ArrayList<User> clientList = new ArrayList<User>();
		User client;
		// url
		String url = null;

		// Loading current session
		HttpSession session = request.getSession();
		
		// get an instance of the hotel
		Hotel lithium = Hotel.getInstance();


		// Recovering the user logged in
			client = (User) session.getAttribute("currentUser");
			//if client is not logged in
			if (client == null){

				try
				{
					// Redirecting to login page
					RequestDispatcher dispatcher = request.getRequestDispatcher("../login.jsp");
					dispatcher.forward(request, response);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}	
			}

		if(request.getParameter("action").toString().equals("reservation"))
		{
			
			// Creating the reservation
			Reservation res = new Reservation();

			//Setting the url to redirect in the case an error occurs
			url = "error.jsp";
			
			String arrival = request.getParameter("arr");
			String departure = request.getParameter("dep");
			String [] arr = arrival.split("/");
			String [] dep = departure.split("/");
			
			Calendar arrDate = new GregorianCalendar(Integer.parseInt(arr[2]),Integer.parseInt(arr[1]) - 1,Integer.parseInt(arr[0]));
			Calendar depDate = new GregorianCalendar(Integer.parseInt(dep[2]),Integer.parseInt(dep[1]) - 1,Integer.parseInt(dep[0]));
			

			res.setClient(client);
			res.setArrival(arrDate);
			res.setDeparture(depDate);
			res.setAdults(Integer.parseInt(request.getParameter("adults")));
			res.setChildren(Integer.parseInt(request.getParameter("kids")));
			res.setBabies(Integer.parseInt(request.getParameter("babies")));
			
			ArrayList <Reservation> reservations = lithium.getReservations();
			ArrayList <Reservation> temp = lithium.getReservations();
				
			if(reservations.isEmpty()){

				reservations.add(res);
				lithium.setReservations(reservations);
				session.setAttribute("reservationList", reservations);				
				// Creating the url
				url = "reservationList.jsp";
			}
			// Verifying if the reservation dates are available
			else {
				Boolean flag = true;
				for(Reservation r : temp){
					
					if((res.getArrival().compareTo(r.getArrival())>=0) && (res.getArrival().compareTo(r.getDeparture())<= 0)){
						url = "invalidReservationError.jsp";
						flag = false;		
					}
					
				}
				if(flag == true){

					reservations.add(res);
					// Creating the url
					url = "reservationList.jsp";
					lithium.setReservations(reservations);
					session.setAttribute("reservationList", reservations);
				}										
				
			}
		}
		try
		{
			// Redirecting
			RequestDispatcher dispatcher = request.getRequestDispatcher("../" + url);
			dispatcher.forward(request, response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response){
		
		// get an instance of the hotel
		Hotel lithium = Hotel.getInstance();

		// Session
		HttpSession session = request.getSession();

		// get reservations
		ArrayList <Reservation> reservations = lithium.getReservations();

		String url = null;

		// Checking the request type
		if(request.getParameter("action").toString().equals("get"))
		{
			// Getting the current id
			int id = Integer.parseInt(request.getParameter("id").toString());

			Reservation res = (Reservation) reservations.get(id);

			// Saving the reservation info on session
			session.setAttribute("get", res);
			
			// View
			url = "viewReservation.jsp";
		}
		// If action is delete a single entry
		else if(request.getParameter("action").toString().equals("del"))
		{
			// Getting the current id
			int id = Integer.parseInt(request.getParameter("id").toString());
			
			// Removing the client
			reservations.remove(id);

			// Saving the updated client list
			session.setAttribute("reservations", reservations);
			System.out.println("remove: " + id);
			
			// Creating the url
			url = "reservationList.jsp";
		}
		// If action is delete multiple entries
		else if(request.getParameter("action").toString().equals("mdel"))
		{
			String mdel;
			ArrayList<Reservation> toBeDeleted = new ArrayList<Reservation>();

			for(int i = 0; i < reservations.size(); i++)
			{
				// Checking if there are any mdel0, mdel1, mdel2...
				mdel = request.getParameter("mdel" + i);
				if(mdel != null)
				{
					// adding the client to the deletion array
					toBeDeleted.add( (Reservation) reservations.get(i));
					// This is necessary because the indexes change if we delete the clients here
				}
			}

			// Deleting itens
			for(int i = 0; i < toBeDeleted.size(); i++)
			{
				// Safely removing users
				reservations.remove(toBeDeleted.get(i));
			}

			// Updating client list
			session.setAttribute("reservations", reservations);
			
			// Creating the url
			url = "reservationList.jsp";

		}
		else
		{
			url = "error.jsp";
		}

		// Redirecting
		try
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("../" + url);
			dispatcher.forward(request, response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
