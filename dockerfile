FROM bitnami/node:20

ENV NODE_ENV production

WORKDIR /app

COPY package.json pnpm-lock.yaml ./
RUN corepack enable && pnpm i --frozen-lockfile
COPY build build
