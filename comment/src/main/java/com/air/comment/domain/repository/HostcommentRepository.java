package com.air.comment.domain.repository;

import com.air.comment.domain.entity.HostComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HostcommentRepository
extends JpaRepository<HostComment, Integer> {
   List<HostComment> findByHostName(String hostName);
}
