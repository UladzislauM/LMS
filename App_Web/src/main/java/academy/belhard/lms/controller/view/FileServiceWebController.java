package academy.belhard.lms.controller.view;

import academy.belhard.lms.service.FileService;
import academy.belhard.lms.service.dto.FileLinkDto;
import academy.belhard.lms.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Controller
@RequiredArgsConstructor
public class FileServiceWebController {
    public static final String FILE_UPLOAD_ERROR = "File upload error";
    public static final String FILE_DOWNLOAD_ERROR = "File download error";
    private final FileService fileService;


    @GetMapping("/uploader")
    public String uploadForm() {
        return "fileService";
    }

    @PostMapping("/uploader")
    public String upload(@RequestParam("file_upload") MultipartFile multipartFile) {
        try {
            String originalFilename = multipartFile.getOriginalFilename();
            InputStream stream = new ByteArrayInputStream(multipartFile.getBytes());
            fileService.save(stream, originalFilename);
        } catch (IOException e) {
            throw new NotFoundException(FILE_UPLOAD_ERROR);
        }
        return "redirect:/";
    }

    @GetMapping("/file/{fileLinkDto}")
    public void download(@PathVariable String fileLink, OutputStream outputStream) {
        try {
            FileLinkDto fl = new FileLinkDto();
            fl.setLink(fileLink);
            InputStream inputStream = fileService.get(fl);
            while (true) {
                int a = inputStream.read();
                outputStream.write(a);
            }
        } catch (IOException e) {
            throw new NotFoundException(FILE_DOWNLOAD_ERROR);
        }
    }
}