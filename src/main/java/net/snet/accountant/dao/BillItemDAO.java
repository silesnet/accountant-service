package net.snet.accountant.dao;

import net.snet.accountant.bo.BillItem;
import net.snet.accountant.mapper.BillItemMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.Iterator;

@RegisterMapper(BillItemMapper.class)
public interface BillItemDAO {

    @SqlQuery("select * from bill_items where bill_id = :id")
    BillItem findById(@Bind("id") long id);

    @SqlQuery("select bill_id from bill_items where bill_id = :id")
    Iterator<Integer> findAllBillItems(@Bind("id") long id);

    void close();
}
