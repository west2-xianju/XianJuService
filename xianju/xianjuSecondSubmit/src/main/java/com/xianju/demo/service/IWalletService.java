package com.xianju.demo.service;

import com.xianju.demo.entity.Wallet;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author feige
 * @since 2023-04-04
 */
public interface IWalletService extends IService<Wallet> {

    void updateBalance(BigDecimal cost, int from_id);

    Map<String, Object> getBalance(Integer userid);

    Map<String, Object> withdraw(Integer userid, BigDecimal money);

    Map<String, Object> toUp(Integer userid, BigDecimal money);

    void updateMoney(BigDecimal price, Integer toId);
}
