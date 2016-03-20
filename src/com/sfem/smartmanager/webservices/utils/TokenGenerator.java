package com.sfem.smartmanager.webservices.utils;

import java.math.BigInteger;
import java.security.SecureRandom;

import com.sfem.smartmanager.UI.Resources;

public final class TokenGenerator {
	private SecureRandom random = new SecureRandom();

	  public String nextTokenValue()
	  {
	   // return new BigInteger(130, random).toString(32);
		 return  Resources.USER_TOKEN;
	  }
}
