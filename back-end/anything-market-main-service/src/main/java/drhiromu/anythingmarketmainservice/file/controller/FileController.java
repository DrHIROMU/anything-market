package drhiromu.anythingmarketmainservice.file.controller;

import drhiromu.anythingmarketmainservice.file.service.FileService;
import drhiromu.anythingmarketmainservice.file.vo.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Controller
@RequestMapping("file")
public class FileController {
    @Autowired
    FileService fileService;

    @GetMapping("/download/{fileId}")
    public void downloadFile(@PathVariable("fileId") String fileId,
                             HttpServletResponse response) {
        try {
            InputStream is = fileService.downloadFile(fileId);
            IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException ex) {
            log.error("Error writing file to output stream. File ID was '{}'", fileId, ex);
            throw new RuntimeException("IOError writing file to output stream");
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            fileService.save(file);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = e.getMessage();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }
}
