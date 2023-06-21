package com.cypcode.DBFileStore.commons;

import java.io.*;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

public class AWSCloudUtil {
	
	private AWSCredentials awsCredentials(String accessKey, String secretKey) {
		AWSCredentials credentials = new BasicAWSCredentials(
				accessKey, 
				secretKey
				);
		return credentials;
	}
	
	private AmazonS3 awsS3ClientBuilder(String accessKey, String secretKey) {
		AmazonS3 s3client = AmazonS3ClientBuilder
				  .standard()
				  .withCredentials(new AWSStaticCredentialsProvider(awsCredentials(accessKey, secretKey)))
				  .withRegion(Regions.SA_EAST_1)
				  .build();
		return s3client;
	}
	
	public void uploadFileToS3(String filename, byte[] fileBytes, String accessKey, String secretKey, String bucket) {
		AmazonS3 s3client = awsS3ClientBuilder(accessKey, secretKey);
		
		File file = new File(filename);

		try (OutputStream os = new FileOutputStream(file)) {
		    os.write(fileBytes);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		s3client.putObject(bucket, filename, file);		
	}
	
	public S3ObjectInputStream downloadFileFromS3(String filename, String accessKey, String secretKey, String bucket) {
		AmazonS3 s3client = awsS3ClientBuilder(accessKey, secretKey);
		S3Object s3object = s3client.getObject(bucket, filename);
		S3ObjectInputStream inputStream = s3object.getObjectContent();
		return inputStream;
	}
}
