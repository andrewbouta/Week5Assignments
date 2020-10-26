package design.patterns;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AdapterPatternTest {

	
	@Test
	void selectionAssertEqualsMethod() {
		OldCoffeeMachine originalM = new OldCoffeeMachine();
		CoffeeTouchscreenAdapter newAdapter = new CoffeeTouchscreenAdapter(originalM);
		
		// Currently broken
		assertEquals("You have selected: A", newAdapter.chooseFirstSelection());
		assertEquals("You have selected: B", newAdapter.chooseSecondSelection());
	}

}
