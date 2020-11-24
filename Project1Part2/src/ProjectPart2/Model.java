package ProjectPart2;

import java.util.ArrayList;

public abstract class Model {
	
	abstract void train(ArrayList<Passenger> data);
	abstract String test(ArrayList<Passenger> data);
	abstract Double getAccuracy(ArrayList<Passenger> data);
	abstract Double getPrecision(ArrayList<Passenger> data);

}

