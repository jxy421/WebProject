package com.hebut.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class UploadController {
	
	@RequestMapping("/toUpload")
	public String toUpload(){
		return "upload";
	}
	
	@RequestMapping("/upload1")
	public String upload1(
			@RequestParam(value="file",required=false)
			MultipartFile file,
			HttpServletRequest request,
			ModelMap model){
		//��file���浽Ŀ��λ��
		String path = request.getSession().getServletContext().getRealPath("upload");
		String fileName = file.getOriginalFilename();
		String filePath = path+"\\"+file.getOriginalFilename();
		System.out.println("��������ʵ·����"+filePath+" ��Ŀ·����"+request.getContextPath());
		try {
			InputStream fis = file.getInputStream();
			FileOutputStream fos = new FileOutputStream(new File(filePath));
			byte[] bbs = new byte[1024];
			int len=-1;
			while(-1!=(len=fis.read(bbs))){
				fos.write(bbs,0,len);
			}
			fos.close();
			fis.close();
			model.addAttribute("fileUrl",request.getContextPath()+"/upload/"+fileName);
			return "result";
		} catch (IOException e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	@RequestMapping("/upload2")
	public String upload2(
			@RequestParam(value="file",required=false)
			MultipartFile file,
			HttpServletRequest request,
			ModelMap model) throws IOException{
		/**
		 * �ж��ļ�����
		 * 1. file.getOriginalFilename()��ȡ��չ��
		 * 2. �ж��Ƿ���������ͣ���������ת��upload.jsp
		 * 3. ��һ��error��Ϣ��ȥ����ֹ����ִ��
		 */
		
		//��file���浽Ŀ��λ��
		String path = request.getSession().getServletContext().getRealPath("upload");
		String fileName = file.getOriginalFilename();
		String filePath = path+"\\"+file.getOriginalFilename();
		System.out.println("��������ʵ·����"+filePath+" ��Ŀ·����"+request.getContextPath());
		InputStream fis = file.getInputStream();
		FileOutputStream fos = new FileOutputStream(new File(filePath));
		byte[] bbs = new byte[1024];
		int len=-1;
		while(-1!=(len=fis.read(bbs))){
			fos.write(bbs,0,len);
		}
		fos.close();
		fis.close();
		model.addAttribute("fileUrl",request.getContextPath()+"/upload/"+fileName);
		return "result";
	}
	
	@ExceptionHandler 
	public ModelAndView doExcetion(Exception ex){
		Map<String,Object> map =new HashMap<String,Object>();
		long maxSize = 0;
		if(ex instanceof MaxUploadSizeExceededException){
			maxSize = ((MaxUploadSizeExceededException)ex).getMaxUploadSize();
			System.out.println(maxSize);
			map.put("error", "�ϴ��ļ�̫�󣬲��ܳ���"+maxSize/1024+"K!");
		}else{
			map.put("error", "�ϴ�ʧ�ܣ�");
		}
		return new ModelAndView("upload",map);
	}
	
	@RequestMapping("/upload3")
	public String upload3(
			@RequestParam(value="file",required=false)
			MultipartFile file,
			HttpServletRequest request,
			ModelMap model){
		//��file���浽Ŀ��λ��
		String path = request.getSession().getServletContext().getRealPath("upload");
		String fileName = file.getOriginalFilename();
		System.out.println(path);
		File targetFile = new File(path,fileName);
		if(!targetFile.exists()){
			targetFile.mkdirs();
		}
		//����
		try{
			file.transferTo(targetFile);
			model.addAttribute("fileUrl",request.getContextPath()+"/upload/"+fileName);
			return "result";
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}
	
	@RequestMapping(value="/uploads")
	public String uploads(
			@RequestParam(value="file",required=false)
			MultipartFile[] files,
			HttpServletRequest request, 
			ModelMap model){
		List<String> urls = new ArrayList<String>();
		for (MultipartFile file : files) {
			String path = request.getSession().getServletContext().getRealPath("upload");
			String fileName = file.getOriginalFilename();
			System.out.println(path);
			File targetFile = new File(path,fileName);
			if(!targetFile.exists()){
				targetFile.mkdirs();
			}
			//����
			try{
				file.transferTo(targetFile);
				urls.add(request.getContextPath()+"/upload/"+fileName);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		model.addAttribute("fileurls", urls);
		return "result";
	}
}
