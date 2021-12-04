package com.ayu.bookmymovie.Service;

import com.ayu.bookmymovie.Model.LayoutCategory;
import com.ayu.bookmymovie.Repository.LayoutCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LayoutCategoryService {

    @Autowired
    LayoutCategoryRepository layoutCategoryRepository;

    public Optional<LayoutCategory> getLayoutCategoryDetails(Long layoutCategoryId){
        return layoutCategoryRepository.findById(layoutCategoryId);
    }

    public LayoutCategory addLayoutCategory(LayoutCategory layoutCategory){
        return layoutCategoryRepository.save(layoutCategory);
    }

    public LayoutCategory updateLayoutCategoryDetails(LayoutCategory layoutCategory){
        Optional<LayoutCategory> layoutCategoryDetailsFromDB = layoutCategoryRepository.findById(layoutCategory.getId());
        if(layoutCategoryDetailsFromDB.isPresent()){
            return layoutCategoryRepository.save(layoutCategory);
        } else {
            return new LayoutCategory();
        }
    }

    public String deleteLayoutCategory(Long layoutCategoryId){
        try{
            layoutCategoryRepository.deleteById(layoutCategoryId);
            return "Deleted Successfully";
        }catch (EmptyResultDataAccessException e){
            return "No records found";
        }
    }
}
