package com.Learn_Up.System.Repositories;

import com.Learn_Up.System.Entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<UsersEntity, Long> {
}
