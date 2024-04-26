package com.air.comment.service;

import com.air.comment.domain.dto.CommentDto;
import com.air.comment.domain.dto.request.CommentRequest;
import com.air.comment.domain.dto.response.CommentResponse;
import com.air.comment.domain.entity.Comment;
import com.air.comment.domain.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public void addComment(CommentRequest request){
        Comment comment = Comment.builder()
                .roomId(request.roomId())
                .commentStar(request.commentStar())
                .comment(request.comment())
                .build();
        Comment save = commentRepository.save(comment);
    }

    public void deleteComment(Integer id){
        commentRepository.deleteById(id);
    }

    @Transactional
    public void editComment(Integer id, CommentRequest request){
        Comment comment = commentRepository.findById(id)
                .orElseThrow();
        comment.setComment(request.comment());
    }

    public List<CommentResponse> loadRoomComment(Integer roomId){
        List<Comment> allByRoomId = commentRepository.findAllByRoomId(roomId);
        return allByRoomId.stream().map(CommentResponse::form).toList();
    }

}
