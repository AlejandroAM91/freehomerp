import { env } from '$env/dynamic/private';

if (env.DB_PASS == null) {
    throw new Error('DB_PASS environment variable not set');
}

export enum CONFIG_ENV {
	DEV = 'development',
	PRO = 'production'
}

export const CONFIG = {
	DB: {
		HOST: env.DB_HOST ?? 'localhost',
		PORT: Number(env.DB_PORT ?? 5432),
		USER: env.DB_USER ?? 'freehomerp',
		PASS: env.DB_PASS,
		NAME: env.DB_NAME ?? 'freehomerp'
	},
	ENVIRONMENT: env.NODE_ENV ?? CONFIG_ENV.DEV,
	SESSION: {
		COOKIE: env.SESSION_COOKIE ?? 'freehomerp_sessionid',
		DOMAIN: env.SESSION_DOMAIN ?? 'localhost',
		MAX_AGE: Number(env.SESSION_MAX_AGE ?? 3600)
	}
};
