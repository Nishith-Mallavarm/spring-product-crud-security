package com.example.demo.repository2;

import com.example.demo.entity2.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {

    @Query("""
    select  u
    from Users u
    join fetch u.roles r
    where u.username = :username
""")
    Users findByUsername(String username);
}
