package com.mellow.srb.core.service.impl;

import com.mellow.srb.core.pojo.entity.UserLoginRecord;
import com.mellow.srb.core.mapper.UserLoginRecordMapper;
import com.mellow.srb.core.service.UserLoginRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
