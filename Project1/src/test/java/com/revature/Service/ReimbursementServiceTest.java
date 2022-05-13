package com.revature.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.DAO.MockReimbursementData;
import com.revature.DAO.ReimbursementDAO;
import com.revature.Model.Reimbursement;
import com.revature.Model.Role;
import com.revature.Model.Status;
import com.revature.Model.Type;
import com.revature.Model.User;
import com.revature.Service.ReimbursementService;

import com.revature.Service.UserService;

public class ReimbursementServiceTest {
	
	private static ReimbursementService reimbursementService;
	private static UserService userService;
	private static ReimbursementDAO reimbursementDAO;
	
	private Reimbursement REIMBURSEMENT_TO_PROCESS;
	private List<Reimbursement> mockPendingReimbursements;
	private List<Reimbursement> mockApprovedReimbursements;
	private List<Reimbursement> mockDeniedReimbursements;
	private User GENERIC_EMPLOYEE_1;
	private User GENERIC_MANAGER_1;
	
	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
		reimbursementService = new ReimbursementService();
		
	}
	
	@BeforeEach
	public void setUp() throws Exception {
		userService = mock(UserService.class);
		reimbursementDAO = mock(ReimbursementDAO.class);
		
		MockReimbursementData mockReimbursementData = new MockReimbursementData();
		
		reimbursementService.reimbursementDAO = reimbursementDAO;
		reimbursementService.userService = userService;
		
		GENERIC_EMPLOYEE_1 = new User(1, "genericEmployee1", "genericPassword", Role.EMPLOYEE);
		GENERIC_MANAGER_1 = new User(1, "genericManager1", "genericPassword", Role.MANAGER);
		
		REIMBURSEMENT_TO_PROCESS = new Reimbursement(2, GENERIC_EMPLOYEE_1.getId(), "Oracle Certification", Type.OTHER, Status.PENDING, 150.00);
		
		List<Reimbursement> mockReimbursements = mockReimbursementData.getReimbursements();
		mockPendingReimbursements = new ArrayList<>();
		mockApprovedReimbursements = new ArrayList<>();
		mockDeniedReimbursements = new ArrayList<>();
		
		for(Reimbursement reimbursement : mockReimbursements) {
			if(reimbursement.getStatus()== Status.PENDING) {
				mockPendingReimbursements.add(reimbursement);
			} else if(reimbursement.getStatus()== Status.APPROVED) {
				mockApprovedReimbursements.add(reimbursement);
			} else {
				mockDeniedReimbursements.add(reimbursement);
			}
			
		}
		
	}
	
	@Test
	public void testGetResolvedReimbursementsReturnOnlyApprovedAndDenied() {
		
		when(reimbursementDAO.getByStatus(Status.APPROVED)).thenReturn(mockApprovedReimbursements);
		when(reimbursementDAO.getByStatus(Status.DENIED)).thenReturn(mockDeniedReimbursements);
		
		List<Reimbursement> resolvedReimbursements = new ArrayList<>();
		resolvedReimbursements.addAll(mockApprovedReimbursements);
		resolvedReimbursements.addAll(mockDeniedReimbursements);
		
		assertEquals(resolvedReimbursements, reimbursementService.getResolvedReimbursements());
		
		verify(reimbursementDAO).getByStatus(Status.APPROVED);
		verify(reimbursementDAO).getByStatus(Status.DENIED);
		
	}
	
	@Test
	public void testGetPendingReimbursementsReturnOnlyPendng() {
		
		when(reimbursementDAO.getByStatus(any(Status.class))).thenReturn(mockPendingReimbursements);
		
		
		
		assertEquals(mockPendingReimbursements, reimbursementService.getPendingReimbursements());
		
		verify(reimbursementDAO).getByStatus(Status.PENDING);
		
	}
	
	
	
	
	
	
	@Test
	public void testUpdateThrowsIllegalArgumentExceptionWhenResolverIsNotManager() {
		
		when(userService.getUserById(anyInt())).thenReturn(GENERIC_EMPLOYEE_1);
		
		assertThrows(IllegalArgumentException.class, () -> reimbursementService.update(REIMBURSEMENT_TO_PROCESS, GENERIC_EMPLOYEE_1.getId(),
				Status.APPROVED));
		
		verify(reimbursementDAO, never()).update(REIMBURSEMENT_TO_PROCESS);
		verify(userService).getUserById(GENERIC_EMPLOYEE_1.getId());
	}
	
	@Test
	public void testSubmitREimbursementThrowsIllegalArgumentExceptionWhenSubmittedByManager() {
		
		when(userService.getUserById(anyInt())).thenReturn(GENERIC_MANAGER_1);
		
		assertThrows(IllegalArgumentException.class, () -> reimbursementService.submitReimbursement(REIMBURSEMENT_TO_PROCESS));
		
		verify(reimbursementDAO, never()).create(REIMBURSEMENT_TO_PROCESS);
		verify(userService).getUserById(GENERIC_MANAGER_1.getId());
	}
	
	@Test
	public void testReimbursementStatusIsChangedAfterUpdate() {
		
		when(userService.getUserById(anyInt())).thenReturn(GENERIC_MANAGER_1);
		
		assertEquals(Status.APPROVED, reimbursementService.update(REIMBURSEMENT_TO_PROCESS, GENERIC_MANAGER_1.getId(), 
				Status.APPROVED).getStatus());
		
		verify(userService).getUserById(GENERIC_EMPLOYEE_1.getId());
		verify(reimbursementDAO).update(REIMBURSEMENT_TO_PROCESS);
	}
	
	@Test
	public void testResolverIsAssignedAfterReimbursementUpdate() {
		
		when(userService.getUserById(anyInt())).thenReturn(GENERIC_MANAGER_1);
		
		assertEquals(GENERIC_MANAGER_1.getId(), reimbursementService.update(REIMBURSEMENT_TO_PROCESS, GENERIC_MANAGER_1.getId(), 
				Status.APPROVED).getResolver());
		
		verify(userService).getUserById(GENERIC_MANAGER_1.getId());
		verify(reimbursementDAO).update(REIMBURSEMENT_TO_PROCESS);
	}
	
	
	

}
