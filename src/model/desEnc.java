package model;

/*

chiffrement
Java desEnc motDePasse TexteaChiffrerEnClair

sortie: leTexteChiffré

Java desDec motdepasse leTextechiffré
le texteChiffré est copié collé lors de la phase de chiffrement

attention à la longueur du mot de passe (8 car pour DES, 16 pour AEs, 24 pour 3DES), on utilise DESede, la version SUN de 3DES
*/

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;
import javax.crypto.spec.SecretKeySpec;


public class desEnc {
  public static void main(String[] argv) throws Exception {
  //AES: clé de 16 octets, 8 pour DES 24 pour 3DES (DESede)

	String mykey=argv[0];
        SecretKey key = new SecretKeySpec(mykey.getBytes(), "DESede"); 
	System.out.println(key);
    	DesEncrypter encrypter = new DesEncrypter(key);
	String encrypted = encrypter.encrypt(argv[1]);
	System.out.println(encrypted);   
  }
}
