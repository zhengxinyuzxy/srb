package com.mellow.srb.core.service.impl;

import com.mellow.srb.core.pojo.entity.UserInfo;
import com.mellow.srb.core.mapper.UserInfoMapper;
import com.mellow.srb.core.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户基本信息 服务实现类
 * </p>
 *
 * @author zhengxinyu
 * @since 2021-08-28
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
