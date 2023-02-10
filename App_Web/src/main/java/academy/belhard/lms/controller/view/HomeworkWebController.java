package academy.belhard.lms.controller.view;

import academy.belhard.lms.service.FileLinkService;
import academy.belhard.lms.service.FileService;
import academy.belhard.lms.service.HomeworkService;
import academy.belhard.lms.service.LessonService;
import academy.belhard.lms.service.UserService;
import academy.belhard.lms.service.dto.FileLinkDto;
import academy.belhard.lms.service.dto.course.HomeworkDto;
import academy.belhard.lms.service.dto.course.LessonDto;
import academy.belhard.lms.service.dto.user.UserDto;
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

import java.util.List;

@RequestMapping("/homeworks")
@RequiredArgsConstructor
@Controller
public class HomeworkWebController {
    private final HomeworkService homeworkService;
    private final FileLinkService fileLinkService;
    private final UserService userService;
    private final FileService fileService;
    private final LessonService lessonService;
    public static final String FILE_UPLOAD_ERROR = "File upload error";


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
    public String getById(Model model, @PathVariable Long id, Long lesson_id) {
        HomeworkDto homework = homeworkService.getById(id);
        model.addAttribute("homework", homework);
        String fileName = "";
        if (homework.getFileLink() != null){
            fileName = homework.getFileLink().getLink();
        }
        model.addAttribute("file_name", fileName);
        LessonDto lesson;
        if (lesson_id != null) {
            lesson = lessonService.getById(lesson_id);
        } else {
            lesson = lessonService.getByHomeworkId(id);
        }
        model.addAttribute("lesson", lesson);
        return "homework";
    }

    @GetMapping("/create")
    public String createForm(Model model, @RequestParam("lesson") Long id) {
        UserAppDetails userAppDetails = (UserAppDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDto userDto = userService.getById(userAppDetails.getId());
        model.addAttribute("user", userDto);
        LessonDto lesson = lessonService.getById(id);
        model.addAttribute("lesson", lesson);
        return "create_homework";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute HomeworkDto homeworkDto, Long lesson_id, @RequestParam("fileUpload") MultipartFile multipartFile) {
        if (!multipartFile.isEmpty()) {
            FileLinkDto link = FileServiceWebController.saveFile(multipartFile, fileService);
            homeworkDto.setFileLink(link);
        }
        UserDto userDto = userService.getById(homeworkDto.getStudent().getId());
        homeworkDto.setStudent(userDto);
        LessonDto lesson = lessonService.getById(lesson_id);
        HomeworkDto created = homeworkService.create(homeworkDto);
        List<HomeworkDto> homeworks = lesson.getHomeworks();
        homeworks.add(created);
        lesson.setHomeworks(homeworks);
        lessonService.update(lesson);
        return "redirect:/homeworks/" + created.getId() + "?lesson_id=" + lesson_id;
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        HomeworkDto toUpdate = homeworkService.getById(id);
        model.addAttribute("homework", toUpdate);
        return "update_homework";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute HomeworkDto homeworkDto, @RequestParam("fileUpload") MultipartFile multipartFile) {
        FileLinkDto link;
        if (!multipartFile.isEmpty()) {
            link = FileServiceWebController.saveFile(multipartFile, fileService);
        } else {
            link = fileLinkService.getById(homeworkDto.getFileLink().getId());
        }
        homeworkDto.setId(id);
        UserDto userDto = userService.getById(homeworkDto.getStudent().getId());
        homeworkDto.setStudent(userDto);
        homeworkDto.setFileLink(link);
        homeworkService.update(homeworkDto);
        return "redirect:/homeworks/" + id;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        homeworkService.delete(id);
        return "redirect:/homeworks/student/" + id;
    }
}
