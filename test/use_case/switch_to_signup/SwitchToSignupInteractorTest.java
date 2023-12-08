package use_case.switch_to_signup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.verify;

public class SwitchToSignupInteractorTest {

    @Mock
    private SwitchToSignupOutputBoundary mockPresenter;

    private SwitchToSignupInteractor interactor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        interactor = new SwitchToSignupInteractor(mockPresenter);
    }

    @Test
    public void execute_CallsPrepareSuccessView() {
        interactor.execute();

        verify(mockPresenter).prepareSuccessView();
    }
}
