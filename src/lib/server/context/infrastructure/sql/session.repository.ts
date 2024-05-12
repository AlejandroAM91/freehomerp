import type { Knex } from 'knex';

import type Session from '$lib/context/domain/session/session.aggregate';
import type SessionRepository from '$lib/context/domain/session/session.repository';

type SessionsView = {
	id: string;
	user_id: string;
	created_at: Date;
	expires_at: Date;
};

export default class SessionSqlRepositoryImpl implements SessionRepository {
	private readonly _db: Knex;

	public constructor(deps: { db: Knex }) {
		this._db = deps.db;
	}

	public async create(session: Session): Promise<void> {
		await this._db<SessionsView>('sessions').insert(mapEntityToView(session));
	}
}

function mapEntityToView({ id, userId, createdAt, expiresAt }: Session): SessionsView {
	return { id, user_id: userId, created_at: createdAt, expires_at: expiresAt };
}
