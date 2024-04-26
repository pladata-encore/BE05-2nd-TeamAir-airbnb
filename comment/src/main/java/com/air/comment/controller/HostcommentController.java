package com.air.comment.controller;

import com.air.comment.domain.dto.HostCommentDto;
import com.air.comment.domain.dto.request.CommentRequest;
import com.air.comment.domain.dto.request.HostcommentRequest;
import com.air.comment.domain.dto.response.HostcommentResponse;
import com.air.comment.domain.entity.HostComment;
import com.air.comment.service.HostcommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comment/host")
@RequiredArgsConstructor

public class HostcommentController {
    private final HostcommentService hostcommentService;

    @PostMapping
    public void addHostComment(@RequestBody HostcommentRequest request) {
        hostcommentService.addHostComment(request);
    }

    @PutMapping("/edit/{commentId}")
    public void editHostComment(@PathVariable("commentId") Integer commentId, @RequestBody HostcommentRequest request){
        hostcommentService.editHostComment(commentId, request);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable("commentId") Integer commentId){
        hostcommentService.deleteComment(commentId);
    }

    @GetMapping("/{commentId}")
    public List<HostcommentResponse> loadHostComment(@PathVariable("commentId") String hostName){
        return hostcommentService.loadHostComment(hostName);
    }
    @GetMapping("/test")
    public String test(){
        return "tttttt";
    }
}
