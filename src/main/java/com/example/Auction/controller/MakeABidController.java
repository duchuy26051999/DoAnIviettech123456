package com.example.Auction.controller;

import com.example.Auction.entity.*;
import com.example.Auction.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class MakeABidController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AuctionService auctionService;

    @Autowired
    private CreditService creditService;


    @Autowired
    private BidService bidService;

    @RequestMapping(value = "/makeABid/{id}", method = RequestMethod.POST)
    public String makeABid(@PathVariable(value = "id") int id,
                           @RequestParam(name = "bid") double bidPrice,
                           HttpSession session, Model model) {
        // get Auction
        AuctionEntity getAuction = auctionService.getAuction(id);
        // get Username
        String username = (String) session.getAttribute("accountName");
        // get Account
        AccountEntity getAccount = accountService.getAccountByUserName(username);
        // kiểm tra account có đăng nhập k
        if (getAccount != null) {
            // get Customer
            CustomerEntity getCustomer = customerService.getCustomerByAccountName(username);
            // get Credit
            CreditEntity getCredit = creditService.getCreditByCustomerId(getCustomer.getId());
            // kiểm tra bạn có credit hay chưa
            if (getCredit != null) {
                // kiểm tra customer đủ tiền để đấu giá không...!
                if (getCredit.getAmount() >= bidPrice) {
                    // kiểm tra list rổng và giá đấu phải lớn hoặc bằng giá khởi điểm
                    List<BidEntity> listBid = bidService.getAllBid1(getAuction.getId());
                    if (listBid.isEmpty() && bidPrice >= getAuction.bidPriceMaxiMum()) {
                        BidEntity bid = new BidEntity();
                        bid.setBidStatus("win");
                        bid.setAuction(getAuction);
                        bid.setCustomer(getCustomer);
                        bid.setDateBid(LocalDateTime.now());
                        bid.setBidPrice(bidPrice);
                        bidService.addBid(bid);
                        return "redirect:/productDetail/{id}";
                    } else {
                        if (getAuction.bidPriceMaxiMum() < bidPrice) {
                            for (BidEntity bidEntity : listBid) {
                                bidEntity.setBidStatus("lost");
                                bidService.addBid(bidEntity);
                            }
                            // nếu thành công thì lưu vào csdl cái bid của customer
                            BidEntity newBid = new BidEntity();
                            newBid.setCustomer(getCustomer);
                            newBid.setBidPrice(bidPrice);
                            newBid.setDateBid(LocalDateTime.now());
                            newBid.setBidStatus("win");
                            newBid.setAuction(getAuction);
                            bidService.addBid(newBid);
                            return "redirect:/productDetail/{id}";
                        } else {
                            String mes = "Giá thầu không hợp lệ, giá thầu phải lớn hơn giá thầu lớn nhất hiện tại!";
                            model.addAttribute("message", mes);
                            return "redirect:/productDetail/{id}";
                        }
                    }
                    }else{
                        return "redirect:/user/creditCard";
                    }
                } else {
                    return "redirect:/user/creditCard";
                }
            } else {
                return "redirect:/login";
            }
        }
    }




