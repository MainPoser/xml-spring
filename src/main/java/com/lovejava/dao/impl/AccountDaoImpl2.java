package com.lovejava.dao.impl;

import com.lovejava.dao.AccountDao;
import com.lovejava.domain.Account;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;


import javax.sql.DataSource;
import java.util.List;

/**
 * @author:tianyao
 * @date:2019-04-12 10:33
 */
public class AccountDaoImpl2 extends JdbcDaoSupport implements AccountDao {

    /**
     * 继承了JdbcDaoSupport后获得了父类的JdbcTemplates的属性,注入dataSources通过getJdbcTemplate就能得到JdbcTemplate对象
     * @param id
     * @return
     */
    @Override
    public Account findAccountById(Integer id) {
        //getJdbcTemplate()方法是从父类上继承下来的。
        List<Account> list = getJdbcTemplate().query("select * from account where id = ? ",new BeanPropertyRowMapper<>(Account.class),id);
        return list.isEmpty()?null:list.get(0);
    }

    @Override
    public Account findAccountByName(String name) {
        //getJdbcTemplate()方法是从父类上继承下来的。
        List<Account> list =  getJdbcTemplate().query("select * from account where name = ? ",new BeanPropertyRowMapper<>(Account.class),name);
        if(list.isEmpty()){
            return null;
        }
        if(list.size()>1){
            throw new RuntimeException("结果集不唯一，不是只有一个账户对象");
        }
        return list.get(0);
    }

    @Override
    public void updateAccount(Account account) {
        //getJdbcTemplate()方法是从父类上继承下来的。
        getJdbcTemplate().update("update account set money = ? where id = ? ",account.getMoney(),account.getId());
    }

}

