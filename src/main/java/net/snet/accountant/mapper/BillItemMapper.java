package net.snet.accountant.mapper;

import net.snet.accountant.bo.BillItem;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BillItemMapper implements ResultSetMapper<BillItem> {
	@Override
	public BillItem map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		return new BillItem(r.getLong("bill_id"),
				r.getString("text"),
				r.getFloat("amount"),
				r.getInt("price"),
				r.getBoolean("is_display_unit"));
	}
}