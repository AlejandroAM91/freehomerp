package api

import (
	"context"
	"fmt"
	"log/slog"
	"net/http"

	"github.com/labstack/echo/v4"
)

type ApiHttp struct {
	logger *slog.Logger
	server *echo.Echo
}

func NewApiHttp() (*ApiHttp, error) {
	server := echo.New()
	server.HideBanner = true
	server.HidePort = true

	apiHttp := &ApiHttp{
		logger: slog.With(slog.String("component", "ApiHttp")),
		server: server,
	}

	setupStatusEndpoints(apiHttp)

	return apiHttp, nil
}

func (api *ApiHttp) Start() {
	var address = ":8080"
	go func() {
		api.logger.Info(fmt.Sprintf("Starting server on %s", address))
		if err := api.server.Start(address); err != nil && err != http.ErrServerClosed {
			api.logger.Error("HTTP Server error", slog.Any("error", err))
			panic(err)
		}
	}()
}

func (api *ApiHttp) Shutdown(ctx context.Context) error {
	api.logger.Info("Shutting down server")
	return api.server.Shutdown(ctx)
}

func setupStatusEndpoints(api *ApiHttp) {
	api.server.GET("/health", func(c echo.Context) error {
		return c.String(200, "OK")
	})
}
