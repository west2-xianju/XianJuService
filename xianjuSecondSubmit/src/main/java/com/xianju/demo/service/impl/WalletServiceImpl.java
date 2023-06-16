package com.xianju.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xianju.demo.entity.Wallet;
import com.xianju.demo.mapper.WalletMapper;
import com.xianju.demo.service.IWalletService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author feige
 * @since 2023-04-04
 */
@Service
public class WalletServiceImpl extends ServiceImpl<WalletMapper, Wallet> implements IWalletService {

    @NotNull
    @Transactional
    public Map<String, Object> getStringObjectMap(Wallet wallet) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("uid",wallet.getUid());
        data.put("user_id",wallet.getUserId());
        data.put("balance",wallet.getBalance());
        data.put("blocked",wallet.isBlocked());
        return data;
    }

    @Override
    @Transactional
    public void updateBalance(BigDecimal cost, int from_id) {
        LambdaQueryWrapper<Wallet> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Wallet::getUserId,from_id);
        Wallet wallet = this.baseMapper.selectOne(wrapper);
        wallet.setBalance(wallet.getBalance().subtract(cost));
        saveOrUpdate(wallet);
    }

    @Override
    @Transactional
    public Map<String, Object> getBalance(Integer userid) {
        LambdaQueryWrapper<Wallet> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Wallet::getUserId,userid);
        Wallet wallet = this.baseMapper.selectOne(wrapper);
        return getStringObjectMap(wallet);
    }
    @Override
    @Transactional
    public Map<String, Object> withdraw(Integer userid, BigDecimal money) {
        LambdaQueryWrapper<Wallet> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Wallet::getUserId,userid);
        Wallet wallet = this.baseMapper.selectOne(wrapper);
        wallet.setBalance(wallet.getBalance().subtract(money));
        saveOrUpdate(wallet);
        return getStringObjectMap(wallet);
    }

    @Override
    @Transactional
    public Map<String, Object> toUp(Integer userid, BigDecimal money) {
        LambdaQueryWrapper<Wallet> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Wallet::getUserId,userid);
        Wallet wallet = this.baseMapper.selectOne(wrapper);
        wallet.setBalance(wallet.getBalance().add(money));
        saveOrUpdate(wallet);
        return getStringObjectMap(wallet);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void updateMoney(BigDecimal price, Integer toId) {
        LambdaQueryWrapper<Wallet> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Wallet::getUserId,toId);
        Wallet wallet = this.baseMapper.selectOne(wrapper);
        wallet.setBalance(wallet.getBalance().add(price));
        saveOrUpdate(wallet);
    }
}
