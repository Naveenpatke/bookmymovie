package com.ayu.bookmymovie.Controller;

import com.ayu.bookmymovie.Model.LayoutCategory;
import com.ayu.bookmymovie.Service.LayoutCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/layout-category")
public class LayoutCategoryController {

    @Autowired
    LayoutCategoryService layoutCategoryService;

    @GetMapping("/get-layout-category-details")
    public Optional<LayoutCategory> getLayoutCategoryDetails(@RequestParam Long LayoutCategoryId){
        return layoutCategoryService.getLayoutCategoryDetails(LayoutCategoryId);
    }

    @PostMapping("/add-layout-category")
    public LayoutCategory addLayoutCategory(@RequestBody LayoutCategory layoutCategory){
        return layoutCategoryService.addLayoutCategory(layoutCategory);
    }

    @PutMapping("/update-layout-category-details")
    public LayoutCategory updateLayoutCategoryDetails(@RequestBody LayoutCategory layoutCategory){
        return layoutCategoryService.updateLayoutCategoryDetails(layoutCategory);
    }

    @DeleteMapping("/delete-layout-category")
    public String deleteLayoutCategory(@RequestParam Long layoutCategoryId){
        return layoutCategoryService.deleteLayoutCategory(layoutCategoryId);
    }
}
