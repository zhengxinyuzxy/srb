package com.mellow.srb.core.service.impl;

import com.mellow.srb.core.pojo.entity.UserAccount;
import com.mellow.srb.core.mapper.UserAccountMapper;
import com.mellow.srb.core.service.UserAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户账户 服务实现类
 * </p>
 *
 * @author zhengxinyu
 * @since 2021-08-28
 */
@Service
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount> implements UserAccountService {

}
