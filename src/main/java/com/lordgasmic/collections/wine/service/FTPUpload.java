package com.lordgasmic.collections.wine.service;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class FTPUpload {

    @Autowired
    private ImageProcessor imageProcessor;

    public void doUpload(final MultipartFile file) throws IOException, JSchException, SftpException {
        final String shortMimeType = Objects.requireNonNull(file.getContentType()).substring(file.getContentType().indexOf('/') + 1);
        final String dir = UUID.randomUUID().toString();
        final String filename = UUID.randomUUID().toString();
        final String fullFileName = filename + "." + shortMimeType;
        final String tbFileName = filename + "_tb." + shortMimeType;
        upload(fullFileName, dir, file.getBytes());
        final byte[] resizeBytes = imageProcessor.resizeImage(file.getBytes(), shortMimeType);
        upload(tbFileName, dir, resizeBytes);
    }

    public static void upload(final String fileName, final String directory, final byte[] bytes) throws JSchException, SftpException {
        final JSch jsch = new JSch();
        jsch.setKnownHosts("/root/.ssh/known_hosts");
        final Session session = jsch.getSession("ftp_user", "172.16.0.51", 22);
        session.setPassword("ftp_user");
        session.connect();
        final Channel sftp = session.openChannel("sftp");
        sftp.connect();
        final ChannelSftp channelSftp = (ChannelSftp) sftp;
        channelSftp.mkdir(directory);
        final InputStream is = new ByteArrayInputStream(bytes);
        channelSftp.put(is, directory + "/" + fileName);
        channelSftp.exit();
        session.disconnect();
    }
}
