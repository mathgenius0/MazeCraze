package mazeCraze;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestGrid {
	
	@Test
	public void testToString() {
		Grid grid = new Grid(2, 2);
		
		grid.toggleBlock(1, 0);
		grid.toggleBlock(0, 1);
		
		String shouldPrint = "X .\n. X\n";
		assertEquals( shouldPrint, grid.toString() );
	}

}
