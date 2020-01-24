/**
 * 
 */
package com.nikhil.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author Nikhil
 *
 */
@Controller
public class MessageDashBoardController {

	
	@RequestMapping(value = "downloadFile.html", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<byte[]> downloadFile(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, @RequestParam("hdnFilePath") String filePath) {


		final String METHOD_NAME = "downloadFile";
        final String URL_NAME = "downloadFile.html";
        try {
        	 String fileName=filePath.substring(filePath.lastIndexOf('\\')+1,filePath.length());
        	 String extension = FilenameUtils.getExtension(fileName);
        	 File file = new File(filePath);
        	 byte[] byteArray = null;   
        	 
			try {
				InputStream inputStream = new FileInputStream(file);

				ByteArrayOutputStream baos = new ByteArrayOutputStream();

				int c;
				while ((c = inputStream.read()) != -1) {
					baos.write(c);
				}
				byteArray = baos.toByteArray();
				inputStream.close();
			} catch (FileNotFoundException e) {
			}

			if (extension != null) {
				switch (extension) {
				case "gif": {
					response.setContentType("image/gif");
				}
					break;
				case "pdf": {
					response.setContentType("application/pdf");
				}
					break;
				case "jpeg": {
					response.setContentType("image/jpeg");
				}
					break;
				case "jpg": {
					response.setContentType("image/jpg");
				}
				
				case "png":
				{
					response.setContentType("image/png");
				}
					break;
				case "rar": {
					response.setContentType("application/x-rar-compressed");
				}
					break;
				case "zip": {
					response.setContentType("application/zip");
				}
					break;
				}
			}
			if(extension!=null && (extension.equals("gif")||extension.equals("pdf")||extension.equals("jpeg")||extension.equals("jpg") || extension.equals("png"))) {
			response.setHeader("Expires", "0");
			response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Pragma", "public");
			
			response.setHeader("Content-Disposition", "inline; filename=" + byteArray);
			if(byteArray!=null) {
				
				response.setContentLength(byteArray.length);
			}
			response.getOutputStream().write(byteArray);
			response.getOutputStream().flush();
			}
			else {
			response.setHeader("Content-disposition", "attachment; filename=" + fileName);;
			FileInputStream in = null;
			in = new FileInputStream(file);

			OutputStream out = response.getOutputStream();

			byte[] buffer = new byte[10242880]; // use bigger if you want
			int length = 0;

			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}
			in.close();
			out.close();
			out.flush();}

		} catch (Exception e) {
			e.printStackTrace();
			deptLogStaticFields.deptErrorLog(null, session,
                    e.getMessage(), e.toString(),
                    CommonService.___8drrd3148796d_Xaf()+" ERROR_LINE_NUMBER ==>> "+e.getStackTrace()[0].getLineNumber(), URL_NAME, CLASS_NAME,
                    METHOD_NAME);

		}
		return null;
	}
	
	public static String getFilePath(String path) { //added by nikhil for adding Extra escape character in filePath 13/11/19  
	    path = path.replace("\\", "\\\\");
	    path = path.replace(":/", ":\\\\");
	    return path;
	}
	
	@RequestMapping(value = "checkingFileExitance.html", method = RequestMethod.POST)
	public @ResponseBody String checkingFileExitance(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("filePath") String filePath) {

   	 	File file = new File(filePath);
		try {
			if (!file.exists() || file.length() == 0) {
				return "1";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "0";
	}
}
