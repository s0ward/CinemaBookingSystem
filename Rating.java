package application;

/** 
 * This class is used to populate the See Ratings TableView
 * in the employee profile.
 *
 * @author Kristiyan Tomov
 */

public class Rating {
	
	/**
	 * The title of the movie
	 */
	private String title;
	
	/**
	 * The rating of the movie
	 */
	private double rating;
	
	/**
	 * Constructor that initialises the object
	 * @param title
	 * @param rating
	 */
	Rating(String title, Double rating){
		this.title = title;
		this.rating = rating;
	}
	
	/**
	 * Set method for the title
	 * @param title
	 */
	public void setTitle(String title){
		this.title = title;
	}
	
	/**
	 * Get method for the title
	 * @return String
	 */
	public String getTitle(){
		return this.title;
	}
	
	/**
	 * Set method for the rating
	 * @param rating
	 */
	public void setRating(Double rating){
		this.rating = rating;
	}
	
	/**
	 * Get method for the rating
	 * @return Double
	 */
	public Double getRating(){
		return this.rating;
	}
}
