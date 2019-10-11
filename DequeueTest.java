import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.NoSuchElementException;


public class DequeueTest {
    Dequeue<Student> q;
    Student s1, s2, s3, s4, s5, s6, s7, s8;

	@BeforeEach
	public void setUp() throws Exception {
		q = new Dequeue<Student> ();
		s1 = new Student("John", "Doe");
		s2 = new Student ("Jane", "Smith");
		s3 = new Student ("Bob", "Taylor");
		s4 = new Student ("Anne", "Frank");
		s5 = new Student("Frank", "Gauvin");
		s6 = new Student("Kevin", "Austin");
		s7 = new Student ("Cindy", "Bryant");
		s8 = new Student ("Peter", "Lander");
	}

	@Test
	public void testaddFrontAddRear() {
		q.addFront(s1);
		q.addFront(s2);
		q.addFront(s3);
		assertThat(q.peekFront(), is(s3));     // assertEquals(s3, q.peekFront());
		q.addRear(s4);
		q.addRear(s5);
		q.addFront(s6);
		q.addRear(s7);
		assertThat(q.peekRear(), is(s7));      // assertEquals(s7, q.peekRear());
		assertThat(q.size(), is(7));           // assertEquals(7, q.size());
	} 
	
    @Test
	public void testRemoveFrontRemoveRear() {
		q.addFront(s1);
		q.addFront(s2);
		q.addFront(s3);
		q.addRear(s4);
		q.addRear(s5);
		q.addFront(s6);
		q.addRear(s7);
		assertThat(q.removeFront(), is(s6));   // assertEquals(s6, q.removeFront());
		assertThat(q.removeRear(), is(s7));    // assertEquals(s7, q.removeRear());
		assertThat(q.removeFront(), is(s3));   // assertEquals(s3, q.removeFront() );
		assertThat(q.size(), is(4));           // assertEquals(4, q.size());
		assertThat(q.removeRear(), is(s5));    // assertEquals(s5, q.removeRear());
		assertThat(q.removeFront(), is(s2));   // assertEquals(s2, q.removeFront());
		assertThat(q.size(), is(2));           // assertEquals(2, q.size());
		assertThat(q.removeFront(), is(s1));   // assertEquals(s1, q.removeFront());
		assertThat(q.removeRear(), is(s4));    // assertEquals(s4, q.removeRear());
		assertTrue(q.empty());
		assertTrue(q.size() == 0);  
		
	} 

	@Test
	public void testIterator() {
		q.addFront(s1);
		q.addFront(s2);
		q.addFront(s3);
		q.addRear(s4);
		assertEquals(4, q.size() );
		q.addRear(s5);
		q.addFront(s6);
		q.addRear(s7);
		assertEquals(7, q.size() );
		Iterator <Student> iter = q.iterator();
		ArrayList<Student> list = new ArrayList<Student>();
		while (iter.hasNext()) {
			list.add(iter.next() );
		}
		assertThat(list.get(0), is(s6));       // assertEquals(s6, list.get(0));
		assertThat(list.get(1), is(s3));       // assertEquals(s3, list.get(1));
		assertThat(list.get(2), is(s2));       // assertEquals(s2, list.get(2));
		assertThat(list.get(3), is(s1));       // assertEquals(s1, list.get(3));
		assertThat(list.get(4), is(s4));       // assertEquals(s4, list.get(4));
		assertThat(list.get(5), is(s5));       // assertEquals(s5, list.get(5));
		assertThat(list.get(6), is(s7));       // assertEquals(s7, list.get(6));
	} 
	
	@Test
	public void testPeekFrontOnEmptyQueue() throws NoSuchElementException  {
		assertThrows(NoSuchElementException.class, () ->
		  {Dequeue<Student> q = new Dequeue<Student>();
		   q.peekFront();});
	}
	
	@Test
	public void testRearFrontOnEmptyQueue() throws NoSuchElementException{
		assertThrows(NoSuchElementException.class, () ->
		  {Dequeue<Student> q = new Dequeue<Student>();
		   q.peekRear();});
	}
	
	@Test
	public void testRemoveFrontOnEmptyQueue() throws NoSuchElementException {
		assertThrows(NoSuchElementException.class, () ->
		  {Dequeue<Student> q = new Dequeue<Student>();
		   q.removeFront();});
	}
	
	@Test
	public void testRemoveRearOnEmptyQueue() throws NoSuchElementException {
		assertThrows(NoSuchElementException.class, () ->
		  {Dequeue<Student> q = new Dequeue<Student>();
		   q.removeRear();});
	}
}