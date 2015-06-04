package hotel;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;

public class RegisterController extends HttpServlet
{
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	{
		ArrayList<User> clientList = new ArrayList<User>();
		// url
		String url = null;

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

		if(request.getParameter("action").toString().equals("register"))
		{
			// Creating a new user
			User client = new User();
			client.setName(request.getParameter("name"));
			client.setCpf(request.getParameter("cpf"));
			client.setDateOfBirth(request.getParameter("dateOfBirth"));
			client.setGender(request.getParameter("gender"));
			client.setMaritalStatus(request.getParameter("maritalStatus"));
			client.setCity(request.getParameter("city"));
			client.setState(request.getParameter("state"));
			client.setPostalCode(request.getParameter("postalCode"));
			client.setEmail(request.getParameter("email"));
			client.setPassword(request.getParameter("password"));
			client.setCreationDate();

			// Adding a new client to the system
			clientList.add(client);

			// Creating the url
			url = "clientList.jsp";
		}
		else if(request.getParameter("action").toString().equals("edit"))
		{
			// Getting the current id
			int id = Integer.parseInt(request.getParameter("editId").toString());

			User client = (User) clientList.get(id);

			client.setName(request.getParameter("name"));
			client.setCpf(request.getParameter("cpf"));
			client.setDateOfBirth(request.getParameter("dateOfBirth"));
			client.setGender(request.getParameter("gender"));
			client.setMaritalStatus(request.getParameter("maritalStatus"));
			client.setCity(request.getParameter("city"));
			client.setState(request.getParameter("state"));
			client.setPostalCode(request.getParameter("postalCode"));
			client.setEmail(request.getParameter("email"));
			client.setPassword(request.getParameter("password"));
			client.setCreationDate();

			// Creating the url
			url = "clientList.jsp";
		}
		else
		{
			url = "error.jsp";
		}

		// Updating the client list
		session.setAttribute("clientList", clientList);

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

		String url = null;

		// Loading current session
		HttpSession session = request.getSession(true);
		// if there is no clientList create one
		if(session.getAttribute("clientList") == null)
		{
			session.setAttribute("clientList", new ArrayList<User>());
		}

		// Recovering the client List from the session
		ArrayList<User> clientList = (ArrayList) session.getAttribute("clientList");

		// Checking the request type
		// If the action is edit, get info from client (used in editClient.jsp)
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
		}
	}
}