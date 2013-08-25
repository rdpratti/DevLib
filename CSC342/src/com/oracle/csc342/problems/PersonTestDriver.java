package com.oracle.csc342.problems;
import java.sql.*;
import java.math.*;
import java.util.*;



public class PersonTestDriver {
	
	List<Person> contacts = new ArrayList<Person>();
	String hostname;
	String port;
	String sid;
	String id;
	String pwrd;

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String inHostname) {
		this.hostname = inHostname;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String inPort) {
		this.port = inPort;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String inSid) {
		this.sid = inSid;
	}

	public String getId() {
		return id;
	}

	public void setId(String inId) {
		this.id = inId;
	}

	public String getPwrd() {
		return pwrd;
	}

	public void setPwrd(String inPwrd) {
		this.pwrd = inPwrd;
	}
      

	public void testCreate() throws SQLException, Exception {
		
		
		BigDecimal increment = new BigDecimal(1);
		BigDecimal nextPersonId = new BigDecimal(1);  
		Timestamp tstamp;
		Calendar tempCalendar;
		Address address;
		Connection conn;
		
		conn = DBConnect.getConnection(hostname, port, sid, id, pwrd);
		PersonDAO personDAO = new PersonDAO();
		nextPersonId  = personDAO.findMaxPersonId();
		nextPersonId = nextPersonId.add(increment);
	    Person worker = new Person(nextPersonId);
	    worker.setFirstName("Roland");
	    worker.setLastName("DePratti");
	    address = new Address();
	    worker.setAddress(address);
	    worker.address.setStreetAddress("61 New King St");
	    worker.address.setCity("Enfield");
	    worker.address.setState("Connecticut");
	    worker.address.setPostalCode("06082");
	    worker.address.setCity("Enfield");
	    worker.setPhoneNum("1-860-745-7745");
	    worker.setFaxNum("1-860-745-3002");
	    /* set timestamp for birth date */
	    tempCalendar = GregorianCalendar.getInstance();
	    tempCalendar.set(Calendar.DAY_OF_MONTH, 18);
	    tempCalendar.set(Calendar.MONTH, 5);
	    tempCalendar.set(Calendar.YEAR, 1953);
	    tstamp = new Timestamp(tempCalendar.getTimeInMillis());
	    worker.setBirthDate(tstamp);
	    contacts.add(worker);
	    
	    Person worker2 = new Person(nextPersonId.add(increment));
	    worker2.setFirstName("Thomas");
	    worker2.setLastName("DePratti");
	    Address address2 = new Address();
	    worker2.setAddress(address2);
	    worker2.address.setStreetAddress("33 Enfield St");
	    worker2.address.setCity("Enfield");
	    worker2.address.setState("Connecticut");
	    worker2.address.setPostalCode("06082");
	    worker2.address.setCity("Enfield");
	    worker2.setPhoneNum("1-860-745-1132");
	    worker2.setFaxNum("1-860-745-3003");
	    /* set timestamp for birth date */
	    tempCalendar = GregorianCalendar.getInstance();
	    tempCalendar.set(Calendar.DAY_OF_MONTH, 23);
	    tempCalendar.set(Calendar.MONTH, 12);
	    tempCalendar.set(Calendar.YEAR, 1954);
	    tstamp = new Timestamp(tempCalendar.getTimeInMillis());
	    worker2.setBirthDate(tstamp);
	    contacts.add(worker2);

	    personDAO.savePeople(contacts);
	    conn.commit();
	    Reports2DAO skills = new Reports2DAO();
	    StringBuffer skillsReport = skills.getSkillSummary();
	    System.out.println(skillsReport);
	    	    
	}

	public void printContacts() throws Exception {
		
		
		Iterator it=contacts.iterator();
		int personNumber = 0;
        while(it.hasNext())

		{
			personNumber++;
			Person people =(Person)it.next();

			System.out.println(" Person " + personNumber + " = " + people.toString());
		}
	
	}
	
	public static void main(String[] args)  
	{
		
	    /*  This command instantiates a class instance passing the connection object.  */   
	    PersonTestDriver testPerson = new PersonTestDriver();

        try {
        	   
			   /*  call purge method to delete all rows from new summary table   */    
	    	   
	    	   testPerson.setHostname(args[0]);
	    	   testPerson.setPort(args[1]);
	    	   testPerson.setSid(args[2]);
	    	   testPerson.setId(args[3]);
	    	   testPerson.setPwrd(args[4]);
	    	    
	    	   testPerson.testCreate();
	    	   testPerson.printContacts();
	    }
	    catch (Exception ex) {
	    	 System.out.println("Error in testing");
	    	 System.out.println(ex.getMessage());
         	 ex.printStackTrace();
         	 System.exit( 1 );
	    }
	}

	}
