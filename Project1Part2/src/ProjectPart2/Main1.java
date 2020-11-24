package ProjectPart2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main1 {

	public static void main(String[] args) {

		List<Passenger> passengers = readPassengersFromCSV("titanic.csv");

		ArrayList<Passenger> train = new ArrayList<Passenger>(); // CREATES TRAIN PASSENGER
		ArrayList<Passenger> test = new ArrayList<Passenger>(); // CREATES TEST PASSENGER

		Random random = new Random(); // RANDOM GENERATOR


		for(Passenger p : passengers) {

			double randomNumber = random.nextDouble(); // CREATES RANDOM NUMBER FOR RANDOMRUMBER VARIABLE

			if(randomNumber < .9) { // IF THE RANDOM NUMBER IS LESS THAT .9
				train.add(p); // IT ADDS THE PASSENGER TO TRAIN PASSENGER

			} else { // IF THE RANDOM NUMBER IS GRETER THAN .9 
				test.add(p); // IT ADDS THE PASSENGER TO TEST PASSENGER
			}

		}

		KNNModel knnmodel = new KNNModel(7);

		knnmodel.train(train);
		knnmodel.test(test);

		double AccuracyData = knnmodel.getAccuracy(test); 
		double PrecisionData = knnmodel.getPrecision(test);

		String Accuracy = String.format("%8s : %2.0f", "Accuracy % ", AccuracyData * 100); 
		String Precision = String.format("%9s : %2.0f", "Precision % ", PrecisionData * 100);


		JFrame MyFrame = new JFrame("Accuracy & Precision");  // TITLE FOR DATA DISPLAY
		MyFrame.setVisible(true); // MAKES IT VISIBLE TO SCREEN

		JPanel panel = new JPanel();  
		MyFrame.add(panel); // ADDS PANEL 
		MyFrame.setSize(200, 200); // SETS SIZE

		JLabel label1 = new JLabel(Accuracy); 
		panel.add(label1);   
		JLabel label2 = new JLabel(Precision);
		panel.add(label2); 




	}


	// SCAN CSV FILE
	private static List<Passenger> readPassengersFromCSV(String fileName){
		List<Passenger> passengers = new ArrayList<>();
		Path pathToFile = Paths.get(fileName);

		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName)); 

			String line = br.readLine();
			line = br.readLine();
			while(line != null) {
				String[] attributes = line.split(",");

				Passenger passenger = createPassenger(attributes);

				passengers.add(passenger);

				line = br.readLine();
			}

		}catch(FileNotFoundException e) {
			System.out.println("File not found");
		}

		catch(IOException ioe) {
			System.out.println("Unable to read file");
			ioe.printStackTrace();
		}
		return passengers;
	}

	// ADD DATA TO PASSENGER 
	private static Passenger createPassenger(String[] data) {


		boolean survived = data[1].equals("1");

		double age;
		if(data.length>5 && data[5].length()>0) {
			age = Double.parseDouble(data[5]);
		} else { age = 0.0; }

		double fare;
		if(data.length>6) {
			fare = Double.parseDouble(data[6]);
		} else { fare = 0.0; }

		return new Passenger(survived, age, fare);
	}


	

}
