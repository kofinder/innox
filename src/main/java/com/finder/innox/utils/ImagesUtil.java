package com.finder.innox.utils;

import java.io.File;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

public class ImagesUtil {
	public static String uploadMultipartFile(MultipartFile clientFile, String subFolder, String prefixFileName, Long id)
			throws Exception {
		String fileName = generateFileName(clientFile, prefixFileName, id);
		File dest = new File(CommonConstant.IMAGE_SAVE_DIRECTORY + subFolder, fileName);
		FileUtils.writeByteArrayToFile(dest, clientFile.getBytes());
		return subFolder + fileName;
	}

	public static String uploadBase64File(String clientFileData, String subFolder, String prefixFileName, Long id)
			throws Exception {
		if (!CommonUtil.isEmpty(clientFileData)) {
			String fileName = generateFileNameForBase64(prefixFileName, id);
			byte[] decoded = Base64.decodeBase64(clientFileData);
			File dest = new File(CommonConstant.IMAGE_SAVE_DIRECTORY + subFolder, fileName);
			FileUtils.writeByteArrayToFile(dest, decoded);
			return subFolder + fileName;
		}
		return "";
	}

	private static String generateFileName(MultipartFile multipartFile, String prefixFileName, Long id) {
		String nano_time = String.valueOf(System.nanoTime());
		String fileName = (prefixFileName != null && !prefixFileName.isEmpty()) ? prefixFileName + "_" : "";
		fileName = id + "_" + fileName + nano_time + multipartFile.getOriginalFilename().replace(" ", "_");
		return fileName;
	}

	private static String generateFileNameForBase64(String prefixFileName, Long id) {
		String nano_time = String.valueOf(System.nanoTime());
		String fileName = (prefixFileName != null && !prefixFileName.isEmpty()) ? prefixFileName + "_" : "";
		fileName = id + "_" + fileName + nano_time + CommonConstant.IMAGE_SUFFIX;
		return fileName;
	}

	public static void deleteFile(String basePath, String fileName) throws Exception {
		File file = new File(basePath + File.separator + fileName);
		FileUtils.forceDelete(file);
	}
}
