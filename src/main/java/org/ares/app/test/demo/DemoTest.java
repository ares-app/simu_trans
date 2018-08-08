package org.ares.app.test.demo;

import javax.annotation.Resource;

import org.ares.app.test.TestappApplication;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) 
@SpringBootTest(classes = TestappApplication.class)
public class DemoTest {

    @Test
    public void encryptPwd() {
        String plaintext="root";  
        String ciphertext=jse.encrypt(plaintext);  
        System.out.println(plaintext + " : " + ciphertext);
        System.out.println(jse.decrypt(ciphertext));
    }
    
    @Resource
    StringEncryptor jse;
    
    public static void main(String[] args){
    	StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();  
        EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();  
        config.setAlgorithm("PBEWithMD5AndDES");  
        config.setPassword("ares@2017");  
        encryptor.setConfig(config);
    	String plaintext="12#456";  
        String ciphertext=encryptor.encrypt(plaintext);  
        System.out.println(plaintext + " : " + ciphertext);
        System.out.println(encryptor.decrypt(ciphertext));
    }
}
