export type UserId = string;

export default class User {
	public constructor(
		public readonly id: UserId,
		public readonly username: string,
		public readonly password: string
	) {}
}
