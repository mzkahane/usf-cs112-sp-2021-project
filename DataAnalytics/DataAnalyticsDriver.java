package project1;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

public class DataAnalyticsDriver {
	
	public static void main(String[] args) {
		DummyPredictor trainData = new DummyPredictor();
		ArrayList<DataPoint> trainDataArray = new ArrayList<>();
		trainDataArray = trainData.readData("trainData.txt");
		
		DataPoint nullPoint = new DataPoint();
		
		System.out.println("Test: " + trainData.test(nullPoint));
		
		System.out.println(trainData.getAccuracy(trainDataArray));
		System.out.println(trainData.getPrecision(trainDataArray));
		
		
		// Sets up and shows JFrame.
		JFrame myFrame = new JFrame();
		myFrame.setTitle("Precision & Accuracy");
		
		Container contentPane = myFrame.getContentPane();
		contentPane.setLayout( new GridLayout(1,2));
		
		JButton precisionLabel = new JButton("Accuracy: " + trainData.getAccuracy(trainDataArray).toString());
		JButton accuracyLabel = new JButton("Precision: " + trainData.getPrecision(trainDataArray).toString());
		
		contentPane.add(precisionLabel);
		contentPane.add(accuracyLabel);
		
		myFrame.pack();
		myFrame.setVisible(true);
	}

}
