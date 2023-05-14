package com.cypcode.DBFileStore.commons;

import java.util.zip.*;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

public class FileStorageUtil {

	public static byte[] compressFile(byte[] file) {
		Deflater deflater = new Deflater();
		deflater.setLevel(Deflater.BEST_COMPRESSION);
		deflater.setInput(file);
		deflater.finish();

		ByteArrayOutputStream stream = new ByteArrayOutputStream(file.length);
		byte[] dataHolder = new byte[4 * 1024];
		while (!deflater.finished()) {
			int size = deflater.deflate(dataHolder);
			stream.write(dataHolder, 0, size);
		}

		try {
			stream.close();
		} catch (Exception e) {
			// do something
		}
		return stream.toByteArray();
	}

	public static byte[] deCompressFile(byte[] file) {
		Inflater inflater = new Inflater();
		inflater.setInput(file);

		ByteArrayOutputStream stream = new ByteArrayOutputStream(file.length);
		byte[] dataHolder = new byte[4 * 1024];

		try {
			while (!inflater.finished()) {
				int size = inflater.inflate(dataHolder);
				stream.write(dataHolder, 0, size);
			}

			stream.close();
		} catch (Exception e) {
			// do something
		}
		return stream.toByteArray();

	}

}
