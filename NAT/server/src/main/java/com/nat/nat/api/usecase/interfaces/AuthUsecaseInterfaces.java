package com.nat.nat.api.usecase.interfaces;

import java.util.List;

public interface AuthUsecaseInterfaces {
    public boolean isValidTokenUsecase(List<String> authHeaders);
}