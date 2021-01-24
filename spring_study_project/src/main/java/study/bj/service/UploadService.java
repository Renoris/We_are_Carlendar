package study.bj.service;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

public interface UploadService {
    public boolean upload(MultipartFile file, HttpServletRequest request);
}
