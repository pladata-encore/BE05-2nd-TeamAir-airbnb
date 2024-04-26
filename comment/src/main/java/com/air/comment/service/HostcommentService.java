package com.air.comment.service;

import com.air.comment.domain.dto.request.CommentRequest;
import com.air.comment.domain.dto.request.HostcommentRequest;
import com.air.comment.domain.dto.response.HostcommentResponse;
import com.air.comment.domain.entity.Comment;
import com.air.comment.domain.entity.HostComment;
import com.air.comment.domain.repository.HostcommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HostcommentService {
    private final HostcommentRepository hostcommentRepository;

    public void addHostComment(HostcommentRequest request){
        HostComment hostComment = HostComment.builder()
                .commentId(request.commentId())
                .hostCommentContent(request.hostCommentContent())
                .hostName(request.hostName())
                .build();
        HostComment save = hostcommentRepository.save(hostComment);
    }

    @Transactional
    public void editHostComment(Integer userid, HostcommentRequest request){
        HostComment hostComment = hostcommentRepository.findById(userid).orElseThrow();
        hostComment.setHostCommentContent(request.hostCommentContent());
    }

    public void deleteComment(Integer commentId){
        hostcommentRepository.deleteById(commentId);
    }

    public List<HostcommentResponse> loadHostComment(String hostName){
        List<HostComment> allByCommentId = hostcommentRepository.findByHostName(hostName);
        return allByCommentId.stream().map(HostcommentResponse::from).toList();
    }

}
