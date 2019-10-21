package edu.gcu.bootcamp.cst135.milestone.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class CheckingTest {

	@Test
	public void testGetOverdraft() {
		final double overdraft = 50.40;
        Checking checking = new Checking("Test",0, overdraft);
        assertEquals("Overdraft test fails", checking.getOverdraft(), overdraft, 0.001);
	}

	@Test
	public void testSetOverdraft() {
		final double Setoverdraft = 251.92;
        Checking checking = new Checking("Test",0, Setoverdraft);
        checking.setOverdraft(Setoverdraft*8);
        assertEquals("Overdraft test fails", checking.getOverdraft(), Setoverdraft*8, 0.001);
	}

	@Test
	public void testGetAccountNumber() {
		final String AccountNumber = "CESGAR123";
        Checking checking = new Checking(AccountNumber,5000,0.001);
        assertEquals("AccountNumber test fails", AccountNumber,checking.getAccountNumber());
	}

	@Test
	public void testGetAccountBalance1() {
		final double AccountBalance = 667.12;
        Checking checking = new Checking("TEST", AccountBalance, 4);
        checking.setAccountBalance(AccountBalance*8);
        
        assertEquals("AccountBalance test fails", checking.getAccountBalance(), AccountBalance*8, 0.001);
	}

//	@Test
//	public void testGetAccountBalance() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testSetAccountBalance() {
//		fail("Not yet implemented");
//	}

}
