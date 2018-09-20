/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aesmain;

/**
 *
 * @author Zayed news
 */
public class convertion {

		 
	   public static String asciiToHex(String asciiValue)
	   {
	      char[] chars = asciiValue.toCharArray();
	      StringBuffer hex = new StringBuffer();
	     for(int i=0;i<chars.length;i++)
	         hex.append(Integer.toHexString((int) chars[i]));
	      
	      return hex.toString();
	   }
	 
	   public static String hexToASCII(String hexValue)
	   {
	      StringBuilder output = new StringBuilder("");
	      for (int i = 0; i < hexValue.length(); i += 2)
	      {
	         String str = hexValue.substring(i, i + 2);
	         output.append((char) Integer.parseInt(str, 16));
	      }
	      return output.toString();
	   }
	   
	   public static byte[] hexStringToByteArray(String s) {
		    int len = s.length();
		    byte[] data = new byte[len / 2];
		    for (int i = 0; i < len; i += 2) {
		        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
		                             + Character.digit(s.charAt(i+1), 16));
		    }
		    return data;
		}
	   //Converting a bytes array to string of hex character
	   public static String byteArrayToHexString(byte[] b) {
	   int len = b.length;
	   String data = new String();

	   for (int i = 0; i < len; i++){
	   data += Integer.toHexString((b[i] >> 4) & 0xf);
	   data += Integer.toHexString(b[i] & 0xf);
	   }
	   return data;
	   }
	   
	   //hex String to 2d byteArray
	   public static byte[][] hexStringTo2DByteArray(String s) {
		    int len = s.length();
		    byte[][] data = new byte[4][4];
		    byte[] input=new byte[len/2];
		    	
		    	
		    	{for (int i = 0; i < len; i += 2)
		    		input[i/2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
		                                 + Character.digit(s.charAt(i+1), 16));
		    	  		    	
		    	}
		    	int counter=0;
		    	for(int j=0;j<4;j++)
		    		for(int k=0;k<4;k++)		    			
		    		{
		    			data[k][j]=input[counter];
		    			counter++;
		    		}
		    return data;
		}
	   
	   
	   //2D byteArray to hexString
	   public static String twoDbyteArrayToHexString(byte[][] b) {
		   
		   String data = new String();

		   for (int i = 0; i < 4; i++)
		   	{for(int j=0;j<4;j++)
		   		{
		   			data += Integer.toHexString((b[j][i] >> 4) & 0xf);
		   			data += Integer.toHexString(b[j][i] & 0xf);
		   			//data+=" ";
		   		}
		   //	data+="\n";
		   	}
		   
		   	return data;
		   }
}
