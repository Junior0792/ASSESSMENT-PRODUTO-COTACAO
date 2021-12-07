package br.edu.infnet.ACME.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;

@Service
public class AmazonClientService {
	
	private AmazonS3 s3client;
	@Value("${aws.key}")
	private String key;
	@Value("${aws.secret}")
	private String secret;
	@Value("${aws.region}")
	private String region;
	@Value("${aws.bucketName}")
	private String bucket;

	@PostConstruct
	private void initializeAmazon() {
	s3client =  AmazonS3ClientBuilder.standard().withRegion(region).withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(key, secret))).build();
	}
	
	public String save(File file) {
		
	String prefixoArquivo = UUID.randomUUID().toString();
	String fileName = prefixoArquivo + "-" + file.getName();
	PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, fileName, file);
	s3client.putObject(putObjectRequest);
	return fileName;
	}

	public String save(MultipartFile multipartFile) {
		try {
			
			File file = convertMultiPartToFile(multipartFile);
	        return save(file);
	        
	        } catch (IOException e) {
	        	
	            e.printStackTrace();
	        }
		
	        return null;
	    }

	 private File convertMultiPartToFile(MultipartFile file) throws IOException {
	 File convFile = new File(file.getOriginalFilename());
	 FileOutputStream fos = new FileOutputStream(convFile);
	 fos.write(file.getBytes());
	 fos.close();
	 return convFile;
	 
	 }

	 public List<String> listar() {
		 
		 List<String> filesNames = new ArrayList<>();
	     ObjectListing objectListing = s3client.listObjects(bucket);
	     List<S3ObjectSummary> objectSummaries = objectListing.getObjectSummaries();
	     if(objectSummaries != null) {
	    	 
	    	 for(S3ObjectSummary summary : objectSummaries) {
	    		 
	    		 String fileName = summary.getKey();
	             filesNames.add(fileName);
	             
	            }
	        }
	     
	     return filesNames;
	    }

	 public void delete(String fileName) {
	 s3client.deleteObject(bucket, fileName);
	 
	 }

	 public File download(String fileName) throws IOException {
		 
		 S3Object object = s3client.getObject(bucket, fileName);
	     S3ObjectInputStream s3is = object.getObjectContent();
	     byte[] bytes = s3is.readAllBytes();
	     File file = File.createTempFile("temp", fileName);
	     FileOutputStream fos = new FileOutputStream(file);
	     fos.write(bytes);
	     s3is.close();
	     fos.close();
	     return file;
	     
	    }

}
