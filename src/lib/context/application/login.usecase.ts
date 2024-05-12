import type Session from '../domain/session/session.aggregate';
import type User from '../domain/user/user.aggregate';

export type LoginParams = { username: string; password: string };
export type LoginResult = { user: User | null; session: Session | null };

export default interface LoginUseCase {
	login(params: LoginParams): Promise<LoginResult>;
}
