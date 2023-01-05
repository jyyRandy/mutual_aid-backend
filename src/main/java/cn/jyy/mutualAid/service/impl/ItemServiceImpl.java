package cn.jyy.mutualAid.service.impl;

import cn.jyy.mutualAid.bean.Item;
import cn.jyy.mutualAid.mapper.ItemMapper;
import cn.jyy.mutualAid.service.ItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Randy
 * @version 1.0
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemService {
}

