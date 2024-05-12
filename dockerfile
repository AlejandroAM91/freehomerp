FROM node:20-alpine3.18 AS base

ENV PNPM_HOME="/pnpm"
ENV PATH="$PNPM_HOME:$PATH"

WORKDIR /app

RUN corepack enable

COPY package.json pnpm-lock.yaml ./

FROM base AS builder

RUN pnpm install --frozen-lockfile
COPY postcss.config.js svelte.config.js tailwind.config.js vite.config.ts ./
COPY static static
COPY src src
RUN pnpm run build

FROM base AS runner

LABEL org.opencontainers.image.title="FreeHomeRP"
LABEL org.opencontainers.image.description="Free Home Resource Planning"
LABEL org.opencontainers.image.authors="Alejandro Alonso Mayo <alejandroalonsomayo@gmail.com>"
LABEL org.opencontainers.image.source=https://github.com/AlejandroAM91/freehomerp
LABEL org.opencontainers.image.version=0.0.1
LABEL org.opencontainers.image.licenses=MIT

EXPOSE 3000

ENV FREEHOMERP_ORIGIN=http://localhost:3000
ENV NODE_ENV=production

RUN pnpm install
COPY --from=builder /app/build .
COPY .deploy/banner.txt .deploy/entrypoint.sh knexfile.cjs ./
COPY migrations migrations

CMD [ "./entrypoint.sh" ]
