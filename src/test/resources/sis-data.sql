INSERT INTO customers VALUES (58, 2058, '9912310024', 'Milena Hejnová', '', 'Stožická 885', 'Tábor 2', '390 02', 10, 'MilenaHejnova@einrot.com', '', '1564', '', '2006-01-24 00:00:00', 40, '2013-12-31 00:00:00', false, true, '', false, true, '', '', '725 672 583', true, 10, 255, 10, false, '4441', '2012-02-24 09:44:25.526708', '2012-02-24 14:15:34.859', '', '', NULL);
INSERT INTO customers VALUES (287, 2287, '9912310135', 'Jana Pulkrábková', '', 'U Parku 387', 'Dolní Bukovsko', '373 65', 10, 'JanaPulkrabkova@rhyta.com', '', '1061', '', '2006-01-24 00:00:00', 40, '2013-12-31 00:00:00', false, true, '', false, true, '', '', '394 624 584', true, 10, 255, 10, false, '5005', '2012-02-24 09:44:25.526708', '2012-02-24 14:15:32.89', '', '', NULL);
INSERT INTO customers VALUES (61, 2061, '9912310026', 'Filip Vedral', '', 'Tylova 1296', 'Karlovy Vary 1', '360 01', 10, 'FilipVedral@fleckens.hu', '', '463', '', '2006-01-24 00:00:00', 40, '2013-12-31 00:00:00', false, true, '', false, true, '', '', '351 144 388', true, 10, 255, 10, false, '4450', '2012-02-24 09:44:25.526708', '2012-02-24 14:15:34.875', '', '', NULL);

INSERT INTO services VALUES (10000101, 58, '2005-12-31 00:00:00', NULL, 'INTERNETdedicated', 200, 40, 100, 100, false, '', 0, '', 'M', 10390);
INSERT INTO services VALUES (10000201, 287, '2005-12-31 00:00:00', NULL, 'INTERNETdedicated', 200, 40, 100, 100, false, '', 0, '', 'M', 10390);
INSERT INTO services VALUES (10001601, 61, '2006-07-27 00:00:00', '2013-08-31 00:00:00', 'LANaccess', 260, 40, 10, 5, false, 'kbtesin, od 1.12.2011', 0, '', 'M', 18100);
INSERT INTO services VALUES (10001602, 61, '2005-12-31 00:00:00', NULL, 'LANfiber', 310, 40, 100, 100, false, 'tovarni, od 17.06.2013', 0, '', 'M', 10060);

Insert into invoicings(id, history_id, name, country, invoicing_date, numberingbase) values (137, 21996, 'CZ 12/2013', 10, '2013-12-05 00:00:00.0', '201314891');

Insert into bills(id, number, billing_date, purge_date, customer_id, period_from, period_to, vat, hash_code, is_confirmed, is_sent, is_delivered, is_archived, deliver_by_mail, customer_name, invoicing_id, synchronized) values (391016, '2013060353', '2013-12-06 00:00:00.0', '2013-12-20 00:00:00.0', 58, '2013-12-01 00:00:00.0', '2013-12-31 00:00:00.0', 21, 'f0003142c7edfa74', true, true, true, false, false, 'Milena Hejnová', 137, '2013-12-09 19:32:19.6');
Insert into bills(id, number, billing_date, purge_date, customer_id, period_from, period_to, vat, hash_code, is_confirmed, is_sent, is_delivered, is_archived, deliver_by_mail, customer_name, invoicing_id, synchronized) values (390887, '2013060224', '2013-12-06 00:00:00.0', '2013-12-20 00:00:00.0', 287, '2013-12-01 00:00:00.0', '2013-12-31 00:00:00.0', 21, 'f0006142c7edf8d4', true, true, true, false, false, 'Jana Pulkrábková', 137, '2013-12-09 19:32:17.897');
Insert into bills(id, number, billing_date, purge_date, customer_id, period_from, period_to, vat, hash_code, is_confirmed, is_sent, is_delivered, is_archived, deliver_by_mail, customer_name, invoicing_id, synchronized) values (390756, '2013060093', '2013-12-06 00:00:00.0', '2013-12-20 00:00:00.0', 61, '2013-12-01 00:00:00.0', '2013-12-31 00:00:00.0', 21, 'f000c142c7edf72e', true, true, true, false, false, 'Filip Vedral', 137, '2013-12-09 19:32:16.069');

Insert into bill_items(bill_id, text, amount, price, is_display_unit) values (391016, 'INTERNETdedicated  10/5 Mbps', 1.0, 208, true);
Insert into bill_items(bill_id, text, amount, price, is_display_unit) values (390887, 'LANaccess  10/5 Mbps', 1.0, 260, true);
Insert into bill_items(bill_id, text, amount, price, is_display_unit) values (390756, 'LANaccess 20/10 Mbps', 1.0, 310, true);
Insert into bill_items(bill_id, text, amount, price, is_display_unit) values (390756, 'Odpočet', 1.0, -300, true);
