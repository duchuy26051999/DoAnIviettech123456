package com.example.Auction.controller;

import com.example.Auction.entity.*;
import com.example.Auction.repository.AccountRepository;
import com.example.Auction.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("/staff")
public class ManageAuctionController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private StaffService staffService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private AuctionService auctionService;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DepositLogService depositLogService;

    @Autowired
    private CommissionService commissionService;

    public String[] position = {"Quản lý", "Nhân viên"};

    private String[] enable = {"true", "false"};

    ////////////////////Category/////////////////////

    @RequestMapping(value = "/manageCategory", method = RequestMethod.GET)
    public String manageCategory(Model model) {
        List<CategoryEntity> category = goodsService.findByOrderByIdDescCategory();
        model.addAttribute("category", category);
        return "staff/category";
    }

    @RequestMapping(value = "/saveCategory", method = RequestMethod.POST)
    public String saveCategory(CategoryEntity category) {
        goodsService.addCategory(category);
        return "redirect:/staff/manageCategory";
    }

    @RequestMapping(value = "/addCategory", method = RequestMethod.GET)
    public String addCategory(Model model) {
        model.addAttribute("addCategory", new CategoryEntity());
        return "staff/addCategory";
    }

    @RequestMapping(value = "/updateCategory/{id}", method = RequestMethod.GET)
    public String updateCategory(@PathVariable(value = "id") int id, Model model) {
        CategoryEntity category = goodsService.getCategoryID(id);
        model.addAttribute("category", category);
        return "staff/updateCategory";
    }

    @RequestMapping(value = "/deleteCategory/{id}", method = RequestMethod.GET)
    public String deleteCategory(@PathVariable(value = "id") int id, Model model) {
        goodsService.deleteCategory(id);
        return "redirect:/staff/manageCategory";
    }

    @RequestMapping(value = "/searchCategoryName", method = RequestMethod.POST)
    public String searchCategoryName(@RequestParam(name = "search") String name,Model model){
        List<CategoryEntity> category = goodsService.searchCategoryName(name);
        model.addAttribute("category", category);
        return "staff/category";
    }
    /////////////////Good////////////////////

    @RequestMapping(value = "/manageGood", method = RequestMethod.GET)
    public String manageGood(Model model, HttpSession session) {
        List<GoodsEntity> goodList = goodsService.getAllGoods();
        List<CategoryEntity> categoryName = goodsService.getAllCategory();
        session.setAttribute("categoryName", categoryName);
        model.addAttribute("goodList", goodList);
        return "staff/good";
    }

    @RequestMapping(value = "/searchGoodName", method = RequestMethod.POST)
    public String searchGoodName(@RequestParam(name = "search") String name,
                                 @RequestParam(name = "category") String category, Model model) {
        List<GoodsEntity> goodList = goodsService.findByGoodNameCategory(name, category);
        model.addAttribute("goodList", goodList);
        return "staff/good";
    }

    /////////////////////Image/////////////////////
    @RequestMapping(value = "/manageImage", method = RequestMethod.GET)
    public String manageImage(Model model) {
        List<ImageEntity> imageList = imageService.findByOrderByIdDescImage();
        model.addAttribute("imageList", imageList);
        return "staff/Image";
    }

    @RequestMapping(value = "/searchImageName", method = RequestMethod.POST)
    public String searchImageName(@RequestParam(name = "search") String name, Model model) {
        List<ImageEntity> imageList = imageService.findByIdLikeOrUrlLike(name);
        model.addAttribute("imageList", imageList);
        return "staff/Image";
    }

    ////////////////////Auction//////////////////////////
    @RequestMapping(value = "/manageAuction", method = RequestMethod.GET)
    public String manageAuction(Model model) {
        List<AuctionEntity> auctionList = auctionService.findByOrderByIdDescAuction();
        model.addAttribute("auctionList", auctionList);
        return "staff/auction";
    }

    @RequestMapping(value = "/reportAuction", method = RequestMethod.GET)
    public String reportAuction(Model model) {
        List<AuctionEntity> auctionList = auctionService.getAllAuction();
        model.addAttribute("auctionList", auctionList);
        return "staff/reportAuction";
    }

    @RequestMapping(value = "/searchReportAuctionSuccess", method = RequestMethod.POST)
    public String searchReportAuctionSuccess(@RequestParam(name = "day") String day,
                                             @RequestParam(name = "day1") String day1, Model model) {
        List<AuctionEntity> auctionList = auctionService.searchReportAuctionSuccess(day, day1);
        model.addAttribute("auctionList", auctionList);
        return "staff/reportAuctionSuccess";
    }

    @RequestMapping(value = "/searchReportAuctionFailure", method = RequestMethod.POST)
    public String searchReportAuctionFailure(@RequestParam(name = "day") String day,
                                             @RequestParam(name = "day1") String day1
            , Model model) {
        List<AuctionEntity> auctionList = auctionService.searchReportAuctionFailure(day, day1);
        model.addAttribute("auctionList", auctionList);
        return "staff/reportAuctionFailure";
    }

    @RequestMapping(value = "/reportAuctionSuccess", method = RequestMethod.GET)
    public String reportAuctionSuccess(Model model) {
        List<AuctionEntity> auctionList = auctionService.reportAuctionSuccess();
        model.addAttribute("auctionList", auctionList);
        return "staff/reportAuctionSuccess";
    }

    @RequestMapping(value = "/reportAuctionFailure", method = RequestMethod.GET)
    public String reportAuctionFailure(Model model) {
        List<AuctionEntity> auctionList = auctionService.reportAuctionFailure();
        model.addAttribute("auctionList", auctionList);
        return "staff/reportAuctionFailure";
    }

    @RequestMapping(value = "/searchAuctionName", method = RequestMethod.POST)
    public String searchAuctionName(@RequestParam(name = "search")String name,
                                    @RequestParam(name = "status") String status, Model model){
        List<AuctionEntity> auctionList = auctionService.searchAuctionName(name,status);
        model.addAttribute("auctionList",auctionList);
        return "staff/auction";
    }

    ////////////////////////User//////////////////////////


    @RequestMapping(value = "/manageAccount", method = RequestMethod.GET)
    public String manageAccount(Model model) {
        List<AccountEntity> accountLists = accountService.findByOrderByIdDescAccount();
        model.addAttribute("accountLists", accountLists);
        return "staff/account";
    }

//    @RequestMapping(value = "/updateAccount/{id}", method = RequestMethod.GET)
//    public String updateAccount(@PathVariable(value = "id") int id, Model model) {
//        AccountEntity accountID = accountService.getAccount(id);
//        model.addAttribute("enable", enable);
//        model.addAttribute("accountID", accountID);
//        return "staff/updateAccount";
//    }

    @RequestMapping(value = "/saveAccountID", method = RequestMethod.POST)
    public String saveAccountID(AccountEntity account) {
        account.setPassWord(encoder.encode(account.getPassWord()));
        RoleEntity roleEntity = roleService.getRoleByName("ROLE_USER");
        account.setRole(new HashSet<RoleEntity>(Arrays.asList(roleEntity)));
        accountService.addAccount(account);
        return "redirect:/staff/manageAccount";
    }

    @RequestMapping(value = "/searchAccountName", method = RequestMethod.POST)
    public String searchNameAccount(@RequestParam(name = "search") String name, Model model) {
        List<AccountEntity> accountLists = accountService.searchAccountUserName(name);
        model.addAttribute("accountLists", accountLists);
        return "staff/account";
    }

    @RequestMapping(value = "/saveCustomer", method = RequestMethod.POST)
    public String saveCustomer(CustomerEntity customer, Model model){
        customerService.addCustomer(customer);
        return "redirect:/staff/manageCustomer";
    }

    /////////////////////Customer////////////////////////////

    @RequestMapping(value = "/manageCustomer", method = RequestMethod.GET)
    public String manageCustomer(Model model) {
        List<CustomerEntity> customerList = customerService.findByOrderByIdDescCustomer();
        model.addAttribute("customerList", customerList);
        return "staff/customerList";
    }

    @RequestMapping(value = "/searchCustomerName", method = RequestMethod.POST)
    public String searchCustomerName(@RequestParam(name = "search") String name, Model model) {
        List<CustomerEntity> customerList = customerService.findByCustomerNameLikeOrAddressLikeOrIdNumberLike(name);
        model.addAttribute("customerList", customerList);
        return "staff/customerList";
    }

    @RequestMapping(value = "/updateCustomer/{id}", method = RequestMethod.GET)
    public String updateCustomer(@PathVariable(value = "id") int id, Model model) {
        CustomerEntity customerID = customerService.customerID(id);
        model.addAttribute("customerID", customerID);
        return "staff/updateCustomer";
    }

    ///////////////////////Staff/////////////////////
//
//    @RequestMapping(value = "/manageAccountStaff", method = RequestMethod.GET)
//    public String pageAccountStaff(Model model) {
//            List<AccountEntity> accountLists = accountService.getAccountStaff();
//            model.addAttribute("accountLists", accountLists);
//            return "staff/accountStaffList";
//    }

    @RequestMapping(value = "/updateStaff/{id}", method = RequestMethod.GET)
    public String updateStaff(@PathVariable(value = "id") int id, Model model) {
        StaffEntity getStaffID = staffService.getStaffID(id);
        model.addAttribute("getStaffID", getStaffID);
        model.addAttribute("position", position);
        return "staff/updateStaff";
    }

    @RequestMapping(value = "/manageStaff", method = RequestMethod.GET)
    public String manageStaff(Model model) {
        List<StaffEntity> staffList = staffService.getAllStaff();
        model.addAttribute("staffList", staffList);
        return "staff/staffList";
    }

    @RequestMapping(value = "/saveStaffID", method = RequestMethod.POST)
    public String saveStaffID(StaffEntity staff) {
        staffService.addStaff(staff);
        return "redirect:/staff/manageStaff";
    }

    @RequestMapping(value = "/searchStaffName", method = RequestMethod.POST)
    public String searchStaffName(@RequestParam(name = "search") String name,
                                  @RequestParam(name = "position") String position, Model model) {
        List<StaffEntity> staffList = staffService.searchStaffName(name, position);
        model.addAttribute("staffList", staffList);
        return "staff/staffList";
    }

    @RequestMapping(value = "/saveStaffAccount", method = RequestMethod.POST)
    public String addStaff(AccountEntity account, Model model,
                           HttpSession session) {
        AccountEntity getAccount = accountService.getUsername(account.getUserName());
        if (getAccount != null) {
            String mes = "Tài khoản này đã tồn tại...!";
            model.addAttribute("mes", mes);
            model.addAttribute("accountStaff", new AccountEntity());
            return "staff/addAccountStaff";
        } else {
            account.setEnabled(true);
            session.setAttribute("account", account);
            model.addAttribute("position", position);
            model.addAttribute("staff", new StaffEntity());
            return "staff/addStaffInformation";
        }
    }


//    @RequestMapping(value = "/searchAccountStaffName", method = RequestMethod.POST)
//    public String searchAccountStaffName(@RequestParam(name = "search") String name, Model model) {
//        List<AccountEntity> accountLists = accountService.searchAccountStaffName(name);
//        model.addAttribute("accountLists", accountLists);
//        return "staff/accountStaffList";
//    }

    @RequestMapping(value = "/addStaffAccount", method = RequestMethod.GET)
    public String addStaffAccount(Model model){
        model.addAttribute("accountStaff", new AccountEntity());
        return "staff/addAccountStaff";
    }

    @RequestMapping(value = "/saveStaffInformation", method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    public String saveStaffInformation(StaffEntity staff, HttpSession session, Model model){
        if(staffService.checkPhone(staff.getPhoneNumber()) != null){
            model.addAttribute("mes","Số điện thoại này đã tồn tại...!");
        }else if(staffService.checkEmail(staff.getEmail()) != null){
            model.addAttribute("mes","Email này đã tồn tại...!");
        }else {
            AccountEntity getAccount = (AccountEntity) session.getAttribute("account");
            getAccount.setPassWord(encoder.encode(getAccount.getPassWord()));
            RoleEntity roleEntity = roleService.getRoleByName("ROLE_STAFF");
            getAccount.setRole(new HashSet<RoleEntity>(Arrays.asList(roleEntity)));
            accountService.addAccount(getAccount);
            staff.setAccount(getAccount);
            staffService.addStaff(staff);
            return "redirect:/staff/manageStaff";
        }
        model.addAttribute("position", position);
        model.addAttribute("staff", new StaffEntity());
        return "staff/addStaffInformation";
    }

    @RequestMapping(value = "/updateAccount/{id}", method = RequestMethod.GET)
    public String updateAccountStaff(@PathVariable(value = "id") int id, Model model) {
        AccountEntity accountID = accountService.getAccount(id);
        model.addAttribute("accountStaff", accountID);
        return "staff/updateAccount";
    }

    @RequestMapping(value = "/saveAccount", method = RequestMethod.POST)
    public String saveAccountStaff(@RequestParam(name = "id") int id,
                                   @RequestParam(name = "username") String username,
                                   @RequestParam(name = "password") String password,
                                   @RequestParam(name = "enable") boolean enable) {
        AccountEntity save = accountService.getAccount(id);
        save.setUserName(username);
        save.setPassWord(encoder.encode(password));
        save.setEnabled(enable);
        accountService.addAccount(save);
        return "redirect:/staff/viewAccount";
    }

    ////////////////////Thong ke so tien////////////////////

    @GetMapping(value = "/commission")
    public String commission(Model model){
        List<CommissionEntity> commission = commissionService.getAll();
        model.addAttribute("commission",commission);
        return "staff/commission";
    }

    @GetMapping(value = "/depositCustomer")
    public String deposit(Model model){
        List<DepositLogEntity> deposit = depositLogService.getAllDeposit();
        model.addAttribute("deposit",deposit);
        return "staff/sumDeposit";
    }

    @RequestMapping(value = "/sumDeposit", method = RequestMethod.POST)
    public String sumDeposit(@RequestParam(name = "day") String day,
                                @RequestParam(name = "day1") String day1, Model model){
        Double sum = depositLogService.sumDeposit(day,day1);
        List<DepositLogEntity> deposit = depositLogService.getAllDeposit();
        model.addAttribute("deposit",deposit);
        model.addAttribute("sum", sum);
        return "staff/sumDeposit";
    }

    @RequestMapping(value = "/sumCommission", method = RequestMethod.POST)
    public String sumCommission(@RequestParam(name = "day") String day,
                                @RequestParam(name = "day1") String day1, Model model){
        Double sum = commissionService.sumCommissions(day,day1);
        List<CommissionEntity> commission = commissionService.getAll();
        model.addAttribute("commission",commission);
        model.addAttribute("sum", sum);
        return "staff/commission";
    }

    ////////////////////////Page/////////////////////////

    @GetMapping("/viewAccount/page/{pageNumber}")
    public String viewDemo(HttpServletRequest request,
                           @PathVariable int pageNumber, Model model, HttpSession session){
        PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("accountLists");
        int pageSize = 3;
        List<AccountEntity> list =(List<AccountEntity>) accountService.findByOrderByIdDescAccount();

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
        request.getSession().setAttribute("accountLists", pages);
        int current = pages.getPage() + 1;
        int begin = Math.max(1, current - list.size());
        int end = Math.min(begin + 5, pages.getPageCount());
        int totalPageCount = pages.getPageCount();
        String baseUrl = "/staff/viewAccount/page/";

        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("baseUrl", baseUrl);
        model.addAttribute("accountLists", pages);

        return "staff/account";
    }

    @RequestMapping(value = "/viewAccount",method = RequestMethod.GET)
    public String viewAccount(Model model, HttpServletRequest request, RedirectAttributes redirect){
        request.getSession().setAttribute("accountLists", null);
        return "redirect:/staff/viewAccount/page/1";
    }

    @PostMapping("/viewAccount/search/{pageNumber}")
    public String search(@RequestParam("search") String name, Model model, HttpServletRequest request,
                         @PathVariable int pageNumber,HttpSession session) {
        List<AccountEntity> account = accountService.searchAccountUserName(name);
        if(account.isEmpty()) {
            PagedListHolder<?> pagedListHolder = (PagedListHolder<?>) request.getSession().getAttribute("accountLists");
            pagedListHolder = new PagedListHolder<>(account);
            model.addAttribute("accountLists", pagedListHolder);
            return "staff/account";
        }
        PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("accountLists");
        int pagesize = 3;
        pages = new PagedListHolder<>(account);
        pages.setPageSize(pagesize);

        final int goToPage = pageNumber - 1;
        if (goToPage <= pages.getPageCount() && goToPage >= 0) {
            pages.setPage(goToPage);
        }
        request.getSession().setAttribute("accountLists", pages);
        int current = pages.getPage() + 1;
        int begin = Math.max(1, current - account.size());
        int end = Math.min(begin + 5, pages.getPageCount());
        int totalPageCount = pages.getPageCount();
        String baseUrl = "/staff/viewAccount/page/";
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("baseUrl", baseUrl);
        model.addAttribute("accountLists", pages);
        return "staff/account";
    }
}
