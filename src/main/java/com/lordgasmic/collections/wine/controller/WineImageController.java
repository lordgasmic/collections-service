package com.lordgasmic.collections.wine.controller;

import com.lordgasmic.collections.wine.models.WineResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@RestController
@Slf4j
public class WineImageController {

    @PutMapping("/api/v1/wineImages")
    public Object addWineImage(@RequestParam("imageFile") final MultipartFile imageFile) throws IOException {
        log.info("Image Upload:");
        log.info("Filename: " + imageFile.getOriginalFilename());
        log.info("Filename: " + imageFile.getName());
        log.info("File Bytes: " + imageFile.getBytes().length);
        log.info("File size: " + imageFile.getSize());
        log.info("Content-type: " + imageFile.getContentType());

        decompressBytes(compressBytes(imageFile.getBytes()));

        return new WineResponse();
    }

    // compress the image bytes before storing it in the database
    public static byte[] compressBytes(final byte[] data) {
        final Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        final byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            final int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (final IOException e) {
        }
        log.info("Compressed Image Byte Size - " + outputStream.toByteArray().length);

        return outputStream.toByteArray();
    }

    // uncompress the image bytes before returning it to the angular application
    public static byte[] decompressBytes(final byte[] data) {
        final Inflater inflater = new Inflater();
        inflater.setInput(data);
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        final byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                final int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (final IOException | DataFormatException ioe) {
            ioe.printStackTrace();
        }
        log.info("Decompressed Image Byte Size: " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }
}
