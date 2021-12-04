package com.ayu.bookmymovie.Service;

import com.ayu.bookmymovie.Model.LayoutCategory;
import com.ayu.bookmymovie.Model.Screen;
import com.ayu.bookmymovie.Repository.LayoutCategoryRepository;
import com.ayu.bookmymovie.Repository.ScreenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LayoutCategoryService {

    @Autowired
    LayoutCategoryRepository layoutCategoryRepository;

    @Autowired
    ScreenRepository screenRepository;

    public Optional<LayoutCategory> getLayoutCategoryDetails(Long layoutCategoryId){
        return layoutCategoryRepository.findById(layoutCategoryId);
    }

    public LayoutCategory addLayoutCategory(LayoutCategory layoutCategory){
        return layoutCategoryRepository.save(layoutCategory);
    }

    public String addLayoutCategoriesToScreen(Long screenId, LayoutCategory layoutCategory){
        Optional<Screen> screen = screenRepository.findById(screenId);
        if (screen.isPresent()){
            List<LayoutCategory> layoutCategories = screen.get().getLayoutCategories();
            layoutCategory.setScreen(screen.get());
            LayoutCategory layoutCategoryFromDb = layoutCategoryRepository.save(layoutCategory);
            layoutCategories.add(layoutCategoryFromDb);
            screen.get().setLayoutCategories(layoutCategories);
            screenRepository.save(screen.get());
            return "Layout category added successfully";
        }else {
            return "No Screen found for given ID";
        }
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
