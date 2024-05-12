import type { LoginParams, LoginResult } from '$lib/context/application/login.usecase';
import type LoginUseCase from '$lib/context/application/login.usecase';
import Session from '$lib/context/domain/session/session.aggregate';
import type SessionRepository from '$lib/context/domain/session/session.repository';
import type UserRepository from '$lib/context/domain/user/user.repository';

export default class LoginUseCaseImpl implements LoginUseCase {
	private readonly _sessionRepository: SessionRepository;
	private readonly _userRepository: UserRepository;

	public constructor(deps: {
		userRepository: UserRepository;
		sessionRepository: SessionRepository;
	}) {
		this._sessionRepository = deps.sessionRepository;
		this._userRepository = deps.userRepository;
	}

	public async login({ username }: LoginParams): Promise<LoginResult> {
		const user = await this._userRepository.findBy({ username });
		if (!user) {
			return { user: null, session: null };
		}

		const session = Session.create(user.id);
		this._sessionRepository.create(session);

		return {
			user,
			session
		};
	}
}
