package ecrow.backend.core.utilities.verification;

import ecrow.backend.core.utilities.results.ErrorResult;
import ecrow.backend.core.utilities.results.Result;
import ecrow.backend.core.utilities.results.SuccessResult;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmailVerificationManager implements EmailVerificationService{

    @Override
    public Result verify(String email) {
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches() ? new SuccessResult() : new ErrorResult("Invalid Email");

    }
}
