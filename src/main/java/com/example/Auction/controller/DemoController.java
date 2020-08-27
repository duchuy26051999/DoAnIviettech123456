package com.example.Auction.controller;

import com.example.Auction.entity.AccountEntity;
import com.example.Auction.entity.CustomerEntity;
import com.example.Auction.service.AccountService;
import com.example.Auction.service.CustomerService;
import com.example.Auction.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;


@Controller
public class DemoController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private JavaMailSender emailSender;


    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String registerFormKH(Model model){
        model.addAttribute("customer",new CustomerEntity());
        return "register";
    }

    @RequestMapping(value = "/registerKH",method = RequestMethod.POST)
    public String registerKH(@Valid CustomerEntity customer, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            model.addAttribute("successMessage","Vui lòng nhập lại thông tin!");
            model.addAttribute("bindingResult",bindingResult);
        }else if(customerService.isEmailAlreadyPresent(customer.getEmail())) {
            model.addAttribute("successMessage","Email đã tồn tại!");
        }else if(customerService.isIdNumberAlreadyPresent(customer.getIdNumber())){
            model.addAttribute("successMessage","Số CMND đã có người đăng ký!");
        }else if(customerService.isPhoneNumberAlreadyPresent(customer.getPhoneNumber())){
            model.addAttribute("successMessage","Số điện thoại đã tồ tại!");
        }else {
            AccountEntity account = new AccountEntity();
            account.setCustomer(customer);
            model.addAttribute("account", account);
            return "registerAccount";
        }
        model.addAttribute("customer",new CustomerEntity());
        return "register";
    }

    @RequestMapping(value = "registerAccountKH",method = RequestMethod.POST)
    public String registerAcc(@Valid AccountEntity account, @RequestParam(value = "passwordConfirm")String passConfirm,
                              BindingResult bindingResult, Model model) throws Exception {
        if (bindingResult.hasErrors()) {
            model.addAttribute("successMessage","Vui lòng sửa lại biểu mẫu!");
            model.addAttribute("bindingResult",bindingResult);
        }
        if(account.getPassWord().equals(passConfirm)) {
            if (accountService.isAccountAlreadyPresent(account)) {
                model.addAttribute("successMessage", "Tài khoản đã tồn tại!");
            } else {
                CustomerEntity newCustomer = customerService.addAccount(account);
                CustomerEntity customerMailTo = customerService.getCustomer(newCustomer.getId());
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(customerMailTo.getEmail());
                message.setSubject("Cảm ơn " + customerMailTo.getCustomerName() + " đã tin dùng dịch vụ của AUCTION SYSTEM");
                message.setText("Xin chào, " + customerMailTo.getCustomerName() + " đã ghé thăm và đăng ký tài khoản tại Website Auction System! \n" +
                        " Chúc bạn có nhiều trải nghiệm thú vị cùng những dịch vụ của chúng tôi! \n" +
                        "Xin chân thành cảm ơn!");
                this.emailSender.send(message);
                model.addAttribute("successMessage", "Register Successfully!");
                return "redirect:/login";
            }
            model.addAttribute("account", account);
            return "registerAccount";
        }else{
            model.addAttribute("successMessage","Mật khẩu xác nhận thất bại!");
            model.addAttribute("account", account);
            return "registerAccount";
        }

    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String loginKH(){
        return "login";
    }



}
