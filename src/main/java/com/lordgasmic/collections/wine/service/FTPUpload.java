package com.lordgasmic.collections.wine.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class FTPUpload {

    @Autowired
    private ImageProcessor imageProcessor;

    public void doUpload(final MultipartFile file) throws IOException {
        final String shortMimeType = Objects.requireNonNull(file.getContentType()).substring(file.getContentType().indexOf('/') + 1);
        final String dir = UUID.randomUUID().toString();
        upload(file.getName(), dir, file.getBytes());
        final byte[] resizeBytes = imageProcessor.resizeImage(file.getBytes(), shortMimeType);

        log.info("file name: " + file.getName());
        //        final String name = file.getName().substring(0, file.getName().lastIndexOf('.')) + "_tb" + file.getName()
        //                                                                                                       .substring(file.getName()
        //                                                                                                                      .lastIndexOf('.' + 1));
        //        upload(name, dir, resizeBytes);
    }

    public static void upload(final String fileName, final String directory, final byte[] bytes) {
        //        JSch jsch = new JSch();
        //        jsch.setKnownHosts("");
        //        Session session = jsch.getSession("", "", 22);
        //
    }
}
