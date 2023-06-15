package com.nhnacademy.notice_board.Controller.user;

import com.nhnacademy.notice_board.Controller.Command;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

@Slf4j
public class fileimageController implements Command {
    private static final String UPLOAD_DIR = "/src/main/upload";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String fileName = req.getParameter("fileName");
        if (Objects.isNull(fileName) || fileName.isEmpty()) {
            res.sendError(400, "fileName parameter is needed");
            return null;
        }

        String filePath = UPLOAD_DIR + File.separator + fileName;
        String realPath = req.getServletContext().getRealPath(filePath);
        realPath = realPath.replace("target/notice_board-1.0-SNAPSHOT/",""); //맥용
        //realPath = realPath.replace("target\\notice_board-1.0-SNAPSHOT\\","");//윈도우용
        Path path = Path.of(realPath);


        log.error("path:{}",path);
        if (!Files.exists(path)) {
            log.error("File not found:" + fileName);
            res.sendError(404, "File not found:" + fileName);
            return null;
        }

        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        try (InputStream is = Files.newInputStream(path);
             OutputStream os = res.getOutputStream()
        ) {

            byte[] buffer = new byte[4096];

            int n;
            while (-1 != (n = is.read(buffer))) {
                os.write(buffer, 0, n);
            }
        } catch (IOException ex) {
            log.error("", ex);
        }
        return "redirect:";
    }
}

