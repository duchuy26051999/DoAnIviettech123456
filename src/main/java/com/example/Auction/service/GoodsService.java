package com.example.Auction.service;

import com.example.Auction.entity.CategoryEntity;
import com.example.Auction.entity.GoodsEntity;
import com.example.Auction.entity.ImageEntity;
import com.example.Auction.repository.CategoryRepository;
import com.example.Auction.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GoodsService {
    @Autowired
    private CategoryRepository categoryRepo;

    @Autowired
    private GoodsRepository goodsRepo;
    @Autowired
    private ImageService imageService;

    public List<CategoryEntity> getAllCategory(){
        List<CategoryEntity> category = null;
        try {
            category = (List<CategoryEntity>) categoryRepo.findAll();
            return category;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public CategoryEntity addCategory(CategoryEntity category){
        CategoryEntity newCategory;
        try {
            newCategory = categoryRepo.save(category);
            return newCategory;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public CategoryEntity updateCategory(CategoryEntity category){
        CategoryEntity newCategory;
        try {
            newCategory = categoryRepo.save(category);
            return newCategory;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public boolean deleteCategory(int id){
        boolean t = false;
        try {
            categoryRepo.deleteById(id);
            t = true;
        }catch (Exception e){
            System.out.println(e);
        }
        return t;
    }

    public List<GoodsEntity> getAllGoods(){
        List<GoodsEntity> goods = null;
        try {
            goods = (List<GoodsEntity>) goodsRepo.findAll();
            return goods;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
    @Transactional(rollbackOn = Exception.class)
    public GoodsEntity addGoods(GoodsEntity goods){
//        goods.setStatus("Register sell");
        GoodsEntity newGood = null;
        newGood = goodsRepo.save(goods);
        for(ImageEntity img : goods.getImageList()){
            img.setGoods(newGood);
            imageService.addImage(img);
        }
        return newGood;
    }

    public void updateGoods(GoodsEntity goods){
//        goods.setStatus("Register sell");
        goodsRepo.save(goods);
    }
    public void goodsFails(GoodsEntity goods){
        GoodsEntity newGood = null;
        newGood = goodsRepo.save(goods);
    }

    public void startGoods(GoodsEntity goods){
        GoodsEntity newGood = null;
        newGood = goodsRepo.save(goods);
    }

//    public GoodsEntity updateGoods(GoodsEntity goods){
//        GoodsEntity newGoods;
//        try {
//            newGoods = goodsRepo.save(goods);
//            return newGoods;
//        }catch (Exception e){
//            System.out.println(e);
//        }
//        return null;
//    }

    public void deleteGoods(GoodsEntity goodsEntity){
        List<ImageEntity> imageEntityList= imageService.getListImageByGoodId(goodsEntity.getId());
        for(ImageEntity img : imageEntityList){
            imageService.deleteImage(img);
        }
        goodsRepo.delete(goodsEntity);
    }

    public List<GoodsEntity> searchGoods(String name){
        name = "%" + name + "%";
        return goodsRepo.findByStatusAndGoodNameLike("Start",name);
    }


    public List<GoodsEntity> searchCategory(String name) {
        return goodsRepo.searchCategory(name);
    }

    public GoodsEntity getGoodByID(int id){
        return goodsRepo.findById(id);
    }

    public List<GoodsEntity> getAllGoodByCustomerID(int id){
        return goodsRepo.findByCustomerId(id);
    }

    public List<GoodsEntity> getAllGoodsStatusLike(int id){
        String st1 = "New";
        String st2 = "Sell Fails";
        return goodsRepo.findByCustomer_IdAndStatusLikeOrStatusLike(id,st1,st2);
    }

    public List<GoodsEntity> getAllGoodOfCustomerOrderById(int id){
        List<GoodsEntity> listGoods = null;
        try{
            listGoods = goodsRepo.findByCustomerIdOrderByIdDesc(id);
        }catch (Exception e){
            System.out.println(e);
        }
        return listGoods;
    }

    public List<GoodsEntity> searchGoodsByName(int id,String name){
        name = "%" + name + "%";
        return goodsRepo.findByCustomer_IdAndGoodNameLike(id,name);
    }

    public List<CategoryEntity> findByOrderByIdDescCategory(){
        List<CategoryEntity> category = null;
        try {
            category = (List<CategoryEntity>) categoryRepo.findByOrderByIdDesc();
            return category;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public CategoryEntity getCategoryID(int id){
        return categoryRepo.findById(id);
    }

    public List<CategoryEntity> searchCategoryName(String name){
        name = "%" + name + "%";
        return categoryRepo.findByCategoryLike(name);
    }

    public List<GoodsEntity> findByGoodNameCategory(String name,String category){
        name = "%" + name + "%";
        return goodsRepo.findByGoodNameCategory(name,category);
    }
}
