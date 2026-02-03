package com.dev.client.app.dto;

public record ErrorResponse(int status, String message, long timestamp) {}