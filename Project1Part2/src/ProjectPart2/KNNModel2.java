package ProjectPart2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;


public class KNNModel2 extends Model {

	private ArrayList<Passenger> trainSet;
	private ArrayList<Passenger> testSet;
	private ArrayList<Boolean> category;
	
	public int k;
	
	private int  valueT= 0;
	private int  valueF = 0;

	private double getDistance(Passenger p1, Passenger p2) {
		double result = Math.sqrt(Math.pow((p1.getAge()-p2.getAge()), 2)+
				Math.pow((p1.getFare()-p2.getFare()),2)); 
		return result;
	}
	
		
	public KNNModel2(int k) {
		this.k = k;
		trainSet = new ArrayList<Passenger>();
		testSet = new ArrayList<Passenger>();
		category = new ArrayList<Boolean>();

	}
	

	void train(ArrayList<Passenger> data) {
		
		for(Passenger passenger : data) {
			
			if(passenger.getSurvived()) {
				valueT++;
			}else {
				valueF++;
			}			
		}
		trainSet.addAll(data);
	}
	

	String test(ArrayList<Passenger> data) {
		
		
		testSet = new ArrayList<Passenger>();
		category = new ArrayList<Boolean>();
		testSet.addAll(data);

		Double[][] array = new Double[data.size()][2];;


		for(int i =0; i < data.size(); i++) {
			for(int j=0; j < trainSet.size(); j++) {
				double distanceTrainTest = getDistance(data.get(i), trainSet.get(j));
				array[i][0] = distanceTrainTest;			
				array[i][1] = trainSet.get(j).getSurvived() ? 1.0 : 0.0;
			}
		}
		
		Arrays.sort(array, new Comparator<Double[]>() {
			public int compare(Double[] a, Double[] b) {
				return a[0].compareTo(b[0]);
			}
		});
		
		int survived = 0;
		int notSurvived = 0;
		
		for(int i = 0; i < k; i++) {		
			if(array[i][1] == 1) {
				survived++;
			}else {
				notSurvived++;
			}	
			
			if(survived < notSurvived) {
				category.add(true);
			} 		
			category.add(false);
		}	

		System.out.println(category.size() + "  " + testSet.size());

		
		return "";
	}

	Double getAccuracy(ArrayList<Passenger> data) {
		
		
		double truePositive = 0; 
		double trueNegative = 0;
		double falsePositive = 0;
		double falseNegative = 0;
		
		test(data);
		for(int i = 0; i < category.size(); i++) {
			if(testSet.get(i).getSurvived() == true) {
				if(testSet.get(i).getSurvived() == category.get(i)) {
					truePositive++;
				} else {
					falsePositive++;
				}
			}
			if(testSet.get(i).getSurvived() == false) {
				if(testSet.get(i).getSurvived() == category.get(i)) {
					trueNegative++;
				} else {
					falseNegative++;
				}
			}
		}

		return (truePositive + trueNegative) / (truePositive + trueNegative + falsePositive + falseNegative);
	}

	
	Double getPrecision(ArrayList<Passenger> data) {
		double truePositive = 0; 
		double trueNegative = 0;
		double falsePositive = 0;
		double falseNegative = 0;
		
		test(data);
		for(int i = 0; i < category.size(); i++) {
			if(testSet.get(i).getSurvived() == true) {
				if(testSet.get(i).getSurvived() == category.get(i)) {
					truePositive++;
				} else {
					falsePositive++;
				}
			}
			if(testSet.get(i).getSurvived() == false) {
				if(testSet.get(i).getSurvived() == category.get(i)) {
					trueNegative++;
				} else {
					falseNegative++;
				}
			}
		}
		
		return (truePositive)/(truePositive + falseNegative);
	}
	
	
	
}
