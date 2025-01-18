FROM node:22-alpine3.21 AS base
ENV PNPM_HOME="/pnpm"
ENV PATH="$PNPM_HOME:$PATH"
RUN corepack enable

WORKDIR /app

FROM base AS migrator

ENV NODE_ENV=production

RUN pnpm install -g knex pg
ADD knex.config.js .
ADD db db

ENTRYPOINT [ "knex", "--knexfile", "knex.config.js" ]
CMD ["migrate:latest"]

FROM base AS builder

ADD package.json pnpm-lock.yaml ./
RUN pnpm i --frozen-lockfile --ignore-scripts
ADD . .
RUN pnpm build

FROM base AS app

ENV NODE_ENV=production HOST=0.0.0.0 PORT=3000 ORIGIN=http://localhost:3000
EXPOSE 3000

ADD package.json pnpm-lock.yaml ./
RUN pnpm i --prod --frozen-lockfile --ignore-scripts
COPY --from=builder /app/build/ ./

CMD ["node", "index.js"]
