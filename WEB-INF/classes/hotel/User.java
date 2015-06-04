package hotel;

import java.util.*;

public class User implements Comparable<User>
{
	// Id
	private int id;

	// Name
	private String name;

	// CPF
	private String cpf;

	// Date of Birth
	private String dateOfBirth;

	// Gender
	private String gender;

	// Marital Status
	private String maritalStatus;

	// City
	private String city;
	
	// State
	private String state;

	// Postal Code
	private String postalCode;

	// Email
	private String email;

	// Password
	private String password;

	// Administrator
	private boolean administrator;

	// Creation date
	private Date creationDate;

	// Setters
	public void setId(int id){
		this.id = id;
	}

	public void setName(String name){
		this.name = name;
	}

	public void setCpf(String cpf){
		this.cpf= cpf;
	}

	public void setDateOfBirth(String dateOfBirth){
		this.dateOfBirth = dateOfBirth;
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public void setMaritalStatus(String maritalStatus){
		this.maritalStatus = maritalStatus;
	}


	public void setCity(String city){
		this.city = city;
	}


	public void setState(String state){
		this.state = state;
	}


	public void setPostalCode(String postalCode){
		this.postalCode = postalCode;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public void setAdministrator(boolean administrator){
		this.administrator = administrator;
	}

	public void setCreationDate(){
		this.creationDate = new Date();
	}


	// Getters
	public int getId(){
		return this.id;
	}

	public String getName()
	{
		return this.name;
	}

	public String getCpf()
	{
		return this.cpf;
	}
	
	public String getDateOfBirth()
	{
		return this.dateOfBirth;
	}

	public String getGender()
	{
		return this.gender;
	}

	public String getMaritalStatus()
	{
		return this.maritalStatus;
	}

	public String getCity()
	{
		return this.city;
	}

	public String getState()
	{
		return this.state;
	}

	public String getPostalCode()
	{
		return this.postalCode;
	}

	public String getEmail()
	{
		return this.email;
	}

	public String getPassword()
	{
		return this.password;
	}

	public boolean getAdministrator(){
		return this.administrator;
	}

	public Date getCreationDate(){
		return this.creationDate;
	}

	public int compareTo(User user)
	{
		return -this.creationDate.compareTo(user.creationDate);
	}
}
