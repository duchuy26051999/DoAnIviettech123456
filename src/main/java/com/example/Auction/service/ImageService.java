package com.example.Auction.service;


import com.example.Auction.entity.ImageEntity;
import com.example.Auction.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepo;

    public ImageEntity addImage(ImageEntity img){
        return imageRepo.save(img);
    }

    public List<ImageEntity> getListImageByGoodId(int id){
        return imageRepo.findByGoods_Id(id);
    }
    public void deleteImage(ImageEntity imageEntity){
        imageRepo.delete(imageEntity);
    }

    public List<ImageEntity> findByOrderByIdDescImage(){
        List<ImageEntity> list;
        try {
            list = (List<ImageEntity>) imageRepo.findByOrderByIdDesc();
            return list;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public List<ImageEntity> findByIdLikeOrUrlLike(String name){
        name = "%"+name+"%";
        return imageRepo.findByImageGoodName(name);
    }
}
