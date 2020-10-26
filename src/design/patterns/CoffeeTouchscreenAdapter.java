package design.patterns;

public class CoffeeTouchscreenAdapter implements CoffeeMachineInterface {
	OldCoffeeMachine originalM;

	public CoffeeTouchscreenAdapter(OldCoffeeMachine newMachine) {
		originalM = newMachine;
	}
	
	public void chooseFirstSelection() {
		originalM.selectA();
	}
	
	public void chooseSecondSelection() {
		originalM.selectB();
}
}
