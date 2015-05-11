package com.augmentum.google.controller;

import org.junit.Test;

import com.augmentum.google.config.Config;
import com.jfinal.ext.test.ControllerTestCase;

public class GoogleControllerTest extends ControllerTestCase<Config>{

	@Test
	public void validatePurchase(){
		String uri = "/google/validatePurchase";
		String result = use(uri).invoke();
		System.out.println("Result : " + result);
	}
}
