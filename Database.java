package application;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is responsible for the connection with the database and all the queries that we need for our application.
 * 
 * @authors Kristiyan Tomov and Federico Ziviani
 *
 */
public class Database {

	/**
	 * JDBC driver name
	 */
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	
	/*
	 * Database URL
	 */
	static final String DB_URL = "jdbc:mysql://mysql2.gear.host";

	/**
	 *  Database credentials
	 */
	static final String USER = "cinemabookingsys";
	static final String PASS = "Ciaociao!";

	/*
	 *  Connection
	 */
	Connection conn = null;
	
	/**
	 * Statement
	 */
	Statement stmt = null;

	/**
	 * Shortcut query that retrieves all the movies
	 */
	static final String MOVIES_QRY = "SELECT * FROM cinemabookingsys.movies";
	
	/**
	 * Shortcut query that retrieves all the projections
	 */
	static final String PROJECTIONS_QRY = "SELECT * FROM cinemabookingsys.projections";
	
	/**
	 * Shortcut query that retrieves all the bookings
	 */
	static final String BOOKINGS_QRY = "SELECT * FROM cinemabookingsys.bookings";
	
	/**
	 * Shortcut query that retrieves all the users
	 */
	static final String CUSTOMERS_QRY = "SELECT * FROM cinemabookingsys.users";
	
	/**
	 * Shortcut query that is used for acc/pass validations
	 */
	static final String VALIDATION_QRY = "SELECT id,pass,user FROM cinemabookingsys.users";
	
	/**
	 * Shortcut query that adds a movie
	 */
	static final String ADD_MOVIE_QRY = "INSERT INTO cinemabookingsys.movies VALUES ";


	/**
	 * The constructor which initialises the connection with the database
	 */
	Database(){
		try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL,USER,PASS);

		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}

	}
	
	/**
	 * Returns all the projections in the database as well as booked and available seats
	 * 
	 * @return ArrayList
	 */
	public ArrayList<String> export(){
		
		ArrayList<String> projections = new ArrayList<String>(50);
		String sql = "SELECT * FROM cinemabookingsys.projections";
		
		try{

			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				
				String projection;
				
				String title = rs.getString("title");
				String date = rs.getString("date");
				String time = rs.getString("time");
				String seats = countSeats(time,date,title);
				
				projection = "Title: "+title+"| Date: "+date+"| Time: "+time+"| Booked Seats: "+seats+"| Available Seats: "+(12-Integer.parseInt(seats));
				
				projections.add(projection);
					
			}
			rs.close();
			stmt.close();
			conn.close();
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return projections;
		
	}
	
	
	/**
	 * Counts the number of booked seats for a given projection
	 * 
	 * @param time
	 * @param date
	 * @param title
	 * @return String
	 */
	public String countSeats(String time, String date, String title){
			
			String result = "NO CONNECTION :(";
			
			String sql = "SELECT COUNT(*) AS seats FROM `cinemabookingsys`.`bookings` WHERE"+
					"`time` = '"+time+"' AND `date` = '"+date+"' AND `title` = '"+title+"';";
			
			try{

				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()){
					result = rs.getString("seats");
				}
				
				//rs.close();
				//stmt.close();
				//conn.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
			
			return result;
		}
	
	
	/**
	 * Updates the current description of a particular movie
	 * 
	 * @param title
	 * @param newDescription
	 */
	public void updateDescription(String title, String newDescription){
			String sql = "UPDATE `cinemabookingsys`.`movies` SET `description` = '"+newDescription+"' WHERE `title` = '"+title+"';";

			try {

				stmt = conn.createStatement();
				stmt.executeUpdate(sql);
				stmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			} catch(Exception e){
				e.printStackTrace();
			} finally{
				try{
					if(stmt!=null)
						stmt.close();
				} catch(SQLException se2){
					se2.printStackTrace();
				}
			}

		}
	
	
	/**
	 * Updates the current user details. It is used in the Update Profile page.
	 * 
	 * @param user
	 * @param newPass
	 * @param newSurname
	 * @param newFirstname
	 * @param newEmail
	 */
	public void updateUser(String user, String newPass, String newSurname, String newFirstname, String newEmail){
		String sql = "UPDATE `cinemabookingsys`.`users` SET `pass` = '"+newPass+"', `surname` = '"+newSurname+"',"
				+ " `firstname` = '"+newFirstname+"',`email` = '"+newEmail+"' WHERE `id` = '"+user+"';";

		try {

			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			try{
				if(stmt!=null)
					stmt.close();
			} catch(SQLException se2){
				se2.printStackTrace();
			}
		}

	}

	/**
	 * Verifies if a particular set of username/password is valid. It returns respectively 0,1,2 for
	 * invalid password, customer login, employee login
	 * 
	 * @param username
	 * @param password
	 * 
	 * @return 0,1 or 2 
	 */
	public byte isValid(String username, String password){
		
		try{
			//STEP 4: Execute a query

			stmt = conn.createStatement();
			String sql = VALIDATION_QRY;
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				String id = rs.getString("id");
				String pass = rs.getString("pass");
				String user = rs.getString("user");
				if(username.equals(id) && password.equals(pass)) {
					if(user.equals("customer")) return 1;
					if(user.equals("employee")) return 2;

				}
			}
			//STEP 6: Clean-up environment
			rs.close();
			stmt.close();
		} catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		} catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		} finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
		}//end finally
		return 0;
	}

	/**
	 * Adds a movie 
	 * 
	 * @param title
	 * @param description
	 * @param picture
	 */
	public void addMovie(String title, String description, String picture){

		String sql = "INSERT INTO `cinemabookingsys`.`movies` (`title`,`description`,`picture`) " 
				+ "VALUES ('"+title+"','"+description+"','"+picture+"');";


		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * Adds a rating given by user(customer)
	 * 
	 * @param user
	 * @param title
	 * @param rating
	 */
	public void addRating(String user, String title, Double rating){

		String sql = "INSERT INTO cinemabookingsys.feedbacks (user, title, rating) " 
				+ "VALUES ('"+user+"','"+title+"','"+rating+"');";


		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	
	
	/**
	 * Adds the details of a new user
	 * 
	 * @param id
	 * @param pass
	 * @param surname
	 * @param firstname
	 * @param email
	 * @param user
	 */
	
	public void addUser(String id, String pass, String surname, String firstname, String email, String user ){
		String sql = "INSERT INTO `cinemabookingsys`.`users` (`id`,`pass`,`surname`,`firstname`,`email`,`user`) "
				+ "VALUES ('"+id+"','"+pass+"','"+surname+"','"+firstname+"','"+email+"','"+user+"');";


		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	/**
	 * Adds a booking
	 * 
	 * @param id
	 * @param time
	 * @param date
	 * @param title
	 * @param seatNumber
	 */
	public void addBooking(String id, String time, String date, String title, String seatNumber){
		String sql = 
				"INSERT INTO `cinemabookingsys`.`bookings`"
						+" (`id`,`time`,`date`,`title`,`seatnumber`) VALUES ('"
						+id+"','"
						+time+"','"
						+date+"','"
						+title+"','"
						+seatNumber+"');";
		try{
			stmt = conn.createStatement();
			stmt.execute(sql);

			//STEP 6: Clean-up environment
			stmt.close();
		} catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
		}//end finally
	}
	
	/**
	 * Adds a projection
	 * 
	 * @param title
	 * @param date
	 * @param time
	 */
	public void addPj(String title, String date, String time) {
		String sql = "INSERT INTO cinemabookingsys.projections (`title`, `date`, `time`)" + "VALUES('"+title+"','"+date+"', '"+time+"');";
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	/**
	 * Returns all the projections dates for a given title
	 * 
	 * @param title
	 * @return ArrayList
	 */
	public ArrayList<String> getPjDate(String title){
		
		ArrayList<String> dates = new ArrayList<String>();
		
		String sql = "SELECT date FROM cinemabookingsys.projections WHERE title = '"+title+"';";
		
		
		try{
			
			stmt = conn.createStatement(); 
			ResultSet rs = stmt.executeQuery(sql);
		
			while(rs.next()) {
				dates.add(rs.getString("date"));
			}
		
			rs.close();
			stmt.close();
		
			return dates;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return dates;
	}

	/**
	 * Returns the projection times for a given movie and a date
	 * @param date
	 * @param title
	 * @return ArrayList
	 */
	public ArrayList<String> getPjTime(String date, String title){
		
		ArrayList<String> times = new ArrayList<String>();

		String sql = "SELECT time FROM cinemabookingsys.projections WHERE title =  '"+title+"' AND date =  '"+date+"';";
		
		try {
			
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
		
			while(rs.next()) {
				times.add(rs.getString("time"));
			}
			
			rs.close();
			stmt.close();
			
			return times;
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return times;
	}


	/**
	 * Returns a list of all the movies
	 * @return ArrayList
	 */
	public ArrayList<String> getMovies(){
		ArrayList<String> movies = new ArrayList<String>(10);
		String sql = "SELECT title FROM cinemabookingsys.movies";

		try{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()){
				movies.add(rs.getString("title"));
			}

			rs.close();
			stmt.close();
			return movies;
		} catch(SQLException se){
			se.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}
		}
		return movies;

	}

	/**
	 * Returns all the bookings of a particular user(customer)
	 * @param user
	 * @return ArrayList
	 */
	public ArrayList<Booking> getBookings(String user) {
		ArrayList<Booking> bookings = new ArrayList<Booking>(20);
		String sql = "SELECT time,date,title,seatnumber FROM cinemabookingsys.bookings WHERE id = '"+user+"';";

		try{
			stmt = conn.createStatement();	
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()){
				bookings.add(new Booking(rs.getString("title"),rs.getString("date"), rs.getString("time"),rs.getString("seatnumber")) );
			}

			rs.close();
			stmt.close();
			return bookings;
		} catch(SQLException se){
			se.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}
		}
		return bookings;
	}
	
	/**
	 * Returns all projections on a given day
	 * @param date
	 * @return ArrayList
	 */
	public ArrayList<Projection> getProjections(String date) {
		
		ArrayList<Projection> projections = new ArrayList<Projection>(20);
		String sql = "SELECT `projections`.`title`, `projections`.`time`, `movies`.`description`" 
				+ "FROM `cinemabookingsys`.`projections` JOIN `cinemabookingsys`.`movies`" 
				+ "ON `projections`.`title` = `movies`.`title`" 
				+ "WHERE `projections`.`date` = '"+date+"';";

		try{
			
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()){
				projections.add(new Projection(rs.getString("title"),rs.getString("time"), rs.getString("description")));
			}

			rs.close();
			stmt.close();
			return projections;
			
		} catch(SQLException se){
			se.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}
		}
		return projections;
	}
	
	/**
	 * Returns all booked seats of a give projection
	 * @param title
	 * @param date
	 * @param time
	 * @return ArrayList
	 */
	public ArrayList<String> getSeats(String title, String date, String time){

		ArrayList<String> bookedSeats = new ArrayList<String>(12);

		String sql = "SELECT seatnumber FROM cinemabookingsys.bookings"
				+" WHERE bookings.date = '" + date + "' AND bookings.time = '" + time + "' AND bookings.title = '" + title + "';";

		try{
			
			stmt = conn.createStatement();	
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()){
				bookedSeats.add(rs.getString("seatnumber"));
			}

			rs.close();
			stmt.close();
			return bookedSeats;
			
		} catch(SQLException se){
			se.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}
		}
		return bookedSeats;
	}
	
	/**
	 * Returns all the movies that have been rated by at least one user(customer)
	 * @return ArrayList
	 */
	public ArrayList<String> getAllRatedMovies(){

		ArrayList<String> movies = new ArrayList<String>(12);

		String sql = "SELECT DISTINCT title FROM cinemabookingsys.feedbacks;";

		try{
			
			stmt = conn.createStatement();	
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()){
				movies.add(rs.getString("title"));
			}

			rs.close();
			stmt.close();
			return movies;
			
		} catch(SQLException se){
			se.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}
		}
		return movies;
	}
	
	/**
	 * Returns the movies rated by that particular user(customer)
	 * @param user
	 * @return ArrayList
	 */
	public ArrayList<String> getRatedMovies(String user){

		ArrayList<String> userMovies = new ArrayList<String>(12);

		String sql = "SELECT title FROM cinemabookingsys.feedbacks"
				+ " WHERE feedbacks.user = '" + user + "';";

		try{
			
			stmt = conn.createStatement();	
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()){
				userMovies.add(rs.getString("title"));
			}

			rs.close();
			stmt.close();
			return userMovies;
			
		} catch(SQLException se){
			se.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}
		}
		return userMovies;
	}
	
	/**
	 * Returns the average rating of a movie averaged over all the ratings of all users(customers) who have rated the
	 * movie
	 * 
	 * @param title
	 * @return Double
	 */
	public Double getMovieRating(String title){

		Double rating = null;
		Double sum = null;
		Double count = null;
		
		String sql = "SELECT SUM(rating) AS sum, COUNT(*) AS count "
				+ "FROM cinemabookingsys.feedbacks "
				+ "WHERE title = '"+title+"';";

		try{
			
			stmt = conn.createStatement();	
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()){
				
				sum = Double.parseDouble(rs.getString("sum"));
				
				count = Double.parseDouble(rs.getString("count"));
				
			}
			
			rating = sum/count;
			
			rs.close();
			stmt.close();
			return rating;
			
		} catch(SQLException se){
			se.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}
		}
		return rating;
	}	
	
	/**
	 * Returns user's details placed in a map for easy access using the keys pass, surname, firstname, email
	 * @param id
	 * @return Map
	 */
	public Map<String, String> getUserDetails(String id){

		Map<String, String> details = new HashMap<String, String>(5);

		String sql = "SELECT * FROM cinemabookingsys.users WHERE id = '"+id+"';";

		try{
			
			stmt = conn.createStatement();	
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()){
				details.put("pass", rs.getString("pass"));
				details.put("surname", rs.getString("surname"));
				details.put("firstname", rs.getString("firstname"));
				details.put("email", rs.getString("email"));
			}

			rs.close();
			stmt.close();
			return details;
			
		} catch(SQLException se){
			se.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}
		}
		return details;
	}
	
	/**
	 * Deletes a booking
	 * @param id
	 * @param time
	 * @param date
	 * @param seatnumber
	 */
	public void deleteBooking(String id, String time, String date, String seatnumber){
		String sql = "DELETE FROM `cinemabookingsys`.`bookings` "
				+ "WHERE `id` = '"+id+"' AND `time` = '"+time+"' AND `date` = '"+date+"' AND `seatnumber` = '"+seatnumber+"';";

		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

	}
	/**
	 * Returns the description of a given movie
	 * @param title
	 * @return String
	 */ 
	public String getDescription(String title){
		
		String result = "";
		String sql = "SELECT * FROM cinemabookingsys.movies WHERE title = '"+title+"' ";
		
		try{
			
			stmt = conn.createStatement(); 
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
		
			result = rs.getString("description");
		
			stmt.close();
			return result;
			
		}catch(SQLException se){
			se.printStackTrace();
		}
		
		return result;

	}
	

	

	public String getPicture(String title) throws SQLException {
		
		String result = "";
		String sql = "SELECT * FROM cinemabookingsys.movies WHERE title = '"+title+"';";
		
		try{
			
			stmt = conn.createStatement(); 
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
		
			result = rs.getString("picture");
		
			stmt.close();
			return result;
			
		}catch(SQLException se){
			se.printStackTrace();
		}
		
		return result;
	}

	/**
	 * Does cleanup: it closes the connection
	 */
	protected void finalize() throws Throwable
	{
		try{
			if(conn!=null){
				conn.close();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}