package egovframework.com.utl.fcc.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@Controller
public class EgovFileDownController {

	/**
     * 파일 다운로드 기능
     */
    @RequestMapping("/utl/fcc/FileDownload.do")
    public void fileDownload(
            @RequestParam("filePath") String filePath,
            @RequestParam(value = "downloadName", required = false) String downloadName,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {

        File file = new File(filePath);

        if (!file.exists()) {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println("<script>alert('파일이 존재하지 않습니다.');history.back();</script>");
            return;
        }

        String fileName = (downloadName != null && !downloadName.isEmpty())
                ? downloadName
                : file.getName();

        String encodedFileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");

        response.setContentType("application/octet-stream");
        response.setContentLengthLong(file.length());
        response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedFileName + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");

        try (
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream())
        ) {
            byte[] buffer = new byte[4096];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            out.flush();
        }
    }
}
