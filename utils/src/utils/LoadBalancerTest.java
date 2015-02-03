package utils;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LoadBalancerTest {

	LoadBalancer lb;

	@Before
	public void setUp() throws Exception {
		lb = new LoadBalancer();
	}

	@Test
	public void testAddServer() {

		assertTrue(lb.addServer("1"));
		assertTrue(lb.addServer("2"));
		assertFalse(lb.addServer("1"));
	}

	@Test
	public void testDeleteServer() {

		lb.addServer("1");
		lb.addServer("2");
		lb.addServer("1");

		assertTrue(lb.deleteServer("1"));
		assertTrue(lb.deleteServer("2"));
		assertFalse(lb.deleteServer("1"));
	}

	@Test
	public void testGetRandomServer() {

		lb.addServer("1");
		lb.addServer("2");
		lb.addServer("1");

		for (int i = 0; i < 100; i++) {
			assertFalse(lb.addServer(lb.getRandomServer()));
		}
	}
}
