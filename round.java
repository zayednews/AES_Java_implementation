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
public class round {
	
	private byte[][] shiftedBytes=new byte[4][4];
	private byte[][] mixedBytes=new byte[4][4];
	
	
	
	//Shift Rows
	public byte[][] shiftRows(byte[][] unShiftedBytes)
	{
		//this.shiftedBytes=unShiftedBytes;
		int nb=4;
		
		//copy
		
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++)
				this.shiftedBytes[i][j]=unShiftedBytes[i][j];
		
		
		for(int row=1;row<4;row++)
			for(int col=0;col<nb;col++)
			{
				this.shiftedBytes[row][col]=unShiftedBytes[row][(col+shift(row,nb))%nb];
				
			}	
		
		
		return this.shiftedBytes;
		
	}//ShiftRows
	
	private int shift(int r, int n)
	{
		return r;
	}
	
	//mixColumns
	public byte[][] mixColumns(byte[][] unmixedBytes)
	{	
		byte mOfx=27; //as 00011011
		for(int row=0;row<4;row++)
			for(int col=0;col<4;col++)
			{
				//System.out.println(unmixedBytes[0][0]<<1);
				if(row==0)    //02.Soc xor 03.S1c xor S2c xor S3c
					{
						byte temp1=unmixedBytes[0][col];
						byte temp2=unmixedBytes[1][col];
						
						
						//high bit set  check 
						//row column e unmatch 
						if(((byte)(temp1 & (byte)(-128))==(byte)-128) && ((byte)(temp2 & (byte)(-128))==(byte)-128) )
						{
							mixedBytes[0][col]=(byte) (((temp1<<1)^mOfx)^
									((temp2^(temp2<<1))^mOfx)
									^unmixedBytes[2][col]^unmixedBytes[3][col]);
						}
						else if(((byte)(temp1 & (byte)(-128))==(byte)-128))
						{
							mixedBytes[0][col]=(byte) (((temp1<<1)^mOfx)^
									(temp2^(temp2<<1))
									^unmixedBytes[2][col]^unmixedBytes[3][col]);
						}
						else if(((byte)(temp2 & (byte)(-128))==(byte)-128))
						{
							mixedBytes[0][col]=(byte) ((temp1<<1)^
									((temp2^(temp2<<1))^mOfx)
									^unmixedBytes[2][col]^unmixedBytes[3][col]);
						}
						else
						{
							mixedBytes[0][col]=(byte) ((temp1<<1)^
									(temp2^(temp2<<1))
									^unmixedBytes[2][col]^unmixedBytes[3][col]);
						}
							
					
						
					}//outer if for row=1
				
				if(row==1)    // S0c xor 02.S1c xor 03.S2c xor S3c
					{
						byte temp3=unmixedBytes[1][col];
						byte temp4=unmixedBytes[2][col];
						
						if(((byte)(temp3 & (byte)(-128))==(byte)-128) && ((byte)(temp4 & (byte)(-128))==(byte)-128) )
						
							{mixedBytes[1][col]=(byte) ((unmixedBytes[0][col]^
									((temp3<<1)^mOfx))
									^(temp4^((temp4<<1)^mOfx))
									^unmixedBytes[3][col]);
							}
						else if(((byte)(temp3 & (byte)(-128))==(byte)-128))
						{
							mixedBytes[1][col]=(byte) ((unmixedBytes[0][col]^
									((temp3<<1)^mOfx))
									^(temp4^((temp4<<1)))
									^unmixedBytes[3][col]);
						}
						else if(((byte)(temp4 & (byte)(-128))==(byte)-128))
						{
							mixedBytes[1][col]=(byte) ((unmixedBytes[0][col]^
									((temp3<<1)))
									^(temp4^((temp4<<1))^mOfx)
									^unmixedBytes[3][col]);
						}
						else
						{
							mixedBytes[1][col]=(byte) ((unmixedBytes[0][col]^
									((temp3<<1)))
									^(temp4^((temp4<<1)))
									^unmixedBytes[3][col]);
						}
					}//end row1 if
					
				if(row==2)    // S0c xor S1c xor 02.S2c xor 03.S3c
					{
						byte temp5=unmixedBytes[2][col];
						byte temp6=unmixedBytes[3][col];
						
						if(((byte)(temp5 & (byte)(-128))==(byte)-128) && ((byte)(temp6 & (byte)(-128))==(byte)-128))
					
							{	mixedBytes[2][col]=(byte) (unmixedBytes[0][col]^
									unmixedBytes[1][col]^
									((temp5<<1)^mOfx)^(temp6^
											((temp6<<1)^mOfx)));
							}
						else if(((byte)(temp5 & (byte)(-128))==(byte)-128))
						{
							mixedBytes[2][col]=(byte) (unmixedBytes[0][col]^
									unmixedBytes[1][col]^
									((temp5<<1)^mOfx)^(temp6^
											((temp6<<1))));
						}
						else if(((byte)(temp6 & (byte)(-128))==(byte)-128))
						{
							mixedBytes[2][col]=(byte) (unmixedBytes[0][col]^
									unmixedBytes[1][col]^
									((temp5<<1))^(temp6^
											((temp6<<1)^mOfx)));
						}
						else
						{
							mixedBytes[2][col]=(byte) (unmixedBytes[0][col]^
									unmixedBytes[1][col]^
									((temp5<<1))^(temp6^
											((temp6<<1))));
						}
					}//end if row2
					
				if(row==3)   // 03.S0c xor S1c xor S2c xor 02.S3c
					{
						byte temp7=unmixedBytes[0][col];
						byte temp8=unmixedBytes[3][col];
						
						if(((byte)(temp7 & (byte)(-128))==(byte)-128) && ((byte)(temp8 & (byte)(-128))==(byte)-128))
						{
							mixedBytes[3][col]=(byte) ((temp7^
									((temp7<<1)^mOfx))^unmixedBytes[1][col]
									^unmixedBytes[2][col]^((temp8<<1)^mOfx));
						}
						else if(((byte)(temp7 & (byte)(-128))==(byte)-128))
						{
							mixedBytes[3][col]=(byte) ((temp7^
									((temp7<<1)^mOfx))^unmixedBytes[1][col]
									^unmixedBytes[2][col]^((temp8<<1)));
						}
						else if(((byte)(temp8 & (byte)(-128))==(byte)-128))
						{
							mixedBytes[3][col]=(byte) ((temp7^
									((temp7<<1)))^unmixedBytes[1][col]
									^unmixedBytes[2][col]^((temp8<<1)^mOfx));
						}
						else
						{
							mixedBytes[3][col]=(byte) ((temp7^
									((temp7<<1)))^unmixedBytes[1][col]
									^unmixedBytes[2][col]^((temp8<<1)));
						}
					
						
					}//end if row3
			}
		return mixedBytes;
	}//end method mixColumn
	
	
	//addRoundKey
	
	public byte[][] addRoundKey(byte[][] unaddedBytes,keyExpands key, int round)
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
		
		System.out.println("Printed Key for round: ");
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
