create table accounts (
  id    uuid primary key,
  code  varchar(50) not null unique
);

create table subaccounts (
  id         uuid primary key,
  account_id uuid not null references accounts(id),
  name       varchar(50) not null unique
);

insert into accounts (id, code) values
  ('0193e522-870d-7ae4-bcd6-012c22c47e2c', 'share_capital'),
  ('0193e522-870d-70ed-b446-1f215557c630', 'banks');
