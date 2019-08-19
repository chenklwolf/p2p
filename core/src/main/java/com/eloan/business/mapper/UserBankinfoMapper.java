package com.eloan.business.mapper;


import com.eloan.business.domain.UserBankinfo;
import com.eloan.business.domain.UserBankinfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserBankinfoMapper {
    int countByExample(UserBankinfoExample example);

    int deleteByExample(UserBankinfoExample example);

    int deleteByPrimaryKey(Long id);

    int insertSelective(UserBankinfo record);

    List<UserBankinfo> selectByExample(UserBankinfoExample example);

    UserBankinfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserBankinfo record, @Param("example") UserBankinfoExample example);

    int updateByExample(@Param("record") UserBankinfo record, @Param("example") UserBankinfoExample example);

    int updateByPrimaryKeySelective(UserBankinfo record);

    int updateByPrimaryKey(UserBankinfo record);


    int insert(UserBankinfo record);

    UserBankinfo selectByUser(Long userid);
}