import static org.junit.Assert.*;
import org.junit.Test;

public class LCA_DAGTest {

	
	@Test 
	public void testV()
	{
		LCA_DAG test = new LCA_DAG(2);
		assertEquals(test.V(), 2);
	}
	
	@Test
	public void testE(){
		
		LCA_DAG test = new LCA_DAG(5);
		
		test.addEdge(1, 2);
		test.addEdge(2, 4);
		test.addEdge(3, 2);
		test.addEdge(4, 4);
		
		assertEquals(test.E(), 4);
	}
	
	@Test
	public void testDAG()
	{
		LCA_DAG test = new LCA_DAG(10);
		
		test.addEdge(1, 2);
		test.addEdge(2, 3);
		test.addEdge(2, 4);
		test.addEdge(3, 5);
		test.addEdge(3, 6);
		
		assertEquals(test.degreeMain(2), 1);
		assertEquals(test.outdegree(3), 2);
		assertEquals(test.E(), 5);
		assertEquals(test.V(), 10);
		String adjacent = "[5, 6]";
		assertEquals(test.adjacent(3).toString(), adjacent);
	}

	@Test
	public void addEdge()
	{
		LCA_DAG test = new LCA_DAG(5);
		
		test.addEdge(1,2);
		test.addEdge(-1, -2); 	
		test.addEdge(3, 12);	
		assertEquals(test.E(), 1);
	}
	
	@Test
	public void testAdjacent()
	{
		LCA_DAG test = new LCA_DAG(5);
		
		test.addEdge(1, 2);
		test.addEdge(2, 4);
		test.addEdge(3, 2);
		test.addEdge(4, 4);
		
		String adjacent = "[4]";
		assertEquals(test.adjacent(2).toString(), adjacent);
	}
	
	@Test
	public void testCycle()
	{
		LCA_DAG test = new LCA_DAG(5);
		
		test.addEdge(0, 1);
		test.addEdge(1, 2);
		test.addEdge(2, 0);
		
		assertTrue(test.cycle());		
		
		LCA_DAG test2 = new LCA_DAG(5);
		
		test2.addEdge(0, 1);
		test2.addEdge(1, 2);
		test2.addEdge(2, 3);
		
	}
	
	@Test
	public void testIndegree()
	{
		LCA_DAG test = new LCA_DAG(5);
		
		test.addEdge(1, 2);
		test.addEdge(2, 4);
		test.addEdge(4, 4);
		
		assertEquals(test.degreeMain(4), 2);		
		assertEquals(test.degreeMain(44), -1);	
	}
	
	@Test
	public void testOutdegree()
	{
		LCA_DAG test = new LCA_DAG(5);
		
		test.addEdge(1, 2);
		test.addEdge(2, 4);
		test.addEdge(4, 4);
		
		assertEquals(test.outdegree(4), 1);		
		assertEquals(test.outdegree(44), -1);	
	}

	public void testValidate()
	{
		LCA_DAG test = new LCA_DAG(5);
		
		assertFalse(test.validate(55));		
		assertFalse(test.validate(-55));	
		assertTrue(test.validate(3));		
	}

	@Test
	public void testLCA()
	{
		LCA_DAG test = new LCA_DAG(16);
		
		test.addEdge(1, 2);
		test.addEdge(2, 4);
		test.addEdge(3, 8);
		test.addEdge(4, 12);
		test.addEdge(5, 16);
		
		assertEquals(test.findLCA(2, 4), 2);	
		assertEquals(test.findLCA(4, 5), -1);	
		assertEquals(test.findLCA(4, 4), 4);	
		assertEquals(test.findLCA(3, 16), -1);	
		assertEquals(test.findLCA(9, 7), -1);	
		assertEquals(test.findLCA(-2, 3), -1);	
		
		LCA_DAG test2 = new LCA_DAG(5);
		assertEquals(test2.findLCA(1, 2), -1);	
	}
}