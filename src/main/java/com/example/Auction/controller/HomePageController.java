package com.example.Auction.controller;

import com.example.Auction.entity.AuctionEntity;
import com.example.Auction.entity.CategoryEntity;
import com.example.Auction.entity.CustomerEntity;
import com.example.Auction.entity.OrdersEntity;
import com.example.Auction.service.AuctionService;
import com.example.Auction.service.CustomerService;
import com.example.Auction.service.GoodsService;
import com.example.Auction.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = {"/","/home"})
public class HomePageController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private AuctionService auctionService;
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/" , method = RequestMethod.GET)
    public String homePage(Model model,HttpSession session){
        List<CategoryEntity> categoryList = goodsService.getAllCategory();
        List<AuctionEntity> auctionList = auctionService.getAllAuctionOrderById("Start");
        List<AuctionEntity> auctionListRecently = auctionService.getAllAuctionRecently(LocalDateTime.now(),LocalDateTime.now().plusDays(1));
        List<AuctionEntity> auctionList1 = new ArrayList<AuctionEntity>(3);
        for (AuctionEntity auction : auctionListRecently){
            if (auction.getAuctionStatus().equals("New")) {
                if(auctionList1.size() < 4) {
                    auctionList1.add(auction);
                }else {
                    break;
                }
            }
        }
        session.setAttribute("category", categoryList);
        model.addAttribute("auctionList1", auctionList);
        model.addAttribute("auctionList2",auctionList1);
        return "index";
    }

    @RequestMapping(value = "/home" , method = RequestMethod.GET)
    public String homeUserPage(Model model, Principal principal, HttpSession session){
        CustomerEntity customer = customerService.getCustomerByAccountName(principal.getName());
        if(customer != null) {
            List<OrdersEntity> listOrder = ordersService.getAllOrdersOfCustomerOrderById(customer.getCustomerName());
            List<OrdersEntity> newListOrder = new ArrayList<>();
            for (OrdersEntity orders : listOrder) {
                if (orders.getStatus().equals("unconfirmed")) {
                    newListOrder.add(orders);
                }
            }
            if (newListOrder.isEmpty()) {
                List<CategoryEntity> category = goodsService.getAllCategory();
                List<AuctionEntity> auctionList = auctionService.getAllAuctionOrderById("Start");
                List<AuctionEntity> auctionListRecently = auctionService.getAllAuctionRecently(LocalDateTime.now(), LocalDateTime.now().plusDays(1));
                List<AuctionEntity> auctionList1 = new ArrayList<AuctionEntity>(3);
                for (AuctionEntity auction : auctionListRecently){
                    if (auction.getAuctionStatus().equals("New")) {
                        if(auctionList1.size() < 4) {
                            auctionList1.add(auction);
                        }else {
                            break;
                        }
                    }
                }
                session.setAttribute("category", category);
                model.addAttribute("auctionList1", auctionList);
                session.setAttribute("hello", "Hello ");
                model.addAttribute("auctionList2", auctionList1);
                session.setAttribute("accountName", principal.getName());
                return "index";
            }
            session.setAttribute("hello", "Hello ");
            session.setAttribute("accountName", principal.getName());
            model.addAttribute("listOrderForCheckout", newListOrder);
            return "user/listOrdersForCheckout";
        }else {
            List<CategoryEntity> category = goodsService.getAllCategory();
            List<AuctionEntity> auctionList = auctionService.getAllAuctionOrderById("Start");
            List<AuctionEntity> auctionListRecently = auctionService.getAllAuctionRecently(LocalDateTime.now(), LocalDateTime.now().plusDays(1));
            List<AuctionEntity> auctionList1 = new ArrayList<AuctionEntity>(3);
            for (AuctionEntity auction : auctionListRecently){
                if (auction.getAuctionStatus().equals("New")) {
                    if(auctionList1.size() < 4) {
                        auctionList1.add(auction);
                    }else {
                        break;
                    }
                }
            }
            session.setAttribute("category", category);
            model.addAttribute("auctionList1", auctionList);
            session.setAttribute("hello", "Hello ");
            model.addAttribute("auctionList2", auctionList1);
            session.setAttribute("accountName", principal.getName());
            return "index";
        }
    }



    @RequestMapping("/loginFailed")
    public String loginError(Model model) {
        model.addAttribute("error", "true");
        return "login";
    }

    @RequestMapping(value = "/contact",method = RequestMethod.GET)
    public String contact(Model model){
        return "contact";
    }

    @RequestMapping(value = "/prepareAuction",method = RequestMethod.GET)
    public String prepareAuction(Model model){
        List<AuctionEntity> prepareAuctionList = auctionService.getAllAuctionRecently(LocalDateTime.now(),LocalDateTime.now().plusDays(1));
        List<AuctionEntity> auctionList = new ArrayList<>();
        for (AuctionEntity auction : prepareAuctionList){
            if (auction.getAuctionStatus().equals("New")) {
                auctionList.add(auction);
            }
        }
        model.addAttribute("auctionList1",auctionList);
        return "prepareAuction";
    }


}
