/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aesmain;

//import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
/**
 *
 * @author Zayed news
 */
public class AesMain extends JFrame {
	
	private final JLabel plainLabel=new JLabel("Enter Your Plaintext: ");
	protected final JTextField plainTextField=new JTextField(20);
	private JLabel cipherLabel, decipherLabel, cipherLabelName, decipherLabelName;
	private final JButton cipherButton, decipherButton;
	
	
	private String cipherText="";
	private String outputCipher="";
	private String plaintext="";
	private String outputDecipherTextInHex="";
	private int len;
	
	
	public AesMain()
	{
		super("Abvanced Encryption Standard (AES)");
		
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450, 350);
		setVisible(true);
                setLocationRelativeTo(null);
		
		plainLabel.setLabelFor(plainTextField);
		plainLabel.setHorizontalAlignment(JLabel.LEFT);
		plainLabel.setBounds(20, 50, 180, 20);
		add(plainLabel);
		//plainTextField.setBounds(200,150,100,100);
	
		plainTextField.setVisible(true);
		plainTextField.setEditable(true);
                plainTextField.setBounds(200, 50, 180, 20);
		add(plainTextField);
		
		cipherButton=new JButton("Encrypt");
                cipherButton.setBounds(20, 80, 180, 20);
		cipherLabel=new JLabel();
                cipherLabel.setBounds(110, 110, 220, 20);
                
                
                cipherLabelName=new JLabel("Encrypt Value:");
                cipherLabelName.setBounds(20, 110, 90, 20);
                
		decipherButton=new JButton("Decrypt");
                decipherButton.setBounds(220, 80, 180, 20);
		decipherLabel=new JLabel();
		decipherLabel.setBounds(110, 170, 220, 20);
                
                decipherLabelName=new JLabel("Decrypt Value:");
		decipherLabelName.setBounds(20, 170, 90, 20);
                
                
		add(cipherButton);
		add(cipherLabel);
		add(decipherButton);
		add(decipherLabel);
                
                add(cipherLabelName);
                add(decipherLabelName);
		
		
		cipherButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					final String plainText=plainTextField.getText();
					System.out.println(plainText);
					String hexPlainText=convertion.asciiToHex(plainText);
					System.out.println("Hex PlainText: \n"+hexPlainText);
					
					 len=hexPlainText.length()+32-(hexPlainText.length()%32);
					int zeroCount=len-hexPlainText.length()-2;
					String zeroCountStr;
					if(zeroCount<10)
						zeroCountStr="0"+Integer.toString(zeroCount);
					else
						zeroCountStr=Integer.toString(zeroCount);
					StringBuilder sb=new StringBuilder(hexPlainText);
					
					for(int i=hexPlainText.length();i<len-2;i++)
						{
							sb.insert(i, "0");
						}
					
					
					hexPlainText=sb.toString().concat(zeroCountStr);
					System.out.println(hexPlainText);
					
					//substring+forloop
					
					
					for(int i=0; i<len;i=i+32)
					{
						//System.out.println(i);
						String hexSubPlainText=hexPlainText.substring(i, i+32);
						//System.out.println(hexSubPlainText);
						cipherText=encryption.encrypt(hexSubPlainText);
						outputCipher+=cipherText;
//						System.out.println("Cipher Text: ");
//						System.out.println(cipherText);
						
					}
					String hhoutk=convertion.hexToASCII(outputCipher);
                                        System.out.println("testing Text: "+hhoutk);
					//cipherLabel.setText(outputCipher);//change
                                        cipherLabel.setText(hhoutk);
				} catch (Exception e2) {
					
					JOptionPane.showMessageDialog(null, "TextArea not get", "Eroor",JOptionPane.ERROR_MESSAGE);
					
				}
				
			}
		});
		
		
		decipherButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					 String encryptedText=outputCipher;
					 
					 for(int i=0;i<len;i+=32)
					 {
						 String subCipherText=encryptedText.substring(i, i+32);
						 String subPlianText=decryption.decrypt(subCipherText);
						 outputDecipherTextInHex+=subPlianText;
					 }
					 
//					 System.out.println("After decipher:\n"+outputDecipherTextInHex);
//					 System.out.println("length is :"+outputDecipherTextInHex.length());
					 
					
					 String howManyZero=outputDecipherTextInHex.substring(outputDecipherTextInHex.length()-2, outputDecipherTextInHex.length());
					//System.out.println(howManyZero);
					 int numberOfZero=Integer.parseInt(howManyZero);
					// System.out.println(numberOfZero);
					 int index=outputDecipherTextInHex.length()-1-numberOfZero-2;
					// System.out.println(index);
					 String decipherTextInHex=outputDecipherTextInHex.substring(0, index+1);
					 String plainTextFinal=convertion.hexToASCII(decipherTextInHex);
					// System.out.println("Ascii :\n"+plainTextnotFinal);
					decipherLabel.setText(plainTextFinal);
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "TextArea not get", "Eroor",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
	
	public static void main(String[] args) {
		new AesMain().setVisible(true);//counter er value non static korte hobe
		

	}

}
