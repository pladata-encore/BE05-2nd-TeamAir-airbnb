package com.air.comment.controller;

import com.air.comment.domain.dto.CommentDto;
import com.air.comment.domain.dto.request.CommentRequest;
import com.air.comment.domain.dto.response.CommentResponse;
import com.air.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comment/guest")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public void addComment(@RequestBody CommentRequest request){
        commentService.addComment(request);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable("id") Integer id){
        commentService.deleteComment(id);
    }

    @PutMapping("/edit/{id}")
    public void editComment(@PathVariable("id") Integer id, @RequestBody CommentRequest request){
        commentService.editComment(id, request);
    }

    @GetMapping("/load/{roomId}")
    public List<CommentResponse> loadRoomComment(@PathVariable("roomId") Integer roomId) {
        return commentService.loadRoomComment(roomId);
    }

}
