package com.example.Auction.service;

import com.example.Auction.entity.*;
import com.example.Auction.repository.AuctionRepository;
import com.example.Auction.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuctionService {
    @Autowired
    private AuctionRepository auctionRepo;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private CategoryRepository categoryRepo;
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private BidService bidService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CreditService creditService;
    @Autowired
    private CommissionLevelService commissionLevelService;
    @Autowired
    private CommissionService commissionService;
    @Autowired
    private TransactionLogService transactionLogService;
    @Autowired
    private OrdersService ordersService;


    public AuctionEntity getAuction(int id){
        return auctionRepo.findById(id);
    }

    public List<AuctionEntity> getAllAuction(){
        return (List<AuctionEntity>) auctionRepo.findAll();
    }
    public List<AuctionEntity> getAllAuctionOrderById(String status){
        return auctionRepo.findByAuctionStatusOrderByDateStartAsc(status);
    }
    public List<AuctionEntity> searchAuction(String name){
        return auctionRepo.findByGoods_GoodNameLike(name);
    }
    public List<AuctionEntity> searchAuctionByCategory(String name){
        return auctionRepo.findByGoods_Category_Category(name);
    }

    public AuctionEntity addAuction(AuctionEntity auction) throws Exception {
        AuctionEntity auctionEntity = auctionRepo.save(auction);
        return auctionEntity;
    }

    public AuctionEntity updateAuction(AuctionEntity auction) throws Exception {
//        GoodsEntity goods = auction.getGoods();
//        CategoryEntity category = categoryRepo.findById(auction.getGoods().getCategory().getId());
//        goods.setCategory(category);
//        goodsService.updateGoods(goods);
        AuctionEntity auctionEntity = auctionRepo.save(auction);
        return auctionEntity;
    }

    public void deleteAuction(AuctionEntity auction){
        auctionRepo.delete(auction);
    }

    public List<AuctionEntity> getAuctionByCustomerID(int id){
        return auctionRepo.findByCustomerId(id);
    }

    public List<AuctionEntity> getAllAuctionOfCustomerOrderById(int id){
        List<AuctionEntity> listAuction = null;
        try{
            listAuction = auctionRepo.findByCustomerIdOrderByIdDesc(id);
        }catch (Exception e){
            System.out.println(e);
        }
        return listAuction;
    }
    public List<AuctionEntity> getAllAuctionRecently(LocalDateTime date1,LocalDateTime date2){
        List<AuctionEntity> listAuction = null;
        try{
            listAuction = auctionRepo.findByDateStartBetweenOrderByDateStartAsc(date1, date2);

        }catch (Exception e){
            System.out.println(e);
        }
        return listAuction;
    }

    public List<AuctionEntity> searchAuctionByCategoryOrderBy(String name){
        return auctionRepo.findByGoods_Category_CategoryAndAuctionStatusOrderByDateStartAsc(name,"Start");
    }

    public AuctionEntity getAuctionByGoodsId(int id){
        return auctionRepo.findByGoods_IdAndAuctionStatus(id,"Start");
    }

    public List<AuctionEntity> getAllAuctionBetween(int id,LocalDateTime date1,LocalDateTime date2){
        return auctionRepo.findByCustomer_IdAndDateStartBetween(id,date1,date2);
    }






    @Scheduled(fixedRate = 3000)
    public void startAuction(){
        List<AuctionEntity> listAuction = getAllAuction();
        for(AuctionEntity auction : listAuction){
            if(auction.getDateStart().compareTo(LocalDateTime.now()) <= 0 && auction.getAuctionStatus().equals("New")){
                auction.setAuctionStatus("Start");
                GoodsEntity goodsEntity = goodsService.getGoodByID(auction.getGoods().getId());
                goodsEntity.setStatus("Start");
                goodsService.startGoods(goodsEntity);
                try {
                    addAuction(auction);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Scheduled(fixedRate = 5000)
    public void closeAuction(){
        List<AuctionEntity> auctionList = getAllAuction();
        for(AuctionEntity auction : auctionList){
            if(auction.getDateEnd().compareTo(LocalDateTime.now()) <= 0 && auction.getAuctionStatus().equals("Start")){
                auction.setAuctionStatus("Close");
                GoodsEntity goodsEntity = goodsService.getGoodByID(auction.getGoods().getId());
                AuctionEntity newAuction = auctionRepo.save(auction);

                if(newAuction.getDesiredPrice() <= newAuction.bidPriceMaxiMum() && newAuction.getBidList() != null){
                    goodsEntity.setStatus("Sell success");
                    BidEntity bid = bidService.getBidOfCustomerHaveBidMax(newAuction.bidPriceMaxiMum(),newAuction.getId());
                    CustomerEntity customerBidder = customerService.getCustomer(bid.getCustomer().getId());
                    CreditEntity creditBidder = creditService.getCreditByCustomerId(customerBidder.getId());
                    Double amountCreditBidder = creditBidder.getAmount() - newAuction.bidPriceMaxiMum();
                    creditBidder.setAmount(amountCreditBidder);
                    creditService.updateCredit(creditBidder);

                    //lưu nhật ký giao dịch của bidder
                    TransactionLogEntity transactionLog = new TransactionLogEntity();
                    transactionLog.setAmount(newAuction.bidPriceMaxiMum());
                    transactionLog.setBalance(amountCreditBidder);
                    transactionLog.setDateLog(LocalDateTime.now());
                    transactionLog.setContent("Pay for your order " + newAuction.getGoods().getGoodName());
                    transactionLog.setLogType("Purchase");
                    transactionLog.setCredit(creditBidder);
                    TransactionLogEntity newTransactionLog = transactionLogService.addTransactionLog(transactionLog);

                    //luu đơn hàng
                    OrdersEntity orders = new OrdersEntity();
                    orders.setAuction(newAuction);
                    orders.setCustomer(customerBidder);
                    orders.setCustomerAddress(customerBidder.getAddress());
                    orders.setCustomerName(customerBidder.getCustomerName());
                    orders.setPhone_Number(customerBidder.getPhoneNumber());
                    orders.setGoods_Name(newAuction.getGoods().getGoodName());
                    orders.setDateWin(LocalDateTime.now());
                    orders.setStatus("unconfirmed");
                    OrdersEntity newOrders = ordersService.addOrders(orders);

                    //gửi mail bidder
                    SimpleMailMessage messageBidder = new SimpleMailMessage();
                    messageBidder.setTo(customerBidder.getEmail());
                    messageBidder.setSubject("Thông báo kết quả phiên đấu giá số "+ newAuction.getId());
                    messageBidder.setText("Xin chào "+ customerBidder.getCustomerName() + "! \n" +
                            "Bạn đã dành chiến thắng trong phiên đấu giá số " + newAuction.getId() +  "!  \n" +
                            "Với giá trúng thầu là " + newAuction.getMaxBid() + " VNĐ \n" +
                            "Tên sản phẩm đấu giá là " + newAuction.getGoods().getGoodName() + "\n" +
                            "Số dư chính của bạn là " + formatAmount(amountCreditBidder) + " VNĐ \n" +
                            "Mời bạn kiểm tra lại thông tin và số dư của thẻ tín dụng của mình và xác nhận đơn hàng. \n" +
                            "Cảm ơn bạn đã tin dùng dịch vụ của chúng tôi!  \n" +
                            "Mọi thắc mắc xin liên hệ ngay với chúng tôi qua SDT: 0966709935 để được tư vấn và hỗ trợ!");
                    this.emailSender.send(messageBidder);

                    //get dữ liệu để tính tiền hoa hồng
                    CommissionEntity commission = new CommissionEntity();
                    List<CommissionLevelEntity> listLevel = commissionLevelService.getAllCommissionLevel();
                    CommissionLevelEntity commissionLevel = commissionLevelService.getIncrement(listLevel,newAuction.bidPriceMaxiMum());
                    Double incrementCommission = commissionLevel.getIncrement();
                    Double amountCommission = newAuction.bidPriceMaxiMum() * incrementCommission;
                    commission.setAmount(amountCommission);
                    commission.setAuction(newAuction);
                    commission.setDatePlus(LocalDateTime.now());
                    CommissionEntity newCommission = commissionService.addCommission(commission);

                    //cập nhật credit seller
                    CustomerEntity customerSeller = newAuction.getCustomer();
                    CreditEntity creditSeller = creditService.getCreditByCustomerId(customerSeller.getId());
                    creditSeller.setAmount(creditSeller.getAmount() + (newAuction.bidPriceMaxiMum() - amountCommission));
                    creditService.updateCredit(creditSeller);


                    //lưu nhật ký giao dịch của seller
                    TransactionLogEntity transactionLogSeller = new TransactionLogEntity();
                    Double amountAuction = newAuction.bidPriceMaxiMum() - amountCommission;
                    transactionLogSeller.setAmount(amountAuction);
                    transactionLogSeller.setBalance(creditSeller.getAmount());
                    transactionLogSeller.setCredit(creditSeller);
                    transactionLogSeller.setContent("Add the sale proceeds by the auction " + newAuction.getId());
                    transactionLogSeller.setLogType("Recharge");
                    transactionLogSeller.setDateLog(LocalDateTime.now());
                    TransactionLogEntity newTransactionLogSeller = transactionLogService.addTransactionLog(transactionLogSeller);

                    //gửi mail seller
                    SimpleMailMessage messageSeller = new SimpleMailMessage();
                    messageSeller.setTo(newAuction.getCustomer().getEmail());
                    messageSeller.setSubject("Thông báo kết quả phiên đấu giá số "+ newAuction.getId());
                    messageSeller.setText("Xin chào "+ newAuction.getCustomer().getCustomerName() + "! \n" +
                            "Phiên đấu giá của bạn đã thành công với giá thầu lớn nhất là " + newAuction.getMaxBid() + " VNĐ \n" +
                            "Số tiền hoa hồng chúng tôi trừ là " + formatAmount(amountCommission) + " VNĐ\n"+
                            "Số tiền bạn nhận được là " + formatAmount(amountAuction) + " VNĐ \n" +
                            "Số dư chính của bạn là " + formatAmount(creditSeller.getAmount()) + " VNĐ \n" +
                            "Bạn vui lòng kiểm tra lại số dư của thẻ tín dụng của mình!  \n" +
                            "Cảm ơn bạn đã tin dùng dịch vụ cảu AUCTION SYSTEM!  \n" +
                            "Mọi thắc mắc xin liên hệ ngay với chúng tôi qua SDT: 0966709935 để được tư vấn và hỗ trợ!");
                    this.emailSender.send(messageSeller);
                    goodsService.startGoods(goodsEntity);
                }else if(newAuction.getBidList().isEmpty()) {
                    goodsEntity.setStatus("Sell Fails");
                    goodsService.goodsFails(goodsEntity);
                    CustomerEntity customer = newAuction.getCustomer();
                    SimpleMailMessage messageFails = new SimpleMailMessage();
                    messageFails.setTo(customer.getEmail());
                    messageFails.setSubject("Thông báo kết quả phiên đấu giá số "+ newAuction.getId());
                    messageFails.setText("Xin chào "+ customer.getCustomerName() + "! \n" +
                            "Thật đáng tiếc,phiên đấu giá của bạn đã không thành công, vì danh sách đấu giá trống!  \n" +
                            "Vui lòng tạo mới phiên đấu giá khác để tiếp tục đấu giá cho sản phẩm của bạn! \n " +
                            "Mời bạn kiểm tra lại tất cả thông tin trong hệ thống của chúng tôi!  \n" +
                            "Cảm ơn bạn đã tin dùng dịch vụ cảu AUCTION SYSTEM!  \n" +
                            "Mọi thắc mắc xin liên hệ ngay với chúng tôi qua SDT: 0966709935 để được tư vấn và hỗ trợ!");
                    this.emailSender.send(messageFails);
                }else {
                    //nếu giá đấu không lớn hơn giá mong muốn
                    goodsEntity.setStatus("Sell Fails");
                    goodsService.goodsFails(goodsEntity);
                    BidEntity bidEntity = bidService.getBidOfCustomerHaveBidMax(newAuction.bidPriceMaxiMum(),newAuction.getId());
                    bidEntity.setBidStatus("lost");
                    BidEntity newBid = bidService.addBid(bidEntity);
                    CustomerEntity customerBidderFails = customerService.getCustomer(newBid.getCustomer().getId());
                    SimpleMailMessage messageBidderFails = new SimpleMailMessage();
                    messageBidderFails.setTo(customerBidderFails.getEmail());
                    messageBidderFails.setSubject("Thông báo kết quả phiên đấu giá số "+ newAuction.getId());
                    messageBidderFails.setText("Xin chào "+ customerBidderFails.getCustomerName() + "! \n" +
                            "Thật đáng tiếc, phiên đấu giá mà bạn tham gia đã không thành công vì giá mong muốn lớn hơn giá của bạn đưa ra!  \n" +
                            "Hãy tiếp tục tham gia các phiên đấu giá khác trong hệ thống để có cơ hội trúng thầu các sản phẩm mới nhé! \n " +
                            "Bạn vui lòng kiểm tra lại tất cả thông tin trong Website! \n" +
                            "Cảm ơn bạn đã tin dùng dịch vụ của AUCTION SYSTEM!  \n" +
                            "Mọi thắc mắc xin liên hệ ngay với chúng tôi qua SDT: 0966709935 để được tư vấn và hỗ trợ!");
                    this.emailSender.send(messageBidderFails);

                    CustomerEntity customerSellerFails = newAuction.getCustomer();
                    SimpleMailMessage messageSellerFails = new SimpleMailMessage();
                    messageSellerFails.setTo(customerSellerFails.getEmail());
                    messageSellerFails.setSubject("Thông báo kết quả phiên đấu giá số "+ newAuction.getId());
                    messageSellerFails.setText("Xin chào "+ customerSellerFails.getCustomerName() + "! \n" +
                            "Thật đáng tiếc, phiên đấu giá của bạn không thành công vì lý do không có giá thầu nào lơn hơn giá bạn mong muốn!  \n" +
                            "Bạn vui lòng tạo phiên đấu giá mới để tiếp tục đấu giá cho các sản phẩm của mình! \n " +
                            "Bạn vui lòng kiểm tra lại tất cả thông tin trong Website! \n" +
                            "Cảm ơn bạn đã tin dùng dịch vụ của AUCTION SYSTEM!  \n" +
                            "Mọi thắc mắc xin liên hệ ngay với chúng tôi qua SDT: 0966709935 để được tư vấn và hỗ trợ!");
                    this.emailSender.send(messageSellerFails);
                    try {
                        addAuction(auction);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static String formatAmount(Double amount){
        DecimalFormat decimo = new DecimalFormat("###,###,###,###.##");
        String kq = decimo.format(amount);
        return kq;
    }

    public List<AuctionEntity> findByOrderByIdDescAuction(){
        List<AuctionEntity> list;
        try {
            list = auctionRepo.findByOrderByIdDesc();
            return list;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public List<AuctionEntity> searchReportAuctionSuccess(String day, String day1){
        List<AuctionEntity> list;
        try {
            list = auctionRepo.searchReportAuctionSuccess(day, day1);
            return list;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public List<AuctionEntity> searchReportAuctionFailure(String day, String day1){
        List<AuctionEntity> list;
        try {
            list = auctionRepo.searchReportAuctionFailure(day,day1);
            return list;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public List<AuctionEntity> reportAuctionSuccess(){
        List<AuctionEntity> list;
        try {
            list = auctionRepo.reportAuctionSuccess();
            return list;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public List<AuctionEntity> reportAuctionFailure(){
        List<AuctionEntity> list;
        try {
            list = auctionRepo.reportAuctionFailure();
            return list;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public List<AuctionEntity> searchAuctionName(String name, String status){
        name = "%" + name + "%";
        return auctionRepo.searchAuctionName(name,name,status);
    }
}
