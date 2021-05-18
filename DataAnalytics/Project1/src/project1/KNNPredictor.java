package project1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class KNNPredictor extends Predictor{

	private int K;
	private int survived = 0;
	private int perished = 0;
	
	ArrayList<DataPoint> passengers = new ArrayList<DataPoint>();
	
	public KNNPredictor(int k) {
		this.K = k;
	}
	
	@Override
	ArrayList<DataPoint> readData(String filename) {
		String label;
		Double age;
		Double fare;
		Boolean isTest;
		
		try (Scanner scanner = new Scanner(new File("titanic.csv"));) {
			scanner.nextLine(); // skips first line of labels
			while (scanner.hasNextLine()) {
				List<String> records = getRecordFromLine(scanner.nextLine());
				// Select the columns from the records and create a DataPoint object
				try {
					label = records.get(1);
					age = Double.parseDouble(records.get(5));
					fare = Double.parseDouble(records.get(6));
					isTest = splitTest();
					// Checks to see if it is train data, then adds to the count of survived/perished respectively
					if(!isTest) {
						if(Integer.parseInt(label) == 1) {
							survived++;
						} else if(Integer.parseInt(label) == 0) {
							perished++;
						}
					}
					// creates the DataPoint and adds it to the ArrayList
					DataPoint p = new DataPoint(age, fare, label, isTest);
					// Store the DataPoint object in a collection
					passengers.add(p);
				} catch (NumberFormatException | IndexOutOfBoundsException e) {
					
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		}
		System.out.println("Survived: " + survived);
		System.out.println("Perished: " + perished);
	// Returns the array containing all of the passenger data
		return passengers;
	}

	@Override
	String test(DataPoint data) {
		Double[][] distanceArr = new Double[survived + perished][2];
		String returnVal = "";
		int r = 0;
		
		// Checks if the input DataPoint is a test, 
		// then creates an array with the distance from each of the training DataPoints and their label.
		if (data.getIsTest() == true) {
			for (int i = 0; i < passengers.size(); i++) {
				if (passengers.get(i).getIsTest() == false) {
					Double distance = getDistance(data, passengers.get(i));
					Double label = Double.parseDouble(passengers.get(i).getLabel());
					
					distanceArr[r][0] = distance;
					distanceArr[r][1] = label;
					r++;
				}
			}
			// Sorts the array smallest distance to largest
			Arrays.sort(distanceArr, Comparator.comparingDouble(o -> o[0]));
			
			int survivorCt = 0;
			int perishedCt = 0;
			
			// Takes the first "K" elements of the distance array and counts how many survived or perished
			for (int i=0; i<K; i++) {
				if (distanceArr[i][1] == 1.0) {
					survivorCt++;
				} else {
					perishedCt++;
				}
			}
			
			// Checks if more survivors or perished were closer to the input DataPoint and returns the matching label
			if (survivorCt > perishedCt) {
				returnVal = "1";
			} else {
				returnVal = "0";
			}
		}
		return returnVal;
	}

	// Variables for getAccuracy and getPrecision
	double truePositive = 0;
	double falsePositive = 0;
	double falseNegative = 0;
	double trueNegative = 0;
	
	@Override
	Double getAccuracy(ArrayList<DataPoint> data) {
		Double testLabel;
		
		for (int i=0; i < data.size(); i++) {
			if (data.get(i).getIsTest() == true) {
				testLabel = Double.parseDouble(test(data.get(i)));
				if (testLabel == 1.0) {
					if (data.get(i).getLabel().equals("1")) {
						truePositive++;
					} else if (data.get(i).getLabel().equals("0")) {
						falsePositive++;
					}
				} else if (testLabel == 0.0) {
					if (data.get(i).getLabel().equals("1")) {
						falseNegative++;
					} else if (data.get(i).getLabel().equals("0")) {
						trueNegative++;
					}
				}
				
			}
		}

		return (double) ((truePositive + trueNegative) / (truePositive + trueNegative + falsePositive + falseNegative)*100);
	}

	@Override
	Double getPrecision(ArrayList<DataPoint> data) {
		return (double) (truePositive / (truePositive + falseNegative)*100);
	}
	
	// Helper function to split the line by commas and 
	// return the values as a List of Strings
	private List<String> getRecordFromLine(String line) {
		List<String> values = new ArrayList<String>();
		try (Scanner rowScanner = new Scanner(line)) {
			rowScanner.useDelimiter(",");
			while (rowScanner.hasNext()) {
				values.add(rowScanner.next());
			}
		}
		return values;
	}
	
	private boolean splitTest() {
		Random rand = new Random();
		double randNum = rand.nextDouble();
		
		if (randNum < 0.9) {
			return false;
		} else {
			return true;
		}
	}
	
	private Double getDistance(DataPoint p1, DataPoint p2) {
		double x1 = p1.getF1();
		double y1 = p1.getF2();
		
		double x2 = p2.getF1();
		double y2 = p2.getF2();
		
		// Computes distance between the two inputed points
		double distance = Math.sqrt(Math.pow((x1-x2), 2) + Math.pow((y1-y2), 2));
		
		return distance;
	}
}
