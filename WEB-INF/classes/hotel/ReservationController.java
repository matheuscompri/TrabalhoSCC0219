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
			
			Calendar arrDate = new GregorianCalendar(Integer.parseInt(arr[2]),Integer.parseInt(arr[1]),Integer.parseInt(arr[0]));
			Calendar depDate = new GregorianCalendar(Integer.parseInt(dep[2]),Integer.parseInt(dep[1]),Integer.parseInt(dep[0]));
			

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
						url = "error.jsp";
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
		/*else if(request.getParameter("action").toString().equals("edit"))
		{
			// Getting the current id
			int id = Integer.parseInt(request.getParameter("editId").toString());

			Reservation res = lithium.getReservations().get(id);
			String arrival = request.getParameter("arr");
			String departure = request.getParameter("dep");
			String [] arr = arrival.split("/");
			String [] dep = departure.split("/");
			
			Calendar arrDate = new GregorianCalendar(Integer.parseInt(arr[2]),Integer.parseInt(arr[1]),Integer.parseInt(arr[0]));
			Calendar depDate = new GregorianCalendar(Integer.parseInt(dep[2]),Integer.parseInt(dep[1]),Integer.parseInt(dep[0]));
			
			res.setClient(client);
			res.setArrival(arrDate);
			res.setDeparture(depDate);
			res.setAdults(Integer.parseInt(request.getParameter("adults")));
			res.setChildren(Integer.parseInt(request.getParameter("kids")));
			res.setBabies(Integer.parseInt(request.getParameter("babies")));
			// Creating the url
			url = "ReservationList.jsp";
		}/*
		else
		{
			url = "error.jsp";
		}

		// Updating the client list
		session.setAttribute("clientList", clientList);

		*/
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

		/*String url = null;

		// Loading current session
		HttpSession session = request.getSession(true);
		// if there is no clientList create one
		if(session.getAttribute("clientList") == null)
		{
			session.setAttribute("clientList", new ArrayList<User>());
		}

		/*
		// Recovering the client List from the session
		ArrayList<User> clientList = (ArrayList) session.getAttribute("clientList");

		// Checking the request type
		// If the action is edit, get info from reservation (used in editReservation.jsp)
		if(request.getParameter("action").toString().equals("get"))
		{
			// Getting the current id
			int id = Integer.parseInt(request.getParameter("id").toString());

			User client = (User) clientList.get(id);

			// Temporary id for editing purpouses
			client.setId(id);

			// Saving the client info on session
			session.setAttribute("get", client);

			// Creating the url
			if(request.getParameter("next").equals("view"))
			{
				// View
				url = "viewClient.jsp";
			}
			else if(request.getParameter("next").equals("edit"))
			{
				// Edit 
				url = "editClient.jsp";
			}
			else
			{
				// Error
				url = "error.jsp";
			}
		}
		// If action is delete a single entry
		else if(request.getParameter("action").toString().equals("del"))
		{
			// Getting the current id
			int id = Integer.parseInt(request.getParameter("id").toString());
			
			// Removing the client
			clientList.remove(id);

			// Saving the updated client list
			session.setAttribute("clientList", clientList);
			
			// Creating the url
			url = "clientList.jsp";
		}
		// If action is delete multiple entries
		else if(request.getParameter("action").toString().equals("mdel"))
		{
			String mdel;
			ArrayList<User> toBeDeleted = new ArrayList<User>();

			for(int i = 0; i < clientList.size(); i++)
			{
				// Checking if there are any mdel0, mdel1, mdel2...
				mdel = request.getParameter("mdel" + i);
				if(mdel != null)
				{
					// adding the client to the deletion array
					toBeDeleted.add( (User) clientList.get(i));
					// This is necessary because the indexes change if we delete the clients here
				}
			}

			// Deleting itens
			for(int i = 0; i < toBeDeleted.size(); i++)
			{
				// Safely removing users
				clientList.remove(toBeDeleted.get(i));
			}

			// Updating client list
			session.setAttribute("clientList", clientList);
			
			// Creating the url
			url = "clientList.jsp";

		}
		else if(request.getParameter("action").toString().equals("search"))
		{
			// Search results to be returned
			ArrayList<User> searchResults = new ArrayList<User>();

			// Checking what kind of search is this
			if(request.getParameter("method").equals("name"))
			{
				// Name to be searched
				String name = request.getParameter("name");

				// Checking if the string matches the name
				for(User client : clientList)
				{
					if(client.getName().toLowerCase().contains(name))
					{
						searchResults.add(client);
					}
				}
			}
			else if(request.getParameter("method").equals("date"))
			{
				// Date format
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

				// Range
				Date after;
				Date before;
				
				try
				{
					// Converting the parameters to date
					after = dateFormat.parse(request.getParameter("after"));
					before = dateFormat.parse(request.getParameter("before"));

					for(User client : clientList)
					{
						if(client.getCreationDate().after(after) && client.getCreationDate().before(before))
						{
							searchResults.add(client);
						}
					}
				}
				catch(ParseException e)
				{
					e.printStackTrace();
				}

			}

			// Filtering the clientList
			session.setAttribute("searchResults", searchResults);

			// Creating the url
			url = "searchResults.jsp";
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
		}*/
	}
}
