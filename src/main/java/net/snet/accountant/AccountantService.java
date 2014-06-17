package net.snet.accountant;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.jdbi.DBIFactory;
import net.snet.accountant.resources.AccountantResource;
import net.snet.accountant.resources.BaseResource;
import net.snet.accountant.resources.InvoiceResource;
import org.skife.jdbi.v2.DBI;

public class AccountantService extends Service<AccountantConfiguration> {
	public static void main(String[] args) throws Exception {
		new AccountantService().run(args);
	}

	@Override
	public void initialize(Bootstrap<AccountantConfiguration> bootstrap) {
		bootstrap.setName("accountant-service");
	}

	@Override
	public void run(AccountantConfiguration configuration, Environment environment) throws ClassNotFoundException {
		final DBIFactory dbiFactory = new DBIFactory();
		final DBI dbi = dbiFactory.build(environment, configuration.getDatabaseConfiguration(), "postgresql");
		if (configuration.getJsonPrettyPrint()) {
			environment.getObjectMapperFactory().enable(SerializationFeature.INDENT_OUTPUT);
		}

		environment.addResource(new AccountantResource(dbi));
		environment.addResource(new InvoiceResource(dbi));
		environment.addResource(new BaseResource());
	}

}
