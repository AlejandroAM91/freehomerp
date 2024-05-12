import type { Knex } from 'knex';

import User from '$lib/context/domain/user/user.aggregate';
import type UserRepository from '$lib/context/domain/user/user.repository';
import type { FindUserByCriteria } from '$lib/context/domain/user/user.repository';

type UsersView = {
	id: string;
	username: string;
	password: string;
};

export default class UserSqlRepositoryImpl implements UserRepository {
	private readonly _db: Knex;

	public constructor(deps: { db: Knex }) {
		this._db = deps.db;
	}

	public async findBy({ username }: FindUserByCriteria): Promise<User | null> {
		let query = this._db<UsersView>('users').first();
		if (username) {
			query = query.where('users.username', username);
		}

		const view = await query;
		return view != null ? mapViewToEntity(view) : null;
	}
}

function mapViewToEntity({ id, username, password }: UsersView): User {
	return new User(id, username, password);
}
