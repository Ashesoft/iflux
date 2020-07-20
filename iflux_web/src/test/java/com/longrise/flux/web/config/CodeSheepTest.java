package com.longrise.flux.web.config;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CodeSheepTest {
    @Autowired
    private StringEncryptor codeSheepEncryptorBean;

    @Test
    public void getEncodePwd(){
        String dbpwd = "root";
        System.err.println(codeSheepEncryptorBean.encrypt(dbpwd));
    }
}
