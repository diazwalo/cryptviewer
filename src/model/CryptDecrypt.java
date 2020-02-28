package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class CryptDecrypt {

	private static void traitement(int mode, Key cle,String algorithme,String transformation,File fichierEntree,File fichierSortie){
		try {
			Cipher cipher;
			cipher = Cipher.getInstance(transformation);
			cipher.init(mode, cle);
			FileInputStream inputStream = new FileInputStream(fichierEntree);
			byte[] inputBytes = new byte[(int) fichierEntree.length()];
			inputStream.read(inputBytes);

			byte[] outputBytes = cipher.doFinal(inputBytes);

			FileOutputStream outputStream = new FileOutputStream(fichierSortie);
			outputStream.write(outputBytes);

			inputStream.close();
			outputStream.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
	}

	private static void encrypt(Key key, String algorithm, File LinkInputFile, boolean overwrite, File newLinkInputFileIfNotOverwrite) {
		if(overwrite) {
			traitement(Cipher.ENCRYPT_MODE, key,algorithm,algorithm, LinkInputFile, LinkInputFile);
		}else {
			try {
				traitement(Cipher.ENCRYPT_MODE, key,algorithm,algorithm, LinkInputFile, newLinkInputFileIfNotOverwrite);
			}catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
	}

	private static void decrypt(Key key, String algorithm, File LinkInputFile, boolean overwrite, File newLinkInputFileIfNotOverwrite) {
		if(overwrite) {
			traitement(Cipher.DECRYPT_MODE, key,algorithm,algorithm, LinkInputFile, LinkInputFile);
		}else {
			try {
				traitement(Cipher.DECRYPT_MODE, key,algorithm,algorithm, LinkInputFile, newLinkInputFileIfNotOverwrite);

			}catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
	}

	private static Key createKey(String cle, String algorithme) {
		try{
			Key cleSecret = new SecretKeySpec(cle.getBytes(), algorithme);
			return cleSecret;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static boolean verifyKeyLength(String algorithm, String key) {
		switch (algorithm) {
		case "AES":
			if(key.getBytes().length==16) return true;
			break;

		case "DES":
			break;
			
		case "DESede" :
			break;
			
			default :
				System.out.println();
		}
		
		if(algorithm.equals("AES") && key.getBytes().length==16) return true;
		if(algorithm.equals("AES") && key.getBytes().length==16) return true;
		else return false;
	}

	public static void main(String[] args) {


		Key sc = createKey("aaaaaaaa", "DES");
		//decrypt(sc, "DES", new File("./res/document.txt"), true, null);
	}

}
