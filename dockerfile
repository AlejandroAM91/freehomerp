FROM golang:1.23-alpine3.21 AS builder

WORKDIR /app

# Download Go modules
COPY go.mod go.sum ./
RUN go mod download

# Copy source files
COPY cmd cmd
COPY internal internal

# Compile source files
RUN CGO_ENABLED=0 GOOS=linux go build -o freehomerp ./cmd/freehomerp


# Create run image
FROM scratch

EXPOSE 8080

COPY --from=builder /app/freehomerp /freehomerp

ENTRYPOINT ["/freehomerp"]
