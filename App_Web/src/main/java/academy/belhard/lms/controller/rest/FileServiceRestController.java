package academy.belhard.lms.controller.rest;

import academy.belhard.lms.service.FileLinkService;
import academy.belhard.lms.service.FileService;
import academy.belhard.lms.service.dto.FileLinkDto;
import academy.belhard.lms.service.exception.LmsException;
import academy.belhard.lms.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@RequestMapping("/api/v1.0")
@RequiredArgsConstructor
@RestController
public class FileServiceRestController {
    public static final String FILE_UPLOAD_ERROR = "File upload error";
    public static final String FILE_DOWNLOAD_ERROR = "File download error";
    private final FileService fileService;
    private final FileLinkService fileLinkService;


    @PostMapping("/uploader")
    @ResponseBody
    public String upload(@RequestParam("file_upload") MultipartFile multipartFile) {
        return saveFile(multipartFile, fileService).getLink();
    }

    @GetMapping("/file/{fileLink}")
    public void download(@PathVariable String fileLink, OutputStream outputStream) {
        try {
            FileLinkDto fileLinkDto = fileLinkService.getByLink(fileLink);
            InputStream inputStream = fileService.get(fileLinkDto);
            byte[] a = inputStream.readAllBytes();
            outputStream.write(a);
        } catch (IOException e) {
            throw new LmsException(FILE_DOWNLOAD_ERROR);
        }
    }

    static FileLinkDto saveFile(MultipartFile multipartFile, FileService fileService) {
        try {
            String originalFilename = multipartFile.getOriginalFilename();
            InputStream stream = new ByteArrayInputStream(multipartFile.getBytes());
            return fileService.save(stream, originalFilename);
        } catch (IOException e) {
            throw new NotFoundException(FILE_UPLOAD_ERROR);
        }
    }
}
