package hotel;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class LoginController extends HttpServlet
{
	public void doPost(HttpServletRequest request, HttpServletResponse response){

		ArrayList<User> clientList = new ArrayList<User>();
		// Loading current session
		HttpSession session = request.getSession();
		// if there is no clientList create one
		if(session.getAttribute("clientList") == null)
		{
			// Creating admin user
			User admin = new User();

			admin.setName("Administrator");
			admin.setEmail("admin@hotel.com");
			admin.setPassword("admin");
			admin.setAdministrator(true);

			// Adding the admin to the client list
			clientList.add(admin);
			session.setAttribute("clientList", clientList);
		}

		// Recovering the client List from the session
		clientList = (ArrayList) session.getAttribute("clientList");

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

		// Url
		String url;

		// If username and password are valid
		if(currentUser != null)
		{
			// Updating the currentUser
			session.setAttribute("currentUser", currentUser);
			
			// Creating the url
			url = "clientList.jsp";
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
	}
}
