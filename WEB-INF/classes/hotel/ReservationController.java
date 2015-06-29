package hotel;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;

import org.hibernate.*;
import org.hibernate.cfg.*;

public class ReservationController extends HttpServlet
{
	private static SessionFactory factory;

	// Method that return the user by userId
	public User getUser(List<User> clientList, int userId)
	{
		for(User tmp : clientList)
		{
			if(tmp.getId() == userId)
				return tmp;
		}
		return null;
	}

	// Method that return the reservation by reservationId
	public Reservation getReservation(List<Reservation> reservationList, int reservationId)
	{
		for(Reservation tmp : reservationList)
		{
			if(tmp.getId() == reservationId)
				return tmp;
		}
		return null;
	}

	// Method to create a reservation in the database 
	public Integer addReservation(Reservation reservation)
	{
		Session session = factory.openSession();
		Transaction tx = null;
		Integer reservationId = null;
		try
		{
	    	tx = session.beginTransaction();
	    	session.save(reservation);
	    	tx.commit();
		}
		catch (HibernateException e)
		{
	    	if (tx!=null) tx.rollback();
	    	e.printStackTrace();
	  	}
	  	finally
	  	{
	     	session.close();
		}
		return reservationId;
	}

	// Method that list all reservations
	public List<Reservation> listReservations(){
		List<Reservation> reservationList = null;

		Session session = factory.openSession();
		Transaction tx = null;
		try
		{
	    	tx = session.beginTransaction();
	    	reservationList = session.createQuery("FROM Reservation").list(); 
	     	tx.commit();
	  	}
	  	catch (HibernateException e)
	  	{
	     	if (tx!=null) tx.rollback();
	     	e.printStackTrace(); 
	  	}
	  	finally 
	  	{
	     	session.close(); 
	  	}
	  	return reservationList;
	}

	// Method that list all users
	public List<User> listUsers(){
		List<User> clientList = null;

		Session session = factory.openSession();
		Transaction tx = null;
		try
		{
	    	tx = session.beginTransaction();
	    	clientList = session.createQuery("FROM User").list(); 
	    	System.out.println("Database: ");
	    	for (User cl : clientList)
	    	{
		        System.out.println("Id: " + cl.getId()); 
		        System.out.println("Name: " + cl.getName()); 
	    	}
	     	tx.commit();

	     	return clientList;

	  	}
	  	catch (HibernateException e)
	  	{
	     	if (tx!=null) tx.rollback();
	     	e.printStackTrace(); 
	  	}
	  	finally 
	  	{
	     	session.close(); 
	  	}
	  	return clientList;
	}

	// Method to delete a reservation
	public void deleteReservation(Integer reservationId)
	{
		Session session = factory.openSession();
		Transaction tx = null;
		try
		{
	     	tx = session.beginTransaction();
	     	Reservation reservation = (Reservation) session.get(Reservation.class, reservationId); 
	    	session.delete(reservation); 
	    	tx.commit();
	  	}
	  	catch (HibernateException e)
	  	{
	     	if (tx!=null) tx.rollback();
	     	e.printStackTrace(); 
	  	}
	  	finally
	  	{
	    	session.close(); 
	  	}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
	{
		// url
		String url = "error.jsp";
		
		try
		{
        	factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        }
        catch (Throwable ex)
        { 
        	System.err.println("Failed to create sessionFactory object." + ex);
        	throw new ExceptionInInitializerError(ex);
       	}

		// Getting the reservation list
		List<Reservation> reservationList = listReservations();

		// User
		User client;

		// Loading current session
		HttpSession session = request.getSession();
		
		// Recovering the user logged in
		client = (User) session.getAttribute("currentUser");

		//if client is not logged in
		if (client == null)
		{
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
			
			// Reservation
			Reservation res;

			// Date format
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

			// Boolean variable that indicates if the reservation can be stored in the database
			Boolean success = true;

			// Range of the reservation
			Date arrival;
			Date departure;
			
			try
			{
				// Converting the parameters to date
				arrival = dateFormat.parse(request.getParameter("arr"));
				departure = dateFormat.parse(request.getParameter("dep"));

				// Creating a new reservation
				res = res = new Reservation();
				res.setClient(client);
				res.setArrival(arrival);
				res.setDeparture(departure);

				res.setAdults(Integer.parseInt(request.getParameter("adults")));
				res.setChildren(Integer.parseInt(request.getParameter("kids")));
				res.setBabies(Integer.parseInt(request.getParameter("babies")));

				// Verifying if this reservation is possible
				for(Reservation r : reservationList){
					
					if((res.getArrival().compareTo(r.getArrival())>=0) && (res.getArrival().compareTo(r.getDeparture())<= 0)){
						url = "invalidReservationError.jsp";
						success = false;		
					}
					
				}

				// If it possible then save to the database
				if(success == true){
					addReservation(res);

					// Creating the url
					url = "reservationList.jsp";
				}
				else
				{
					request.setAttribute("errormsg", "There is another reservation in this date. Please choose another one...");
				}

			}
			catch(ParseException e)
			{
				e.printStackTrace();
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

		factory.close();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	{
		// URL
		String url = "error.jsp";

		try
		{
        	factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        }
        catch (Throwable ex)
        { 
        	System.err.println("Failed to create sessionFactory object." + ex);
        	throw new ExceptionInInitializerError(ex);
       	}

		// Getting the reservations list
		List<Reservation> reservationList = listReservations();

       	// Getting the user List
		List<User> clientList = listUsers();

		// Session
		HttpSession session = request.getSession();

		// Checking if the request is to get a reservation
		if(request.getParameter("action").toString().equals("get"))
		{
			// Getting the current id
			int id = Integer.parseInt(request.getParameter("id").toString());

			Reservation res = (Reservation) getReservation(reservationList, id);
			// Recovering user info by userId field on reservations
			User user = getUser(clientList, res.getClientId());
			// Setting the user on the reservation
			res.setClient(user);

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
			deleteReservation(id);
			
			// Creating the url
			url = "reservationList.jsp";
		}
		// If action is delete multiple entries
		else if(request.getParameter("action").toString().equals("mdel"))
		{
			Enumeration enumeration = request.getParameterNames();
        	while (enumeration.hasMoreElements()) {
            	String parameterName = (String) enumeration.nextElement();
            	if(parameterName.contains("mdel"))
            	{
            		parameterName = parameterName.replace("mdel", "");
            		int id = Integer.parseInt(parameterName);
            		deleteReservation(id);
            	}
        	}
	
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

	factory.close();	
	}
}
