#!/bin/sh
set -e

FREEHOMERP_DB_MIGRATION="${FREEHOMERP_DB_MIGRATION:-true}"

freehomerp_banner() {
    echo -e "\e[0;33m"
    cat banner.txt
    echo -e "\e[m"
}

freehomerp_db_migrate() {
    if [ "$FREEHOMERP_DB_MIGRATION" = true ]; then
        echo -e "\e[0;32mMigrating database...\e[m"
        pnpm exec knex --knexfile knexfile.cjs migrate:latest
    fi
}

freehomerp_start() {
    export SESSION_COOKIE=$FREEHOMERP_SESSION_COOKIE
    export SESSION_DOMAIN=$FREEHOMERP_SESSION_DOMAIN
    export SESSION_MAX_AGE=$FREEHOMERP_SESSION_MAX_AGE

    echo -e "\e[0;32mStarting server...\e[m"
    node index.js
}

freehomerp_banner
freehomerp_db_migrate
freehomerp_start
