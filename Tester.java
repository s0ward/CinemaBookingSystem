package application;

import java.sql.SQLException;

/**
 * This class is used for debugging purposes. Predominantly, it helped test queries.
 * @author Kristiyan Tomov
 *
 */

public class Tester {
	public static void main(String[] args) throws SQLException{
		
		Database data = new Database();
		//System.out.println(data.isValid("user","pass"));
		//data.addUser("monty1", "hall1", "Hall1", "Monty1", "montyhall@gmail.com", "1customer");
		//System.out.println(data.getMovies());
		//data.updateUser("monty", "test", "Test", "Test", "test@gmail.com");
		//System.out.print(data.getBookings("user"));
		//data.deleteBooking("user", "11pm", "06/20/2017");
		//System.out.println(data.getSeats("The Matrix", "01/12/2017", "11pm"));
		//data.addBooking("user", "16pm", "2017-12-01", "The Matrix", "C3");
		//Map<String, String> map = data.getUserDetails("user");
		//System.out.print("pass: " + map.get("pass") + " surname: " + map.get("surname") + " firstname" + map.get("firstname") + " email" + map.get("email"));
		//data.addPj("Title", "Date", "Time");
		
		//System.out.print(data.countSeats("10am", "2017-12-01", "Hanibal"));
		//for (String s: data.export()){
		//	System.out.println(s);
		//data.addMovie("TEST1", "TEST", "C:/Users/User/2017-11-20.png");
		//CMyBookingsController CMBCtll = new CMyBookingsController();
		//System.out.println(data.getRatedMovies("user"));
		//data.addRating("user", "The Matrix", 5.0);
		//System.out.println(data.getMovieRating("The Matrix"));
		//System.out.print(data.getMovieRating("The Dictator"));
	}
}
