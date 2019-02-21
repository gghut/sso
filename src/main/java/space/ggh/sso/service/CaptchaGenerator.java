package space.ggh.sso.service;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author by ggh on 18-12-4.
 */
public class CaptchaGenerator {

    private MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

    public CaptchaGenerator() throws NoSuchAlgorithmException {
    }

    public boolean verify(String text, int random){
        return getSHA256Str(text+random).startsWith("3548");
    }

    private String getSHA256Str(String str){
        String encodeStr = "";
        try {
            messageDigest.update(str.getBytes("UTF-8"));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodeStr;
    }

    private String byte2Hex(byte[] bytes){
        StringBuilder stringBuffer = new StringBuilder();
        String temp;
        for (byte aByte : bytes) {
            temp = Integer.toHexString(aByte & 0xFF);
            if (temp.length() == 1) {
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }
}
