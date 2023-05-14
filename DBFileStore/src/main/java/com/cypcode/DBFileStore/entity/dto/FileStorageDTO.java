package com.cypcode.DBFileStore.entity.dto;

public class FileStorageDTO {

	private String filename;
	private String filetype;
	private String filebase64;
	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}
	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
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
