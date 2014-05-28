package net.snet.accountant.dao;

import net.snet.accountant.bo.Bill;
import net.snet.accountant.bo.BillItem;
import net.snet.accountant.mapper.BillItemMapper;
import net.snet.accountant.mapper.BillMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.sql.Timestamp;
import java.util.Iterator;


public interface BillDAO {

    @SqlQuery("select * from bills where id = :id")
    @RegisterMapper(BillMapper.class)
    Bill findBillById(@Bind("id") long id);

    @SqlUpdate("update bills set synchronized = :time where id = :id")
    @RegisterMapper(BillMapper.class)
    void updateBillTime(@Bind("id") int id, @Bind("time") Timestamp time);

    @SqlQuery("select * from bills where invoicing_id = :id order by number")
    @RegisterMapper(BillMapper.class)
    Iterator<Bill> findAllBillsByInvoicingId(@Bind("id") long id);

    @SqlQuery("select * from bill_items where bill_id = :id")
    @RegisterMapper(BillItemMapper.class)
    Iterator<BillItem> findBillItemById(@Bind("id") long id);

    void close();

}
