package com.example.Auction.controller;

import com.example.Auction.entity.AuctionEntity;
import com.example.Auction.entity.GoodsEntity;
import com.example.Auction.service.AuctionService;
import com.example.Auction.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchAuctionController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private AuctionService auctionService;

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String searchGoods(@RequestParam(name = "search") String name,
                              Model model){
        List<AuctionEntity> auctionList = new ArrayList<>();
        List<GoodsEntity> listGood = goodsService.searchGoods(name);
        for(GoodsEntity good: listGood){
            AuctionEntity auction = auctionService.getAuctionByGoodsId(good.getId());
            auctionList.add(auction);
        }
        model.addAttribute("auctionList", auctionList);
        return "product";
    }

    @RequestMapping(value = "/searchCategory/{categoryName}", method = RequestMethod.GET)
    public String searchCategory(@PathVariable(value = "categoryName") String name, Model model){
        List<AuctionEntity> auctionList = auctionService.searchAuctionByCategory(name);
        List<AuctionEntity> listAuction = new ArrayList<>();
        for (AuctionEntity auction : auctionList){
            if (auction.getAuctionStatus().equals("Start")) {
                listAuction.add(auction);
            }
        }
        model.addAttribute("auctionList", listAuction);
        return "product";

    }
}
