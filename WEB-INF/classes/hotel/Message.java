package hotel;

import java.util.Date;

public class Message implements Comparable<Message>
{
	// Message name
	private String name;

	// Message email 
	private String email;

	// Message phone 
	private String phone;

	// Message read status
	private boolean readStatus;

	// Message Date
	private Date date;

	// How did you hear about us? 
	private boolean newspaper;
	private boolean recomendation;
	private boolean socialNetworks;
	private boolean googleSearch;
	private boolean magazines;
	private boolean other;

	// Message
	public String message;

	// Setters
	public void setName(String name){
		this.name = name;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public void setNewspaper(boolean newspaper){
		this.newspaper = newspaper;
	}

	public void setRecomendation(boolean recomendation){
		this.recomendation = recomendation;
	}

	public void setSocialNetworks(boolean socialNetworks){
		this.socialNetworks = socialNetworks;
	}

	public void setGoogleSearch(boolean googleSearch){
		this.googleSearch = googleSearch;
	}

	public void setMagazines(boolean magazines){
		this.magazines = magazines;
	}

	public void setOther(boolean other){
		this.other = other;
	}

	public void setRead(boolean readStatus){
		this.readStatus = readStatus;
	}

	public void setDate(){
		this.date = new Date();
	}
	// Getters
	public String getName(){
		return this.name;
	}

	public String getEmail(){
		return this.email;
	}

	public String getPhone(){
		return this.phone;
	}

	public String getMessage(){
		return this.message;
	}

	public boolean getNewspaper(){
		return this.newspaper;
	}

	public boolean getRecomendation(){
		return this.recomendation;
	}

	public boolean getSocialNetworks(){
		return this.socialNetworks;
	}

	public boolean getGoogleSearch(){
		return this.googleSearch;
	}

	public boolean getMagazines(){
		return this.magazines;
	}

	public boolean getOther(){
		return this.other;
	}

	public boolean getRead(){
		return this.readStatus;
	}
	
	public Date getDate(){
		return this.date;
	}

	public String getDatestring(){
		return this.date.toString();
	}

	public int compareTo(Message message)
	{
		return -this.date.compareTo(message.date);
	}
}
