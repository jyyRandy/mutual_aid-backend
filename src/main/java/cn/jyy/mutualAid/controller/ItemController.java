package cn.jyy.mutualAid.controller;

/**
 * @author Randy
 * @version 1.0
 */

import cn.jyy.mutualAid.bean.Item;
import cn.jyy.mutualAid.service.ItemService;
import cn.jyy.mutualAid.util.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ItemController {
    @Autowired(required = false)
    ItemService itemService;
    @PostMapping("/save")
    public Result<?> save(@Validated @RequestBody Item item, Errors errors) {
        Map<String, Object> map = new HashMap<>();
        List<FieldError> fieldErrors = errors.getFieldErrors();
        for (FieldError e : fieldErrors) {
            map.put(e.getField(), e.getDefaultMessage());
        } if
        (map.isEmpty()) {
            itemService.save(item);
            return Result.success();
        } else {
            return Result.error("400", "校验失败", map);
        }
    }

    @ResponseBody
    @RequestMapping("/items")
    public Result<?> listItems() {
        List<Item> itemList = itemService.list();
        return Result.success(itemList);
    }

    @PutMapping("/update")
    @ResponseBody
    public Result<?> update(@RequestBody Item item) {
        itemService.updateById(item);
        return Result.success();
    }

    @DeleteMapping("/del/{id}")
    @ResponseBody
    public Result<?> del(@PathVariable Integer id) {
        itemService.removeById(id);
        return Result.success();
    }

    @ResponseBody
    @RequestMapping("/itemsByPage")
    public Result<?> listItemsByPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                     @RequestParam(defaultValue = "5") Integer pageSize) {
        Page<Item> itemPage = itemService.page(new Page<>(pageNum, pageSize));
        return Result.success(itemPage);
    }

    @ResponseBody
    @RequestMapping("/itemsBySearchPage")
    public Result<?> listItemsByConditionPage
            (@RequestParam(defaultValue = "1") Integer pageNum,
             @RequestParam(defaultValue = "5") Integer pageSize,
             @RequestParam(defaultValue = "") String search) {
        QueryWrapper<Item> queryWrapper = Wrappers.query();
        if (StringUtils.hasText(search)) {
            queryWrapper.like("info", search);
        }
        Page<Item> page =
                itemService.page(new Page<>(pageNum, pageSize), queryWrapper);
        return Result.success(page);
    }
}
