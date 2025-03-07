package com.green.controller;

import java.io.File;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.green.service.NewsService;
import com.green.domain.FileUploadVO;
import com.green.domain.NewsVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("news")
public class NewsController {

	@Setter(onMethod_=@Autowired)
	private NewsService service;
	
	@Setter(onMethod_=@Autowired)
	private HttpSession session;
	
	
	@GetMapping("/list")
	public void getList(Model model) {
		log.info("getList : " + service.getList());
		List<NewsVO> list = service.getList();
		list.forEach(i->i.getRegDate());
		model.addAttribute("news_list", service.getList());
	}
	
	@GetMapping("/get")
	public void getOne(@RequestParam("nno") Long nno ,Model model) {
		log.info("getOne : " + service.getOne(nno));
		model.addAttribute("get", service.getOne(nno));
	}
	
	@GetMapping("/register")
	public void insertG(Model model) {
		log.info("insert page get");
	}
	
	@PostMapping("/register")
	public String insertP(NewsVO news) {
		log.info("insert page POST");
		service.insert(news);
		return "redirect:/news/list";
	}
	
	@GetMapping("/modify")
	public void updateG(@RequestParam("nno") Long nno, Model model) {
		log.info("update Get");
		model.addAttribute("get", service.getOne(nno));
	}
	
	@PostMapping("/modify")
	public String updateP(NewsVO news) {
		log.info("update page POST");
		service.update(news);
		return "redirect:/news/list";
	}
	
	@PostMapping("/delete")
	public String delete(Long nno, HttpServletRequest request) {
		log.info("delete page POST");
		String resourceSrc = request.getServletContext().getRealPath("/resources/");
		FileUploadVO getFile = service.getFile(nno);
		deleteFiles(getFile,resourceSrc);
		service.delete(nno);
		return "redirect:/news/list";
	}
	
	//file upload
	@PostMapping(value = "/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<FileUploadVO>> uploadAjaxPost(MultipartFile[] uploadFile, HttpServletRequest request) {
		log.info("uploadAjaxAction");
		List<FileUploadVO> list = new ArrayList<>();
		String uploadFolderPath = "/news";
		String resourceSrc = request.getServletContext().getRealPath("/resources/");
		log.info("resourceSrc : "+resourceSrc);
		File uploadPath = new File(resourceSrc, uploadFolderPath);
		log.info(uploadPath);
		log.info(uploadPath.exists());
		if (uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
		log.info("uploadFile: "+uploadFile);
		for (MultipartFile multipartFile : uploadFile) {
			log.info("multipartFile : "+multipartFile);
			FileUploadVO fileVO = new FileUploadVO();
			String uploadFileName = multipartFile.getOriginalFilename();
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
			log.info("only file name: " + uploadFileName);
			fileVO.setFileName(uploadFileName);
			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			try {
				File saveFile = new File(uploadPath, uploadFileName);
				multipartFile.transferTo(saveFile);
				log.info("saveFile : " + saveFile);
				fileVO.setUuid(uuid.toString());
				fileVO.setUploadPath(uploadFolderPath);
				list.add(fileVO);
			} catch (Exception e) {
				e.printStackTrace();
				log.info(e);
			}
		} // end for
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	
	@PostMapping("/deleteFile")
	@ResponseBody
	public ResponseEntity<String> deleteFile(String fileName , String uuid, HttpServletRequest request){
		log.info("컨트롤러 파일 삭제  : " +fileName);
		String resourceSrc = request.getServletContext().getRealPath("/resources/");
		
		//File file;
		try {
			//file = new File(resourceSrc + "news\\" + URLDecoder.decode(fileName,"UTF-8"));
			//file.delete();
			//System.out.println("delete : "+resourceSrc + "news\\" + URLDecoder.decode(fileName,"UTF-8"));
			Path file = Paths.get(resourceSrc+ "news\\" + uuid + "_" + fileName);
			Files.deleteIfExists(file); //파일이 존재하면 삭제 
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("file delete ", HttpStatus.OK);
	}
	
	private void deleteFiles(FileUploadVO getFile, String resourceSrc) {
		if(getFile == null) return; //null일 경우 함수 종료
		log.info("컨트롤러에서의 첨부파일 삭제..............."   + getFile);
		try { //파일이나 데이터베이스에 접근시 항상 try ~ catch문 사용 
			Path file = Paths.get(resourceSrc + getFile.getUploadPath() + "\\" + getFile.getUuid() + "_" + getFile.getFileName());
			log.info(resourceSrc + getFile.getUploadPath() + "\\" + getFile.getUuid() + "_" + getFile.getFileName());
			Files.deleteIfExists(file); //파일이 존재하면 삭제 
		} catch (Exception e) {
			log.error("파일 삭제시 오류:" + e.getMessage());
		}

	}
	
}
