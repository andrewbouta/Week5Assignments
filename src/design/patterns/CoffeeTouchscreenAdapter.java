package design.patterns;

public class CoffeeTouchscreenAdapter implements CoffeeMachineInterface {
	OldCoffeeMachine originalM;

	// Initalize Constructor
	public CoffeeTouchscreenAdapter(OldCoffeeMachine newMachine) {
		originalM = newMachine;
	}

	// Calls method from the original machine (selection A)
	public void chooseFirstSelection() {
		originalM.selectA();
	}

	// Calls method from the original machine (selection B)
	public void chooseSecondSelection() {
		originalM.selectB();
	}
}
