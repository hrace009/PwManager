/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pwmanager.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Adriano
 */
public class md5 {

    public static String MD5(String md5) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes("UTF-8"));//aplica a codificação
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {//converte os bytes para uma string hexadecimal
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            throw new java.security.NoSuchAlgorithmException("Não foi possível aplicar o hash");
        }
    }

}
