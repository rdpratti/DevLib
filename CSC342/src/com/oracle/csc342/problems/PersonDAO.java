package com.oracle.csc342.problems;


import java.math.BigDecimal;
import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class PersonDAO {
	
    public void createPerson(Person person) throws SQLException
    {
        Connection con = null;
        PreparedStatement ps= null;
        
        System.out.println("Person to be Inserted \n");
        System.out.println(person.toString());
        
		try
        {
            
        	con = DBConnect.getConnection();
            ps=con.prepareStatement("insert into CSC342.person (Person_id, First_name, Last_name, Street_address, city, state, postal_code," +
            		"Birth_date, Phone, Fax) values(?,?,?,?,?,?,?,?,?,?)");
            ps.setBigDecimal(1, person.getPersonId());
            ps.setString(2,person.getFirstName());
            ps.setString(3,person.getLastName());
            ps.setString(4,person.getAddress().getStreetAddress());
            ps.setString(5,person.getAddress().getCity());
            ps.setString(6,person.getAddress().getState());
            ps.setString(7,person.getAddress().getPostalCode());
            ps.setTimestamp(8,person.getBirthDate());
            ps.setString(9,person.getPhoneNum());
            ps.setString(10,person.getFaxNum());
            
            ps.executeUpdate();
            System.out.println("Addition Success");
        
        }
        catch(SQLException e)
        {
            System.out.println("Error in Create Person" + e.getSQLState());
            System.out.println("/nError Code: " + e.getErrorCode());
            System.out.println("/nMessage: " + e.getMessage());

            System.exit( 1 );
        }
        catch(Exception e)
        {
            System.out.println("unknown Error in Create Person");
            System.out.println("/nMessage: " + e.getMessage());
            System.exit( 1 );
        }
		finally
		{
			if (con != null)
				System.out.println("closing Person create statement \n");
			    ps.close();
				
		}
        
    }

public BigDecimal findMaxPersonId() throws SQLException
{

    
    
    BigDecimal maxPersonId = new BigDecimal(0);
    
    Connection con = null;
    PreparedStatement ps= null;
    ResultSet rs=null;
    
    try
    {
    
    	con = DBConnect.getConnection();
    	ps=con.prepareStatement("select max(p.person_id) from CSC342.Person p");
        
        rs=ps.executeQuery();
        while(rs.next())
        {
            maxPersonId = rs.getBigDecimal(1);
            System.out.println("Get Max Person Id Success ");
                
        }
    }
    catch(SQLException e)
    {
        System.out.println("Error in get max person access" + e.getSQLState());
        System.out.println("/nError Code: " + e.getErrorCode());
        System.out.println("/nMessage: " + e.getMessage());
        System.exit( 1 );
    }
    catch(Exception e)
    {
        System.out.println("unknown Error in get max person");
        System.out.println("/nMessage: " + e.getMessage());
        System.exit( 1 );
    }
    finally
	{
		if (con != null)
			System.out.println("closing Person connection \n");
			rs.close();
			ps.close();
	}
    return maxPersonId;
}

public Person viewPerson(BigDecimal personId) throws SQLException
    {

    
        ResultSet rs=null;
        Person outPerson = new Person();
        Address outAddress = new Address();
        Connection con = null;
        PreparedStatement ps=null;
        
        try
        {
    
        	con = DBConnect.getConnection();
        	ps=con.prepareStatement("select p.person_id, p.first_name, p.last_name, p.street_address, " +
            		"p.city, p.state, p.postal_code, p.birth_date, p.phone, p.fax " +
            		"from CSC342.person p" +
            		"where p.person_id =?");
            ps.setBigDecimal(1,personId);

            rs=ps.executeQuery();
            while(rs.next())
            {
                outPerson.setPersonId(rs.getBigDecimal(1));
                outPerson.setFirstName(rs.getString(2));
                outPerson.setLastName(rs.getString(3));
                outAddress.setStreetAddress(rs.getString(4));
                outAddress.setCity(rs.getString(5));
                outAddress.setState(rs.getString(6));
                outAddress.setPostalCode(rs.getString(7));
                outPerson.setAddress(outAddress);
                outPerson.setBirthDate(rs.getTimestamp(8));
                outPerson.setPhoneNum(rs.getString(9));
                outPerson.setFaxNum(rs.getString(10));
                
                /* don't need to set parent, must be done when you instantiate the ee class (must setup past classes correctly. */
                
                System.out.println("View Person Success");
            }
        }
        catch(SQLException e)
        {
            System.out.println("Error in Person view access" + e.getSQLState());
            System.out.println("/nError Code: " + e.getErrorCode());
            System.out.println("/nMessage: " + e.getMessage());
            System.exit( 1 );
        }
        catch(Exception e)
        {
            System.out.println("unknown Error in Person view access");
            System.out.println("/nMessage: " + e.getMessage());
            System.exit( 1 );
        }
        finally
    	{
    		if (con != null)
    			System.out.println("closing Person connection \n");
				rs.close();
				ps.close();	
    	}
        return outPerson;
    }
	public void updatePerson(Person empl) throws SQLException
    {
    
		System.out.println("Person to be Updated \n");
		System.out.println(empl.toString());
		Connection con = null;
    	PreparedStatement ps= null;
    	
        try
        {

        	con = DBConnect.getConnection();
            
        	ps=con.prepareStatement("update CSC342.person set p.first_name = ?, p.last_name = ?, " +
            		"p.street_address = ?, p.city = ?, p.state = ?, p.postal_code = ?, p.birth_date = ?, p.phone = ?, " +
            		"p.fax = ? where person_id = ?");
           
            ps.setString(1,empl.getFirstName());
            ps.setString(2,empl.getLastName());
            ps.setString(3,empl.getAddress().getStreetAddress());
            ps.setString(4,empl.getAddress().getCity());
            ps.setString(5,empl.getAddress().getState());
            ps.setString(6,empl.getAddress().getPostalCode());
            ps.setTimestamp(7,empl.getBirthDate());
            ps.setString(8,empl.getPhoneNum());
            ps.setString(9,empl.getFaxNum());
            ps.setBigDecimal(10,empl.getPersonId());
            ps.executeQuery();
            System.out.println("updated");
        }
        catch(SQLException e)
	        {
	            System.out.println("Error in Person Update" + e.getSQLState());
	            System.out.println("/nError Code: " + e.getErrorCode());
	            System.out.println("/nMessage: " + e.getMessage());
	            System.exit( 1 );
	        }
	    catch(Exception e)
	        {
	            System.out.println("unknown Error in Person Update");
	            System.out.println("/nMessage: " + e.getMessage());
	            System.exit( 1 );
        }
        finally
    	{
    		if (con != null)
    			System.out.println("closing Person connection \n");
				ps.close();
    	}
    }
	public void deletePerson(BigDecimal personId) throws SQLException
    {
    
		System.out.println("Person to be Deleted \n");
		System.out.println("Person Id = " + personId + "\n");
		
		
		Connection con = null;
    	PreparedStatement ps=null;
    	
        try
        {
    
        	con = DBConnect.getConnection();
            ps=con.prepareStatement("delete CSC342.person where person_id=?");
            ps.setBigDecimal(1,personId);
            ps.executeQuery();
            System.out.println("deleted");
        }
        catch(SQLException e)
        {
            System.out.println("Error in Person Delete" + e.getSQLState());
            System.out.println("/nError Code: " + e.getErrorCode());
            System.out.println("/nMessage: " + e.getMessage());
            System.exit( 1 );
        }
        catch(Exception e)
        {
            System.out.println("unknown Error in Person delete");
            System.out.println("/nMessage: " + e.getMessage());
            System.exit( 1 );
        }
        finally
    	{
    		if (con != null)
    			System.out.println("closing Person connection \n");
				ps.close();
				
    	}
    }

    	public void countPeople() throws SQLException
        {
        
        	Connection con = null;
        	PreparedStatement ps= null;
        	ResultSet rs = null;
        	String sql1 = "Select count(*) from CSC342.Person p inner join CSC342.customer c " + 
        				  " on (p.person_id = c.customer_id)";
        	
            try
            {
        
            	con = DBConnect.getConnection();
                ps=con.prepareStatement(sql1);
                int personCt = 0;
                
                rs = ps.executeQuery();
                while(rs.next())
                {
                    personCt = rs.getInt(1);
                }    
                System.out.println("countPeople success " + personCt);
            }
            catch(SQLException e)
            {
                System.out.println("Error in countPeople" + e.getSQLState());
                System.out.println("/nError Code: " + e.getErrorCode());
                System.out.println("/nMessage: " + e.getMessage());
                System.exit( 1 );
            }
            catch(Exception e)
            {
                System.out.println("unknown Error in countPeople");
                System.out.println("/nMessage: " + e.getMessage());
                System.exit( 1 );
            }
            finally
        	{
        		if (con != null)
        			System.out.println("closing count objects \n");
        		    rs.close();
        		    ps.close();
    				    				
        	}
        
    }

    	public void savePeople(List<Person> people) throws SQLException
        {
        
        	Connection con = null;
        	
        	String sql1 = "Select count(*) as person_count from CSC342.Person p WHERE p.person_id = ?";
        	PreparedStatement ps = null;
        	ResultSet rs = null;

        	try
            {
    
        	    con = DBConnect.getConnection();
                ps=con.prepareStatement(sql1);

        	    for (Iterator<Person> it = people.iterator(); it.hasNext();)
        	      {
        		      Person testPerson = it.next();
                      ps.setBigDecimal(1,testPerson.getPersonId());  
                      rs = ps.executeQuery();
                      while(rs.next())
                      {
                        if (rs.getInt(1) == 1)
                        	updatePerson(testPerson);
                        else
                        if (rs.getInt(1) == 0)
                        	createPerson(testPerson);
                        else
                        	throw new RuntimeException("More than one person has Person Id");
                      }
        	      }    
                
             }
             catch(SQLException e)
              {
                 System.out.println("Error in savePeople" + e.getSQLState());
                 System.out.println("/nError Code: " + e.getErrorCode());
                 System.out.println("/nMessage: " + e.getMessage());
                 System.exit( 1 );
              }
             catch(Exception e)
              {
                 System.out.println("unknown Error in savePeople");
                 System.out.println("/nMessage: " + e.getMessage());
                 System.exit( 1 );
              }
             finally
        	  {
                  rs.close();
            	  ps.close();
        	  }
        
        	}
}

	

