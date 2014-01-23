package net.snet.accountant.dao;

import net.snet.accountant.bo.Customer;
import net.snet.accountant.mapper.CustomerMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(CustomerMapper.class)
public interface CustomerDAO {

    @SqlQuery("select * from customers where id = :id")
    Customer findById(@Bind("id") long id);

    void close();
}


