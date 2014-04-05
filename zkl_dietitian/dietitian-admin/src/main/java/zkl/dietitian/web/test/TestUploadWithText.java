package zkl.dietitian.web.test;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import zkl.dietitian.entity.privilege.User;

/**
 * 文件上传与text同时提交
 * @author zkl
 *
 */

@Controller
@RequestMapping("/upload")
public class TestUploadWithText {

	@RequestMapping(value = "/text", method = RequestMethod.GET)
	public String upload(){
		return "test/testupload";
	}
	@RequestMapping(value = "/doUpload", method = RequestMethod.POST)
	public String doupload(User user,@RequestParam(value = "testFile", required = false) MultipartFile file,HttpServletRequest request, HttpServletResponse response){
		System.out.println(user.getName());
		 String fileName = file.getOriginalFilename();  
		 System.out.println(fileName);
		 try {
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File("C:/Users/zkl/Desktop/updownload", file.getOriginalFilename()));
		} catch (IOException e) {
			e.printStackTrace();
		}  
		return "test/testupload";
	}
}
