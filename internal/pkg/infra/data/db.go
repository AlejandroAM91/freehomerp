package data

import (
	"context"
	"log/slog"

	"github.com/golang-migrate/migrate/v4"
	_ "github.com/golang-migrate/migrate/v4/database/postgres"
	_ "github.com/golang-migrate/migrate/v4/source/file"
)

type PostgresDBRepository struct {
	logger *slog.Logger
}

func NewPostgresDBRepository() (*PostgresDBRepository, error) {
	pgdb := &PostgresDBRepository{
		logger: slog.With(slog.String("component", "Data::PostgresDB")),
	}

	return pgdb, nil
}

func (pgdb *PostgresDBRepository) Start() {
	pgdb.logger.Info("Starting component.")

	m, err := migrate.New(
		"file://./migrations",
		"postgres://freehomerp:freehomerp@localhost:5432/freehomerp?sslmode=disable")
	if err != nil {
		pgdb.logger.Error("DB Migration error", slog.Any("error", err))
		panic(err)
	}
	err = m.Up()
	if err != nil && err != migrate.ErrNoChange {
		pgdb.logger.Error("DB Migration error", slog.Any("error", err))
		panic(err)
	}

}

func (pgdb *PostgresDBRepository) Shutdown(ctx context.Context) error {
	pgdb.logger.Info("Shutting down component.")
	return nil
}
