package com.revature.DAO;

import java.util.ArrayList;
import java.util.List;

import com.revature.Model.Role;
import com.revature.Model.User;


public class MockUserData {
	private final List<User> users = new ArrayList<>();
		
	public MockUserData() {
		
		User TEST_EMP_1 = new User ( 1, "testemp1","testPass1", Role.EMPLOYEE);
		User TEST_EMP_2 = new User ( 2, "testemp2","testPass2", Role.EMPLOYEE);
		User TEST_EMP_3 = new User ( 3, "testemp3","testPass3", Role.EMPLOYEE);
		User TEST_MAN_1 = new User ( 4, "testman1","testPass1", Role.MANAGER);
		User TEST_MAN_2 = new User ( 5, "testman2","testPass2", Role.MANAGER);
		User TEST_MAN_3 = new User ( 6, "testman3","testPass3", Role.MANAGER);
		
		users.add(TEST_EMP_1);
		users.add(TEST_EMP_2);
		users.add(TEST_EMP_3);
		users.add(TEST_MAN_1);
		users.add(TEST_MAN_2);
		users.add(TEST_MAN_3);
	}
	public List<User> getUsers(){return users;}
}
