package com.ly.bs.dao.accountcenter

import javax.inject.Named;
import java.util.List;

import com.ly.bs.modules.accountcenter.bean.BusBussinessEntityBean;

/**
* @ClassName: BusBussinessEntityMapper
* @Description: 系统用户日志数据库操作接口
* @author wangyu
* @date 2017-06-12 13:59:37
*
*/
@Named("busBussinessEntityMapper")
public interface BusBussinessEntityMapper {

    /**
    * @Description: 新增
    **/
    int insertSelective(BusBussinessEntityBean bean);

    /**
    * @Description: 按主键ID删除
    **/
    int deleteByPrimaryKey();

    /**
    * @Description: 更新
    **/
    int updateByPrimaryKeySelective(BusBussinessEntityBean bean);

    /**
    * @Description: 根据主键查询
    **/
    BusBussinessEntityBean selectByPrimaryKey();

    /**
    * @Description: 查询所有
    **/
    List<BusBussinessEntityBean> selectAll();
}