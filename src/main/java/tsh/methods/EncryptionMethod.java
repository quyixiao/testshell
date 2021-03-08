package tsh.methods;

import org.apache.commons.codec.digest.DigestUtils;
import tsh.util.TStringUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptionMethod {



    public static Object sha256(Object ... t) throws Exception {
        try {
            String signStrBefore = (String)t[0];
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(signStrBefore.getBytes());
            String tempStr = null;
            StringBuilder stb = new StringBuilder();
            byte[] origBytes = md.digest();
            for (int i = 0; i < origBytes.length; i++) {
                // 这里按位与是为了把字节转整时候取其正确的整数，java中一个int是4个字节
                // 如果origBytes[i]最高位为1，则转为int时，int的前三个字节都被1填充了
                tempStr = Integer.toHexString(origBytes[i] & 0xff);
                if (tempStr.length() == 1) {
                    stb.append("0");
                }
                stb.append(tempStr);

            }
            return stb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new NoSuchAlgorithmException();
        }
    }

    public static String md5(Object ... t) {
        String md5Str = (String)t[0];
        if (TStringUtil.isBlank(md5Str)) {
            return null;
        }
        return DigestUtils.md5Hex(md5Str);
    }


}
