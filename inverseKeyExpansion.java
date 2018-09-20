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
public class inverseKeyExpansion {
	
		//private byte[] keyArray=new byte[16];
			public byte[][] word=new byte[4][44];
			private byte[] roundConstantTable= {1,2,4,8,16,32,64,-128,27,54};
			protected  int counter=-1;
			protected byte[] temp=new byte[4];
			
			
			
			public inverseKeyExpansion(byte[] keyArray) {
				
				
				//initialize word
				int index=0;
				for(int i=0;i<4;i++)
					{for(int j=0;j<4;j++)
						{word[j][i]=keyArray[index];
						 System.out.printf("%d ",keyArray[index]);
						 index++;				 
						}
					System.out.println();
					System.out.println("word"+i+"-->>"+word[0][i]+" "+word[1][i]+" "+word[2][i]+" "+word[3][i]);
					}
				
				//word array expansion
				
				
				
				
				for(int i=4;i<44;i++)
				{	
					
					if(i%4==0)
						{
							// byte[] temp= {word[0][i-1],word[1][i-1],word[2][i-1],word[3][i-1]};
							for(int j=0;j<4;j++)
								temp[j]=word[j][i-1];
							counter++;
							temp=gFunction(temp);
							
							System.out.println("After gFun Call:");
							for(int k=0;k<4;k++)
								System.out.printf("%d ",temp[k]);
							
							for(int j=0;j<4;j++)
							{
							
							word[j][i]=(byte) ( (byte)(word[j][i-4])^(byte)(temp[j]));
							}
							
						 
						}
					else
					{   for(int j=0;j<4;j++)
						temp[j]=word[j][i-4];
						//byte[] temp= {word[0][i-4],word[1][i-4],word[2][i-4],word[3][i-4]};						
					System.out.println("After if not gFun Call:temp");
					for(int k=0;k<4;k++)
						System.out.printf("%d ",temp[k]);
					
					
					for(int j=0;j<4;j++)
					{
					
					word[j][i]=(byte) ( (byte)(word[j][i-1])^(byte)(temp[j]));
					
					}
					}
					
					System.out.println("word"+i+"-->>"+word[0][i]+" "+word[1][i]+" "+word[2][i]+" "+word[3][i]);
				}//for
				
				//print final result of word
				String roundKeys=byteArrayToHexString(word);
				System.out.println(roundKeys);
//				for(int i=0;i<44;i++)
//					{System.out.println();
//					for(int j=0;j<4;j++)
//						System.out.printf("%d ",word[i][j]);
//					}
					
				
			}//con
			private byte[] gFunction(byte[] temp) {
				
				//Left Circular Shift
				byte leftmostValue=temp[0];
				for(int i=0;i<3;i++)
				{
					temp[i]=temp[i+1];
					
				}
				temp[3]=leftmostValue;

				System.out.println();
				for(int i=0;i<4;i++)
					System.out.printf("%d ",temp[i]);
				
				//Substitute Bytes
				//String hexSubstitutedString="";
				
				byte[] substitutedByte=new byte[4];
				for(int i=0;i<4;i++)
				{
					substituteBytes SubBytes=new substituteBytes(temp[i]);
					substitutedByte[i]=SubBytes.subBytes(temp[i]);
				}
				
				
				System.out.println("After Sub in Byte: ");
				for(int i=0;i<4;i++)
					System.out.printf("%d ",substitutedByte[i]);
				
				//Round Constant Array init in Byte
				
				byte[] rcArray= {roundConstantTable[counter],(byte)0,(byte)0,(byte)0};
				
				//print rc
				System.out.println("RC Array");
				for(int i=0;i<4;i++)
				System.out.printf("%d ",rcArray[i]);
				
				
				//XOR
				byte[] resultOfgFunction=new byte[4];
				for(int i=0;i<4;i++)
					resultOfgFunction[i]= (byte) ((byte) (substitutedByte[i])^(byte)(rcArray[i]));
				
				//print final
						System.out.println("After XOR");
						for(int i=0;i<4;i++)
						System.out.printf("%d ",resultOfgFunction[i]);
				
				return resultOfgFunction;
			}//gFunction end
			
//			 private static byte[] hexStringToByteArray(String s) {
//				    int len = s.length();
//				    byte[] data = new byte[len / 2];
//				    for (int i = 0; i < len; i += 2) {
//				        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
//				                             + Character.digit(s.charAt(i+1), 16));
//				    }
//				    return data;
//				}
			 
			//Converting a bytes array to string of hex character
			  public static String byteArrayToHexString(byte[][] b) {
			 
			  String data = new String();
			  for(int i=0;i<44;i++)
			  { //int len = b[i].length;
			  	 if(i%4==0)
			  		 data+="\n Round-->"+i/4+": ";

				  for (int j = 0; j < 4; j++){
					  data += Integer.toHexString((b[j][i] >> 4) & 0xf);
					  data += Integer.toHexString(b[j][i] & 0xf);
			  		}
				  data+=" ";
				  
				  
			  }
			  return data;
			  }
			
			
		

	


}
