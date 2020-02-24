package model;

/*

chiffre avec la clé publique
java chiffreRSA fichierClePublique ChaineaChiffrer fichierChiffre

*/

import java.io.*;
import java.nio.file.*;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.KeyFactory;
import java.security.spec.*;
import java.util.Base64;

import javax.crypto.Cipher;

public class chiffreRSA {
    
    public static void main(String [] args) throws Exception {

/*	Morceau de code utile

	// Lecture des octets de la clé privée depuis le fichier
	byte[] bytesPriv = Files.readAllBytes(Paths.get(args[0]));

	// regenere la clé privée en mémoire
	PKCS8EncodedKeySpec ksPriv = new PKCS8EncodedKeySpec(bytesPriv);
	KeyFactory kfPriv = KeyFactory.getInstance("RSA");
	PrivateKey privateKey = kfPriv.generatePrivate(ksPriv);

*/
	// Lecture des octets de la clé publique depuis le fichier
	byte[] bytesPub = Files.readAllBytes(Paths.get(args[0]));
 
	// regenere la clé publique en mémoire
	X509EncodedKeySpec ksPub = new X509EncodedKeySpec(bytesPub);
	KeyFactory kfPub = KeyFactory.getInstance("RSA");
	PublicKey pubKey = kfPub.generatePublic(ksPub);
        	     
	// chiffre le message avec la clé publique   
	byte [] encrypted = encrypt(pubKey, args[1]);          
        System.out.println(new String(encrypted));   

	String outFile = args[2];
	FileOutputStream out = new FileOutputStream(outFile);
	out.write(encrypted);
	out.close();     
    }

public static byte[] encrypt(PublicKey pubKey, String message) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);  

        return cipher.doFinal(message.getBytes());  
    } 
}
