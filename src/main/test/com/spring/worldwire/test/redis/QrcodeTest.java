package com.spring.worldwire.test.redis;


import com.spring.worldwire.utils.QrcodeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Desc: Desc
 * User: luxun
 * Date: 2018/9/17 17:05
 * To change this template use File | Settings | File and Code Templates | Includes | File Header.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class QrcodeTest {

    @Autowired
    private QrcodeUtil qrcodeUtil;

    @Test
    public void generate() {
        System.out.println(qrcodeUtil.GenerateDefaultQrcode("hahahah"));
    }

}
