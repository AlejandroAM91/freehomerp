const {
  APP_DB_HOST = 'localhost',
  APP_DB_NAME = 'freehomerp',
  APP_DB_PASS,
  APP_DB_PORT = '5432',
  APP_DB_USER = 'freehomerp',
} = process.env;

/**
 * @type { Object.<string, import("knex").Knex.Config> }
 */
export default {
  client: 'postgresql',
  connection: {
    database: APP_DB_NAME,
    host: APP_DB_HOST,
    port: APP_DB_PORT,
    user: APP_DB_USER,
    password: APP_DB_PASS,
  },
  migrations: {
    directory: './db/migrations',
  },
  seeds: {
    directory: './db/seeds',
  },
  pool: {
    min: 2,
    max: 10,
  },
};
