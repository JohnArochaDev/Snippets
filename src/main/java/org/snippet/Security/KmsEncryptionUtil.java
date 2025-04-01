package org.snippet.Security;

import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.kms.KmsClient;
import software.amazon.awssdk.services.kms.model.EncryptRequest;
import software.amazon.awssdk.services.kms.model.EncryptResponse;
import software.amazon.awssdk.services.kms.model.DecryptRequest;
import software.amazon.awssdk.services.kms.model.DecryptResponse;

import java.util.Base64;

public class KmsEncryptionUtil {

    private static final KmsClient kmsClient = KmsClient.create();

    public static String encrypt(String plaintext, String keyId) {
        SdkBytes plaintextBytes = SdkBytes.fromUtf8String(plaintext);
        EncryptRequest encryptRequest = EncryptRequest.builder()
            .keyId(keyId)
            .plaintext(plaintextBytes)
            .build();
        EncryptResponse encryptResponse = kmsClient.encrypt(encryptRequest);

        // Convert the ciphertext to a Base64-encoded string
        byte[] encryptedBytes = encryptResponse.ciphertextBlob().asByteArray();
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedData) {
        // Decode the Base64-encoded string into a byte array
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedData);
        SdkBytes encryptedSdkBytes = SdkBytes.fromByteArray(encryptedBytes);

        DecryptRequest decryptRequest = DecryptRequest.builder()
            .ciphertextBlob(encryptedSdkBytes)
            .build();
        DecryptResponse decryptResponse = kmsClient.decrypt(decryptRequest);

        // Convert the decrypted plaintext back to a UTF-8 string
        return decryptResponse.plaintext().asUtf8String();
    }
}