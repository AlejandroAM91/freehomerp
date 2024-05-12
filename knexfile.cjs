/**
 * @type { Object.<string, import("knex").Knex.Config> }
 */
module.exports = {
	development: {
		client: 'postgresql',
		connection: {
			user: 'freehomerp',
			password: 'freehomerp',
			database: 'freehomerp'
		}
	},
	production: {
		client: 'postgresql',
		connection: {
			host: process.env.FREEHOMERP_DB_HOST,
			port: Number(process.env.FREEHOMERP_DB_PORT || 5432),
			user: process.env.FREEHOMERP_DB_USER,
			password: process.env.FREEHOMERP_DB_PASS,
			database: process.env.FREEHOMERP_DB_NAME
		},
		pool: {
			min: 2,
			max: 10
		}
	}
};
