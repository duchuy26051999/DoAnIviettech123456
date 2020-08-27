package com.example.Auction.controller;

import com.example.Auction.entity.*;
import com.example.Auction.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class DemoForAuctionController {
    @Autowired
    private AuctionService auctionService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private BidService bidService;
    @Autowired
    private TransactionLogService transactionLogService;
    @Autowired
    private CreditService creditService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private OrdersService ordersService;


    @GetMapping("/myCredit")
    public String  bidDemo(Model model, HttpServletRequest request, RedirectAttributes redirect){
        request.getSession().setAttribute("transactionList", null);

        if(model.asMap().get("success") != null)
            redirect.addFlashAttribute("success",model.asMap().get("success").toString());
        return "redirect:/user/myCredit/page/1";
    }

    @GetMapping("/myCredit/page/{pageNumber}")
    public String viewDemo(HttpServletRequest request,
                           @PathVariable int pageNumber, Model model, HttpSession session){
        PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("transactionList");
        int pageSize = 10;
        CustomerEntity customer = customerService.getCustomerByAccountName((String) session.getAttribute("accountName"));
        CreditEntity credit = creditService.getCreditByCustomerId(customer.getId());
        List<TransactionLogEntity> list =(List<TransactionLogEntity>) transactionLogService.getAllTransactionLogOrderByDateLog(credit.getId());

        System.out.println(list.size());
        if (pages == null) {
            pages = new PagedListHolder<>(list);
            pages.setPageSize(pageSize);
        } else {
            final int goToPage = pageNumber - 1;
            if (goToPage <= pages.getPageCount() && goToPage >= 0) {
                pages.setPage(goToPage);
            }
        }
        request.getSession().setAttribute("transactionList", pages);
        int current = pages.getPage() + 1;
        int begin = Math.max(1, current - list.size());
        int end = Math.min(begin + 5, pages.getPageCount());
        int totalPageCount = pages.getPageCount();
        String baseUrl = "/user/myCredit/page/";

        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("baseUrl", baseUrl);
        model.addAttribute("transactionLog", pages);
        model.addAttribute("credit",credit);

        return "user/myAuctionDemo";
    }

    @PostMapping("/myCredit/search/{pageNumber}")
    public String search(@RequestParam("dateStart") String dateStart,@RequestParam("dateEnd") String dateEnd, Model model, HttpServletRequest request,
                            @PathVariable int pageNumber,HttpSession session) {
        LocalDateTime date1 = LocalDateTime.parse(dateStart);
        LocalDateTime date2 = LocalDateTime.parse(dateEnd);
        CustomerEntity customer = customerService.getCustomerByAccountName((String) session.getAttribute("accountName"));
        CreditEntity credit = creditService.getCreditByCustomerId(customer.getId());
        List<TransactionLogEntity> transactionList = transactionLogService.getAllTransactionLogByDateLogBetween(credit.getId(),date1,date2);
        if(transactionList.isEmpty()) {
            model.addAttribute("message", "Search results not available");
            model.addAttribute("listLog",transactionList);
            return "user/myAuctionDemo";
        }
        PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("transactionList");
        int pageSize = 10;
        pages = new PagedListHolder<>(transactionList);
        pages.setPageSize(pageSize);

        final int goToPage = pageNumber - 1;
        if (goToPage <= pages.getPageCount() && goToPage >= 0) {
            pages.setPage(goToPage);
        }
        request.getSession().setAttribute("transactionList", pages);
        int current = pages.getPage() + 1;
        int begin = Math.max(1, current - transactionList.size());
        int end = Math.min(begin + 5, pages.getPageCount());
        int totalPageCount = pages.getPageCount();
        String baseUrl = "/user/myCredit/page/";
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("baseUrl", baseUrl);
        model.addAttribute("transactionLog", pages);
        model.addAttribute("credit",credit);
        return "user/myAuctionDemo";
    }

}
