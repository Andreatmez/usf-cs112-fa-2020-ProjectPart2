package ProjectPart2;

public class Passenger {
	
	private boolean survived;
	private double age;
	private double fare;
	
	public Passenger(boolean survived, double age, double fare) {
		this.survived = survived;
		this.age = age;
		this.fare = fare;
	}
	
	public Passenger() {
		this(false, 0, 0);
	}
	
	public boolean getSurvived() {
		return survived;
	}
	
	public void setSurvived(boolean survived) {
		this.survived = survived;
	}
	
	public double getAge() {
		return age;
	}
	
	public void setAge(double age) {
		this.age = age;
	}
	
	public double getFare() {
		return fare;
	}
	
	public void setFare(double fare) {
		this.fare = fare;
	}
	
	public String toString() {
		return "Survived: " + survived + " Age: " + age + " Fare: " + fare;
	}
		

}
