package com.example.demo;

import com.example.demo.Entity.UserEntity;
import com.example.demo.repository.userrepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SpringNewApplicationTests {
	@Autowired
	private userrepository UserRepo;

	@Test
	void contextLoads() {
		assertEquals(3,1+2);
	UserEntity user = UserRepo.findByuserName("Gopal");

		assertTrue(!user.getJournalentity().isEmpty());
//		assertTrue(2>1);


	}

}
