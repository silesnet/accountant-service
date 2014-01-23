package net.snet.accountant.dao;

import net.snet.accountant.bo.Bill;
import net.snet.accountant.mapper.BillMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.sql.Timestamp;
import java.util.Iterator;

@RegisterMapper(BillMapper.class)
public interface BillDAO {

    @SqlQuery("select * from bills where id = :id")
    Bill findById(@Bind("id") long id);

    @SqlQuery("select * from bills where invoicing_id = :id")
    Iterator<Bill> findAllBillsByInvoicingId(@Bind("id") long id);

    // Get all bills to billing period
    @SqlQuery("select id from bills where to_char(billing_date,'YYYY-MM') LIKE :date")
    Iterator<Integer> findAllBills(@Bind("date") String date);

    void close();

}
