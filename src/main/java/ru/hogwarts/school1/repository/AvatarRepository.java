package ru.hogwarts.school1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school1.model.Avatar;

public interface AvatarRepository extends JpaRepository <Avatar, Long> {

}
