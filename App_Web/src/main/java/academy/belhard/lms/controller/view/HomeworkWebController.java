package academy.belhard.lms.controller.view;

import academy.belhard.lms.service.FileLinkService;
import academy.belhard.lms.service.FileService;
import academy.belhard.lms.service.HomeworkService;
import academy.belhard.lms.service.UserService;
import academy.belhard.lms.service.dto.FileLinkDto;
import academy.belhard.lms.service.dto.course.HomeworkDto;
import academy.belhard.lms.service.dto.user.UserDto;
import academy.belhard.lms.service.exception.NotFoundException;
import academy.belhard.lms.service.impl.UserAppDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@RequestMapping("/homeworks")
@RequiredArgsConstructor
@Controller
public class HomeworkWebController {
    private final HomeworkService homeworkService;
    private final FileLinkService fileLinkService;
    private final UserService userService;
    private final FileService fileService;
    public static final String FILE_UPLOAD_ERROR = "File upload error";
    public static final String FILE_DOWNLOAD_ERROR = "File download error";


    @GetMapping
    public String getAll(Model model, @PageableDefault @SortDefault("id") Pageable pageable) {
        Page<HomeworkDto> homeworks = homeworkService.getAll(pageable);
        model.addAttribute("homeworks", homeworks);
        return "homeworks";
    }

    @GetMapping("/student/{id}")
    public String getAllByStudentId(@PathVariable Long id, Model model, @PageableDefault @SortDefault("id") Pageable pageable) {
        Page<HomeworkDto> homeworks = homeworkService.getAllByStudentId(id, pageable);
        model.addAttribute("homeworks", homeworks);
        return "homeworks";
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable Long id) {
        HomeworkDto homework = homeworkService.getById(id);
        model.addAttribute("homework", homework);
        return "homework";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        UserAppDetails userAppDetails = (UserAppDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDto userDto = userService.getById(userAppDetails.getId());
        model.addAttribute("user", userDto);
        return "create_homework";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute HomeworkDto homeworkDto, @RequestParam("file_upload") MultipartFile multipartFile) {
        FileLinkDto link = saveFile(multipartFile);
        UserDto userDto = userService.getById(homeworkDto.getStudent().getId());
        homeworkDto.setFileLink(link);
        homeworkDto.setStudent(userDto);
        HomeworkDto created = homeworkService.create(homeworkDto);
        return "redirect:/homeworks/" + created.getId();
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        HomeworkDto toUpdate = homeworkService.getById(id);
        model.addAttribute("homework", toUpdate);
        return "update_homework";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, String link_name, @ModelAttribute HomeworkDto homeworkDto, @RequestParam("file_upload") MultipartFile multipartFile) {
        FileLinkDto link;
        if (!multipartFile.isEmpty()) {
            link = saveFile(multipartFile);
        } else {
            link = fileLinkService.getByLink(link_name);
        }
        homeworkDto.setId(id);
        UserDto userDto = userService.getById(homeworkDto.getStudent().getId());
        homeworkDto.setStudent(userDto);
        homeworkDto.setFileLink(link);
        homeworkService.update(homeworkDto);
        return "redirect:/homeworks/" + id;
    }

    private FileLinkDto saveFile(MultipartFile multipartFile) {
        try {
            String originalFilename = multipartFile.getOriginalFilename();
            InputStream stream = new ByteArrayInputStream(multipartFile.getBytes());
            return fileService.save(stream, originalFilename);
        } catch (IOException e) {
            throw new NotFoundException(FILE_UPLOAD_ERROR);
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        homeworkService.delete(id);
        return "redirect:/homeworks/student/" + id;
    }
}
