/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
export function up(knex) {
  knex.schema.createTable('accounts', (table) => {
    table.uuid('id').defaultTo(knex.fn.uuid());
    table.string('code').defaultTo(knex.fn.uuid());
  });
}

/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
export function down(knex) {
  knex.schema.dropTable('accounts');
}
