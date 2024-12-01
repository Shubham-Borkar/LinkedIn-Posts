package com.mds.repo.mysql;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mds.entity.mysql.UserEntity;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

}
