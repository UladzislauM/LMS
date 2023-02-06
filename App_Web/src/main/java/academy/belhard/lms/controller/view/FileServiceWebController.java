package academy.belhard.lms.controller.view;

import academy.belhard.lms.service.FileLinkService;
import academy.belhard.lms.service.FileService;
import academy.belhard.lms.service.dto.FileLinkDto;
import academy.belhard.lms.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    private final FileLinkService fileLinkService;

    @GetMapping("/uploader")
    public String uploadForm() {
        return "fileService";
    }

    @PostMapping("/uploader")
    public String upload(@RequestParam("file_upload") MultipartFile multipartFile, Model model) {
        try {
            String originalFilename = multipartFile.getOriginalFilename();
            InputStream stream = new ByteArrayInputStream(multipartFile.getBytes());
            FileLinkDto fileLinkDto = fileService.save(stream, originalFilename);
            model.addAttribute("file_name", fileLinkDto);
        } catch (IOException e) {
            throw new NotFoundException(FILE_UPLOAD_ERROR);
        }
        return "redirect:/";
    }

    @GetMapping("/file/{fileLink}")
    public void download(@PathVariable String fileLink, OutputStream outputStream) {
        try {
            FileLinkDto fileLinkDto = fileLinkService.getByLink(fileLink);
            InputStream inputStream = fileService.get(fileLinkDto);
            byte[] a = inputStream.readAllBytes();
            outputStream.write(a);
        } catch (IOException e) {
            throw new NotFoundException(FILE_DOWNLOAD_ERROR);
        }
    }
}
