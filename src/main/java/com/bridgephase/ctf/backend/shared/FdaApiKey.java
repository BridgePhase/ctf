package com.bridgephase.ctf.backend.shared;

import java.util.Random;

public class FdaApiKey {
	public static final int KEY_LENGTH = 40;
    private static final String[] keys = {
    	"Bgmkg56DvLHrmPJIt5IiRgaADs9gn8nuUEBwhKXz", 
    	"3Dgdj6IhmBbcg57FpRP4lSzWUQyCM3CbkxSyGa14",
    	"rwvGgmG59cL6YKA4sFqzB0d03rfRtvmwKt0jF8ry",
    	"PqN1GS3vT72rNMhP1HCsgn5kguEV31Y29pWzsbaG"
    	};
    private static final Random random = new Random();
	
	private static class Holder {
        static final FdaApiKey INSTANCE = new FdaApiKey();
    }
	
	public String key() {
		return keys[random.nextInt(keys.length)];
	}

    public static FdaApiKey instance() {
        return Holder.INSTANCE;
    }
}
