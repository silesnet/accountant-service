package net.snet.accountant.mapper;

import net.snet.accountant.bo.Bill;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BillMapper implements ResultSetMapper<Bill> {
    @Override
    public Bill map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new Bill(
                r.getLong("id"),
                r.getString("number"),
                r.getTimestamp("billing_date"),
                r.getTimestamp("purge_date"),
                r.getLong("customer_id"),
                r.getTimestamp("period_from"),
                r.getTimestamp("period_to"),
                r.getInt("vat"),
                r.getString("hash_code"),
                r.getBoolean("is_confirmed"),
                r.getBoolean("is_sent"),
                r.getBoolean("is_delivered"),
                r.getBoolean("is_archived"),
                r.getBoolean("deliver_by_mail"),
                r.getString("customer_name"),
                r.getLong("invoicing_id"),
                r.getTimestamp("synchronized")
        );
    }
}
