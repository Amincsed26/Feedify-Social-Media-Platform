package com.example.backend.services;

import com.example.backend.dtos.ReportPostDTO;
import com.example.backend.entities.Post;

import com.example.backend.entities.ReportPost;
import com.example.backend.exceptions.PostNoFoundException;
import com.example.backend.exceptions.ReportNotFoundException;
import com.example.backend.repositories.PostRepository;
import com.example.backend.repositories.ReportPostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import com.example.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;


@Service
public class ReportPostService {
    @Autowired
    private ReportPostRepository reportPostRepository;
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    public void reportPost(ReportPostDTO reportPostDTO) {
        long userId = userService.getUserId();
        if(reportPostRepository.findReportPostByUserIdAndPostIdAndReason(userId,
                reportPostDTO.getPostID(), reportPostDTO.getReason()) != null)
            throw new ReportNotFoundException("You have already reported this post");
        ReportPost reportPost = new ReportPost();
        reportPost.setPost(postRepository.findById(reportPostDTO.getPostID())
                .orElseThrow(() -> new PostNoFoundException("Post not found")));
        reportPost.setUser(userRepository.findUserById(userId)
                .orElseThrow(() -> new PostNoFoundException("User not found")));
        reportPost.setReason(reportPostDTO.getReason());
        reportPost.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        reportPostRepository.save(reportPost);
    }


    public List<ReportPostDTO> getAllPostReports() {
        List<ReportPost> reportPosts = reportPostRepository.findByOrderByCreatedAtDesc();
        List<ReportPostDTO> reportPostDTOs = new ArrayList<>();
        if (reportPosts == null)
            throw new NullPointerException("Reports are null in database!");
        for (ReportPost reportPost : reportPosts) {
            reportPostDTOs.add(new ReportPostDTO(
                    reportPost.getId(),
                    reportPost.getPost().getId(),
                    reportPost.getUser().getId(),
                    reportPost.getUser().getEmail(),
                    reportPost.getReason(),
                    reportPost.getCreatedAt())
            );
        }
        return reportPostDTOs;
    }
    
    public void deletePost(long reportID){
        ReportPost reportPost = reportPostRepository.findReportPostById(reportID);
        if (reportPost == null)
            throw new ReportNotFoundException("Report not found");
        Post post = reportPost.getPost();
        if (post == null)
            throw new PostNoFoundException("Post not found");
        postRepository.delete(post);

    }

    public void denyReport(long reportID){
        ReportPost reportPost = reportPostRepository.findReportPostById(reportID);
        if (reportPost == null)
            throw new ReportNotFoundException("Report not found");
        reportPostRepository.delete(reportPost);
    }
    
}
