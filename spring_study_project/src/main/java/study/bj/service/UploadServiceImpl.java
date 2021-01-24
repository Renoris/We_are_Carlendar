package study.bj.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Service
public class UploadServiceImpl implements UploadService{
    @Override
    public boolean upload(MultipartFile file, HttpServletRequest request) {
        try{
            //request.getServletContext에 getRealPath로 실제 서버의 물리적인 위치를 받을수 있음.
            File path=new File(request.getServletContext().getRealPath("/")+"WEB-INF/static/"+file.getOriginalFilename());
            FileOutputStream fileOutputStream=new FileOutputStream(path);
            //파일업로드의 경우 바로 파일열고 스트림으로 바이트 전송하면 성능이 안나옴
            BufferedOutputStream bufferedOutputStream=new BufferedOutputStream(fileOutputStream);
            bufferedOutputStream.write(file.getBytes());
            bufferedOutputStream.close();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
