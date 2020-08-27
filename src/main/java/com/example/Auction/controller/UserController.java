package com.example.Auction.controller;

import com.example.Auction.entity.*;
import com.example.Auction.repository.CategoryRepository;
import com.example.Auction.repository.ImageRepository;
import com.example.Auction.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private CategoryRepository categoryRepo;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private ImageRepository imageRepo;
    @Autowired
    private AuctionService auctionService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CreditService creditService;
    @Autowired
    private TransactionLogService transactionLogService;
    @Autowired
    private BidService bidService;
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private BCryptPasswordEncoder encoder;


    private void setCategoryDropDowList(Model model) {
        List<CategoryEntity> categoryList = (List<CategoryEntity>) categoryRepo.findAll();
        if (!categoryList.isEmpty()) {
            Map<Integer, String> categoryMap = new LinkedHashMap<>();
            for (CategoryEntity categoryEntity : categoryList) {
                categoryMap.put(categoryEntity.getId(), categoryEntity.getCategory());
            }
            model.addAttribute("categoryList", categoryMap);
        }
    }

    private void setGoodsByCustomerDropDowList(Model model,HttpSession session) {
        CustomerEntity customer = customerService.getCustomerByAccountName((String) session.getAttribute("accountName"));
        List<GoodsEntity> goodsList = (List<GoodsEntity>) goodsService.getAllGoodsStatusLike(customer.getId());
        if (!goodsList.isEmpty()) {
            Map<Integer, String> goodsMap = new LinkedHashMap<>();
            for (GoodsEntity goodsEntity : goodsList) {
                goodsMap.put(goodsEntity.getId(), goodsEntity.getGoodName());
            }
            model.addAttribute("listGoods", goodsMap);
        }
    }


    @RequestMapping(value = "/listGood",method = RequestMethod.GET)
    public String goodListOfCustomer(Model model, HttpServletRequest request, RedirectAttributes redirect){
        request.getSession().setAttribute("goodsList", null);

        if(model.asMap().get("success") != null)
            redirect.addFlashAttribute("success",model.asMap().get("success").toString());
        return "redirect:/user/myGoodsList/page/1";
    }

    @GetMapping("/myGoodsList/page/{pageNumber}")
    public String goodListView(HttpServletRequest request,
                           @PathVariable int pageNumber, Model model, HttpSession session){
        PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("goodsList");
        int pageSize = 10;
        CustomerEntity customer = customerService.getCustomerByAccountName((String) session.getAttribute("accountName"));
        List<GoodsEntity> list =(List<GoodsEntity>) goodsService.getAllGoodOfCustomerOrderById(customer.getId());
        List<GoodsEntity> newList = new ArrayList<>();
        for(GoodsEntity goods : list){
            List<ImageEntity> imageList =(List<ImageEntity>) imageService.getListImageByGoodId(goods.getId());
            goods.setImageList(imageList);
            newList.add(goods);
        }

        System.out.println(newList.size());
        if (pages == null) {
            pages = new PagedListHolder<>(newList);
            pages.setPageSize(pageSize);
        } else {
            final int goToPage = pageNumber - 1;
            if (goToPage <= pages.getPageCount() && goToPage >= 0) {
                pages.setPage(goToPage);
            }
        }
        request.getSession().setAttribute("goodsList", pages);
        int current = pages.getPage() + 1;
        int begin = Math.max(1, current - list.size());
        int end = Math.min(begin + 5, pages.getPageCount());
        int totalPageCount = pages.getPageCount();
        String baseUrl = "/user/myGoodsList/page/";

        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("baseUrl", baseUrl);
        model.addAttribute("good", pages);

        return "user/viewListGood";
    }

    @PostMapping("/myGoodsList/searchGoods/{pageNumber}")
    public String searchGoods(@RequestParam("goodsName") String name, Model model, HttpServletRequest request,
                         @PathVariable int pageNumber,HttpSession session) {
        CustomerEntity customer = customerService.getCustomerByAccountName((String) session.getAttribute("accountName"));
        List<GoodsEntity> goodList = goodsService.searchGoodsByName(customer.getId(),name);
        List<GoodsEntity> newList = new ArrayList<>();
        for(GoodsEntity goods : goodList){
            List<ImageEntity> imageList =(List<ImageEntity>) imageService.getListImageByGoodId(goods.getId());
            goods.setImageList(imageList);
            newList.add(goods);
        }

        if(newList.isEmpty()) {
            model.addAttribute("message", "Search results not available");
            model.addAttribute("goodsList",newList);
            return "user/viewListGood";
        }
        PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("goodsList");
        int pagesize = 10;
        pages = new PagedListHolder<>(newList);
        pages.setPageSize(pagesize);

        final int goToPage = pageNumber - 1;
        if (goToPage <= pages.getPageCount() && goToPage >= 0) {
            pages.setPage(goToPage);
        }
        request.getSession().setAttribute("goodsList", pages);
        int current = pages.getPage() + 1;
        int begin = Math.max(1, current - goodList.size());
        int end = Math.min(begin + 5, pages.getPageCount());
        int totalPageCount = pages.getPageCount();
        String baseUrl = "/user/myGoodsList/page/";
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("baseUrl", baseUrl);
        model.addAttribute("good", pages);
        return "user/viewListGood";
    }

    @RequestMapping(value = "/formNewGood",method = RequestMethod.GET)
    public String formNewGood(Model model){
        model.addAttribute("good",new GoodsEntity());
        setCategoryDropDowList(model);
        return "user/registerGoods";
    }

//    @RequestMapping(value = "/searchGoods",method = RequestMethod.POST)
//    public String searchGoods(@RequestParam("goodsName") String goodsName,Model model,HttpSession session){
//        CustomerEntity customer = customerService.getCustomerByAccountName((String) session.getAttribute("accountName"));
//        List<GoodsEntity> listGoods = goodsService.searchGoodsByName(customer.getId(),goodsName);
//        if(listGoods.isEmpty()) {
//            model.addAttribute("message", "Search results not available");
//            model.addAttribute("good",listGoods);
//            return "user/viewListGood";
//        }
//        model.addAttribute("good",listGoods);
//        return "user/viewListGood";
//    }

    @RequestMapping(value = "/newGood",method = RequestMethod.POST)
    public String addGood(Model model,HttpSession session,GoodsEntity goods,@RequestParam("photo") MultipartFile[] photo){
        CustomerEntity customer = customerService.getCustomerByAccountName((String) session.getAttribute("accountName"));
        CategoryEntity category = categoryRepo.findById(goods.getCategory().getId());
        goods.setCustomer(customer);
        goods.setCategory(category);
        try {
            goods.setStatus("New");
            List<ImageEntity> imgList = new ArrayList<>();
            for(MultipartFile file: photo){
                ImageEntity imageEntity = new ImageEntity();
                Path path = Paths.get("uploads/");
                try {
                    InputStream inputStream = file.getInputStream();
                    Files.copy(inputStream, path.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
                    imageEntity.setUrl(file.getOriginalFilename().toLowerCase());
                    imgList.add(imageEntity);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            goods.setImageList(imgList);
            GoodsEntity newGood = goodsService.addGoods(goods);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        model.addAttribute("good",new GoodsEntity());
        model.addAttribute("message","insert goods success");
//        setCategoryDropDowList(model);
        return "redirect:/user/listGood";
    }

    @RequestMapping(value = "/formNewAuction",method = RequestMethod.GET)
    public String formNewAuction(Model model,HttpSession session){
        setGoodsByCustomerDropDowList(model,session);
        model.addAttribute("auction",new AuctionEntity());
        return "user/registerAuction";
    }

    @RequestMapping(value = "/updateGood/{id}",method = RequestMethod.GET)
    public String formUpdateGood(@PathVariable(value = "id") int id, Model model,HttpSession session){
        GoodsEntity goods = goodsService.getGoodByID(id);
        if(goods.getStatus().equals("Start")){
            return "redirect:/user/listGood";
        }else{
            setCategoryDropDowList(model);
            model.addAttribute("goodsUpdate", goods);
            return "user/updateGood";
        }
    }

    @RequestMapping(value = "/saveGood", method = RequestMethod.POST)
    public String saveGoodUpdate(Model model,GoodsEntity goodsEntity,HttpSession session) throws Exception {
        goodsService.updateGoods(goodsEntity);
        model.addAttribute("message","update goods success");
        return "redirect:/user/listGood";
    }

    @RequestMapping(value = "/deleteGood/{id}",method = RequestMethod.GET)
    public String deleteGood(@PathVariable(value = "id") int id, Model model){
        GoodsEntity goods = goodsService.getGoodByID(id);
        if(goods.getStatus().equals("New")){
            goodsService.deleteGoods(goods);
            model.addAttribute("message","delete goods success");
            return "redirect:/user/listGood";
        }
        return "redirect:/user/listGood";
    }

    @RequestMapping(value = "/newAuction",method = RequestMethod.POST)
    public String addNewAuction(AuctionEntity auctionEntity, Model model, HttpSession session){
        AccountEntity account = accountService.getAccountByUserName((String) session.getAttribute("accountName"));
        auctionEntity.setCustomer(account.getCustomer());
        AuctionEntity newAuction = new AuctionEntity();

        if(auctionEntity.getDesiredPrice() < auctionEntity.getPriceStart()){
            model.addAttribute("mess1","Giá mong muốn phải lớn hơn giá khởi điểm");
            setGoodsByCustomerDropDowList(model,session);
            model.addAttribute("auction",new AuctionEntity());
            return "user/registerAuction";
        }else  if(auctionEntity.getDateStart().compareTo(LocalDateTime.now()) <= 0) {
            model.addAttribute("mess1","Ngày bắt đầu phải lớn hơn ngày hiện tại");
            setGoodsByCustomerDropDowList(model,session);
            model.addAttribute("auction",new AuctionEntity());
            return "user/registerAuction";
        }else if(auctionEntity.getDateEnd().compareTo(auctionEntity.getDateStart()) > 0){
            auctionEntity.setAuctionStatus("New");
            GoodsEntity goodsEntity = goodsService.getGoodByID(auctionEntity.getGoods().getId());
            goodsEntity.setStatus("Register sell");
            goodsService.updateGoods(goodsEntity);
            try {
                newAuction = auctionService.addAuction(auctionEntity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            String err = "Ngày kết thúc phải lớn hơn ngày bắt đầu!";
            model.addAttribute("mess1",err);
            setGoodsByCustomerDropDowList(model,session);
            model.addAttribute("auction",new AuctionEntity());
            return "user/registerAuction";
        }
        if(newAuction == null) {
            model.addAttribute("auction", new AuctionEntity());
            setCategoryDropDowList(model);
            return "user/registerAuction";
        }else {
            return "redirect:/user/myAuction";
        }
    }


    @RequestMapping(value = "/creditCard",method = RequestMethod.GET)
    public String creditPage(Model model, HttpServletRequest request, RedirectAttributes redirect,HttpSession session){
        CustomerEntity customer = customerService.getCustomerByAccountName((String) session.getAttribute("accountName"));
        if(creditService.getCreditByCustomerId(customer.getId()) == null){
            return "user/createCredit";
        }else{
            request.getSession().setAttribute("transactionList", null);

            if(model.asMap().get("success") != null)
                redirect.addFlashAttribute("success",model.asMap().get("success").toString());
            return "redirect:/user/creditCard/page/1";
        }
    }

    @GetMapping("/creditCard/page/{pageNumber}")
    public String viewTransaction(HttpServletRequest request,
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
        String baseUrl = "/user/creditCard/page/";

        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("baseUrl", baseUrl);
        model.addAttribute("transactionLog", pages);
        model.addAttribute("credit",credit);

        return "user/credit";
    }

    @PostMapping("/creditCard/search/{pageNumber}")
    public String searchTransaction(@RequestParam("dateStart") String dateStart,@RequestParam("dateEnd") String dateEnd, Model model, HttpServletRequest request,
                         @PathVariable int pageNumber,HttpSession session) {
        LocalDateTime date1 = LocalDateTime.parse(dateStart);
        LocalDateTime date2 = LocalDateTime.parse(dateEnd);
        CustomerEntity customer = customerService.getCustomerByAccountName((String) session.getAttribute("accountName"));
        CreditEntity credit = creditService.getCreditByCustomerId(customer.getId());
        List<TransactionLogEntity> transactionList = transactionLogService.getAllTransactionLogByDateLogBetween(credit.getId(),date1,date2);
        if(transactionList.isEmpty()) {
            model.addAttribute("message", "Search results not available");
            model.addAttribute("listLog",transactionList);
            return "user/credit";
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
        String baseUrl = "/user/creditCard/page/";
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("baseUrl", baseUrl);
        model.addAttribute("transactionLog", pages);
        model.addAttribute("credit",credit);
        return "user/credit";
    }

    @RequestMapping(value = "/createCard",method = RequestMethod.GET)
    public String createCredit(HttpSession session,Model model){
        CustomerEntity customer = customerService.getCustomerByAccountName((String) session.getAttribute("accountName"));
        CreditEntity credit = new CreditEntity();
        Long card = credit.createCredit();
        while (creditService.isCreditAlreadyPresent(card.toString())){
            card = credit.createCredit();
        }
        credit.setCardNumber(card.toString());
        credit.setAmount(0.0);
        credit.setCustomer(customer);
        CreditEntity newCredit = creditService.createCredit(credit);
        model.addAttribute("credit",newCredit);
        List<TransactionLogEntity> transactionList = transactionLogService.getAllTransactionLogByCreditId(credit.getId());
        model.addAttribute("listLog",transactionList);
        return "user/credit";
    }




    @RequestMapping(value = "/myAuction", method = RequestMethod.GET)
    public String history(Model model, HttpServletRequest request, RedirectAttributes redirect){
        request.getSession().setAttribute("auctionList", null);
            if(model.asMap().get("success") != null)
                redirect.addFlashAttribute("success",model.asMap().get("success").toString());
            return "redirect:/user/myAuction/page/1";
        }


    @GetMapping("/myAuction/page/{pageNumber}")
    public String searchAuction(HttpServletRequest request,
                           @PathVariable int pageNumber, Model model, HttpSession session) {
        PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("auctionList");
        int pageSize = 8;
        CustomerEntity customer = customerService.getCustomerByAccountName((String) session.getAttribute("accountName"));
        List<AuctionEntity> list = (List<AuctionEntity>) auctionService.getAllAuctionOfCustomerOrderById(customer.getId());
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
        request.getSession().setAttribute("auctionList", pages);
        int current = pages.getPage() + 1;
        int begin = Math.max(1, current - list.size());
        int end = Math.min(begin + 5, pages.getPageCount());
        int totalPageCount = pages.getPageCount();
        String baseUrl = "/user/myAuction/page/";

        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("baseUrl", baseUrl);
        model.addAttribute("auction", pages);

        return "user/myAuction";
    }

    @GetMapping("/myBid")
    public String  bidLog(Model model, HttpServletRequest request, RedirectAttributes redirect){
        request.getSession().setAttribute("bidList", null);

        if(model.asMap().get("success") != null)
            redirect.addFlashAttribute("success",model.asMap().get("success").toString());
        return "redirect:/user/myBid/page/1";
    }

    @GetMapping("/myBid/page/{pageNumber}")
    public String viewDemo(HttpServletRequest request,
                           @PathVariable int pageNumber, Model model, HttpSession session){
        PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("bidList");
        int pageSize = 8;
        CustomerEntity customer = customerService.getCustomerByAccountName((String) session.getAttribute("accountName"));
        List<BidEntity> list =(List<BidEntity>) bidService.getAllBidOfCustomerOrderById(customer.getId());
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
        request.getSession().setAttribute("bidList", pages);
        int current = pages.getPage() + 1;
        int begin = Math.max(1, current - list.size());
        int end = Math.min(begin + 5, pages.getPageCount());
        int totalPageCount = pages.getPageCount();
        String baseUrl = "/user/myBid/page/";

        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("baseUrl", baseUrl);
        model.addAttribute("bid", pages);

        return "user/bidLog";
    }

    @RequestMapping(value = "/myBid/searchBid/{pageNumber}",method = RequestMethod.POST)
    public String searchBid(@RequestParam("dateStart") String dateStart,@RequestParam("dateEnd") String dateEnd, Model model, HttpServletRequest request,
                         @PathVariable int pageNumber,HttpSession session) {
        LocalDateTime date1 = LocalDateTime.parse(dateStart);
        LocalDateTime date2 = LocalDateTime.parse(dateEnd);
        CustomerEntity customer = customerService.getCustomerByAccountName((String) session.getAttribute("accountName"));
        List<BidEntity> bidList = bidService.getAllBidLogBetWeen(customer.getId(),date1,date2);
        if(bidList.isEmpty()) {
            model.addAttribute("message", "Search results not available");
            model.addAttribute("bidList",bidList);
            return "user/bidLog";
        }
        PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("bidList");
        int pagesize = 8;
        pages = new PagedListHolder<>(bidList);
        pages.setPageSize(pagesize);

        final int goToPage = pageNumber - 1;
        if (goToPage <= pages.getPageCount() && goToPage >= 0) {
            pages.setPage(goToPage);
        }
        request.getSession().setAttribute("bidList", pages);
        int current = pages.getPage() + 1;
        int begin = Math.max(1, current - bidList.size());
        int end = Math.min(begin + 5, pages.getPageCount());
        int totalPageCount = pages.getPageCount();
        String baseUrl = "/user/myBidDemo/page/";
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("baseUrl", baseUrl);
        model.addAttribute("bid", pages);
        return "user/bidLog";
    }

    @PostMapping("/myAuction/searchAuction/{pageNumber}")
    public String search(@RequestParam("dateStart") String dateStart,@RequestParam("dateEnd") String dateEnd, Model model, HttpServletRequest request,
                         @PathVariable int pageNumber,HttpSession session) {
        LocalDateTime date1 = LocalDateTime.parse(dateStart);
        LocalDateTime date2 = LocalDateTime.parse(dateEnd);
        CustomerEntity customer = customerService.getCustomerByAccountName((String) session.getAttribute("accountName"));
        List<AuctionEntity> listAuction = auctionService.getAllAuctionBetween(customer.getId(),date1,date2);
        if(listAuction.isEmpty()) {
            model.addAttribute("message", "Search results not available");
            model.addAttribute("bidList",listAuction);
            return "user/myAuction";
        }
        PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("auctionList");
        int pagesize = 8;
        pages = new PagedListHolder<>(listAuction);
        pages.setPageSize(pagesize);

        final int goToPage = pageNumber - 1;
        if (goToPage <= pages.getPageCount() && goToPage >= 0) {
            pages.setPage(goToPage);
        }
        request.getSession().setAttribute("auctionList", pages);
        int current = pages.getPage() + 1;
        int begin = Math.max(1, current - listAuction.size());
        int end = Math.min(begin + 5, pages.getPageCount());
        int totalPageCount = pages.getPageCount();
        String baseUrl = "/user/myAuction/page/";
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("baseUrl", baseUrl);
        model.addAttribute("auction", pages);
        return "user/myAuction";
    }



    @RequestMapping(value = "/updateAuction/{id}", method = RequestMethod.GET)
    public String updateAuction(@PathVariable(value = "id") int id, Model model,HttpSession session) {
        AuctionEntity auction = auctionService.getAuction(id);
        // kiểm tra ngày dateStart có lớn hơn ngày hiện tại k
        // nếu lớn hơn thì kq trả về 1 , 1 lớn hơn 0 sẽ thực hiện chức năng
        if (auction.getDateStart().compareTo(LocalDateTime.now()) > 0 && auction.getGoods().getStatus().equals("Register sell")) {
            setGoodsByCustomerDropDowList(model,session);
            model.addAttribute("auctionUpdate", auction);
            return "user/updateAuction";
        } else {
            return "redirect:/user/myAuction";
        }
    }

    @RequestMapping(value = "/deleteAuction/{id}",method = RequestMethod.GET)
    public String deleteAuction(@PathVariable(value = "id") int id){
        AuctionEntity auction = auctionService.getAuction(id);
        // kiểm tra ngày dateStart có lớn hơn ngày hiện tại k
        // nếu lớn hơn thì kq trả về 1 , 1 lớn hơn 0 sẽ thực hiện chức năng
        if(auction.getDateStart().compareTo(LocalDateTime.now()) > 0 && auction.getGoods().getStatus().equals("Register sell")){
            GoodsEntity goodsEntity = auction.getGoods();
            goodsEntity.setStatus("New");
            goodsService.updateGoods(goodsEntity);
            auctionService.deleteAuction(auction);
            return "redirect:/user/myAuction";
        }
        return "redirect:/user/myAuction";
    }


    @RequestMapping(value = "/saveAuction", method = RequestMethod.POST)
    public String saveAuctionUpdate(Model model,AuctionEntity auctionEntity,HttpSession session) throws Exception {
        if(auctionEntity.getPriceStart() < auctionEntity.getDesiredPrice()){
            if (auctionEntity.getDateEnd().compareTo(auctionEntity.getDateStart()) > 0){
                auctionEntity.setAuctionStatus("New");
                AuctionEntity newAuction = auctionService.updateAuction(auctionEntity);
                return "redirect:/user/myAuction";
            } else {
                String err = "DateEnd phai lon hon DateStar!";
                model.addAttribute("mess2", err);
                setGoodsByCustomerDropDowList(model,session);
                model.addAttribute("auctionUpdate", auctionEntity);
                return "user/updateAuction";
            }
        }else {
            String message = "Giá mong muốn phải lớn hơn giá khởi điểm";
            model.addAttribute("message", message);
            setGoodsByCustomerDropDowList(model,session);
            model.addAttribute("auctionUpdate", auctionEntity);
            return "user/updateAuction";
        }
    }


    @RequestMapping(value = "/myOrders",method = RequestMethod.GET)
    public String myOrders(Model model, HttpServletRequest request, RedirectAttributes redirect){
        request.getSession().setAttribute("goodsList", null);

        if(model.asMap().get("success") != null)
            redirect.addFlashAttribute("success",model.asMap().get("success").toString());
        return "redirect:/user/myOrders/page/1";
    }

    @GetMapping("/myOrders/page/{pageNumber}")
    public String viewOrders(HttpServletRequest request,
                           @PathVariable int pageNumber, Model model, HttpSession session){
        PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("goodsList");
        int pageSize = 10;
        CustomerEntity customer = customerService.getCustomerByAccountName((String) session.getAttribute("accountName"));
        List<OrdersEntity> list =(List<OrdersEntity>) ordersService.getAllOrdersOfCustomerOrderById(customer.getCustomerName());

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
        request.getSession().setAttribute("goodsList", pages);
        int current = pages.getPage() + 1;
        int begin = Math.max(1, current - list.size());
        int end = Math.min(begin + 5, pages.getPageCount());
        int totalPageCount = pages.getPageCount();
        String baseUrl = "/user/myOrders/page/";

        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("baseUrl", baseUrl);
        model.addAttribute("order", pages);

        return "user/listOrders";
    }

    @RequestMapping(value = "/listOrderForCheck",method = RequestMethod.GET)
    public String lisOrderForCheck(Model model,HttpSession session){
        CustomerEntity customer = customerService.getCustomerByAccountName((String) session.getAttribute("accountName"));
        List<OrdersEntity> listOrder = ordersService.getAllOrdersOfCustomerOrderById(customer.getCustomerName());
        List<OrdersEntity> listForCheck = new ArrayList<>();
        for(OrdersEntity orders : listOrder){
            if(orders.getStatus().equals("unconfirmed")){
                listForCheck.add(orders);
            }
        }
        if(listForCheck.isEmpty()){
        List<OrdersEntity> listOrdersView = ordersService.getAllOrdersSuccess(customer.getId(),"confirmed");
        model.addAttribute("listOrder",listOrdersView);
        model.addAttribute("message","Không có đơn hàng cần xác nhận");
        return "user/listOrdersForCheckout";
        }
        model.addAttribute("listOrderForCheckout",listForCheck);
        return "user/listOrdersForCheckout";
    }

    @RequestMapping(value = "/checkout/{id}",method = RequestMethod.GET)
    public String formCheckout(Model model,@PathVariable(value = "id") int id){
        OrdersEntity orders = ordersService.getOrderById(id);
        model.addAttribute("orders",orders);
        return "user/checkout";
    }

    @RequestMapping(value = "/checkout",method = RequestMethod.POST)
    public String checkout(Model model,HttpSession session,OrdersEntity orders){
        OrdersEntity newOrders = ordersService.checkoutOrders(orders);
        if(newOrders == null){
            return "redirect:user/checkout/{id}";
        }
        return "redirect:/user/myOrders";
    }

    @RequestMapping(value = "/profile",method = RequestMethod.GET)
    public String profile(Model model,HttpSession session){
        CustomerEntity customer = customerService.getCustomerByAccountName((String) session.getAttribute("accountName"));
        model.addAttribute("customer",customer);
        return "user/profile";
    }

    @RequestMapping(value = "/updateCustomer",method = RequestMethod.POST)
    public String formUpdateProfile(CustomerEntity customer, Model model,HttpSession session){
        AccountEntity account = accountService.getAccountByUserName((String) session.getAttribute("accountName"));
        customer.setAccount(account);
        CustomerEntity newCustomer = customerService.updateCustomer(customer);
        model.addAttribute("customer", newCustomer);
        return "redirect:/user/profile";
    }

    @RequestMapping(value = "/changePasswordForm",method = RequestMethod.GET)
    public String changePassPage(Model model,HttpSession session){
        return "user/changerPassword";
    }

    @RequestMapping(value = "/changePassword",method = RequestMethod.POST)
    public String changePassword(@RequestParam(value = "newPass") String newPass,
                                 @RequestParam(value = "confirmPass") String confirmPass,Model model,HttpSession session){
        if(newPass.length() >= 8 && confirmPass.length() >= 8) {
            if (newPass.equals(confirmPass)) {
                AccountEntity account = accountService.getAccountByUserName((String) session.getAttribute("accountName"));
                account.setPassWord(encoder.encode(confirmPass));
                AccountEntity newAccount = accountService.addAccount(account);
                model.addAttribute("successMessage", "Change Password success");
                return "user/changerPassword";
            } else {
                model.addAttribute("successMessage", "Change Password Unsuccessful!");
                model.addAttribute("message", "New password and confirm password does not match");
                return "user/changerPassword";
            }
        }
        model.addAttribute("message", "Password must be at least 8 characters");
        return "user/changerPassword";
    }

}
