import User from './user.aggregate';

export type FindUserByCriteria = Partial<{ username: string }>;

export default interface UserRepository {
	findBy(criteria: FindUserByCriteria): Promise<User | null>;
}
