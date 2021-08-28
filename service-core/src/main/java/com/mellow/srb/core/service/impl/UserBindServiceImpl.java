package com.mellow.srb.core.service.impl;

import com.mellow.srb.core.pojo.entity.UserBind;
import com.mellow.srb.core.mapper.UserBindMapper;
import com.mellow.srb.core.service.UserBindService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户绑定表 服务实现类
 * </p>
 *
 * @author zhengxinyu
 * @since 2021-08-28
 */
@Service
public class UserBindServiceImpl extends ServiceImpl<UserBindMapper, UserBind> implements UserBindService {

}
