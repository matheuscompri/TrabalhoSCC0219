package hotel;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class MessageController extends HttpServlet
{

	public void doPost(HttpServletRequest request, HttpServletResponse response)
	{
		// Loading current session
		HttpSession session = request.getSession();
		// if there is no messageList create one
		if(session.getAttribute("messageList") == null)
		{
			session.setAttribute("messageList", new ArrayList<Message>());
		}

		// Recovering the message List from the session
		ArrayList messageList = (ArrayList) session.getAttribute("messageList");

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

		// Adding a new message to the system
		messageList.add(message);

		// Sorting the Messages by date
		Collections.sort(messageList);

		// Updating the message list
		session.setAttribute("messageList", messageList);

		// Creating the url
		String url = "messageList.jsp";
	
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
		// if there is no message list create one
		if(session.getAttribute("messageList") == null)
		{
			session.setAttribute("messageList", new ArrayList<Message>());
		}

		// Recovering the message List from the session
		ArrayList messageList = (ArrayList) session.getAttribute("messageList");

		// Checking the request type
		if(request.getParameter("action").toString().equals("get"))
		{
			// Getting the current id
			int id = Integer.parseInt(request.getParameter("id").toString());

			Message message = (Message) messageList.get(id);

			// Marking as read
			message.setRead(true);

			// Updating the message list
			session.setAttribute("messageList", messageList);

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
			messageList.remove(id);

			// Saving the updated message list
			session.setAttribute("messageList", messageList);
			
			// Creating the url
			url = "messageList.jsp";
		}
		// If action is delete multiple entries
		else if(request.getParameter("action").toString().equals("mdel"))
		{
			String mdel;
			ArrayList<Message> toBeDeleted = new ArrayList<Message>();

			for(int i = 0; i < messageList.size(); i++)
			{
				// Checking if there are any mdel0, mdel1, mdel2...
				mdel = request.getParameter("mdel" + i);
				if(mdel != null)
				{
					// adding the message to the deletion array
					toBeDeleted.add( (Message) messageList.get(i));
					// This is necessary because the indexes change if we delete the message here
				}
			}

			// Deleting itens
			for(int i = 0; i < toBeDeleted.size(); i++)
			{
				// Safely removing users
				messageList.remove(toBeDeleted.get(i));
			}

			// Updating message list
			session.setAttribute("messageList", messageList);
			
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
