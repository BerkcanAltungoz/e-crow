package ecrow.backend.core.utilities.verification;

import ecrow.backend.core.utilities.results.Result;



public interface EmailVerificationService {
    public Result verify(String email);
}
