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
public class encryption {
	
	public static byte[] keyArray;

	public static String encrypt(String p) {
		
		
		String hex=p; //"4556cdef7890cdef5432678765cdeaf5";
				  //4556cdef7890cdef5432678765cdeaf5
		convertion convert=new convertion();
		round round=new round();
		substituteBytes sub =new substituteBytes();
		
		byte[][] byteToEncrypt=convert.hexStringTo2DByteArray(hex);
		
		String keyString="2b7e151628aed2a6abf7158809cf4f3c";
		keyArray=convert.hexStringToByteArray(keyString);
		//KeyExpansion key=new KeyExpansion(keyArray);
		keyExpands key=new keyExpands(keyArray);
		byte[][] addedKeyBytes = new byte[4][4];
		byte[][] afterSub=new byte[4][4];
		byte[][] shiftedRows=new byte[4][4];
		byte[][] mixedColumns=new byte[4][4];
		
		//Initial Transformation
		
		byte[][] byteForRound=round.addRoundKey(byteToEncrypt, key, 0);
		
		String test=convert.twoDbyteArrayToHexString(byteForRound);
		System.out.println(test);
		
		
		
		//Rounds 1 to 9
		
		for(int cycle=1;cycle<=9;cycle++)
		{
			//Substitution
			
			for(int i=0;i<4;i++)
				for(int j=0;j<4;j++)
				{
					byte toSubByte=byteForRound[i][j];
					afterSub[i][j]=sub.subBytes(toSubByte);
					
				}
			System.out.println();
			System.out.println("Substitution Completed: Round-->>"+cycle);
			String test1=convert.twoDbyteArrayToHexString(afterSub);
			System.out.println(test1);
			
			//ShiftRows
			shiftedRows=round.shiftRows(afterSub);
			System.out.println();
			System.out.println("ShiftRows Completed: Round-->>"+cycle);
			String test11=convert.twoDbyteArrayToHexString(shiftedRows);
			System.out.println(test11);
			
			//mixColumns
			mixedColumns=round.mixColumns(shiftedRows);
			System.out.println();
			System.out.println("MixColumns Completed: Round-->>"+cycle);
			String testmix=convert.twoDbyteArrayToHexString(mixedColumns);
			System.out.println(testmix);
			
			//Add Round Key
			
			addedKeyBytes=round.addRoundKey(mixedColumns, key, cycle);
			System.out.println();
			System.out.println("AddRoundKey Completed: Round-->>"+cycle);
			String testadd=convert.twoDbyteArrayToHexString(addedKeyBytes);
			System.out.println(testadd);
			
			byteForRound=addedKeyBytes;
		}
		
		
		//round 10
		
		//Substitution
		
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++)
			{
				byte toSubByte=addedKeyBytes[i][j];
				afterSub[i][j]=sub.subBytes(toSubByte);
				
			}
		//shiftRows
		
		shiftedRows=round.shiftRows(afterSub);
		
		//add Round Key
		
		addedKeyBytes=round.addRoundKey(shiftedRows, key, 10);
		
		System.out.println();
		String encryptedString=convert.twoDbyteArrayToHexString(addedKeyBytes);
		System.out.println("THE ENCRYPTED MESSAGE: ");
		System.out.println(encryptedString);
		
		
		return encryptedString;


	}

}
