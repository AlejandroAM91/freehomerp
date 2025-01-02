package freehomerp

import (
	"log/slog"
	"os"

	"github.com/spf13/cobra"
)

var rootCmd = &cobra.Command{
	Use:   "freehomerp",
	Short: "freehomerp - Open Source Home Resource Planning",
	Long:  "Open Source Home Resource Planning",
	Run: func(cmd *cobra.Command, args []string) {

	},
}

func Execute() {
	logger := slog.New(slog.NewJSONHandler(os.Stdout, nil))
	slog.SetDefault(logger)

	if err := rootCmd.Execute(); err != nil {
		slog.Error("Error on command execution", slog.Any("error", err))
		os.Exit(1)
	}
}
