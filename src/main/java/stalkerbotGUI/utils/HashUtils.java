package stalkerbotGUI.utils;

import stalkerbotGUI.exception.ApplicationException;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

public class HashUtils {

    public static String getMD5Hash(String string){
        String hash = null;
        try {
            hash = DatatypeConverter.printHexBinary(
                MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8")));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (hash==null)
            throw new ApplicationException("String : "+string+" - cannot be convert to hash");

        return hash;
    }

}
