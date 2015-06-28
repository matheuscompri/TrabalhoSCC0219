package hotel;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;

import org.hibernate.*;
import org.hibernate.cfg.*;

public class RegisterController extends HttpServlet
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

	// Method to update an user
	public void updateUser(Integer userId, User newUser){
		Session session = factory.openSession();
		Transaction tx = null;
		try
		{
	    	tx = session.beginTransaction();
	     	User user = (User) session.get(User.class, userId); 

	    	user.setName(newUser.getName());
			
			user.setName(newUser.getName());
			user.setCpf(newUser.getCpf());
			user.setDateOfBirth(newUser.getDateOfBirth());
			user.setGender(newUser.getGender());
			user.setMaritalStatus(newUser.getMaritalStatus());
			user.setCity(newUser.getCity());
			user.setState(newUser.getState());
			user.setPostalCode(newUser.getPostalCode());
			user.setEmail(newUser.getEmail());
			user.setPassword(newUser.getPassword());
			user.setCreationDate();

			session.update(user); 
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

	// Method to delete an user
	public void deleteUser(Integer userId)
	{
		Session session = factory.openSession();
		Transaction tx = null;
		try
		{
	     	tx = session.beginTransaction();
	     	User user = (User) session.get(User.class, userId); 
	    	session.delete(user); 
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
		// Url
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

       	// Saving the Clientlist
    	List<User> clientList = listUsers();

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

			// Adding a new client
			addUser(client);

			// Creating the url
			url = "login.jsp";
		}
		else if(request.getParameter("action").toString().equals("edit"))
		{
			// Getting the current id
			int id = Integer.parseInt(request.getParameter("editId").toString());

			User client = (User) getUser(clientList, id);

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

			updateUser(id, client);

			// Creating the url
			url = "clientList.jsp";
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

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	{
		// Url
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

       	// Saving the Clientlist
    	List<User> clientList = listUsers();

    	// Loading current session
		HttpSession session = request.getSession();

		// Checking the request type
		// If the action is edit, get info from client (used in editClient.jsp)
		if(request.getParameter("action").toString().equals("getClientList"))
		{
			// Saving the client List into the session
			session.setAttribute("clientList", clientList);

			url = "clientList.jsp";
		}
		else if(request.getParameter("action").toString().equals("get"))
		{
			// Getting the current id
			int id = Integer.parseInt(request.getParameter("id").toString());

			User client = (User) getUser(clientList, id);

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
			deleteUser(id);
			
			// Creating the url
			url = "clientList.jsp";
		}
		// If action is delete multiple entries
		else if(request.getParameter("action").toString().equals("mdel"))
		{
			String mdel;
			ArrayList<User> toBeDeleted = new ArrayList<User>();
	
			// Serach results ArrayList
			ArrayList<User> searchResults;

			// if there is no searchResults create one
			if(session.getAttribute("searchResults") == null)
			{
				searchResults = new ArrayList<User>();
				session.setAttribute("searchResults", searchResults);
			}

			// Recovering the client List from the session
			searchResults = (ArrayList) session.getAttribute("searchResults");

			// Verifying if it came from searchResults.jsp
			boolean isSearchResult = request.getParameter("searchResults") != null;

			Enumeration enumeration = request.getParameterNames();
        	while (enumeration.hasMoreElements()) {
            	String parameterName = (String) enumeration.nextElement();
            	if(parameterName.contains("mdel"))
            	{
            		parameterName = parameterName.replace("mdel", "");
            		int id = Integer.parseInt(parameterName);

            		System.out.println("Parameter: " + id);
            		deleteUser(id);
            	}
        	}
        				
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
				if(request.getParameter("name") == null)
				{
					name = "";
				}

				// Checking if the string matches the name
				for(User client : clientList)
				{
					if(client.getName().toLowerCase().contains(name.toLowerCase()))
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
						System.out.println("client " + client.getName() + " " + client.getCreationDate());

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
