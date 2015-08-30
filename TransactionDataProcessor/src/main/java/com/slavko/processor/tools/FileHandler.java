package com.slavko.processor.tools;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.web.multipart.MultipartFile;

public class FileHandler {

	public static File multipartFileToFile(MultipartFile multipartFile) {

		File file = new File(multipartFile.getOriginalFilename());

		try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file))) {

			byte[] bytes = multipartFile.getBytes();
			stream.write(bytes);
			
		} catch (Exception e) {
			System.out.println("You failed to upload " + multipartFile.getName() + " => " + e.getMessage());
			return file;
		}
		return file;
	}

	public static String getFileExtention(String filename) {

		int dotPosition = filename.lastIndexOf(".") + 1;
		return filename.substring(dotPosition);
	}

}
