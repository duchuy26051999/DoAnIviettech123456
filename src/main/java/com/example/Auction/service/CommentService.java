package com.example.Auction.service;

import com.example.Auction.entity.CommentsEntity;
import com.example.Auction.repository.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentsRepository commentsRepo;

    public CommentsEntity addComment(CommentsEntity comments){
        CommentsEntity newComment;
        try {
            newComment = commentsRepo.save(comments);
            return newComment;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public List<CommentsEntity> getAllCommentByIdAuction(int id){
        return commentsRepo.findByAuctionId(id);
    }
}
