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
public class inverseMixColumns {
	private byte mOfx=27; //as 00011011
	
	private byte[][] mixedBytes=new byte[4][4];
	//mixColumns
		public byte[][] invMixColumns(byte[][] unmixedBytes)
		{	
			
			for(int row=0;row<4;row++)
				for(int col=0;col<4;col++)
				{
					//System.out.println(unmixedBytes[0][0]<<1);
					if(row==0)    //0e.S0c xor 0b.S1c xor 0d.S2c xor 09.S3c
						{
							byte s0c=unmixedBytes[0][col];
							byte s1c=unmixedBytes[1][col];
							byte s2c=unmixedBytes[2][col];
							byte s3c=unmixedBytes[3][col];
							
							byte s0c0e=(byte) (xtime(s0c)^xtime(xtime(s0c))^xtime(xtime(xtime(s0c))));
							byte s1c0b=(byte) (s1c^xtime(s1c)^xtime(xtime(xtime(s1c))));
							byte s2c0d=(byte) (s2c^xtime(xtime(s2c))^xtime(xtime(xtime(s2c))));
							byte s3c09=(byte) (s3c^xtime(xtime(xtime(s3c))));
							
							mixedBytes[0][col]=(byte)(s0c0e^s1c0b^s2c0d^s3c09);				
							
											
						}//outer if for row=1
					
					if(row==1)    // 09.S0c xor 0e.S1c xor 0b.S2c xor 0d.S3c
						{
						byte s0c=unmixedBytes[0][col];
						byte s1c=unmixedBytes[1][col];
						byte s2c=unmixedBytes[2][col];
						byte s3c=unmixedBytes[3][col];
						
						byte s0c09=(byte) (s0c^xtime(xtime(xtime(s0c))));
						byte s1c0e=(byte) (xtime(s1c)^xtime(xtime(s1c))^xtime(xtime(xtime(s1c))));
						byte s2c0b=(byte) (s2c^xtime(s2c)^xtime(xtime(xtime(s2c))));
						byte s3c0d=(byte) (s3c^xtime(xtime(s3c))^xtime(xtime(xtime(s3c))));
						
						
						mixedBytes[1][col]=(byte)(s0c09^s1c0e^s2c0b^s3c0d);
						}//end row1 if
						
					if(row==2)    // 0d.S0c xor 09.S1c xor 0e.S2c xor 0b.S3c
						{
						byte s0c=unmixedBytes[0][col];
						byte s1c=unmixedBytes[1][col];
						byte s2c=unmixedBytes[2][col];
						byte s3c=unmixedBytes[3][col];
						
						byte s0c0d=(byte) (s0c^xtime(xtime(s0c))^xtime(xtime(xtime(s0c))));
						byte s1c09=(byte) (s1c^xtime(xtime(xtime(s1c))));
						byte s2c0e=(byte) (xtime(s2c)^xtime(xtime(s2c))^xtime(xtime(xtime(s2c))));
						byte s3c0b=(byte) (s3c^xtime(s3c)^xtime(xtime(xtime(s3c))));
						
						
						
						mixedBytes[2][col]=(byte)(s0c0d^s1c09^s2c0e^s3c0b);
						}//end if row2
						
					if(row==3)   // 0b.S0c xor 0d.S1c xor 09.S2c xor 0e.S3c
						{
						byte s0c=unmixedBytes[0][col];
						byte s1c=unmixedBytes[1][col];
						byte s2c=unmixedBytes[2][col];
						byte s3c=unmixedBytes[3][col];
						
						byte s0c0b=(byte) (s0c^xtime(s0c)^xtime(xtime(xtime(s0c))));
						byte s1c0d=(byte) (s1c^xtime(xtime(s1c))^xtime(xtime(xtime(s1c))));
						byte s2c09=(byte) (s2c^xtime(xtime(xtime(s2c))));
						byte s3c0e=(byte) (xtime(s3c)^xtime(xtime(s3c))^xtime(xtime(xtime(s3c))));
						
											
						mixedBytes[3][col]=(byte)(s0c0b^s1c0d^s2c09^s3c0e);
							
						}//end if row3
				}//for
			
			return mixedBytes;
		
		}//end method mixColumn
		
		
		private byte xtime(byte s)
		{
			if((byte)(s&(byte)-128)==(byte)-128)
			
				return (byte) ((s<<1)^mOfx);
			else
				
				return (byte) (s<<1);
		}
		
		

}
