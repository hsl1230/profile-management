package com.telus.dl.profilemanagement.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

@Service
public class CryptService {
    private final String keyString;
    private final String ivString;

    public CryptService(@Value("crypt.key") String keyString, @Value("crypt.iv") String ivString) {
        this.keyString = keyString;
        this.ivString = ivString;
    }

    public String encrypt(Object obj){
        try {
            // wrap key data in Key/IV specs to pass to cipher
            IvParameterSpec ivSpec = new IvParameterSpec(ivString.getBytes());
            // create the cipher with the algorithm you choose
            // see javadoc for Cipher class for more info, e.g.

            DESKeySpec dkey = new  DESKeySpec(keyString.getBytes());
            SecretKeySpec key = new SecretKeySpec(dkey.getKey(), "DES");
            Cipher enCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

            byte[] input = convertToByteArray(obj);
            enCipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
            return Base64Utils.encodeToString(enCipher.doFinal(input));
        } catch (Exception ex) {
            throw new RuntimeException("Error occurred when do encrypt", ex);
        }
    }

    public Object decrypt(Object encrypted) {
        try {
            // wrap key data in Key/IV specs to pass to cipher
            IvParameterSpec ivSpec = new IvParameterSpec(ivString.getBytes());
            // create the cipher with the algorithm you choose
            // see javadoc for Cipher class for more info, e.g.

            DESKeySpec dkey = new  DESKeySpec(keyString.getBytes());
            SecretKeySpec key = new SecretKeySpec(dkey.getKey(), "DES");
            Cipher deCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

            deCipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
            return convertFromByteArray(deCipher.doFinal(Base64Utils.decodeFromString((String)encrypted)));
        } catch (Exception ex) {
            throw new RuntimeException("Error occurred when do encrypt", ex);
        }
    }

    private Object convertFromByteArray(byte[] byteObject) throws IOException,
            ClassNotFoundException {
        ByteArrayInputStream bais;

        ObjectInputStream in;
        bais = new ByteArrayInputStream(byteObject);
        in = new ObjectInputStream(bais);
        Object o = in.readObject();
        in.close();
        return o;
    }

    private byte[] convertToByteArray(Object complexObject) throws IOException {
        ByteArrayOutputStream baos;

        ObjectOutputStream out;

        baos = new ByteArrayOutputStream();

        out = new ObjectOutputStream(baos);

        out.writeObject(complexObject);

        out.close();

        return baos.toByteArray();

    }


}