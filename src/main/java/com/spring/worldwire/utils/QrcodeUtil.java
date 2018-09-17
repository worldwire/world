package com.spring.worldwire.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;

/**
 * Desc: Desc
 * User: luxun
 * Date: 2018/9/17 16:45
 * To change this template use File | Settings | File and Code Templates | Includes | File Header.
 */
@Component
public class QrcodeUtil {

    private final int qrCodeSize = 300;
    private final String format = "png";

    private static final int BLACK = 0xFF000000;//用于设置图案的颜色
    private static final int WHITE = 0xFFFFFFFF; //用于背景色

    @Value("${file.basedir}")
    private String baseFilePath;

    public String GenerateDefaultQrcode(String content) {
        String fileName = System.currentTimeMillis() + ".png";
        File f = new File(baseFilePath + File.separator + fileName);
        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdirs();
        }
        try {
            FileOutputStream outputStream = new FileOutputStream(f);
            HashMap hints = new HashMap();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
            hints.put(EncodeHintType.MARGIN, 2);
            //生成二维码
            try {
                //OutputStream stream = new OutputStreamWriter();
                BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, qrCodeSize, qrCodeSize, hints);
                MatrixToImageWriter.writeToFile(bitMatrix, format, f);
            } catch (Exception e) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
