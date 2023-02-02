package academy.belhard.lms.controller.view;

import academy.belhard.lms.service.FileService;
import academy.belhard.lms.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequiredArgsConstructor
@RequestMapping("/uploader")
public class FileServiceWebController {
    public static final String FILE_UPLOAD_ERROR = "File upload error";
    private final FileService fileService;


    @GetMapping()
    public String uploadForm() {
        return "fileService";
    }

    @PostMapping()
    public String upload(@RequestParam("file_upload") MultipartFile multipartFile) {
        try {
            String originalFilename = multipartFile.getOriginalFilename();
            String[] strings = originalFilename.split("\\.");
            String extension = strings[strings.length - 1];
            byte[] bytes = multipartFile.getBytes();
            InputStream stream = new ByteArrayInputStream(bytes);
            fileService.save(stream, extension);
        } catch (IOException e) {
            throw new NotFoundException(FILE_UPLOAD_ERROR);
        }
        return "redirect:/";
    }
}