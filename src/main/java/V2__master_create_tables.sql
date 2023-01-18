
CREATE TABLE currency (
  id SERIAL PRIMARY KEY,
  currency VARCHAR NOT NULL,
  description VARCHAR NULL,
  tenant_id BIGINT,
  created_by BIGINT NOT NULL, 
  updated_by BIGINT NOT NULL,
  created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
  updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
  is_active BOOLEAN DEFAULT true,
  is_deleted BOOLEAN DEFAULT false
);


 
CREATE TABLE fiscal_calendar (
	id SERIAL PRIMARY KEY,
	key VARCHAR NOT NULL,
	description VARCHAR NULL,
	start_month VARCHAR NULL,
	start_day VARCHAR NULL,
	start_week_day VARCHAR NULL,
	start_period_of_year VARCHAR NULL,
	seventh_yr_extra_week VARCHAR NULL,
	tenant_id BIGINT,
	created_by BIGINT NOT NULL, 
	updated_by BIGINT NOT NULL,
	created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
	updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
	is_active BOOLEAN DEFAULT true,
	is_deleted BOOLEAN DEFAULT false
 );



CREATE TABLE department (
  id SERIAL PRIMARY KEY,
  name VARCHAR NOT NULL,
  depart_code VARCHAR NOT NULL,
  activation_date date NULL DEFAULT '01/01/1900',
  end_date date NULL,
  dept_head_name VARCHAR NULL,
  report_owner VARCHAR NULL,
  tenant_id BIGINT,
  created_by BIGINT NOT NULL, 
  updated_by BIGINT NOT NULL,
  created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
  updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
  is_active BOOLEAN DEFAULT true,
  is_deleted BOOLEAN DEFAULT false
);


CREATE TABLE group_company (
  id SERIAL PRIMARY KEY,
  gc_name VARCHAR NOT NULL,
  gc_code VARCHAR NOT NULL,
  currency_id BIGINT,  FOREIGN KEY (currency_id) REFERENCES currency(id),
  country_id BIGINT, FOREIGN KEY (country_id) REFERENCES country(id),
  tenant_id BIGINT,
  created_by BIGINT NOT NULL, 
  updated_by BIGINT NOT NULL,
  created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
  updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
  is_active BOOLEAN DEFAULT true,
  is_deleted BOOLEAN DEFAULT false
 );
 
 
 
CREATE TABLE company (
	id SERIAL PRIMARY KEY,
	name VARCHAR NOT NULL,
	code VARCHAR NOT NULL,
	currency_id BIGINT,  FOREIGN KEY (currency_id) REFERENCES currency(id),
	country_id BIGINT, FOREIGN KEY (country_id) REFERENCES country(id),
	gc_id BIGINT, FOREIGN KEY (gc_id) REFERENCES group_company(id),
	fc_id BIGINT, FOREIGN KEY (fc_id) REFERENCES fiscal_Calendar(id),
	activation_date date NULL,
	end_date date NULL,
	tenant_id BIGINT,
	created_by BIGINT NOT NULL, 
	updated_by BIGINT NOT NULL,
	created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
	updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
	is_active BOOLEAN DEFAULT true,
	is_deleted BOOLEAN DEFAULT false
 );
 
CREATE TABLE business_unit (
	id SERIAL PRIMARY KEY,
	name VARCHAR NOT NULL,
	code VARCHAR NOT NULL,
	bu_owner VARCHAR NOT NULL,
	bu_owner_email VARCHAR NOT NULL,
	gc_id BIGINT, FOREIGN KEY (gc_id) REFERENCES group_company(id),
	activation_date date NULL,
	end_date date NULL,
	tenant_id BIGINT,
	created_by BIGINT NOT NULL, 
	updated_by BIGINT NOT NULL,
	created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
	updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
	is_active BOOLEAN DEFAULT true,
	is_deleted BOOLEAN DEFAULT false
 );
