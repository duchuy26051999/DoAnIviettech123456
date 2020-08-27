package com.example.Auction.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static java.time.LocalDateTime.now;

@Entity
@Table(name = "auction")
public class AuctionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "customerID")
    private CustomerEntity customer;
    @ManyToOne
    @JoinColumn(name = "goodsID")
    private GoodsEntity goods;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dateStart;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dateEnd;
    private Double priceStart;
    private Double desiredPrice;
    private String auctionStatus;
    @OneToMany(mappedBy = "auction", fetch = FetchType.LAZY)
    private List<OrdersEntity> ordersList;
    @OneToMany(mappedBy = "auction", fetch = FetchType.EAGER)
    private List<BidEntity> bidList;
    @OneToMany(mappedBy = "auction", fetch = FetchType.LAZY)
    private List<CommentsEntity> comments;
    @OneToOne(mappedBy = "auction",fetch = FetchType.LAZY)
    private CommissionEntity commission;


    public AuctionEntity() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public GoodsEntity getGoods() {
        return goods;
    }

    public void setGoods(GoodsEntity goods) {
        this.goods = goods;
    }

    public LocalDateTime getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDateTime dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDateTime getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDateTime dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Double getPriceStart() {
        return priceStart;
    }

    public void setPriceStart(Double priceStart) {
        this.priceStart = priceStart;
    }

    public Double getDesiredPrice() {
        return desiredPrice;
    }

    public void setDesiredPrice(Double desiredPrice) {
        this.desiredPrice = desiredPrice;
    }

    public String getAuctionStatus() {
        return auctionStatus;
    }

    public void setAuctionStatus(String auctionStatus) {
        this.auctionStatus = auctionStatus;
    }

    public List<OrdersEntity> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<OrdersEntity> ordersList) {
        this.ordersList = ordersList;
    }

    public List<BidEntity> getBidList() {
        return bidList;
    }

    public void setBidList(List<BidEntity> bidList) {
        this.bidList = bidList;
    }

    public List<CommentsEntity> getComments() {
        return comments;
    }

    public void setComments(List<CommentsEntity> comments) {
        this.comments = comments;
    }

    public Long getTimeLeft(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dkt = null;
        Date dht = null;
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timeStart = formatter3.format(now());
        String timeStop = formatter3.format(dateEnd);
        try {
            dkt = format.parse(timeStop);
            dht =  format.parse(timeStart);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Long diff = dkt.getTime() - dht.getTime();
        return diff;
    }
    public Long getHours(){
        Long diff = getTimeLeft();
        return diff / 3600000;
    }
    public Long getMinutes(){
        Long diff = getTimeLeft();
        return (diff / (60 * 1000)) % 60;
    }
    public Long getSeconds(){
        Long diff = getTimeLeft();
        return (diff / 1000) %60;
    }

    public String getMaxBid(){
        double maxBid = priceStart;
        for(int i = 0;i < bidList.size(); i++){
            if(bidList.get(i).getBidPrice() > maxBid){
                maxBid = bidList.get(i).getBidPrice();
            }
        }
        DecimalFormat decimo = new DecimalFormat("###,###,###,###.##");
        String kq = decimo.format(maxBid);
        return kq;
    }

    public String formatBid(Double bid){
        DecimalFormat decimo = new DecimalFormat("###,###,###,###.##");
        String kq = decimo.format(bid);
        return kq;
    }

    public String formatPriceStart(){
        DecimalFormat decimo = new DecimalFormat("###,###,###,###.##");
        String priceStartFormat = decimo.format(priceStart);
        return priceStartFormat;
    }
    public String formatPriceDesired(){
        DecimalFormat decimo = new DecimalFormat("###,###,###,###.##");
        String priceDesiredFormat = decimo.format(desiredPrice);
        return priceDesiredFormat;
    }

    public Double bidPriceMaxiMum(){
        Double bidMax = priceStart;
        for(int i = 0; i < bidList.size(); i++){
            if(bidList.get(i).getBidPrice() > bidMax){
                bidMax = bidList.get(i).getBidPrice();
            }
        }
        return bidMax;
    }

    public String formatBidPrice(){
        Locale locale = new Locale("vi","VN");
        NumberFormat n = NumberFormat.getCurrencyInstance(locale);
        n.setRoundingMode(RoundingMode.HALF_UP);
        return n.format(priceStart);
    }
    public String formatDateStart(){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateFormat = format.format(dateStart);
        return dateFormat;
    }
    public String formatDateEnd(){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateFormat = format.format(dateEnd);
        return dateFormat;
    }

    public Long getNextTime(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dkt = null;
        Date dht = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timeStart = formatter.format(now());
        String timeStop = formatter.format(dateStart);
        try {
            dkt = format.parse(timeStop);
            dht =  format.parse(timeStart);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Long diff = dkt.getTime() - dht.getTime();
        return diff;
    }
    public Long getNextHours(){
        Long diff = getNextTime();
        return diff / 3600000;
    }
    public Long getNextMinutes(){
        Long diff = getNextTime();
        return (diff / (60 * 1000)) % 60;
    }
    public Long getNextSeconds(){
        Long diff = getNextTime();
        return (diff / 1000) %60;
    }

}
