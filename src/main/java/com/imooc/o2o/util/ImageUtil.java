package com.imooc.o2o.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import com.imooc.o2o.dto.ImageHolder;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public class ImageUtil {
	
	private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
	private static final SimpleDateFormat sFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	private static final Random r = new Random();

	public static String generateThumbnail(ImageHolder thumbnail, String targetAddr) {
		String realFileName = getRandomFileName();
		String extension = getFileExtension(thumbnail.getImageName());
		makeDirPath(targetAddr);
		String relativeAddr = targetAddr + realFileName + extension;
		File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
		try {
			Thumbnails.of(thumbnail.getImage()).size(200, 200)
					.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.5f)
					.outputQuality(0.8f).toFile(dest);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return relativeAddr;
	}

	/**
	 * 创建目标路径所涉及到的目录，
	 * 
	 * @param targetAddr
	 */
	private static void makeDirPath(String targetAddr) {
		String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
		File dirPath = new File(realFileParentPath);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}

	}

	/**
	 * 获取输入文件流的拓展名（.jpg,.png...）
	 * 
	 * @param thumbnail
	 * @return
	 */
	private static String getFileExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

	/**
	 * 生成随机文件名，当前年月日小时，分钟，秒加五位随机数
	 * 
	 * @return
	 */
	public static String getRandomFileName() {
		// 获取随机五位数
		int rannum = r.nextInt(89999) + 10000;
		String nowTimeSrt = sFormat.format(new Date());
		return nowTimeSrt + rannum;
	}

	/**
	 * store 是文件 的路径还是目录的路径 如果是文件路径则删除该文件 不是就删除该目录下的所有文件
	 * 
	 * @param shorePath
	 */
	public static void deleteFileOrPath(String shorePath) {
		File fileOrPath = new File(PathUtil.getImgBasePath() + shorePath);
		if (fileOrPath.exists()) {
			if (fileOrPath.isDirectory()) {
				File files[] = fileOrPath.listFiles();
				for (int i = 0; i < files.length; i++) {
					files[i].delete();
				}
			}
			fileOrPath.delete();
		}

	}

	public static void main(String[] args) throws IOException {
		Thumbnails.of(new File("D:/images/liuyifei.jpg")).size(1280, 1024)
				.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.5f)
				.outputQuality(0.8f).toFile("D:/images/liuyifeinew.jpg");

	}

	/**
	 * 
	 * @param productImgHolder
	 * @param dest
	 * @return
	 */
	public static String generateNormalImg(ImageHolder thumbnail, String targetAddr) {

		String realFileName = getRandomFileName();
		String extension = getFileExtension(thumbnail.getImageName());
		makeDirPath(targetAddr);
		String relativeAddr = targetAddr + realFileName + extension;
		
		File dest = new File(PathUtil.getImgBasePath() + relativeAddr);

		try {
			Thumbnails.of(thumbnail.getImage()).size(337, 640)
					.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.5f)
					.outputQuality(0.9f).toFile(dest);
		} catch (IOException e) {
			throw new RuntimeException("创建失败");
		}
		System.out.println(dest);
		return relativeAddr;
	}

}
