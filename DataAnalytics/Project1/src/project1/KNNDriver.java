package project1;

import java.util.Scanner;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.Container;
import java.text.DecimalFormat;

public class KNNDriver {

	public static void main(String[] args) {
		// Extra Credit: Takes in user input for the K value
		System.out.println("Enter an odd integer value for K: ");
		Scanner sc = new Scanner(System.in);
		
		int userK = sc.nextInt();
		
		// Sets up the predictor and reads the data into an array
		KNNPredictor predictor = new KNNPredictor(userK);
		
		ArrayList<DataPoint> testArray = new ArrayList<DataPoint>();
		testArray = predictor.readData("titanic.csv");
		// gets the precision and accuracy and assigns them to respective variables
		double accuracy = predictor.getAccuracy(testArray);
		double precision = predictor.getPrecision(testArray);
		
		// Sets up the JFrame to display accuracy and precision
		JFrame myFrame = new JFrame();
		myFrame.setTitle("KNN Predictor");
		
		Container contentPane = myFrame.getContentPane();
		contentPane.setLayout(new GridLayout(1, 2));
		
		DecimalFormat df = new DecimalFormat("##.###");
		
		JButton precisionLabel = new JButton("Precision: " + df.format(precision) + "%");
		JButton accuracyLabel = new JButton("Accuracy: " + df.format(accuracy) + "%");
		
		contentPane.add(precisionLabel);
		contentPane.add(accuracyLabel);
		
		myFrame.pack();
		myFrame.setVisible(true);
	}

}
