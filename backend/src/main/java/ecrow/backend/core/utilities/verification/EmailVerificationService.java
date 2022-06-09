package ecrow.backend.core.utilities.verification;

import kodlamaio.hrms.core.utilities.results.Result;


public interface EmailVerificationService {
    public Result verify(String email);
}
