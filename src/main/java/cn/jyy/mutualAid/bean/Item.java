package cn.jyy.mutualAid.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Randy
 * @version 1.0
 */
@Data
@TableName("item")
public class Item {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @NotEmpty(message = "请输入物品信息")
    private String info;
    @NotEmpty(message = "请输入贡献者")
    private String contributor;
    @NotEmpty(message = "请输入电子邮箱")
    private String email;
    
}
