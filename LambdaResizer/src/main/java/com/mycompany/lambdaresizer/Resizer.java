package com.mycompany.lambdaresizer;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.UUID;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.imgscalr.Scalr;

public class Resizer implements RequestHandler<resizerInput, String>
{

    AmazonS3 s3client;
    @Override
    public String handleRequest(resizerInput i, Context cntxt)
    {
        String resizedurl = createUrl(i, cntxt);
        if (!alreadyExists(resizedurl)) {
            BufferedImage originalImage = readImage(i, cntxt);
            if (originalImage != null) {
                InputStream resizedImage = resizeImage(originalImage, i, cntxt);
                if (resizedImage != null) {
                    if (!storeImageInS3(resizedImage, resizedurl, cntxt)) {
                        return "Failed to store image in S3";
                    }
                    else {
                        return resizedurl;
                    }
                }
                else {
                    return "Failed to resize Image";
                }
            }
            else {
                return "Failed to read Original Image";
            }
        }

        return resizedurl;

    }
    private String createUrl(resizerInput i, Context cntxt)
    {
        String resizedUrl = "";
        String publicUrl = System.getenv("publicurl");
        String fullHash = "" + Math.abs(i.getUrl().hashCode());
        String fileName = "";
        try {
            fileName = Paths.get(new URI(i.getUrl()).getPath()).getFileName().toString();
        }
        catch (URISyntaxException ex) {
            cntxt.getLogger().log("Unable to create url : " + i.getUrl() + " " + ex.getMessage());
        }
        resizedUrl = publicUrl + fileName + "-" + fullHash + "-" + i.getWidth() + "-" + i.getHeight();
        return resizedUrl;
    }

    private BufferedImage readImage(resizerInput i, Context cntxt)
    {
        try {
            return ImageIO.read(new URL(i.getUrl()).openStream());
        }
        catch (IOException ex) {
            cntxt.getLogger().log("Failed to read original url " + i.getUrl() + " " + ex.getMessage());
            return null;
        }
    }

    private InputStream resizeImage(BufferedImage image, resizerInput i, Context cntxt)
    {
        try {
            BufferedImage img = Scalr.resize(image, Scalr.Method.BALANCED, Scalr.Mode.AUTOMATIC, i.getWidth(), i.getHeight(), Scalr.OP_ANTIALIAS);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(img, "gif", os);
            InputStream is = new ByteArrayInputStream(os.toByteArray());
            return is;
        }
        catch (IOException ex) {
            cntxt.getLogger().log("Image Resizing failed : " + i.getUrl() + " " + ex.getMessage());
            return null;
        }
    }

    private AmazonS3 getS3Client()
    {
        if (s3client == null) {
            s3client = AmazonS3ClientBuilder.defaultClient();
        }
        return s3client;
    }

    private String getS3Key(String resizedUrl)
    {
        try {
            return Paths.get(new URI(resizedUrl).getPath()).getFileName().toString();
        }
        catch (URISyntaxException ex) {
            return "";
        }
    }

    private Boolean storeImageInS3(InputStream is, String resizedUrl, Context cntxt)
    {
        String s3Key = getS3Key(resizedUrl);
        String bucketName = System.getenv("bucketname");
        File tempFile = null;
        try {
            tempFile = File.createTempFile(UUID.randomUUID().toString(), ".gif");
            FileUtils.copyInputStreamToFile(is, tempFile);
            PutObjectRequest por = new PutObjectRequest(bucketName, s3Key, tempFile).withCannedAcl(CannedAccessControlList.PublicRead);
            PutObjectResult res = getS3Client().putObject(por);
            cntxt.getLogger().log("Stored in s3 " + bucketName + "/" + s3Key);
        }
        catch (IOException ex) {
            cntxt.getLogger().log("Error creating temp file : " + ex.getMessage());
            return false;
        }
        finally {
            if (tempFile != null) {
                tempFile.delete();

            }
        }
        return true;

    }

    private Boolean alreadyExists(String resizedUrl)
    {
        String bucketname = System.getenv("bucketname");
        try {
            getS3Client().getObjectMetadata(bucketname, getS3Key(resizedUrl));
        }
        catch (AmazonServiceException e) {
            return false;
        }
        return true;
    }


}