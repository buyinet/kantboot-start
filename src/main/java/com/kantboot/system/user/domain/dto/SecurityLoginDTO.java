package com.kantboot.system.user.domain.dto;

import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import lombok.Data;

/**
 * 登录dto
 * @author 方某方
 */
@Data
public class SecurityLoginDTO {

    private String username;

    private String password;

    /**
     * 账号公钥
     */
    private String usernamePublicKey;

    /**
     * 密码公钥
     */
    private String passwordPublicKey;

    public static void main(String[] args) {

        RSA rsa = new RSA(
                "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAK7EE8hZmLDVAPk1bmT8QRSvCkqq9Ze3sh2I9GYBhuQaRvRPzE6Rn9vDBTucBTiRsbUOGj7dSgq3YHzjlcwc7+PkloaN/ji7jnar+Cg5n+Sg6JCy3bmdYovZyiRjUXp+W2UVjIyydqdlJsfrwjBYOLN4Fo5wYoHAdSI9PGHconYLAgMBAAECgYAHPs/HJZfD9QC2VDXAcAjOr7cQg2ftI0t6F/xx4ixSdphsdoZ012q2NK6Z2w0IXFnutKf35Tq1mQaiBtll/ekgCum00a5kUxte0tgbU9mpT5CDszPyuBFMcTxNAnvqS6boCX1012VSAGOA80uC1HTHKbkPI2Ra9GBWp8u1QN/VkQJBAO0zBlqff/MRg/ZHR0GinNI++WWcq8F9bJVYTlAcpvvepeS79rn3brqsJScp/vdBpM1ggoT9ONW943o7mbTDIikCQQC8njnf9st8euG6yRPaN2ZoctLIPxU07sdnDFYkdLqtDnFTiTqW9l/LmPF2QL5WWt+ygrV5z+k2iVs0OR8pXyUTAkBN8wO2ik4A8hOcvoXTMv44NYu98cOb3Xzug0uRFilmAiAgUAjfyklwPRwkh6+LT945w+qAi7q+ux1jUOzNM4cpAkAH+CN33ASS+Noquy40jRytkxRm04uA1gnkx7eoCKqOf0q4fdbU5An+HzMsjOW93k8PSsiCIg+3dyw8U6GGbPezAkBeytqGczDSHUNL2IEysu6fqnBUU+v9bsclErnt1yBfK7lHOla1s4E3WV/DLW0iLAuGlnHlRdy64Hch2FgkKP05"
                ,"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCuxBPIWZiw1QD5NW5k/EEUrwpKqvWXt7IdiPRmAYbkGkb0T8xOkZ/bwwU7nAU4kbG1Dho+3UoKt2B845XMHO/j5JaGjf44u452q/goOZ/koOiQst25nWKL2cokY1F6fltlFYyMsnanZSbH68IwWDizeBaOcGKBwHUiPTxh3KJ2CwIDAQAB");

        String username = "1234567";
        // 公钥加密
        String encryptHex = rsa.encryptHex(username, KeyType.PublicKey);
        System.out.println("encryptHex = " + encryptHex);
        // 私钥解密
        String decryptStr = rsa.decryptStr(encryptHex, KeyType.PrivateKey);
        System.out.println("decryptStr = " + decryptStr);

    }

}
