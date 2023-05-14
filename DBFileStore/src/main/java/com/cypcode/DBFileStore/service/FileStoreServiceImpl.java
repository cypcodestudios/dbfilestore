package com.cypcode.DBFileStore.service;

import java.io.IOException;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cypcode.DBFileStore.commons.FileStorageUtil;
import com.cypcode.DBFileStore.entity.FileStorage;

@Service
public class FileStoreServiceImpl {
	@Autowired
	FileStoreService fsService;

	public String uploadMulitpartFile(MultipartFile data) {
		FileStorage file = new FileStorage();
		file.setFilename(data.getOriginalFilename());
		file.setFiletype(data.getContentType());
		try {
			file.setFilebyte(FileStorageUtil.compressFile(data.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		FileStorage newFile = fsService.persistFile(file);
		if(newFile != null) {
			return String.format("File %s uploaded successfully.", data.getOriginalFilename());
		}
		
		return String.format("File %s upload failed.", data.getOriginalFilename());
	}

	public String uploadBase64File(String filename, String filetype, String data) {
		FileStorage file = new FileStorage();
		file.setFilename(filename);
		file.setFiletype(filetype);
		file.setFilebase64(data);
		FileStorage newFile = fsService.persistFile(file);
		
		if(newFile != null) {
			return String.format("File %s uploaded successfully.", filename);
		}
		
		return String.format("File %s uplaod failed.", filename);
	}
	
	public FileStorage retreiveFile(String filename) {
		return fsService.retrieveFileByName(filename);
	}
	
	public byte[] downloadMulitpartFile(String filename) {
		return FileStorageUtil.deCompressFile(fsService.retrieveFileByName(filename).getFilebyte());
	}

	public byte[] downloadBase64File(String filename) {
		String data = fsService.retrieveFileByName(filename).getFilebase64();
		String content = data.split(",")[1];
		if(content == null) {
			return null;
		}
		return Base64.decodeBase64(content);
	}

}
