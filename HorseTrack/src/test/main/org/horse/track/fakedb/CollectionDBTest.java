package org.horse.track.fakedb;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class CollectionDBTest {
	
	private CollectionDB instance;
	
	@Before
	public void setUp(){
		this.instance = CollectionDB.getInstance();
	}
	
	
	@Test
	public void testSingleton() {
		CollectionDB target = CollectionDB.getInstance();
		assertThat("Instances should be identical ", instance, is(equalTo(target)));
	}

	@Test
	public void testNextSequence(){
		Long fixture = instance.getNextSequence();
		Long target = instance.getNextSequence();
		assertThat(fixture, is(equalTo(target-1)));
	}
	

}
