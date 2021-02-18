package com.xuetang9.qingying.web;

import com.github.pagehelper.PageInfo;
import com.xuetang9.qingying.domain.Brand;
import com.xuetang9.qingying.service.BrandService;
import com.xuetang9.qingying.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/16 9:40
 * @copyright 老九学堂
 */
@RestController
@RequestMapping("/api")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/brands")
    public JsonResult queryByPage(@RequestParam(defaultValue = "1") int pageNum,
                                  @RequestParam(defaultValue = "5") int pageSize,
                                  String filterWords){
        JsonResult jsonResult = new JsonResult();

        List<Brand> brands = brandService.listByPageAndFilter(pageNum, pageSize, filterWords);
        PageInfo<Brand> pageInfo = new PageInfo<>(brands);

        jsonResult.setCode(200);
        jsonResult.setData(pageInfo);

        return jsonResult;
    }

    @PostMapping("/brands/add")
    public JsonResult add(@Validated Brand brand){
        JsonResult jsonResult = new JsonResult();

        jsonResult.setAutoShowMessage(true);
        try {
            brandService.save(brand);
            jsonResult.setCode(200);
        } catch (Exception e) {
            jsonResult.setCode(2000);
            jsonResult.setMessage("商品新增失败！");
            e.printStackTrace();
        }

        return jsonResult;
    }

    @GetMapping("/brands/filter/{filterWords}")
    public JsonResult queryByFilterWords(@PathVariable String filterWords){
        JsonResult jsonResult = new JsonResult();

        List<Brand> brands = brandService.listByFilterWords(filterWords);
        jsonResult.setCode(200);
        jsonResult.setData(brands);

        return jsonResult;
    }

    @GetMapping("/categories/{categoryId}/brands")
    public JsonResult queryByCategoryId(@PathVariable int categoryId){
        JsonResult jsonResult = new JsonResult();

        List<Brand> brands = brandService.listByCategoryId(categoryId);
        jsonResult.setCode(200);
        jsonResult.setData(brands);

        return jsonResult;
    }

}
