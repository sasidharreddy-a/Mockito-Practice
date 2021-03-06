package com.test;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.main.CalculatorService;
import com.main.MathApplication;

@RunWith(MockitoJUnitRunner.class)
public class MockitoOrder {

	   private MathApplication mathApplication;
	   private CalculatorService calcService;

	   @Before
	   public void setUp(){
	      mathApplication = new MathApplication();
	      calcService = Mockito.mock(CalculatorService.class);
	      mathApplication.setCalculatorService(calcService);
	   }

	   @Test
	   public void testAddAndSubtract(){

	      //add the behavior to add numbers
	      when(calcService.add(20.0,10.0)).thenReturn(30.0);

	      //subtract the behavior to subtract numbers
	      when(calcService.subtract(20.0,10.0)).thenReturn(10.0);

	      //test the add functionality
	      Assert.assertEquals(mathApplication.add(20.0, 10.0),30.0,0);

	      //test the subtract functionality
	      Assert.assertEquals(mathApplication.subtract(20.0, 10.0),10.0,0);

	      //create an inOrder verifier for a single mock
	      InOrder inOrder = Mockito.inOrder(calcService);

	      //following will make sure that add is first called then subtract is called.
	      inOrder.verify(calcService).subtract(20.0,10.0);
	      inOrder.verify(calcService).add(20.0,10.0);
	   }

}
