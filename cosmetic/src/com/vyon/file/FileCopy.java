package com.vyon.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class FileCopy {
	public static void imgCopy(String gimg, HttpServletRequest request) {
		// TODO Auto-generated method stub
		System.out.println("FileCopy.imgCopy");

		FileInputStream fis = null;
		FileOutputStream fos1 = null;
		FileOutputStream fos2 = null;
		FileChannel fin = null;
		FileChannel fout = null;

		try {
			// 파일 존재 여부 확인
			String path = request.getServletContext().getRealPath("");
			System.out.println("path :" + path);

			// 원본 파일
			String orignalFile = path + "\\goods\\gimage\\" + gimg;
			System.out.println("orignalFile: " + orignalFile);
			fis = new FileInputStream(orignalFile);

			// 서버 복사될 곳
			String newServerFile = path + "\\order\\gimage\\" + gimg;
			System.out.println("newServerFile: " + newServerFile);
			fos1 = new FileOutputStream(newServerFile);

			fin = fis.getChannel();
			fout = fos1.getChannel();

			long size = fin.size();

			fin.transferTo(0, size, fout);

			// 프로젝트 파일에 복사될 곳
			String newProjFile = "C:\\workspace\\jsp\\cosmetic\\WebContent\\order\\gimage\\"
					+ gimg;
			fos2 = new FileOutputStream(newProjFile);
			System.out.println("newProjFile : " + newProjFile);

			fout = fos2.getChannel();
			fin.transferTo(0, size, fout);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fout.close();
				fin.close();
				fos1.close();
				fos2.close();
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void imgCopy(List<String> imgNameList,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		System.out.println("FileCopy.imgCopy");

		FileInputStream fis = null;
		FileOutputStream fos1 = null;
		FileOutputStream fos2 = null;
		FileChannel fin = null;
		FileChannel fout = null;

		try {
			for (String gimg : imgNameList) {
				// 파일 존재 여부 확인
				String path = request.getServletContext().getRealPath("");
				System.out.println("path :" + path);

				// 원본 파일
				String orignalFile = path + "\\goods\\gimage\\" + gimg;
				System.out.println("orignalFile: " + orignalFile);
				fis = new FileInputStream(orignalFile);

				// 서버 복사될 곳
				String newServerFile = path + "\\order\\gimage\\" + gimg;
				System.out.println("newServerFile: " + newServerFile);
				fos1 = new FileOutputStream(newServerFile);

				fin = fis.getChannel();
				fout = fos1.getChannel();

				long size = fin.size();

				fin.transferTo(0, size, fout);

				// 프로젝트 파일에 복사될 곳
				String newProjFile = "C:\\workspace\\jsp\\cosmetic\\WebContent\\order\\gimage\\"
						+ gimg;
				fos2 = new FileOutputStream(newProjFile);
				System.out.println("newProjFile : " + newProjFile);

				fout = fos2.getChannel();
				fin.transferTo(0, size, fout);				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fout.close();
				fin.close();
				fos1.close();
				fos2.close();
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
