<script lang="ts">
	import { enhance } from '$app/forms';

	import type { ActionData } from './$types';

	export let form: ActionData;
</script>

<div class="w-full max-w-sm mx-auto mt-8">
	<h1 class="my-8 text-center text-emerald-600 text-2xl font-bold">Login</h1>

	<form method="post" use:enhance class="w-full flex flex-col gap-2">
		<div class="flex flex-row">
			<label for="username" class="basis-1/3"> Username: </label>
			<input
				type="text"
				name="username"
				class="basis-2/3 border border-emerald-600 focus:outline-none focus:ring focus:ring-emerald-400 rounded"
				required
			/>
		</div>

		<div class="flex flex-row">
			<label for="password" class="basis-1/3"> Password: </label>
			<input
				type="password"
				name="password"
				class="basis-2/3 border border-emerald-600 focus:outline-none focus:ring focus:ring-emerald-400 rounded"
				required
			/>
		</div>

		<div class="basis-full text-center text-red-600" class:invisible={!form?.error}>
			{form?.error}
		</div>

		<div class="mt-3 basis-full flex flex-row justify-center">
			<button class="p-2 text-white bg-emerald-600 hover:bg-emerald-700 rounded">Login</button
			>
		</div>
	</form>
</div>
