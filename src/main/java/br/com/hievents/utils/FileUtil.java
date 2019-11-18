package br.com.hievents.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;

public class FileUtil {

	public static Boolean validateFileSize(File file) throws IOException {
		Boolean isValid = false;
		try {
			Long rowCount = countFileLines(file);
			if(rowCount > 1) {
				isValid = true;
			}
		} catch (IOException exception) {
			throw exception;
		}
		return isValid;
	}
	
	public static Long countFileLines(File file) throws IOException{
		Long rowCount = Files.lines(Paths.get(file.getPath()), StandardCharsets.ISO_8859_1).count();
		return rowCount;
	}
	
	public static void removeAllFilesFromFolder(String folder) {
		try {
			File file = new File(folder);
			if(file.exists()) {
				FileUtils.cleanDirectory(new File(folder));
			} else {
				file.mkdir();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
