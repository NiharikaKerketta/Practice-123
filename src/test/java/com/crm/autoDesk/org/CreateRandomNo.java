package com.crm.autoDesk.org;

import java.util.Random;

public class CreateRandomNo {

	public static void main(String[] args) {
		
		Random ran = new Random();
		int ranDom = ran.nextInt(10000);
		System.out.println(ranDom);
	}

}
