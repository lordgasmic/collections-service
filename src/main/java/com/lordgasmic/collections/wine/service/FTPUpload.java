package com.lordgasmic.collections.wine.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Component
@Slf4j
public class FTPUpload {

    @Autowired
    private ImageProcessor imageProcessor;

    public void doUpload(final MultipartFile file) throws IOException {
        final String shortMimeType = Objects.requireNonNull(file.getContentType()).substring(file.getContentType().indexOf('/') + 1);
        final String dir = UUID.randomUUID().toString();
        upload(file.getName(), dir, file.getBytes());
        final byte[] resizeBytes = imageProcessor.resizeImage(file.getBytes(), shortMimeType);

        final String name = file.getName().substring(0, file.getName().lastIndexOf('.')) + "_tb" + file.getName()
                                                                                                       .substring(file.getName()
                                                                                                                      .lastIndexOf('.' + 1));
        upload(name, dir, resizeBytes);
    }

    public static void upload(final String fileName, final String directory, final byte[] bytes) {
        final FTPClient client = new FTPClient();
        ByteArrayInputStream inputStream = null;
        try {
            client.connect("172.16.0.51");
            client.login("ftp_user", "ftp_user");
            inputStream = new ByteArrayInputStream(bytes);

            client.makeDirectory(directory);
            client.storeFile(fileName, inputStream);
            client.logout();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                client.disconnect();
            } catch (final IOException e) {
                log.error("error trying to close input stream", e);
            }
        }
    }
}
