const bcrypt = require('bcrypt');

/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.seed = async function (knex) {
	const groups = [
		{
			id: crypto.randomUUID(),
			name: 'Home'
		}
	];

	const subgroups = groups.map((group) => ({
		id: crypto.randomUUID(),
		group_id: group.id,
		name: 'General'
	}));

	const users = [
		{
			id: crypto.randomUUID(),
			username: 'admin',
			password: bcrypt.hashSync('admin', 10)
		}
	];

	const users_subgroups = subgroups.flatMap((subgroup) => {
		return users.map((user) => ({
			user_id: user.id,
			subgroup_id: subgroup.id
		}));
	});

	// Deletes ALL existing entries
	await knex('users').del();
	await knex('subgroups').del();
	await knex('groups').del();

	await knex('groups').insert(groups);
	await knex('subgroups').insert(subgroups);
	await knex('users').insert(users);
	await knex('users_subgroups').insert(users_subgroups);
};
