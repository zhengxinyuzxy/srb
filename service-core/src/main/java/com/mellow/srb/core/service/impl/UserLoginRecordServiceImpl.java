package com.mellow.srb.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mellow.srb.core.pojo.entity.UserLoginRecord;
import com.mellow.srb.core.mapper.UserLoginRecordMapper;
import com.mellow.srb.core.service.UserLoginRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户登录记录表 服务实现类
 * </p>
 *
 * @author zhengxinyu
 * @since 2021-08-28
 */
@Service
public class UserLoginRecordServiceImpl extends ServiceImpl<UserLoginRecordMapper, UserLoginRecord> implements UserLoginRecordService {

    @Override
    public List<UserLoginRecord> listTop50(Long id) {
        QueryWrapper<UserLoginRecord> userLoginRecordQueryWrapper = new QueryWrapper<>();
        userLoginRecordQueryWrapper.eq("user_id", id)
                .orderByDesc("id")
                .last("limit 50");
        List<UserLoginRecord> userLoginRecords = baseMapper.selectList(userLoginRecordQueryWrapper);
        return userLoginRecords;
    }
}
