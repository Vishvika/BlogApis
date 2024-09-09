package com.blog.blog_app_apis.repositories;

import com.blog.blog_app_apis.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.yaml.snakeyaml.events.Event;

public interface RoleRepo extends JpaRepository<Role, Integer> {
}
