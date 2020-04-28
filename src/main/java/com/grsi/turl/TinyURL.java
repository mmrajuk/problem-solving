package com.grsi.turl;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Requirements and Goals
 *
 * 1. Make long url shorten to 1/3rd in length
 * 2. Per day 1000000 requests
 *    per month 300000 requests
 */

public class TinyURL {

    public static void main(String[] args) throws NoSuchAlgorithmException {

        String longUrl = "https://www.amazon.com/Dr-Scholls-Shoes-Madison-Fashion/dp/B06XD3YX4H/ref=sr_1_2?dchild=1&pf_rd_p=74926647-5897-423a-adf4-e90823f63fd6&pf_rd_r=P5G64E4WAXD0AT2107XZ&qid=1584914858&s=apparel&sr=1-2";

        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] md5Hash= md5.digest(longUrl.getBytes());
        String md5HashInHex = DatatypeConverter
                .printBase64Binary(md5Hash).toUpperCase();
        System.out.println(md5HashInHex);
    }
}
