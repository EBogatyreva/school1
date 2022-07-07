package ru.hogwarts.school1.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school1.exception.BadRequest;
import ru.hogwarts.school1.exception.NotFoundException;
import ru.hogwarts.school1.model.Avatar;
import ru.hogwarts.school1.model.Student;
import ru.hogwarts.school1.repository.AvatarRepository;
import ru.hogwarts.school1.repository.StudentRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service

public class AvatarService {
    Logger logger = LoggerFactory.getLogger(FacultyService.class);
    @Value("${student.avatar.dir.path}")
    private String avatarsDir;

    private final StudentService studentService;//for work with methods of student
    private final AvatarRepository avatarRepository;//for work with database
    private final StudentRepository studentRepository;

    public AvatarService(StudentService studentService, AvatarRepository avatarRepository, StudentRepository studentRepository) {
        this.studentService = studentService;
        this.avatarRepository = avatarRepository;
        this.studentRepository = studentRepository;
    }

    public void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException {
        logger.info("Was invoked method for upload avatar");

        if (studentRepository.findById(studentId).get() == null) {
            logger.warn("Id couldn't be null");
            throw new NotFoundException();

        }

        Student student = studentRepository.getById(studentId);
        Path filePath = Path.of(avatarsDir, student + "." + getExtensions(Objects.requireNonNull(avatarFile.getOriginalFilename())));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (
                InputStream is = avatarFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }
        Avatar avatar = findAvatar(studentId);
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setData(avatarFile.getBytes());
        avatarRepository.save(avatar);
    }

    private String getExtensions(String fileName) {
        logger.info("Was invoked method getExtensions");
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public Avatar findAvatar(Long id) {
        logger.info("Was invoked method findAvatar");
        return avatarRepository.findAvatarById(id);
    }

    public List<Avatar> getAllAvatars(Integer pageNumber, Integer pageSize) {
        logger.info("Was invoked method getAllAvatars");
        if (pageNumber == 0) {
            logger.warn("PageNumber couldn't be null");
            throw new BadRequest();
        }
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);

        return avatarRepository.findAll(pageRequest).getContent();
    }
}
