package com.app.otp;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;

import io.nayuki.qrcodegen.QrCode;
import io.nayuki.qrcodegen.QrCode.Ecc;

public class QRUtils {
	
	public static BufferedImage generateQrCode(String text) {
        // Create a QR code with the given text and a low error correction level
        QrCode qrCode = QrCode.encodeText(text, Ecc.LOW);
        
        // Convert the QR code to a BufferedImage
        int size = qrCode.size*15;
        
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
        
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                image.setRGB(x, y, qrCode.getModule(x/7, y/7) ? 0x000000 : 0xFFFFFF); // Black for true, white for false
            }
        }      
        return image;
    }
	
	public static String generateQrCodeBase64(String text) throws IOException {
        BufferedImage qrCodeImage = generateQrCode(text);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(qrCodeImage, "png", baos);
        baos.flush();
        byte[] imageInByte = baos.toByteArray();
        baos.close();

        return Base64.getEncoder().encodeToString(imageInByte);
    }
	
	
	 public static BufferedImage generateQrCodeImage(String text) {
	        // Create a QR code with the given text and a low error correction level
	        QrCode qrCode = QrCode.encodeText(text, Ecc.LOW);

	        // Convert the QR code to a BufferedImage
	        int size = qrCode.size * 15;

	        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);

	        for (int y = 0; y < size; y++) {
	            for (int x = 0; x < size; x++) {
	                image.setRGB(x, y, qrCode.getModule(x / 7, y / 7) ? 0x000000 : 0xFFFFFF); // Black for true, white for false
	            }
	        }
	        return image;
	    }
	
}
