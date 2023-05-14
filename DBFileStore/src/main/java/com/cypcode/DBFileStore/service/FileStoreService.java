package com.cypcode.DBFileStore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cypcode.DBFileStore.dao.FileStoreRepository;
import com.cypcode.DBFileStore.entity.FileStorage;

@Service
public class FileStoreService {
	@Autowired
	private FileStoreRepository repository;
	
	public FileStorage persistFile(FileStorage file) {
		return repository.save(file);
	}
	public FileStorage retrieveFileByName(String filename) {
		return repository.findByFileName(filename);
	}
	
	public void removeFile(String filename) throws Exception {
		FileStorage file = retrieveFileByName(filename);
		if(file == null) {
			throw new Exception(String.format("File with name %s not found.", filename));
		}
		repository.delete(file);
	}
	
}
