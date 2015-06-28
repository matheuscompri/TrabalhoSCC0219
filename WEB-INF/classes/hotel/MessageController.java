package hotel;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

import org.hibernate.*;
import org.hibernate.cfg.*;

public class MessageController extends HttpServlet
{
	private static SessionFactory factory;
	
	// Method that return the user by userId
	public Message getMessage(List<Message> messageList, int messageId)
	{
		for(Message tmp : messageList)
		{
			if(tmp.getId() == messageId)
				return tmp;
		}
		return null;
	}

	// Method to create a message in the database 
	public Integer addMessage(Message message)
	{
		Session session = factory.openSession();
		Transaction tx = null;
		Integer messageId = null;
		try
		{
	    	tx = session.beginTransaction();
	    	session.save(message);
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
		return messageId;
	}

	// Method that list all messages
	public List<Message> listMessages(){
		List<Message> messageList = null;

		Session session = factory.openSession();
		Transaction tx = null;
		try
		{
	    	tx = session.beginTransaction();
	    	messageList = session.createQuery("from Message order by message_date desc").list(); 
	     	tx.commit();

	     	return messageList;

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
	  	return messageList;
	}

	// Method to update a message
	public void updateReadStatus(Integer messageId, boolean status){
		Session session = factory.openSession();
		Transaction tx = null;
		try
		{
	    	tx = session.beginTransaction();
	     	Message message = (Message) session.get(Message.class, messageId); 

	    	message.setRead(status);
			
			session.update(message); 
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

	// Method to delete a message
	public void deleteMessage(Integer messageId)
	{
		Session session = factory.openSession();
		Transaction tx = null;
		try
		{
	     	tx = session.beginTransaction();
	     	Message message = (Message) session.get(Message.class, messageId); 
	    	session.delete(message); 
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

		// Creating the url
		String url = "index.jsp";

		try
		{
        	factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        }
        catch (Throwable ex)
        { 
        	System.err.println("Failed to create sessionFactory object." + ex);
        	throw new ExceptionInInitializerError(ex);
       	}

		// Loading current session
		HttpSession session = request.getSession();

		// Getting the message list
		List<Message> messageList = listMessages();

		// Creating a new message
		Message message = new Message();
		message.setName(request.getParameter("name"));
		message.setEmail(request.getParameter("email"));
		message.setPhone(request.getParameter("phone"));
		message.setMessage(request.getParameter("message"));
		message.setRead(false);
		message.setDate();

		message.setNewspaper(request.getParameter("newspaper") != null);
		message.setRecomendation(request.getParameter("recomendation") != null);
		message.setSocialNetworks(request.getParameter("socialNetworks") != null);
		message.setGoogleSearch(request.getParameter("googleSearch") != null);
		message.setMagazines(request.getParameter("magazines") != null);
		message.setOther(request.getParameter("other") != null);

		// Adding a new message to the database
		addMessage(message);
	
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

		// Getting the message list
		List<Message> messageList = listMessages();

		// Loading current session
		HttpSession session = request.getSession(true);

		// Checking the request type
		if(request.getParameter("action").toString().equals("getMessageList"))
		{
			// Sorting the Messages by date
			//Collections.sort(messageList);

			// Updating the message list
			session.setAttribute("messageList", messageList);

			url = "messageList.jsp";
		}
		else if(request.getParameter("action").toString().equals("get"))
		{
			// Getting the current id
			int id = Integer.parseInt(request.getParameter("id").toString());

			Message message = (Message) getMessage(messageList, id);

			// Marking as read
			updateReadStatus(id, true);

			// Saving the message info on session
			session.setAttribute("get", message);

			// Creating the url
			url = "viewMessage.jsp";
		}
		// If action is delete a single entry
		else if(request.getParameter("action").toString().equals("del"))
		{
			// Getting the current id
			int id = Integer.parseInt(request.getParameter("id"));
			
			// Removing the client
			deleteMessage(id);

			// Creating the url
			url = "messageList.jsp";
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
            		deleteMessage(id);
            	}
        	}

			// Creating the url
			url = "messageList.jsp";

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
