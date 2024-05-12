import { CONFIG } from '$lib/server/config';

import type { UserId } from '../user/user.aggregate';

export type SessionId = string;

export default class Session {
	public constructor(
		public readonly id: SessionId,
		public readonly userId: UserId,
		public readonly createdAt: Date,
		public readonly expiresAt: Date
	) {}

	public static create(userId: UserId): Session {
		return new Session(
			crypto.randomUUID(),
			userId,
			new Date(),
			new Date(Date.now() + CONFIG.SESSION.MAX_AGE * 1000)
		);
	}
}
