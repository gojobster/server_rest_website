package com.jobster.server.util;

import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;

public final class Seguridad {

	public final static String GenerarSHA56(String chain) {

		try{
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			return aux (digest, chain);
		}catch(Exception ex){
			return null;
		}
	}

	public final static String GenerateSecureRandomString () {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}

    public final static String GenerarRandomFileName() {

        return RandomStringUtils.randomAlphanumeric(10);

    }

    public final static String GenerateMD5(String chain) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            return aux (digest, chain);
        } catch (Exception ex) {
            return null;
        }
    }

    private final static String aux (MessageDigest digest, String chain) {
		digest.update(chain.getBytes());
        byte[] byteData = digest.digest();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}

	private Seguridad(){
		//this prevents even the native class from calling this constructor as well :
		throw new AssertionError();
	}
}
