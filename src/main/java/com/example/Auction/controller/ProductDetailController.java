package com.example.Auction.controller;

import com.example.Auction.entity.*;
import com.example.Auction.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

import static java.time.LocalDateTime.now;

@Controller
public class ProductDetailController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private AuctionService auctionService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CommentService commentService;
    @DateTimeFormat(pattern = "yyyy-MM-dd' 'hh:mm:ss")
    @Autowired
    private ImageService imageService;
    @Autowired
    private BidService bidService;

    @RequestMapping(value = "/productDetail/{id}", method = RequestMethod.GET)
    public String productDetail(@PathVariable(value = "id") int id, Model model,
                                HttpSession session) {
        AuctionEntity auctionDetails = auctionService.getAuction(id);
        List<BidEntity> bid = bidService.getAllBid1(auctionDetails.getId());
        double s = bidService.getPriceBidIncrement(auctionDetails.getPriceStart(), auctionDetails.getId());
        String price = bidService.getPriceBidIncrementFormat(auctionDetails.getPriceStart(), auctionDetails.getId());
        List<ImageEntity> imageList = imageService.getListImageByGoodId(auctionDetails.getGoods().getId());
        model.addAttribute("imageList", imageList);
        model.addAttribute("auctionDetails", auctionDetails);
        model.addAttribute("comment", new CommentsEntity());
        List<CommentsEntity> commentList = commentService.getAllCommentByIdAuction(auctionDetails.getId());
        model.addAttribute("listComment", commentList);
        model.addAttribute("bid", bid);
        model.addAttribute("price",s);
        model.addAttribute("formatPrice",price);
//        model.addAttribute("message",message);
        return "user/product_detail";
    }

    @RequestMapping(value = "/postMessage/{id}",method = RequestMethod.POST)
    public String postComment(CommentsEntity comment,Model model, HttpSession session, @PathVariable(value = "id") int idAuction){
        comment.setDatePost(LocalDateTime.now());
        AuctionEntity auction = auctionService.getAuction(idAuction);
        comment.setAuction(auction);
        String accountName = (String) session.getAttribute("accountName");

        CustomerEntity customer = customerService.getCustomerByAccountName(accountName);
        if(customer == null){
            model.addAttribute("message","Bạn chưa có account!");
        }else {
            comment.setCustomer(customer);
            CommentsEntity newComment = commentService.addComment(comment);
        }

        return "redirect:/productDetail/{id}";
    }

    @GetMapping(value = "/plusBid/{id}/{price}")
    public String plusBid(@PathVariable(value = "id") int id,Model model,@PathVariable(value = "price") Double bid){
        AuctionEntity auctionDetails = auctionService.getAuction(id);
        List<BidEntity> bidList = bidService.getAllBid1(auctionDetails.getId());
        Double increment = bidService.getIncrement(bid);
        double s = bid * increment;
        String price = auctionDetails.formatBid(s);
        List<ImageEntity> imageList = imageService.getListImageByGoodId(auctionDetails.getGoods().getId());
        model.addAttribute("imageList", imageList);
        model.addAttribute("auctionDetails", auctionDetails);
        model.addAttribute("comment", new CommentsEntity());
        List<CommentsEntity> commentList = commentService.getAllCommentByIdAuction(auctionDetails.getId());
        model.addAttribute("listComment", commentList);
        model.addAttribute("bid", bidList);
        model.addAttribute("price",s);
        model.addAttribute("formatPrice",price);
        return "user/product_detail";
    }


    @GetMapping(value = "/minusBid/{id}/{price}")
    public String minusBid(@PathVariable(value = "id") int id,Model model,@PathVariable(value = "price") Double bid){
        AuctionEntity auctionDetails = auctionService.getAuction(id);
        List<BidEntity> bidList = bidService.getAllBid1(auctionDetails.getId());
        Double increment = bidService.getIncrement(bid);
        double s = bid / increment;
        String price = auctionDetails.formatBid(s);
        List<ImageEntity> imageList = imageService.getListImageByGoodId(auctionDetails.getGoods().getId());
        model.addAttribute("imageList", imageList);
        model.addAttribute("auctionDetails", auctionDetails);
        model.addAttribute("comment", new CommentsEntity());
        List<CommentsEntity> commentList = commentService.getAllCommentByIdAuction(auctionDetails.getId());
        model.addAttribute("listComment", commentList);
        model.addAttribute("bid", bidList);
        model.addAttribute("price",s);
        model.addAttribute("formatPrice",price);
        return "user/product_detail";
    }
}
