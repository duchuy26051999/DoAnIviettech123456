package com.example.Auction.controller;

import com.example.Auction.entity.CreditEntity;
import com.example.Auction.entity.DepositLogEntity;
import com.example.Auction.entity.StaffEntity;
import com.example.Auction.entity.TransactionLogEntity;
import com.example.Auction.service.CreditService;
import com.example.Auction.service.DepositLogService;
import com.example.Auction.service.StaffService;
import com.example.Auction.service.TransactionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.soap.Addressing;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    private CreditService creditService;
    @Autowired
    private StaffService staffService;
    @Autowired
    private DepositLogService depositLogService;
    @Autowired
    private TransactionLogService transactionLogService;

    @RequestMapping("/home")
    public String viewHome(HttpSession session, Model model) {
        String name = (String) session.getAttribute("accountName");
        model.addAttribute("accountName",name);
        return "staff/index1";
    }

    @RequestMapping(value = "/depositAmount",method = RequestMethod.GET)
    public String depositAmountForCustomer(Model model, HttpServletRequest request, RedirectAttributes redirect){
        request.getSession().setAttribute("creditList", null);

        if(model.asMap().get("success") != null)
            redirect.addFlashAttribute("success",model.asMap().get("success").toString());
        return "redirect:/staff/depositAmount/page/1";
    }

    @GetMapping("/depositAmount/page/{pageNumber}")
    public String viewDemo(HttpServletRequest request,
                           @PathVariable int pageNumber, Model model, HttpSession session){
        PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("creditList");
        int pageSize = 10;
        List<CreditEntity> list =(List<CreditEntity>) creditService.getAllCredit();

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
        request.getSession().setAttribute("creditList", pages);
        int current = pages.getPage() + 1;
        int begin = Math.max(1, current - list.size());
        int end = Math.min(begin + 5, pages.getPageCount());
        int totalPageCount = pages.getPageCount();
        String baseUrl = "/staff/depositAmount/page/";

        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("baseUrl", baseUrl);
        model.addAttribute("credit", pages);

        return "staff/depositCustomer";
    }

    @PostMapping("/depositAmount/search/{pageNumber}")
    public String searchCredit(@RequestParam("codeSearch") String name, Model model, HttpServletRequest request,
                         @PathVariable int pageNumber,HttpSession session) {
        //CustomerEntity customer = customerService.getCustomerByAccountName((String) session.getAttribute("accountName"));
        CreditEntity credit = creditService.getCreditByCardNumber(name);
        List<CreditEntity> creditList = new ArrayList<>();
        if(credit != null){
            creditList.add(credit);
        }

        if(creditList.isEmpty()) {
            model.addAttribute("message", "Search results not available");
            model.addAttribute("creditList",creditList);
            return "staff/depositCustomer";
        }else {
            PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("creditList");
            int pagesize = 10;
            pages = new PagedListHolder<>(creditList);
            pages.setPageSize(pagesize);

            final int goToPage = pageNumber - 1;
            if (goToPage <= pages.getPageCount() && goToPage >= 0) {
                pages.setPage(goToPage);
            }
            request.getSession().setAttribute("creditList", pages);
            int current = pages.getPage() + 1;
            int begin = Math.max(1, current - creditList.size());
            int end = Math.min(begin + 5, pages.getPageCount());
            int totalPageCount = pages.getPageCount();
            String baseUrl = "/staff/depositAmount/page/";
            model.addAttribute("beginIndex", begin);
            model.addAttribute("endIndex", end);
            model.addAttribute("currentIndex", current);
            model.addAttribute("totalPageCount", totalPageCount);
            model.addAttribute("baseUrl", baseUrl);
            model.addAttribute("credit", pages);
            return "staff/depositCustomer";
        }
    }


    @RequestMapping(value = "/deposit",method = RequestMethod.POST)
    public String deposit(@RequestParam(name = "cardNumber") String cardNumber,
                          @RequestParam(name = "amount") Double amount,Model model,HttpSession session,RedirectAttributes redirect){
        CreditEntity credit = creditService.getCreditByCardNumber(cardNumber);
        if(credit != null){
            credit.setAmount(credit.getAmount() + amount);
            //lưu vao depositLog
            DepositLogEntity depositLog = new DepositLogEntity();
            depositLog.setAmount(amount);
            depositLog.setCredit(credit);
            StaffEntity staff = staffService.getStaffByAccountName((String) session.getAttribute("accountName"));
            depositLog.setStaff(staff);
            depositLog.setDateDeposit(LocalDateTime.now());
            DepositLogEntity newDepositLog = depositLogService.addDepositLog(depositLog);
            //lưu vao transactionLog
            TransactionLogEntity transactionLog = new TransactionLogEntity();
            transactionLog.setAmount(amount);
            transactionLog.setBalance(credit.getAmount());
            transactionLog.setContent("Deposit amount");
            transactionLog.setCredit(credit);
            transactionLog.setDateLog(LocalDateTime.now());
            transactionLog.setLogType("Recharge");
            TransactionLogEntity newTransactionLog = transactionLogService.addTransactionLog(transactionLog);
            if(newDepositLog != null && newTransactionLog != null){
                creditService.updateCredit(credit);
                List<CreditEntity> listCredit = new ArrayList<CreditEntity>();
                listCredit.add(credit);
                redirect.addAttribute("listCredit", listCredit);
                model.addAttribute("message1","Deposit Success");
                return "redirect:/staff/depositAmount/deposit/1";
            }
        }
        List<CreditEntity> listCredit = creditService.getAllCredit();
        model.addAttribute("listCredit",listCredit);
        model.addAttribute("message1","Card number not exist");
        return "redirect:/staff/depositAmount";
    }

    @GetMapping("/depositAmount/deposit/{pageNumber}")
    public String deposit(Model model, HttpServletRequest request,
                          @PathVariable int pageNumber,@RequestParam(value = "listCredit") List<CreditEntity> creditList) {
        if(creditList.isEmpty()) {
            model.addAttribute("message", "Search results not available");
            model.addAttribute("creditList",creditList);
            return "staff/depositCustomer";
        }
        PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("creditList");
        int pagesize = 10;
        pages = new PagedListHolder<>(creditList);
        pages.setPageSize(pagesize);

        final int goToPage = pageNumber - 1;
        if (goToPage <= pages.getPageCount() && goToPage >= 0) {
            pages.setPage(goToPage);
        }
        request.getSession().setAttribute("creditList", pages);
        int current = pages.getPage() + 1;
        int begin = Math.max(1, current - creditList.size());
        int end = Math.min(begin + 5, pages.getPageCount());
        int totalPageCount = pages.getPageCount();
        String baseUrl = "/staff/depositAmount/page/";
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("baseUrl", baseUrl);
        model.addAttribute("credit", pages);
        return "staff/depositCustomer";
    }

    @RequestMapping(value = "/formDeposit/{cardNumber}",method = RequestMethod.GET)
    public String formDeposit(@PathVariable(value = "cardNumber") String cardNumber,Model model){
        CreditEntity credit = creditService.getCreditByCardNumber(cardNumber);
        model.addAttribute("creditID",credit);
        return "staff/depositForm";
    }
}
