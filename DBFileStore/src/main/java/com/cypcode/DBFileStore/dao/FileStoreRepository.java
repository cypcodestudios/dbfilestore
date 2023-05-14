package com.cypcode.DBFileStore.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cypcode.DBFileStore.entity.FileStorage;


@Repository
@Transactional
public interface FileStoreRepository extends JpaRepository<FileStorage, Long>{

	FileStorage findByFileName(String filename);
}
