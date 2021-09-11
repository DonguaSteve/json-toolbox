package com.dg.jsontools.util;

public class ConvertUtils {

   static final char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

   public static String bytes2HexString(byte[] src) {
      char[] res = new char[src.length << 1];
      for (int i = 0, j = 0; i < src.length; i++) {
         res[j++] = hexDigits[src[i] >>> 4 & 0x0f];
         res[j++] = hexDigits[src[i] & 0x0f];
      }
      return new String(res);
   }

   public static byte[] hexString2Bytes(String hexString) {
      int len = hexString.length();
      if (len % 2 != 0) {
         throw new IllegalArgumentException("Length is not even");
      }
      char[] hexBytes = hexString.toUpperCase().toCharArray();
      byte[] res = new byte[len / 2];
      for (int i = 0; i < len; i += 2) {
         res[i >> 1] = (byte) (hex2Dec(hexBytes[i]) << 4 | hex2Dec(hexBytes[i + 1]));
      }
      return res;
   }

   private static int hex2Dec(char hexChar) {
      if (hexChar >= '0' && hexChar <= '9') {
         return hexChar - '0';
      } else if (hexChar >= 'A' && hexChar <= 'E') {
         return hexChar - 'A' + 10;
      } else {
         throw new IllegalArgumentException();
      }
   }
   
   public static String decToHexString(int n) {
      StringBuffer s = new StringBuffer();
      String a;
      char[] b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
      while (n != 0) {
         s = s.append(b[n % 16]);
         n = n / 16;
      }
      a = s.reverse().toString();
      return a;
   }
}
