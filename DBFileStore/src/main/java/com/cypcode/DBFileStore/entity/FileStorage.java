package com.cypcode.DBFileStore.entity;


import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileStorage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "filename")
	private String fileName;
	
	@Column(name = "filetype")
	private String filetype;
	
	@Column(name = "filebyte", length = 5000)
	private byte[] filebyte;
	
	@Column(name = "filebase64", length = 20000)
	private String filebase64;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the filename
	 */
	public String getFilename() {
		return fileName;
	}
	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.fileName = filename;
	}
	/**
	 * @return the filetype
	 */
	public String getFiletype() {
		return filetype;
	}
	/**
	 * @param filetype the filetype to set
	 */
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	/**
	 * @return the filebyte
	 */
	public byte[] getFilebyte() {
		return filebyte;
	}
	/**
	 * @param filebyte the filebyte to set
	 */
	public void setFilebyte(byte[] filebyte) {
		this.filebyte = filebyte;
	}
	/**
	 * @return the filebase64
	 */
	public String getFilebase64() {
		return filebase64;
	}
	/**
	 * @param filebase64 the filebase64 to set
	 */
	public void setFilebase64(String filebase64) {
		this.filebase64 = filebase64;
	}
	
	
}
