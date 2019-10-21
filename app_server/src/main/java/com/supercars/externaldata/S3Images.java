/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supercars.externaldata;

import com.amazonaws.SdkClientException;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.supercars.logging.CarLogger;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

/**
 *
 * @author tombatchelor
 */
public class S3Images {

    static final Regions CLIENT_REGION = Regions.US_WEST_2;
    static final String BUCKET_NAME = "carimages-observeinc";

    private final static Logger logger = Logger.getLogger(S3Images.class.getName());
    
    static {
        CarLogger.setup(S3Images.class.getName());
    }
    
    public static BufferedImage getImage(String imageName) {
        logger.log(Level.FINE, "Getting image: {0} from S3 bucket: " + BUCKET_NAME, imageName);
        BufferedImage image = null;
        try {
            S3Object object = getClient().getObject(BUCKET_NAME, imageName);
            ImageInputStream iin = ImageIO.createImageInputStream(object.getObjectContent());
            image = ImageIO.read(iin);
            logger.log(Level.FINE, "Get image {0} successful", imageName);
        } catch (IOException | SdkClientException ex) {
            logger.log(Level.SEVERE, "Error getting image: " + imageName + " From Bucket: " + BUCKET_NAME, ex);
        }

        return image;
    }

    public static void saveImage(File image, String imageName) {
        logger.log(Level.FINE,"Saving image: {0} to: " + BUCKET_NAME, imageName);
        getClient().putObject(BUCKET_NAME, imageName, image);
        logger.log(Level.FINE,"Save image: {0} successful", imageName);
    }

    private static AmazonS3 getClient() {
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new EnvironmentVariableCredentialsProvider())
                .withRegion(CLIENT_REGION)
                .build();
    }
}
