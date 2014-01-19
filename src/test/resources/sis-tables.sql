--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: bill_items; Type: TABLE; Schema: public; Owner: sisa; Tablespace: 
--

CREATE TABLE bill_items (
    bill_id bigint NOT NULL,
    text character varying(100),
    amount real,
    price integer,
    is_display_unit boolean
);


ALTER TABLE public.bill_items OWNER TO sisa;

--
-- Name: bills; Type: TABLE; Schema: public; Owner: sisa; Tablespace: 
--

CREATE TABLE bills (
    id bigint NOT NULL,
    number character varying(15),
    billing_date timestamp without time zone,
    purge_date timestamp without time zone,
    customer_id bigint NOT NULL,
    period_from timestamp without time zone NOT NULL,
    period_to timestamp without time zone NOT NULL,
    vat integer,
    hash_code character varying(50) NOT NULL,
    is_confirmed boolean,
    is_sent boolean,
    is_delivered boolean,
    is_archived boolean,
    deliver_by_mail boolean,
    customer_name character varying(80),
    invoicing_id bigint,
    synchronized timestamp without time zone
);


ALTER TABLE public.bills OWNER TO sisa;

--
-- Name: commands; Type: TABLE; Schema: public; Owner: sisa; Tablespace: 
--

CREATE TABLE commands (
    id bigint NOT NULL,
    command character varying(50) NOT NULL,
    entity character varying(30),
    entity_id bigint,
    data character varying,
    status character varying(20) NOT NULL,
    inserted_on timestamp without time zone NOT NULL,
    started_on timestamp without time zone,
    finished_on timestamp without time zone
);


ALTER TABLE public.commands OWNER TO sisa;

--
-- Name: commands_id_seq; Type: SEQUENCE; Schema: public; Owner: sisa
--

CREATE SEQUENCE commands_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.commands_id_seq OWNER TO sisa;

--
-- Name: commands_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: sisa
--

ALTER SEQUENCE commands_id_seq OWNED BY commands.id;


--
-- Name: customers; Type: TABLE; Schema: public; Owner: sisa; Tablespace: 
--

CREATE TABLE customers (
    id bigint NOT NULL,
    history_id bigint NOT NULL,
    public_id character varying(20) NOT NULL,
    name character varying(80) NOT NULL,
    supplementary_name character varying(40),
    street character varying(40),
    city character varying(40),
    postal_code character varying(10),
    country integer,
    email character varying(50),
    dic character varying(20),
    contract_no character varying(50),
    connection_spot character varying(100),
    inserted_on timestamp without time zone NOT NULL,
    frequency integer,
    lastly_billed timestamp without time zone,
    is_billed_after boolean,
    deliver_by_email boolean,
    deliver_copy_email character varying(100),
    deliver_by_mail boolean,
    is_auto_billing boolean,
    info character varying(150),
    contact_name character varying(50),
    phone character varying(60),
    is_active boolean,
    status integer,
    shire_id bigint,
    format integer,
    deliver_signed boolean,
    symbol character varying(20),
    updated timestamp without time zone,
    synchronized timestamp without time zone,
    account_no character varying(26),
    bank_no character varying(4),
    variable integer
);


ALTER TABLE public.customers OWNER TO sisa;

--
-- Name: events; Type: TABLE; Schema: public; Owner: sisa; Tablespace: 
--

CREATE TABLE events (
    id bigint NOT NULL,
    event character varying(50) NOT NULL,
    entity character varying(30),
    entity_id bigint,
    data character varying,
    command_id bigint,
    happened_on timestamp without time zone NOT NULL
);


ALTER TABLE public.events OWNER TO sisa;

--
-- Name: events_id_seq; Type: SEQUENCE; Schema: public; Owner: sisa
--

CREATE SEQUENCE events_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.events_id_seq OWNER TO sisa;

--
-- Name: events_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: sisa
--

ALTER SEQUENCE events_id_seq OWNED BY events.id;


--
-- Name: invoicings; Type: TABLE; Schema: public; Owner: sisa; Tablespace: 
--

CREATE TABLE invoicings (
    id bigint NOT NULL,
    history_id bigint NOT NULL,
    name character varying(80) NOT NULL,
    country integer,
    invoicing_date timestamp without time zone,
    numberingbase character varying(15)
);


ALTER TABLE public.invoicings OWNER TO sisa;

--
-- Name: services; Type: TABLE; Schema: public; Owner: sisa; Tablespace: 
--

CREATE TABLE services (
    id bigint NOT NULL,
    customer_id bigint,
    period_from timestamp without time zone NOT NULL,
    period_to timestamp without time zone,
    name character varying(70) NOT NULL,
    price integer NOT NULL,
    frequency integer,
    download integer,
    upload integer,
    is_aggregated boolean,
    info character varying(150),
    replace_id bigint,
    additionalname character varying(50),
    bps character(1),
    old_id bigint
);


ALTER TABLE public.services OWNER TO sisa;

--
-- Name: id; Type: DEFAULT; Schema: public; Owner: sisa
--

ALTER TABLE commands ALTER COLUMN id SET DEFAULT nextval('commands_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: sisa
--

ALTER TABLE events ALTER COLUMN id SET DEFAULT nextval('events_id_seq'::regclass);


--
-- Name: bills_pkey; Type: CONSTRAINT; Schema: public; Owner: sisa; Tablespace: 
--

ALTER TABLE ONLY bills
    ADD CONSTRAINT bills_pkey PRIMARY KEY (id);


--
-- Name: commands_pkey; Type: CONSTRAINT; Schema: public; Owner: sisa; Tablespace: 
--

ALTER TABLE ONLY commands
    ADD CONSTRAINT commands_pkey PRIMARY KEY (id);


--
-- Name: customers_history_id_key; Type: CONSTRAINT; Schema: public; Owner: sisa; Tablespace: 
--

ALTER TABLE ONLY customers
    ADD CONSTRAINT customers_history_id_key UNIQUE (history_id);


--
-- Name: customers_pkey; Type: CONSTRAINT; Schema: public; Owner: sisa; Tablespace: 
--

ALTER TABLE ONLY customers
    ADD CONSTRAINT customers_pkey PRIMARY KEY (id);


--
-- Name: events_pkey; Type: CONSTRAINT; Schema: public; Owner: sisa; Tablespace: 
--

ALTER TABLE ONLY events
    ADD CONSTRAINT events_pkey PRIMARY KEY (id);


--
-- Name: invoicings_history_id_key; Type: CONSTRAINT; Schema: public; Owner: sisa; Tablespace: 
--

ALTER TABLE ONLY invoicings
    ADD CONSTRAINT invoicings_history_id_key UNIQUE (history_id);


--
-- Name: invoicings_pkey; Type: CONSTRAINT; Schema: public; Owner: sisa; Tablespace: 
--

ALTER TABLE ONLY invoicings
    ADD CONSTRAINT invoicings_pkey PRIMARY KEY (id);


--
-- Name: services_pkey; Type: CONSTRAINT; Schema: public; Owner: sisa; Tablespace: 
--

ALTER TABLE ONLY services
    ADD CONSTRAINT services_pkey PRIMARY KEY (id);


--
-- Name: bill_index; Type: INDEX; Schema: public; Owner: sisa; Tablespace: 
--

CREATE INDEX bill_index ON bill_items USING btree (bill_id);


--
-- Name: customer_index; Type: INDEX; Schema: public; Owner: sisa; Tablespace: 
--

CREATE INDEX customer_index ON bills USING btree (customer_id);


--
-- Name: customer_index2; Type: INDEX; Schema: public; Owner: sisa; Tablespace: 
--

CREATE INDEX customer_index2 ON services USING btree (customer_id);


--
-- Name: invoicing_index; Type: INDEX; Schema: public; Owner: sisa; Tablespace: 
--

CREATE INDEX invoicing_index ON bills USING btree (invoicing_id);


--
-- Name: fk20690d88e58fea1d; Type: FK CONSTRAINT; Schema: public; Owner: sisa
--

ALTER TABLE ONLY bill_items
    ADD CONSTRAINT fk20690d88e58fea1d FOREIGN KEY (bill_id) REFERENCES bills(id);


--
-- Name: fk5235105e31f2d3d; Type: FK CONSTRAINT; Schema: public; Owner: sisa
--

ALTER TABLE ONLY services
    ADD CONSTRAINT fk5235105e31f2d3d FOREIGN KEY (customer_id) REFERENCES customers(id);


--
-- PostgreSQL database dump complete
--

