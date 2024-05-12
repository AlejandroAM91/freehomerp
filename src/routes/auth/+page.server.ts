import { fail, redirect } from '@sveltejs/kit';

import type LoginUseCase from '$lib/context/application/login.usecase';
import { CONFIG, CONFIG_ENV } from '$lib/server/config';
import { di } from '$lib/server/di';

import type { Actions, PageServerLoad } from './$types';

const ERROR_MESSAGE_FIELD_EMPTY = 'Empty username or password';
const ERROR_MESSAGE_FIELD_INVALID = 'Invalid username or password';

export const actions = {
	default: async ({ cookies, request }) => {
		const loginUseCase: LoginUseCase = di.resolve('loginUseCase');

		const data = await request.formData();
		const username = data.get('username')?.toString();
		const password = data.get('password')?.toString();
		if (!username || !password) {
			return fail(400, { error: ERROR_MESSAGE_FIELD_EMPTY });
		}

		const { user, session } = await loginUseCase.login({ username, password });
		if (!user || !session) {
			return fail(401, { error: ERROR_MESSAGE_FIELD_INVALID });
		}

		cookies.set(CONFIG.SESSION.COOKIE, session.id, {
			sameSite: 'strict',
			path: '/',
			maxAge: CONFIG.SESSION.MAX_AGE,
			httpOnly: true,
			secure: CONFIG.ENVIRONMENT === CONFIG_ENV.PRO,
			domain: CONFIG.SESSION.DOMAIN
		});
        return redirect(303, '/');
	}
} satisfies Actions;

export const load: PageServerLoad = async ({ cookies }) => {
	if(cookies.get(CONFIG.SESSION.COOKIE)) {
        return redirect(303, '/');
    }
};
