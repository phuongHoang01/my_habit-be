package com.myhabit.common.helper;

import java.util.UUID;

public class RandomString {

	public static String randomIdString() {
		return UUID.randomUUID().toString();
	}
}
