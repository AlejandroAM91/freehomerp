import { asClass, asValue, createContainer, Lifetime } from 'awilix';
import knex from 'knex';

import { CONFIG } from './config';
import LoginUseCaseImpl from './context/application/login.usecase';
import SessionSqlRepositoryImpl from './context/infrastructure/sql/session.repository';
import UserSqlRepositoryImpl from './context/infrastructure/sql/user.repository';

export const di = createContainer({ strict: true });
di.register({
	loginUseCase: asClass(LoginUseCaseImpl, { lifetime: Lifetime.SINGLETON }),

	sessionRepository: asClass(SessionSqlRepositoryImpl, { lifetime: Lifetime.SINGLETON }),
	userRepository: asClass(UserSqlRepositoryImpl, { lifetime: Lifetime.SINGLETON })
});

{
	// Database definition
	const db = knex({
		client: 'pg',
		connection: {
			host: CONFIG.DB.HOST,
			port: CONFIG.DB.PORT,
			user: CONFIG.DB.USER,
			password: CONFIG.DB.PASS,
			database: CONFIG.DB.NAME
		}
	});
	di.register({ db: asValue(db) });
}
