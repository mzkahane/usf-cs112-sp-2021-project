package project1;

import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class DummyPredictor extends Predictor {

	Double greenAvg = 0.0;
	Double blueAvg = 0.0;
	
	@Override
	ArrayList<DataPoint> readData(String filename) {
		return null;
	}

	@Override
	String test(DataPoint data) {
		double fDiff = Math.abs(data.getF1() - data.getF2());
		
		if (Math.abs(fDiff - greenAvg) < Math.abs(fDiff - blueAvg)) {
			return "Green";
		} else {
			return "Blue";
		}
	}

	@Override
	Double getAccuracy(ArrayList<DataPoint> data) {
		return 4.6;
	}

	@Override
	Double getPrecision(ArrayList<DataPoint> data) {
		return 3.2;
	}

}
