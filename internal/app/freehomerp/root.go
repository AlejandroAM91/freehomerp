package freehomerp

import (
	"context"
	"log/slog"
	"os"
	"os/signal"
	"syscall"

	"github.com/alejandroam91/freehomerp/internal/pkg/infra/api"
	"github.com/alejandroam91/freehomerp/internal/pkg/infra/data"
	"github.com/spf13/cobra"
)

var rootCmd = &cobra.Command{
	Use:   "freehomerp",
	Short: "freehomerp - Open Source Home Resource Planning",
	Long:  "Open Source Home Resource Planning",
	RunE: func(cmd *cobra.Command, args []string) error {
		shutdownChan := make(chan os.Signal, 1)
		signal.Notify(shutdownChan, syscall.SIGINT, syscall.SIGTERM)

		pgdb, err := data.NewPostgresDBRepository()
		if err != nil {
			return err
		}
		pgdb.Start()

		apiHttp, err := api.NewApiHttp()
		if err != nil {
			return err
		}

		apiHttp.Start()

		<-shutdownChan
		if err := apiHttp.Shutdown(context.Background()); err != nil {
			return err
		}

		if err := pgdb.Shutdown(context.Background()); err != nil {
			return err
		}

		return nil
	},
}

func Execute() {
	logger := slog.New(slog.NewJSONHandler(os.Stdout, nil))
	slog.SetDefault(logger)

	if err := rootCmd.Execute(); err != nil {
		os.Exit(1)
	}
}
