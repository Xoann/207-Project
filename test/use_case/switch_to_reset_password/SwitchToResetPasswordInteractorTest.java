package use_case.switch_to_reset_password;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.verify;

public class SwitchToResetPasswordInteractorTest {

    @Mock
    private SwitchToResetPasswordOutputBoundary mockPresenter;

    private SwitchToResetPasswordInteractor interactor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        interactor = new SwitchToResetPasswordInteractor(mockPresenter);
    }

    @Test
    public void execute_CallsPrepareSuccessView() {
        interactor.execute();

        verify(mockPresenter).prepareSuccessView();
    }
}
