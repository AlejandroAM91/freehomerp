/**
 * @param { import("knex").Knex } knex
 */
exports.up = function (knex) {
	return knex.schema
		.createTable('groups', function (table) {
			table.string('id', 36).primary();
			table.string('name', 50).notNullable();
		})
		.createTable('subgroups', function (table) {
			table.string('id', 36).primary();
			table.string('group_id', 36).notNullable().references('id').inTable('groups');
			table.string('name', 50).notNullable();
		})
		.createTable('users', function (table) {
			table.string('id', 36).primary();
			table.string('username', 50).notNullable().index();
			table.string('password', 60).notNullable();
		})
		.createTable('sessions', function (table) {
			table.string('id', 36).primary();
			table.string('user_id', 36).notNullable().references('id').inTable('users');
			table.datetime('created_at').notNullable();
			table.datetime('expires_at').notNullable();
		})
		.createTable('users_subgroups', function (table) {
			table.string('user_id', 36).notNullable().references('id').inTable('users');
			table.string('subgroup_id', 36).notNullable().references('id').inTable('subgroups');
			table.primary(['user_id', 'subgroup_id']);
		});
};

/**
 * @param { import("knex").Knex } knex
 */
exports.down = function (knex) {
	return knex.schema
		.dropTableIfExists('users_subgroups')
		.dropTableIfExists('sessions')
		.dropTableIfExists('users')
		.dropTableIfExists('subgroups')
		.dropTableIfExists('groups');
};
