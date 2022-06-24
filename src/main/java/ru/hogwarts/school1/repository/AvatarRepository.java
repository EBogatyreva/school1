package ru.hogwarts.school1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school1.model.Avatar;

@Repository
public interface AvatarRepository extends PagingAndSortingRepository<Avatar, Long> {
    Avatar findAvatarById(Long id);
}
