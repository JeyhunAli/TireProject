package com.tireproject.tests;

import org.testng.annotations.Test;

public class test extends BaseTest {

	@Test
	public void getSizeResult_test() {

		homePage.getMainSizePage();
		homePage.getSizeResult();

	}
}
