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

import static com.ayu.bookmymovie.Constants.BookMyMovieConstants.*;

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
            return LAYOUT_CATEGORY_ADDED_SUCCESSFULLY;
        }else {
            return NO_SCREEN_FOUND_FOR_GIVEN_ID;
        }
    }

    public LayoutCategory updateLayoutCategoryDetails(LayoutCategory layoutCategory){
        Optional<LayoutCategory> layoutCategoryDetailsFromDB = layoutCategoryRepository.findById(layoutCategory.getId());
        if(layoutCategoryDetailsFromDB.isPresent()){
            layoutCategoryDetailsFromDB.get().setCategoryName(layoutCategory.getCategoryName());
            layoutCategoryDetailsFromDB.get().setPrice(layoutCategory.getPrice());
            layoutCategoryDetailsFromDB.get().setMaxSeats(layoutCategory.getMaxSeats());

            return layoutCategoryRepository.save(layoutCategoryDetailsFromDB.get());
        } else {
            return new LayoutCategory();
        }
    }

    public String deleteLayoutCategory(Long layoutCategoryId){
        try{
            layoutCategoryRepository.deleteById(layoutCategoryId);
            return LAYOUT_CATEGORY_DELETED_SUCCESSFULLY;
        }catch (EmptyResultDataAccessException e){
            return NO_RECORD_FOUND;
        }
    }
}
