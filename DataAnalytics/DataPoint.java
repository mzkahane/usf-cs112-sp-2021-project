package project1;

public class DataPoint {
	private Double f1;
	private Double f2;
	private String label;
	private Boolean isTest;
	
	// Constructors
	public DataPoint(Double f1, Double f2, String label, Boolean isTest) {
		this.f1 = f1;
		this.f2 = f2;
		setLabel(label);
		this.isTest = isTest;
	}
	
	public DataPoint() {
		this.f1 = 0.0;
		this.f2 = 0.0;
		setLabel("Blue");
		this.isTest = true;
	}
	
	// Accessors
	public Double getF1() {
		return this.f1;
	}
	
	public Double getF2() {
		return this.f2;
	}
	
	public String getLabel() {
		return this.label;
	}
	
	public Boolean getIsTest() {
		return this.isTest;
	}
	
	// Mutators
	public void setF1(Double f) {
		if (f < 0) {
			return;
		}
		this.f1 = f;
	}
	
	public void setF2(Double f) {
		if (f < 0) {
			return;
		}
		this.f2 = f;
	}
	
	public void setLabel(String label) {
		if (!(label.equals("Green") || label.equals("Blue"))) {
			return;
		}
		this.label = label;
	}
	
	public void setIsTest(Boolean isTest) {
		this.isTest = isTest;
	}
}
