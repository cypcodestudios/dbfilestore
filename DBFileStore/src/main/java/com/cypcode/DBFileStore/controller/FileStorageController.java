package com.cypcode.DBFileStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cypcode.DBFileStore.entity.FileStorage;
import com.cypcode.DBFileStore.entity.dto.FileStorageDTO;
import com.cypcode.DBFileStore.service.FileStoreServiceImpl;

@RestController
@RequestMapping("file")
public class FileStorageController {
	@Autowired
	FileStoreServiceImpl fsServiceImpl;

	@PostMapping("multipart")
	public ResponseEntity<?> uploadMulitpartFile(@RequestParam("file") MultipartFile data) {
		String response = fsServiceImpl.uploadMulitpartFile(data);

		return ResponseEntity.ok(response);
	}
	
	@PostMapping("multipart/s3")
	public ResponseEntity<?> uploadMulitpartFileToS3(@RequestParam("file") MultipartFile data) {
		String response = fsServiceImpl.uploadFileToS3(data);

		return ResponseEntity.ok(response);
	}

	@PostMapping("base64")
	public ResponseEntity<?> uploadBase64File(@RequestBody FileStorageDTO data) {
		String response = fsServiceImpl.uploadBase64File(data.getFilename(), data.getFiletype(), data.getFilebase64());

		return ResponseEntity.ok(response);
	}

	@PostMapping(value = "upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<?> upload(@RequestParam(value = "file", required = false) MultipartFile multipartData,
			@RequestBody(required = false) FileStorageDTO base64Data) {

		if (base64Data == null) {
			String response = fsServiceImpl.uploadMulitpartFile(multipartData);
			return ResponseEntity.ok(response);
		} else {
			String response = fsServiceImpl.uploadBase64File(base64Data.getFilename(), base64Data.getFiletype(),
					base64Data.getFilebase64());
			return ResponseEntity.ok(response);
		}
	}

	@GetMapping("multipart/{filename}")
	public ResponseEntity<?> downloadMulitpartFile(@PathVariable String filename) {
		FileStorage fileDetails = fsServiceImpl.retreiveFile(filename);
		byte[] file = fsServiceImpl.downloadMulitpartFile(filename);

		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf(fileDetails.getFiletype()))
				.body(file);
	}
	
	@GetMapping("multipart/s3/{filename}")
	public ResponseEntity<?> downloadMulitpartS3File(@PathVariable String filename) {
		FileStorage fileDetails = fsServiceImpl.retreiveFile(filename);
		byte[] file = fsServiceImpl.downloadS3File(filename);

		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf(fileDetails.getFiletype()))
				.body(file);
	}

	@GetMapping("base64/{filename}")
	public ResponseEntity<?> downloadBase64File(@PathVariable String filename) {
		FileStorage fileDetails = fsServiceImpl.retreiveFile(filename);
		byte[] file = fsServiceImpl.downloadBase64File(filename);

		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf(fileDetails.getFiletype()))
				.body(file);
	}

	@GetMapping("download/{filename}")
	public ResponseEntity<?> download(@PathVariable String filename) {
		FileStorage fileDetails = fsServiceImpl.retreiveFile(filename);
		if (fileDetails.getFilebyte() != null) {
			byte[] file = fsServiceImpl.downloadMulitpartFile(filename);

			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf(fileDetails.getFiletype()))
					.body(file);
		} else {
			byte[] file = fsServiceImpl.downloadBase64File(filename);

			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf(fileDetails.getFiletype()))
					.body(file);
		}
	}

	@GetMapping("base64/{filename}/details")
	public ResponseEntity<?> getBase64FileDetails(@PathVariable String filename) {
		FileStorage fileDetails = fsServiceImpl.retreiveFile(filename);

		return ResponseEntity.status(HttpStatus.OK).body(fileDetails);
	}
}
