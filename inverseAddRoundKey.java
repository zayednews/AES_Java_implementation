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
public class inverseAddRoundKey {
	
	
	
	public byte[][] addRoundKey(byte[][] unaddedBytes, inverseKeyExpansion key, int round)
	{
		
		
		byte[][] temp=new byte[4][4];
		byte[][] resultAddRound=new byte[4][4];
		
		//key is copying to temp
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++)
			{
				temp[j][i]=key.word[j][round*4+i];
				//System.out.printf("%d ",temp[i][j]);
			}
		
		System.out.println("Printed Key for round: "+round);
		String testaddkey=convertion.twoDbyteArrayToHexString(temp);
		System.out.println(testaddkey);
		//key XORing with unaddedBytes
		
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++)
			{
				resultAddRound[j][i]=(byte) (unaddedBytes[j][i]^temp[j][i]);
			}
		
		
		return resultAddRound;
	}

}
