package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class CryptDecrypt {
	public static Key myKey;
	
	public CryptDecrypt(String clef) {
		CryptDecrypt.myKey = createKey(clef, "DESede");
	}
	
	private static String traitement(int mode, Key cle,String algorithme,String transformation,File fichierEntree,File fichierSortie){
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
			return "Erreur lors du traitement";
		} catch (NoSuchAlgorithmException e) {
			return "Ce fichier est déjà chiffré ou déjà déchiffré";
		} catch (NoSuchPaddingException e) {
			return "Erreur lors du traitement";
		} catch (InvalidKeyException e) {
			return "Erreur lors du traitement";
		} catch (IllegalBlockSizeException e) {
			return "Erreur lors du traitement";
		} catch (BadPaddingException e) {
			return "Ce fichier n'est pas crypté";
		}
		return "";
	}

	public static String encrypt(Key key, String algorithm, File LinkInputFile, boolean overwrite, File newLinkInputFileIfNotOverwrite) {
		String msgError="";
		if(overwrite) {
			msgError=traitement(Cipher.ENCRYPT_MODE, key,algorithm,algorithm, LinkInputFile, LinkInputFile);
		}else {
			try {
				msgError=traitement(Cipher.ENCRYPT_MODE, key,algorithm,algorithm, LinkInputFile, newLinkInputFileIfNotOverwrite);
			}catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
		return msgError;
	}

	public static String decrypt(Key key, String algorithm, File LinkInputFile, boolean overwrite, File newLinkInputFileIfNotOverwrite) {
		String msgError="";
		if(overwrite) {
			msgError=traitement(Cipher.DECRYPT_MODE, key,algorithm,algorithm, LinkInputFile, LinkInputFile);
		}else {
			try {
				msgError=traitement(Cipher.DECRYPT_MODE, key,algorithm,algorithm, LinkInputFile, newLinkInputFileIfNotOverwrite);

			}catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
		return msgError;
	}

	private static Key createKey(String cle, String algorithme) {
		if(verifyKeyLength(algorithme, cle)) {
			try{
				Key cleSecret = new SecretKeySpec(cle.getBytes(), algorithme);
				return cleSecret;
			}catch (Exception e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	private static boolean verifyKeyLength(String algorithm, String key) {
		String msg = "longueur de la clé non valide \n";
		switch (algorithm) {
		case "AES":
			if(key.getBytes().length==16) return true;
			else System.out.println(msg+"Entrez une clé de 16 octets");

			break;

		case "DES":
			if(key.getBytes().length==8) return true;
			else System.out.println(msg+"Entrez une clé de 8 octets");

			break;

		case "DESede" :
			if(key.getBytes().length==24) return true;
			else System.out.println(msg+"Entrez une clé de 24 octets");
			break;

		default :
			System.out.println("type d'algorithm non valide");
		}
		return false;
	}

	/*public static void main(String[] args) {


		Key sc = createKey("aaaaaaaaaaaaaaaaaaaaaaaa", "DESede");
		decrypt(sc, "DESede", new File("./res/document.txt"), true, null);
	}*/

}
