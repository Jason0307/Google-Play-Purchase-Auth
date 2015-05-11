/**
 * 
 */
package com.augmentum.google.model;

import org.junit.Test;

/**
 * @author Jason.Zhu
 * @email jasonzhu@augmentum.com.cn
 * @date   May 13, 2014 9:44:41 AM
 */
public class TestUser extends BaseTest<User>{

	
	@Test
	public void testFindByRelevanceCode(){
		
		User user = User.dao.findByRelevanceCode("JtbFw1OD");
		assertNotNull(user);
	}
}
