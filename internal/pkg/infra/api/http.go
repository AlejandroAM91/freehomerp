package api

import (
	"context"
	"fmt"
	"log/slog"
	"net/http"

	"github.com/labstack/echo/v4"
)

type HttpApi struct {
	logger *slog.Logger
	server *echo.Echo
}

func NewApiHttp() (*HttpApi, error) {
	server := echo.New()
	server.HideBanner = true
	server.HidePort = true

	apiHttp := &HttpApi{
		logger: slog.With(slog.String("component", "API::Http")),
		server: server,
	}

	setupStatusEndpoints(apiHttp)

	return apiHttp, nil
}

func (api *HttpApi) Start() {
	api.logger.Info("Starting component.")
	var address = ":8080"
	go func() {
		api.logger.Info(fmt.Sprintf("Starting http server on %s", address))
		if err := api.server.Start(address); err != nil && err != http.ErrServerClosed {
			api.logger.Error("HTTP Server error", slog.Any("error", err))
			panic(err)
		}
	}()
}

func (api *HttpApi) Shutdown(ctx context.Context) error {
	api.logger.Info("Shutting down component.")
	return api.server.Shutdown(ctx)
}

func setupStatusEndpoints(api *HttpApi) {
	api.server.GET("/health", func(c echo.Context) error {
		return c.String(200, "OK")
	})
}
