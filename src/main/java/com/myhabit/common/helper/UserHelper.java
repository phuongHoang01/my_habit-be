package com.myhabit.common.helper;

import org.springframework.security.core.context.SecurityContextHolder;

import com.myhabit.entities.UserPrincipal;

public class UserHelper {

	public static UserPrincipal getCurrentUserLoginInSystem() {
		UserPrincipal currentLoginUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return currentLoginUser;
	}
}
