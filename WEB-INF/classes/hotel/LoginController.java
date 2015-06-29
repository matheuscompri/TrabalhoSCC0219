package hotel;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

import org.hibernate.*;
import org.hibernate.cfg.*;

public class LoginController extends HttpServlet
{
	private static SessionFactory factory;

	// Method to create an user in the database 
	public Integer addUser(User user)
	{
		Session session = factory.openSession();
		Transaction tx = null;
		Integer userId = null;
		try
		{
	    	tx = session.beginTransaction();
	    	session.save(user);
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
		return userId;
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

	public void doPost(HttpServletRequest request, HttpServletResponse response){

		
		try
		{
        	factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        }
        catch (Throwable ex)
        { 
        	System.err.println("Failed to create sessionFactory object." + ex);
        	throw new ExceptionInInitializerError(ex);
       	}

       	// Saving the Clientlist
    	List<User> clientList = listUsers();

		// url
		String url = "error.jsp";

		// if there is no clientList create one
		if(clientList.isEmpty())
		{
			// Creating admin user
			User admin = new User();

			admin.setName("Administrator");
			admin.setEmail("admin@hotel.com");
			admin.setPassword("admin");
			admin.setCreationDate();
			admin.setAdministrator(true);

			// Adding the admin to the client list
			addUser(admin);
		}

		// Getting the username
		String username = request.getParameter("username");
		// and password
		String password = request.getParameter("password");

		// Initializing current user with null
		User currentUser = null;

		// Searching for the user that is trying to login
		for(User client : clientList)
		{
			// Verifying if the username and password are correct
			if(client.getEmail().equals(username) && client.getPassword().equals(password))
			{
				currentUser = client;
				break;
			}
		}

		// Loading current session
		HttpSession session = request.getSession();

		// If username and password are valid
		if(currentUser != null)
		{
			// Updating the currentUser
			session.setAttribute("currentUser", currentUser);
			
			// Creating the url
			url = "index.jsp";
		}
		else
		{
			// Username or password are invalid
			// Creating the error url
			url = "loginError.jsp";
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

	public void doGet(HttpServletRequest request, HttpServletResponse response){

		// url
		String url = "index.jsp";

		// Loading current session
		HttpSession session = request.getSession();

		// Checking if it is a logout request
		if(request.getParameter("action").equals("logout"))
		{
			// Cleaning currentUser
			session.setAttribute("currentUser", null);
			// Redirecting to login
			url = "login.jsp";
		}
		else
		{
			url = "error.jsp";
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
}
