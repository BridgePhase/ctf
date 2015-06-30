package com.bridgephase.ctf.backend.shared;

import java.util.Random;

public class FdaApiKey {
	public static final int KEY_LENGTH = 40;
    private static final String[] keys = {
    	"Bgmkg56DvLHrmPJIt5IiRgaADs9gn8nuUEBwhKXz", 
    	"3Dgdj6IhmBbcg57FpRP4lSzWUQyCM3CbkxSyGa14",
    	"rwvGgmG59cL6YKA4sFqzB0d03rfRtvmwKt0jF8ry",
    	"PqN1GS3vT72rNMhP1HCsgn5kguEV31Y29pWzsbaG",
    	"LgSXzLFpE0KH2MD4WBdAuhheOUapDCaJSdpnpqU8",
    	"F4bh03KyETk3E1IxKClPnDisPtE0s1Qwg7qSNQ8V",
    	"MTJRV0cdFgSKDC8vKmUuxN8usChShutoJpAK79Ef",
    	"iIU0mCsAF1v8HmIOFmbOntsn05BeuAqCNJV4eYNz",
    	"9Y2weGhDCusPkGYkxJGMnCzV4sSqDjbWmgljDA8L",
    	"r518JEXTkxyzEga6IJwJc91lBlyl95n3pbB7twOu",
    	"Za508FHNiLB4cmMQNg7hGp8fH8c1cWBLkzoWrfE3",
    	"CnvnH18fQbR4exsdptiJDq7l3wIlmFkOJ3yvb9e2"
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
