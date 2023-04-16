package com.admin.shop3.repository;


import com.admin.shop3.entity.User;
import com.admin.shop3.entity.state.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findAllByRoleOrderByIdDesc(Role role, Pageable pageable);

    User findOneByName(String name);

    Optional<User> findById(Long id);


    void delete(User user);
}

