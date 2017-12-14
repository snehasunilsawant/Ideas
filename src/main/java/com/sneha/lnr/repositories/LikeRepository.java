package com.sneha.lnr.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sneha.lnr.models.PLike;
import com.sneha.lnr.models.Post;
import com.sneha.lnr.models.User;

@Repository
public interface LikeRepository extends CrudRepository<PLike, Long> {

	PLike findByUserAndPost(User user , Post post);
}
