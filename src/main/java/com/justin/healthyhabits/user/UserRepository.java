package com.justin.healthyhabits.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<SiteUsers, String> {
}
